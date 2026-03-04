package asbestosstar.bootstrap.minecraft;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jboss.modules.ModuleFinder;

import asbestosstar.bootstrap.BootstrapCommon;
import featurecreep.loader.ExecutionSide;
import featurecreep.loader.GameProvider;
import featurecreep.loader.GetPackagesFromClassLoader;

public class MinecraftGameProvider  implements GameProvider {

	public static Set<String> packages_needed = new HashSet<String>();
	static {
		for (String str : GetPackagesFromClassLoader.getPackageNamesInCurrentClassLoader()) {
			packages_needed.add(str);
		}

	}

	public static boolean debugmode = false;
	public static Instrumentation instrumentation = BootstrapCommon.instrument;

	@Override
	public boolean getDebugMode() {
		// TODO Auto-generated method stub
		return debugmode;
	}

	@Override
	public boolean setDebugMode(boolean val) {
		// TODO Auto-generated method stub
		debugmode = val;
		return debugmode;
	}

	@Override
	public Path[] getModulePKZipLocations() {
		Path modsDir = java.nio.file.Paths.get("mods").toAbsolutePath().normalize();
		if (!java.nio.file.Files.exists(modsDir) || !java.nio.file.Files.isDirectory(modsDir)) {
			return new Path[0];
		}

		try (var stream = java.nio.file.Files.list(modsDir)) {
			return stream.filter(path -> java.nio.file.Files.isRegularFile(path))
					.filter(path -> path.getFileName().toString().endsWith(".jar")).toArray(Path[]::new);
		} catch (java.io.IOException e) {
			return new Path[0];
		}
	}

	@Override
	public Path[] getClassPathPKZipLocations() {
		return new Path[0];
	}

	@Override
	public Instrumentation getInstrumentation() {
		// TODO Auto-generated method stub
		return instrumentation;
	}

	@Override
	public Instrumentation setInstrumentation(Instrumentation instrument) {
		// TODO Auto-generated method stub
		this.instrumentation = instrument;
		return instrumentation;
	}

	@Override
	public Set<String> getNeededPackages() {
		// TODO Auto-generated method stub
		return this.packages_needed;
	}

	@Override
	public void addNeededPackage(String pack) {
		// TODO Auto-generated method stub
		packages_needed.add(pack);
	}

	@Override
	public List<String> getAvoidedModSuffixes() {
		// TODO Auto-generated method stub

		List<String> set = new ArrayList<String>();
		set.add(".nil.jar");
		set.add(".nil");
		set.add(".deactivation");
		set.add(".disabled");
		return set;

	}

	@Override
	public ExecutionSide getExecutionSide() {
	    ClassLoader cl = Thread.currentThread().getContextClassLoader();
	    if (cl == null) {
	        cl = this.getClass().getClassLoader();
	    }

	    boolean clientPresent = cl.getResource("net/minecraft/client/Minecraft.class") != null;

	    return clientPresent ? ExecutionSide.CLIENT : ExecutionSide.SERVER;
	}

	@Override
	public List<ModuleFinder> getDefaultModuleFinders() {
		// TODO Auto-generated method stub
		return new ArrayList<ModuleFinder>();
	}

	@Override
	public boolean isSuperLoaderModZip(File zip) {
		return false;//Override in providers for Super Loader
	}

	@Override
	public boolean isSuperLoaderModFolder(File folder) {
		// TODO Auto-generated method stub
		return false;//Override in providers for Super Loader
	}

	
	
	
	
}
