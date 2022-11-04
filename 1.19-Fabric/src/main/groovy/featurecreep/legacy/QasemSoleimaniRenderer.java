package featurecreep.legacy;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class QasemSoleimaniRenderer extends MobEntityRenderer<QasemSoleimaniEntity, QasemSoleimaniEntityModel> 
{

	
    public QasemSoleimaniRenderer(EntityRendererFactory.Context context) {
        super(context, new QasemSoleimaniEntityModel(context.getPart(QasemSoleimaniEntityModel.LAYER_LOCATION)), 0.5f);
    }
	

    @Override
    public Identifier getTexture(QasemSoleimaniEntity entity) {
        return new Identifier("featurecreep", "textures/entity/qasem_soleimani/qasem_soleimani.png");
    }
	
	
}
