package featurecreep.legacy;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class FCSugarPlant extends SugarCaneBlock {

	public FCSugarPlant(Settings settings) {
		super(settings);
		// TODO Auto-generated constructor stub
	}
	@Override
	  public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
	    
		if (this == FeatureCreepMC.CORN_PLANT) {
		Block.dropStack((World)world, pos, FeatureCreepMC.CORN.getDefaultStack());
		}else if (this == FeatureCreepMC.QUINOA_PLANT) {
			Block.dropStack((World)world, pos, FeatureCreepMC.QUINOA.getDefaultStack());
		}else {
			
			
		}
			
			
		
		
	}
}
