package featurecreep.legacy;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public class EntityAttributeListener {

	
	public static void registerAttributes()
	{
		WindigoEntity.setCustomAttributes();
		FabricDefaultAttributeRegistry.register(FeatureCreepMC.WINDIGO, WindigoEntity.setCustomAttributes());
		FabricDefaultAttributeRegistry.register(FeatureCreepMC.YELLOW_WINDIGO, YellowWindigoEntity.setCustomAttributes());
		FabricDefaultAttributeRegistry.register(FeatureCreepMC.QASEM_SOLEIMANI, QasemSoleimaniEntity.setCustomAttributes());

		
	}
	
	
	
	
	
}
