/**
 * 
 */
package featurecreep.api.bg.registries;

import game.Biome;
import game.Block;
import game.BuiltInRegistries;
import game.CreativeTab;
import game.CreativeTabs;
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
		return BuiltInRegistries.ITEMS.get(new ResourceLocation(registry_name));
	}
	
	public static Item getItemFromGameRegistries(int id)
	{
		return Item.byID(id);
	}

	public static boolean ItemKeyExistsInRegistry(String registry_name)
	{
		return BuiltInRegistries.ITEMS.containsRL(new ResourceLocation(registry_name));
	}
	
	public static Block getBlockFromGameRegistries(String registry_name)//We also need a Number ID version of this
	{
		return BuiltInRegistries.block.get(new ResourceLocation(registry_name));
	}
	
	public static Block getBlockFromGameRegistries(int id)
	{
		return BuiltInRegistries.block.byId(id);//May not work
	}
	
	public static boolean BlockKeyExistsInRegistry(String registry_name)
	{
		return BuiltInRegistries.block.containsRL(new ResourceLocation(registry_name));
	}
	
	
	public static CreativeTab getItemGroupByName(String name)
	{
		for (int t = 0; t < CreativeTabs.asList().size(); t++) {
	
			
			if (CreativeTabs.asList().get(t).getUnlocalisedName().getString().equals(name)) {
				return CreativeTabs.asList().get(t);
			}
	
	
		}
		
		return null;
	}
	
	public static CreativeTab getItemGroupByID(int id)
	{
		for (int t = 0; t < CreativeTabs.asList().size(); t++) {
	
			
		//	if (ItemGroups.getGroups().get(t).getIndex() == id) {
		//		return ItemGroups.getGroups().get(t);
		//	}
	
	
		}
		
		return null;
	}
	//Gotta soon make ItemGroup checkers
	
	
	//Will need to change some biome registries after we add biomes
	public static Biome getBiomeFromGameRegistries(String registry_name)//We also need a Number ID version of this
	{


		
		
		return null;  //get(new Identifier(registry_name)); 
	}
	
	public static Biome getBiomeFromGameRegistries(int id)
	{
		return null; 
	}
	
	public static boolean BiomeKeyExistsInRegistry(String registry_name)
	{
		return false;
	}
	
}
