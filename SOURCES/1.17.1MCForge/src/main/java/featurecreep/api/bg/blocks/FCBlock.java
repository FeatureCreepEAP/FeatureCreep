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
import game.BlockState;
import game.GameRegistries;
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
    super(Block.Info.fromMaterial(material.get()).hardnessAndResistance(strength / 10));
        initialise(id, modid, name,  group, material, strength, drops);

  }






  @Override
  public void onBroken(World world, Player player, BlockPos pos, BlockState blockState, @Nullable TileEntity tileEntity, ItemStack stack) {
      player.incrementStat(PlayerStatisticList.MINED.getOrCreateStat(this));
      player.addExhaustion(0.005f);

    ArrayList<BlockDropArrayObject> arr = getDrops(new VanillaItem(stack.getItem(), GameRegistries.ITEM.getName(stack.getItem()).toString()));
  System.out.println("Block Broken");
    for (int i = 0; i < arr.size(); i++) {
    	  System.out.println("Scanning Drops");

    	BlockDropArrayObject loot = arr.get(i);
    	
    	if (loot instanceof featurecreep.api.bg.blocks.drop.SelfBlockDropArrayObject) {
            System.out.println("Dropping Self");
            executeDropItem(new FCWorld(world), new FCBlockPos(pos.getX(), pos.getY(), pos.getZ()),this);//for a sec i thought i had to declare a whole new vanila item, turns out not
          } else {
            for (int t = 0; t < loot.drop.size(); t++) {
              System.out.println("Right tool used");
              if (loot.drop.get(t) instanceof BlockOrItem) {
            	  executeDropItem(new FCWorld(world), new FCBlockPos(pos.getX(), pos.getY(), pos.getZ()), (BlockOrItem) loot.drop.get(t));
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

}
