package dangerzone.items;

import dangerzone.InventoryContainer;
import dangerzone.Recipe;
import dangerzone.blocks.Block;
import dangerzone.entities.Entity;

public class ReturnRecipe extends Recipe {
	
	Object ofind = null;
	Object oreplace = null;
	
	public  ReturnRecipe( Object i1, Object i2, Object i3,
			Object i4, Object i5, Object i6,
			Object i7, Object i8, Object i9,
			Object outthing, int count, boolean ordrd, Object tthis, Object tthat){
		super(i1, i2, i3, i4, i5, i6, i7, i8, i9, outthing, count, ordrd);
		ofind = tthis;
		oreplace = tthat;
	}
	
	public void finishCrafting(Entity e){
		InventoryContainer ic;
		int[] saver = new int[9];
		//find and save
		for(int i=0;i<9;i++){
			 ic = e.getCrafting(i);
			 saver[i] = 0;
			if(ic != null && (ofind == ic.getItem() || ofind == ic.getBlock())){						 				 
				saver[i] = 1;
			}
		}
		
		//delete one of everything
		super.finishCrafting(e);
		
		//replace if gone
		for(int i=0;i<9;i++){
			ic = e.getCrafting(i);
			if(saver[i] != 0 && ic == null){	
				if(oreplace instanceof Item){
					ic = new InventoryContainer(0, ((Item) oreplace).itemID, 1, 0);
				}
				if(oreplace instanceof Block){
					ic = new InventoryContainer(((Block) oreplace).blockID, 0, 1, 0);
				}
				e.setCrafting(i, ic);				
				e.setCraftingChanged(i);
			}
		}
	}

}
