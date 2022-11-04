package featurecreep.loader;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import featurecreep.FeatureCreep;
import net.fabricmc.loader.api.FabricLoader;

//For Loading Basic Mods used in Pre Release 1&2, Deprecated but still usable, for now
public class FCLoaderBasic {


	
	public static void loadMods()
	{

		//Load FC Loaders JBoss Modules

		//Path path = Paths.get("/home/rhel/Documents/FeatureCreep/esr/1.18.2-Fabric/run/mods");
		//PluginModuleFinder loader = new PluginModuleFinder(path);
		//try {
			//loader.findModule("examplemod", modloader);
		//} catch (ModuleLoadException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		//}
		//org.jboss.modules.Main("");
		
		File directoryPath = new File(FeatureCreep.modpath);
		String contents[] = directoryPath.list();
		System.out.println("List of files and directories in the specified directory:");
		//I need to make this multicore



		if (contents != null) {
		for(int i=0; i<contents.length; i++) {
		   System.out.println("FeatureCreep is trying to load "+contents[i]);
		   String[] args = new String[] { "-jar", FeatureCreep.modpath + contents[i]};
		   try {
			   //This was a last resort, but at least it works quite well, i need to make it use modules.xml
		   org.jboss.modules.Main.main(args);
		   } catch (Throwable e) {
		   	// TODO Auto-generated catch block
		   	e.printStackTrace();
		   	//I need to make this display different messages if it is detected to be a mod from another platform. It is planned
		   }

		}


		}else {
			FeatureCreep.LOGGER.info("No Mods Found in Mods Folder");
		}


	
	}
	
}
