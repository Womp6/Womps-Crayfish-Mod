package womp.crayfish.mod.blocks;

import org.jetbrains.annotations.VisibleForTesting;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import womp.crayfish.mod.CrayfishMod;
import womp.crayfish.mod.entity.CrayfishEntity;
import womp.crayfish.mod.registry.CrayfishEntityInit;
import womp.crayfish.mod.registry.CrayfishSoundsInit;

public class CrayfishEggBlock extends Block implements Waterloggable {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 1.5, 16.0);
    private static int minHatchTime = 3600;
    private static int maxHatchTime = 12000;

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.WATERLOGGED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState()
            .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public CrayfishEggBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return CrayfishEggBlock.canLayAt(world, pos.down());
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.scheduleBlockTick(pos, this, CrayfishEggBlock.getHatchTime(world.getRandom()));
    }

    private static int getHatchTime(Random random) {
        return random.nextBetweenExclusive(minHatchTime, maxHatchTime);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!this.canPlaceAt(state, world, pos)) {
            return Blocks.WATER.getDefaultState();
        }
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!this.canPlaceAt(state, world, pos)) {
            this.breakWithoutDrop(world, pos);
            return;
        }
        this.hatch(world, pos, random);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity.getType().equals(EntityType.FALLING_BLOCK)) {
            this.breakWithoutDrop(world, pos);
        }
    }

    private static boolean canLayAt(BlockView world, BlockPos pos) {
        FluidState blockState = world.getFluidState(pos);
        FluidState fluidState2 = world.getFluidState(pos.up());
        return blockState.getFluid() == Fluids.EMPTY && fluidState2.getFluid() == Fluids.WATER;
    }

    private void hatch(ServerWorld world, BlockPos pos, Random random) {
        this.breakWithoutDrop(world, pos);
        world.playSound(null, pos, CrayfishSoundsInit.CRAYFISHES_HATCH, SoundCategory.BLOCKS, 1.0f, 1.0f);
        this.spawnCrayfishes(world, pos, random);
    }

    private void breakWithoutDrop(World world, BlockPos pos) {
        world.breakBlock(pos, false);
    }

    private void spawnCrayfishes(ServerWorld world, BlockPos pos, Random random) {
        int i = random.nextBetweenExclusive(2, 5);
        for (int j = 1; j <= i; ++j) {
            CrayfishEntity child = CrayfishEntityInit.CRAYFISH.create(world);
            if (child == null) continue;
            child.setBreedingAge(-24000);
            double d = (double)pos.getX() + this.getSpawnOffset(random);
            double e = (double)pos.getZ() + this.getSpawnOffset(random);
            int k = random.nextBetweenExclusive(1, 361);
            child.refreshPositionAndAngles(d, (double)pos.getY() - 0, e, k, 0.0f);
            child.setPersistent();
            child.setVariant(random.nextInt(CrayfishEntity.MAX_VARIANTS));
            world.spawnEntity(child);
        }
    }

    private double getSpawnOffset(Random random) {
        double d = 0.1875;
        return MathHelper.clamp(random.nextDouble(), d, 1.0 - d);
    }

    @VisibleForTesting
    public static void setHatchTimeRange(int min, int max) {
        minHatchTime = min;
        maxHatchTime = max;
    }

    @VisibleForTesting
    public static void resetHatchTimeRange() {
        minHatchTime = 3600;
        maxHatchTime = 12000;
    }
    
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        if(stack.isOf(Items.BUCKET)) {
            ItemStack itemStack = new ItemStack(CrayfishMod.CAVIAR_BUCKET);
            ItemStack itemStack2 = ItemUsage.exchangeStack(stack, player, itemStack, false);
            player.setStackInHand(hand, itemStack2);
            world.playSound(null, pos.getX() + 0.5d, pos.getY(), pos.getZ() + 0.5d, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1, 1);
            world.setBlockState(pos, (state.get(WATERLOGGED) ? Blocks.WATER : Blocks.AIR).getDefaultState(), Block.NOTIFY_ALL);
            return ActionResult.success(world.isClient);
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}
