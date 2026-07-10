package asbestosstar.bootstrap.sm;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.jboss.modules.Module;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.transformer.IMixinTransformer;

import asbestosstar.bootstrap.BootstrapCommon;
import featurecreep.loader.FCLoaderBasic;
import featurecreep.loader.GameProvider;
import featurecreep.loader.flat.FCLoaderFlat;

/**
 * Loader-neutral Sponge Mixin runtime.
 *
 * <p>
 * All loader-specific hooks delegate to the same byte[] or ClassNode entry
 * points in this class.
 * </p>
 */
public final class SpongeMixinRuntime {
	private static final Set<Object> REGISTERED_MODULES = Collections.newSetFromMap(new IdentityHashMap<>());

	private static volatile boolean initialized;
	private static volatile boolean defaultPhase;
	private static volatile GameProvider provider;
	private static volatile FCLoaderBasic moduleLoader;
	private static volatile FCLoaderFlat flatLoader;

	private SpongeMixinRuntime() {
	}

	public static synchronized void initialize(GameProvider gameProvider, FCLoaderBasic modules, FCLoaderFlat flat) {
		if (initialized) {
			return;
		}

		provider = gameProvider;
		moduleLoader = modules;
		flatLoader = flat;

		System.setProperty("mixin.bootstrapService", FeatureCreepMixinServiceBootstrap.class.getName());
		System.setProperty("mixin.service", FeatureCreepMixinService.class.getName());
		System.setProperty("mixin.globalPropertyService", FeatureCreepGlobalPropertyService.class.getName());

		MixinBootstrap.init();
		initializeMixinExtras();

		initialized = true;
		gotoDefaultPhase();
	}

	/**
	 * Registers the configuration owned by every discovered module.
	 *
	 * <p>
	 * No command-line property is used. Each module remains the source of its own
	 * configuration through the SpongeMixinConfig module property.
	 * </p>
	 */
	public static synchronized void registerDiscoveredModConfigurations() {
		if (moduleLoader == null) {
			return;
		}

		for (Module module : moduleLoader.getModules()) {
			if (!REGISTERED_MODULES.add(module)) {
				continue;
			}

			String configuration = module.getProperty("SpongeMixinConfig");
			if (configuration == null || configuration.trim().isEmpty()) {
				continue;
			}

			registerConfiguration(module.getClassLoader(), configuration.trim(), module.getName());
		}
	}

	public static synchronized void registerConfiguration(ClassLoader ownerLoader, String configuration,
			String ownerName) {
		ClassLoader previous = Thread.currentThread().getContextClassLoader();

		try {
			Thread.currentThread().setContextClassLoader(ownerLoader);

			if (ownerLoader.getResource(configuration) == null) {
				throw new IllegalArgumentException(
						"Mixin configuration " + configuration + " was not found in " + ownerName);
			}

			Mixins.addConfiguration(configuration);
			System.out.println("[FeatureCreep/Mixin] Registered " + configuration + " from " + ownerName);
		} finally {
			Thread.currentThread().setContextClassLoader(previous);
		}
	}

	public static byte[] transformBytes(String className, String transformedName, byte[] classBytes) {
		if (!isReady() || classBytes == null) {
			return classBytes;
		}

		IMixinTransformer transformer = FeatureCreepMixinService.getTransformer();
		if (transformer == null) {
			return classBytes;
		}

		String original = normalize(className);
		String transformed = normalize(transformedName == null ? className : transformedName);

		byte[] preTransformed = PreMixinTransformPipeline.transform(transformed, classBytes);

		byte[] result = transformer.transformClassBytes(original, transformed, preTransformed);

		return result == null ? preTransformed : result;
	}

	public static boolean transformClassNode(String className, ClassNode node) {
		if (!isReady() || node == null) {
			return false;
		}

		IMixinTransformer transformer = FeatureCreepMixinService.getTransformer();
		if (transformer == null) {
			return false;
		}

		String normalized = normalize(className);

		/*
		 * Apply byte-oriented pre-transforms first so both transformation paths have
		 * identical behavior.
		 */
		ClassWriter writer = new ClassWriter(0);
		node.accept(writer);
		byte[] preTransformed = PreMixinTransformPipeline.transform(normalized, writer.toByteArray());

		ClassNode preNode = new ClassNode();
		new ClassReader(preTransformed).accept(preNode, 0);

		boolean changed = transformer.transformClass(MixinEnvironment.getCurrentEnvironment(), normalized, preNode);

		if (changed) {
			copyNode(preNode, node);
		}
		return changed;
	}

	public static BytecodeTransformer byteArrayTransformer() {
		return SpongeMixinRuntime::transformBytes;
	}

	public static ClassNodeTransformer classNodeTransformer() {
		return SpongeMixinRuntime::transformClassNode;
	}

	public static boolean isReady() {
		return initialized && defaultPhase;
	}

	public static GameProvider getProvider() {
		return provider;
	}

	private static void initializeMixinExtras() {
		try {
			Class<?> bootstrap = Class.forName("com.llamalad7.mixinextras.MixinExtrasBootstrap", true,
					SpongeMixinRuntime.class.getClassLoader());
			bootstrap.getMethod("init").invoke(null);
		} catch (ClassNotFoundException ignored) {
		} catch (ReflectiveOperationException exception) {
			throw new IllegalStateException("Could not initialize MixinExtras", exception);
		}
	}

	private static void gotoDefaultPhase() {
		try {
			Method method = MixinEnvironment.class.getDeclaredMethod("gotoPhase", MixinEnvironment.Phase.class);
			method.setAccessible(true);
			method.invoke(null, MixinEnvironment.Phase.DEFAULT);
			defaultPhase = true;
		} catch (ReflectiveOperationException exception) {
			throw new IllegalStateException("Could not enter Sponge Mixin DEFAULT phase", exception);
		}
	}

	private static String normalize(String name) {
		return name == null ? null : name.replace('/', '.');
	}

	private static void copyNode(ClassNode source, ClassNode target) {
		target.version = source.version;
		target.access = source.access;
		target.name = source.name;
		target.signature = source.signature;
		target.superName = source.superName;
		target.interfaces = source.interfaces;
		target.sourceFile = source.sourceFile;
		target.sourceDebug = source.sourceDebug;
		target.module = source.module;
		target.outerClass = source.outerClass;
		target.outerMethod = source.outerMethod;
		target.outerMethodDesc = source.outerMethodDesc;
		target.visibleAnnotations = source.visibleAnnotations;
		target.invisibleAnnotations = source.invisibleAnnotations;
		target.visibleTypeAnnotations = source.visibleTypeAnnotations;
		target.invisibleTypeAnnotations = source.invisibleTypeAnnotations;
		target.attrs = source.attrs;
		target.innerClasses = source.innerClasses;
		target.nestHostClass = source.nestHostClass;
		target.nestMembers = source.nestMembers;
		target.permittedSubclasses = source.permittedSubclasses;
		target.recordComponents = source.recordComponents;
		target.fields = source.fields;
		target.methods = source.methods;
	}
}
