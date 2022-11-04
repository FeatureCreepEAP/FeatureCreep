package featurecreep.legacy;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.Dilation;

@Environment(EnvType.CLIENT)
public class EntityTestingClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        /*
         * Registers our Cube Entity's renderer, which provides a model and texture for the entity.
         *
         * Entity Renderers can also manipulate the model before it renders based on entity context (EndermanEntityRenderer#render).
         */
     
        // In 1.17, use EntityRendererRegistry.register (seen below) instead of EntityRendererRegistry.INSTANCE.register (seen above)
        EntityRendererRegistry.register(FeatureCreepMC.QASEM_SOLEIMANI, (context) -> {
            return new QasemSoleimaniRenderer(context);
        });
 
        
        EntityRendererRegistry.register(FeatureCreepMC.WINDIGO, (context) -> {
            return new WindigoRenderer(context);
        });
        
        
        EntityRendererRegistry.register(FeatureCreepMC.YELLOW_WINDIGO, (context) -> {
            return new YellowWindigoRenderer(context);
        });
        
        
        EntityRendererRegistry.register(FeatureCreepMC.MECH_KILLER_ROBOT_1000, (context) -> {
            return new MechKillerRobot1000Renderer(context);
        });
        
        
        
        //Thanks to lovely snails mod for reference
 
        EntityModelLayerRegistry.registerModelLayer(QasemSoleimaniEntityModel.LAYER_LOCATION, () -> QasemSoleimaniEntityModel.createBodyLayer(Dilation.NONE));
   
    
        EntityModelLayerRegistry.registerModelLayer(Windigo.LAYER_LOCATION, () -> Windigo.createBodyLayer());
        EntityModelLayerRegistry.registerModelLayer(Yellow_Windigo.LAYER_LOCATION, () -> Yellow_Windigo.createBodyLayer());

        EntityModelLayerRegistry.registerModelLayer(mech_killer_robot_1000.LAYER_LOCATION, () -> mech_killer_robot_1000.createBodyLayer());

    }
   
    
    
    
}