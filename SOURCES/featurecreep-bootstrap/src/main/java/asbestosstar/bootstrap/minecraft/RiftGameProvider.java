package asbestosstar.bootstrap.minecraft;

import java.io.File;
import java.util.zip.ZipFile;

/** Rift Loader provider. Rift/LaunchWrapper supplies the class transformation chain. */
public class RiftGameProvider extends MinecraftGameProvider {
    @Override
    public boolean isSuperLoaderModZip(File zip) {
        if (zip == null || !zip.isFile() || !zip.getName().endsWith(".jar")) return false;
        try (ZipFile zf = new ZipFile(zip)) {
            return zf.getEntry("riftmod.json") != null || zf.getEntry("META-INF/riftmod.json") != null;
        } catch (Exception ignored) {
            return false;
        }
    }
}
