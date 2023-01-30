package featurecreep.api.bg.blocks;

import featurecreep.api.bg.blocks.drop.BlockDropArrayObject;
import featurecreep.api.bg.blocks.drop.BlockDropArrayObjects;
import featurecreep.api.bg.blocks.materials.UnifiedBlockMaterial;
import featurecreep.api.bg.tooltypes.ToolTypes;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import io.smallrye.common.constraint.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FCBlock extends Block implements FCBlockAPI<FCBlock> {

	public BlockFieldHolder holder = new BlockFieldHolder();
	@Override public BlockFieldHolder holder() {	return holder;	}
  

  public FCBlock(int id, String modid, String name, UnifiedItemGroupGetter group, UnifiedBlockMaterial material, int strength, BlockDropArrayObject[] drops) {
    super(material.get());
    initialise(id, modid, name,  group, material, strength, drops);
  this.blockHardness = (strength /10);
  }


  
  @Override
  public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
  {
      player.addStat(StatList.getBlockStats(this));
      player.addExhaustion(0.005F);
      
      for (int i = 0; i < getDropArrayObjects().length; i++) {
          if (getDropArrayObjects()[i].getTool.equals(ToolTypes.BLANK)) {

            System.out.println("Right tool used Dropping items");

            getDrops(worldIn, pos, getDropArrayObjects()[i]);

          } else {
            System.out.println(stack.getItem().toString()); // Took so damn long to realise i needed stack and not player.ActiveItem

            //System.out.println(stack.getItem().getClass().isAssignableFrom(getDropArrayObjects()[i].getTool.get));
            System.out.println(getDropArrayObjects()[i].getTool.get.isAssignableFrom(stack.getItem().getClass()));

            if (getDropArrayObjects()[i].getTool.get.isAssignableFrom(stack.getItem().getClass())) {
              System.out.println("You used the right tool");
              getDrops(worldIn, pos, getDropArrayObjects()[i]);
            } else {
              System.out.print("Wrong Tool Used For This Array, you need an instance of" + getDropArrayObjects()[i].getTool.get.getCanonicalName() + "But instead got" + stack.getItem().getClass().getName());
            }

          }

        }
  }
      
 

  private void getDrops(World world, BlockPos pos, BlockDropArrayObject loot) {

    if (loot.equals(BlockDropArrayObjects.SELF)) {
      System.out.println("Dropping Self");
      Block.spawnAsEntity(world, pos, new ItemStack(this));
    } else {
      for (int t = 0; t < loot.drop.size(); t++) {
        System.out.println("Right tool used");
        if (loot.drop.get(t) instanceof Block) {
          Block.spawnAsEntity(world, pos, new ItemStack((Block) loot.drop.get(t)));
          //   Block.dropStacks(state, world, pos, blockEntity, player, new ItemStack((Block) getDropArrayObjects()[i].drop.get(t)));
        } else {
          Block.spawnAsEntity(world, pos, new ItemStack((Item) loot.drop.get(t)));
          //  Block.dropStacks(state, world, pos, blockEntity, player, new ItemStack((Item) getDropArrayObjects()[i].drop.get(t)));
        } //Gotta do entites soon

      }
    }

  }

 

  @Override
  public FCBlock isSingleSided(boolean answer) {
	    holder().single_sided = answer;
	return this;  
  }

}
