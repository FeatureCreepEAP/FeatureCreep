package featurecreep.api.bg.datapacks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import featurecreep.api.bg.craftingzone.CraftingZone;

public class CraftZoneLoader {

	
	public static void saveCraftNodes()
	{
		for(int i=0; i<CraftingZone.objects.size(); i++) {
			File craftfile = new File(DataPackLoader.datapacklocation + "/data/" + "craftingzone" + "/recipes/" + Math.random() + ".json");
			craftfile.getParentFile().mkdirs();
			try {
				FileWriter configwriter = new FileWriter(craftfile);
				configwriter.write(CraftingZone.objects.get(i).get114ModelNode().toJSONString(false));
				configwriter.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			craftfile.deleteOnExit();
			
		}

		
	}
		//Soon I need to make it base64 rather than random
	public static void saveMeltNodes()
	{
		for(int i=0; i<CraftingZone.melts.size(); i++) {
			File craftfile = new File(DataPackLoader.datapacklocation + "/data/" + "craftingzone" + "/recipes/" + Math.random() + ".json");
			craftfile.getParentFile().mkdirs();
			try {
				FileWriter configwriter = new FileWriter(craftfile);
				configwriter.write(CraftingZone.melts.get(i).get114ModelNode().toJSONString(false));
				configwriter.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			craftfile.deleteOnExit();
			
		}

		
	}
	
	
	
	
}
