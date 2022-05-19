package dangerzone.items;

import dangerzone.InventoryContainer;
import dangerzone.entities.Entity;

public class ItemSemiPistol extends ItemPistol{

	public ItemSemiPistol(String n, String txt, int att, int uses) {
		super(n, txt, att, uses);
	}
	
	public boolean singleshot(Entity holder, InventoryContainer ic, int holdcount){
		return false;
	}
	
	public boolean semiauto(Entity holder, InventoryContainer ic, int holdcount){
		if(holder == null)return false;
		if(ic == null)return false;
		ic.tmpi = 10; //start animation!
		if(!holder.world.isServer)return false; //only server fom here down		
		if(this.maxuses-ic.currentuses <= 1)return false; //EMPTY!!!
		return shoot_a_shot(holder, ic, holdcount);
	}

}
