package featurecreep.api.bg.datapacks;

import featurecreep.FeatureCreep;

public class DataPackLoader {

	public static String datapacklocation = new String(FeatureCreep.gamepath+"/tmp/datapack/");
	public static int packnumber = 4;
	
	public static void onInitialise(){
		CraftZoneLoader.saveCraftNodes();
		CraftZoneLoader.saveMeltNodes();
	}
	
	
}
