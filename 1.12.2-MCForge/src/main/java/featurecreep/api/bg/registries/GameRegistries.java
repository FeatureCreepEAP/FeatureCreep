/**
 * 
 */
package featurecreep.api.bg.registries;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

/**
 * @author rhel
 *
 */
public class GameRegistries {

	//Soon I gotta mark sortcuts to the actual registries
	
	public static Item getItemFromGameRegistries(String registry_name)//We also need a Number ID version of this
	{
		return Item.getByNameOrId(registry_name);
	}
	
	public static Item getItemFromGameRegistries(int id)
	{
		return Item.getItemById(id);
	}

	public static boolean ItemKeyExistsInRegistry(String registry_name)
	{
		return Item.REGISTRY.containsKey(new ResourceLocation(registry_name));
	}
	
	public static Block getBlockFromGameRegistries(String registry_name)//We also need a Number ID version of this
	{
		return Block.getBlockFromName(registry_name);
	}
	
	public static Block getBlockFromGameRegistries(int id)
	{
		return Block.getBlockById(id);
	}
	
	public static boolean BlockKeyExistsInRegistry(String registry_name)
	{
		return Block.REGISTRY.containsKey(new ResourceLocation(registry_name));
	}
	
	
	public static CreativeTabs getItemGroupByName(String name)
	{
		for (int t = 0; t < CreativeTabs.CREATIVE_TAB_ARRAY.length; t++) {
	
			
			if (CreativeTabs.CREATIVE_TAB_ARRAY[t].getTabLabel().equals(name)) {
				return CreativeTabs.CREATIVE_TAB_ARRAY[t];
			}
	
	
		}
		
		return null;
	}
	
	public static CreativeTabs getItemGroupByID(int id)
	{
		for (int t = 0; t < CreativeTabs.CREATIVE_TAB_ARRAY.length; t++) {
	
			
			if (CreativeTabs.CREATIVE_TAB_ARRAY[t].getTabIndex() == id) {
				return CreativeTabs.CREATIVE_TAB_ARRAY[t];
			}
	
	
		}
		
		return null;
	}
	//Gotta soon make ItemGroup checkers
	
	
	//Will need to change some biome registries after we add biomes
	public static Biome getBiomeFromGameRegistries(String registry_name)//We also need a Number ID version of this
	{
		return Biome.REGISTRY.getObject(new ResourceLocation(registry_name));
	}
	
	public static Biome getBiomeFromGameRegistries(int id)
	{
		return Biome.getBiomeForId(id);
	}
	
	public static boolean BiomeKeyExistsInRegistry(String registry_name)
	{
		return Biome.REGISTRY.containsKey(new ResourceLocation(registry_name));
	}
	
}
