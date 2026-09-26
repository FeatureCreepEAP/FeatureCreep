package asbestosstar.bootstrap.minecraft;

import java.io.File;
import java.util.zip.ZipFile;

/** NilLoader provider. NilLoader's ClassTransformer API is used for Mixin transformation. */
public class NilGameProvider extends MinecraftGameProvider {
    @Override
    public boolean isSuperLoaderModZip(File zip) {
        if (zip == null || !zip.isFile() || !zip.getName().endsWith(".jar")) return false;
        try (ZipFile zf = new ZipFile(zip)) {
            if (zf.getEntry("META-INF/nil/mappings.json") == null) return false;
            return zf.stream().anyMatch(e -> e.getName().endsWith(".nilmod.css")
                    && !e.getName().endsWith("nilloader.nilmod.css"));
        } catch (Exception ignored) {
            return false;
        }
    }
}
