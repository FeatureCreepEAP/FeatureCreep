package featurecreep.api.bg.blocks;

import java.util.ArrayList;

import dangerzone.DangerZone;
import dangerzone.InventoryContainer;
import dangerzone.Player;
import dangerzone.blocks.Block;
import dangerzone.entities.EntityBlockItem;
import dangerzone.items.Item;
import dangerzone.rendering.StitchedTexture;
import featurecreep.api.bg.blocknitem.BlockOrItem;
import featurecreep.api.bg.blocks.drop.BlockDropArrayObject;
import featurecreep.api.bg.blocks.drop.BlockDropArrayObjects;
import featurecreep.api.bg.blocks.materials.UnifiedBlockMaterial;
import featurecreep.api.bg.entity.AbstractEntity;
import featurecreep.api.bg.items.vanilla.VanillaItem;
import featurecreep.api.bg.tooltypes.ToolTypes;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import featurecreep.api.bg.world.FCWorld;
import slick.Texture;

public class FCBlock extends Block implements FCBlockAPI<FCBlock> {

	public BlockFieldHolder holder = new BlockFieldHolder();

	@Override
	public BlockFieldHolder holder() {
		return holder;
	}

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

	public FCBlock(int id, String modid, String name, UnifiedItemGroupGetter group, UnifiedBlockMaterial material,
			int strength, BlockDropArrayObject[] drops) {
		super(modid + ":" + name, "");
		initialise(id, modid, name, group, material, strength, drops);

	}

	// server-side only
	public void onBlockBroken(Player p, int dimension, int x, int y, int z, int s) {
		super.onBlockBroken(p, dimension, x, y, z, s);
		if (p == null)
			return;
		if (DangerZone.rand.nextInt(10) != 1)
			return;

		ArrayList<BlockDropArrayObject> arr = getDrops(new VanillaItem(toStack(1).getItem(), getFCRegistryName()));
		System.out.println("Block Broken");
		for (int i = 0; i < arr.size(); i++) {
			System.out.println("Scanning Drops");

			BlockDropArrayObject loot = arr.get(i);

			if (loot instanceof featurecreep.api.bg.blocks.drop.SelfBlockDropArrayObject) {
				System.out.println("Dropping Self");
				executeDropItem(new FCWorld(p.world), new FCBlockPos(x, y, z), this);// for a sec i thought i had to
																						// declare a whole new vanila
																						// item, turns out not
			} else {
				for (int t = 0; t < loot.drop.size(); t++) {
					System.out.println("Right tool used");
					if (loot.drop.get(t) instanceof BlockOrItem) {
						executeDropItem(new FCWorld(p.world), new FCBlockPos(x, y, z), (BlockOrItem) loot.drop.get(t));
						// Block.dropStacks(state, world, pos, blockEntity, player, new
						// ItemStack((Block) getDropArrayObjects()[i].drop.get(t)));
					} else if (loot.drop.get(t) instanceof AbstractEntity) {

					}

				}
			}

		}

	}

	// Just like MC i copied Workbench settings

	// Just like MC i copied Workbench settings

	// side 0 = top
	// side 1 = front
	// side 2 = back
	// side 3 = left
	// side 4 = right
	// side 5 = bottom
	public Texture getTexture(int side) {

		if (ttop == null) {
			ttop = initBlockTexture(this.getUpTextureName());
		}
		if (tbottom == null) {
			tbottom = initBlockTexture(this.getDownTextureName());
		}
		if (tleft == null) {
			tleft = initBlockTexture(this.getWestTextureName());
		}
		if (tright == null) {
			tright = initBlockTexture(this.getEastTextureName());
		}
		if (tfront == null) {
			tfront = initBlockTexture(this.getNorthTextureName());
		}
		if (tback == null) {
			tback = initBlockTexture(this.getSouthTextureName());
		}

		if (side == 0)
			return ttop;
		if (side == 5)
			return tbottom;
		if (side == 3)
			return tleft;
		if (side == 4)
			return tright;
		if (side == 1)
			return tfront;
		if (side == 2)
			return tback;
		return null;
	}

	public StitchedTexture getStitchedTexture(int side) {
		if (side == 0)
			return sttop;
		if (side == 5)
			return stbottom;
		if (side == 3)
			return stleft;
		if (side == 4)
			return stright;
		if (side == 1)
			return stfront;
		return stback;
	}

	public String getStitchedTextureName(int side) {
		if (side == 0)
			return this.getUpTextureName();
		if (side == 5)
			return this.getDownTextureName();
		if (side == 3)
			return this.getWestTextureName();
		if (side == 4)
			return this.getEastTextureName();
		if (side == 1)
			return this.getNorthTextureName();
		return this.getSouthTextureName();
	}

	@Override
	public FCBlock isSingleSided(boolean answer) {
		holder().single_sided = answer;
		return this;
	}

}
