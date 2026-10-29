package featurecreep.api.bg.model;

import com.mojang.blaze3d.vertex.PoseStack;
import featurecreep.api.model.Model;
import featurecreep.api.model.Pose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;

/**
 * Draws one model. Box-only models go through a vanilla part tree submitted with {@code submitModelPart}; models with
 * per-face cuboids or mesh geometry go through {@link ModelRig}. The choice is made once per model.
 */
final class ModelDraw {

    private static final int DEFAULT_TEXTURE_SIZE = 16;

    private final Model model;
    private final LayerDefinition layer;
    private final Identifier texture;

    ModelDraw(Model model) {
        this.model = model;
        this.layer = VanillaRig.eligible(model)
                ? VanillaRig.layer(model, textureSize("texture_width"), textureSize("texture_height"))
                : null;
        this.texture = ModelRig.texture(model, 0);
    }

    private int textureSize(String key) {
        String value = model.metadata().get(key);
        return value == null ? DEFAULT_TEXTURE_SIZE : Integer.parseInt(value);
    }

    Pose restPose() {
        return Pose.rest(model);
    }

    /** A vanilla part tree for one renderer, baked once. Null when the model takes the custom path. */
    ModelPart newRoot() {
        return layer == null ? null : layer.bakeRoot();
    }

    /**
     * Submits the model with the given pose. Vanilla reads the part fields when it draws the queued submit, so a root
     * may only be shared when every submit writes the same values; each entity render state owns its own root.
     */
    void draw(ModelPart root, Pose pose, PoseStack stack, SubmitNodeCollector collector, int light, int overlay) {
        if (layer == null) {
            ModelRig.submit(model, pose, stack, collector, light, overlay);
            return;
        }
        VanillaRig.apply(root, model, pose);
        collector.submitModelPart(root, stack, RenderTypes.entityCutoutCull(texture), light, overlay, null);
    }
}
