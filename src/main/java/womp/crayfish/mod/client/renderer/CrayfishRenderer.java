package womp.crayfish.mod.client.renderer;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import womp.crayfish.mod.client.model.CrayfishModel;
import womp.crayfish.mod.entity.CrayfishEntity;
import womp.crayfish.mod.registry.CrayfishClientInit;

public class CrayfishRenderer extends MobEntityRenderer<CrayfishEntity, CrayfishModel> {

    public CrayfishRenderer(EntityRendererFactory.Context renderManager) {
         super(renderManager, new CrayfishModel(renderManager.getPart(CrayfishClientInit.CRAYFISH_MODEL)), 0.4f);
     }
 
     @Override
     public Identifier getTexture(CrayfishEntity animatable) {
         int variant = animatable.getVariant();
         return new Identifier("crayfish", "textures/entity/crayfish/crayfish_" + variant + ".png");
     }
 
     @Override
     public void render(CrayfishEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                        VertexConsumerProvider bufferSource, int packedLight) {
         if(entity.isBaby()) {
             poseStack.scale(0.5f, 0.5f, 0.5f);
          }
 
         super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
     }
 }
