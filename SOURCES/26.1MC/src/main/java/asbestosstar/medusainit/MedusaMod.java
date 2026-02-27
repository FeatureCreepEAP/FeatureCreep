package asbestosstar.medusainit;

import asbestosstar.bootstrap.BootstrapCommon;
import featurecreep.FeatureCreep;
import featurecreep.api.GameInjections;
import featurecreep.loader.FCLoaderBasicR9;
import featurecreep.loader.FCTransformer;
import featurecreep.loader.GameProvider;
import net.neoforged.fml.common.Mod;

@Mod("featurecreep")
public class MedusaMod {

	
	public static boolean init_agent=BootstrapCommon.initDefault();
	public static GameProvider prov = new MedusaGameProvider();

	
	
	
	
	
	static {
		BootstrapCommon.loader = new FCLoaderBasicR9(prov, 8);
		FeatureCreep.loader.loadMods();
		FeatureCreep.loader.setMainTransformer(new FCTransformer(FeatureCreep.loader));
		FeatureCreep.loader.addTransformer(new GameInjections());
		FeatureCreep.loader.PremainAgents();
		FeatureCreep.onInitialise();

		
	}

	public MedusaMod() {

		// This is our mod's event bus, used for things like registry or lifecycle
		// events
	//	IEventBus MOD_BUS = FMLJavaModLoadingContext.get().getModEventBus();
	  //  MOD_BUS.register(this);
//		FeatureCreep.onInitialise();


	}

//	@SubscribeEvent
//	public void register(RegisterEvent e) {
//
//		FeatureCreep.onInitialise();
//
//	}

	
	
	
}
