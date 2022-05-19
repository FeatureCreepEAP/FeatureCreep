package featurecreep.api.ui.tabs.vanilla;

import net.minecraft.item.ItemGroup;

public class VanillaCreativeTab  
{
	public String tabname;

	
public VanillaCreativeTab(String name) {
	tabname=name;
}

//Minecraft Only
public static ItemGroup getVanillaGroupFromString(VanillaCreativeTab groupname)
{
if (groupname.tabname == "BUILDING_BLOCKS")
{
return ItemGroup.BUILDING_BLOCKS;	
}
else if (groupname.tabname == "BREWING")
{
	return ItemGroup.BREWING;	
}
else if (groupname.tabname == "COMBAT")
{
	return ItemGroup.COMBAT;
}
else if (groupname.tabname == "DECORATIONS")
{
	return ItemGroup.DECORATIONS;
}
else if (groupname.tabname == "FOOD")
{
	return ItemGroup.FOOD;
}
else if (groupname.tabname == "MATERIALS")
{
	return ItemGroup.MATERIALS;
}
else if (groupname.tabname == "MISC")
{
	return ItemGroup.MISC;
}
else if (groupname.tabname == "REDSTONE")
{
	return ItemGroup.REDSTONE;
}
else if (groupname.tabname == "TOOLS")
{
	return ItemGroup.TOOLS;
}
else if (groupname.tabname == "TRANSPORTATION")
{
	return ItemGroup.TRANSPORTATION;
}else
{
	return null;
}





}



}
