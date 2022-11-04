package com.asbestosstar.featurecreep;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class MinersDream extends Item implements IHadModels{

	
public	int minx;
	public int maxx;
	public int miny;
	public int maxy; 
public	int minz; 
	public int maxz;

	public MinersDream(String name, int minx0, int maxx0, int miny0, int maxy0, int minz0, int maxz0)
	{
	setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MATERIALS);
		
		FeatureCreepMC.ITEMS.add(this);
	
		minx = minx0;
		maxx = maxx0;
		miny = miny0;
		maxy = maxy0; 
		minz = minz0; 
		maxz = maxz0;
	
	
	
	}
	
	
	public void registerModels() {
		// TODO Auto-generated method stub
	 FeatureCreepMC.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer playerEntity, EnumHand hand) {
		ItemStack stack = playerEntity.getHeldItem(hand);
		playerEntity.setActiveHand(hand);
		BlockPos blockPosition = playerEntity.getPosition();
		EnumFacing direction = playerEntity.getAdjustedHorizontalFacing();
		if(!playerEntity.capabilities.isCreativeMode) {
			stack.shrink(1);
		}
		if(!world.isRemote) {
			for(int x = minx; x <= maxx; x++) {
				for(int y = miny; y <= maxy; y++) {
					for(int z = minz; z <= maxz; z++) {
						BlockPos blockPosition0 = new BlockPos(blockPosition);
						if(direction == EnumFacing.SOUTH) {
							blockPosition0 = new BlockPos(blockPosition.getX() + x, blockPosition.getY() + y, blockPosition.getZ() + z);
						}else if(direction == EnumFacing.NORTH) {
							blockPosition0 = new BlockPos(blockPosition.getX() - x, blockPosition.getY() + y, blockPosition.getZ() - z);
						}else if(direction == EnumFacing.EAST) {
							blockPosition0 = new BlockPos(blockPosition.getX() + z, blockPosition.getY() + y, blockPosition.getZ() + x);
						}else if(direction == EnumFacing.WEST) {
							blockPosition0 = new BlockPos(blockPosition.getX() - z, blockPosition.getY() + y, blockPosition.getZ() - x);
						}

						BlockPos RemovePos = new BlockPos(blockPosition0.getX(), blockPosition0.getY() + 1, blockPosition0.getZ());
					
						if(		world.getBlockState(blockPosition0) == Blocks.STONE.getDefaultState() || world.getBlockState(blockPosition0) == Blocks.SANDSTONE.getDefaultState() || world.getBlockState(blockPosition0) == Blocks.STONE.getStateFromMeta(0) || world.getBlockState(blockPosition0) == Blocks.STONE.getStateFromMeta(1) || world.getBlockState(blockPosition0) == Blocks.STONE.getStateFromMeta(2) || world.getBlockState(blockPosition0) == Blocks.STONE.getStateFromMeta(3) || world.getBlockState(blockPosition0) == Blocks.STONE.getStateFromMeta(4) || world.getBlockState(blockPosition0) == Blocks.STONE.getStateFromMeta(5) || world.getBlockState(blockPosition0) == Blocks.STONE.getStateFromMeta(6) || world.getBlockState(blockPosition0) == Blocks.STONE.getStateFromMeta(7) || world.getBlockState(blockPosition0) == Blocks.STONE.getStateFromMeta(8) || world.getBlockState(RemovePos) == Blocks.WATER.getDefaultState()) {
							world.setBlockState(blockPosition0, Blocks.AIR.getDefaultState());
						}
					}
				}
			}
			return new ActionResult<>(EnumActionResult.SUCCESS, stack);
		}
		return new ActionResult<>(EnumActionResult.FAIL, stack);
	}
	
	
}
