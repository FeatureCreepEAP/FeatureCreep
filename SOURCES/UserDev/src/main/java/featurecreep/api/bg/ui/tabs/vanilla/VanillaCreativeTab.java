package featurecreep.api.bg.ui.tabs.vanilla;

import featurecreep.api.bg.ui.FCCreativeTab;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;

public class VanillaCreativeTab implements UnifiedItemGroupGetter {
	public String tabname; // Forgot this existed when making ItemGroup Holder, but after looking at it is
							// may be good to keep as an intermediary so will be used in Global Registries

	public VanillaCreativeTab(String name) {
		tabname = name;
		setTabName(name);
		setID(0);
	}

	public static FCCreativeTab getVanillaGroupFromString(VanillaCreativeTab groupname) {
		if (groupname.tabname == "BUILDING_BLOCKS") {
//return ItemGroup.BUILDING_BLOCKS;	
			return null;
		} else if (groupname.tabname == "BREWING") {
//	return ItemGroup.BREWING;	
			return null;
		} else if (groupname.tabname == "COMBAT") {
//	return ItemGroup.COMBAT;
			return null;
		} else if (groupname.tabname == "DECORATIONS") {
//	return ItemGroup.DECORATIONS;
			return null;
		} else if (groupname.tabname == "FOOD") {
//	return ItemGroup.FOOD;
			return null;
		} else if (groupname.tabname == "MATERIALS") {
//	return ItemGroup.MATERIALS;
			return null;
		} else if (groupname.tabname == "MISC") {
//	return ItemGroup.MISC;
			return null;
		} else if (groupname.tabname == "REDSTONE") {
//	return ItemGroup.REDSTONE;
			return null;
		} else if (groupname.tabname == "TOOLS") {
//	return ItemGroup.TOOLS;
			return null;
		} else if (groupname.tabname == "TRANSPORTATION") {
			// return ItemGroup.TRANSPORTATION;
			return null;
		} else {
			return null;
		}

	}

}
