package featurecreep.unsupported;

import com.asbestosstar.mixerlogger.MixerLoggerMain;

import featurecreep.api.GameInjections;

public class LaunchActivities {

	
	
	public static String preLaunchActivities() {
		// TODO Auto-generated method stub
		if (!ModuleRemapper.completa) {
			ModuleRemapper.remapMods();
		}

		if(GameInjections.debug_mode) {
			MixerLoggerMain.doit();
		}
		
		

		return "";
	}
	
	
	
	
	
	
	
	
}

