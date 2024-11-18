package featurecreep.api.bg.datapacks;

import featurecreep.FeatureCreep;
import featurecreep.api.bg.FCPackLoad;
import game.Client;

public class DataPackLoader {

	public static String datapacklocation = new String(FeatureCreep.gamepath+"/tmp/datapack/");
	public static int packnumber = 5;
	
	public static void onInitialise(){
		CraftZoneLoader.saveCraftNodes();
		CraftZoneLoader.saveMeltNodes();

     	Client.getInstance().getLocalResourcePackRepository().addPackFinder(FCPackLoad.INSTANC E);


	}

	
	
}
