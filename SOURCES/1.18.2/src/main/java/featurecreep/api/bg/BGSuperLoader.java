package featurecreep.api.bg;

import featurecreep.FeatureCreepMCInit;
import featurecreep.loader.ExecutionSide;

public class BGSuperLoader {

	public static ExecutionSide getExecutionSide() {
//Soon we must make it for clint only and server only (like plugin) enviornments but that will have seperate Class
		if(FeatureCreepMCInit.launch_side.equals(ExecutionSide.CLIENT)) {
	return ExecutionSide.CLIENT;
		}else{
			return ExecutionSide.SERVER;
		}
		
	}
	
	
	
	
}
