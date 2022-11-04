package featurecreep;

import java.io.File;
import java.nio.file.Path;

import featurecreep.api.FCPackLoad;
import featurecreep.api.PackLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;



	
	
	@Mod("featurecreep")
	public class FeatureCreepMCInit {
//	    public static final Logger LOGGER = LogManager.getLogger();

		protected static Path gamepath = FMLPaths.GAMEDIR.get();
		protected static String modpath = FMLPaths.MODSDIR.get().toString() + "/";

                
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

	        
	        
	        
	        // Most other events are fired on Forge's bus.
	        // If we want to use annotations to register event listeners,
	        // we need to register our object like this!
	        MinecraftForge.EVENT_BUS.register(this);

	        // For more information on how to deal with events in Forge,
	        // like automatically subscribing an entire class to an event bus
	        // or using static methods to listen to events,
	        // feel free to check out the Forge wiki!
	        MinecraftClient.getInstance().getResourcePackManager().registerProvider(new FCPackLoad(new File(PackLoader.fc_pack_location)));


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
	
	



