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
import featurecreep.loader.GetPackagesFromClassLoader;

public class MedusaGameProvider extends MinecraftGameProvider {

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
		// Fancy Mod Loader (NeoForge-based) compatible side detection
		if (net.neoforged.fml.loading.FMLEnvironment.getDist() == net.neoforged.api.distmarker.Dist.CLIENT) {
			return ExecutionSide.CLIENT;
		} else {
			return ExecutionSide.SERVER;
		}
	}

	@Override
	public boolean isSuperLoaderModZip(File zip) {
		if (!zip.isFile() || !zip.getName().endsWith(".jar")) {
			return false;
		}

		try (java.util.zip.ZipFile zipFile = new java.util.zip.ZipFile(zip)) {
			boolean hasModsToml = zipFile.getEntry("META-INF/neoforge.mods.toml") != null;

			// Check for service files under META-INF/services/ that start with cpw.mods or
			// net.minecraftforge
			java.util.Enumeration<? extends java.util.zip.ZipEntry> entries = zipFile.entries();
			boolean hasForgeService = false;
			while (entries.hasMoreElements()) {
				String name = entries.nextElement().getName();
				if (name.startsWith("META-INF/services/")) {
					String serviceName = name.substring("META-INF/services/".length());
					if (serviceName.startsWith("cpw.mods.") || serviceName.startsWith("net.neoforged.")) {
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

}
