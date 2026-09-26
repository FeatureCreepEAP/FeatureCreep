package asbestosstar.bootstrap.sm.util;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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
     */
    public static synchronized void installRuntimeJars(List<Path> jars) throws Exception {
        ClassLoader parent = Thread.currentThread().getContextClassLoader();
        if (parent == null) parent = SpongeMixinUtils.class.getClassLoader();
        URL[] urls = new URL[jars.size()];
        for (int i = 0; i < jars.size(); i++) urls[i] = jars.get(i).toUri().toURL();
        standaloneMixinLoader = new StandaloneMixinClassLoader(urls, parent);
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
            mixins.getMethod("addConfiguration", String.class).invoke(null, BOOTSTRAP_CONFIG);
            System.out.println("[FeatureCreep] Registered bootstrap Mixin config " + BOOTSTRAP_CONFIG);
            return true;
        } catch (Throwable t) {
            REGISTERED.remove("bootstrap\u0000" + BOOTSTRAP_CONFIG);
            System.err.println("[FeatureCreep] Failed to register bootstrap Mixin config " + BOOTSTRAP_CONFIG + ": " + t);
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
        StandaloneMixinClassLoader(URL[] urls, ClassLoader parent) { super(urls, parent); }

        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            // For the standalone path, keep Mixin and ASM as one coherent runtime.
            // Parent-first loading can otherwise pick up a partial Mixin installation
            // while ASM is missing, which is exactly the failure this loader avoids.
            if (name.startsWith("org.spongepowered.asm.") || name.startsWith("org.objectweb.asm.")) {
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
