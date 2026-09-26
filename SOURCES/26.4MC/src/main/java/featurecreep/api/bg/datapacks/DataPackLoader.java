package featurecreep.api.bg.datapacks;

public class DataPackLoader {

	public static void onInitialise() {
		CraftZoneLoader.saveCraftNodes();
		CraftZoneLoader.saveMeltNodes();
	}

}
