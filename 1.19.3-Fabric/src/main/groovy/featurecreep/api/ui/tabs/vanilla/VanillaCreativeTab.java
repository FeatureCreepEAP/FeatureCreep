package featurecreep.api.ui.tabs.vanilla;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;

public class VanillaCreativeTab  
{
	public String tabname;
	
public VanillaCreativeTab(String name) {
	tabname=name;
}

//Minecraft Only gotta change the mappings for these once i figure them out
public static ItemGroup getVanillaGroupFromString(VanillaCreativeTab groupname)
{
if (groupname.tabname.equals("BUILDING_BLOCKS"))
{
return ItemGroups.BUILDING_BLOCKS;	
}
else if (groupname.tabname.equals("BREWING"))
{
	return ItemGroups.SPAWN_EGGS;	
}
else if (groupname.tabname.equals("COMBAT"))
{
	return ItemGroups.COMBAT;
}
else if (groupname.tabname.equals("DECORATIONS"))
{
	return ItemGroups.BUILDING_BLOCKS;
}
else if (groupname.tabname.equals("FOOD"))
{
	return ItemGroups.CONSUMABLES;
}
else if (groupname.tabname.equals("MATERIALS"))
{
	return ItemGroups.NATURAL;
}
else if (groupname.tabname.equals("MISC"))
{
	return ItemGroups.SPAWN_EGGS;
}
else if (groupname.tabname.equals("REDSTONE"))
{
	return ItemGroups.REDSTONE;
}
else if (groupname.tabname.equals("TOOLS"))
{
	return ItemGroups.TOOLS;
}
else if (groupname.tabname.equals("TRANSPORTATION"))
{
	return ItemGroups.FUNCTIONAL;
}else
{
	return null;
}





}



}