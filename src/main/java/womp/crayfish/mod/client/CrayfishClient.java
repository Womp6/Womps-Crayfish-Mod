package womp.crayfish.mod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import womp.crayfish.mod.CrayfishMod;
import womp.crayfish.mod.registry.CrayfishClientInit;
import womp.crayfish.mod.screen.CrayfishTrapScreen;

@Environment(EnvType.CLIENT)
public class CrayfishClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CrayfishClientInit.registerClientEntityRenders();
        CrayfishClientInit.registerClientEntityModels();
        BlockRenderLayerMap.INSTANCE.putBlock(CrayfishMod.CRAYFISH_TRAP, RenderLayer.getCutout());
        HandledScreens.register(CrayfishMod.CRAYFISH_TRAP_SCREEN_HANDLER, CrayfishTrapScreen::new);
        BlockRenderLayerMap.INSTANCE.putBlock(CrayfishMod.CRAYFISH_EGGS_BLOCK, RenderLayer.getCutout());
    }
}
