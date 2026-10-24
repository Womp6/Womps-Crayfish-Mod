package womp.crayfish.mod.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.StewItem;
import net.minecraft.world.World;

public class BucketFood extends StewItem {

    public BucketFood(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        super.finishUsing(stack, world, user);
        
        if (stack.isEmpty()) {
           return new ItemStack(Items.BUCKET);
        } else {
           if (user instanceof PlayerEntity && !((PlayerEntity)user).getAbilities().creativeMode) {
              ItemStack itemStack = new ItemStack(Items.BUCKET);
              PlayerEntity playerEntity = (PlayerEntity)user;
              if (!playerEntity.getInventory().insertStack(itemStack)) {
                 playerEntity.dropItem(itemStack, false);
              }
           }
  
           return stack;
        }
     }
    }
