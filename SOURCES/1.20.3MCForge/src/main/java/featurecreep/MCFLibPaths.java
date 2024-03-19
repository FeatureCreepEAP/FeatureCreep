package featurecreep;

import java.nio.file.Path;

import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.LibraryFinder;

class MCFLibPaths {

	@Deprecated
	public static Path MCForgeLib() {
		return LibraryFinder.findPathForMaven(FMLLoader.versionInfo().forgeGroup(), "forge", "", "universal",
				FMLLoader.versionInfo().mcAndForgeVersion());
	}

	@Deprecated
	public static Path MCSRGLib() {
		// return LibraryFinder.findPathForMaven(FMLLoader.versionInfo().forgeGroup(),
		// "forge", "", "universal", FMLLoader.versionInfo().mcAndForgeVersion());
		return LibraryFinder.findPathForMaven("net.minecraft", "client", "", "srg",
				FMLLoader.versionInfo().mcAndMCPVersion());

	}

	/* 40 */

}
