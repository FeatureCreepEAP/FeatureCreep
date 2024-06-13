package featurecreep.api.bg.blocks;

import java.util.ArrayList;

import featurecreep.api.bg.blocknitem.BlockOrItem;
import featurecreep.api.bg.blocks.drop.BlockDropArrayObject;
import featurecreep.api.bg.blocks.materials.UnifiedBlockMaterial;
import featurecreep.api.bg.entity.AbstractEntity;
import featurecreep.api.bg.items.vanilla.VanillaItem;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import featurecreep.api.bg.world.FCWorld;
import game.BlockAccessInterface;
import game.BlockPos;
import game.BlockPropertiesData;
import game.Item;
import game.ItemStack;
import game.Ore;
import game.Player;
import game.PlayerStatisticList;
import game.TileEntity;
import game.World;
import io.smallrye.common.constraint.Nullable;

public class FCOre extends Ore implements FCBlockAPI<FCOre> {
    public BlockFieldHolder holder;
    public Object resource;
    
    @Override
    public BlockFieldHolder holder() {
        return this.holder;
    }
    
    public FCOre(int integer2, String string3, String string4, UnifiedItemGroupGetter unifiedItemGroupGetter, UnifiedBlockMaterial unifiedBlockMaterial, int integer7, BlockDropArrayObject[] arr, Object object) {
        this.holder = new BlockFieldHolder();
        this.initialise(integer2, string3, string4, unifiedItemGroupGetter, unifiedBlockMaterial, integer7, arr);
        this.resource = object;
    }
    
 
    @Override
    public void onMinedSucessfully(World worldIn, Player player, BlockPos pos, BlockPropertiesData blockPropertiesData, @Nullable TileEntity tileEntity, ItemStack stack) 
    {
        player.incrementStat(PlayerStatisticList.getBlockStats(this));
        player.addExhaustion(0.005F);
        

  	    ArrayList<BlockDropArrayObject> arr = getDrops(new VanillaItem(stack.getItem(), Item.registry.getName(stack.getItem()).toString()));
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
    public FCOre isSingleSided(boolean boolean2) {
        this.holder().single_sided = boolean2;
        return this;
    }
    
    public boolean canHarvestBlock(BlockAccessInterface iBlockAccess, BlockPos blockPos, Player player) {
        return true;
    }
}

