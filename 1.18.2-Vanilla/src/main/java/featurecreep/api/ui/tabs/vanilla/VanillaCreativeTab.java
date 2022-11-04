package featurecreep.api.ui.tabs.vanilla;

import net.minecraft.item.ItemGroup;

public class VanillaCreativeTab 
{
	public static ItemGroup VanillaTab;
	
	public VanillaCreativeTab(ItemGroup tab)
	{
		VanillaTab = tab;
		
	}
	public VanillaCreativeTab()
	{

	}
	

public static ItemGroup getVanillaTab()
{
	return VanillaTab;
}

}