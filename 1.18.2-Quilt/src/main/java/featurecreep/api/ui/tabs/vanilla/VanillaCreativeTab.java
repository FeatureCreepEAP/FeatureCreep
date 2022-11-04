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
if (groupname.tabname.equals("BUILDING_BLOCKS"))
{
return ItemGroup.BUILDING_BLOCKS;	
}
else if (groupname.tabname.equals("BREWING"))
{
	return ItemGroup.BREWING;	
}
else if (groupname.tabname.equals("COMBAT"))
{
	return ItemGroup.COMBAT;
}
else if (groupname.tabname.equals("DECORATIONS"))
{
	return ItemGroup.DECORATIONS;
}
else if (groupname.tabname.equals("FOOD"))
{
	return ItemGroup.FOOD;
}
else if (groupname.tabname.equals("MATERIALS"))
{
	return ItemGroup.MATERIALS;
}
else if (groupname.tabname.equals("MISC"))
{
	return ItemGroup.MISC;
}
else if (groupname.tabname.equals("REDSTONE"))
{
	return ItemGroup.REDSTONE;
}
else if (groupname.tabname.equals("TOOLS"))
{
	return ItemGroup.TOOLS;
}
else if (groupname.tabname.equals("TRANSPORTATION"))
{
	return ItemGroup.TRANSPORTATION;
}else
{
	return null;
}





}



}