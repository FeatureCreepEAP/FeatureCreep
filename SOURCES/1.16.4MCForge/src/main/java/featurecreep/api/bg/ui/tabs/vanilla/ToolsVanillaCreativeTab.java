package featurecreep.api.bg.ui.tabs.vanilla;

import game.CreativeTab;

public class ToolsVanillaCreativeTab
{
	public static CreativeTab VanillaTab;
	
	public ToolsVanillaCreativeTab(CreativeTab tab)
	{
		VanillaTab = tab;
		
	}
	
public static CreativeTab getVanillaTab()
{
	return VanillaTab;
}

}