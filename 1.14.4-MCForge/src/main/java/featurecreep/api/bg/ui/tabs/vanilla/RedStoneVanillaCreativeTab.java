package featurecreep.api.bg.ui.tabs.vanilla;

import net.minecraft.item.ItemGroup;

public class RedStoneVanillaCreativeTab
{
	public static ItemGroup VanillaTab;
	
	public RedStoneVanillaCreativeTab(ItemGroup tab)
	{
		VanillaTab = tab;
		
	}
	
public static ItemGroup getVanillaTab()
{
	return VanillaTab;
}

}