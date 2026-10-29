package featurecreep.api.bg.mc.config;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jboss.modules.Module;

import asbestosstar.bootstrap.BootstrapCommon;
import featurecreep.loader.config.FCConfigApi;
import featurecreep.loader.config.FCConfigRegistry;

/**
 * Minecraft-side entry point for the FeatureCreep config API.
 *
 * <p>The registry itself is the loader's {@link FCConfigRegistry}. This class only
 * owns its lifecycle (idempotent install after module discovery), enumerates the
 * FeatureCreep modules the loader knows about, and keeps the list of reload hooks
 * that run when the client resource manager reloads.
 *
 * <p>Nothing here may start the loader again: {@link #install()} only reads the
 * modules that bootstrap has already resolved.
 */
public final class FCMinecraftConfig {

    /** Module property holding the stable FeatureCreep module id. */
    public static final String PROP_MOD_ID = "FeatureCreep-Mod-Id";
    /** Module property holding the human readable name. */
    public static final String PROP_NAME = "FeatureCreep-Name";
    /** Module property holding the description. */
    public static final String PROP_DESCRIPTION = "FeatureCreep-Description";
    /** Module property holding the author list. */
    public static final String PROP_AUTHORS = "FeatureCreep-Authors";
    /** Module property holding the module version. */
    public static final String PROP_MODULE_VERSION = "Module-Version";
    /** Module property holding a PNG path inside the module jar, used as the module icon. */
    public static final String PROP_ICON = "FeatureCreep-Icon";

    private static final Pattern MODULE_NAME = Pattern.compile("<module[^>]*\\sname=\"([^\"]+)\"");
    private static final Pattern MOD_ID = Pattern.compile("name=\"FeatureCreep-Mod-Id\" value=\"([^\"]+)\"");

    private static final Object LOCK = new Object();
    private static FCConfigRegistry registry;
    private static final List<Runnable> reloaders = new CopyOnWriteArrayList<Runnable>();

    private FCMinecraftConfig() {
    }

    /**
     * Creates the config registry and discovers config providers from the loaded
     * FeatureCreep modules. Safe to call more than once; only the first call does
     * work.
     */
    public static void install() {
        synchronized (LOCK) {
            if (registry != null) {
                return;
            }
            // Publish the registry before discovery so a provider that calls back
            // into install()/api() sees a consistent instance instead of recursing.
            FCConfigRegistry created = new FCConfigRegistry();
            registry = created;
            try {
                created.discoverProviders(loadedModules());
            } catch (VirtualMachineError e) {
                throw e;
            } catch (Throwable t) {
                System.err.println("FeatureCreep config discovery failed: " + t);
            }
        }
    }

    /** The active config API, or {@link FCConfigApi#none()} before install. */
    public static FCConfigApi api() {
        FCConfigRegistry current;
        synchronized (LOCK) {
            current = registry;
        }
        return current != null ? current : FCConfigApi.none();
    }

    /** Registers a hook that runs whenever the client resource manager reloads. */
    public static void registerReloader(Runnable reloader) {
        reloaders.add(Objects.requireNonNull(reloader, "reloader"));
    }

    /**
     * Runs every registered reload hook. A failing hook is reported and skipped so
     * one bad module cannot break the reload for the rest.
     */
    public static void reloadAll() {
        for (Runnable reloader : reloaders) {
            try {
                reloader.run();
            } catch (VirtualMachineError e) {
                throw e;
            } catch (Throwable t) {
                System.err.println("FeatureCreep config reloader " + reloader + " failed: " + t);
            }
        }
    }

    /** Loaded modules that declare a FeatureCreep module id, in loader order. */
    public static List<Module> featureCreepModules() {
        List<Module> result = new ArrayList<Module>();
        for (Module module : loadedModules()) {
            if (module != null && !blank(module.getProperty(PROP_MOD_ID))) {
                result.add(module);
            }
        }
        return Collections.unmodifiableList(result);
    }

    /** The FeatureCreep module id of a module, or {@code null} if it has none. */
    public static String modId(Module module) {
        String id = module == null ? null : module.getProperty(PROP_MOD_ID);
        return blank(id) ? null : id.trim();
    }

    /**
     * The jar in the game's mods folder that declares this module, matched by its
     * JBoss module name or its FeatureCreep module id. {@code null} when none matches.
     */
    public static File moduleJar(Module module) {
        File[] jars = new File(System.getProperty("user.dir"), "mods").listFiles((dir, name) -> name.endsWith(".jar"));
        if (jars == null) return null;
        for (File jar : jars) {
            try (JarFile jarFile = new JarFile(jar)) {
                JarEntry xml = jarFile.getJarEntry("module.xml");
                if (xml == null) continue;
                String text = new String(jarFile.getInputStream(xml).readAllBytes(), StandardCharsets.UTF_8);
                Matcher name = MODULE_NAME.matcher(text);
                Matcher modId = MOD_ID.matcher(text);
                boolean matches = (name.find() && name.group(1).equals(module.getName()))
                        || (modId.find() && modId.group(1).equals(module.getName()));
                if (matches) return jar;
            } catch (IOException e) {
                System.err.println("[FeatureCreep] Could not inspect " + jar + ": " + e);
            }
        }
        return null;
    }

    private static List<Module> loadedModules() {
        if (BootstrapCommon.loader == null) {
            return Collections.emptyList();
        }
        return BootstrapCommon.loader.getModules();
    }

    static boolean blank(String value) {
        return value == null || value.isBlank();
    }

    /** The trimmed value, or {@code null} when it is absent or blank. */
    static String blankToNull(String value) {
        return blank(value) ? null : value.trim();
    }
}
