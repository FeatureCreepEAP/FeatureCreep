/**
 * 
 */
package featurecreep.api.bg.registries;

/**
 * @author rhel
 *
 */
public class GameRegistries {

//  public static Item getItemFromGameRegistries(String registry_name) //We also need a Number ID version of this
//  {
//    return getItemFromGameRegistries(Items.findByName(registry_name));
//  }
//
//  public static Item getItemFromGameRegistries(int id) {
//    return Items.getItem(id);
//  }

  public static boolean ItemKeyExistsInRegistry(String registry_name) {
		return false; //Userdev does not need to return anything	, this should work in production though
  }

  //public static Block getBlockFromGameRegistries(String registry_name) //We also need a Number ID version of this
  //{  }

  //public static Block getBlockFromGameRegistries(int id) { return Blocks.getBlock(id);  }

  public static boolean BlockKeyExistsInRegistry(String registry_name) {


	return false; //Userdev does not need to return anything	, this should work in production though

  }

  //DO NOT USE

//  public static Object getItemGroupByName(String name) {
//    return GlobalRegistries.getItemGroupByName(name);
 // }

//  public static Object getItemGroupByID(int id) {
 //   return GlobalRegistries.getItemGroupByID(id);
 // }

  //Gotta soon make ItemGroup checkers

  //Will need to change some biome registries after we add biomes
  //public static Biome getBiomeFromGameRegistries(String registry_name) //We also need a Number ID version of this
  //{  }

  //Should be avoided

 // public static Biome getBiomeFromGameRegistries(int id) {  }

  public static boolean BiomeKeyExistsInRegistry(String registry_name) {

		return false; //Userdev does not need to return anything	, this should work in production though

  }

}