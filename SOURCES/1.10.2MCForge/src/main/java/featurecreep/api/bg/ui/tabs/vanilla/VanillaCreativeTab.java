package featurecreep.api.bg.ui.tabs.vanilla;

import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import game.CreativeTab;

public class VanillaCreativeTab  implements UnifiedItemGroupGetter
{
	public String tabname;
	
public VanillaCreativeTab(String name) {
	tabname=name;
	setTabName(getVanillaGroupFromString(this).getId());
	setID(getVanillaGroupFromString(this).getIndex());
}

//Minecraft Only
public static CreativeTab getVanillaGroupFromString(VanillaCreativeTab groupname)
{
if (groupname.tabname.equals("BUILDING_BLOCKS"))
{
return CreativeTab.BUILDING_BLOCKS;	
}
else if (groupname.tabname.equals("BREWING"))
{
	return CreativeTab.BREWING;	
}
else if (groupname.tabname.equals("COMBAT"))
{
	return CreativeTab.COMBAT;
}
else if (groupname.tabname.equals("DECORATIONS"))
{
	return CreativeTab.DECORATIONS;
}
else if (groupname.tabname.equals("FOOD"))
{
	return CreativeTab.FOOD;
}
else if (groupname.tabname.equals("MATERIALS"))
{
	return CreativeTab.MATERIALS;
}
else if (groupname.tabname.equals("MISC"))
{
	return CreativeTab.MISC;
}
else if (groupname.tabname.equals("REDSTONE"))
{
	return CreativeTab.REDSTONE;
}
else if (groupname.tabname.equals("TOOLS"))
{
	return CreativeTab.TOOLS;
}
else if (groupname.tabname.equals("TRANSPORTATION"))
{
	return CreativeTab.TRANSPORTATION;
}else
{
	return null;
}





}

@Override
public CreativeTab get() {
	// TODO Auto-generated method stub
	return getVanillaGroupFromString(this);
}



}