package featurecreep.api.bg;

import featurecreep.loader.ExecutionSide;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;

public class BGSuperLoader {

	public static ExecutionSide getExecutionSide() {
//Soon we must make it for clint only and server only (like plugin) enviornments but that will have seperate Class
		if(FMLLaunchHandler.side().isClient()) {
	return ExecutionSide.CLIENT;
		}else{
			return ExecutionSide.SERVER;
		}
		
	}
	
	
	
	
}
