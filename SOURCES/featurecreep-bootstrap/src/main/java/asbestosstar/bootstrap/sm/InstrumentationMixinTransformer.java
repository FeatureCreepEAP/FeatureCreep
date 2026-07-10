package asbestosstar.bootstrap.sm;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

/**
 * Vanilla/TLauncher Instrumentation adapter.
 */
public final class InstrumentationMixinTransformer implements ClassFileTransformer {

	private static final ThreadLocal<Boolean> ACTIVE = ThreadLocal.withInitial(() -> Boolean.FALSE);

	@Override
	public byte[] transform(Module module, ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) {
		if (className == null || classfileBuffer == null || Boolean.TRUE.equals(ACTIVE.get()) || excluded(className)) {
			return null;
		}

		if (!SpongeMixinRuntime.isReady()) {
			return null;
		}

		try {
			ACTIVE.set(Boolean.TRUE);
			byte[] transformed = SpongeMixinRuntime.transformBytes(className, className, classfileBuffer);

			return transformed == classfileBuffer ? null : transformed;
		} catch (Throwable throwable) {
			System.err.println("[FeatureCreep/Mixin] Instrumentation transform failed for " + className);
			throwable.printStackTrace(System.err);
			return null;
		} finally {
			ACTIVE.set(Boolean.FALSE);
		}
	}

	private static boolean excluded(String name) {
		return name.startsWith("java/") || name.startsWith("javax/") || name.startsWith("jdk/")
				|| name.startsWith("sun/") || name.startsWith("org/objectweb/asm/")
				|| name.startsWith("org/spongepowered/asm/") || name.startsWith("asbestosstar/bootstrap/sm/");
	}
}
