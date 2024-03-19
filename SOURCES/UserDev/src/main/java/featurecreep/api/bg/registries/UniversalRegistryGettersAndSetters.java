package featurecreep.api.bg.registries;

import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import featurecreep.api.bg.ui.tabs.vanilla.VanillaCreativeTab;

public class UniversalRegistryGettersAndSetters {

	// public static Item getItembyName(String registry_name){}

	// public static Item getItembyID(int id) {}

	public static FCItemAPI getFCItembyName(String registry_name) {
		return null; // Userdev does not need to return anything , this should work in production
						// though
	}

	public static FCItemAPI getFCItembyID(int id) {
		return null; // Userdev does not need to return anything , this should work in production
						// though
	}

	// public static void registerItem(Item item, String registry_name, Object
	// default_tab, int id)

	public static void registerItem(FCItemAPI item) {
		// Userdev does not need to return anything
	}

	// Do not use the Itemgroup getters
//	public static Object getItemGroupByName(String registry_name)	{}

//	public static Object getItemGroupbyID(int id)	{}

	public static UnifiedItemGroupGetter getFCItemGroupbyName(String registry_name) {
		if (GlobalRegistries.getItemGroupByName(registry_name) != null) {
			return GlobalRegistries.getItemGroupByName(registry_name);
		} else {
			return new VanillaCreativeTab(registry_name);
		}

	}

	public static UnifiedItemGroupGetter getFCItemGroupbyID(int id) {
		return null; // Userdev does not need to return anything , this should work in production
						// though

	}

	public static void registerItemGroup(Object tab, String registry_name, int id) {

	}

	public static void registerItemGroup(UnifiedItemGroupGetter tab) {
//Userdev does not need to return anything	, this should work in production though
	}

	// public static Block getBlockbyName(String registry_name){}

	// public static Block getBlockbyID(int id) {}

	public static FCBlockAPI getFCBlockbyName(String registry_name) {
		return null; // Userdev does not need to return anything , this should work in production
						// though
	}

	public static FCBlockAPI getFCBlockbyID(int id) {
		return null; // Userdev does not need to return anything , this should work in production
						// though
	}

//	public static void registerBlock(Block block, String registry_name, Object default_tab, int id)	{	}

	public static void registerBlock(FCBlockAPI block) {
		// Userdev does not need to return anything , this should work in production
		// though
	}

}
