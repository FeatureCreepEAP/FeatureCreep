package featurecreep.api.bg.items;

import dangerzone.items.Item;
import featurecreep.FeatureCreep;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;

public class FCItem extends Item implements FCItemAPI<FCItem>
{
		public ItemFieldHolder holder = new ItemFieldHolder();
	@Override public ItemFieldHolder holder() {	return holder;	}
           
	public FCItem(int id, String modid, String name, UnifiedItemGroupGetter group)
	{
		super(modid + ":" + name, FeatureCreep.gamepath+"/resourcepacks/fcpack_8/assets/" + modid + "/textures/items/" + name + ".png");
	initialise(id, modid, name, group);
	}


	
}
