package featurecreep.api.bg.ui.tabs.vanilla;

import game.CreativeTab;

public class MaterialsVanillaCreativeTab
{
	public static CreativeTab VanillaTab;
	
	public MaterialsVanillaCreativeTab(CreativeTab tab)
	{
		VanillaTab = tab;
		
	}

public static CreativeTab getVanillaTab()
{
	return VanillaTab;
}

}