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

// Made with CubikStudio, Exported with Blockbench 4.2.4 for FC3
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class mech_killer_robot_1000 extends EntityModel<MechKillerRobot1000Entity>  {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new ModelIdentifier("featurecreep", "mech_killer_robot_1000"), "main");
	private final ModelPart bb_main;

	public mech_killer_robot_1000(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static TexturedModelData createBodyLayer() {
		ModelData ModelData = new ModelData();
		ModelPartData ModelPartData = ModelData.getRoot();

		ModelPartData bb_main = ModelPartData.addChild("bb_main", ModelPartBuilder.create().uv(36, 96).cuboid(13.0F, -1.0F, -10.0F, 10.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(36, 98).cuboid(13.0F, -6.0F, -9.0F, 11.0F, 6.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 96).cuboid(13.0F, -2.0F, -8.0F, 11.0F, 2.0F, 7.0F, new Dilation(0.0F))
		.uv(74, 105).cuboid(13.0F, 1.0F, -2.0F, 11.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 84).cuboid(13.0F, -6.0F, -8.0F, 11.0F, 10.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 73).cuboid(13.0F, -4.0F, -6.0F, 11.0F, 9.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 73).cuboid(-10.0F, -4.0F, -6.0F, 11.0F, 9.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 84).cuboid(-10.0F, -6.0F, -8.0F, 11.0F, 10.0F, 2.0F, new Dilation(0.0F))
		.uv(36, 98).cuboid(-10.0F, -6.0F, -9.0F, 11.0F, 6.0F, 1.0F, new Dilation(0.0F))
		.uv(60, 103).cuboid(-10.0F, -1.0F, -10.0F, 10.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 96).cuboid(-10.0F, -2.0F, -8.0F, 11.0F, 2.0F, 7.0F, new Dilation(0.0F))
		.uv(74, 105).cuboid(-10.0F, 1.0F, -2.0F, 11.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 105).cuboid(-10.0F, -6.0F, -8.0F, 34.0F, 20.0F, 3.0F, new Dilation(0.0F))
		.uv(120, 124).cuboid(24.0F, 30.0F, -8.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(120, 120).cuboid(-12.0F, 30.0F, -8.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(116, 107).cuboid(-13.0F, -6.0F, -7.0F, 1.0F, 20.0F, 1.0F, new Dilation(0.0F))
		.uv(116, 107).cuboid(26.0F, -6.0F, -7.0F, 1.0F, 20.0F, 1.0F, new Dilation(0.0F))
		.uv(74, 107).cuboid(-4.0F, 13.0F, -7.0F, 20.0F, 20.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		return TexturedModelData.of(ModelData, 16, 16);
	}


	@Override
	public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setAngles(MechKillerRobot1000Entity var1, float var2, float var3, float var4, float var5, float var6) {
		// TODO Auto-generated method stub
		
	}
}