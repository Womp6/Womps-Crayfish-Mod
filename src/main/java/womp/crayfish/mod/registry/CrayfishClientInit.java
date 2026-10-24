package womp.crayfish.mod.registry;

import womp.crayfish.mod.client.model.CrayfishModel;
import womp.crayfish.mod.client.renderer.CrayfishRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class CrayfishClientInit {
    public static final EntityModelLayer CRAYFISH_MODEL = new EntityModelLayer(new Identifier("crayfish", "crayfish"), "crayfish_model");

    public static void registerClientEntityRenders() {
        EntityRendererRegistry.register(CrayfishEntityInit.CRAYFISH, CrayfishRenderer::new);
    }

    public static void registerClientEntityModels() {
        EntityModelLayerRegistry.registerModelLayer(CRAYFISH_MODEL, CrayfishModel::getTexturedModelData);
    }
}
