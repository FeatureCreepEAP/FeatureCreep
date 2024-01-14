package featurecreep.api.bg.blocks;

import java.util.ArrayList;

import featurecreep.api.bg.blocknitem.BlockOrItem;
import featurecreep.api.bg.blocks.drop.BlockDropArrayObject;
import featurecreep.api.bg.blocks.materials.UnifiedBlockMaterial;
import featurecreep.api.bg.entity.AbstractEntity;
import featurecreep.api.bg.items.vanilla.VanillaItem;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import featurecreep.api.bg.world.FCWorld;
import game.Block;
import game.BlockPos;
import game.IBlockAccess;
import game.IBlockstate;
import game.Item;
import game.ItemStack;
import game.Player;
import game.PlayerStatisticList;
import game.TileEntity;
import game.World;
import io.smallrye.common.constraint.Nullable;




public class FCBlock extends Block implements FCBlockAPI<FCBlock> {

public BlockFieldHolder holder = new BlockFieldHolder();
	@Override public BlockFieldHolder holder() {	return holder;	}

  public FCBlock(int id, String modid, String name, UnifiedItemGroupGetter group, UnifiedBlockMaterial material, int strength, BlockDropArrayObject[] drops) {
    super(material.get());
	    initialise(id, modid, name,  group, material, strength, drops);

  this.destroy_speed = (strength /10);
  }


  @Override
  public void onMinedSucessfully(World worldIn, Player player, BlockPos pos, IBlockstate state, @Nullable TileEntity te, ItemStack stack)
  {
      player.incrementStat(PlayerStatisticList.getBlockStats(this));
      player.addExhaustion(0.005F);
      

	    ArrayList<BlockDropArrayObject> arr = getDrops(new VanillaItem(stack.toItem(), Item.registry.getName(stack.toItem()).toString()));
	  System.out.println("Block Broken");
	    for (int i = 0; i < arr.size(); i++) {
	    	  System.out.println("Scanning Drops");

	    	BlockDropArrayObject loot = arr.get(i);
	    	
	    	if (loot instanceof featurecreep.api.bg.blocks.drop.SelfBlockDropArrayObject) {
	            System.out.println("Dropping Self");
	            executeDropItem(new FCWorld(worldIn), new FCBlockPos(pos.getX(), pos.getY(), pos.getZ()),this);//for a sec i thought i had to declare a whole new vanila item, turns out not
	          } else {
	            for (int t = 0; t < loot.drop.size(); t++) {
	              System.out.println("Right tool used");
	              if (loot.drop.get(t) instanceof BlockOrItem) {
	            	  executeDropItem(new FCWorld(worldIn), new FCBlockPos(pos.getX(), pos.getY(), pos.getZ()), (BlockOrItem) loot.drop.get(t));
	                //   Block.dropStacks(state, world, pos, blockEntity, player, new ItemStack((Block) getDropArrayObjects()[i].drop.get(t)));
	              }else if (loot.drop.get(t) instanceof AbstractEntity) {
	            	
	              }
	              
	            }
	          }
	    	
	    }
  }
  
      
 


 
  @Override
  public FCBlock isSingleSided(boolean answer) {
	    holder().single_sided = answer;
	return this;  
  }

  //@Override Did not survive change in env well but may still work in game
  public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, Player player)
  {
      return true;
  }
  
  
  
}
