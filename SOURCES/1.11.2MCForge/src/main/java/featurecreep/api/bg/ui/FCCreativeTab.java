package featurecreep.api.bg.ui;

import featurecreep.api.bg.items.FCItem;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import game.CreativeTab;
import game.ItemStack;

public class FCCreativeTab extends CreativeTab implements UnifiedItemGroupGetter{

public String id;	
	
	
	public FCCreativeTab(int index, String id, FCItem item)
	{
super (index, id);
id=id;		


	}
	public FCCreativeTab()
	{
		super(0, null);
	}
	
	

	@Override
	public ItemStack createIcon() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CreativeTab get() {
		// TODO Auto-generated method stub
		return this;
	}
	
	
}