package asbestosstar.bootstrap.minecraft;

import asbestosstar.bootstrap.BootstrapCommon;
import featurecreep.loader.FCLoaderBasicR9;
import featurecreep.loader.GameProvider;
import featurecreep.loader.flat.FCLoaderFlatR1;

public class MinecraftCommonStartup {

	public static GameProvider prov = getGameProvider();
	static {
		BootstrapCommon.loader = new FCLoaderBasicR9(prov, 8);
		BootstrapCommon.flatloader = new FCLoaderFlatR1(prov);
		System.out.println("Starting FeatureCreep. Loading Mods :");

		// Load Module Mods (JBoss Modules)
		BootstrapCommon.loader.loadMods();

		// Load Flat Mods (URLClassLoader)
		// Note: Flat mods handle their own transformers internally via the ClassLoader
		// hook.
		// They do not support Agents/Premains.
		System.out.println("Loading Flat Mods :");
		BootstrapCommon.flatloader.loadMods();

		// Check if Hotswap is needed by the Module Loader (Agents are only supported
		// here)
		boolean moduleHotswap = BootstrapCommon.loader.isHotswapNeeded();

		if (moduleHotswap) {
			boolean init_agent = BootstrapCommon.initDefault();
			System.out.println("init agent is :");
			System.out.println(init_agent);
			prov.setInstrumentation(BootstrapCommon.instrument);

			// Setup Instrumentation for Module Loader
			BootstrapCommon.loader.setupInstrumentation();

			System.out.println("instrumentation is null in GameProvider: ");
			System.out.println(prov.getInstrumentation() == null);
			System.out.println("instrumentation is null in FCLoaderBasic: ");
			System.out.println(BootstrapCommon.loader.getInstrumentation() == null);
			System.out.println("Getting transformers");
			// BootstrapCommon.loader.addTransformer(new GameInjections());
			System.out.println("Getting Premains");

			// Run Premains for Module Mods
			BootstrapCommon.loader.PremainAgents();

			// Flat Mods do not support Agents or Premains.
			// Transformer classes defined in fcflat.properties are loaded automatically
			// during loadMods().
		}

	}

	/**
	 * Minecraft Vanilla Bootstrap for 1.21.11+
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		start();
	}

	public static void start() {
		// Run Module Mods
		BootstrapCommon.loader.runMods();

		// Run Flat Mods (Runs main classes defined in fcflat.properties)
		System.out.println("Running Flat Mods");
		BootstrapCommon.flatloader.runMods();
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