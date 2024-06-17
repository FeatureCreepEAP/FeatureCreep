package featurecreep;

import java.nio.file.Files;
import java.nio.file.Path;

import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.MavenCoordinateResolver;

class MCFLibPaths {

	@Deprecated
	public static Path MCForgeLib() {
		return null;
	}

	@Deprecated
	public static Path MCSRGLib() {
		// return LibraryFinder.findPathForMaven(FMLLoader.versionInfo().forgeGroup(),
		// "forge", "", "universal", FMLLoader.versionInfo().mcAndForgeVersion());
		return findPathForMaven("net.minecraft", "client", "", "srg",
				FMLLoader.versionInfo().mcAndNeoFormVersion());

	}

	/* 40 */

	
	
	//LibraryFinder was removed so we copy it
    public static Path libsPath;
    static Path findLibsPath() {
        if (libsPath == null) {
            libsPath = Path.of(System.getProperty("libraryDirectory", "libraries"));
            if (!Files.isDirectory(libsPath))
                throw new IllegalStateException("Library directory: `" + libsPath + "` does not exist. Set it via the `libraryDirectory` system property.");
        }
        return libsPath;
    }

    public static Path findPathForMaven(final String group, final String artifact, final String extension, final String classifier, final String version) {
        return findLibsPath().resolve(MavenCoordinateResolver.get(group, artifact, extension, classifier, version));
    }	
	
}
