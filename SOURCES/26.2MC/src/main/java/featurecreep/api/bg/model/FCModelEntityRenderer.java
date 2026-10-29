package featurecreep.api.bg.model;

import com.mojang.blaze3d.vertex.PoseStack;
import featurecreep.api.model.Pose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Entity;

public final class FCModelEntityRenderer<T extends Entity & ModelHolder>
		extends EntityRenderer<T, FCModelEntityRenderer.State> {

	public static final class State extends EntityRenderState {
		ModelSpec spec;
		Pose pose;
		ModelDraw draw;
		ModelPart root;
	}

	public FCModelEntityRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public State createRenderState() {
		return new State();
	}

	@Override
	public void extractRenderState(T entity, State state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		ModelSpec spec = entity.modelSpec();
		state.spec = spec;
		state.pose = ModelRig.pose(spec, entity.animationSeconds(partialTick));
		state.draw = ModelDraw.of(spec);
		state.root = state.draw.newRoot();
	}

	@Override
	public void submit(State state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState camera) {
		super.submit(state, poseStack, collector, camera);
		poseStack.pushPose();
		ModelRig.applyEntityBase(poseStack);
		state.draw.submit(state.spec, state.root, state.pose, poseStack, collector, state.lightCoords,
				OverlayTexture.NO_OVERLAY);
		poseStack.popPose();
	}
}
