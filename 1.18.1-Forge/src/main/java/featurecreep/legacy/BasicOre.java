package featurecreep.legacy;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;


public class BasicOre extends Block {
    private boolean isFossilisedOre = false;

    public BasicOre(AbstractBlock.Settings properties, boolean fossilised) {
        super(properties);
        this.isFossilisedOre = fossilised;
    }

    public BasicOre(AbstractBlock.Settings properties) {
        super(properties);
    }

   

  
}
