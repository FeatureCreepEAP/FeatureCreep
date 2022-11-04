package featurecreep.api.blocks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jboss.dmr.ModelNode;

import featurecreep.api.blocks.drop.BlockDropArrayObject;
import featurecreep.api.blocks.drop.BlockDropArrayObjects;
import featurecreep.api.blocks.materials.VanillaBlockMaterial;
import featurecreep.api.tooltypes.ToolTypes;
import featurecreep.api.ui.tabs.vanilla.VanillaCreativeTab;
import io.smallrye.common.constraint.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FCBlock extends Block implements FCBlockAPI {

  public String public_modid;
  public String public_name;
  public int number_id;
  public CreativeTabs default_tab;
  public boolean single_sided = false;
  public BlockDropArrayObject[] drop_arrays;

  public FCBlock(int id, String modid, String name, CreativeTabs group, VanillaBlockMaterial material, int strength, BlockDropArrayObject[] drops) {
    super(material.get);
    public_modid = modid;
    public_name = name;
    registerModels(this);
    this.default_tab = group;
    this.number_id = id;
    drop_arrays = drops;
  this.blockHardness = (strength /10);
  }

  public FCBlock(int id, String modid, String name, VanillaCreativeTab group, VanillaBlockMaterial material, int strength, BlockDropArrayObject[] drops) {
    this(id, modid, name, VanillaCreativeTab.getVanillaGroupFromString(group), material, strength, drops);
  }

  public void registerModels(Block item) {
    // TODO Auto-generated method stub

    String down_texture;
    String east_texture;
    String north_texture;
    String particle_texture;
    String south_texture;
    String up_texture;
    String west_texture;

    if (single_sided == true) {
      down_texture = public_modid + ":blocks/" + public_name;
      east_texture = public_modid + ":blocks/" + public_name;
      north_texture = public_modid + ":blocks/" + public_name;
      particle_texture = public_modid + ":blocks/" + public_name;
      south_texture = public_modid + ":blocks/" + public_name;
      up_texture = public_modid + ":blocks/" + public_name;
      west_texture = public_modid + ":blocks/" + public_name;

    } else {
      down_texture = public_modid + ":blocks/" + public_name + "_down";
      east_texture = public_modid + ":blocks/" + public_name + "_east";
      north_texture = public_modid + ":blocks/" + public_name + "_north";
      particle_texture = public_modid + ":blocks/" + public_name + "_particle";
      south_texture = public_modid + ":blocks/" + public_name + "_south";
      up_texture = public_modid + ":blocks/" + public_name + "_up";
      west_texture = public_modid + ":blocks/" + public_name + "_west";

    }

    //Item Generation

    //I could just do a long string but i will need to use this format for some other things so may as well start 
    ModelNode node = new ModelNode();
    node.get("parent").set(public_modid + ":block/" + public_name);
    //node.get("textures").get("layer0").set(public_modid + ":items/" + public_name); Not needed in Blocks

    System.out.print(node.toJSONString(false));

    try {

      File myObj = new File(featurecreep.api.PackLoader.fc_pack_location + "/assets/" + public_modid + "/models/item/" + public_name + ".json");

      if (!myObj.exists()) {

        System.out.println(myObj.toString());
        myObj.getParentFile().mkdirs();

        FileWriter myWriter = new FileWriter(myObj);
        myWriter.write(node.toJSONString(true));
        myWriter.close();

      }

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    //Block Model Generation	      

    ModelNode block_node = new ModelNode();
    block_node.get("parent").set("minecraft:block/cube");
    block_node.get("textures").get("down").set(down_texture);
    block_node.get("textures").get("east").set(east_texture);
    block_node.get("textures").get("north").set(north_texture);
    block_node.get("textures").get("particle").set(particle_texture);
    block_node.get("textures").get("south").set(south_texture);
    block_node.get("textures").get("up").set(up_texture);
    block_node.get("textures").get("west").set(west_texture);

    System.out.print(block_node.toJSONString(false));

    try {

      File myObj = new File(featurecreep.api.PackLoader.fc_pack_location + "/assets/" + public_modid + "/models/block/" + public_name + ".json");

      if (!myObj.exists()) {

        System.out.println(myObj.toString());
        myObj.getParentFile().mkdirs();

        FileWriter myWriter = new FileWriter(myObj);
        myWriter.write(block_node.toJSONString(true));
        myWriter.close();

      }

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    //Blockstates

    ModelNode blockstate = new ModelNode();
    blockstate.get("variants").get("normal").get("model").set(public_modid + ":" + public_name);

    System.out.print(blockstate.toJSONString(false));

    try {

      File myObj = new File(featurecreep.api.PackLoader.fc_pack_location + "/assets/" + public_modid + "/blockstates/" + public_name + ".json");

      if (!myObj.exists()) {

        System.out.println(myObj.toString());
        myObj.getParentFile().mkdirs();

        FileWriter myWriter = new FileWriter(myObj);
        myWriter.write(blockstate.toJSONString(true));
        myWriter.close();

      }

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  public String getModId() {
    // TODO Auto-generated method stub
    return public_modid;
  }

  public String getUnlocName() {
    // TODO Auto-generated method stub
    return public_name;
  }

  public int getNumberID() {
    // TODO Auto-generated method stub
    return number_id;
  }

  public CreativeTabs getDefaultCreativeTab() {
    // TODO Auto-generated method stub
    return default_tab;
  }

  public void isSingleSided(boolean answer) {
    single_sided = answer;
  }

  
  @Override
  public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
  {
      player.addStat(StatList.getBlockStats(this));
      player.addExhaustion(0.005F);
      
      for (int i = 0; i < drop_arrays.length; i++) {
          if (drop_arrays[i].getTool.equals(ToolTypes.BLANK)) {

            System.out.println("Right tool used Dropping items");

            getDrops(worldIn, pos, drop_arrays[i]);

          } else {
            System.out.println(stack.getItem().toString()); // Took so damn long to realise i needed stack and not player.ActiveItem

            //System.out.println(stack.getItem().getClass().isAssignableFrom(drop_arrays[i].getTool.get));
            System.out.println(drop_arrays[i].getTool.get.isAssignableFrom(stack.getItem().getClass()));

            if (drop_arrays[i].getTool.get.isAssignableFrom(stack.getItem().getClass())) {
              System.out.println("You used the right tool");
              getDrops(worldIn, pos, drop_arrays[i]);
            } else {
              System.out.print("Wrong Tool Used For This Array, you need an instance of" + drop_arrays[i].getTool.get.getCanonicalName() + "But instead got" + stack.getItem().getClass().getName());
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
          //   Block.dropStacks(state, world, pos, blockEntity, player, new ItemStack((Block) drop_arrays[i].drop.get(t)));
        } else {
          Block.spawnAsEntity(world, pos, new ItemStack((Item) loot.drop.get(t)));
          //  Block.dropStacks(state, world, pos, blockEntity, player, new ItemStack((Item) drop_arrays[i].drop.get(t)));
        } //Gotta do entites soon

      }
    }

  }

}