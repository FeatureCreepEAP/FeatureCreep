package featurecreep.api.bg.items;

import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import game.Item;

public class FCItem extends Item implements FCItemAPI<FCItem>
{
	
		public ItemFieldHolder holder = new ItemFieldHolder();
	@Override public ItemFieldHolder holder() {	return holder;	}
	
	
	
	public FCItem(int id, String modid, String name, UnifiedItemGroupGetter group)
	{
		super(new Item.Info().setCreativeTab(group.get()));
initialise(id, modid, name, group);
	}


	
	
	
	
}

