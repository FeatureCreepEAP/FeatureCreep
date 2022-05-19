package dangerzone.items;

import dangerzone.gui.InventoryMenus;

public class ItemBullets extends Item {

	public ItemBullets(String n, String txt) {
		super(n, txt);
		burntime = 5;
		menu = InventoryMenus.HARDWARE;
	}

}
