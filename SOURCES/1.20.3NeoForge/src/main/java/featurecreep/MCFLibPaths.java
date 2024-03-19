package featurecreep;

import java.nio.file.Path;

import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.LibraryFinder;

class MCFLibPaths {

	@Deprecated
	public static Path MCForgeLib() {
		return LibraryFinder.findPathForMaven("forge");
	}

	@Deprecated
	public static Path MCSRGLib() {
		// return LibraryFinder.findPathForMaven(FMLLoader.versionInfo().forgeGroup(),
		// "forge", "", "universal", FMLLoader.versionInfo().mcAndForgeVersion());
		return LibraryFinder.findPathForMaven("net.minecraft", "client", "", "srg",
				FMLLoader.versionInfo().mcAndFmlVersion());

	}

	/* 40 */

}
