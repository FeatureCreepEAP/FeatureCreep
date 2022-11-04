package featurecreep.legacy;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static net.minecraft.util.math.Direction.*;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.util.*;



public class Digger extends Item{

	
public Digger(Settings properties, int minx0, int maxx0, int miny0, int maxy0, int minz0, int maxz0) {
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
public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {

	pos = miner.getBlockPos();
	Direction direction = miner.getMovementDirection();
		if(!world.isClient) {
		for(int x = minx; x <= maxx; x++) {
			for(int y = miny; y <= maxy; y++) {
				for(int z = minz; z <= maxz; z++) {
					BlockPos pos0 = new BlockPos(pos);
					if(direction == Direction.SOUTH) {
						pos0 = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
					}else if(direction == Direction.NORTH) {
						pos0 = new BlockPos(pos.getX() - x, pos.getY() + y, pos.getZ() - z);
					}else if(direction == Direction.EAST) {
						pos0 = new BlockPos(pos.getX() + z, pos.getY() + y, pos.getZ() + x);
					}else if(direction == Direction.WEST) {
						pos0 = new BlockPos(pos.getX() - z, pos.getY() + y, pos.getZ() - x);
					}

					BlockPos RemovePos = new BlockPos(pos0.getX(), pos0.getY() + 1, pos0.getZ());
				
					if(		world.getBlockState(pos0) == Blocks.STONE.getDefaultState() || world.getBlockState(pos0) == Blocks.SANDSTONE.getDefaultState() || world.getBlockState(pos0) == Blocks.DIORITE.getDefaultState() || world.getBlockState(pos0) == Blocks.ANDESITE.getDefaultState() || world.getBlockState(pos0) == Blocks.GRANITE.getDefaultState()) {
						world.setBlockState(pos0, Blocks.AIR.getDefaultState());
					}
				}
			}
		}
		return true;
		}
	return false;
	}


}
	
	
