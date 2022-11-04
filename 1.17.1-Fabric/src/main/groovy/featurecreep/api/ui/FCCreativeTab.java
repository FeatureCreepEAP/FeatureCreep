package featurecreep.api.ui;

import featurecreep.api.items.FCItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class FCCreativeTab extends ItemGroup{

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
	
	
}