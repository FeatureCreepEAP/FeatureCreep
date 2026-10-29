package featurecreep.api.bg.mc.config;

import java.io.IOException;
import java.io.InputStream;

import com.mojang.blaze3d.platform.NativeImage;

import featurecreep.loader.config.FCConfigApi;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.Identifier;

/** The FeatureCreep logo drawn on the title screen's FC button, read from the loader jar root. */
public final class FCTitleIcon {

    private static final String PATH = "featurecreep.png";
    private static final Identifier ID = Identifier.fromNamespaceAndPath("featurecreep", "title/logo");

    private static boolean attempted;
    private static Icon icon;

    private FCTitleIcon() {
    }

    /** Registers the logo texture on first call. {@code null} when it cannot be loaded. */
    public static Icon get() {
        if (!attempted) {
            attempted = true;
            icon = load();
        }
        return icon;
    }

    private static Icon load() {
        try (InputStream in = FCConfigApi.class.getClassLoader().getResourceAsStream(PATH)) {
            if (in == null) {
                System.err.println("[FeatureCreep] " + PATH + " not found on the loader classpath");
                return null;
            }
            NativeImage image = NativeImage.read(in);
            Minecraft.getInstance().getTextureManager().register(ID, new DynamicTexture(ID::toString, image));
            return new Icon(ID);
        } catch (IOException | RuntimeException e) {
            System.err.println("[FeatureCreep] Could not load " + PATH + ": " + e);
            return null;
        }
    }

    public record Icon(Identifier id) {
    }
}
