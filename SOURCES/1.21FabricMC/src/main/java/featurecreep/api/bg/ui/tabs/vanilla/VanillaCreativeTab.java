package featurecreep.api.bg.ui.tabs.vanilla;

import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

public class VanillaCreativeTab implements UnifiedItemGroupGetter {
	public String tabname;

	public VanillaCreativeTab(String name) {
		tabname = name;
		setTabName(getVanillaGroupFromString(this).getDisplayName().getString()); // May not work, ideally tabname
																						// will be used for most //In
																						// Yarn the ID is the String
																						// name, kinda throws off
																						// considering here its the
																						// number
		setID(0);// TODO
	}

//Minecraft Only gotta change the mappings for these once i figure them out
	public static CreativeModeTab getVanillaGroupFromString(VanillaCreativeTab groupname) {
	    String tabId = null;

	    switch (groupname.tabname) {
	        case "BUILDING_BLOCKS":
	            tabId = "building_blocks";
	            break;
	        case "DECORATIONS":
	            tabId = "functional_blocks"; // best match
	            break;
	        case "COMBAT":
	            tabId = "combat";
	            break;
	        case "FOOD":
	            tabId = "food_and_drinks";
	            break;
	        case "TOOLS":
	            tabId = "tools_and_utilities";
	            break;
	        case "REDSTONE":
	            tabId = "redstone_blocks";
	            break;
	        case "MATERIALS":
	        case "BREWING":
	        case "MISC":
	            tabId = "ingredients";
	            break;
	        case "TRANSPORTATION":
	            tabId = "functional_blocks";
	            break;
	        default:
	            return null;
	    }

	    ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath("minecraft", tabId));
	    return BuiltInRegistries.CREATIVE_MODE_TAB.get(key);
	}

	@Override
	public CreativeModeTab get() {
		// TODO Auto-generated method stub
		return getVanillaGroupFromString(this);
	}

}