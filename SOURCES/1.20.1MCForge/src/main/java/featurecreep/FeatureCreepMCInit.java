package featurecreep;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import featurecreep.api.bg.registries.FCForgeRegistries;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;



	
	
	@Mod("featurecreep")
	public class FeatureCreepMCInit {
		public static final Logger LOGGER = LoggerFactory.getLogger("modid");
//	    public static final Logger LOGGER = LogManager.getLogger();

		public static Path gamepath = Paths.get(System.getProperty("user.dir"));
		protected static String modpath = gamepath.toString() + "/mods/";

                
	    public FeatureCreepMCInit() {
		  	  
	        FeatureCreep.onInitialise();
      	    	
	        // This is our mod's event bus, used for things like registry or lifecycle events
	        IEventBus MOD_BUS = FMLJavaModLoadingContext.get().getModEventBus();

	        //FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

	        
	        
	        // This listener is fired on both client and server during setup.
	        //MOD_BUS.addListener(this::commonSetup);
	        // This listener is only fired during client setup, so we can use client-side methods here.
	        //MOD_BUS.addListener(this::clientSetup);

	  //      MOD_BUS.addListener(this::loadcomplete);

	        
	        FMLJavaModLoadingContext.get().getModEventBus().addListener(FCForgeRegistries::onRegisterCreativeTabs);
	        
	        // Most other events are fired on Forge's bus.
	        // If we want to use annotations to register event listeners,
	        // we need to register our object like this!
	        MinecraftForge.EVENT_BUS.register(this);

	        // For more information on how to deal with events in Forge,
	        // like automatically subscribing an entire class to an event bus
	        // or using static methods to listen to events,
	        // feel free to check out the Forge wiki!
	        

	    }
	    
	    
	    
	    
	    
	    

//	    private void commonSetup(final FMLCommonSetupEvent event) {	    	
	    	
//	    	LOGGER.info("Hello from common setup! This is *after* registries are done, so we can do this:");
//	        LOGGER.info("Look, I found a {}!", Items.DIAMOND.getRegistryName());

	        
	        
	        

	        
	    	
	    	
	    //}

//	    private void clientSetup(final FMLClientSetupEvent event) {
//	        LOGGER.info("Hey, we're on Minecraft version {}!", MinecraftClient.getInstance().getGameVersion());
//	    }

//	    @SubscribeEvent
//	    public void kaboom(ExplosionEvent.Detonate event) {
//	        LOGGER.info("Kaboom! Something just blew up in {}!", event.getWorld());
//	    }
	    
//	    @SubscribeEvent
//	    public void loadcomplete(FMLLoadCompleteEvent event) {

//	    }
	    
	    
	}
	
	



