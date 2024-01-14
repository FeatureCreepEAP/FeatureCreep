package featurecreep.api.bg.ui.tabs.vanilla;

import game.CreativeTab;

public class MiscVanillaCreativeTab
{
	public static CreativeTab VanillaTab;
	
	public MiscVanillaCreativeTab(CreativeTab tab)
	{
		VanillaTab = tab;
		
	}
	
public static CreativeTab getVanillaTab()
{
	return VanillaTab;
}

}