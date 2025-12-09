package asbestosstar.cpwinit;

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

public class MCForgeGameProvider implements GameProvider {

	public static String[] packages_needed = GetPackagesFromClassLoader.getPackageNamesInCurrentClassLoader();
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
	        return stream
	            .filter(path -> java.nio.file.Files.isRegularFile(path))
	            .filter(path -> path.getFileName().toString().endsWith(".jar"))
	            .toArray(Path[]::new);
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
		Set<String> set = new HashSet<String>();
		for (String str : packages_needed) {
			set.add(str);
		}

		return set;
	}

	@Override
	public void addNeededPackage() {
		// TODO Auto-generated method stub
		packages_needed = GetPackagesFromClassLoader.getPackageNamesInCurrentClassLoader();
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
	    // Forge-compatible way to detect logical side (works in both dedicated server and client environments)
	    if (net.minecraftforge.fml.loading.FMLEnvironment.dist == net.minecraftforge.api.distmarker.Dist.CLIENT) {
	        return ExecutionSide.CLIENT;
	    } else {
	        return ExecutionSide.SERVER;
	    }
	}

	@Override
	public List<ModuleFinder> getDefaultModuleFinders() {
		// TODO Auto-generated method stub
		return new ArrayList<ModuleFinder>();
	}

	@Override
	public boolean isSuperLoaderModZip(File zip) {
	    if (!zip.isFile() || !zip.getName().endsWith(".jar")) {
	        return false;
	    }

	    try (java.util.zip.ZipFile zipFile = new java.util.zip.ZipFile(zip)) {
	        boolean hasModsToml = zipFile.getEntry("META-INF/mods.toml") != null;

	        // Check for service files under META-INF/services/ that start with cpw.mods or net.minecraftforge
	        java.util.Enumeration<? extends java.util.zip.ZipEntry> entries = zipFile.entries();
	        boolean hasForgeService = false;
	        while (entries.hasMoreElements()) {
	            String name = entries.nextElement().getName();
	            if (name.startsWith("META-INF/services/")) {
	                String serviceName = name.substring("META-INF/services/".length());
	                if (serviceName.startsWith("cpw.mods.") || serviceName.startsWith("net.minecraftforge.")) {
	                    hasForgeService = true;
	                    break;
	                }
	            }
	        }

	        return hasModsToml || hasForgeService;
	    } catch (java.io.IOException e) {
	        // Treat unreadable files as non-mods
	        return false;
	    }
	}

	@Override
	public boolean isSuperLoaderModFolder(File folder) {
		// TODO Auto-generated method stub
		return false;//MCForge does not have folder mods
	}

}
