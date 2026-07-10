package asbestosstar.bootstrap.sm;

import org.objectweb.asm.tree.ClassNode;

/**
 * Generic ClassNode transformation contract.
 */
@FunctionalInterface
public interface ClassNodeTransformer {
	boolean transform(String className, ClassNode classNode);
}
