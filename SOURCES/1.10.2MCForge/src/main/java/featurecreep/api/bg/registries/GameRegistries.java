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
		return Item.fromString(registry_name);
	}
	
	public static Item getItemFromGameRegistries(int id)
	{
		return Item.byID(id);
	}

	public static boolean ItemKeyExistsInRegistry(String registry_name)
	{
		return Item.registry.containsRL(new ResourceLocation(registry_name));
	}
	
	public static Block getBlockFromGameRegistries(String registry_name)//We also need a Number ID version of this
	{
		return Block.fromString(registry_name);
	}
	
	public static Block getBlockFromGameRegistries(int id)
	{
		return Block.byID(id);
	}
	
	public static boolean BlockKeyExistsInRegistry(String registry_name)
	{
		return Block.registry.containsRL(new ResourceLocation(registry_name));
	}
	
	
	public static CreativeTab getItemGroupByName(String name)
	{
		for (int t = 0; t < CreativeTab.GROUPS.length; t++) {
	
			
			if (CreativeTab.GROUPS[t].createIcon().equals(name)) {
				return CreativeTab.GROUPS[t];
			}
	
	
		}
		
		return null;
	}
	
	public static CreativeTab getItemGroupByID(int id)
	{
		for (int t = 0; t < CreativeTab.GROUPS.length; t++) {
	
			
			if (CreativeTab.GROUPS[t].getIndex() == id) {
				return CreativeTab.GROUPS[t];
			}
	
	
		}
		
		return null;
	}
	//Gotta soon make ItemGroup checkers
	
	
	//Will need to change some biome registries after we add biomes
	public static Biome getBiomeFromGameRegistries(String registry_name)//We also need a Number ID version of this
	{
		return Biome.registry.fromRL(new ResourceLocation(registry_name));
	}
	
	public static Biome getBiomeFromGameRegistries(int id)
	{
		return Biome.byID(id);
	}
	
	public static boolean BiomeKeyExistsInRegistry(String registry_name)
	{
		return Biome.registry.containsRL(new ResourceLocation(registry_name));
	}
	
}
