package featurecreep.api.ui.tabs.vanilla;

import net.minecraft.item.ItemGroup;

public class CombatVanillaCreativeTab
{
	static ItemGroup VanillaTab;
	
	public CombatVanillaCreativeTab(ItemGroup tab)
	{
		VanillaTab = tab;
		
	}

public static ItemGroup getVanillaTab()
{
	return VanillaTab;
}

}