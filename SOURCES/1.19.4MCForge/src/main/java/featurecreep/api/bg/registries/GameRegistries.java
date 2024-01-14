/**
 * 
 */
package featurecreep.api.bg.registries;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

/**
 * @author rhel
 *
 */
public class GameRegistries {

	//Soon I gotta mark sortcuts to the actual registries
	
	public static Item getItemFromGameRegistries(String registry_name)//We also need a Number ID version of this
	{
		return Registries.ITEM.get(new Identifier(registry_name));
	}
	
	public static Item getItemFromGameRegistries(int id)
	{
		return Item.byRawId(id);
	}

	public static boolean ItemKeyExistsInRegistry(String registry_name)
	{
		return Registries.ITEM.containsId(new Identifier(registry_name));
	}
	
	public static Block getBlockFromGameRegistries(String registry_name)//We also need a Number ID version of this
	{
		return Registries.BLOCK.get(new Identifier(registry_name));
	}
	
	public static Block getBlockFromGameRegistries(int id)
	{
		return Registries.BLOCK.get(id);//May not work
	}
	
	public static boolean BlockKeyExistsInRegistry(String registry_name)
	{
		return Registries.BLOCK.containsId(new Identifier(registry_name));
	}
	
	
	public static ItemGroup getItemGroupByName(String name)
	{
		for (int t = 0; t < ItemGroups.getGroups().size(); t++) {
	
			
			if (ItemGroups.getGroups().get(t).getDisplayName().getString().equals(name)) {
				return ItemGroups.getGroups().get(t);
			}
	
	
		}
		
		return null;
	}
	
	public static ItemGroup getItemGroupByID(int id)
	{
		for (int t = 0; t < ItemGroups.getGroups().size(); t++) {
	
			
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
