package featurecreep.api.bg.ui.tabs.vanilla;

import net.minecraft.item.ItemGroup;

public class DecorVanillaCreativeTab
{
	public static ItemGroup VanillaTab;
	
	public DecorVanillaCreativeTab(ItemGroup tab)
	{
		VanillaTab = tab;
		
	}

public static ItemGroup getVanillaTab()
{
	return VanillaTab;
}

}