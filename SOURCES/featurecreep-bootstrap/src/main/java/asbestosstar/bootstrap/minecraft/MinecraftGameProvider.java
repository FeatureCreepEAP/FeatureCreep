package asbestosstar.bootstrap.minecraft;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jboss.modules.ModuleFinder;

import asbestosstar.bootstrap.BootstrapCommon;
import featurecreep.loader.ExecutionSide;
import featurecreep.loader.GameProvider;
import featurecreep.loader.GetPackagesFromClassLoader;

/**
 * Base provider shared by vanilla, Fabric, Forge, and NeoForge.
 */
public class MinecraftGameProvider implements GameProvider {
	private static final Set<String> PACKAGES_NEEDED = new HashSet<>();

	static {
		PACKAGES_NEEDED.add("net/minecraft");
		PACKAGES_NEEDED.add("com/mojang");
		PACKAGES_NEEDED.add("it/unimi/dsi/fastutil");
		PACKAGES_NEEDED.add("org/spongepowered/asm");

		for (String packageName : GetPackagesFromClassLoader.getPackageNamesInCurrentClassLoader()) {
			PACKAGES_NEEDED.add(packageName);
		}
	}

	private static volatile boolean debugMode;
	private static volatile Instrumentation instrumentation = BootstrapCommon.instrument;

	@Override
	public boolean getDebugMode() {
		return debugMode;
	}

	@Override
	public boolean setDebugMode(boolean value) {
		debugMode = value;
		return value;
	}

	@Override
	public Path[] getModulePKZipLocations() {
		Path mods = Paths.get(System.getProperty("featurecreep.modsDir", "mods")).toAbsolutePath().normalize();
		return new Path[] { mods };
	}

	@Override
	public Path[] getClassPathPKZipLocations() {
		return new Path[0];
	}

	@Override
	public Instrumentation getInstrumentation() {
		return instrumentation;
	}

	@Override
	public Instrumentation setInstrumentation(Instrumentation value) {
		instrumentation = value;
		return value;
	}

	@Override
	public Set<String> getNeededPackages() {
		return PACKAGES_NEEDED;
	}

	@Override
	public void addNeededPackage(String packageName) {
		PACKAGES_NEEDED.add(packageName);
	}

	@Override
	public List<String> getAvoidedModSuffixes() {
		List<String> suffixes = new ArrayList<>();
		suffixes.add(".nil.jar");
		suffixes.add(".nil");
		suffixes.add(".deactivation");
		suffixes.add(".disabled");
		return suffixes;
	}

	@Override
	public ExecutionSide getExecutionSide() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null) {
			loader = getClass().getClassLoader();
		}

		return loader.getResource("net/minecraft/client/Minecraft.class") != null ? ExecutionSide.CLIENT
				: ExecutionSide.SERVER;
	}

	@Override
	public List<ModuleFinder> getDefaultModuleFinders() {
		return new ArrayList<>();
	}

	@Override
	public boolean isSuperLoaderModZip(File zip) {
		return false;
	}

	@Override
	public boolean isSuperLoaderModFolder(File folder) {
		return false;
	}
}
