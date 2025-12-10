package asbestosstar.cpwinit;

import asbestosstar.bootstrap.BootstrapCommon;
import featurecreep.FeatureCreep;
import featurecreep.api.GameInjections;
import featurecreep.loader.FCLoaderBasicR9;
import featurecreep.loader.FCTransformer;
import featurecreep.loader.GameProvider;

public class MCFExecute {

    // DO NOT reference GameProvider or MCForgeGameProvider in static fields!
    public static GameProvider prov;
    public static boolean init_agent;
	
    
    public static void initFeatureCreep() {
        prov = new MCForgeGameProvider();
        init_agent = BootstrapCommon.initDefault();
        FeatureCreep.loader = new FCLoaderBasicR9(prov, 8);
        FeatureCreep.loader.loadMods();
        FeatureCreep.loader.setMainTransformer(new FCTransformer(FeatureCreep.loader));
        FeatureCreep.loader.addTransformer(new GameInjections());
        FeatureCreep.loader.PremainAgents();
    }
    
}
