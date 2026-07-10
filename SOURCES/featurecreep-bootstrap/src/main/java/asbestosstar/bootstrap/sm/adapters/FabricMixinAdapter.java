package asbestosstar.bootstrap.sm.adapters;

import org.objectweb.asm.tree.ClassNode;

import asbestosstar.bootstrap.sm.SpongeMixinRuntime;

/**
 * Adapter to call from a Fabric class-loading hook.
 *
 * <p>
 * Use whichever method matches the hook available in your Fabric loader
 * integration.
 * </p>
 */
public final class FabricMixinAdapter {
	private FabricMixinAdapter() {
	}

	public static byte[] transform(String className, String transformedName, byte[] bytes) {
		return SpongeMixinRuntime.transformBytes(className, transformedName, bytes);
	}

	public static boolean transform(String className, ClassNode classNode) {
		return SpongeMixinRuntime.transformClassNode(className, classNode);
	}
}
