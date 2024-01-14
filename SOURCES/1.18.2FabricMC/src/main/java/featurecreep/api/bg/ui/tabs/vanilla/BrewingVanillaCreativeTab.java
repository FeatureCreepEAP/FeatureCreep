package featurecreep.api.bg.ui.tabs.vanilla;

import game.CreativeTab;

public class BrewingVanillaCreativeTab
{
	public static CreativeTab VanillaTab;
	
	public BrewingVanillaCreativeTab(CreativeTab tab)
	{
		VanillaTab = tab;
		
	}

	

public static CreativeTab getVanillaTab()
{
	return VanillaTab;
}

}