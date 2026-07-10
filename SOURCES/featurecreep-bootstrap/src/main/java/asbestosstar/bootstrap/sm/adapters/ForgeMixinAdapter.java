package asbestosstar.bootstrap.sm.adapters;

import org.objectweb.asm.tree.ClassNode;

import asbestosstar.bootstrap.sm.SpongeMixinRuntime;

/**
 * Adapter to call from the Forge/ModLauncher transformation service.
 */
public final class ForgeMixinAdapter {
	private ForgeMixinAdapter() {
	}

	public static byte[] transform(String className, String transformedName, byte[] bytes) {
		return SpongeMixinRuntime.transformBytes(className, transformedName, bytes);
	}

	public static boolean transform(String className, ClassNode classNode) {
		return SpongeMixinRuntime.transformClassNode(className, classNode);
	}
}
