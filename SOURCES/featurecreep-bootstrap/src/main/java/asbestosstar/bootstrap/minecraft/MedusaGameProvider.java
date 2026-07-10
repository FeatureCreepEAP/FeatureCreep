package asbestosstar.bootstrap.minecraft;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import featurecreep.loader.ExecutionSide;

public final class MedusaGameProvider extends MinecraftGameProvider {
	@Override
	public ExecutionSide getExecutionSide() {
		return net.neoforged.fml.loading.FMLEnvironment.getDist() == net.neoforged.api.distmarker.Dist.CLIENT
				? ExecutionSide.CLIENT
				: ExecutionSide.SERVER;
	}

	@Override
	public boolean isSuperLoaderModZip(File zip) {
		if (!zip.isFile() || !zip.getName().endsWith(".jar")) {
			return false;
		}

		try (ZipFile file = new ZipFile(zip)) {
			if (file.getEntry("META-INF/neoforge.mods.toml") != null) {
				return true;
			}

			Enumeration<? extends ZipEntry> entries = file.entries();
			while (entries.hasMoreElements()) {
				String name = entries.nextElement().getName();
				if (name.startsWith("META-INF/services/cpw.mods.")
						|| name.startsWith("META-INF/services/net.neoforged.")) {
					return true;
				}
			}
		} catch (IOException ignored) {
		}

		return false;
	}
}
