package asbestosstar.bootstrap.sm.adapters;

import org.objectweb.asm.tree.ClassNode;

import asbestosstar.bootstrap.sm.SpongeMixinRuntime;

/**
 * Adapter to call from the NeoForge/ModLauncher transformation service.
 */
public final class NeoForgeMixinAdapter {
	private NeoForgeMixinAdapter() {
	}

	public static byte[] transform(String className, String transformedName, byte[] bytes) {
		return SpongeMixinRuntime.transformBytes(className, transformedName, bytes);
	}

	public static boolean transform(String className, ClassNode classNode) {
		return SpongeMixinRuntime.transformClassNode(className, classNode);
	}
}
