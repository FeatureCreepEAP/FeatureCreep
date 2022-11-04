package featurecreep;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jboss.modules.ModuleLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import featurecreep.api.FCCommandArgs;
import featurecreep.loader.PluginModuleFinder;
import net.minecraft.client.MinecraftClient;


public class FeatureCreepMCInit {

	
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");
public ModuleLoader modloader;

public static String gamepath;	
public static String modpath;	
	


	public static void main(String[] args) {

		
		
		FCCommandArgs arguments = new FCCommandArgs();
		arguments.parse(args);
		File instance = new File(arguments.getOrDefault("gameDir", "."));

		
		LOGGER.info("StartingMCClass");
		net.minecraft.client.main.Main.main(args);
		//		String[] argsstartmc = new String[] { "-class", "net.minecraft.client.MinecraftClient" + args};

//		try {
//			org.jboss.modules.Main.main(argsstartmc);
//		} catch (Throwable e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		
		
		gamepath = instance.toString();	
		modpath = instance.toString() + ("/mods/");	
			
		LOGGER.info("Loading FeatureCreep Initialisation Class");



    	FeatureCreep.onInitialise();





//Load FC Loaders JBoss Modules

Path path = Paths.get("/home/rhel/Documents/FeatureCreep/esr/1.18.2-Fabric/run/mods");
PluginModuleFinder loader = new PluginModuleFinder(path);
//try {
//loader.findModule("examplemod", modloader);
//} catch (ModuleLoadException e) {
// TODO Auto-generated catch block
//e.printStackTrace();
//}
//org.jboss.modules.Main("");


File directoryPath = new File(modpath);
String contents[] = directoryPath.list();
System.out.println("List of files and directories in the specified directory:");
//I need to make this multicore



if (contents != null) {
for(int i=0; i<contents.length; i++) {
System.out.println("FeatureCreep is trying to load "+contents[i]);
String[] argsloader = new String[] { "-jar", modpath + contents[i]};
try {
   //This was a last resort, but at least it works quite well, i need to make it use modules.xml
	org.jboss.modules.Main.main(argsloader);
} catch (Throwable e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	//I need to make this display different messages if it is detected to be a mod from another platform. It is planned
}

}


}else {
LOGGER.info("No Mods Found in Mods Folder");
}








		
	}



}
