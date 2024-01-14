package featurecreep.legacy;

import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class UltimateHoe extends EnchantedHoe {

	public UltimateHoe(ToolMaterial tier, int attackDamageIn, float attackSpeedIn, Settings builderIn) {
		super(tier, attackDamageIn, attackSpeedIn, builderIn);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
    public ActionResult useOnBlock(ItemUsageContext context) {
		
        PlayerEntity playerEntity = context.getPlayer();
        World world = context.getWorld();
        BlockPos blockPosition = playerEntity.getBlockPos();
	Direction direction = playerEntity.getMovementDirection();
	if(!world.isClient) {
		for(int x = -5; x <= 5; x++) {
			for(int y = -5; y <= 5; y++) {
				for(int z = -5; z <= 5; z++) {
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
				
					if(		world.getBlockState(blockPosition0) == Blocks.DIRT.getDefaultState() || world.getBlockState(blockPosition0) == Blocks.COARSE_DIRT.getDefaultState() || world.getBlockState(blockPosition0) == Blocks.GRASS.getDefaultState() || world.getBlockState(blockPosition0) == Blocks.GRASS_BLOCK.getDefaultState() || world.getBlockState(blockPosition0) == Blocks.MYCELIUM.getDefaultState()) {
						world.setBlockState(blockPosition0, Blocks.FARMLAND.getDefaultState().with(FarmlandBlock.MOISTURE, 7));
					}
				}
			}
		}
        return ActionResult.success(world.isClient);
		}
    return ActionResult.PASS;
	}

	
}
