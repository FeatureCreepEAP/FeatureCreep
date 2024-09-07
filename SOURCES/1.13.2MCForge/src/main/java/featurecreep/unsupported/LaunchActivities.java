package featurecreep.unsupported;

public class LaunchActivities {

	
	
	public static String preLaunchActivities() {
		// TODO Auto-generated method stub
		if (!ModuleRemapper.completa) {
			ModuleRemapper.remapMods();
		}

//		if(FeatureCreep.debug_mode) {
//			MixerLoggerMain.doit();     Añadir lugego
//		}
		
		

		return "";
	}
	
	
	
	
	
	
	
	
}
