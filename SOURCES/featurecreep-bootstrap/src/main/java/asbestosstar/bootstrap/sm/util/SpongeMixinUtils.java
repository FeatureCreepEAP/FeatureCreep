package asbestosstar.bootstrap.sm.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

import org.jboss.modules.Module;

import asbestosstar.bootstrap.BootstrapCommon;
import featurecreep.loader.FCModuleProperties;

/** Runtime Mixin integration. Host interaction is reflection-based; the small bootstrap config plugin uses the standard Mixin API directly. */
public final class SpongeMixinUtils {
    public static final String IMPLEMENTATION_CONFIG = "featurecreepimpl.mixins.json";
    /** Compatibility alias: the bootstrap now registers the implementation config directly. */
    public static final String BOOTSTRAP_CONFIG = IMPLEMENTATION_CONFIG;
    private static final Set<String> REGISTERED = ConcurrentHashMap.newKeySet();
    private static volatile ClassLoader standaloneMixinLoader;

    private SpongeMixinUtils() {}

    public static boolean isMixinPresent() {
        return classExists("org.spongepowered.asm.mixin.Mixins");
    }

    /**
     * Standalone Mixin 0.8.x does not shade ASM. Check the set of ASM modules
     * FeatureCreep needs before treating a vanilla Mixin runtime as usable.
     */
    public static boolean isStandaloneRuntimeComplete() {
        return isMixinPresent()
                && classExists("org.objectweb.asm.ClassVisitor")
                && classExists("org.objectweb.asm.tree.ClassNode")
                && classExists("org.objectweb.asm.commons.Remapper")
                && classExists("org.objectweb.asm.tree.analysis.Analyzer")
                && classExists("org.objectweb.asm.util.CheckClassAdapter");
    }

    /** Resolve a class from the active host or standalone Mixin runtime. */
    static Class<?> mixinClass(String name) throws ClassNotFoundException {
        return loadClass(name);
    }

    /**
     * Adds a complete standalone Mixin runtime without putting it on host-owned
     * superloader class paths. Used only by vanilla/Rift/Nil fallback paths.
     *
     * <p>The bootstrap's own code source is always the first URL so the standalone
     * loader enumerates this jar's {@code META-INF/services} registrations (the real
     * {@code StandaloneMixinService} and {@code FeatureCreepGlobalPropertyService} in
     * {@code asbestosstar.bootstrap.sm.mixinhost}) before the stock LaunchWrapper and
     * ModLauncher entries inside the Mixin jar, whose classes fail to link outside
     * their hosts with a raw {@link NoClassDefFoundError} during ServiceLoader
     * iteration.
     */
    public static synchronized void installRuntimeJars(List<Path> jars) throws Exception {
        ClassLoader parent = Thread.currentThread().getContextClassLoader();
        if (parent == null) parent = SpongeMixinUtils.class.getClassLoader();
        List<URL> urls = new ArrayList<>(jars.size() + 2);
        // A generated jar holding only our Mixin service registrations. Relying on
        // the bootstrap jar for this is not safe: launchers may attribute these
        // classes to a fat bundle (whose shade step strips META-INF/services from
        // dependencies) or to no jar at all, in which case the Mixin jar's own
        // services file would be enumerated first and its LaunchWrapper-bound
        // provider would crash ServiceLoader iteration with a raw
        // NoClassDefFoundError. This jar is written fresh on every install, so the
        // registration always wins the enumeration order.
        urls.add(writeServiceRegistrationJar().toUri().toURL());
        URL self = selfCodeSource();
        if (self != null) {
            // Provides the sm.mixinhost service implementation classes (loaded
            // child-first) and anything else the runtime resolves through this jar.
            urls.add(self);
        }
        for (Path jar : jars) urls.add(jar.toUri().toURL());
        System.out.println("[FeatureCreep] Standalone Mixin runtime URLs: " + urls);
        boolean onAppClassPath = appendToAppClassPath(urls);
        standaloneMixinLoader = new StandaloneMixinClassLoader(urls.toArray(new URL[0]),
                onAppClassPath ? ClassLoader.getSystemClassLoader() : parent, !onAppClassPath);
    }

    /**
     * Places the Mixin runtime on the application class path, so MixinExtras defines its
     * generated ref classes in the loader that defines vanilla classes. Otherwise the
     * generated classes exist only in the standalone loader and TitleScreen cannot link them.
     */
    private static boolean appendToAppClassPath(List<URL> urls) {
        if (BootstrapCommon.instrument == null) BootstrapCommon.initDefault();
        if (BootstrapCommon.instrument == null) return false;
        try {
            for (URL url : urls) {
                BootstrapCommon.instrument.appendToSystemClassLoaderSearch(new JarFile(new File(url.toURI())));
            }
            return true;
        } catch (Exception e) {
            System.err.println("[FeatureCreep] Could not place Mixin runtime on the app class path: " + e);
            return false;
        }
    }

    private static Path writeServiceRegistrationJar() throws IOException {
        Path dir = Path.of(System.getProperty("user.home", "."))
                .resolve(".featurecreep").resolve("cache").resolve("jars");
        Files.createDirectories(dir);
        Path jar = dir.resolve("featurecreep-mixin-services.jar");
        try (JarOutputStream out = new JarOutputStream(Files.newOutputStream(jar))) {
            writeServiceEntry(out, "org.spongepowered.asm.service.IMixinService",
                    "asbestosstar.bootstrap.sm.mixinhost.StandaloneMixinService");
            writeServiceEntry(out, "org.spongepowered.asm.service.IGlobalPropertyService",
                    "asbestosstar.bootstrap.sm.mixinhost.FeatureCreepGlobalPropertyService");
        }
        return jar;
    }

    private static void writeServiceEntry(JarOutputStream out, String service, String implementation) throws IOException {
        out.putNextEntry(new JarEntry("META-INF/services/" + service));
        out.write((implementation + "\n").getBytes(java.nio.charset.StandardCharsets.UTF_8));
        out.closeEntry();
    }

    static URL selfCodeSource() {
        try {
            java.security.CodeSource source = SpongeMixinUtils.class.getProtectionDomain().getCodeSource();
            return source == null ? null : source.getLocation();
        } catch (Throwable t) {
            return null;
        }
    }

    /**
     * True once the standalone runtime jars (with this jar's code source first) are
     * installed. Host-owned Mixin runtimes never install this loader, which is what
     * keeps {@code StandaloneMixinService.isValid()} from claiming host environments.
     */
    public static boolean isStandaloneRuntimeActive() {
        return standaloneMixinLoader != null;
    }

    /** Returns the isolated Mixin class loader installed for vanilla launches. */
    public static ClassLoader standaloneMixinClassLoader() {
        return standaloneMixinLoader;
    }

    /** Compatibility helper for older callers. */
    public static synchronized void installRuntimeJar(Path jar) throws Exception {
        installRuntimeJars(List.of(jar));
    }

    public static boolean hasDeclaredMixinConfigs() {
        if (BootstrapCommon.loader == null) return false;
        for (Module mod : BootstrapCommon.loader.getModules()) {
            if (!FCModuleProperties.getMixinConfigs(mod).isEmpty()) return true;
        }
        return false;
    }

    /**
     * Register FeatureCreep's version-specific implementation Mixin config directly
     * with the already-active host Mixin runtime. Superloader metadata advertises
     * the same {@code featurecreepimpl.mixins.json} config.
     */
    public static boolean registerBootstrapMixinConfig() {
        if (!isMixinPresent()) return false;
        if (!REGISTERED.add("bootstrap\u0000" + BOOTSTRAP_CONFIG)) return true;
        try {
            Class<?> mixins = loadClass("org.spongepowered.asm.mixin.Mixins");
            Thread thread = Thread.currentThread();
            ClassLoader old = thread.getContextClassLoader();
            try {
                if (BootstrapCommon.loader != null) {
                    for (Module mod : BootstrapCommon.loader.getModules()) {
                        if (mod.getClassLoader().getResource(BOOTSTRAP_CONFIG) != null) {
                            thread.setContextClassLoader(mod.getClassLoader());
                            break;
                        }
                    }
                }
                mixins.getMethod("addConfiguration", String.class).invoke(null, BOOTSTRAP_CONFIG);
                System.out.println("[FeatureCreep] Registered bootstrap Mixin config " + BOOTSTRAP_CONFIG);
                return true;
            } finally {
                thread.setContextClassLoader(old);
            }
        } catch (Throwable t) {
            REGISTERED.remove("bootstrap\u0000" + BOOTSTRAP_CONFIG);
            Throwable cause = t instanceof InvocationTargetException ite && ite.getCause() != null
                    ? ite.getCause() : t;
            System.err.println("[FeatureCreep] Failed to register bootstrap Mixin config " + BOOTSTRAP_CONFIG + ": " + cause);
            cause.printStackTrace(System.err);
            return false;
        }
    }

    public static List<String> getDeclaredMixinConfigs() {
        List<String> out = new ArrayList<>();
        if (BootstrapCommon.loader == null) return out;
        for (Module mod : BootstrapCommon.loader.getModules()) {
            for (String path : FCModuleProperties.getMixinConfigs(mod)) {
                out.add(mod.getName() + ":" + path);
            }
        }
        return out;
    }

    /** Register configs against the Mixin runtime already active in the current class space. */
    public static void injectSpongeMixins() {
        if (BootstrapCommon.loader == null || !isMixinPresent()) return;
        try {
            Class<?> mixins = loadClass("org.spongepowered.asm.mixin.Mixins");
            Method add = mixins.getMethod("addConfiguration", String.class);
            for (Module mod : BootstrapCommon.loader.getModules()) {
                for (String configPath : FCModuleProperties.getMixinConfigs(mod)) {
                    if (IMPLEMENTATION_CONFIG.equals(configPath)) {
                        continue; // registered early by host metadata / registerBootstrapMixinConfig
                    }
                    String key = mod.getName() + "\u0000" + configPath;
                    if (!REGISTERED.add(key)) continue;
                    Thread thread = Thread.currentThread();
                    ClassLoader old = thread.getContextClassLoader();
                    try {
                        thread.setContextClassLoader(mod.getClassLoader());
                        add.invoke(null, configPath);
                        System.out.println("[FeatureCreep] Registered Mixin config " + configPath + " from " + mod.getName());
                    } catch (Throwable t) {
                        REGISTERED.remove(key);
                        System.err.println("[FeatureCreep] Failed to register Mixin config " + configPath + " from " + mod.getName() + ": " + t);
                    } finally {
                        thread.setContextClassLoader(old);
                    }
                }
            }
        } catch (ReflectiveOperationException e) {
            System.err.println("[FeatureCreep] Mixin API is present but incompatible: " + e);
        }
    }

    /** Emits standalone configuration/target state for diagnosing a no-op transformer. */
    public static void debugMixinState(Object transformer, String... targets) {
        if (!Boolean.getBoolean("featurecreep.debug")) return;
        try {
            Class<?> mixins = loadClass("org.spongepowered.asm.mixin.Mixins");
            Method getConfigs = mixins.getMethod("getConfigs");
            Object configs = getConfigs.invoke(null);
            Method unvisited = mixins.getMethod("getUnvisitedCount");
            System.out.println("[FeatureCreep] Mixin configs=" + configs
                    + " unvisitedBefore=" + unvisited.invoke(null));
            if (configs instanceof Iterable<?> iterable) {
                for (Object config : iterable) {
                    try {
                        Method name = config.getClass().getMethod("getName");
                        Method configApi = config.getClass().getMethod("getConfig");
                        if (!name.canAccess(config)) name.setAccessible(true);
                        if (!configApi.canAccess(config)) configApi.setAccessible(true);
                        Object configValue = configApi.invoke(config);
                        Method targetSet = configValue.getClass().getMethod("getTargets");
                        if (!targetSet.canAccess(configValue)) targetSet.setAccessible(true);
                        Method classes = configValue.getClass().getMethod("getClasses");
                        if (!classes.canAccess(configValue)) classes.setAccessible(true);
                        Method mixinCount = configValue.getClass().getDeclaredMethod("getMixinCount");
                        if (!mixinCount.canAccess(configValue)) mixinCount.setAccessible(true);
                        System.out.println("[FeatureCreep] Mixin config detail name="
                                + name.invoke(config) + " classes=" + classes.invoke(configValue)
                                + " mixins=" + mixinCount.invoke(configValue)
                                + " targets=" + targetSet.invoke(configValue));
                    } catch (Throwable detailFailure) {
                        System.err.println("[FeatureCreep] Mixin config detail failed: " + detailFailure);
                    }
                }
            }
            Class<?> envClass = loadClass("org.spongepowered.asm.mixin.MixinEnvironment");
            Object environment = envClass.getMethod("getCurrentEnvironment").invoke(null);
            Method couldTransform = transformer == null ? null
                    : transformer.getClass().getMethod("couldTransformClass", envClass, String.class);
            if (couldTransform != null && !couldTransform.canAccess(transformer)) couldTransform.setAccessible(true);
            for (String target : targets) {
                Object result = couldTransform == null ? "no-transformer"
                        : couldTransform.invoke(transformer, environment, target.replace('/', '.'));
                System.out.println("[FeatureCreep] Mixin couldTransform " + target + "=" + result);
            }
            if (configs instanceof Iterable<?> iterable) {
                for (Object config : iterable) {
                    try {
                        Method configApi = config.getClass().getMethod("getConfig");
                        if (!configApi.canAccess(config)) configApi.setAccessible(true);
                        Object configValue = configApi.invoke(config);
                        Method name = config.getClass().getMethod("getName");
                        if (!name.canAccess(config)) name.setAccessible(true);
                        Method targetSet = configValue.getClass().getMethod("getTargets");
                        if (!targetSet.canAccess(configValue)) targetSet.setAccessible(true);
                        System.out.println("[FeatureCreep] Mixin selected config name="
                                + name.invoke(config) + " targets=" + targetSet.invoke(configValue));
                    } catch (Throwable detailFailure) {
                        System.err.println("[FeatureCreep] Mixin selected config detail failed: " + detailFailure);
                    }
                }
            }
            System.out.println("[FeatureCreep] Mixin unvisited configs="
                    + mixins.getMethod("getUnvisitedCount").invoke(null));
            if (BootstrapCommon.loader != null) {
                for (Module mod : BootstrapCommon.loader.getModules()) {
                    for (String path : FCModuleProperties.getMixinConfigs(mod)) {
                        try (InputStream in = mod.getClassLoader().getResourceAsStream(path)) {
                            System.out.println("[FeatureCreep] Mixin resource " + mod.getName()
                                    + ":" + path + "=" + (in != null));
                        }
                    }
                }
            }
        } catch (Throwable failure) {
            System.err.println("[FeatureCreep] Mixin state diagnostic failed: " + failure);
            failure.printStackTrace(System.err);
        }
    }

    /** Registers MixinExtras' injection points and expression services with the active Mixin. */
    public static void initMixinExtras() {
        try {
            seedGlobalPropertyService();
            Class<?> bootstrap = loadClass("com.llamalad7.mixinextras.MixinExtrasBootstrap");
            bootstrap.getMethod("init").invoke(null);
        } catch (ClassNotFoundException absent) {
        } catch (Throwable t) {
            Throwable cause = t instanceof InvocationTargetException ite && ite.getCause() != null
                    ? ite.getCause() : t;
            System.err.println("[FeatureCreep] MixinExtras initialisation failed: " + cause);
            cause.printStackTrace(System.err);
        }
    }

    /**
     * Mixin's MixinService.initPropertyService() looks up providers through the system
     * classloader, so the standalone blackboard is never found. Seed the singleton's
     * cached property service directly with the standalone implementation.
     */
    private static void seedGlobalPropertyService() throws ReflectiveOperationException {
        Class<?> mixinService = loadClass("org.spongepowered.asm.service.MixinService");
        mixinService.getMethod("getService").invoke(null);
        java.lang.reflect.Field instance = mixinService.getDeclaredField("instance");
        instance.setAccessible(true);
        Object service = instance.get(null);
        java.lang.reflect.Field property = mixinService.getDeclaredField("propertyService");
        property.setAccessible(true);
        if (property.get(service) != null) return;
        Class<?> impl = loadClass("asbestosstar.bootstrap.sm.mixinhost.FeatureCreepGlobalPropertyService");
        property.set(service, impl.getConstructor().newInstance());
    }

    /**
     * Configs without an explicit {@code mixin.env} bind to the DEFAULT environment, but
     * a standalone init leaves the phase at PREINIT, so Mixin would select no config.
     * Advance the service to DEFAULT the same way Mixin's own bootstrap does.
     */
    public static void advanceToDefaultPhase() {
        try {
            Class<?> env = loadClass("org.spongepowered.asm.mixin.MixinEnvironment");
            Object phase = env.getMethod("getPhase").invoke(env.getMethod("getCurrentEnvironment").invoke(null));
            if (!"PREINIT".equals(String.valueOf(phase))) return;
            Class<?> phaseClass = loadClass("org.spongepowered.asm.mixin.MixinEnvironment$Phase");
            Method gotoPhase = env.getDeclaredMethod("gotoPhase", phaseClass);
            if (!gotoPhase.canAccess(null)) gotoPhase.setAccessible(true);
            gotoPhase.invoke(null, phaseClass.getField("DEFAULT").get(null));
            Object next = env.getMethod("getPhase").invoke(env.getMethod("getCurrentEnvironment").invoke(null));
            System.out.println("[FeatureCreep] Mixin phase advanced " + phase + " -> " + next);
        } catch (Throwable t) {
            Throwable cause = t instanceof InvocationTargetException ite && ite.getCause() != null
                    ? ite.getCause() : t;
            System.err.println("[FeatureCreep] Could not advance Mixin phase: " + cause);
            cause.printStackTrace(System.err);
        }
    }

    public static Object activeTransformer() {
        if (!isMixinPresent()) return null;
        try {
            Class<?> env = loadClass("org.spongepowered.asm.mixin.MixinEnvironment");
            Object current = env.getMethod("getCurrentEnvironment").invoke(null);
            Object transformer = env.getMethod("getActiveTransformer").invoke(current);
            if (transformer != null) return transformer;

            // Standalone hosts may not have asked the service to construct a transformer yet.
            Class<?> transformerClass = loadClass("org.spongepowered.asm.mixin.transformer.MixinTransformer");
            var ctor = transformerClass.getDeclaredConstructor();
            if (!ctor.canAccess(null)) ctor.setAccessible(true);
            transformer = ctor.newInstance();
            try {
                Class<?> iTransformer = loadClass("org.spongepowered.asm.mixin.transformer.IMixinTransformer");
                env.getMethod("setActiveTransformer", iTransformer).invoke(current, transformer);
            } catch (ReflectiveOperationException ignored) {
            }
            return transformer;
        } catch (Throwable t) {
            System.err.println("[FeatureCreep] Could not obtain Mixin transformer: " + t);
            return null;
        }
    }

    public static byte[] transform(Object transformer, String className, byte[] bytes) throws Exception {
        if (transformer == null || bytes == null) return bytes;
        Method m = transformer.getClass().getMethod("transformClassBytes", String.class, String.class, byte[].class);
        // Fabric's MixinTransformer implementation is package-private even though
        // this entry point is public. The standalone runtime loads it in a child
        // classloader, so ordinary reflective invocation fails access checks.
        if (!m.canAccess(transformer)) m.setAccessible(true);
        String binary = className == null ? null : className.replace('/', '.');
        return (byte[]) m.invoke(transformer, binary, binary, bytes);
    }

    private static boolean classExists(String name) {
        try { loadClass(name); return true; } catch (Throwable t) { return false; }
    }

    private static Class<?> loadClass(String name) throws ClassNotFoundException {
        ClassLoader standalone = standaloneMixinLoader;
        if (standalone != null) {
            try { return Class.forName(name, false, standalone); } catch (ClassNotFoundException ignored) {}
        }
        ClassLoader tccl = Thread.currentThread().getContextClassLoader();
        if (tccl != null) {
            try { return Class.forName(name, false, tccl); } catch (ClassNotFoundException ignored) {}
        }
        return Class.forName(name, false, SpongeMixinUtils.class.getClassLoader());
    }

    /** Standalone Mixin can resolve FC module resources without flattening module classes. */
    private static final class StandaloneMixinClassLoader extends URLClassLoader {
        private final boolean childFirst;

        StandaloneMixinClassLoader(URL[] urls, ClassLoader parent, boolean childFirst) {
            super(urls, parent);
            this.childFirst = childFirst;
        }

        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            if (!childFirst) return super.loadClass(name, resolve);
            // For the standalone path, keep Mixin and ASM as one coherent runtime.
            // Parent-first loading can otherwise pick up a partial Mixin installation
            // while ASM is missing, which is exactly the failure this loader avoids.
            // sm.mixinhost implements Mixin's service SPI and must link against these
            // standalone Mixin copies; loading it parent-first would split the service
            // type across two class spaces. MixinExtras reads MixinService's static
            // property service, so it must share the same class space as well.
            if (name.startsWith("org.spongepowered.asm.") || name.startsWith("org.objectweb.asm.")
                    || name.startsWith("com.llamalad7.")
                    || name.startsWith("asbestosstar.bootstrap.sm.mixinhost.")) {
                synchronized (getClassLoadingLock(name)) {
                    Class<?> loaded = findLoadedClass(name);
                    if (loaded == null) {
                        try { loaded = findClass(name); } catch (ClassNotFoundException ignored) {}
                    }
                    if (loaded != null) {
                        if (resolve) resolveClass(loaded);
                        return loaded;
                    }
                }
            }
            return super.loadClass(name, resolve);
        }

        @Override
        public URL getResource(String name) {
            URL own = findResource(name);
            if (own != null) return own;
            URL parent = super.getResource(name);
            if (parent != null) return parent;
            if (BootstrapCommon.loader != null) {
                for (Module mod : BootstrapCommon.loader.getModules()) {
                    URL u = mod.getClassLoader().getResource(name);
                    if (u != null) return u;
                }
            }
            return null;
        }

        @Override
        public InputStream getResourceAsStream(String name) {
            URL url = getResource(name);
            if (url != null) {
                try { return url.openStream(); } catch (Exception ignored) {}
            }
            if (BootstrapCommon.loader != null) {
                for (Module mod : BootstrapCommon.loader.getModules()) {
                    InputStream in = mod.getClassLoader().getResourceAsStream(name);
                    if (in != null) return in;
                }
            }
            return null;
        }
    }
}
