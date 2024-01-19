package featurecreep;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;



@EventBusSubscriber
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
   // FeatureCreepMC.initi();
    

    
    }
    
    
    public FeatureCreepMCInit()
    {
        FeatureCreep.onInitialise();

    }
    
    @EventHandler
    public static void serverInit(FMLServerStartingEvent event) {
    	event.registerServerCommand(new FeatureCreep.registerFCDNF());
    }
    
    
	//@SubscribeEvent    
	//public static void onItemRegister(RegistryEvent.Register<Item> event) Whoever thought this was the best way is evil
 	//{
	//	event.getRegistry().registerAll(FCForgeRegistries.ITEMS.toArray(new Item[0]));
	
//	}
    
    
}
	    
	    
	//}
	
	



