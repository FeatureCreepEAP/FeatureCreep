package asbestosstar.bootstrap.minecraft;

import java.io.File;
import java.util.zip.ZipFile;

/** Fabric/Knot provider. Fabric owns Mixin; FeatureCreep only contributes configs. */
public class FabricGameProvider extends MinecraftGameProvider {
    @Override
    public boolean isSuperLoaderModZip(File zip) {
        if (zip == null || !zip.isFile() || !zip.getName().endsWith(".jar")) return false;
        try (ZipFile zf = new ZipFile(zip)) {
            return zf.getEntry("fabric.mod.json") != null;
        } catch (Exception ignored) {
            return false;
        }
    }
}
