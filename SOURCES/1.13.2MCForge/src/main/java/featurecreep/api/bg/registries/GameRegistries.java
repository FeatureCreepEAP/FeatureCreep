/**
 * 
 */
package featurecreep.api.bg.registries;

import game.Biome;
import game.Block;
import game.CreativeTab;
import game.Item;
import game.ResourceLocation;

/**
 * @author rhel
 *
 */
public class GameRegistries {

	//Soon I gotta mark sortcuts to the actual registries
	
	public static Item getItemFromGameRegistries(String registry_name)//We also need a Number ID version of this
	{
		return game.GameRegistries.var_unknown_73807.get(new ResourceLocation(registry_name));
	}
	
	public static Item getItemFromGameRegistries(int id)
	{
		return Item.byID(id);
	}

	public static boolean ItemKeyExistsInRegistry(String registry_name)
	{
		return game.GameRegistries.var_unknown_73807.containsRL(new ResourceLocation(registry_name));
	}
	
	public static Block getBlockFromGameRegistries(String registry_name)//We also need a Number ID version of this
	{
		return game.GameRegistries.BLOCKS.get(new ResourceLocation(registry_name));
	}
	
	public static Block getBlockFromGameRegistries(int id)
	{
		return game.GameRegistries.BLOCKS.def_unknown_84722(id);//May not work
	}
	
	public static boolean BlockKeyExistsInRegistry(String registry_name)
	{
		return game.GameRegistries.BLOCKS.containsRL(new ResourceLocation(registry_name));
	}
	
	
	public static CreativeTab getItemGroupByName(String name)
	{
		for (int t = 0; t < CreativeTab.field_78032_a.length; t++) {
	
			
			if (CreativeTab.field_78032_a[t].getId().equals(name)) {
				return CreativeTab.field_78032_a[t];
			}
	
	
		}
		
		return null;
	}
	
	public static CreativeTab getItemGroupByID(int id)
	{
		for (int t = 0; t < CreativeTab.field_78032_a.length; t++) {
	
			
			if (CreativeTab.field_78032_a[t].getIndex() == id) {
				return CreativeTab.field_78032_a[t];
			}
	
	
		}
		
		return null;
	}
	//Gotta soon make ItemGroup checkers
	
	
	//Will need to change some biome registries after we add biomes
	public static Biome getBiomeFromGameRegistries(String registry_name)//We also need a Number ID version of this
	{
		return game.GameRegistries.BIOME.get(new ResourceLocation(registry_name));
	}
	
	public static Biome getBiomeFromGameRegistries(int id)
	{
		return game.GameRegistries.BIOME.def_unknown_84722(id);
	}
	
	public static boolean BiomeKeyExistsInRegistry(String registry_name)
	{
		return game.GameRegistries.BIOME.containsRL(new ResourceLocation(registry_name));
	}
	
}
