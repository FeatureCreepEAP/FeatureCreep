package featurecreep.legacy;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.util.ModelIdentifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FeatureCreepMC.MODID)
public class FeatureCreepMC {
	
	public static final String MODID = "featurecreep";
	public static final String MODNAME = "FeatureCreep";
	
	public static FeatureCreepMC INSTANCE;
	
	public static final Logger LOGGER = LogManager.getLogger();

	public FeatureCreepMC() {
		INSTANCE = this;
		
		
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		FCBlocks.ITEMS.register(eventBus);
		FCBlocks.BLOCKS.register(eventBus);
		FCItems.ITEMS.register(eventBus);
		


		MinecraftForge.EVENT_BUS.register(this);
		
		MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, BiomeGen::onBiomeLoadingEvent);
		//MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, Fuels::onFurnaceFuelBurnTimeEvent);
		  MinecraftForge.EVENT_BUS.register(Fuels.instance);


	}


	
	
	
	public static ModelIdentifier prefix(String name) {
		return new ModelIdentifier(MODID, name.toLowerCase(Locale.ROOT));
	}
}
