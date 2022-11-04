package featurecreep.api.ui.tabs.vanilla;

import net.minecraft.creativetab.CreativeTabs;

public class VanillaCreativeTab  
{
	public String tabname;
	
public VanillaCreativeTab(String name) {
	tabname=name;
}

//Minecraft Only
public static CreativeTabs getVanillaGroupFromString(VanillaCreativeTab groupname)
{
if (groupname.tabname == "BUILDING_BLOCKS")
{
return CreativeTabs.BUILDING_BLOCKS;	
}
else if (groupname.tabname == "BREWING")
{
	return CreativeTabs.BREWING;	
}
else if (groupname.tabname == "COMBAT")
{
	return CreativeTabs.COMBAT;
}
else if (groupname.tabname == "DECORATIONS")
{
	return CreativeTabs.DECORATIONS;
}
else if (groupname.tabname == "FOOD")
{
	return CreativeTabs.FOOD;
}
else if (groupname.tabname == "MATERIALS")
{
	return CreativeTabs.MATERIALS;
}
else if (groupname.tabname == "MISC")
{
	return CreativeTabs.MISC;
}
else if (groupname.tabname == "REDSTONE")
{
	return CreativeTabs.REDSTONE;
}
else if (groupname.tabname == "TOOLS")
{
	return CreativeTabs.TOOLS;
}
else if (groupname.tabname == "TRANSPORTATION")
{
	return CreativeTabs.TRANSPORTATION;
}else
{
	return null;
}





}



}