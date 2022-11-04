package featurecreep.legacy;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import static net.minecraft.util.math.Direction.*;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.util.*;



public class MinersDream extends Item{

	
public MinersDream(Settings properties, int minx0, int maxx0, int miny0, int maxy0, int minz0, int maxz0) {
		super(properties);
		// TODO Auto-generated constructor stub
	
		minx = minx0;
		maxx = maxx0;
		miny = miny0;
		maxy = maxy0; 
	minz = minz0; 
		maxz = maxz0;

}



public	int minx;
	public int maxx;
	public int miny;
	public int maxy; 
public	int minz; 
	public int maxz;


	
	//FeatureCreepMC
@Override
		public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
		ItemStack stack = playerEntity.getStackInHand(hand);
		playerEntity.setCurrentHand(hand);
		BlockPos blockPosition = playerEntity.getBlockPos();
		Direction direction = playerEntity.getMovementDirection();
		if(!playerEntity.abilities.creativeMode) {
			stack.decrement(1);
		}
		if(!world.isClient) {
			for(int x = minx; x <= maxx; x++) {
				for(int y = miny; y <= maxy; y++) {
					for(int z = minz; z <= maxz; z++) {
						BlockPos blockPosition0 = new BlockPos(blockPosition);
						if(direction == Direction.SOUTH) {
							blockPosition0 = new BlockPos(blockPosition.getX() + x, blockPosition.getY() + y, blockPosition.getZ() + z);
						}else if(direction == Direction.NORTH) {
							blockPosition0 = new BlockPos(blockPosition.getX() - x, blockPosition.getY() + y, blockPosition.getZ() - z);
						}else if(direction == Direction.EAST) {
							blockPosition0 = new BlockPos(blockPosition.getX() + z, blockPosition.getY() + y, blockPosition.getZ() + x);
						}else if(direction == Direction.WEST) {
							blockPosition0 = new BlockPos(blockPosition.getX() - z, blockPosition.getY() + y, blockPosition.getZ() - x);
						}

						BlockPos RemovePos = new BlockPos(blockPosition0.getX(), blockPosition0.getY() + 1, blockPosition0.getZ());
					
						if(		world.getBlockState(blockPosition0) == Blocks.STONE.getDefaultState() || world.getBlockState(blockPosition0) == Blocks.SANDSTONE.getDefaultState() || world.getBlockState(blockPosition0) == Blocks.DIORITE.getDefaultState() || world.getBlockState(blockPosition0) == Blocks.ANDESITE.getDefaultState() || world.getBlockState(blockPosition0) == Blocks.GRANITE.getDefaultState()) {
							world.setBlockState(blockPosition0, Blocks.AIR.getDefaultState());
						}
					}
				}
			}
			return new TypedActionResult<>(ActionResult.SUCCESS, stack);
			}
		return new TypedActionResult<>(ActionResult.FAIL, stack);
		}
	
	
}