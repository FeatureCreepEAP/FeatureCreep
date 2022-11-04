package featurecreep.legacy;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;


public class BasicOre extends Block {
    private boolean isFossilisedOre = false;

  
    
    
    public BasicOre(AbstractBlock.Settings properties, boolean fossilised) {
        super(properties);
        this.isFossilisedOre = fossilised;
    }

    public BasicOre(AbstractBlock.Settings properties) {
        super(properties);
    }

   
	@Override
	  public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
	//	if (stack.getItem() instanceof PickaxeItem) {
		
		
		if (this == FeatureCreepMC.KYANITE_ORE)
		Block.dropStack((World)world, pos, FeatureCreepMC.KYANITE_ORE.asItem().getDefaultStack());
	    else if (this == FeatureCreepMC.SUN_STONE)
		Block.dropStack((World)world, pos, FeatureCreepMC.SUN_STONE.asItem().getDefaultStack());
	    else if (this == FeatureCreepMC.CELESTIAL_ORE)
		Block.dropStack((World)world, pos, FeatureCreepMC.CELESTIAL_ORE.asItem().getDefaultStack());
	    else if (this == FeatureCreepMC.EXTRA_CELESTIAL_ORE)
		Block.dropStack((World)world, pos, FeatureCreepMC.EXTRA_CELESTIAL_ORE.asItem().getDefaultStack());
	    else if (this == FeatureCreepMC.WAX_ORE)
		Block.dropStack((World)world, pos, FeatureCreepMC.WAX_ORE.asItem().getDefaultStack());
	    else if (this == FeatureCreepMC.LEAD_ORE)
		Block.dropStack((World)world, pos, FeatureCreepMC.LEAD_ORE.asItem().getDefaultStack());
	    else if (this == FeatureCreepMC.CALCIUM_ORE)
		Block.dropStack((World)world, pos, FeatureCreepMC.CALCIUM_ORE.asItem().getDefaultStack());
	    else if (this == FeatureCreepMC.TOURMALINE_ORE)
		Block.dropStack((World)world, pos, FeatureCreepMC.TOURMALINE_ORE.asItem().getDefaultStack());
	    else if (this == FeatureCreepMC.WHITE_TOURMALINE_ORE)
		Block.dropStack((World)world, pos, FeatureCreepMC.WHITE_TOURMALINE_ORE.asItem().getDefaultStack());
	    else if (this == FeatureCreepMC.PINK_TOURMALINE_ORE)
		Block.dropStack((World)world, pos, FeatureCreepMC.PINK_TOURMALINE_ORE.asItem().getDefaultStack());
	    else if (this == FeatureCreepMC.COPPER_ORE)
		Block.dropStack((World)world, pos, FeatureCreepMC.COPPER_ORE.asItem().getDefaultStack());
	    else if (this == FeatureCreepMC.SILVER_ORE)
		Block.dropStack((World)world, pos, FeatureCreepMC.SILVER_ORE.asItem().getDefaultStack());
	    else if (this == FeatureCreepMC.TIN_ORE)
		Block.dropStack((World)world, pos, FeatureCreepMC.TIN_ORE.asItem().getDefaultStack());
	    else if (this == FeatureCreepMC.PLATINUM_ORE)
		Block.dropStack((World)world, pos, FeatureCreepMC.PLATINUM_ORE.asItem().getDefaultStack());
	    else if (this == FeatureCreepMC.SAPPHIRE_ORE)
		Block.dropStack((World)world, pos, FeatureCreepMC.SAPPHIRE.getDefaultStack());
	    else if (this == FeatureCreepMC.SALT_BLOCK)
			Block.dropStack((World)world, pos, FeatureCreepMC.SAPPHIRE.getDefaultStack());
//	}
		 if (this == FeatureCreepMC.CRYSTAL_WOOD_PLANK)
		 Block.dropStack((World)world, pos, FeatureCreepMC.CRYSTAL_WOOD_PLANK.asItem().getDefaultStack());
		 else if (this == FeatureCreepMC.CRYSTAL_WOOD)
		 Block.dropStack((World)world, pos, FeatureCreepMC.CRYSTAL_WOOD.asItem().getDefaultStack());
		 else if (this == FeatureCreepMC.BLACK_SAND)
		Block.dropStack((World)world, pos, FeatureCreepMC.BLACK_SAND.asItem().getDefaultStack());
			
		
	}
	
}