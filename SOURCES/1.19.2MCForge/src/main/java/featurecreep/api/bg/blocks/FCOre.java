package featurecreep.api.bg.blocks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jboss.dmr.ModelNode;
import org.jetbrains.annotations.Nullable;

import featurecreep.api.bg.blocknitem.BlockOrItem;
import featurecreep.api.bg.blocks.drop.BlockDropArrayObject;
import featurecreep.api.bg.blocks.drop.BlockDropArrayObjects;
import featurecreep.api.bg.blocks.materials.UnifiedBlockMaterial;
import featurecreep.api.bg.blocks.materials.VanillaBlockMaterial;
import featurecreep.api.bg.entity.AbstractEntity;
import featurecreep.api.bg.items.vanilla.VanillaItem;
import featurecreep.api.bg.tooltypes.ToolTypes;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import featurecreep.api.bg.ui.tabs.vanilla.VanillaCreativeTab;
import featurecreep.api.bg.world.FCWorld;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class FCOre extends OreBlock implements FCBlockAPI<FCOre> {

	public BlockFieldHolder holder = new BlockFieldHolder();
	@Override public BlockFieldHolder holder() {	return holder;	}
  public Object resource;


	  public FCOre(int id, String modid, String name, UnifiedItemGroupGetter group, UnifiedBlockMaterial material, int strength, BlockDropArrayObject[] drops, Object ore_material) {
    super(Block.Settings.of(material.get()).strength(strength / 10));
initialise(id, modid, name,  group, material, strength, drops);
resource = ore_material;

//ForgeRegistries.BLOCKS.register(public_modid+":"+public_name, this);

//ForgeRegistries.ITEMS.register(public_modid+":"+public_name, new BlockItem(this, new Item.Settings().group(default_tab)));
  
  }


	  @Override
	  public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
	    player.incrementStat(Stats.MINED.getOrCreateStat(this));
	    player.addExhaustion(0.005f);

	    ArrayList<BlockDropArrayObject> arr = getDrops(new VanillaItem(stack.getItem(), Registry.ITEM.getId(stack.getItem()).toString()));
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
  public FCOre isSingleSided(boolean answer) {
	holder().    single_sided = answer;
	return this;  
  }
  
  
  

}
