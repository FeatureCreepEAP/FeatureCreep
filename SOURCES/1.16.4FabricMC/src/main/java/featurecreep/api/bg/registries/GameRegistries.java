
package featurecreep.api.bg.registries;

import game.Biome;
import game.Block;
import game.CreativeTab;
import game.Item;
import game.MapWorldGenerationRegistries;
import game.GameRegistriesInterface;
import game.ResourceLocation;

/**
 * @author rhel
 *
 */
public class GameRegistries {

	//Soon I gotta mark sortcuts to the actual registries
	
	public static Item getItemFromGameRegistries(String registry_name)//We also need a Number ID version of this
	{
		return GameRegistriesInterface.ITEM.get(new ResourceLocation(registry_name));
	}
	
	public static Item getItemFromGameRegistries(int id)
	{
		return Item.byID(id);
	}

	public static boolean ItemKeyExistsInRegistry(String registry_name)
	{
		return GameRegistriesInterface.ITEM.containsRL(new ResourceLocation(registry_name));
	}
	
	public static Block getBlockFromGameRegistries(String registry_name)//We also need a Number ID version of this
	{
		return GameRegistriesInterface.BLOCK.get(new ResourceLocation(registry_name));
	}
	
	public static Block getBlockFromGameRegistries(int id)
	{
		return GameRegistriesInterface.BLOCK.byId(id);//May not work
	}
	
	public static boolean BlockKeyExistsInRegistry(String registry_name)
	{
		return GameRegistriesInterface.BLOCK.containsRL(new ResourceLocation(registry_name));
	}
	
	
	public static CreativeTab getItemGroupByName(String name)
	{
		for (int t = 0; t < CreativeTab.GROUPS.length; t++) {
	
			
			if (CreativeTab.GROUPS[t].getName().equals(name)) {
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
	//Gotta soon make CreativeTab checkers
	
	
	//Will need to change some biome registries after we add biomes
	public static Biome getBiomeFromGameRegistries(String registry_name)//We also need a Number ID version of this
	{
		return MapWorldGenerationRegistries.BIOME.get(new ResourceLocation(registry_name));
	}
	
	public static Biome getBiomeFromGameRegistries(int id)
	{
		return MapWorldGenerationRegistries.BIOME.byId(id);
	}
	
	public static boolean BiomeKeyExistsInRegistry(String registry_name)
	{
		return MapWorldGenerationRegistries.BIOME.containsRL(new ResourceLocation(registry_name));
	}
	
}
