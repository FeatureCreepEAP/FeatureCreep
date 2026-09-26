package asbestosstar.bootstrap.minecraft;

import java.io.File;
import java.util.zip.ZipFile;

/** Sponge provider. Sponge already boots Mixin and remains the owner of its transform pipeline. */
public class SpongePoweredGameProvider extends MinecraftGameProvider {
    @Override
    public boolean isSuperLoaderModZip(File zip) {
        if (zip == null || !zip.isFile() || !zip.getName().endsWith(".jar")) return false;
        try (ZipFile zf = new ZipFile(zip)) {
            return zf.getEntry("META-INF/sponge_plugins.json") != null
                    || zf.getEntry("mcmod.info") != null;
        } catch (Exception ignored) {
            return false;
        }
    }
}
