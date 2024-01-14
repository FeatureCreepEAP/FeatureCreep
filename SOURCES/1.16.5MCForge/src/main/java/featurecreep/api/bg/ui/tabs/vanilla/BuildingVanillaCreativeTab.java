package featurecreep.api.bg.ui.tabs.vanilla;

import game.CreativeTab;

public class BuildingVanillaCreativeTab
{
	public static CreativeTab VanillaTab;
	
	public BuildingVanillaCreativeTab(CreativeTab tab)
	{
		VanillaTab = tab;
		
	}

public static CreativeTab getVanillaTab()
{
	return VanillaTab;
}

}