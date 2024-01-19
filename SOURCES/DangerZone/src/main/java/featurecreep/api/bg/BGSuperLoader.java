package featurecreep.api.bg;

import dangerzone.DangerZone;
import featurecreep.loader.ExecutionSide;

public class BGSuperLoader {

	public static ExecutionSide getExecutionSide() {
//Soon we must make it for clint only and server only (like plugin) enviornments but that will have seperate Class
		if(DangerZone.start_client) {
	return ExecutionSide.CLIENT;
		}else{
			return ExecutionSide.SERVER;
		}
		
	}
	
	
	
	
}
