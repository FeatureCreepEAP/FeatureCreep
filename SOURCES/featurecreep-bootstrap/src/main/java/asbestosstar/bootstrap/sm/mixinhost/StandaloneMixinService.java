package asbestosstar.bootstrap.sm.mixinhost;

import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import org.jboss.modules.Module;

import org.spongepowered.asm.launch.platform.container.ContainerHandleURI;
import org.spongepowered.asm.launch.platform.container.ContainerHandleVirtual;
import org.spongepowered.asm.launch.platform.container.IContainerHandle;
import org.spongepowered.asm.logging.ILogger;
import org.spongepowered.asm.logging.LoggerAdapterConsole;
import org.spongepowered.asm.service.IAdviceProvider;
import org.spongepowered.asm.service.IClassBytecodeProvider;
import org.spongepowered.asm.service.IFeatureValidator;
import org.spongepowered.asm.service.IClassProvider;
import org.spongepowered.asm.service.IClassTracker;
import org.spongepowered.asm.service.IMixinAuditTrail;
import org.spongepowered.asm.service.ITransformerProvider;
import org.spongepowered.asm.service.MixinServiceAbstract;

import asbestosstar.bootstrap.BootstrapCommon;
import asbestosstar.bootstrap.sm.util.SpongeMixinUtils;

/**
 * Standalone Mixin host service for the vanilla/Rift/Nil bootstrap path.
 *
 * <p>The published Mixin 0.8.x jar only ships LaunchWrapper- and ModLauncher-bound
 * services, whose classes fail to link outside their hosts with a raw
 * {@link NoClassDefFoundError} during {@link java.util.ServiceLoader} iteration,
 * before Mixin gets a chance to skip them as invalid. This service is registered
 * ahead of both (see the bootstrap jar's
 * {@code META-INF/services/org.spongepowered.asm.service.IMixinService}), so the
 * first provider the service loader instantiates is a valid one and the stock
 * entries are never even parsed.</p>
 *
 * <p>Classes in this package are always loaded by {@code StandaloneMixinClassLoader}
 * (child-first for this package), so the SPI types above link against the standalone
 * Mixin copies rather than the application classloader's view. Never reference these
 * classes from the rest of the bootstrap; keep call sites in {@code sm.util}
 * reflection-based.</p>
 *
 * <p>Note: unlike the upstream artifact, Fabric's sponge-mixin fork shades Guava
 * (relocated to {@code org.spongepowered.include.com.google.common}), so the
 * application classpath does not need to provide it.</p>
 */
public final class StandaloneMixinService extends MixinServiceAbstract {
    private IClassProvider classProvider;
    private IClassBytecodeProvider bytecodeProvider;

    @Override
    public String getName() {
        return "FeatureCreepStandalone";
    }

    /**
     * Only claim the environment when FeatureCreep itself installed the standalone
     * runtime. Host-owned loaders (Fabric/Forge/NeoForge/Sponge) keep their own
     * Mixin service even if this jar's services file is visible to them.
     */
    @Override
    public boolean isValid() {
        return SpongeMixinUtils.isStandaloneRuntimeActive();
    }

    @Override
    public void prepare() {
        this.classProvider = new StandaloneClassProvider();
        this.bytecodeProvider = new StandaloneBytecodeProvider();
    }

    /**
     * The base service creates a no-op logger, which silently discards Mixin's
     * config preparation errors (leaving configs with zero prepared mixins). Route
     * Mixin logging to the console so those failures are visible.
     */
    @Override
    protected ILogger createLogger(String name) {
        LoggerAdapterConsole logger = new LoggerAdapterConsole(name);
        logger.setDebugStream(System.err);
        return logger;
    }

    @Override
    public IClassProvider getClassProvider() {
        return this.classProvider;
    }

    @Override
    public IClassBytecodeProvider getBytecodeProvider() {
        return this.bytecodeProvider;
    }

    // Fabric's fork (0.17.x+) adds two SPI hooks consumed only by the
    // EnumExtension subtype: an advice provider that renders the "raise your
    // compatibility level" hint, and a validator run before an enum extension
    // is applied. FeatureCreep never produces enum-extension mixins, so no
    // host-specific validation is needed.
    @Override
    public IAdviceProvider getAdviceProvider() {
        return IAdviceProvider.GENERIC;
    }

    @Override
    public IFeatureValidator getFeatureValidator() {
        return null;
    }

    @Override
    public ITransformerProvider getTransformerProvider() {
        // The FeatureCreep javaagent applies the MixinTransformer through
        // Instrumentation; there is no host transformer chain to interact with.
        return null;
    }

    @Override
    public IClassTracker getClassTracker() {
        return null;
    }

    @Override
    public IMixinAuditTrail getAuditTrail() {
        return null;
    }

    @Override
    public Collection<String> getPlatformAgents() {
        return List.of(FeatureCreepPlatformAgent.class.getName());
    }

    @Override
    public IContainerHandle getPrimaryContainer() {
        URL location = codeSource();
        if (location != null) {
            try {
                return new ContainerHandleURI(location.toURI());
            } catch (java.net.URISyntaxException ignored) {
            }
        }
        return new ContainerHandleVirtual(getName());
    }

    @Override
    public InputStream getResourceAsStream(String name) {
        ClassLoader runtime = StandaloneMixinService.class.getClassLoader();
        InputStream in = runtime.getResourceAsStream(name);
        if (in != null) return in;
        ClassLoader tccl = Thread.currentThread().getContextClassLoader();
        if (tccl != null && tccl != runtime) {
            in = tccl.getResourceAsStream(name);
            if (in != null) return in;
        }
        if (BootstrapCommon.loader != null) {
            for (Module mod : BootstrapCommon.loader.getModules()) {
                in = mod.getClassLoader().getResourceAsStream(name);
                if (in != null) return in;
            }
        }
        return null;
    }

    static URL codeSource() {
        try {
            java.security.CodeSource source =
                    StandaloneMixinService.class.getProtectionDomain().getCodeSource();
            return source == null ? null : source.getLocation();
        } catch (Throwable t) {
            return null;
        }
    }

    /**
     * Resolves classes across the standalone runtime, the application classpath and
     * FeatureCreep module classloaders, in that order. Module loaders are not part of
     * the standalone loader's delegation chain, so they are searched explicitly; this
     * is how Mixin finds FeatureCreep's own mixin classes on vanilla.
     */
    static final class StandaloneClassProvider implements IClassProvider {
        @Override
        @Deprecated
        public URL[] getClassPath() {
            return new URL[0];
        }

        @Override
        public Class<?> findClass(String name) throws ClassNotFoundException {
            return this.findClass(name, true);
        }

        @Override
        public Class<?> findClass(String name, boolean initialize) throws ClassNotFoundException {
            if (Boolean.getBoolean("featurecreep.debug") && name.startsWith("featurecreep.")) {
                System.out.println("[FeatureCreep] Mixin class request " + name);
            }
            ClassLoader runtime = StandaloneMixinService.class.getClassLoader();
            try {
                return Class.forName(name, initialize, runtime);
            } catch (ClassNotFoundException notFound) {
                ClassLoader tccl = Thread.currentThread().getContextClassLoader();
                if (tccl != null && tccl != runtime) {
                    try {
                        return Class.forName(name, initialize, tccl);
                    } catch (ClassNotFoundException ignored) {
                    }
                }
                if (BootstrapCommon.loader != null) {
                    for (Module mod : BootstrapCommon.loader.getModules()) {
                        try {
                            return Class.forName(name, initialize, mod.getClassLoader());
                        } catch (ClassNotFoundException ignored) {
                        }
                    }
                }
                throw notFound;
            }
        }

        @Override
        public Class<?> findAgentClass(String name, boolean initialize) throws ClassNotFoundException {
            return this.findClass(name, initialize);
        }
    }
}
