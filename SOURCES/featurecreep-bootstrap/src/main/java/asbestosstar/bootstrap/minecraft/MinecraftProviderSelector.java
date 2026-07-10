package asbestosstar.bootstrap.minecraft;

import asbestosstar.bootstrap.BootstrapCommon;
import featurecreep.loader.GameProvider;

public final class MinecraftProviderSelector {
	private MinecraftProviderSelector() {
	}

	public static GameProvider detect() {
		if (BootstrapCommon.classExists("net.neoforged.fml.common.Mod")) {
			return new MedusaGameProvider();
		}
		if (BootstrapCommon.classExists("net.minecraftforge.fml.common.Mod")) {
			return new MCForgeGameProvider();
		}
		if (BootstrapCommon.classExists("net.fabricmc.loader.api.FabricLoader")) {
			return new FabricMinecraftGameProvider();
		}
		return new VanillaMinecraftGameProvider();
	}
}
