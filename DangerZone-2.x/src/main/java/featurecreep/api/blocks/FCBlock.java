package featurecreep.api.blocks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jboss.dmr.ModelNode;
import org.newdawn.slick.opengl.Texture;

import dangerzone.DangerZone;
import dangerzone.InventoryContainer;
import dangerzone.Player;
import dangerzone.StitchedTexture;
import dangerzone.blocks.Block;
import dangerzone.entities.EntityBlockItem;
import dangerzone.items.Item;
import featurecreep.FeatureCreep;
import featurecreep.api.blocks.drop.BlockDropArrayObject;
import featurecreep.api.blocks.drop.BlockDropArrayObjects;
import featurecreep.api.blocks.materials.VanillaBlockMaterial;
import featurecreep.api.tooltypes.ToolTypes;
import featurecreep.api.ui.FCCreativeTab;
import featurecreep.api.ui.tabs.vanilla.VanillaCreativeTab;

public class FCBlock extends Block implements FCBlockAPI {

  public String public_modid;
  public String public_name;
  public int number_id;
  public boolean single_sided = false;
  public BlockDropArrayObject[] drop_arrays;
  public FCCreativeTab default_tab;
  
  
  Texture ttop = null;
	Texture tbottom = null;
	Texture tleft = null;
	Texture tright = null;
	Texture tfront = null;
	Texture tback = null;
	String topname;
	String bottomname;
	String leftname;
	String rightname;
	String frontname;
	String backname;
	StitchedTexture sttop = new StitchedTexture();
	StitchedTexture stbottom = new StitchedTexture();
	StitchedTexture stleft = new StitchedTexture();
	StitchedTexture stright = new StitchedTexture();
	StitchedTexture stfront = new StitchedTexture();
	StitchedTexture stback = new StitchedTexture();
  

  public FCBlock(int id, String modid, String name, FCCreativeTab group, VanillaBlockMaterial material, int strength, BlockDropArrayObject[] drops) {
    super(modid + ":" + name, "");
    public_modid = modid;
    public_name = name;
    registerModels(this);
    this.number_id = id;
    drop_arrays = drops;
  }

  public FCBlock(int id, String modid, String name, VanillaCreativeTab group, VanillaBlockMaterial material, int strength, BlockDropArrayObject[] drops) {
    this(id, modid, name, (FCCreativeTab) null, material, strength, drops);
  }

  public void registerModels(Block item) {
    // TODO Auto-generated method stub

    if (single_sided == true) {
    	topname = FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + public_modid + "/textures/blocks/" +public_name +".png";
		bottomname = FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + public_modid + "/textures/blocks/" +public_name +".png";
		leftname = FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + public_modid + "/textures/blocks/" +public_name +".png";
		rightname = FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + public_modid + "/textures/blocks/" +public_name +".png";
		frontname = FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + public_modid + "/textures/blocks/" +public_name +".png";
		backname = FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + public_modid + "/textures/blocks/" +public_name +".png";
		System.out.println(topname);

    } else {
    	
    	
		topname = FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + public_modid + "/textures/blocks/" +public_name +"_up.png";
		bottomname = FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + public_modid + "/textures/blocks/" +public_name +"_down.png";
		leftname = FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + public_modid + "/textures/blocks/" +public_name +"_west.png";
		rightname = FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + public_modid + "/textures/blocks/" +public_name +"_east.png";
		frontname = FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + public_modid + "/textures/blocks/" +public_name +"_north.png";
		backname = FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + public_modid + "/textures/blocks/" +public_name +"_south.png";
    	
  System.out.println(topname);

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

  public FCCreativeTab getDefaultCreativeTab() {
    // TODO Auto-generated method stub
    return default_tab;
  }

  public void isSingleSided(boolean answer) {
    single_sided = answer;
  }
  //server-side only
  public void onBlockBroken(Player p, int dimension, int x, int y, int z, int s) {
    super.onBlockBroken(p, dimension, x, y, z, s);
    if (p == null) return;
    if (DangerZone.rand.nextInt(10) != 1) return;

    InventoryContainer ic = p.getHotbar(p.gethotbarindex());
    if (ic.getItem() == null) return;

    for (int i = 0; i < drop_arrays.length; i++) {
      if (drop_arrays[i].getTool.equals(ToolTypes.BLANK)) {

        System.out.println("Right tool used Dropping items");

        getDrops(p, x, y, z, drop_arrays[i], dimension);

      } else {
        System.out.println(ic.getItem().toString()); // Took so damn long to realise i needed stack and not player.ActiveItem

        //System.out.println(stack.getItem().getClass().isAssignableFrom(drop_arrays[i].getTool.get));
        System.out.println(drop_arrays[i].getTool.get.isAssignableFrom(ic.getItem().getClass()));

        if (drop_arrays[i].getTool.get.isAssignableFrom(ic.getItem().getClass())) {
          System.out.println("You used the right tool");
          getDrops(p, x, y, z, drop_arrays[i], dimension);
        } else {
          System.out.print("Wrong Tool Used For This Array, you need an instance of" + drop_arrays[i].getTool.get.getCanonicalName() + "But instead got" + ic.getItem().toString());
        }

      }

    }

  }

  private void getDrops(Player player, int x, int y, int z, BlockDropArrayObject loot, int dimension) {

    if (loot.equals(BlockDropArrayObjects.SELF)) {
      System.out.println("Dropping Self");

      EntityBlockItem e = (EntityBlockItem) player.world.createEntityByName(DangerZone.blockitemname, dimension, (double) x + 0.5f, (double) y + 0.5f, (double) z + 0.5f);
      if (e != null) {
        e.fill(this.blockID, 0, 1); //I am a block!	
        player.world.spawnEntityInWorld(e);
      }

    } else {
      for (int t = 0; t < loot.drop.size(); t++) {
        System.out.println("Right tool used");
        if (loot.drop.get(t) instanceof Block) {
          // Block.dropStack(world, pos, new ItemStack((Block) loot.drop.get(t)));
          EntityBlockItem e = (EntityBlockItem) player.world.createEntityByName(DangerZone.blockitemname, dimension, (double) x + 0.5f, (double) y + 0.5f, (double) z + 0.5f);
          Block block = (Block) loot.drop.get(t);
          if (e != null) {
            e.fill(block.blockID, 0, 1); //I am a block!	
            player.world.spawnEntityInWorld(e);
          }
          //   Block.dropStacks(state, world, pos, blockEntity, player, new ItemStack((Block) drop_arrays[i].drop.get(t)));
        } else {

          EntityBlockItem e = (EntityBlockItem) player.world.createEntityByName(DangerZone.blockitemname, dimension, (double) x + 0.5f, (double) y + 0.5f, (double) z + 0.5f);
          Item block = (Item) loot.drop.get(t);
          if (e != null) {
            e.fill(0, block.itemID, 1); //I am an item!	
            player.world.spawnEntityInWorld(e);
          }
        }

      //  Block.dropStack(world, pos, new ItemStack((Item) loot.drop.get(t)));
        //  Block.dropStacks(state, world, pos, blockEntity, player, new ItemStack((Item) drop_arrays[i].drop.get(t)));
      } //Gotta do entites soon

    }
  }

  
  
  //Just like MC i copied Workbench settings
  
	//side 0 = top
	//side 1 = front
	//side 2 = back
	//side 3 = left
	//side 4 = right
	//side 5 = bottom
	public Texture getTexture(int side){

		if(ttop == null){
			ttop = initBlockTexture(topname);
		}
		if(tbottom == null){
			tbottom = initBlockTexture(bottomname);
		}
		if(tleft == null){
			tleft = initBlockTexture(leftname);
		}
		if(tright == null){
			tright = initBlockTexture(rightname);
		}
		if(tfront == null){
			tfront = initBlockTexture(frontname);
		}
		if(tback == null){
			tback = initBlockTexture(backname);
		}
		
		if(side == 0)return ttop;
		if(side == 5)return tbottom;
		if(side == 3)return tleft;
		if(side == 4)return tright;
		if(side == 1)return tfront;
		if(side == 2)return tback;
		return null;
	}
	
	public StitchedTexture getStitchedTexture(int side){	
		if(side == 0)return sttop;
		if(side == 5)return stbottom;
		if(side == 3)return stleft;
		if(side == 4)return stright;
		if(side == 1)return stfront;
		return stback;
	}
	
	public String getStitchedTextureName(int side){
		if(side == 0)return topname;
		if(side == 5)return bottomname;
		if(side == 3)return leftname;
		if(side == 4)return rightname;
		if(side == 1)return frontname;
		return backname;
	}
  
  
  
}

