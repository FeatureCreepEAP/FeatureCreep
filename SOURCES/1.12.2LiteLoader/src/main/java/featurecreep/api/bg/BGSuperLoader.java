package featurecreep.api.bg;

import com.mumfrey.liteloader.core.LiteLoader;
import com.mumfrey.liteloader.launch.LoaderEnvironment.EnvironmentType;

import featurecreep.loader.ExecutionSide;

public class BGSuperLoader {

	public static ExecutionSide getExecutionSide() {
//Soon we must make it for clint only and server only (like plugin) enviornments but that will have seperate Class
		if(LiteLoader.getEnvironmentType().equals(EnvironmentType.CLIENT)) {
	return ExecutionSide.CLIENT;
		}else{
			return ExecutionSide.SERVER;
		}
		
	}
	
	
	
	
}
