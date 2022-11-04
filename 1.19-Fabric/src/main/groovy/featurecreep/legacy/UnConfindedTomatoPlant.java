package featurecreep.legacy;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class UnConfindedTomatoPlant extends TomatoPlant {

	public UnConfindedTomatoPlant(Settings settings) {
		super(settings);
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	 @Override
	    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
	            int i = 1;
	                ++i;
	            
	            if (i < 3) {
	                int j = state.get(AGE);
	                if (j == 15) {
	                    world.setBlockState(pos.up(), this.getDefaultState());
	                    world.setBlockState(pos.north(), this.getDefaultState());
	                    world.setBlockState(pos.south(), this.getDefaultState());
	                    world.setBlockState(pos.west(), this.getDefaultState());
	                    world.setBlockState(pos.east(), this.getDefaultState());
	                    world.setBlockState(pos.down(), this.getDefaultState());

	                    world.setBlockState(pos, (BlockState)state.with(AGE, 0), Block.NO_REDRAW);
	                } else {
	                    world.setBlockState(pos, (BlockState)state.with(AGE, j + 1), Block.NO_REDRAW);
	                }
	           
	            
	            
	            }
	
	 }
	
	
	 
	  
	 
	 
	 
	 
	
}
