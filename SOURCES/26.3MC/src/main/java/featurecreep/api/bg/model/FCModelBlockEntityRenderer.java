package featurecreep.api.bg.model;

import com.mojang.blaze3d.vertex.PoseStack;
import featurecreep.api.model.Pose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;

public final class FCModelBlockEntityRenderer<T extends BlockEntity & ModelHolder>
		implements BlockEntityRenderer<T, FCModelBlockEntityRenderer.State> {

	public static final class State extends BlockEntityRenderState {
		ModelSpec spec;
		Pose pose;
		ModelDraw draw;
		ModelPart root;
	}

	@Override
	public State createRenderState() {
		return new State();
	}

	@Override
	public void extractRenderState(T blockEntity, State state, float partialTick, Vec3 cameraPos,
			ModelFeatureRenderer.CrumblingOverlay overlay) {
		BlockEntityRenderState.extractBase(blockEntity, state, overlay);
		ModelSpec spec = blockEntity.modelSpec();
		state.spec = spec;
		state.pose = ModelRig.pose(spec, blockEntity.animationSeconds(partialTick));
		state.draw = ModelDraw.of(spec);
		state.root = state.draw.newRoot();
	}

	@Override
	public void submit(State state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState camera) {
		poseStack.pushPose();
		ModelRig.applyBlockBase(poseStack);
		state.draw.submit(state.spec, state.root, state.pose, poseStack, collector, state.lightCoords,
				OverlayTexture.NO_OVERLAY);
		poseStack.popPose();
	}
}
