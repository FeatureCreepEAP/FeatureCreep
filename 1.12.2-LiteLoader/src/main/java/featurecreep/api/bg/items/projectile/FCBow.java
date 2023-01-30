package featurecreep.api.bg.items.projectile;

import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;

public class FCBow extends ItemBow  implements FCItemAPI<FCBow>{

	

public featurecreep.api.bg.items.ItemFieldHolder holder = new featurecreep.api.bg.items.ItemFieldHolder();
@Override public featurecreep.api.bg.items.ItemFieldHolder holder() {	return holder;	}

	
public FCBow(int id, String modid, String name, UnifiedItemGroupGetter group)
{
	super();
	initialise(id, modid, name, group);


}




}
