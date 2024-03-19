package featurecreep;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

@Deprecated
public class FabricDirs {

//static Path cachedir = FeatureCreep.gamepath.resolve(FabricLoaderImpl.CACHE_DIR_NAME);
//static Path remappedjardir = cachedir.resolve(FabricLoaderImpl.REMAPPED_JARS_DIR_NAME);
//static File MCIntermediarydirfile = new File(MCIntermediarydir);

	
	public static String getMCVersion() {
		for(ModContainer container:FabricLoader.getInstance().getAllMods()) {
		if(container.getMetadata().getVersion().equals("minecraft")) {
			return container.getMetadata().getVersion().getFriendlyString();
		}
			
			
		}
	return null;
	
	}
	
	
	
@Deprecated
public static String getMCIntermediary()
{
	/*
	
	String MC_VERSION_TEST = MinClient.getInstance().getGameVersion();
	String MC_VERSION;

	System.out.println(		MC_VERSION = MinecraftClient.getInstance().getGameVersion());
	if (!MC_VERSION_TEST.contains("Fabric"))
	{
		MC_VERSION = MinecraftClient.getInstance().getGameVersion();
	}else if (MC_VERSION_TEST.contains("FabricClient")){
		MC_VERSION = "";

	}else if (MC_VERSION_TEST.equals("Fabric")){
		MC_VERSION = "";

	}	
	else if (MC_VERSION_TEST.length() < 7){
		MC_VERSION = "";

	}	
	else
	{
		MC_VERSION = MinecraftClient.getInstance().getGameVersion().substring(7);

	}
	
	System.out.print(MC_VERSION);
			String MCIntermediarydir =  remappedjardir.toString() + "/minecraft-" + MC_VERSION + "-"+ FabricLoaderImpl.VERSION.toString();


	
	
	File folder = new File(MCIntermediarydir);
	if (folder.exists())
	{
	String 	ClientJar = new String(MCIntermediarydir + "/client-intermediary.jar");
	String 	ServerJar = new String(MCIntermediarydir + "/server-intermediary.jar");
	File ClientJarFile = new File(ClientJar);
if (ClientJarFile.exists())
{
	
return ClientJar;

}else
{
	return 	ServerJar;
}
		
	}else
	{
		String MCIntermediarydir0 =  remappedjardir.toString() + "/minecraft-" + MC_VERSION;
		String 	ClientJar = new String(MCIntermediarydir0 + "/client-intermediary.jar");
		String 	ServerJar = new String(MCIntermediarydir0 + "/server-intermediary.jar");
		File ClientJarFile = new File(ClientJar);
		if (ClientJarFile.exists())
		{
		return ClientJar;
		}else
		{
		return 	ServerJar;
		}
	
	}
	*/
	return "";
	
}





	
	
	
}
