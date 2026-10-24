package womp.crayfish.mod.entity;

import java.util.EnumSet;
import java.util.function.Predicate;

import org.jetbrains.annotations.Nullable;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Bucketable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TadpoleEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameRules;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import womp.crayfish.mod.CrayfishMod;
import womp.crayfish.mod.items.CrayfishTags;
import womp.crayfish.mod.registry.CrayfishEntityInit;
import womp.crayfish.mod.registry.CrayfishSoundsInit;

public class CrayfishEntity extends AnimalEntity implements Bucketable {

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState moveAnimationState = new AnimationState();

    public static final int MAX_VARIANTS = 28;
    private int tickCounter = 0;
    private static final TrackedData<Integer> VARIANT = DataTracker.registerData(CrayfishEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> FROM_BUCKET = DataTracker.registerData(CrayfishEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> HAS_EGG = DataTracker.registerData(CrayfishEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> IS_HUNGRY = DataTracker.registerData(CrayfishEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public CrayfishEntity(EntityType<? extends CrayfishEntity> entityType, World world) {
        super((EntityType<? extends AnimalEntity>)entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0f);
    }

    public static DefaultAttributeContainer.Builder createCrayfishAttributes() {
        return HostileEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0d).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0d).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.14);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new CrayfishLayEggGoal(this, 1));
        this.goalSelector.add(1, new CrayfishMateGoal(this, 1));
        this.goalSelector.add(1, new FollowParentGoal(this, 1.1));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1d, true));
        this.targetSelector.add(2, new RevengeGoal(this, new Class[0]));
        this.targetSelector.add(2, new HungryActiveTargetGoal<>(this, TadpoleEntity.class, false));
        this.targetSelector.add(2, new HungryActiveTargetGoal<>(this, FishEntity.class, false));
        this.targetSelector.add(2, new HungryActiveTargetGoal<>(this, TurtleEntity.class, 10, true, true, (entity) -> {
            return entity.isBaby();
        }));
        this.goalSelector.add(3, new WanderInWaterGoal(this, 1));
        this.goalSelector.add(5, new WanderToWaterGoal(this, 1));
        this.goalSelector.add(7, new WanderAroundGoal(this, 1));
        this.goalSelector.add(9, new LookAroundGoal(this));
    }

    @Override
    public boolean isBreedingItem(ItemStack item) {
        return item.isIn(CrayfishTags.Items.CRAYFISH_FOOD);
    }
    
    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        CrayfishEntity child;
        if((child = CrayfishEntityInit.CRAYFISH.create(world)) != null && entity instanceof CrayfishEntity mate) {
            child.setVariant((random.nextBoolean() ? this : mate).getVariant());
            child.setPersistent();
            return child;
        }
        return null;
    }

    public boolean isIdle() {
        return this.prevX == this.getX() && this.prevZ == this.getZ();
    }

    public boolean isWalking() {
        if (this.prevX != this.getX()) {
            return true;
        }
        if (this.prevZ != this.getZ()) {
            return true;
        }
        return false;
    }

    private void setupAnimationStates() {
       
        if (isIdle()) {
            this.idleAnimationState.startIfNotRunning(this.age);
        }

        if (this.isWalking()) {
            this.moveAnimationState.startIfNotRunning(this.age);
        }

        if (!isIdle()) {
            this.idleAnimationState.stop();
        }

        if (isIdle()) {
            this.moveAnimationState.stop();
        }
    }

    @Override
    public void tick() {
        super.tick();

        //ANIMATION
        if (this.getWorld().isClient()) {
            this.setupAnimationStates();
        }

        //HUNGRY
        tickCounter++;

        if (tickCounter < 6000) {
            this.setHungry(false);
        }

        if (tickCounter >= 6000 && !this.isHungry()) {
            this.setHungry(true);
        }

        if (this.isHungry() && tickCounter >= 6600) {
            this.setHungry(false);
            tickCounter = 0;
        }
    }

    @Override
    public boolean canSpawn(WorldView world) {
        return world.doesNotIntersectEntities(this);
    }

    public static boolean canSpawn(EntityType<CrayfishEntity> type, ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random random) {
        int i = world.getSeaLevel();
        int j = i - 26;
        return pos.getY() >= j && pos.getY() <= i && world.getFluidState(pos.down()).isIn(FluidTags.WATER) && world.getBlockState(pos.up()).isOf(Blocks.WATER);
    }

    @Override
    public EntityGroup getGroup() {
        return EntityGroup.AQUATIC;
    }

    public boolean hasEgg() {
        return this.dataTracker.get(HAS_EGG);
    }

    void setHasEgg(boolean hasEgg) {
        this.dataTracker.set(HAS_EGG, hasEgg);
    }

    public boolean isHungry() {
        return this.dataTracker.get(IS_HUNGRY);
    }

    void setHungry(boolean isHungry) {
        this.dataTracker.set(IS_HUNGRY, isHungry);
    }

    @Override
    public boolean isFromBucket() {
        return this.dataTracker.get(FROM_BUCKET);
    }

    @Override
    public boolean isPushedByFluids() {
        return false;
    }

    public void travel(Vec3d movementInput) {
        if (this.isTouchingWater()) {
            this.updateVelocity(0.01f, movementInput.multiply(50));
            this.move(MovementType.SELF, this.getVelocity());
            if (!this.isAttacking()) {
                this.setVelocity(this.getVelocity().multiply(.8, .825, .8).add(0, -0.01d, 0));
            }
            if (this.isAttacking()) {
                this.setVelocity(this.getVelocity().multiply(.8, .85, .8).add(0, -0.01, 0));
            }
        } else {
            super.travel(movementInput);
        }

    }

    @Override
    public int getLimitPerChunk() {
        return 4;
    }

    @Override
    public void setFromBucket(boolean fromBucket) {
        this.dataTracker.set(FROM_BUCKET, fromBucket);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(VARIANT, 0);
        this.dataTracker.startTracking(FROM_BUCKET, false);
        this.dataTracker.startTracking(HAS_EGG, false);
        this.dataTracker.startTracking(IS_HUNGRY, false);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            Entity entity = source.getAttacker();
            if (entity != null && !(entity instanceof PlayerEntity) && !(entity instanceof ArrowEntity)) {
                amount = (amount + 1.0f) / 2.0f;
            }
            return super.damage(source, amount);
        }
    }

    @Override
    public boolean tryAttack(Entity target) {
        boolean bl = target.damage(this.getDamageSources().mobAttack(this), (float)(this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE)));
        if (bl) {
           this.applyDamageEffects(this, target);
           this.playSound(CrayfishSoundsInit.CRAYFISH_ATTACK, 1.0f, 1.0f);
        }
  
        return bl;
     }

    public int getVariant() {
        return this.dataTracker.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.dataTracker.set(VARIANT, variant);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getVariant());
        nbt.putBoolean("FromBucket", this.isFromBucket());
        nbt.putBoolean("HasEgg", this.hasEgg());
        nbt.putBoolean("IsHungry", this.isHungry());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        setVariant(MathHelper.clamp(nbt.getInt("Variant"), 0, MAX_VARIANTS - 1));
        this.setFromBucket(nbt.getBoolean("FromBucket"));
        this.setHasEgg(nbt.getBoolean("HasEgg"));
        this.setHungry(nbt.getBoolean("IsHungry"));
    }

    @Override
    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, NbtCompound entityNbt) {
        if (entityNbt == null) {
            double chance = getRandom().nextDouble();
            if (chance <= 0.95) setVariant(getRandom().nextInt(MAX_VARIANTS));
        } else {
            if (entityNbt.contains("Variant", 3)) {
                this.setVariant(entityNbt.getInt("Variant"));
            }
            if (entityNbt.contains("Health", 99)) {
                this.setHealth(entityNbt.getFloat("Health"));
            }
        }
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    @Override
    public void copyDataToStack(ItemStack bucket) {
        NbtCompound nbtCompound = bucket.getOrCreateNbt();
        nbtCompound.putInt("Variant", this.getVariant());
        nbtCompound.putFloat("Health", this.getHealth());
        nbtCompound.putInt("Age", this.getBreedingAge());
    }

    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(CrayfishMod.CRAYFISH_BUCKET);
    }

    @Override
    @Nullable
    protected SoundEvent getDeathSound() {
        return CrayfishSoundsInit.CRAYFISH_DEATH;
    }

    @Override
    @Nullable
    protected SoundEvent getHurtSound(DamageSource source) {
        return CrayfishSoundsInit.CRAYFISH_HURT;
    }

    @Override
    public boolean canBeLeashedBy(PlayerEntity player) {
        return false;
    }
    
    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        return Bucketable.tryBucket(player, hand, this).orElse(super.interactMob(player, hand));
    }

    @Override
    public boolean cannotDespawn() {
        return super.cannotDespawn() || this.isFromBucket();
    }

    @Override
    public int getMaxAir() {
        return 16000;
    }

    public void hydrateFromPotion() {
        int i = this.getAir() + 70;
        this.setAir(Math.min(i, this.getMaxAir()));
    }

    protected void tickAir(int air) {
        if (this.isAlive() && !this.isWet()) {
            this.setAir(air - 1);
            if (this.getAir() == -20) {
                this.setAir(0);
                this.damage(getWorld().getDamageSources().dryOut(), 2.0f);
            }
        } else {
            this.setAir(this.getMaxAir());
        }
    }

    @Override
    public void baseTick() {
        int i = this.getAir();
        super.baseTick();
        if (!this.isAiDisabled()) {
            this.tickAir(i);
        }
    }

    @Override
    public void copyDataFromNbt(NbtCompound nbt) {
        if (nbt.contains("Age")) {
            this.setBreedingAge(nbt.getInt("Age"));
        }
    }

    static class WanderInWaterGoal
    extends Goal {
        public static final int DEFAULT_CHANCE = 120;
    protected final PathAwareEntity mob;
    protected double targetX;
    protected double targetY;
    protected double targetZ;
    protected final double speed;
    protected int chance;
    protected boolean ignoringChance;
    private final boolean canDespawn;
    private final World world;

    public WanderInWaterGoal(PathAwareEntity mob, double speed) {
        this(mob, speed, 120);
    }

    public WanderInWaterGoal(PathAwareEntity mob, double speed, int chance) {
        this(mob, speed, chance, true);
    }

    public WanderInWaterGoal(PathAwareEntity entity, double speed, int chance, boolean canDespawn) {
        this.mob = entity;
        this.speed = speed;
        this.chance = chance;
        this.canDespawn = canDespawn;
        this.world = mob.getWorld();
        this.setControls(EnumSet.of(Goal.Control.MOVE));
    }

    @Override
    public boolean canStart() {
        Vec3d vec3d;
        if (!this.ignoringChance) {
            if (this.canDespawn && this.mob.getDespawnCounter() >= 100) {
                return false;
            }
            if (this.mob.getRandom().nextInt(WanderAroundGoal.toGoalTicks(this.chance)) != 0) {
                return false;
            }
        }
        if ((vec3d = this.getWanderTarget()) == null) {
            return false;
        }
        this.targetX = vec3d.x;
        this.targetY = vec3d.y;
        this.targetZ = vec3d.z;
        this.ignoringChance = false;
        return this.mob.isTouchingWater() && this.mob.getFluidHeight(FluidTags.WATER) > this.mob.getSwimHeight() || this.mob.isInLava();
    }

    @Nullable
    protected Vec3d getWanderTarget() {
         net.minecraft.util.math.random.Random random = this.mob.getRandom();
            BlockPos blockPos = this.mob.getBlockPos();
            for (int i = 0; i < 10; ++i) {
                BlockPos blockPos2 = blockPos.add(random.nextInt(6) - 3, 2 - random.nextInt(4), random.nextInt(6) - 3);
                if (!this.world.getBlockState(blockPos2).isOf(Blocks.WATER)) continue;
                return Vec3d.ofBottomCenter(blockPos2);
            }
            return null;
    }

    @Override
    public boolean shouldContinue() {
        return !this.mob.getNavigation().isIdle();
    }

    @Override
    public void start() {
        this.mob.getNavigation().startMovingTo(this.targetX, this.targetY, this.targetZ, this.speed);
    }

    @Override
    public void stop() {
        this.mob.getNavigation().stop();
        super.stop();
    }

    public void ignoreChanceOnce() {
        this.ignoringChance = true;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }
    }

    static class WanderToWaterGoal
    extends Goal {
        private final PathAwareEntity mob;
        private double x;
        private double y;
        private double z;
        private final double speed;
        private final World world;

        public WanderToWaterGoal(PathAwareEntity mob, double speed) {
            this.mob = mob;
            this.speed = speed;
            this.world = mob.getWorld();
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }

        @Override
        public boolean canStart() {
            if (this.mob.isTouchingWater()) {
                return false;
            };
            Vec3d vec3d = this.getWanderTarget();
            if (vec3d == null) {
                return false;
            }
            this.x = vec3d.x;
            this.y = vec3d.y;
            this.z = vec3d.z;
            return true;
        }

        @Override
        public boolean shouldContinue() {
            return !this.mob.getNavigation().isIdle();
        }

        @Override
        public void start() {
            this.mob.getNavigation().startMovingTo(this.x, this.y, this.z, this.speed);
        }

        @Nullable
        private Vec3d getWanderTarget() {
            net.minecraft.util.math.random.Random random = this.mob.getRandom();
            BlockPos blockPos = this.mob.getBlockPos();
            for (int i = 0; i < 10; ++i) {
                BlockPos blockPos2 = blockPos.add(random.nextInt(6) - 3, 1 - random.nextInt(4), random.nextInt(6) - 3);
                if (!this.world.getBlockState(blockPos2).isOf(Blocks.WATER)) continue;
                return Vec3d.ofBottomCenter(blockPos2);
            }
            return null;
        }
    }

    @Override
    public SoundEvent getBucketFillSound() {
        return SoundEvents.ITEM_BUCKET_FILL_FISH;
    }

    @Override
    public boolean canBreatheInWater() {
        return true;
    }

    static class CrayfishMateGoal extends AnimalMateGoal {

        private final CrayfishEntity crayfish;

        CrayfishMateGoal(CrayfishEntity crayfish, double speed) {
            super(crayfish, speed);
            this.crayfish = crayfish;
        }

        @Override
        public boolean canStart() {
            return super.canStart() && !this.crayfish.hasEgg();
        }

        @Override
        protected void breed() {
            ServerPlayerEntity serverPlayerEntity = this.animal.getLovingPlayer();
            if (serverPlayerEntity == null && this.mate.getLovingPlayer() != null) {
                serverPlayerEntity = this.mate.getLovingPlayer();
            }
            if (serverPlayerEntity != null) {
                serverPlayerEntity.incrementStat(Stats.ANIMALS_BRED);
                Criteria.BRED_ANIMALS.trigger(serverPlayerEntity, this.animal, this.mate, null);
            }
            this.crayfish.setHasEgg(true);
            this.animal.setBreedingAge(6000);
            this.mate.setBreedingAge(6000);
            this.animal.resetLoveTicks();
            this.mate.resetLoveTicks();
            Random random = this.animal.getRandom();
            if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
                this.world.spawnEntity(new ExperienceOrbEntity(this.world, this.animal.getX(), this.animal.getY(), this.animal.getZ(), random.nextInt(7) + 1));
            }
        }
    }

    static class CrayfishLayEggGoal
    extends MoveToTargetPosGoal {
        private final CrayfishEntity crayfish;
        

        CrayfishLayEggGoal(CrayfishEntity crayfish, double speed) {
            super(crayfish, speed, 16);
            this.crayfish = crayfish;
        }

        @Override
        public boolean canStart() {
            if (this.crayfish.hasEgg()) {
                return super.canStart();
            }
            return false;
        }

        @Override
        public boolean shouldContinue() {
            return super.shouldContinue() && this.crayfish.hasEgg();
        }

        @Override
        public void tick() {
            super.tick();
            BlockPos blockPos = this.crayfish.getBlockPos();
            if (this.crayfish.isTouchingWater() && this.hasReached()) {
                    World world = this.crayfish.getWorld();
                    world.playSound(null, blockPos, CrayfishSoundsInit.CRAYFISH_LAYS_EGGS, SoundCategory.BLOCKS, 0.3f, 0.9f + world.random.nextFloat() * 0.2f);
                    BlockPos blockPos2 = this.targetPos.up();
                    BlockState blockState = (BlockState)CrayfishMod.CRAYFISH_EGGS_BLOCK.getDefaultState();
                    world.setBlockState(blockPos2, blockState, Block.NOTIFY_ALL);
                    world.emitGameEvent(GameEvent.BLOCK_PLACE, blockPos2, GameEvent.Emitter.of(this.crayfish, blockState));
                    this.crayfish.setHasEgg(false);
                    this.crayfish.setLoveTicks(600);
            }
        }

        @Override
        protected boolean isTargetPos(WorldView world, BlockPos pos) {
            FluidState blockState = world.getFluidState(pos);
            FluidState fluidUp = world.getFluidState(pos.up());
            BlockState blockup = world.getBlockState(pos.up());
            return blockState.isEmpty() && fluidUp.isOf(Fluids.WATER) && (!blockup.isOf(CrayfishMod.CRAYFISH_EGGS_BLOCK));
        }
    }

    static class HungryActiveTargetGoal<T extends LivingEntity>
    extends TrackTargetGoal {
    protected final Class<T> targetClass;

    protected final int reciprocalChance;
    @Nullable
    protected LivingEntity targetEntity;
    protected TargetPredicate targetPredicate;

    public HungryActiveTargetGoal(MobEntity mob, Class<T> targetClass, boolean checkVisibility) {
        this(mob, targetClass, 10, checkVisibility, false, null);
    }

    public HungryActiveTargetGoal(MobEntity mob, Class<T> targetClass, boolean checkVisibility, Predicate<LivingEntity> targetPredicate) {
        this(mob, targetClass, 10, checkVisibility, false, targetPredicate);
    }

    public HungryActiveTargetGoal(MobEntity mob, Class<T> targetClass, boolean checkVisibility, boolean checkCanNavigate) {
        this(mob, targetClass, 10, checkVisibility, checkCanNavigate, null);
    }

    public HungryActiveTargetGoal(MobEntity mob, Class<T> targetClass, int reciprocalChance, boolean checkVisibility, boolean checkCanNavigate, @Nullable Predicate<LivingEntity> targetPredicate) {
        super(mob, checkVisibility, checkCanNavigate);
        this.targetClass = targetClass;
        this.reciprocalChance = HungryActiveTargetGoal.toGoalTicks(reciprocalChance);
        this.setControls(EnumSet.of(Goal.Control.TARGET));
        this.targetPredicate = TargetPredicate.createAttackable().setBaseMaxDistance(this.getFollowRange()).setPredicate(targetPredicate);
    }

    @Override
    public boolean canStart() {
        if (this.reciprocalChance > 0 && this.mob.getRandom().nextInt(this.reciprocalChance) != 0) {
            return false;
        }

        if (!((CrayfishEntity) this.mob).isHungry()) {
            return false;
        }

        if (CrayfishMod.passivecrayfish == true) {
            return false;
        }
        
        this.findClosestTarget();
        return this.targetEntity != null;
    }

    protected Box getSearchBox(double distance) {
        return this.mob.getBoundingBox().expand(distance, 4.0, distance);
    }

    protected void findClosestTarget() {
        this.targetEntity = this.targetClass == PlayerEntity.class || this.targetClass == ServerPlayerEntity.class ? this.mob.getWorld().getClosestPlayer(this.targetPredicate, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ()) : this.mob.getWorld().getClosestEntity(this.mob.getWorld().getEntitiesByClass(this.targetClass, this.getSearchBox(this.getFollowRange()), livingEntity -> true), this.targetPredicate, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
    }

    @Override
    public void start() {
        this.mob.setTarget(this.targetEntity);
        super.start();
    }

    public void setTargetEntity(@Nullable LivingEntity targetEntity) {
        this.targetEntity = targetEntity;
    }
    }
}
