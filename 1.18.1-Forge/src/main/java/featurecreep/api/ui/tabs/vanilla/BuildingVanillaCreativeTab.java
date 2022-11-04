package featurecreep.api.ui.tabs.vanilla;

import net.minecraft.item.ItemGroup;

public class BuildingVanillaCreativeTab
{
	public static ItemGroup VanillaTab;
	
	public BuildingVanillaCreativeTab(ItemGroup tab)
	{
		VanillaTab = tab;
		
	}

public static ItemGroup getVanillaTab()
{
	return VanillaTab;
}

}