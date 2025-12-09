package asbestosstar.cpwinit;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fml.common.Mod;

/**
 * This is likely temporary — we plan to maybe move back to modlauncher
 * services.
 */
@Mod("featurecreep")
public class MCFMod {

	static {
		initJarInJar();
	}

	private static void initJarInJar() {
		try {
			URL codeSource = MCFMod.class.getProtectionDomain().getCodeSource().getLocation();
			System.out.println("Original URL: " + codeSource);

			String urlStr = codeSource.toString();

			// Handle ModLauncher's union: scheme
			if (urlStr.startsWith("union:")) {
				// union:/path/to/mod.jar -> file:/path/to/mod.jar
				urlStr = "file://" + urlStr.substring("union:".length());
			}

			// Handle jar:file:/...!/... → extract file part
			if (urlStr.startsWith("jar:")) {
				urlStr = urlStr.substring(4); // remove "jar:"
			}

			// Remove everything after '!' (nested JAR path)
			int exclam = urlStr.indexOf('!');
			if (exclam != -1) {
				urlStr = urlStr.substring(0, exclam);
			}

			// Now parse as a file URL
			URI uri = URI.create(urlStr);
			File thisJarFile = new File(uri);

			System.out.println("Resolved JAR file: " + thisJarFile.getAbsolutePath());

			if (!thisJarFile.isFile()) {
				System.out.println("Resolved file is not a regular file");
				return;
			}

			// Continue with JIJ loading...
			List<URL> jarUrls = new ArrayList<>();
			try (FileSystem fs = FileSystems.newFileSystem(thisJarFile.toPath(), new java.util.HashMap<>())) {
				Path jarsDir = fs.getPath("/jars");
				if (Files.exists(jarsDir)) {
					try (var paths = Files.walk(jarsDir)) {
						paths.filter(path -> path.toString().endsWith(".jar")).filter(Files::isRegularFile)
								.forEach(path -> {
									try {
										String relativePath = jarsDir.relativize(path).toString();
										URL nestedUrl = new URL("jar:" + thisJarFile.toURI() + "!/" + relativePath);
										jarUrls.add(nestedUrl);
										System.out.println("Loaded: " + nestedUrl);
									} catch (Exception ignored) {
										ignored.printStackTrace();
									}
								});
					}
				}
			}

			if (jarUrls.isEmpty()) {
				System.out.println("No JARs found in /jars/");
				return;
			}

		      URLClassLoader jijLoader = new URLClassLoader(jarUrls.toArray(new URL[0]), MCFMod.class.getClassLoader());
	            Thread.currentThread().setContextClassLoader(jijLoader);

	            // Step 2: NOW load and initialize FeatureCreep using reflection or direct call
	            // But since we can't reference GameProvider directly, we use reflection to avoid static refs.

	            Class<?> gameProviderClass = Class.forName("featurecreep.loader.GameProvider", true, jijLoader);
	            Class<?> mcForgeProviderClass = Class.forName("asbestosstar.cpwinit.MCForgeGameProvider", true, jijLoader);
	            Object prov = mcForgeProviderClass.getConstructor().newInstance();

	            Class<?> bootstrapCommon = Class.forName("asbestosstar.bootstrap.BootstrapCommon", true, jijLoader);
	            boolean init_agent = (Boolean) bootstrapCommon.getMethod("initDefault").invoke(null);

	            Class<?> fcLoaderClass = Class.forName("featurecreep.loader.FCLoaderBasicR9", true, jijLoader);
	            Object loader = fcLoaderClass.getConstructor(gameProviderClass, int.class).newInstance(prov, 8);

	            // Set FeatureCreep.loader
	            Class<?> featureCreepClass = Class.forName("featurecreep.FeatureCreep", true, jijLoader);
	            java.lang.reflect.Field loaderField = featureCreepClass.getDeclaredField("loader");
	            loaderField.setAccessible(true);
	            loaderField.set(null, loader);

	            // Call methods on loader
	            loader.getClass().getMethod("loadMods").invoke(loader);

	            Class<?> fcTransformerClass = Class.forName("featurecreep.loader.FCTransformer", true, jijLoader);
	            Object transformer = fcTransformerClass.getConstructor(loader.getClass()).newInstance(loader);
	            loader.getClass().getMethod("setMainTransformer", Class.forName("org.jboss.modules.ClassTransformer", true, jijLoader)).invoke(loader, transformer);

	            Class<?> gameInjectionsClass = Class.forName("featurecreep.api.GameInjections", true, jijLoader);
	            Object gameInjections = gameInjectionsClass.getConstructor().newInstance();
	            loader.getClass().getMethod("addTransformer", Class.forName("org.jboss.modules.ClassTransformer", true, jijLoader)).invoke(loader, gameInjections);

	            loader.getClass().getMethod("PremainAgents").invoke(loader);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}