package asbestosstar.bootstrap.minecraft;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

/** Fabric pre-launch entrypoint. Fabric supplies its own Mixin runtime. */
public final class FabricBootstrapEntrypoint implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        MinecraftCommonStartup.start();
    }
}
