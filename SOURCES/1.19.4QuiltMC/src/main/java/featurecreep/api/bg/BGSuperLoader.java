package featurecreep.api.bg;

import org.quiltmc.loader.api.minecraft.MinecraftQuiltLoader;

import featurecreep.loader.ExecutionSide;
import net.fabricmc.api.EnvType;

public class BGSuperLoader {

	public static ExecutionSide getExecutionSide() {
//Soon we must make it for clint only and server only (like plugin) enviornments but that will have seperate Class
		if(MinecraftQuiltLoader.getEnvironmentType().equals(EnvType.CLIENT)) {
	return ExecutionSide.CLIENT;
		}else{
			return ExecutionSide.SERVER;
		}
		
	}
	
	
	
	
}
