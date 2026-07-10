package asbestosstar.bootstrap.minecraft;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import featurecreep.loader.ExecutionSide;

public final class MCForgeGameProvider extends MinecraftGameProvider {
	@Override
	public ExecutionSide getExecutionSide() {
		return net.minecraftforge.fml.loading.FMLEnvironment.dist == net.minecraftforge.api.distmarker.Dist.CLIENT
				? ExecutionSide.CLIENT
				: ExecutionSide.SERVER;
	}

	@Override
	public boolean isSuperLoaderModZip(File zip) {
		if (!zip.isFile() || !zip.getName().endsWith(".jar")) {
			return false;
		}

		try (ZipFile file = new ZipFile(zip)) {
			if (file.getEntry("META-INF/mods.toml") != null) {
				return true;
			}

			Enumeration<? extends ZipEntry> entries = file.entries();
			while (entries.hasMoreElements()) {
				String name = entries.nextElement().getName();
				if (name.startsWith("META-INF/services/cpw.mods.")
						|| name.startsWith("META-INF/services/net.minecraftforge.")) {
					return true;
				}
			}
		} catch (IOException ignored) {
		}

		return false;
	}
}
