package featurecreep.api.bg.ui.tabs.vanilla;

import net.minecraft.item.ItemGroup;

public class MaterialsVanillaCreativeTab
{
	public static ItemGroup VanillaTab;
	
	public MaterialsVanillaCreativeTab(ItemGroup tab)
	{
		VanillaTab = tab;
		
	}

public static ItemGroup getVanillaTab()
{
	return VanillaTab;
}

}