package featurecreep.api.bg.ui.tabs.vanilla;

import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;

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
return Registries.ITEM_GROUP.get(ItemGroups.BUILDING_BLOCKS);	
}
else if (groupname.tabname.equals("BREWING"))
{
	return Registries.ITEM_GROUP.get(ItemGroups.SPAWN_EGGS);	
}
else if (groupname.tabname.equals("COMBAT"))
{
	return Registries.ITEM_GROUP.get(ItemGroups.COMBAT);
}
else if (groupname.tabname.equals("DECORATIONS"))
{
	return Registries.ITEM_GROUP.get(ItemGroups.BUILDING_BLOCKS);
}
else if (groupname.tabname.equals("FOOD"))
{
	return Registries.ITEM_GROUP.get(ItemGroups.FOOD_AND_DRINK);
}
else if (groupname.tabname.equals("MATERIALS"))
{
	return Registries.ITEM_GROUP.get(ItemGroups.NATURAL);
}
else if (groupname.tabname.equals("MISC"))
{
	return Registries.ITEM_GROUP.get(ItemGroups.SPAWN_EGGS);
}
else if (groupname.tabname.equals("REDSTONE"))
{
	return Registries.ITEM_GROUP.get(ItemGroups.REDSTONE);
}
else if (groupname.tabname.equals("TOOLS"))
{
	return Registries.ITEM_GROUP.get(ItemGroups.TOOLS);
}
else if (groupname.tabname.equals("TRANSPORTATION"))
{
	return Registries.ITEM_GROUP.get(ItemGroups.FUNCTIONAL);
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