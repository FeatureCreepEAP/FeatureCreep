package featurecreep.legacy;

import net.minecraft.block.Block;
import net.minecraft.item.AliasedBlockItem;

public class FCFruit extends AliasedBlockItem {

	public FCFruit(Block block, Settings settings) {
		super(block, settings);
		// TODO Auto-generated constructor stub
	}

	
	/*  @Override
	    public ActionResult useOnBlock(ItemUsageContext context) {
	        BlockPos hitPos = context.getBlockPos();
	        World world = context.getWorld();
	        BlockState state = world.getBlockState(hitPos);
	        if (state.getBlock() instanceof FarmlandBlock && context.getSide() == Direction.UP) {
	            return super.useOnBlock(context);
	        }
	        return ActionResult.FAIL;
	    }*/
	
}
