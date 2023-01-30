package featurecreep.api.bg.ui.tabs.vanilla;

import net.minecraft.item.ItemGroup;

public class BrewingVanillaCreativeTab
{
	public static ItemGroup VanillaTab;
	
	public BrewingVanillaCreativeTab(ItemGroup tab)
	{
		VanillaTab = tab;
		
	}

	

public static ItemGroup getVanillaTab()
{
	return VanillaTab;
}

}