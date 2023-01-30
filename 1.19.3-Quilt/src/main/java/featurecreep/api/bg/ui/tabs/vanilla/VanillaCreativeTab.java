package featurecreep.api.bg.ui.tabs.vanilla;

import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;

public class VanillaCreativeTab  implements UnifiedItemGroupGetter
{
	public String tabname;
	
public VanillaCreativeTab(String name) {
	tabname=name;
	setTabName(getVanillaGroupFromString(this).getDisplayName().getString()); //May not work, ideally tabname will be used for most //In Yarn the ID is the String name, kinda throws off considering here its the number
	setID(0);//TODO
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
	return ItemGroups.FOOD_AND_DRINK;
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

@Override
public ItemGroup get() {
	// TODO Auto-generated method stub
	return getVanillaGroupFromString(this);
}



}