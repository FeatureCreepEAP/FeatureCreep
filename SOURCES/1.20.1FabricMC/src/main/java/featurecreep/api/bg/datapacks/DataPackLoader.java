package featurecreep.api.bg.datapacks;

import java.io.File;
import java.util.Set;

import featurecreep.FeatureCreep;
import featurecreep.api.bg.FCPackLoad;
import mx.kenzie.mirror.Mirror;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourcePackProvider;

public class DataPackLoader {

	public static String datapacklocation = new String(FeatureCreep.gamepath+"/tmp/datapack/");
	public static int packnumber = 15;
	
	public static void onInitialise(){
		File location = new File(datapacklocation);
		location.delete();
		location.deleteOnExit();
	//	Set<ResourcePackProvider> provs =	(Set<ResourcePackProvider>)Mirror.of(MinecraftClient.getInstance().getResourcePackManager()).field("field_14227");
	//	provs.add(new FCPackLoad(new File(datapacklocation)));
		//.register was removed in a version of MC so we must mirror it
		OreFeatureGenerator.createOreFeatures();
		CraftZoneLoader.saveCraftNodes();
		CraftZoneLoader.saveMeltNodes();

	//	Mirror.of(MinecraftClient.getInstance().getResourcePackManager()).method("registerFCPack").invoke();
	}
	
	
	
	//https://stackoverflow.com/questions/7768071/how-to-delete-directory-content-in-java
//	public static void deleteFolder(File folder) {
//	    File[] files = folder.listFiles();
//	    if(files!=null) { //some JVMs return null for empty dirs
//	        for(File f: files) {
//	            if(f.isDirectory()) {
//	                deleteFolder(f);
//	            } else {
//	                f.delete();
//	            }
//	        }
//	    }
//	    folder.delete();
//	}
	
	
	
}
