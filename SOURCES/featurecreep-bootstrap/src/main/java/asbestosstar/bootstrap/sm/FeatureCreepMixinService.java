package asbestosstar.bootstrap.sm;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.Collections;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.launch.platform.container.ContainerHandleURI;
import org.spongepowered.asm.launch.platform.container.IContainerHandle;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.transformer.IMixinTransformer;
import org.spongepowered.asm.mixin.transformer.IMixinTransformerFactory;
import org.spongepowered.asm.service.IAdviceProvider;
import org.spongepowered.asm.service.IClassBytecodeProvider;
import org.spongepowered.asm.service.IClassProvider;
import org.spongepowered.asm.service.IClassTracker;
import org.spongepowered.asm.service.IFeatureValidator;
import org.spongepowered.asm.service.IMixinAuditTrail;
import org.spongepowered.asm.service.IMixinInternal;
import org.spongepowered.asm.service.ITransformer;
import org.spongepowered.asm.service.ITransformerProvider;
import org.spongepowered.asm.service.MixinServiceAbstract;

import asbestosstar.bootstrap.BootstrapCommon;

/**
 * Mixin service used by every FeatureCreep Minecraft provider.
 */
public final class FeatureCreepMixinService extends MixinServiceAbstract
		implements IClassProvider, IClassBytecodeProvider, ITransformerProvider, IClassTracker {

	private static volatile IMixinTransformer transformer;
	private final ClassLoader gameClassLoader;

	public FeatureCreepMixinService() {
		ClassLoader context = Thread.currentThread().getContextClassLoader();
		gameClassLoader = context != null ? context : ClassLoader.getSystemClassLoader();
	}

	@Override
	public void offer(IMixinInternal internal) {
		super.offer(internal);
		if (internal instanceof IMixinTransformerFactory) {
			transformer = ((IMixinTransformerFactory) internal).createTransformer();
		}
	}

	public static IMixinTransformer getTransformer() {
		return transformer;
	}

	@Override
	@Deprecated
	public URL[] getClassPath() {
		if (gameClassLoader instanceof URLClassLoader) {
			return ((URLClassLoader) gameClassLoader).getURLs();
		}
		return new URL[0];
	}

	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		return gameClassLoader.loadClass(name);
	}

	@Override
	public Class<?> findClass(String name, boolean initialize) throws ClassNotFoundException {
		return Class.forName(name, initialize, gameClassLoader);
	}

	@Override
	public Class<?> findAgentClass(String name, boolean initialize) throws ClassNotFoundException {
		return Class.forName(name, initialize, FeatureCreepMixinService.class.getClassLoader());
	}

	@Override
	public ClassNode getClassNode(String name) throws ClassNotFoundException, IOException {
		return getClassNode(name, true, 0);
	}

	@Override
	public ClassNode getClassNode(String name, boolean runTransformers) throws ClassNotFoundException, IOException {
		return getClassNode(name, runTransformers, 0);
	}

	@Override
	public ClassNode getClassNode(String name, boolean runTransformers, int readerFlags)
			throws ClassNotFoundException, IOException {
		byte[] bytes = rawBytes(name);
		if (bytes == null) {
			throw new ClassNotFoundException(name);
		}

		if (runTransformers) {
			bytes = PreMixinTransformPipeline.transform(name.replace('/', '.'), bytes);
		}

		ClassNode node = new ClassNode();
		new ClassReader(bytes).accept(node, readerFlags);
		return node;
	}

	private byte[] rawBytes(String className) throws IOException {
		String resource = className.replace('.', '/').replace('\\', '/') + ".class";

		InputStream stream = gameClassLoader.getResourceAsStream(resource);
		if (stream == null) {
			stream = FeatureCreepMixinService.class.getClassLoader().getResourceAsStream(resource);
		}
		if (stream == null) {
			return null;
		}

		try (InputStream input = stream) {
			return input.readAllBytes();
		}
	}

	@Override
	public Collection<ITransformer> getTransformers() {
		return Collections.emptyList();
	}

	@Override
	public Collection<ITransformer> getDelegatedTransformers() {
		return Collections.emptyList();
	}

	@Override
	public void addTransformerExclusion(String name) {
	}

	@Override
	public void registerInvalidClass(String className) {
	}

	@Override
	public boolean isClassLoaded(String className) {
		if (BootstrapCommon.instrument == null) {
			return false;
		}

		for (Class<?> loaded : BootstrapCommon.instrument.getAllLoadedClasses()) {
			if (loaded.getName().equals(className)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getClassRestrictions(String className) {
		return "";
	}

	@Override
	public String getName() {
		return "FeatureCreep Minecraft Mixin Service";
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public IClassProvider getClassProvider() {
		return this;
	}

	@Override
	public IClassBytecodeProvider getBytecodeProvider() {
		return this;
	}

	@Override
	public ITransformerProvider getTransformerProvider() {
		return this;
	}

	@Override
	public IClassTracker getClassTracker() {
		return this;
	}

	@Override
	public IMixinAuditTrail getAuditTrail() {
		return null;
	}

	@Override
	public Collection<String> getPlatformAgents() {
		return Collections.singletonList("org.spongepowered.asm.launch.platform.MixinPlatformAgentDefault");
	}

	@Override
	public IContainerHandle getPrimaryContainer() {
		try {
			URL source = FeatureCreepMixinService.class.getProtectionDomain().getCodeSource().getLocation();
			return new ContainerHandleURI(source.toURI());
		} catch (Exception exception) {
			throw new IllegalStateException(exception);
		}
	}

	@Override
	public InputStream getResourceAsStream(String name) {
		InputStream stream = gameClassLoader.getResourceAsStream(name);
		if (stream != null) {
			return stream;
		}
		return FeatureCreepMixinService.class.getClassLoader().getResourceAsStream(name);
	}

	@Override
	public MixinEnvironment.CompatibilityLevel getMinCompatibilityLevel() {
		return MixinEnvironment.CompatibilityLevel.JAVA_8;
	}

	@Override
	public MixinEnvironment.CompatibilityLevel getMaxCompatibilityLevel() {
		return MixinEnvironment.CompatibilityLevel.JAVA_22;
	}

	@Override
	public IFeatureValidator getFeatureValidator() {
		return IFeatureValidator.ALLOW_ALL;
	}

	@Override
	public IAdviceProvider getAdviceProvider() {
		return IAdviceProvider.GENERIC;
	}
}
