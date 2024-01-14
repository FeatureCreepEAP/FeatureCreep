package featurecreep.api.bg.ui.tabs.vanilla;

import game.CreativeTab;

public class CombatVanillaCreativeTab
{
	static CreativeTab VanillaTab;
	
	public CombatVanillaCreativeTab(CreativeTab tab)
	{
		VanillaTab = tab;
		
	}

public static CreativeTab getVanillaTab()
{
	return VanillaTab;
}

}