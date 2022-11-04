package featurecreep.api.ui.tabs.vanilla;

import net.minecraft.item.ItemGroup;

public class ToolsVanillaCreativeTab
{
	public static ItemGroup VanillaTab;
	
	public ToolsVanillaCreativeTab(ItemGroup tab)
	{
		VanillaTab = tab;
		
	}
	
public static ItemGroup getVanillaTab()
{
	return VanillaTab;
}

}