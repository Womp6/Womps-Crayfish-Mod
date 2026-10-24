package womp.crayfish.mod.villager;

import com.google.common.collect.ImmutableSet;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import womp.crayfish.mod.CrayfishMod;

public class Crayfish_Trapper {

    public static final PointOfInterestType CRAYFISH_POI = registerPOI("crayfish_poi", CrayfishMod.CRAYFISH_TRAP);

    public static final VillagerProfession CRAYFISH_TRAPPER = registerProfession("crayfish_trapper", RegistryKey.of(Registries.POINT_OF_INTEREST_TYPE.getKey(), new Identifier("crayfish", "crayfish_poi")));

    public static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registries.VILLAGER_PROFESSION, new Identifier("crayfish", name), VillagerProfessionBuilder.create().id(new Identifier("crayfish", name)).workstation(type).workSound(SoundEvents.BLOCK_CHAIN_BREAK).build());
    }

    public static PointOfInterestType registerPOI(String name, Block block) {
        return PointOfInterestHelper.register(new Identifier("crayfish", name), 1, 1, ImmutableSet.copyOf(block.getStateManager().getStates())); 

    }
    
    public static void registerVillagers() {

    }

    public static void registerTrades() {
        TradeOfferHelper.registerVillagerOffers(Crayfish_Trapper.CRAYFISH_TRAPPER, 1, factories -> {
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_CRAYFISH, 16),
                new ItemStack(Items.EMERALD, 1),
                16, 2, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_COMMON_YABBY_CRAYFISH, 13),
                new ItemStack(Items.EMERALD, 1),
                16, 2, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_VIRILE_CRAYFISH, 11),
                new ItemStack(Items.EMERALD, 1),
                16, 2, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_RUSTY_CRAYFISH, 10),
                new ItemStack(Items.EMERALD, 1),
                16, 2, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_AUSTRALIAN_REDCLAW_CRAYFISH, 12),
                new ItemStack(Items.EMERALD, 1),
                16, 2, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_MARBLED_CRAYFISH, 14),
                new ItemStack(Items.EMERALD, 1),
                16, 2, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_ZEBRA_CRAYFISH, 10),
                new ItemStack(Items.EMERALD, 1),
                16, 2, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_SLOUGH_CRAYFISH, 14),
                new ItemStack(Items.EMERALD, 1),
                16, 2, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_CAVE_CRAYFISH, 15),
                new ItemStack(Items.EMERALD, 1),
                16, 2, .05f
            )));
        });
        TradeOfferHelper.registerVillagerOffers(Crayfish_Trapper.CRAYFISH_TRAPPER, 2, factories -> {
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_LOUISIANA_CRAYFISH, 12),
                new ItemStack(Items.EMERALD, 1),
                16, 10, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_WHITE_TUBERCLED_CRAYFISH, 10),
                new ItemStack(Items.EMERALD, 1),
                16, 10, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_SUPERNOVA_CRAYFISH, 8),
                new ItemStack(Items.EMERALD, 1),
                16, 10, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_SIGNAL_CRAYFISH, 10),
                new ItemStack(Items.EMERALD, 1),
                16, 10, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_SWAMP_DWARF_CRAYFISH, 12),
                new ItemStack(Items.EMERALD, 1),
                16, 10, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_SPINYCHEEK_CRAYFISH, 13),
                new ItemStack(Items.EMERALD, 1),
                16, 10, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_PARKHILL_PRAIRIE_CRAYFISH, 11),
                new ItemStack(Items.EMERALD, 1),
                16, 10, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_ORANGE_DWARF_CRAYFISH, 12),
                new ItemStack(Items.EMERALD, 1),
                16, 10, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_JAPANESE_CRAYFISH, 10),
                new ItemStack(Items.EMERALD, 1),
                16, 10, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_MURRAY_CRAYFISH, 9),
                new ItemStack(Items.EMERALD, 1),
                16, 10, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_HAIRY_MARRON_CRAYFISH, 9),
                new ItemStack(Items.EMERALD, 1),
                16, 10, .05f
            )));
        });
        TradeOfferHelper.registerVillagerOffers(Crayfish_Trapper.CRAYFISH_TRAPPER, 3, factories -> {
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_BIG_SANDY_CRAYFISH, 6),
                new ItemStack(Items.EMERALD, 1),
                16, 20, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_NASHVILLE_CRAYFISH, 7),
                new ItemStack(Items.EMERALD, 1),
                16, 20, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_TASMANIAN_GIANT_CRAYFISH, 8),
                new ItemStack(Items.EMERALD, 1),
                16, 20, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.CLAYFISH, 4),
                new ItemStack(Items.EMERALD, 1),
                16, 20, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_SAPPHIRE_CRAYFISH, 5),
                new ItemStack(Items.EMERALD, 1),
                16, 20, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_NOBLE_CRAYFISH, 6),
                new ItemStack(Items.EMERALD, 1),
                16, 2, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_THUNDERBOLT_CRAYFISH, 6),
                new ItemStack(Items.EMERALD, 1),
                16, 2, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(CrayfishMod.RAW_BLUE_KONG_CRAYFISH, 6),
                new ItemStack(Items.EMERALD, 1),
                16, 2, .05f
            )));
        });
        TradeOfferHelper.registerVillagerOffers(Crayfish_Trapper.CRAYFISH_TRAPPER, 4, factories -> {
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(CrayfishMod.RAW_CRAYFISH, 6),
                new ItemStack(CrayfishMod.COOKED_CRAYFISH, 6),
                16, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(CrayfishMod.RAW_COMMON_YABBY_CRAYFISH, 6),
                new ItemStack(CrayfishMod.COOKED_COMMON_YABBY_CRAYFISH, 6),
                16, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(CrayfishMod.RAW_VIRILE_CRAYFISH, 6),
                new ItemStack(CrayfishMod.COOKED_VIRILE_CRAYFISH, 6),
                16, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(CrayfishMod.RAW_RUSTY_CRAYFISH, 6),
                new ItemStack(CrayfishMod.COOKED_RUSTY_CRAYFISH, 6),
                16, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(CrayfishMod.RAW_AUSTRALIAN_REDCLAW_CRAYFISH, 6),
                new ItemStack(CrayfishMod.COOKED_AUSTRALIAN_REDCLAW_CRAYFISH, 6),
                16, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(CrayfishMod.RAW_MARBLED_CRAYFISH, 6),
                new ItemStack(CrayfishMod.COOKED_MARBLED_CRAYFISH, 6),
                16, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(CrayfishMod.RAW_ZEBRA_CRAYFISH, 6),
                new ItemStack(CrayfishMod.COOKED_ZEBRA_CRAYFISH, 6),
                16, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(CrayfishMod.RAW_LOUISIANA_CRAYFISH, 5),
                new ItemStack(CrayfishMod.COOKED_LOUISIANA_CRAYFISH, 5),
                16, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(CrayfishMod.RAW_WHITE_TUBERCLED_CRAYFISH, 5),
                new ItemStack(CrayfishMod.COOKED_WHITE_TUBERCLED_CRAYFISH, 5),
                16, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(CrayfishMod.RAW_SUPERNOVA_CRAYFISH, 5),
                new ItemStack(CrayfishMod.COOKED_SUPERNOVA_CRAYFISH, 5),
                16, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(CrayfishMod.RAW_SIGNAL_CRAYFISH, 5),
                new ItemStack(CrayfishMod.COOKED_SIGNAL_CRAYFISH, 5),
                16, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(CrayfishMod.RAW_SWAMP_DWARF_CRAYFISH, 5),
                new ItemStack(CrayfishMod.COOKED_SWAMP_DWARF_CRAYFISH, 5),
                16, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(CrayfishMod.RAW_SPINYCHEEK_CRAYFISH, 5),
                new ItemStack(CrayfishMod.COOKED_SPINYCHEEK_CRAYFISH, 5),
                16, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(CrayfishMod.RAW_PARKHILL_PRAIRIE_CRAYFISH, 5),
                new ItemStack(CrayfishMod.COOKED_PARKHILL_PRAIRIE_CRAYFISH, 5),
                16, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(CrayfishMod.RAW_ORANGE_DWARF_CRAYFISH, 5),
                new ItemStack(CrayfishMod.COOKED_ORANGE_DWARF_CRAYFISH, 5),
                16, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(CrayfishMod.RAW_JAPANESE_CRAYFISH, 5),
                new ItemStack(CrayfishMod.COOKED_JAPANESE_CRAYFISH, 5),
                16, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(CrayfishMod.RAW_MURRAY_CRAYFISH, 5),
                new ItemStack(CrayfishMod.COOKED_MURRAY_CRAYFISH, 5),
                16, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(CrayfishMod.RAW_BIG_SANDY_CRAYFISH, 4),
                new ItemStack(CrayfishMod.COOKED_BIG_SANDY_CRAYFISH, 4),
                16, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(CrayfishMod.RAW_NASHVILLE_CRAYFISH, 4),
                new ItemStack(CrayfishMod.COOKED_NASHVILLE_CRAYFISH, 4),
                16, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(CrayfishMod.RAW_TASMANIAN_GIANT_CRAYFISH, 4),
                new ItemStack(CrayfishMod.COOKED_TASMANIAN_GIANT_CRAYFISH, 4),
                16, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(CrayfishMod.RAW_SAPPHIRE_CRAYFISH, 4),
                new ItemStack(CrayfishMod.COOKED_SAPPHIRE_CRAYFISH, 4),
                16, 30, .05f
            )));
        });
        TradeOfferHelper.registerVillagerOffers(Crayfish_Trapper.CRAYFISH_TRAPPER, 5, factories -> {
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 2),
                new ItemStack(CrayfishMod.CRAYFISH_TRAP, 1),
                12, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 3),
                new ItemStack(CrayfishMod.CRAYFISH_BUCKET, 1),
                16, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 3),
                new ItemStack(CrayfishMod.GUMBO, 1),
                12, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 2),
                new ItemStack(CrayfishMod.LIGHT_CRAYFISH_STEW, 1),
                12, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 3),
                new ItemStack(CrayfishMod.CRAYFISH_STEW, 1),
                12, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 5),
                new ItemStack(CrayfishMod.CRAYFISH_BOIL, 1),
                12, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 5),
                new ItemStack(CrayfishMod.GOLDEN_CRAYFISH, 5),
                12, 30, .05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 4),
                new ItemStack(CrayfishMod.CAVIAR_BUCKET, 1),
                12, 30, .05f
            )));
        });
    }
}
