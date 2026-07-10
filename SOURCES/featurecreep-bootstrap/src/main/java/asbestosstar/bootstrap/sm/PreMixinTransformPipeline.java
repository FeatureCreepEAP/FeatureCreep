package asbestosstar.bootstrap.sm;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Shared pre-Mixin transformation pipeline for access wideners, coremods, or
 * other FeatureCreep transformers.
 */
public final class PreMixinTransformPipeline {
	private static final List<BytecodeTransformer> TRANSFORMERS = new CopyOnWriteArrayList<>();

	private PreMixinTransformPipeline() {
	}

	public static void register(BytecodeTransformer transformer) {
		TRANSFORMERS.add(transformer);
	}

	public static byte[] transform(String className, byte[] classBytes) {
		byte[] current = classBytes;

		for (BytecodeTransformer transformer : TRANSFORMERS) {
			current = transformer.transform(className, className, current);
			if (current == null) {
				throw new IllegalStateException(transformer.getClass().getName() + " returned null for " + className);
			}
		}

		return current;
	}
}
