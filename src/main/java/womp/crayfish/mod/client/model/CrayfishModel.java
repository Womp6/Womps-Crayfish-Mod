package womp.crayfish.mod.client.model;

import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import womp.crayfish.mod.entity.CrayfishEntity;
import womp.crayfish.mod.entity.animations.CrayfishAnimations;

public class CrayfishModel extends SinglePartEntityModel<CrayfishEntity> {

    private final ModelPart crayfish;
	
	public CrayfishModel(ModelPart root) {
		this.crayfish = root.getChild("crayfish");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData crayfish = modelPartData.addChild("crayfish", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData body = crayfish.addChild("body", ModelPartBuilder.create().uv(0, 9).cuboid(-2.0F, -3.0F, -5.0F, 4.0F, 2.0F, 6.0F, new Dilation(0.0F))
		.uv(10, 17).cuboid(-2.0F, -3.0F, 1.0F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData tail = body.addChild("tail", ModelPartBuilder.create().uv(2, 12).cuboid(3.0F, -1.5F, 11.0F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-1.5F, -2.0F, 2.0F, 3.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(12, 0).cuboid(-3.0F, -1.5F, 10.0F, 6.0F, 0.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 12).cuboid(-4.0F, -1.5F, 11.0F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		tail.addChild("cube_r1", ModelPartBuilder.create().uv(2, 11).cuboid(4.92F, -1.5F, 6.6F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 11).cuboid(5.92F, -1.5F, 7.6F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F))
		.uv(2, 10).cuboid(4.92F, -1.5F, 7.6F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 10).cuboid(4.92F, -1.5F, 8.6F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F))
		.uv(2, 9).cuboid(4.92F, -1.5F, 9.6F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 9).cuboid(6.6F, -1.5F, 4.92F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 3).cuboid(7.6F, -1.5F, 5.92F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 2).cuboid(7.6F, -1.5F, 4.92F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 1).cuboid(8.6F, -1.5F, 4.92F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(9.6F, -1.5F, 4.92F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		tail.addChild("cube_r3", ModelPartBuilder.create().uv(0, 17).cuboid(-1.5F, -3.6F, 0.2F, 3.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.5288F, 0.0F, 0.0F));

		ModelPartData claws = body.addChild("claws", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData claw1 = claws.addChild("claw1", ModelPartBuilder.create().uv(0, 24).cuboid(-5.0F, -1.0F, -8.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(14, 2).cuboid(-3.0F, -1.0F, -8.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(8, 21).cuboid(-4.0F, -1.0F, -6.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		claw1.addChild("cube_r6", ModelPartBuilder.create().uv(13, 6).cuboid(-7.0F, -1.5F, -2.0F, 4.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.1447F, -0.6855F, -0.2262F));

		ModelPartData claw2 = claws.addChild("claw2", ModelPartBuilder.create().uv(10, 24).cuboid(4.0F, -1.0F, -8.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(14, 9).cuboid(2.0F, -1.0F, -8.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 21).cuboid(3.0F, -1.0F, -6.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		claw2.addChild("cube_r5", ModelPartBuilder.create().uv(13, 7).cuboid(3.0F, -1.5F, -2.0F, 4.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.1447F, 0.6855F, 0.2262F));

		ModelPartData antennas = body.addChild("antennas", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData antenna1 = antennas.addChild("antenna1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		antenna1.addChild("cube_r7", ModelPartBuilder.create().uv(0, 0).cuboid(0.3F, -0.3F, -9.3F, 1.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.2411F, -0.2821F, -1.5048F));

		ModelPartData antenna2 = antennas.addChild("antenna2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		antenna2.addChild("cube_r8", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, 0.0F, -9.3F, 1.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.2411F, 0.2821F, 1.6032F));

		ModelPartData legs = crayfish.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData leg_1 = legs.addChild("leg_1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		leg_1.addChild("leg1", ModelPartBuilder.create().uv(0, 6).cuboid(-4.3F, -1.65F, -3.0F, 3.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		ModelPartData leg_2 = legs.addChild("leg_2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 2.0F));

		leg_2.addChild("leg2", ModelPartBuilder.create().uv(0, 4).cuboid(-4.3F, -1.65F, -1.0F, 3.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -2.0F, 0.0F, 0.0F, -0.3491F));

		ModelPartData leg_3 = legs.addChild("leg_3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 4.0F));

		leg_3.addChild("leg3", ModelPartBuilder.create().uv(0, 5).cuboid(-4.3F, -1.65F, 1.0F, 3.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -4.0F, 0.0F, 0.0F, -0.3491F));

		ModelPartData leg_4 = legs.addChild("leg_4", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		leg_4.addChild("leg4", ModelPartBuilder.create().uv(0, 7).cuboid(1.3F, -1.65F, -3.0F, 3.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

		ModelPartData leg_5 = legs.addChild("leg_5", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 2.0F));

		leg_5.addChild("leg5", ModelPartBuilder.create().uv(13, 13).cuboid(1.3F, -1.65F, -1.0F, 3.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -2.0F, 0.0F, 0.0F, 0.3491F));

		ModelPartData leg_6 = legs.addChild("leg_6", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 4.0F));

		leg_6.addChild("leg6", ModelPartBuilder.create().uv(13, 14).cuboid(1.3F, -1.65F, 1.0F, 3.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -4.0F, 0.0F, 0.0F, 0.3491F));
		return TexturedModelData.of(modelData, 32, 32);
	}

	@Override
	public void setAngles(CrayfishEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.updateAnimation(entity.moveAnimationState, CrayfishAnimations.CRAYFISH_WALK, ageInTicks, 1f);
		this.updateAnimation(entity.idleAnimationState, CrayfishAnimations.CRAYFISH_IDLE, ageInTicks, 1f);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		crayfish.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

    @Override
    public ModelPart getPart() {
       return crayfish;
    }
}
