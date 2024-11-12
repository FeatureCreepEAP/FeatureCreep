package featurecreep.api.bg.datapacks;

import java.io.File;

import featurecreep.FeatureCreep;

public class DataPackLoader {

	public static String datapacklocation = new String(FeatureCreep.gamepath + "/tmp/datapack/");
	public static int packnumber = 22;

	public static void onInitialise() {
		// Set<ResourcePackProvider> provs =
		// (Set<ResourcePackProvider>)Mirror.of(MinecraftClient.getInstance().getResourcePackManager()).field("field_14227");
		// provs.add(new FCPackLoad(new File(datapacklocation)));
		// .register was removed in a version of MC so we must mirror it
		OreFeatureGenerator.createOreFeatures();
		CraftZoneLoader.saveCraftNodes();
		CraftZoneLoader.saveMeltNodes();

		// Mirror.of(MinecraftClient.getInstance().getResourcePackManager()).method("registerFCPack").invoke();
	}


}
