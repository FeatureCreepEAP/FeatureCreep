package featurecreep.api.bg.orespawn;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.jboss.dmr.ModelNode;

import featurecreep.FeatureCreep;
import game.Block;
import game.BlockMatcher;
import game.ConfiguredMapPlacement;
import game.DepthAverageDecoratorConfig;
import game.GameRegistries;
import game.MapWorldGenerationRegistries;
import game.RegistryKey;
import game.ResourceLocation;
import game.TerrainPlacementMod;
import game.WorldGenFeature;
import game.WorldGenerationObjectConfiguration;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import obf.class_unknown_1069.Feature;
import obf.class_unknown_1210;

public class OrespawnBasicFeatureParser {

	
	
	
	//I gotta rewrite all of the Orespawn Module including this part once I get more time
	//Loads the contents from %GAMEDIR%/orespawn/config/
	public static void spawnOresFromDefaultConfig()
	{
	
	String orespawn_dir = new String (FeatureCreep.gamepath.toString() +  ("/orespawn/config/"));
	File file = new File(orespawn_dir);
		
		String contents[] = file.list();
		
		if(FeatureCreep.debug_mode) {
		System.out.println("List of files and directories in the specified directory:");
		}
		
		//I need to make this multicore



		if (contents != null) {
		for(int i=0; i<contents.length; i++) {
		   
			
			if(FeatureCreep.debug_mode) {
			System.out.println("FeatureCreep is trying to load "+contents[i]);

			System.out.println(orespawn_dir + contents[i] + "/");
			}
			
			splitOS3Basic(getModelNodesFromFile(orespawn_dir + contents[i] + "/"));

		}
		
		}else {
			FeatureCreep.LOGGER.info("No OreSpawn Configs Found");
		}
		
		
	}
	
	
public static ModelNode	getModelNodesFromFile(String file)
{
	if (file.contains(".json")){
try {
	return ModelNode.fromJSONStream(new FileInputStream(file));
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	return new ModelNode();
}
	}else
	{
		try {
			return ModelNode.fromStream(new FileInputStream(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelNode();
		}
	}


}
	

public static void 	splitOS3Basic(ModelNode node)
{
	List<ModelNode> list = node.get("spawns").asList();
	for(ModelNode rowNode : list) {
		if(FeatureCreep.debug_mode) {
		System.out.println(rowNode.asString().split("\"")[1]);
		}
		parseOS3Basic(rowNode.get(0), rowNode.asString().split("\"")[1]);
		
	}
	
	
	
}


	
public static void 	parseOS3Basic(ModelNode node,String name)
{
	
if (node.get("enabled").asBoolean() == true)
{
	//List<String> replace_registry_names = new ArrayList<String>(); We will do array list later
	String replace_registry_names = new String();
	if (node.get("replaces").asString().equals("default"))
	{
		replace_registry_names = "minecraft:stone";
	}else
	{
		replace_registry_names = node.get("replaces").asString();
	}
	
	replace_registry_names = getCorrectNameSpace(replace_registry_names);
	
	
	String[] block_identifier = replace_registry_names.split(":");
	Block replacedBlock = GameRegistries.BLOCK.get(new ResourceLocation(block_identifier[0], block_identifier[1]));
	
	String new_block = node.get("blocks").get(0).get("name").asString();//I needa Do this as a List eventually to handle the Array
	
	
	
	if(FeatureCreep.debug_mode) {
	System.out.println(getCorrectNameSpace(new_block));
	}
	String[] new_block_identifier = getCorrectNameSpace(new_block).split(":");
	Block newBlock = GameRegistries.BLOCK.get(new ResourceLocation(new_block_identifier[0], new_block_identifier[1]));
	
	
	
	if(FeatureCreep.debug_mode) {
	System.out.println(replacedBlock.getName());
	System.out.println(newBlock.getName());
}

    BlockMatcher RULE = new BlockMatcher(replacedBlock);
    WorldGenerationObjectConfiguration ORE_CONFIG = WorldGenFeature.ORE.config(new TerrainPlacementMod(RULE, newBlock.getDefaultState(), node.get("parameters").get("size").asInt())).createDecoratedFeature((ConfiguredMapPlacement)((ConfiguredMapPlacement)class_unknown_1210.COUNT_RANGE.config(new DepthAverageDecoratorConfig(node.get("parameters").get("minHeight").asInt(), node.get("parameters").get("maxHeight").asInt())).spreadHorizontally()).repeat(node.get("parameters").get("frequency").asInt()));
 

	// Vein size
			 // Number of veins per chunk
	

    RegistryKey<WorldGenerationObjectConfiguration<?, ?>> ORE_CONFIG_KEY = RegistryKey.of(GameRegistries.WORLD_GENERATION_OBJECT_CONFIGURATION,
  	      new ResourceLocation("featurecreep", name));
    
  	    GameRegistries.register(MapWorldGenerationRegistries.WORLD_GENERATION_OBJECT_CONFIGURATION, ORE_CONFIG_KEY.getValue(), ORE_CONFIG);
  	    BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), Feature.UNDERGROUND_ORES, ORE_CONFIG_KEY);
}
	
	
}


public static String getCorrectNameSpace(String old)
{
String new_string = new String (old);	



if (new_string.contains("vanilla:"))
{
	new_string = new_string.replace("vanilla:", "minecraft:");
}

if (new_string.contains("dangerzone:"))
{
	new_string = new_string.replace("dangerzone:", "minecraft:");
}

return new_string;
}


	
	
}
