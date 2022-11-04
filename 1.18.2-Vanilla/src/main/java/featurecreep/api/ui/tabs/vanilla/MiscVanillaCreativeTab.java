package featurecreep.api.ui.tabs.vanilla;

import net.minecraft.item.ItemGroup;

public class MiscVanillaCreativeTab
{
	public static ItemGroup VanillaTab;
	
	public MiscVanillaCreativeTab(ItemGroup tab)
	{
		VanillaTab = tab;
		
	}
	
public static ItemGroup getVanillaTab()
{
	return VanillaTab;
}

}