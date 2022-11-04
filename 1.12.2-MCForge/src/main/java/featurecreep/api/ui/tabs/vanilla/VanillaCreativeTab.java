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
if (groupname.tabname.equals("BUILDING_BLOCKS"))
{
return CreativeTabs.BUILDING_BLOCKS;	
}
else if (groupname.tabname.equals("BREWING"))
{
	return CreativeTabs.BREWING;	
}
else if (groupname.tabname.equals("COMBAT"))
{
	return CreativeTabs.COMBAT;
}
else if (groupname.tabname.equals("DECORATIONS"))
{
	return CreativeTabs.DECORATIONS;
}
else if (groupname.tabname.equals("FOOD"))
{
	return CreativeTabs.FOOD;
}
else if (groupname.tabname.equals("MATERIALS"))
{
	return CreativeTabs.MATERIALS;
}
else if (groupname.tabname.equals("MISC"))
{
	return CreativeTabs.MISC;
}
else if (groupname.tabname.equals("REDSTONE"))
{
	return CreativeTabs.REDSTONE;
}
else if (groupname.tabname.equals("TOOLS"))
{
	return CreativeTabs.TOOLS;
}
else if (groupname.tabname.equals("TRANSPORTATION"))
{
	return CreativeTabs.TRANSPORTATION;
}else
{
	return null;
}





}



}