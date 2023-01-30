package featurecreep.api.bg.blocks;

import org.jetbrains.annotations.Nullable;

import featurecreep.api.bg.blocks.drop.BlockDropArrayObject;
import featurecreep.api.bg.blocks.drop.BlockDropArrayObjects;
import featurecreep.api.bg.blocks.materials.UnifiedBlockMaterial;
import featurecreep.api.bg.tooltypes.ToolTypes;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FCOre extends OreBlock implements FCBlockAPI<FCOre> {

	public BlockFieldHolder holder = new BlockFieldHolder();
	@Override public BlockFieldHolder holder() {	return holder;	}
  public Object resource;


	  public FCOre(int id, String modid, String name, UnifiedItemGroupGetter group, UnifiedBlockMaterial material, int strength, BlockDropArrayObject[] drops, Object ore_material) {
    super(Block.Settings.of(material.get()).strength(strength / 10));
initialise(id, modid, name,  group, material, strength, drops);
resource = ore_material;
  }



  @Override
  public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
    player.incrementStat(Stats.MINED.getOrCreateStat(this));
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

    if (loot.equals(BlockDropArrayObjects.SELF)) {
      System.out.println("Dropping Self");
      Block.dropStack(world, pos, new ItemStack(this));
    } else {
      for (int t = 0; t < loot.drop.size(); t++) {
        System.out.println("Right tool used");
        if (loot.drop.get(t) instanceof Block) {
          Block.dropStack(world, pos, new ItemStack((Block) loot.drop.get(t)));
          //   Block.dropStacks(state, world, pos, blockEntity, player, new ItemStack((Block) getDropArrayObjects()[i].drop.get(t)));
        } else {
          Block.dropStack(world, pos, new ItemStack((Item) loot.drop.get(t)));
          //  Block.dropStacks(state, world, pos, blockEntity, player, new ItemStack((Item) getDropArrayObjects()[i].drop.get(t)));
        } //Gotta do entites soon

      }
    }

  }
  
  

  @Override
  public FCOre isSingleSided(boolean answer) {
holder().	    single_sided = answer;
	return this;  
  }

}
