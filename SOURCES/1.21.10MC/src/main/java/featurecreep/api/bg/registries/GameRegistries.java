/**
 * 
 */
package featurecreep.api.bg.registries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

/**
 * @author rhel
 *
 */
public class GameRegistries {

	// Soon I gotta mark sortcuts to the actual registries

	public static Item getItemFromGameRegistries(String registry_name)// We also need a Number ID version of this
	{
		return BuiltInRegistries.ITEM.get(ResourceLocation.parse(registry_name)).get().value();
	}

	public static Item getItemFromGameRegistries(int id) {
		return Item.byId(id);
	}

	public static boolean ItemKeyExistsInRegistry(String registry_name) {
		return BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(registry_name));
	}

	public static Block getBlockFromGameRegistries(String registry_name)// We also need a Number ID version of this
	{
		return BuiltInRegistries.BLOCK.get(ResourceLocation.parse(registry_name)).get().value();
	}

	public static Block getBlockFromGameRegistries(int id) {
		return BuiltInRegistries.BLOCK.byId(id);// May not work
	}

	public static boolean BlockKeyExistsInRegistry(String registry_name) {
		return BuiltInRegistries.BLOCK.containsKey(ResourceLocation.parse(registry_name));
	}

	public static CreativeModeTab getItemGroupByName(String name) {
		for (int t = 0; t < CreativeModeTabs.allTabs().size(); t++) {

			if (CreativeModeTabs.allTabs().get(t).getDisplayName().getString().equals(name)) {
				return CreativeModeTabs.allTabs().get(t);
			}

		}

		return null;
	}

	public static CreativeModeTab getItemGroupByID(int id) {
		for (int t = 0; t < CreativeModeTabs.allTabs().size(); t++) {

			// if (ItemGroups.getGroups().get(t).getIndex() == id) {
			// return ItemGroups.getGroups().get(t);
			// }

		}

		return null;
	}
	// Gotta soon make ItemGroup checkers

	// Will need to change some biome registries after we add biomes
	public static Biome getBiomeFromGameRegistries(String registry_name)// We also need a Number ID version of this
	{

		return null; // get(new Identifier(registry_name));
	}

	public static Biome getBiomeFromGameRegistries(int id) {
		return null;
	}

	public static boolean BiomeKeyExistsInRegistry(String registry_name) {
		return false;
	}

}