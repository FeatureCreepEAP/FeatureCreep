package featurecreep.legacy;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class MechKillerRobot1000Renderer extends MobEntityRenderer<MechKillerRobot1000Entity, mech_killer_robot_1000> 
{

	
    public MechKillerRobot1000Renderer(EntityRendererFactory.Context context) {
        super(context, new mech_killer_robot_1000(context.getPart(mech_killer_robot_1000.LAYER_LOCATION)), 0.5f);
    }
	

    @Override
    public Identifier getTexture(MechKillerRobot1000Entity entity) {
        return new Identifier("featurecreep", "textures/entity/blank.png");
    }
	
	
}
