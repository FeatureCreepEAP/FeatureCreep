package featurecreep.api.bg.datapacks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jboss.dmr.ModelNode;

import featurecreep.FeatureCreep;

public class DataPackLoader {

	public static String datapacklocation = new String(FeatureCreep.gamepath+"/resourcepacks/fcdatapack3/");//We can hardcode a datapack name as there are no datapacks past 1.12 afaik
	public static int packnumber = 3;
	
	public static void onInitialise(){
		CraftZoneLoader.saveCraftNodes();
	}

	
	
}
