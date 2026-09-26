package asbestosstar.bootstrap.minecraft;

import org.spongepowered.plugin.builtin.jvm.Plugin;

/** Sponge entrypoint. Sponge supplies its own Mixin runtime. */
@Plugin("featurecreep")
public final class SpongeBootstrapPlugin {
    public SpongeBootstrapPlugin() {
        MinecraftCommonStartup.start();
    }
}
