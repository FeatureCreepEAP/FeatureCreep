package asbestosstar.cpwinit;

import asbestosstar.bootstrap.BootstrapCommon;
import featurecreep.loader.GameProvider;
import net.minecraftforge.fml.common.Mod;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * This is likely temporary we plan to maybe move back to modlauncher services for this one
 */
@Mod("featurecreep")
public class MCFMod {

    static {
        initJarInJar();
    }

    public static boolean init_agent = BootstrapCommon.initDefault();
    public static GameProvider prov = new MCForgeGameProvider();

    private static void initJarInJar() {
        try {
            // Get the location of this class's JAR
            URL thisJarUrl = MCFMod.class.getProtectionDomain().getCodeSource().getLocation();
            if (!"file".equals(thisJarUrl.getProtocol())) {
                // Running from non-file source (e.g. nested JAR, dev env) — skip JIJ
                return;
            }

            File thisJarFile = new File(thisJarUrl.toURI());
            if (!thisJarFile.isFile()) {
                return;
            }

            // Open the current JAR as a zip-based FileSystem
            try (FileSystem fs = FileSystems.newFileSystem(thisJarFile.toPath(), null)) {
                Path jarsDir = fs.getPath("/jars");
                if (!Files.exists(jarsDir)) {
                    return;
                }

                List<URL> jarUrls = new ArrayList<>();
                try (var paths = Files.walk(jarsDir)) {
                    paths
                        .filter(path -> path.toString().endsWith(".jar"))
                        .filter(Files::isRegularFile)
                        .forEach(path -> {
                            try {
                                // Construct nested JAR URL: jar:file:/.../main.jar!/jars/x.jar
                                String relativePath = jarsDir.relativize(path).toString();
                                URL nestedUrl = new URL("jar:" + thisJarUrl + "!/" + relativePath);
                                jarUrls.add(nestedUrl);
                            } catch (Exception ignored) {
                                // Skip malformed entries
                            }
                        });
                }

                if (jarUrls.isEmpty()) {
                    return;
                }

                // Create a new classloader with embedded JARs
                URLClassLoader jijLoader = new URLClassLoader(
                    jarUrls.toArray(new URL[0]),
                    MCFMod.class.getClassLoader()
                );

                // Set as context classloader so Forge/mods can load from it
                Thread.currentThread().setContextClassLoader(jijLoader);
            }
        } catch (Exception e) {
            // Silent failure is safest in mod init — avoid crashing the game
        }
    }
}