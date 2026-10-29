package featurecreep.api.bg.model;

import com.mojang.blaze3d.vertex.PoseStack;
import featurecreep.api.model.Pose;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;

/**
 * Draws one model. Box-only models go through a vanilla part tree submitted with {@code submitModelPart}; models with
 * per-face cuboids or mesh geometry go through {@link ModelRig}. The choice is made once per model.
 */
final class ModelDraw {

	private static final Map<ModelSpec, ModelDraw> DRAWS = new WeakHashMap<>();

	private final LayerDefinition layer;

	private ModelDraw(ModelSpec spec) {
		this.layer = VanillaRig.eligible(spec.model())
				? VanillaRig.layer(spec.model(), spec.textureWidth(), spec.textureHeight())
				: null;
	}

	static ModelDraw of(ModelSpec spec) {
		synchronized (DRAWS) {
			return DRAWS.computeIfAbsent(spec, ModelDraw::new);
		}
	}

	/** A fresh vanilla part tree for one render state. Null when the model takes the custom path. */
	ModelPart newRoot() {
		return layer == null ? null : layer.bakeRoot();
	}

	/**
	 * Submits the model with the given pose. The vanilla root holds the pose after this call, so each render state must
	 * own its root.
	 */
	void submit(ModelSpec spec, ModelPart root, Pose pose, PoseStack stack, SubmitNodeCollector collector, int light,
			int overlay) {
		if (root == null) {
			ModelRig.submit(spec, pose, stack, collector, light, overlay);
			return;
		}
		VanillaRig.apply(root, spec.model(), pose);
		collector.submitModelPart(root, stack, renderType(spec.texture()), light, overlay, null);
	}

	static RenderType renderType(Identifier texture) {
		return RenderTypes.entityCutoutCull(texture);
	}
}
