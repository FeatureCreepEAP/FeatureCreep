package asbestosstar.bootstrap.minecraft;

import net.neoforged.fml.common.Mod;

/** NeoForge entrypoint. NeoForge supplies its own Mixin runtime. */
@Mod("featurecreep")
public final class MedusaMod {
    public MedusaMod() {
        MinecraftCommonStartup.start();
    }
}
