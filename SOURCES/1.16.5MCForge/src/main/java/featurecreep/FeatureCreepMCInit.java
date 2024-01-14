package featurecreep;

import java.nio.file.Path;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;



	
	
	@Mod("featurecreep")
	public class FeatureCreepMCInit {
//		public static final Logger LOGGER = LoggerFactory.getLogger("modid");
//	    public static final Logger LOGGER = LogManager.getLogger();

		protected static Path gamepath = FMLPaths.GAMEDIR.get();
		protected static String modpath = FMLPaths.MODSDIR.get().toString() + "/";

                
	    public FeatureCreepMCInit() {
		  	  
	        FeatureCreep.onInitialise();
      	    	
	        // This is our mod's event bus, used for things like registry or lifecycle events
	        IEventBus MOD_BUS = FMLJavaModLoadingContext.get().getModEventBus();

	        //FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

			
		//	FCBlocks.ITEMS.register(MOD_BUS);
		//	FCBlocks.BLOCKS.register(MOD_BUS);
		//	FCItems.ITEMS.register(MOD_BUS);
			
	        
	        // This listener is fired on both client and server during setup.
	        //MOD_BUS.addListener(this::commonSetup);
	        // This listener is only fired during client setup, so we can use client-side methods here.
	        //MOD_BUS.addListener(this::clientSetup);

	  //      MOD_BUS.addListener(this::loadcomplete);

	        
	        
	        
	        // Most other events are fired on Forge's bus.
	        // If we want to use annotations to register event listeners,
	        // we need to register our object like this!
	        MinecraftForge.EVENT_BUS.register(this);

	        // For more information on how to deal with events in Forge,
	        // like automatically subscribing an entire class to an event bus
	        // or using static methods to listen to events,
	        // feel free to check out the Forge wiki!
	        

	    }
	    
	    

	    
	    
	}
	
	



