package featurecreep.legacy;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class YellowWindigoRenderer extends MobEntityRenderer<YellowWindigoEntity, Yellow_Windigo> 
{

	
    public YellowWindigoRenderer(EntityRendererFactory.Context context) {
        super(context, new Yellow_Windigo(context.getPart(Yellow_Windigo.LAYER_LOCATION)), 0.5f);
    }
	

    @Override
    public Identifier getTexture(YellowWindigoEntity entity) {
        return new Identifier("featurecreep", "textures/entity/windigo/yellow_windigo.png");
    }
	
	
}
