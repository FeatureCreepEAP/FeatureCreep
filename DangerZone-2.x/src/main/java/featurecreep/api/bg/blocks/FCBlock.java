package featurecreep.api.bg.blocks;


import dangerzone.DangerZone;
import dangerzone.InventoryContainer;
import dangerzone.Player;
import dangerzone.blocks.Block;
import dangerzone.entities.EntityBlockItem;
import dangerzone.items.Item;
import dangerzone.rendering.StitchedTexture;
import featurecreep.api.bg.blocks.drop.BlockDropArrayObject;
import featurecreep.api.bg.blocks.drop.BlockDropArrayObjects;
import featurecreep.api.bg.blocks.materials.UnifiedBlockMaterial;
import featurecreep.api.bg.tooltypes.ToolTypes;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import slick.Texture;

public class FCBlock extends Block implements FCBlockAPI<FCBlock> {

public BlockFieldHolder holder = new BlockFieldHolder();
	@Override public BlockFieldHolder holder() {	return holder;	}

	Texture ttop = null;
	Texture tbottom = null;
	Texture tleft = null;
	Texture tright = null;
	Texture tfront = null;
	Texture tback = null;
	StitchedTexture sttop = new StitchedTexture();
	StitchedTexture stbottom = new StitchedTexture();
	StitchedTexture stleft = new StitchedTexture();
	StitchedTexture stright = new StitchedTexture();
	StitchedTexture stfront = new StitchedTexture();
	StitchedTexture stback = new StitchedTexture();
	

  public FCBlock(int id, String modid, String name, UnifiedItemGroupGetter group, UnifiedBlockMaterial material, int strength, BlockDropArrayObject[] drops) {
    super(modid + ":" + name, "");
    initialise(id, modid, name,  group, material, strength, drops);

  }


  //server-side only
  public void onBlockBroken(Player p, int dimension, int x, int y, int z, int s) {
    super.onBlockBroken(p, dimension, x, y, z, s);
    if (p == null) return;
    if (DangerZone.rand.nextInt(10) != 1) return;

    InventoryContainer ic = p.getHotbar(p.gethotbarindex());
    if (ic.getItem() == null) return;

    for (int i = 0; i < getDropArrayObjects().length; i++) {
      if (getDropArrayObjects()[i].getTool.equals(ToolTypes.BLANK)) {

        System.out.println("Right tool used Dropping items");

        getDrops(p, x, y, z, getDropArrayObjects()[i], dimension);

      } else {
        System.out.println(ic.getItem().toString()); // Took so damn long to realise i needed stack and not player.ActiveItem

        //System.out.println(stack.getItem().getClass().isAssignableFrom(getDropArrayObjects()[i].getTool.get));
        System.out.println(getDropArrayObjects()[i].getTool.get.isAssignableFrom(ic.getItem().getClass()));

        if (getDropArrayObjects()[i].getTool.get.isAssignableFrom(ic.getItem().getClass())) {
          System.out.println("You used the right tool");
          getDrops(p, x, y, z, getDropArrayObjects()[i], dimension);
        } else {
          System.out.print("Wrong Tool Used For This Array, you need an instance of" + getDropArrayObjects()[i].getTool.get.getCanonicalName() + "But instead got" + ic.getItem().toString());
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
          //   Block.dropStacks(state, world, pos, blockEntity, player, new ItemStack((Block) getDropArrayObjects()[i].drop.get(t)));
        } else {

          EntityBlockItem e = (EntityBlockItem) player.world.createEntityByName(DangerZone.blockitemname, dimension, (double) x + 0.5f, (double) y + 0.5f, (double) z + 0.5f);
          Item block = (Item) loot.drop.get(t);
          if (e != null) {
            e.fill(0, block.itemID, 1); //I am an item!	
            player.world.spawnEntityInWorld(e);
          }
        }

        //  Block.dropStack(world, pos, new ItemStack((Item) loot.drop.get(t)));
        //  Block.dropStacks(state, world, pos, blockEntity, player, new ItemStack((Item) getDropArrayObjects()[i].drop.get(t)));
      } //Gotta do entites soon

    }
  }

  //Just like MC i copied Workbench settings

  //Just like MC i copied Workbench settings
  
 	//side 0 = top
 	//side 1 = front
 	//side 2 = back
 	//side 3 = left
 	//side 4 = right
 	//side 5 = bottom
 	public Texture getTexture(int side){

 		if(ttop == null){
 			ttop = initBlockTexture(this.getUpTextureName());
 		}
 		if(tbottom == null){
 			tbottom = initBlockTexture(this.getDownTextureName());
 		}
 		if(tleft == null){
 			tleft = initBlockTexture(this.getWestTextureName());
 		}
 		if(tright == null){
 			tright = initBlockTexture(this.getEastTextureName());
 		}
 		if(tfront == null){
 			tfront = initBlockTexture(this.getNorthTextureName());
 		}
 		if(tback == null){
 			tback = initBlockTexture(this.getSouthTextureName());
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
 		if(side == 0)return this.getUpTextureName();
 		if(side == 5)return this.getDownTextureName();
 		if(side == 3)return this.getWestTextureName();
 		if(side == 4)return this.getEastTextureName();
 		if(side == 1)return this.getNorthTextureName();
 		return this.getSouthTextureName();
 	}

 

  @Override
  public FCBlock isSingleSided(boolean answer) {
	    holder().single_sided = answer;
	return this;  
  }
  
}
