package featurecreep.api.bg.datapacks;

import featurecreep.api.bg.PackLoader;
import featurecreep.api.bg.craftingzone.CraftingZone;
import featurecreep.api.io.BasicIO;
import featurecreep.api.io.BasicIO;
import featurecreep.api.bg.PackLoader;
public class CraftZoneLoader {

	public static void saveCraftNodes() {
		for (int i = 0; i < CraftingZone.objects.size(); i++) {
			String craftfile = new String("data/" + "craftingzone" + "/recipes/"
					+ Math.random() + ".json");
			PackLoader.entries.put(craftfile, BasicIO.stringToByteArray(CraftingZone.objects.get(i).get119ModelNode().toJSONString(false)));
		}

	}

	// Soon I need to make it base64 rather than random
	public static void saveMeltNodes() {
		for (int i = 0; i < CraftingZone.melts.size(); i++) {
			String craftfile = new String("data/" + "craftingzone" + "/recipes/"
					+ Math.random() + ".json");
			PackLoader.entries.put(craftfile, BasicIO.stringToByteArray(CraftingZone.melts.get(i).get119ModelNode().toJSONString(false)));
		}

	}

}
