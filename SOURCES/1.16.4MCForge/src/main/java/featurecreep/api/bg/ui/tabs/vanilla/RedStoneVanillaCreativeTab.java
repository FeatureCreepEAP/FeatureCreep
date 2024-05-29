package featurecreep.api.bg.ui.tabs.vanilla;

import game.CreativeTab;

public class RedStoneVanillaCreativeTab
{
	public static CreativeTab VanillaTab;
	
	public RedStoneVanillaCreativeTab(CreativeTab tab)
	{
		VanillaTab = tab;
		
	}
	
public static CreativeTab getVanillaTab()
{
	return VanillaTab;
}

}