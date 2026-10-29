package featurecreep.api.bg.model;

import featurecreep.api.model.Model;
import featurecreep.api.model.animation.Animator;
import net.minecraft.resources.Identifier;

/**
 * A model as the bridge renders it: the model, an optional animator, and the atlas texture it samples. Texture size is
 * read from {@code Model.metadata()} keys {@code texture_width} and {@code texture_height}; the canonical constructor
 * takes the sizes directly for models without that metadata.
 */
public record ModelSpec(Model model, Animator animator, Identifier texture, int textureWidth, int textureHeight) {

	public ModelSpec {
		if (textureWidth <= 0 || textureHeight <= 0) {
			throw new IllegalArgumentException("texture size must be positive: " + textureWidth + "x" + textureHeight);
		}
	}

	public static ModelSpec of(Model model, Animator animator, Identifier texture) {
		return new ModelSpec(model, animator, texture, dimension(model, "texture_width"),
				dimension(model, "texture_height"));
	}

	private static int dimension(Model model, String key) {
		String raw = model.metadata().get(key);
		if (raw == null) {
			throw new IllegalArgumentException("model metadata is missing " + key);
		}
		return Integer.parseInt(raw);
	}
}
