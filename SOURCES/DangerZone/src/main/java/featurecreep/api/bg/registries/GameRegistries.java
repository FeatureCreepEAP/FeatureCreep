/**
 * 
 */
package featurecreep.api.bg.registries;

import dangerzone.biomes.Biome;
import dangerzone.blocks.Block;
import dangerzone.blocks.Blocks;
import dangerzone.items.Item;
import dangerzone.items.Items;
import dangerzone.world.Dimensions;

/**
 * @author rhel
 *
 */
public class GameRegistries {

	public static Item getItemFromGameRegistries(String registry_name) // We also need a Number ID version of this
	{
		return getItemFromGameRegistries(Items.findByName(registry_name));
	}

	public static Item getItemFromGameRegistries(int id) {
		return Items.getItem(id);
	}

	public static boolean ItemKeyExistsInRegistry(String registry_name) {
		boolean answer;

		for (int t = 0; t < Items.ItemArray.length; t++) {

			if (Items.ItemArray[t] != null) {
				if (Items.ItemArray[t].uniquename.equals(registry_name)) {
					return true;
				}
			}

		}

		return false;
	}

	public static Block getBlockFromGameRegistries(String registry_name) // We also need a Number ID version of this
	{
		return getBlockFromGameRegistries(Blocks.findByName(registry_name));
	}

	public static Block getBlockFromGameRegistries(int id) {
		return Blocks.getBlock(id);
	}

	public static boolean BlockKeyExistsInRegistry(String registry_name) {

		boolean answer;

		for (int t = 0; t < Blocks.BlockArray.length; t++) {

			if (Blocks.BlockArray[t] != null) {
				if (Blocks.BlockArray[t].uniquename.equals(registry_name)) {
					return true;
				}
			}

		}

		return false;

	}

	// DO NOT USE

	public static Object getItemGroupByName(String name) {
		return GlobalRegistries.getItemGroupByName(name);
	}

	public static Object getItemGroupByID(int id) {
		return GlobalRegistries.getItemGroupByID(id);
	}

	// Gotta soon make ItemGroup checkers

	// Will need to change some biome registries after we add biomes
	public static Biome getBiomeFromGameRegistries(String registry_name) // We also need a Number ID version of this
	{
		// DZ does biomes different, though i do eventually need to have a dimension
		// getter

		for (int d = 0; d < Dimensions.DimensionArray.length; d++) {

			for (int b = 0; b < Dimensions.DimensionArray[d].getBiomeManager().biomes.length; b++) {

				if (Dimensions.DimensionArray[d].getBiomeManager().biomes[b].uniquename.equals(registry_name)) {
					return Dimensions.DimensionArray[d].getBiomeManager().biomes[b];
				}

			}

		}

		return null;
	}

	// Should be avoided

	public static Biome getBiomeFromGameRegistries(int id) {

		for (int d = 0; d < Dimensions.DimensionArray.length; d++) {

			return Dimensions.DimensionArray[d].getBiomeManager().biomes[id];

		}

		return null;

	}

	public static boolean BiomeKeyExistsInRegistry(String registry_name) {

		// DZ does biomes different, though i do eventually need to have a dimension
		// getter

		for (int d = 0; d < Dimensions.DimensionArray.length; d++) {

			for (int b = 0; b < Dimensions.DimensionArray[d].getBiomeManager().biomes.length; b++) {

				if (Dimensions.DimensionArray[d].getBiomeManager().biomes[b].uniquename.equals(registry_name)) {
					return true;
				}

			}

		}

		return false;

	}

}