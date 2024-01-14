package featurecreep.api.bg.ui.tabs.vanilla;

import game.CreativeTab;

public class DecorVanillaCreativeTab
{
	public static CreativeTab VanillaTab;
	
	public DecorVanillaCreativeTab(CreativeTab tab)
	{
		VanillaTab = tab;
		
	}

public static CreativeTab getVanillaTab()
{
	return VanillaTab;
}

}