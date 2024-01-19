package featurecreep.api.bg;

import org.dimdev.riftloader.RiftLoader;
import org.dimdev.riftloader.Side;

import featurecreep.loader.ExecutionSide;

public class BGSuperLoader {

	public static ExecutionSide getExecutionSide() {
//Soon we must make it for clint only and server only (like plugin) enviornments but that will have seperate Class
		if(RiftLoader.instance.getSide().equals(Side.CLIENT) || RiftLoader.instance.getSide().equals(Side.BOTH)) {//Common can be client or perhaps normal client is client only?
	return ExecutionSide.CLIENT;
		}else{
			return ExecutionSide.SERVER;
		}
		
	}
	
	
	
	
}
