package womp.crayfish.mod;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class CrayfishFoodComponents {

    //CRAYFISH MOD 0.0.1

    public static final FoodComponent COOKED_CRAYFISH = (new FoodComponent.Builder()).hunger(4).saturationModifier((float) 0.8).build();

    public static final FoodComponent RAW_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build();

    //CRAYFISH MOD 0.0.5

    public static final FoodComponent CLAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 1F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 400, 0), 1F).build();

    //CRAYFISH MOD 0.0.6

    public static final FoodComponent RAW_SAPPHIRE_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build();

    public static final FoodComponent COOKED_SAPPHIRE_CRAYFISH = (new FoodComponent.Builder()).hunger(4).saturationModifier((float) 0.8).build();

    //CRAYFISH MOD 0.0.8

    public static final FoodComponent RAW_LOUISIANA_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build();
    
    public static final FoodComponent COOKED_LOUISIANA_CRAYFISH = (new FoodComponent.Builder()).hunger(4).saturationModifier((float) 0.8).build();

    //CRAYFISH MOD 0.0.9

    public static final FoodComponent RAW_WHITE_TUBERCLED_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build();
    
    public static final FoodComponent COOKED_WHITE_TUBERCLED_CRAYFISH = (new FoodComponent.Builder()).hunger(4).saturationModifier((float) 0.8).build();

    //CRAYFISH MOD 1.10.0

    public static final FoodComponent RAW_ZEBRA_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build();
    
    public static final FoodComponent COOKED_ZEBRA_CRAYFISH = (new FoodComponent.Builder()).hunger(4).saturationModifier((float) 0.8).build();

    //CRAYFISH MOD 1.11.0

    public static final FoodComponent RAW_VIRILE_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build();

    public static final FoodComponent COOKED_VIRILE_CRAYFISH = (new FoodComponent.Builder()).hunger(4).saturationModifier((float) 0.8).build();

    //CRAYFISH MOD 1.12.0

    public static final FoodComponent RAW_RUSTY_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build();

    public static final FoodComponent COOKED_RUSTY_CRAYFISH = (new FoodComponent.Builder()).hunger(4).saturationModifier((float) 0.8).build();

    //CRAYFISH MOD 1.13.0

    public static final FoodComponent RAW_SUPERNOVA_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build();

    public static final FoodComponent COOKED_SUPERNOVA_CRAYFISH = (new FoodComponent.Builder()).hunger(4).saturationModifier((float) 0.8).build();

    //CRAYFISH MOD 1.14.0

    public static final FoodComponent RAW_SIGNAL_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build();

    public static final FoodComponent COOKED_SIGNAL_CRAYFISH = (new FoodComponent.Builder()).hunger(4).saturationModifier((float) 0.8).build();

    //CRAYFISH MOD 1.15.0

    public static final FoodComponent RAW_SWAMP_DWARF_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build();

    public static final FoodComponent COOKED_SWAMP_DWARF_CRAYFISH = (new FoodComponent.Builder()).hunger(3).saturationModifier((float) 0.8).build();

    public static final FoodComponent BURNT_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.1).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 400, 1), 1F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 400, 0), 1F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 1), 1F).build();

    //CRAYFISH MOD 1.16.0

    public static final FoodComponent RAW_AUSTRALIAN_REDCLAW_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build();

    public static final FoodComponent COOKED_AUSTRALIAN_REDCLAW_CRAYFISH = (new FoodComponent.Builder()).hunger(4).saturationModifier((float) 0.8).build();

    //CRAYFISH MOD 1.17.0

    public static final FoodComponent RAW_BIG_SANDY_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build();

    public static final FoodComponent COOKED_BIG_SANDY_CRAYFISH = (new FoodComponent.Builder()).hunger(4).saturationModifier((float) 0.8).build();

    public static final FoodComponent GUMBO = (new FoodComponent.Builder()).hunger(10).saturationModifier((float) 0.9).build();

    //CRAYFISH MOD 1.17.1

    public static final FoodComponent CRAYFISH_STEW = (new FoodComponent.Builder()).hunger(10).saturationModifier((float) 0.9).build();

    public static final FoodComponent LIGHT_CRAYFISH_STEW = (new FoodComponent.Builder()).hunger(8).saturationModifier((float) 0.9).build();

    public static final FoodComponent CRAYFISH_BOIL = (new FoodComponent.Builder()).hunger(15).saturationModifier((float) 0.9).build();

    public static final FoodComponent DIAMOND_CRAYFISH = (new FoodComponent.Builder()).hunger(7).saturationModifier(1).alwaysEdible().statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 250, 1), 1F).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 2), 1F).statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000, 0), 1F).build();

    public static final FoodComponent GOLDEN_CRAYFISH = (new FoodComponent.Builder()).hunger(6).saturationModifier(1).build();

    //CRAYFISH MOD 1.18.0

    public static final FoodComponent RAW_SPINYCHEEK_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build();

    public static final FoodComponent COOKED_SPINYCHEEK_CRAYFISH = (new FoodComponent.Builder()).hunger(4).saturationModifier((float) 0.8).build();

    //CRAYFISH MOD 1.19.0

    public static final FoodComponent RAW_PARKHILL_PRAIRIE_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build();

    public static final FoodComponent COOKED_PARKHILL_PRAIRIE_CRAYFISH = (new FoodComponent.Builder()).hunger(4).saturationModifier((float) 0.8).build();

    //CRAYFISH MOD 1.20.0

    public static final FoodComponent RAW_COMMON_YABBY_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build();

    public static final FoodComponent COOKED_COMMON_YABBY_CRAYFISH = (new FoodComponent.Builder()).hunger(4).saturationModifier((float) 0.8).build();

    //CRAYFISH MOD 1.21.0

    public static final FoodComponent RAW_NASHVILLE_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build();

    public static final FoodComponent COOKED_NASHVILLE_CRAYFISH = (new FoodComponent.Builder()).hunger(4).saturationModifier((float) 0.8).build();

    //CRAYFISH MOD 1.23.0

    public static final FoodComponent RAW_MURRAY_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build(); 

    public static final FoodComponent COOKED_MURRAY_CRAYFISH = (new FoodComponent.Builder()).hunger(4).saturationModifier((float) 0.8).build();

    //CRAYFISH MOD 1.24.0

    public static final FoodComponent RAW_TASMANIAN_GIANT_CRAYFISH = (new FoodComponent.Builder()).hunger(2).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build(); 

    public static final FoodComponent COOKED_TASMANIAN_GIANT_CRAYFISH = (new FoodComponent.Builder()).hunger(6).saturationModifier((float) 0.8).build();

    //CRAYFISH MOD 1.25.0

    public static final FoodComponent RAW_JAPANESE_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build(); 

    public static final FoodComponent COOKED_JAPANESE_CRAYFISH = (new FoodComponent.Builder()).hunger(4).saturationModifier((float) 0.8).build();
    
    //CRAYFISH MOD 1.26.0

    public static final FoodComponent RAW_MARBLED_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build(); 

    public static final FoodComponent COOKED_MARBLED_CRAYFISH = (new FoodComponent.Builder()).hunger(4).saturationModifier((float) 0.8).build();
    
    //CRAYFISH MOD 1.27.0

    public static final FoodComponent RAW_ORANGE_DWARF_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build(); 

    public static final FoodComponent COOKED_ORANGE_DWARF_CRAYFISH = (new FoodComponent.Builder()).hunger(3).saturationModifier((float) 0.8).build();

    //CRAYFISH MOD 1.29.0

    public static final FoodComponent RAW_SLOUGH_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build();
    
    public static final FoodComponent COOKED_SLOUGH_CRAYFISH = (new FoodComponent.Builder()).hunger(4).saturationModifier((float) 0.8).build();

    //CRAYFISH MOD 1.30.0

    public static final FoodComponent RAW_CAVE_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build();
    
    public static final FoodComponent COOKED_CAVE_CRAYFISH = (new FoodComponent.Builder()).hunger(4).saturationModifier((float) 0.8).build();

    //CRAYFISH MOD 1.31.0

    public static final FoodComponent RAW_NOBLE_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build();
    
    public static final FoodComponent COOKED_NOBLE_CRAYFISH = (new FoodComponent.Builder()).hunger(4).saturationModifier((float) 0.8).build();

    //CRAYFISH MOD 1.32.0

    public static final FoodComponent RAW_HAIRY_MARRON_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build();
    
    public static final FoodComponent COOKED_HAIRY_MARRON_CRAYFISH = (new FoodComponent.Builder()).hunger(4).saturationModifier((float) 0.8).build();

    //CRAYFISH MOD 1.33.0

    public static final FoodComponent COOKED_THUNDERBOLT_CRAYFISH = (new FoodComponent.Builder()).hunger(4).saturationModifier((float) 0.8).build();

    public static final FoodComponent RAW_THUNDERBOLT_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build();

    public static final FoodComponent COOKED_BLUE_KONG_CRAYFISH = (new FoodComponent.Builder()).hunger(4).saturationModifier((float) 0.8).build();

    public static final FoodComponent RAW_BLUE_KONG_CRAYFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier((float) 0.2).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).build();
}