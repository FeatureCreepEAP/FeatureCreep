//Originally Made in CubikStudio, imported to BlockBench for updating

package featurecreep.legacy;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;

public class Yellow_Windigo extends EntityModel<YellowWindigoEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new ModelIdentifier("featurecreep", "yellow_windigo"), "main");
	private final ModelPart e41;
	private final ModelPart bb_main;

	public Yellow_Windigo(ModelPart root) {
		this.e41 = root.getChild("e41");
		this.bb_main = root.getChild("bb_main");
	}

	public static TexturedModelData createBodyLayer() {
		ModelData ModelData = new ModelData();
		ModelPartData ModelPartData = ModelData.getRoot();

		ModelPartData e41 = ModelPartData.addChild("e41", ModelPartBuilder.create(), ModelTransform.pivot(45.5F, 24.0F, -5.0F));

		ModelPartData bb_main = ModelPartData.addChild("bb_main", ModelPartBuilder.create().uv(100, 147).cuboid(8.5F, -10.0F, -9.0F, 10.0F, 10.0F, 18.0F, new Dilation(0.0F))
		.uv(160, 210).cuboid(-7.5F, -6.5F, 10.0F, 10.0F, 10.0F, 5.0F, new Dilation(0.0F))
		.uv(52, 7).cuboid(-7.5F, -6.5F, -15.0F, 10.0F, 10.0F, 5.0F, new Dilation(0.0F))
		.uv(90, 215).cuboid(-20.0F, -22.0F, -14.5F, 30.0F, 5.0F, 5.0F, new Dilation(0.0F))
		.uv(152, 60).cuboid(-27.0F, -10.0F, -22.0F, 10.0F, 1.0F, 20.0F, new Dilation(0.0F))
		.uv(200, 209).cuboid(-25.5F, -13.5F, -32.0F, 3.0F, 1.0F, 10.0F, new Dilation(0.0F))
		.uv(200, 209).cuboid(-27.0F, -10.0F, -6.5F, 3.0F, 1.0F, 10.0F, new Dilation(0.0F))
		.uv(200, 209).cuboid(-27.0F, -10.0F, -11.5F, 3.0F, 1.0F, 10.0F, new Dilation(0.0F))
		.uv(200, 209).cuboid(-27.0F, -10.0F, -16.0F, 3.0F, 1.0F, 10.0F, new Dilation(0.0F))
		.uv(200, 209).cuboid(-27.0F, -10.0F, -20.5F, 3.0F, 1.0F, 10.0F, new Dilation(0.0F))
		.uv(90, 215).cuboid(-20.0F, -20.5F, 10.0F, 30.0F, 5.0F, 5.0F, new Dilation(0.0F))
		.uv(152, 60).cuboid(-27.0F, -8.5F, 2.5F, 10.0F, 1.0F, 20.0F, new Dilation(0.0F))
		.uv(200, 209).cuboid(-25.5F, -12.0F, 22.0F, 3.0F, 1.0F, 10.0F, new Dilation(0.0F))
		.uv(200, 209).cuboid(-27.0F, -8.5F, 4.0F, 3.0F, 1.0F, 10.0F, new Dilation(0.0F))
		.uv(200, 209).cuboid(-27.0F, -8.5F, 8.5F, 3.0F, 1.0F, 10.0F, new Dilation(0.0F))
		.uv(200, 209).cuboid(-27.0F, -8.5F, 13.0F, 3.0F, 1.0F, 10.0F, new Dilation(0.0F))
		.uv(200, 209).cuboid(-27.0F, -8.5F, 18.0F, 3.0F, 1.0F, 10.0F, new Dilation(0.0F))
		.uv(156, 150).cuboid(-18.5F, -3.0F, -7.5F, 15.0F, 10.0F, 15.0F, new Dilation(0.0F))
		.uv(152, 104).cuboid(-30.0F, 6.5F, -6.5F, 15.0F, 10.0F, 13.0F, new Dilation(0.0F))
		.uv(0, 46).cuboid(-35.0F, 0.0F, -7.5F, 15.0F, 15.0F, 15.0F, new Dilation(0.0F))
		.uv(152, 81).cuboid(-50.0F, 8.5F, -6.5F, 15.0F, 10.0F, 13.0F, new Dilation(0.0F))
		.uv(0, 206).cuboid(-70.0F, -17.0F, -13.0F, 20.0F, 25.0F, 25.0F, new Dilation(0.0F))
		.uv(100, 101).cuboid(-102.0F, 1.0F, -6.5F, 13.0F, 13.0F, 13.0F, new Dilation(0.0F))
		.uv(200, 220).cuboid(-80.0F, -4.6F, -9.0F, 10.0F, 18.0F, 18.0F, new Dilation(0.0F))
		.uv(0, 14).cuboid(-89.5F, -2.7F, -8.0F, 10.0F, 16.0F, 16.0F, new Dilation(0.0F))
		.uv(100, 127).cuboid(-127.5F, 5.5F, -6.0F, 25.0F, 10.0F, 10.0F, new Dilation(0.0F))
		.uv(90, 208).cuboid(-63.0F, 21.0F, -15.5F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(90, 208).cuboid(-63.0F, 21.0F, 12.0F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(90, 211).cuboid(-50.0F, 26.5F, -10.5F, 15.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(90, 211).cuboid(-35.5F, 26.5F, -10.0F, 15.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(90, 211).cuboid(-33.5F, 26.5F, 9.0F, 15.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(90, 211).cuboid(-48.0F, 26.5F, 8.5F, 15.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 175).cuboid(-13.0F, 23.8641F, -36.3785F, 25.0F, 1.0F, 30.0F, new Dilation(0.0F))
		.uv(0, 144).cuboid(-10.0F, 22.0F, -65.5F, 20.0F, 1.0F, 30.0F, new Dilation(0.0F))
		.uv(90, 225).cuboid(-12.5F, 3.0F, 3.5F, 25.0F, 1.0F, 30.0F, new Dilation(0.0F))
		.uv(110, 175).cuboid(-10.0F, 1.55F, 33.4115F, 20.0F, 1.0F, 30.0F, new Dilation(0.0F))
		.uv(0, 76).cuboid(-10.0F, -10.0F, -10.0F, 20.0F, 10.0F, 20.0F, new Dilation(0.0F))
		.uv(100, 75).cuboid(18.0F, -10.0F, -8.0F, 10.0F, 10.0F, 16.0F, new Dilation(0.0F))
		.uv(52, 22).cuboid(27.0F, -10.0F, -7.0F, 10.0F, 10.0F, 14.0F, new Dilation(0.0F))
		.uv(210, 184).cuboid(37.0F, -10.0F, -6.0F, 10.0F, 10.0F, 12.0F, new Dilation(0.0F))
		.uv(60, 56).cuboid(45.5F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new Dilation(0.0F))
		.uv(0, 106).cuboid(54.0F, 0.5F, -17.0F, 3.0F, 3.0F, 35.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		return TexturedModelData.of(ModelData, 512, 512);
	}

	@Override
	public void setAngles(YellowWindigoEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void render(MatrixStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		e41.render(poseStack, buffer, packedLight, packedOverlay);
		bb_main.render(poseStack, buffer, packedLight, packedOverlay);
	}



}