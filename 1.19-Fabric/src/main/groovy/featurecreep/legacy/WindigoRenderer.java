package featurecreep.legacy;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class WindigoRenderer extends MobEntityRenderer<WindigoEntity, Windigo> 
{

	
    public WindigoRenderer(EntityRendererFactory.Context context) {
        super(context, new Windigo(context.getPart(Windigo.LAYER_LOCATION)), 0.5f);
    }
	

    @Override
    public Identifier getTexture(WindigoEntity entity) {
        return new Identifier("featurecreep", "textures/entity/windigo/windigo.png");
    }
	
	
}
