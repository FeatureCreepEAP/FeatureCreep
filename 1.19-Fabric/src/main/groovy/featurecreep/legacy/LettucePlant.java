package featurecreep.legacy;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PotatoesBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class LettucePlant extends PotatoesBlock {

	public LettucePlant(Settings settings) {
		super(settings);
		// TODO Auto-generated constructor stub
	}
	@Override
	   protected ItemConvertible getSeedsItem() {
	        return FeatureCreepMC.LETTUCE;
	    }
	@Override
	  public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
	    Block.dropStack((World)world, pos, FeatureCreepMC.LETTUCE.getDefaultStack());
	    
	}


}
