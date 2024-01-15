package featurecreep.api.bg.items.projectile;

import dangerzone.items.ItemBowEmpty;
import featurecreep.FeatureCreep;
import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;

public class FCBow extends ItemBowEmpty  implements FCItemAPI<FCBow>{

	

public featurecreep.api.bg.items.ItemFieldHolder holder = new featurecreep.api.bg.items.ItemFieldHolder();
@Override public featurecreep.api.bg.items.ItemFieldHolder holder() {	return holder;	}

	
public FCBow(int id, String modid, String name, UnifiedItemGroupGetter group)
{
	super(modid + ":" + name, FeatureCreep.gamepath+"/resourcepacks/fcpack_8/assets/" + modid + "/textures/items/" + name + ".png", 200);
initialise(id, modid, name, group);


}




}
