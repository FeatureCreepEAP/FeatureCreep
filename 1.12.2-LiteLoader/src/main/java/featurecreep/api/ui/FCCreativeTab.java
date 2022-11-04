package featurecreep.api.ui;

import featurecreep.api.items.FCItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class FCCreativeTab extends CreativeTabs{

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
	
	
}