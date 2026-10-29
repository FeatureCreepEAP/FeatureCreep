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

/** Renders an entity as a model, with its pose refreshed from a {@link PoseSource} each frame. */
public final class ModelEntityRenderer extends EntityRenderer<Entity, ModelEntityRenderer.State> {

    /** Fills the pose for an entity. The pose arrives at rest, so a source only writes the parts it animates. */
    @FunctionalInterface
    public interface PoseSource {
        void apply(Entity entity, float partialTick, Pose pose);
    }

    static final PoseSource REST = (entity, partialTick, pose) -> {
    };

    private final ModelDraw draw;
    private final PoseSource poseSource;

    ModelEntityRenderer(EntityRendererProvider.Context context, ModelDraw draw, PoseSource poseSource) {
        super(context);
        this.draw = draw;
        this.poseSource = poseSource;
    }

    @Override
    public State createRenderState() {
        return new State(draw.restPose(), draw.newRoot());
    }

    @Override
    public void extractRenderState(Entity entity, State state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.pose.reset();
        poseSource.apply(entity, partialTick, state.pose);
    }

    @Override
    public void submit(State state, PoseStack stack, SubmitNodeCollector collector, CameraRenderState camera) {
        super.submit(state, stack, collector, camera);
        draw.draw(state.root, state.pose, stack, collector, state.lightCoords, OverlayTexture.NO_OVERLAY);
    }

    public static final class State extends EntityRenderState {
        final Pose pose;
        final ModelPart root;

        State(Pose pose, ModelPart root) {
            this.pose = pose;
            this.root = root;
        }
    }
}
