package featurecreep.api.bg;

import featurecreep.loader.ExecutionSide;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

public class BGSuperLoader {

	public static ExecutionSide getExecutionSide() {
//Soon we must make it for clint only and server only (like plugin) enviornments but that will have seperate Class
		if (FabricLoader.getInstance().getEnvironmentType().equals(EnvType.CLIENT)) {
			return ExecutionSide.CLIENT;
		} else {
			return ExecutionSide.SERVER;
		}

	}

}
