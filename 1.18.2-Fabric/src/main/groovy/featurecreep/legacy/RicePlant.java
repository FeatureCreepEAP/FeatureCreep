package featurecreep.legacy;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.KelpBlock;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class RicePlant extends KelpBlock {

	public RicePlant(Settings settings) {
		super(settings);
		// TODO Auto-generated constructor stub
	}

	
	 @Override
	    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
	        if (world.isWater(pos.up())) {
	            int i = 1;
	            while (world.getBlockState(pos.down(i)).isOf(this)) {
	                ++i;
	            }
	            
	            if (i < 3) {
	                int j = state.get(AGE);
	                if (j == 15) {
	                    world.setBlockState(pos.up(), this.getDefaultState());
	                    world.setBlockState(pos, (BlockState)state.with(AGE, 0), Block.NO_REDRAW);
	                    world.setBlockState(pos.north(), this.getDefaultState());
	                    world.setBlockState(pos.south(), this.getDefaultState());
	                    world.setBlockState(pos.west(), this.getDefaultState());
	                    world.setBlockState(pos.east(), this.getDefaultState());
	                    world.setBlockState(pos.down(), this.getDefaultState());
	                } else {
	                    world.setBlockState(pos, (BlockState)state.with(AGE, j + 1), Block.NO_REDRAW);
	                }
	           
	            
	            
	            }
	
	
	 }	
	 }
	
	 @Override
	    protected Block getPlant() {
	        return FeatureCreepMC.RICE_PLANT;
	    }

	@Override
	  public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
	    Block.dropStack((World)world, pos, FeatureCreepMC.RICE.getDefaultStack());
	    
	}
	 
	
	
	
	 
}
