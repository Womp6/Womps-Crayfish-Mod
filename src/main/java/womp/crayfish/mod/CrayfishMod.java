package womp.crayfish.mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import womp.crayfish.mod.blocks.CrayfishEggBlock;
import womp.crayfish.mod.blocks.CrayfishTrapBlock;
import womp.crayfish.mod.blocks.CrayfishTrapBlockEntity;
import womp.crayfish.mod.command.PassiveCrayfishCommand;
import womp.crayfish.mod.entity.CrayfishEntity;
import womp.crayfish.mod.items.BucketFood;
import womp.crayfish.mod.items.CrayfishBucketItem;
import womp.crayfish.mod.items.Stew;
import womp.crayfish.mod.registry.CrayfishEntityInit;
import womp.crayfish.mod.registry.CrayfishSoundsInit;
import womp.crayfish.mod.screen.CrayfishTrapScreenHandler;
import womp.crayfish.mod.villager.Crayfish_Trapper;

public class CrayfishMod implements ModInitializer {

	//CRAYFISH MOD 1.27.1
	
	public static final RegistryKey<ItemGroup> CRAYFISH_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier("crayfish", "crayfish_group"));

	//CRAYFISH MOD 1.35.0

	public static boolean passivecrayfish = false;


//CRAYFISH

	//CRAYFISH MOD 0.0.1

	public static final Item RAW_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_CRAYFISH));

	public static final Item COOKED_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_CRAYFISH));

	public static final Item CRAYFISH = new Item(new Item.Settings());

	//CRAYFISH MOD 0.0.5

	public static final Item CLAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.CLAYFISH));

	//CRAYFISH MOD 0.0.6

	public static final Item RAW_SAPPHIRE_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_SAPPHIRE_CRAYFISH));

	public static final Item SAPPHIRE_CRAYFISH = new Item(new Item.Settings());

	public static final Item COOKED_SAPPHIRE_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_SAPPHIRE_CRAYFISH));

	//CRAYFISH MOD 0.0.8

	public static final Item RAW_LOUISIANA_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_LOUISIANA_CRAYFISH));

	public static final Item LOUISIANA_CRAYFISH = new Item(new Item.Settings());

	public static final Item COOKED_LOUISIANA_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_LOUISIANA_CRAYFISH));

	//CRAYFISH MOD 0.0.9

	public static final Item RAW_WHITE_TUBERCLED_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_WHITE_TUBERCLED_CRAYFISH));

	public static final Item WHITE_TUBERCLED_CRAYFISH = new Item(new Item.Settings());

	public static final Item COOKED_WHITE_TUBERCLED_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_WHITE_TUBERCLED_CRAYFISH));

	//CRAYFISH MOD 1.10.0

	public static final Item RAW_ZEBRA_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_ZEBRA_CRAYFISH));

	public static final Item ZEBRA_CRAYFISH = new Item(new Item.Settings());

	public static final Item COOKED_ZEBRA_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_ZEBRA_CRAYFISH));

	//CRAYFISH MOD 1.11.0

	public static final Item RAW_VIRILE_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_VIRILE_CRAYFISH));

	public static final Item VIRILE_CRAYFISH = new Item(new Item.Settings());

	public static final Item COOKED_VIRILE_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_VIRILE_CRAYFISH));

	//CRAYFISH MOD 1.12.0

	public static final Item RAW_RUSTY_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_RUSTY_CRAYFISH));

	public static final Item RUSTY_CRAYFISH = new Item(new Item.Settings());

	public static final Item COOKED_RUSTY_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_RUSTY_CRAYFISH));

	//CRAYFISH MOD 1.13.0

	public static final Item RAW_SUPERNOVA_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_SUPERNOVA_CRAYFISH));

	public static final Item SUPERNOVA_CRAYFISH = new Item(new Item.Settings());

	public static final Item COOKED_SUPERNOVA_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_SUPERNOVA_CRAYFISH));

	//CRAYFISH MOD 1.14.0

	public static final Item RAW_SIGNAL_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_SIGNAL_CRAYFISH));

	public static final Item SIGNAL_CRAYFISH = new Item(new Item.Settings());

	public static final Item COOKED_SIGNAL_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_SIGNAL_CRAYFISH));

	//CRAYFISH MOD 1.15.0

	public static final Item RAW_SWAMP_DWARF_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_SWAMP_DWARF_CRAYFISH));

	public static final Item SWAMP_DWARF_CRAYFISH = new Item(new Item.Settings());
	
	public static final Item COOKED_SWAMP_DWARF_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_SWAMP_DWARF_CRAYFISH));

	//CRAYFISH MOD 1.16.0

	public static final Item RAW_AUSTRALIAN_REDCLAW_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_AUSTRALIAN_REDCLAW_CRAYFISH));

	public static final Item AUSTRALIAN_REDCLAW_CRAYFISH = new Item(new Item.Settings());

	public static final Item COOKED_AUSTRALIAN_REDCLAW_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_AUSTRALIAN_REDCLAW_CRAYFISH));

	//CRAYFISH MOD 1.17.0

	public static final Item RAW_BIG_SANDY_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_BIG_SANDY_CRAYFISH));

	public static final Item BIG_SANDY_CRAYFISH = new Item(new Item.Settings());

	public static final Item COOKED_BIG_SANDY_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_BIG_SANDY_CRAYFISH));

	//CRAYFISH MOD 1.18.0

	public static final Item RAW_SPINYCHEEK_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_SPINYCHEEK_CRAYFISH));

	public static final Item SPINYCHEEK_CRAYFISH = new Item(new Item.Settings());

	public static final Item COOKED_SPINYCHEEK_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_SPINYCHEEK_CRAYFISH));

	//CRAYFISH MOD 1.19.0

	public static final Item RAW_PARKHILL_PRAIRIE_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_PARKHILL_PRAIRIE_CRAYFISH));

	public static final Item PARKHILL_PRAIRIE_CRAYFISH = new Item(new Item.Settings());

	public static final Item COOKED_PARKHILL_PRAIRIE_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_PARKHILL_PRAIRIE_CRAYFISH));

	//CRAYFISH MOD 1.20.0

	public static final Item RAW_COMMON_YABBY_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_COMMON_YABBY_CRAYFISH));

	public static final Item COMMON_YABBY_CRAYFISH = new Item(new Item.Settings());

	public static final Item COOKED_COMMON_YABBY_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_COMMON_YABBY_CRAYFISH));

	//CRAYFISH MOD 1.21.0

	public static final Item RAW_NASHVILLE_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_NASHVILLE_CRAYFISH));

	public static final Item NASHVILLE_CRAYFISH = new Item(new Item.Settings());

	public static final Item COOKED_NASHVILLE_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_NASHVILLE_CRAYFISH));

	//CRAYFISH MOD 1.23.0

	public static final Item RAW_MURRAY_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_MURRAY_CRAYFISH));

	public static final Item MURRAY_CRAYFISH = new Item(new Item.Settings());

	public static final Item COOKED_MURRAY_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_MURRAY_CRAYFISH));

	//CRAYFISH MOD 1.24.0
	
	public static final Item RAW_TASMANIAN_GIANT_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_TASMANIAN_GIANT_CRAYFISH));

	public static final Item TASMANIAN_GIANT_CRAYFISH = new Item(new Item.Settings());

	public static final Item COOKED_TASMANIAN_GIANT_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_TASMANIAN_GIANT_CRAYFISH));

	//CRAYFISH MOD 1.25.0

	public static final Item RAW_JAPANESE_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_JAPANESE_CRAYFISH));

	public static final Item JAPANESE_CRAYFISH = new Item(new Item.Settings());

	public static final Item COOKED_JAPANESE_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_JAPANESE_CRAYFISH));

	//CRAYFISH MOD 1.26.0

	public static final Item RAW_MARBLED_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_MARBLED_CRAYFISH));

	public static final Item MARBLED_CRAYFISH = new Item(new Item.Settings());

	public static final Item COOKED_MARBLED_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_MARBLED_CRAYFISH));

	//CRAYFISH MOD 1.27.0

	public static final Item RAW_ORANGE_DWARF_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_ORANGE_DWARF_CRAYFISH));

	public static final Item ORANGE_DWARF_CRAYFISH = new Item(new Item.Settings());

	public static final Item COOKED_ORANGE_DWARF_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_ORANGE_DWARF_CRAYFISH));

	//CRAYFISH MOD 1.29.0

	public static final Item RAW_SLOUGH_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_SLOUGH_CRAYFISH));

	public static final Item SLOUGH_CRAYFISH = new Item(new Item.Settings());

	public static final Item COOKED_SLOUGH_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_SLOUGH_CRAYFISH));

	//CRAYFISH MOD 1.30.0

	public static final Item RAW_CAVE_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_CAVE_CRAYFISH));

	public static final Item CAVE_CRAYFISH = new Item(new Item.Settings());

	public static final Item COOKED_CAVE_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_CAVE_CRAYFISH));

	//CRAYFISH MOD 1.31.0

	public static final Item RAW_NOBLE_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_NOBLE_CRAYFISH));

	public static final Item NOBLE_CRAYFISH = new Item(new Item.Settings());

	public static final Item COOKED_NOBLE_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_NOBLE_CRAYFISH));

	//CRAYFISH MOD 1.32.0

	public static final Item RAW_HAIRY_MARRON_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_HAIRY_MARRON_CRAYFISH));

	public static final Item HAIRY_MARRON_CRAYFISH = new Item(new Item.Settings());

	public static final Item COOKED_HAIRY_MARRON_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_HAIRY_MARRON_CRAYFISH));

	//CRAYFISH MOD 1.33.0

	public static final Item RAW_THUNDERBOLT_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_THUNDERBOLT_CRAYFISH));

	public static final Item COOKED_THUNDERBOLT_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_THUNDERBOLT_CRAYFISH));

	public static final Item THUNDERBOLT_CRAYFISH = new Item(new Item.Settings());

	public static final Item RAW_BLUE_KONG_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.RAW_BLUE_KONG_CRAYFISH));

	public static final Item COOKED_BLUE_KONG_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.COOKED_BLUE_KONG_CRAYFISH));

	public static final Item BLUE_KONG_CRAYFISH = new Item(new Item.Settings());

	
//OTHER CRAYFISH

	//CRAYFISH MOD 1.14.0

	public static final Item SUPERCRAYFISH = new Item(new Item.Settings().fireproof());

	//CRAYFISH MOD 1.15.0

	public static final Item BURNT_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.BURNT_CRAYFISH));

	//CRAYFISH MOD 1.17.1

	public static final Item DIAMOND_CRAYFISH = new Item(new Item.Settings().rarity(Rarity.EPIC).food(CrayfishFoodComponents.DIAMOND_CRAYFISH));

	public static final Item GOLDEN_CRAYFISH = new Item(new Item.Settings().food(CrayfishFoodComponents.GOLDEN_CRAYFISH));


//CRAYFISH FOOD ITEMS

	//CRAYFISH MOD 1.17.0

	public static final Item GUMBO = new Stew(new Item.Settings().maxCount(1).food(CrayfishFoodComponents.GUMBO));

	//CRAYFISH MOD 1.17.1

	public static final Item CRAYFISH_STEW = new Stew(new Item.Settings().maxCount(1).food(CrayfishFoodComponents.CRAYFISH_STEW));

	public static final Item LIGHT_CRAYFISH_STEW = new Stew(new Item.Settings().maxCount(1).food(CrayfishFoodComponents.LIGHT_CRAYFISH_STEW));

	public static final Item CRAYFISH_BOIL = new Stew(new Item.Settings().maxCount(1).food(CrayfishFoodComponents.CRAYFISH_BOIL));

	//CRAYFISH MOD 1.35.0

	public static final Item CAVIAR_BUCKET = new BucketFood(new Item.Settings().food((new FoodComponent.Builder()).hunger(6).saturationModifier((float) 0.9).build()).maxCount(1));


//OTHER CRAYFISH ITEMS

	//CRAYFISH MOD 1.22.0

	public static final Item CRAYFISH_BUCKET = new CrayfishBucketItem(() -> CrayfishEntityInit.CRAYFISH, Fluids.WATER, Items.BUCKET, false, new Item.Settings().maxCount(1));

    public static final Item CRAYFISH_SPAWN_EGG = new SpawnEggItem(CrayfishEntityInit.CRAYFISH, 11499264, 8871950, new Item.Settings());

	//CRAYFISH MOD 1.28.0

	public static final CrayfishTrapBlock CRAYFISH_TRAP = new CrayfishTrapBlock(FabricBlockSettings.copy(Blocks.CHAIN).strength(4F, 4F).sounds(BlockSoundGroup.CHAIN).requiresTool().nonOpaque());

	//CRAYFISH MOD 1.35.0

	public static final Block CRAYFISH_EGGS_BLOCK = new CrayfishEggBlock(AbstractBlock.Settings.create().mapColor(MapColor.WATER_BLUE).breakInstantly().noCollision().sounds(BlockSoundGroup.FROGSPAWN).pistonBehavior(PistonBehavior.DESTROY).nonOpaque());


//OTHER

	//CRAYFISH MOD 0.0.1

	public static final String LOGGER = null;

	//CRAYFISH MOD 1.22.0
	
	public static final String MODID = "crayfish";
	
    public static final String MODNAME = "Womp's Crayfish Mod";
	
	public int spawnWeight = 1;
	
	public int minGroupSize = 1;
	
	public int maxGroupSize = 1;
	
	//CRAYFISH MOD 1.28.0

	public static BlockEntityType<CrayfishTrapBlockEntity> CRAYFISH_TRAP_BLOCK_ENTITY;
	
	public static ScreenHandlerType<CrayfishTrapScreenHandler> CRAYFISH_TRAP_SCREEN_HANDLER;
	
	public static Identifier createID(String path) {
		return new Identifier("crayfish", path);
	}

	@Override
	public void onInitialize() {

	//CRAYFISH

		//CRAYFISH GROUP

		Registry.register(Registries.ITEM_GROUP, CRAYFISH_GROUP, FabricItemGroup.builder().icon(() -> new ItemStack(CrayfishMod.CRAYFISH)).displayName(Text.translatable("Womp's Crayfish Mod")).build());

		//CRAYFISH MOD 0.0.1

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_crayfish"), RAW_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_crayfish"), COOKED_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "crayfish"), CRAYFISH);

		//CRAYFISH MOD 0.0.5

		Registry.register(Registries.ITEM, new Identifier("crayfish", "clayfish"), CLAYFISH);

		//CRAYFISH MOD 0.0.6

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_sapphire_crayfish"), RAW_SAPPHIRE_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "sapphire_crayfish"), SAPPHIRE_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_sapphire_crayfish"), COOKED_SAPPHIRE_CRAYFISH);

		//CRAYFISH MOD 0.0.8

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_louisiana_crayfish"), RAW_LOUISIANA_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "louisiana_crayfish"), LOUISIANA_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_louisiana_crayfish"), COOKED_LOUISIANA_CRAYFISH);

		//CRAYFISH MOD 0.0.9

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_white_tubercled_crayfish"), RAW_WHITE_TUBERCLED_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "white_tubercled_crayfish"), WHITE_TUBERCLED_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_white_tubercled_crayfish"), COOKED_WHITE_TUBERCLED_CRAYFISH);

		//CRAYFISH MOD 1.10.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_zebra_crayfish"), RAW_ZEBRA_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "zebra_crayfish"), ZEBRA_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_zebra_crayfish"), COOKED_ZEBRA_CRAYFISH);

		//CRAYFISH MOD 1.11.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_virile_crayfish"), RAW_VIRILE_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "virile_crayfish"), VIRILE_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_virile_crayfish"), COOKED_VIRILE_CRAYFISH);

		//CRAYFISH MOD 1.12.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_rusty_crayfish"), RAW_RUSTY_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "rusty_crayfish"), RUSTY_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_rusty_crayfish"), COOKED_RUSTY_CRAYFISH);

		//CRAYFISH MOD 1.13.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_supernova_crayfish"), RAW_SUPERNOVA_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "supernova_crayfish"), SUPERNOVA_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_supernova_crayfish"), COOKED_SUPERNOVA_CRAYFISH);

		//CRAYFISH MOD 1.14.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_signal_crayfish"), RAW_SIGNAL_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "signal_crayfish"), SIGNAL_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_signal_crayfish"), COOKED_SIGNAL_CRAYFISH);

		//CRAYFISH MOD 1.15.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_swamp_dwarf_crayfish"), RAW_SWAMP_DWARF_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "swamp_dwarf_crayfish"), SWAMP_DWARF_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_swamp_dwarf_crayfish"), COOKED_SWAMP_DWARF_CRAYFISH);

		//CRAYFISH MOD 1.16.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_australian_redclaw_crayfish"), RAW_AUSTRALIAN_REDCLAW_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "australian_redclaw_crayfish"), AUSTRALIAN_REDCLAW_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_australian_redclaw_crayfish"), COOKED_AUSTRALIAN_REDCLAW_CRAYFISH);

		//CRAYFISH MOD 1.17.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_big_sandy_crayfish"), RAW_BIG_SANDY_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "big_sandy_crayfish"), BIG_SANDY_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_big_sandy_crayfish"), COOKED_BIG_SANDY_CRAYFISH);

		//CRAYFISH MOD 1.18.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_spinycheek_crayfish"), RAW_SPINYCHEEK_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "spinycheek_crayfish"), SPINYCHEEK_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_spinycheek_crayfish"), COOKED_SPINYCHEEK_CRAYFISH);

		//CRAYFISH MOD 1.19.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_parkhill_prairie_crayfish"), RAW_PARKHILL_PRAIRIE_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "parkhill_prairie_crayfish"), PARKHILL_PRAIRIE_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_parkhill_prairie_crayfish"), COOKED_PARKHILL_PRAIRIE_CRAYFISH);

		//CRAYFISH MOD 1.20.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_common_yabby_crayfish"), RAW_COMMON_YABBY_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "common_yabby_crayfish"), COMMON_YABBY_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_common_yabby_crayfish"), COOKED_COMMON_YABBY_CRAYFISH);

		//CRAYFISH MOD 1.21.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_nashville_crayfish"), RAW_NASHVILLE_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "nashville_crayfish"), NASHVILLE_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_nashville_crayfish"), COOKED_NASHVILLE_CRAYFISH);

		//CRAYFISH MOD 1.23.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_murray_crayfish"), RAW_MURRAY_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "murray_crayfish"), MURRAY_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_murray_crayfish"), COOKED_MURRAY_CRAYFISH);

		//CRAYFISH MOD 1.24.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_tasmanian_giant_crayfish"), RAW_TASMANIAN_GIANT_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "tasmanian_giant_crayfish"), TASMANIAN_GIANT_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_tasmanian_giant_crayfish"), COOKED_TASMANIAN_GIANT_CRAYFISH);

		//CRAYFISH MOD 1.25.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_japanese_crayfish"), RAW_JAPANESE_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "japanese_crayfish"), JAPANESE_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_japanese_crayfish"), COOKED_JAPANESE_CRAYFISH);

		//CRAYFISH MOD 1.26.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_marbled_crayfish"), RAW_MARBLED_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "marbled_crayfish"), MARBLED_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_marbled_crayfish"), COOKED_MARBLED_CRAYFISH);

		//CRAYFISH MOD 1.27.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_orange_dwarf_crayfish"), RAW_ORANGE_DWARF_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "orange_dwarf_crayfish"), ORANGE_DWARF_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_orange_dwarf_crayfish"), COOKED_ORANGE_DWARF_CRAYFISH);

		//CRAYFISH MOD 1.29.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_slough_crayfish"), RAW_SLOUGH_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "slough_crayfish"), SLOUGH_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_slough_crayfish"), COOKED_SLOUGH_CRAYFISH);

		//CRAYFISH MOD 1.30.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_cave_crayfish"), RAW_CAVE_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cave_crayfish"), CAVE_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_cave_crayfish"), COOKED_CAVE_CRAYFISH);

		//CRAYFISH MOD 1.31.0
		
		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_noble_crayfish"), RAW_NOBLE_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "noble_crayfish"), NOBLE_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_noble_crayfish"), COOKED_NOBLE_CRAYFISH);

		//CRAYFISH MOD 1.32.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_hairy_marron_crayfish"), RAW_HAIRY_MARRON_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "hairy_marron_crayfish"), HAIRY_MARRON_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_hairy_marron_crayfish"), COOKED_HAIRY_MARRON_CRAYFISH);

		//CRAYFISH MOD 1.33.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_thunderbolt_crayfish"), RAW_THUNDERBOLT_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "thunderbolt_crayfish"), THUNDERBOLT_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_thunderbolt_crayfish"), COOKED_THUNDERBOLT_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "raw_blue_kong_crayfish"), RAW_BLUE_KONG_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "blue_kong_crayfish"), BLUE_KONG_CRAYFISH);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "cooked_blue_kong_crayfish"), COOKED_BLUE_KONG_CRAYFISH);


	//OTHER CRAYFISH

		//CRAYFISH MOD 1.14.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "supercrayfish"), SUPERCRAYFISH);
		
		//CRAYFISH MOD 1.15.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "burnt_crayfish"), BURNT_CRAYFISH);
	
		//CRAYFISH MOD 1.17.1

		Registry.register(Registries.ITEM, new Identifier("crayfish", "diamond_crayfish"), DIAMOND_CRAYFISH);
	
		Registry.register(Registries.ITEM, new Identifier("crayfish", "golden_crayfish"), GOLDEN_CRAYFISH);


	//CRAYFISH FOOD ITEMS

		//CRAYFISH MOD 1.17.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "gumbo"), GUMBO);

		//CRAYFISH MOD 1.17.1
		
		Registry.register(Registries.ITEM, new Identifier("crayfish", "crayfish_stew"), CRAYFISH_STEW);
		
		Registry.register(Registries.ITEM, new Identifier("crayfish", "light_crayfish_stew"), LIGHT_CRAYFISH_STEW);
		
		Registry.register(Registries.ITEM, new Identifier("crayfish", "crayfish_boil"), CRAYFISH_BOIL);

		//CRAYFISH MOD 1.35.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "caviar_bucket"), CAVIAR_BUCKET);


	//OTHER CRAYFISH ITEMS

		//CRAYFISH MOD 1.22.0

		Registry.register(Registries.ITEM, new Identifier("crayfish", "crayfish_bucket"), CRAYFISH_BUCKET);
		
		Registry.register(Registries.ITEM, new Identifier("crayfish", "crayfish_spawn_egg"), CRAYFISH_SPAWN_EGG);

		//CRAYFISH MOD 1.28.0
	
		Registry.register(Registries.BLOCK, new Identifier("crayfish", "crayfish_trap"), CRAYFISH_TRAP);
	
		Registry.register(Registries.ITEM, new Identifier("crayfish", "crayfish_trap"), new BlockItem(CRAYFISH_TRAP, new FabricItemSettings()));

		//CRAYFISH MOD 1.35.0

		Registry.register(Registries.BLOCK, new Identifier("crayfish", "crayfish_eggs_block"), CRAYFISH_EGGS_BLOCK);

		Registry.register(Registries.ITEM, new Identifier("crayfish", "crayfish_eggs"), new BlockItem(CRAYFISH_EGGS_BLOCK, new FabricItemSettings()));

	
	//ENTITIES

		//CRAYFISH MOD 1.22.0

		CrayfishEntityInit.registerEntities();
		
		FabricDefaultAttributeRegistry.register(CrayfishEntityInit.CRAYFISH, CrayfishEntity.createCrayfishAttributes());
	
		BiomeModifications.addSpawn(BiomeSelectors.tag(ConventionalBiomeTags.RIVER), SpawnGroup.WATER_AMBIENT, CrayfishEntityInit.CRAYFISH, 7, 1 ,2);


	//OTHER

		//CRAYFISH MOD 1.22.0

		CrayfishSoundsInit.registerSounds();

		//CRAYFISH MOD 1.28.0
		
		CRAYFISH_TRAP_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, "crayfish:crayfish_trap_block_entity", FabricBlockEntityTypeBuilder.create(CrayfishTrapBlockEntity::new, CRAYFISH_TRAP).build(null));
	
		CRAYFISH_TRAP_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, createID("crayfish_trap"), new ScreenHandlerType<>(CrayfishTrapScreenHandler::new, FeatureFlags.VANILLA_FEATURES));
	
		Crayfish_Trapper.registerVillagers();
	
		Crayfish_Trapper.registerTrades();

		//CRAYFISH MOD 1.35

		CommandRegistrationCallback.EVENT.register(PassiveCrayfishCommand::register);

	
	//ITEM GROUPS
		
		ItemGroupEvents.modifyEntriesEvent(CRAYFISH_GROUP).register(content -> {
			content.add(RAW_CRAYFISH);
			content.add(COOKED_CRAYFISH);
			content.add(CLAYFISH);
			content.add(RAW_SAPPHIRE_CRAYFISH);
			content.add(COOKED_SAPPHIRE_CRAYFISH);
			content.add(RAW_LOUISIANA_CRAYFISH);
			content.add(COOKED_LOUISIANA_CRAYFISH);
			content.add(RAW_WHITE_TUBERCLED_CRAYFISH);
			content.add(COOKED_WHITE_TUBERCLED_CRAYFISH);
			content.add(RAW_ZEBRA_CRAYFISH);
			content.add(COOKED_ZEBRA_CRAYFISH);
			content.add(RAW_VIRILE_CRAYFISH);
			content.add(COOKED_VIRILE_CRAYFISH);
			content.add(RAW_RUSTY_CRAYFISH);
			content.add(COOKED_RUSTY_CRAYFISH);
			content.add(RAW_SUPERNOVA_CRAYFISH);
			content.add(COOKED_SUPERNOVA_CRAYFISH);
			content.add(RAW_SIGNAL_CRAYFISH);
			content.add(COOKED_SIGNAL_CRAYFISH);
			content.add(RAW_SWAMP_DWARF_CRAYFISH);
			content.add(COOKED_SWAMP_DWARF_CRAYFISH);
			content.add(RAW_AUSTRALIAN_REDCLAW_CRAYFISH);
			content.add(COOKED_AUSTRALIAN_REDCLAW_CRAYFISH);
			content.add(RAW_BIG_SANDY_CRAYFISH);
			content.add(COOKED_BIG_SANDY_CRAYFISH);
			content.add(RAW_SPINYCHEEK_CRAYFISH);
			content.add(COOKED_SPINYCHEEK_CRAYFISH);
			content.add(RAW_PARKHILL_PRAIRIE_CRAYFISH);
			content.add(COOKED_PARKHILL_PRAIRIE_CRAYFISH);
			content.add(RAW_COMMON_YABBY_CRAYFISH);
			content.add(COOKED_COMMON_YABBY_CRAYFISH);
			content.add(RAW_NASHVILLE_CRAYFISH);
			content.add(COOKED_NASHVILLE_CRAYFISH);
			content.add(RAW_MURRAY_CRAYFISH);
			content.add(COOKED_MURRAY_CRAYFISH);
			content.add(RAW_TASMANIAN_GIANT_CRAYFISH);
			content.add(COOKED_TASMANIAN_GIANT_CRAYFISH);
			content.add(RAW_JAPANESE_CRAYFISH);
			content.add(COOKED_JAPANESE_CRAYFISH);
			content.add(RAW_MARBLED_CRAYFISH);
			content.add(COOKED_MARBLED_CRAYFISH);
			content.add(RAW_ORANGE_DWARF_CRAYFISH);
			content.add(COOKED_ORANGE_DWARF_CRAYFISH);
			content.add(RAW_SLOUGH_CRAYFISH);
			content.add(COOKED_SLOUGH_CRAYFISH);
			content.add(RAW_CAVE_CRAYFISH);
			content.add(COOKED_CAVE_CRAYFISH);
			content.add(RAW_NOBLE_CRAYFISH);
			content.add(COOKED_NOBLE_CRAYFISH);
			content.add(RAW_HAIRY_MARRON_CRAYFISH);
			content.add(COOKED_HAIRY_MARRON_CRAYFISH);
			content.add(RAW_THUNDERBOLT_CRAYFISH);
			content.add(COOKED_THUNDERBOLT_CRAYFISH);
			content.add(RAW_BLUE_KONG_CRAYFISH);
			content.add(COOKED_BLUE_KONG_CRAYFISH);
			content.add(CRAYFISH);
			content.add(SAPPHIRE_CRAYFISH);
			content.add(LOUISIANA_CRAYFISH);
			content.add(WHITE_TUBERCLED_CRAYFISH);
			content.add(ZEBRA_CRAYFISH);
			content.add(VIRILE_CRAYFISH);
			content.add(RUSTY_CRAYFISH);
			content.add(SUPERNOVA_CRAYFISH);
			content.add(SIGNAL_CRAYFISH);
			content.add(SWAMP_DWARF_CRAYFISH);
			content.add(AUSTRALIAN_REDCLAW_CRAYFISH);
			content.add(BIG_SANDY_CRAYFISH);
			content.add(SPINYCHEEK_CRAYFISH);
			content.add(PARKHILL_PRAIRIE_CRAYFISH);
			content.add(COMMON_YABBY_CRAYFISH);
			content.add(NASHVILLE_CRAYFISH);
			content.add(MURRAY_CRAYFISH);
			content.add(TASMANIAN_GIANT_CRAYFISH);
			content.add(JAPANESE_CRAYFISH);
			content.add(MARBLED_CRAYFISH);
			content.add(ORANGE_DWARF_CRAYFISH);
			content.add(SLOUGH_CRAYFISH);
			content.add(CAVE_CRAYFISH);
			content.add(NOBLE_CRAYFISH);
			content.add(HAIRY_MARRON_CRAYFISH);
			content.add(THUNDERBOLT_CRAYFISH);
			content.add(BLUE_KONG_CRAYFISH);
			content.add(BURNT_CRAYFISH);
			content.add(DIAMOND_CRAYFISH);
			content.add(GOLDEN_CRAYFISH);
			content.add(GUMBO);
			content.add(CRAYFISH_STEW);
			content.add(LIGHT_CRAYFISH_STEW);
			content.add(CRAYFISH_BOIL);
			content.add(CAVIAR_BUCKET);
			content.add(CRAYFISH_BUCKET);
			content.add(CRAYFISH_SPAWN_EGG);
			content.add(CRAYFISH_TRAP);
			content.add(CRAYFISH_EGGS_BLOCK);
		});
	}
}