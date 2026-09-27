package asbestosstar.bootstrap.sm.util;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.security.ProtectionDomain;
import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Collections;
import java.io.IOException;

import asbestosstar.bootstrap.BootstrapCommon;
import asbestosstar.bootstrap.minecraft.MinecraftHostKind;

/**
 * Selects the safest available Mixin integration for the active superloader.
 * Host-owned Mixin installations are always reused and never downloaded/replaced.
 */
public final class MixinRuntimeCoordinator {
    private MixinRuntimeCoordinator() {}

    /**
     * Whether the Mixin path itself requires Instrumentation. Only standalone
     * vanilla does; Fabric/Forge/NeoForge/Sponge own Mixin, while Rift/Nil expose
     * transformation chains of their own.
     */
    public static boolean mixinsRequireInstrumentation(MinecraftHostKind host) {
        return host == MinecraftHostKind.VANILLA && SpongeMixinUtils.hasDeclaredMixinConfigs();
    }

    public static void initialize(MinecraftHostKind host) {
        if (!SpongeMixinUtils.hasDeclaredMixinConfigs()) return;

        switch (host) {
            case FABRIC, FORGE, NEOFORGE, SPONGE -> useHostOwnedMixin(host);
            case RIFT -> bootstrapRift();
            case NIL -> bootstrapNil();
            case VANILLA -> bootstrapVanilla();
        }
    }

    private static void useHostOwnedMixin(MinecraftHostKind host) {
        // Deliberately never resolve, download, or construct a second Mixin runtime.
        if (!SpongeMixinUtils.isMixinPresent()) {
            System.err.println("[FeatureCreep] " + host + " was detected but its Mixin runtime is not visible; FeatureCreep will not download a replacement.");
            return;
        }
        // Normally the host metadata has already loaded featurecreepimpl.mixins.json.
        // Register it explicitly as a harmless fallback for hosts/forks that do not
        // consume the metadata automatically.
        SpongeMixinUtils.registerBootstrapMixinConfig();
        SpongeMixinUtils.injectSpongeMixins();
    }

    private static void bootstrapRift() {
        // Rift exposes a LaunchWrapper transformer chain. Prefer the Mixin copy Rift
        // already carries; if the particular Rift fork does not carry one, resolve
        // standalone Mixin without touching JVM Instrumentation.
        if (!SpongeMixinUtils.isMixinPresent() && !StandaloneMixinResolver.ensureAvailable()) {
            System.err.println("[FeatureCreep] Rift was detected but no Mixin runtime could be made available.");
            return;
        }
        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            Class<?> bootstrap = SpongeMixinUtils.mixinClass("org.spongepowered.asm.launch.MixinBootstrap");
            bootstrap.getMethod("init").invoke(null);

            Class<?> launch = Class.forName("net.minecraft.launchwrapper.Launch", false, cl);
            Field classLoaderField = launch.getField("classLoader");
            Object launchClassLoader = classLoaderField.get(null);
            Class<?> tweakerClass = SpongeMixinUtils.mixinClass("org.spongepowered.asm.launch.MixinTweaker");
            Object tweaker = tweakerClass.getConstructor().newInstance();
            for (Method m : tweakerClass.getMethods()) {
                if (m.getName().equals("injectIntoClassLoader") && m.getParameterCount() == 1
                        && m.getParameterTypes()[0].isInstance(launchClassLoader)) {
                    m.invoke(tweaker, launchClassLoader);
                    break;
                }
            }
            SpongeMixinUtils.registerBootstrapMixinConfig();
            SpongeMixinUtils.injectSpongeMixins();
        } catch (Throwable t) {
            System.err.println("[FeatureCreep] Rift Mixin bootstrap failed: " + t);
        }
    }

    private static void bootstrapNil() {
        if (!standaloneMixinBootstrap()) return;
        Object transformer = SpongeMixinUtils.activeTransformer();
        if (transformer == null) return;

        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            Class<?> ct = Class.forName("nilloader.api.ClassTransformer", false, cl);
            Object proxy = Proxy.newProxyInstance(cl, new Class<?>[] { ct }, (p, method, args) -> {
                if (!method.getName().equals("transform") || args == null) return defaultValue(method.getReturnType());
                if (args.length == 2 && args[0] instanceof String name && args[1] instanceof byte[] bytes) {
                    return SpongeMixinUtils.transform(transformer, name, bytes);
                }
                if (args.length == 3 && args[1] instanceof String name && args[2] instanceof byte[] bytes) {
                    return SpongeMixinUtils.transform(transformer, name, bytes);
                }
                return args.length > 0 ? args[args.length - 1] : null;
            });
            ct.getMethod("register", ct).invoke(null, proxy);
            SpongeMixinUtils.injectSpongeMixins();
            System.out.println("[FeatureCreep] Installed Mixin bridge into NilLoader ClassTransformer pipeline.");
        } catch (Throwable t) {
            System.err.println("[FeatureCreep] NilLoader Mixin bridge failed: " + t);
        }
    }

    private static void bootstrapVanilla() {
        Instrumentation instrumentation = BootstrapCommon.instrument;
        if (instrumentation == null) {
            System.err.println("[FeatureCreep] Vanilla Mixin configs require startup Instrumentation. The generated launcher/server configuration should supply -javaagent; standalone Mixin bootstrap was not started.");
            return;
        }

        if (!standaloneMixinBootstrap()) return;
        SpongeMixinUtils.injectSpongeMixins();
        Object transformer = SpongeMixinUtils.activeTransformer();
        if (transformer == null) return;

        instrumentation.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(Module module, ClassLoader loader, String className, Class<?> classBeingRedefined,
                    ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                if (className == null || classfileBuffer == null || excluded(className)) return null;
                try {
                    byte[] out = SpongeMixinUtils.transform(transformer, className, classfileBuffer);
                    return out == classfileBuffer ? null : out;
                } catch (Throwable t) {
                    throw new IllegalClassFormatException("Mixin transform failed for " + className + ": " + t);
                }
            }
        }, false);
        System.out.println("[FeatureCreep] Installed standalone Vanilla Mixin transformer through declared transform need.");
    }

    private static boolean standaloneMixinBootstrap() {
        if (!StandaloneMixinResolver.ensureAvailable()) {
            System.err.println("[FeatureCreep] Standalone Mixin runtime could not be made available.");
            return false;
        }
        try {
            Class<?> bootstrap = SpongeMixinUtils.mixinClass("org.spongepowered.asm.launch.MixinBootstrap");
            bootstrap.getMethod("init").invoke(null);
            SpongeMixinUtils.registerBootstrapMixinConfig();
            return true;
        } catch (Throwable t) {
            System.err.println("[FeatureCreep] Standalone Mixin bootstrap failed: " + t);
            return false;
        }
    }

    private static boolean excluded(String name) {
        return name.startsWith("java/") || name.startsWith("jdk/") || name.startsWith("sun/")
                || name.startsWith("org/spongepowered/asm/") || name.startsWith("asbestosstar/bootstrap/")
                || name.startsWith("featurecreep/loader/");
    }

    private static Object defaultValue(Class<?> type) {
        if (!type.isPrimitive()) return null;
        if (type == boolean.class) return false;
        if (type == byte.class) return (byte) 0;
        if (type == short.class) return (short) 0;
        if (type == int.class) return 0;
        if (type == long.class) return 0L;
        if (type == float.class) return 0f;
        if (type == double.class) return 0d;
        if (type == char.class) return '\0';
        return null;
    }
}
