package featurecreep.api.bg.ui.tabs.vanilla;

import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import game.CreativeTab;
import game.CreativeTabs;

public class VanillaCreativeTab  implements UnifiedItemGroupGetter
{
	public String tabname;
	
public VanillaCreativeTab(String name) {
	tabname=name;
	setTabName(getVanillaGroupFromString(this).getUnlocalisedName().getString()); //May not work, ideally tabname will be used for most //In Yarn the ID is the String name, kinda throws off considering here its the number
	setID(0);//TODO
}

//Minecraft Only gotta change the mappings for these once i figure them out
	public static CreativeTab getVanillaGroupFromString(VanillaCreativeTab groupname)
	{ // In vainilla the tabs are private
	if (groupname.tabname.equals("BUILDING_BLOCKS"))
	{
	return CreativeTabs.BUILDING_BLOCKS;	
	}
	else if (groupname.tabname.equals("BREWING"))
	{
		return CreativeTabs.MISULANIOUS;	
	}
	else if (groupname.tabname.equals("COMBAT"))
	{
		return CreativeTabs.COMBAT;
	}
	else if (groupname.tabname.equals("DECORATIONS"))
	{
		return CreativeTabs.BUILDING_BLOCKS;
	}
	else if (groupname.tabname.equals("FOOD"))
	{
		return CreativeTabs.FOODSTUFFS;
	}
	else if (groupname.tabname.equals("MATERIALS"))
	{
		return CreativeTabs.PLANTS;
	}
	else if (groupname.tabname.equals("MISC"))
	{
		return CreativeTabs.MISULANIOUS;
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
		return CreativeTabs.FUNCTION;
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