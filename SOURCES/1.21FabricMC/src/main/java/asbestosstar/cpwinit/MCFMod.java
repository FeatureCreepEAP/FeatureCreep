package asbestosstar.cpwinit;

import asbestosstar.bootstrap.BootstrapCommon;
import featurecreep.FeatureCreep;
import featurecreep.api.GameInjections;
import featurecreep.loader.FCLoaderBasicR9;
import featurecreep.loader.FCTransformer;
import featurecreep.loader.GameProvider;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;

/**
 * This is likely temporary we plan to maybe move back to modlauncher services
 * for this one
 */
@Mod("featurecreep")
public class MCFMod {

//	static {
//		initJarInJar();
//	}

	public static boolean init_agent = BootstrapCommon.initDefault();
	public static GameProvider prov = new MCForgeGameProvider();

	static {
		BootstrapCommon.loader = new FCLoaderBasicR9(prov, 8);
		FeatureCreep.loader.loadMods();
		FeatureCreep.loader.setMainTransformer(new FCTransformer(FeatureCreep.loader));
		FeatureCreep.loader.addTransformer(new GameInjections());
		FeatureCreep.loader.PremainAgents();

	}

	public MCFMod() {

		// This is our mod's event bus, used for things like registry or lifecycle
		// events
		IEventBus MOD_BUS = FMLJavaModLoadingContext.get().getModEventBus();
	    MOD_BUS.register(this);

	}

	@SubscribeEvent
	public void register(RegisterEvent e) {

		FeatureCreep.onInitialise();

	}

//	private static void initJarInJar() {
//		try {
//			URL thisJarUrl = MCFMod.class.getProtectionDomain().getCodeSource().getLocation();
//			if (!"file".equals(thisJarUrl.getProtocol())) {
//				return;
//			}
//
//			File thisJarFile = new File(thisJarUrl.toURI());
//			if (!thisJarFile.isFile()) {
//				return;
//			}
//
//			// Java 8 compatible: use Map<String, ?> env (can be empty for default ZIP
//			// filesystem)
//			try (FileSystem fs = FileSystems.newFileSystem(thisJarFile.toPath(),
//					new java.util.HashMap<String, String>())) {
//				Path jarsDir = fs.getPath("/jars");
//				if (!Files.exists(jarsDir)) {
//					return;
//				}
//
//				List<URL> jarUrls = new ArrayList<>();
//				try (var paths = Files.walk(jarsDir)) {
//					paths.filter(path -> path.toString().endsWith(".jar")).filter(Files::isRegularFile)
//							.forEach(path -> {
//								try {
//									String relativePath = jarsDir.relativize(path).toString();
//									URL nestedUrl = new URL("jar:" + thisJarUrl + "!/" + relativePath);
//									jarUrls.add(nestedUrl);
//								} catch (Exception ignored) {
//								}
//							});
//				}
//
//				if (!jarUrls.isEmpty()) {
//					URLClassLoader jijLoader = new URLClassLoader(jarUrls.toArray(new URL[0]),
//							MCFMod.class.getClassLoader());
//					Thread.currentThread().setContextClassLoader(jijLoader);
//				}
//			}
//		} catch (Exception e) {
//			// Silent fail
//		}
//	}
}