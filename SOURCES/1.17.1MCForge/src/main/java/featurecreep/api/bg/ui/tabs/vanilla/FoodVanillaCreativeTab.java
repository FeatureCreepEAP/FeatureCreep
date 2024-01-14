package featurecreep.api.bg.ui.tabs.vanilla;

import game.CreativeTab;

public class FoodVanillaCreativeTab
{
	public static CreativeTab VanillaTab;
	
	public FoodVanillaCreativeTab(CreativeTab tab)
	{
		VanillaTab = tab;
		
	}

public static CreativeTab getVanillaTab()
{
	return VanillaTab;
}

}