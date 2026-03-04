package asbestosstar.bootstrap.minecraft;

import asbestosstar.bootstrap.BootstrapCommon;
import featurecreep.loader.FCLoaderBasicR9;
import featurecreep.loader.FCTransformer;
import featurecreep.loader.GameProvider;

public class MinecraftCommonStartup {

	public static boolean init_agent = BootstrapCommon.initDefault();
	public static GameProvider prov = getGameProvider();

	/**
	 * Minecraft Vanilla Bootstrap for 1.21.11+
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		start();
	}

	public static void start() {
		System.out.println("Starting FeatureCreep. init agent is :");
		System.out.println(init_agent);
		System.out.println("instrumentation is null in GameProvider: ");
		System.out.println(prov.getInstrumentation() == null);
		BootstrapCommon.loader = new FCLoaderBasicR9(prov, 8);
		System.out.println("instrumentation is null in FCLoaderBasic: ");
		System.out.println(BootstrapCommon.loader.getInstrumentation() == null);
		BootstrapCommon.loader.loadMods();
		System.out.println("Getting transformers");
		BootstrapCommon.loader.addTransformer(new GameInjections());
		System.out.println("Getting Premains");
		BootstrapCommon.loader.PremainAgents();
		
		
		
		
		
	}

	private static GameProvider getGameProvider() {

		if (classExists("net.neoforged.fml.common.Mod")) {
			return new MedusaGameProvider();
		}

		if (classExists("net.minecraftforge.fml.common.Mod")) {
			return new MCForgeGameProvider();
		}

		return new MinecraftGameProvider();
	}

	private static boolean classExists(String className) {
		try {
			Class.forName(className, false, Thread.currentThread().getContextClassLoader());
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

}
