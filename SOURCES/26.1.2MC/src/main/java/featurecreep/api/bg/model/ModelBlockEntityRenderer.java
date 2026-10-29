package featurecreep.api.bg.model;

import com.mojang.blaze3d.vertex.PoseStack;
import featurecreep.api.model.Pose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.entity.BlockEntity;

/** Renders a block entity as a static model at its block position. */
public final class ModelBlockEntityRenderer implements BlockEntityRenderer<BlockEntity, ModelBlockEntityRenderer.State> {

    private final ModelDraw draw;
    private final ModelPart root;

    ModelBlockEntityRenderer(ModelDraw draw) {
        this.draw = draw;
        this.root = draw.newRoot();
    }

    @Override
    public State createRenderState() {
        return new State(draw.restPose());
    }

    @Override
    public void submit(State state, PoseStack stack, SubmitNodeCollector collector, CameraRenderState camera) {
        draw.draw(root, state.pose, stack, collector, state.lightCoords, OverlayTexture.NO_OVERLAY);
    }

    public static final class State extends BlockEntityRenderState {
        final Pose pose;

        State(Pose pose) {
            this.pose = pose;
        }
    }
}
