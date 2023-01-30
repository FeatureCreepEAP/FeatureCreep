package featurecreep.api.bg.ui.tabs.vanilla;

import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.item.ItemGroup;

public class VanillaCreativeTab  implements UnifiedItemGroupGetter
{
	public String tabname;
	
public VanillaCreativeTab(String name) {
	tabname=name;
	setTabName(getVanillaGroupFromString(this).getId()); //In Yarn the ID is the String name, kinda throws off considering here its the number
	setID(getVanillaGroupFromString(this).getIndex());
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

@Override
public ItemGroup get() {
	// TODO Auto-generated method stub
	return getVanillaGroupFromString(this);
}



}