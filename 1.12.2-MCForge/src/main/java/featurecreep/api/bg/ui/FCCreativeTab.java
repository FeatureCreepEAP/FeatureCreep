package featurecreep.api.bg.ui;

import featurecreep.api.bg.items.FCItem;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class FCCreativeTab extends CreativeTabs implements UnifiedItemGroupGetter{

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
	public ItemStack getTabIconItem() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CreativeTabs get() {
		// TODO Auto-generated method stub
		return this;
	}
	
	
}