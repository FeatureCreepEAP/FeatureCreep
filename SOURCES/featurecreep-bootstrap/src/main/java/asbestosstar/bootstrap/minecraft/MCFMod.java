package asbestosstar.bootstrap.minecraft;

import net.minecraftforge.fml.common.Mod;

/** Forge entrypoint. Forge supplies its own Mixin runtime. */
@Mod("featurecreep")
public final class MCFMod {
    public MCFMod() {
        MinecraftCommonStartup.start();
    }
}
