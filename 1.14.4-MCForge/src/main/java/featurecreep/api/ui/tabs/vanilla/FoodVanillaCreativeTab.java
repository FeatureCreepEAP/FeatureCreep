package featurecreep.api.ui.tabs.vanilla;

import net.minecraft.item.ItemGroup;

public class FoodVanillaCreativeTab
{
	public static ItemGroup VanillaTab;
	
	public FoodVanillaCreativeTab(ItemGroup tab)
	{
		VanillaTab = tab;
		
	}

public static ItemGroup getVanillaTab()
{
	return VanillaTab;
}

}