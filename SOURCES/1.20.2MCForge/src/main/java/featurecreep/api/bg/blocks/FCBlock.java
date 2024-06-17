package featurecreep.api.bg.blocks;

import featurecreep.api.bg.blocks.drop.BlockDropArrayObject;
import featurecreep.api.bg.blocks.materials.UnifiedBlockMaterial;
import featurecreep.api.bg.tooltypes.ToolTypes;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import game.Block;
import game.BlockPos;
import game.BlockPropertiesData;
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
	    super(Block.Info.normal().hardness(strength / 10));//Need to add material again soon
    initialise(id, modid, name,  group, material, strength, drops);

  }



  @Override
	public void onMinedSucessfully(World world, Player player, BlockPos pos, BlockPropertiesData state, @Nullable TileEntity blockEntity,
		  ItemStack stack) {
	player.incrementStat(PlayerStatisticList.MINED.getOrCreateStat(this));
    player.addExhaustion(0.005f);

    for (int i = 0; i < getDropArrayObjects().length; i++) {
      if (getDropArrayObjects()[i].getTool.equals(ToolTypes.BLANK)) {

        System.out.println("Right tool used Dropping items");

        getDrops(world, pos, getDropArrayObjects()[i]);

      } else {
        System.out.println(stack.getItem().toString()); // Took so damn long to realise i needed stack and not player.ActiveItem

        //System.out.println(stack.getItem().getClass().isAssignableFrom(getDropArrayObjects()[i].getTool.get));
        System.out.println(getDropArrayObjects()[i].getTool.get.isAssignableFrom(stack.getItem().getClass()));

        if (getDropArrayObjects()[i].getTool.get.isAssignableFrom(stack.getItem().getClass())) {
          System.out.println("You used the right tool");
          getDrops(world, pos, getDropArrayObjects()[i]);
        } else {
          System.out.print("Wrong Tool Used For This Array, you need an instance of" + getDropArrayObjects()[i].getTool.get.getCanonicalName() + "But instead got" + player.getActiveItem().getClass().getName());
        }

      }

    }

  }

  private void getDrops(World world, BlockPos pos, BlockDropArrayObject loot) {

 if (loot instanceof featurecreep.api.bg.blocks.drop.SelfBlockDropArrayObject) {
      System.out.println("Dropping Self");
      Block.drop(world, pos, new ItemStack(this));
    } else {
      for (int t = 0; t < loot.drop.size(); t++) {
        System.out.println("Right tool used");
        if (loot.drop.get(t) instanceof Block) {
          Block.drop(world, pos, new ItemStack((Block) loot.drop.get(t)));
          //   Block.dropStacks(state, world, pos, blockEntity, player, new ItemStack((Block) getDropArrayObjects()[i].drop.get(t)));
        } else {
          Block.drop(world, pos, new ItemStack((Item) loot.drop.get(t)));
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
