package featurecreep;

import com.asbestosstar.featurecreep.FeatureCreepMC;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;




@Mod(modid = FeatureCreepMCInit.MODID, name = FeatureCreepMCInit.NAME, version = FeatureCreepMCInit.VERSION)
public class FeatureCreepMCInit
{
    public static final String MODID = "featurecreep";
    public static final String NAME = "FeatureCreepMC";
    public static final String VERSION = "4.0";

   // private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
//        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
//        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    FeatureCreep.onInitialise();


    
    
    
    
    FeatureCreepMC.initi();
    
    
    
    
    
    
    
    
    }
}
	    
	    
	//}
	
	



