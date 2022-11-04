package featurecreep.legacy;

import net.minecraft.block.Block;


public class BasicOre extends Block {
    private boolean isFossilisedOre = false;

    public BasicOre(Block.Settings properties, boolean fossilised) {
        super(properties);
        this.isFossilisedOre = fossilised;
    }

    public BasicOre(Block.Settings properties) {
        super(properties);
    }

   

  
}
