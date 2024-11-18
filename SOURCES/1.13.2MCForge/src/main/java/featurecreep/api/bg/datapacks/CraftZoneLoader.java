package featurecreep.api.bg.datapacks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import featurecreep.api.bg.craftingzone.CraftingZone;

public class CraftZoneLoader {

	
		public static void saveCraftNodes() {
		for (int i = 0; i < CraftingZone.objects.size(); i++) {
			String craftfile = new String("data/" + "craftingzone" + "/recipes/"
					+ Math.random() + ".json");
			PackLoader.entries.put(craftfile, BasicIO.stringToByteArray(CraftingZone.objects.get(i).get112ModelNode().toJSONString(false)));
		}

	}

	// Soon I need to make it base64 rather than random
	public static void saveMeltNodes() {
		for (int i = 0; i < CraftingZone.melts.size(); i++) {
			String craftfile = new String("data/" + "craftingzone" + "/recipes/"
					+ Math.random() + ".json");
			PackLoader.entries.put(craftfile, BasicIO.stringToByteArray(CraftingZone.melts.get(i).get113ModelNode().toJSONString(false)));
		}

	}
	
	
}
