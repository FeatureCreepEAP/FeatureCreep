package asbestosstar.bootstrap.sm;

/**
 * Generic byte-array transformation contract usable by vanilla,
 * Instrumentation, Fabric, Forge, and NeoForge adapters.
 */
@FunctionalInterface
public interface BytecodeTransformer {
	byte[] transform(String className, String transformedName, byte[] classBytes);
}
