package asbestosstar.bootstrap.minecraft;

import java.io.File;

import featurecreep.loader.ExecutionSide;

public class MCForgeGameProvider extends MinecraftGameProvider {


	@Override
	public ExecutionSide getExecutionSide() {
		// Forge-compatible way to detect logical side (works in both dedicated server
		// and client environments)
		if (net.minecraftforge.fml.loading.FMLEnvironment.dist == net.minecraftforge.api.distmarker.Dist.CLIENT) {
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
			boolean hasModsToml = zipFile.getEntry("META-INF/mods.toml") != null;

			// Check for service files under META-INF/services/ that start with cpw.mods or
			// net.minecraftforge
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


}
