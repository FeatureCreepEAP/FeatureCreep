package featurecreep.api.bg.orespawn;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.jboss.dmr.ModelNode;

import featurecreep.FeatureCreep;
import featurecreep.api.bg.registries.GameRegistries;
import game.Biome;
import game.Biomes;
import game.Block;
import game.DecoratorComponent;
import game.MineralDepositFeatureGenerator;
import game.MineralDepositFeatureGenerator.Target;
import game.RangeDecoratorConfiguration;
import game.RegistryInterface;
import game.ResourceLocation;
import game.StageGeneration.Feature;
import game.WorldDecorationGenerator;
import game.WorldGenerationObjectConfiguration;

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
	Block replacedBlock = RegistryInterface.BLOCK.get(new ResourceLocation(block_identifier[0], block_identifier[1]));
	
	String new_block = node.get("blocks").get(0).get("name").asString();//I needa Do this as a List eventually to handle the Array
	
	
	
	if(FeatureCreep.debug_mode) {
	System.out.println(getCorrectNameSpace(new_block));
	}
	
	String[] new_block_identifier = getCorrectNameSpace(new_block).split(":");
	Block newBlock = RegistryInterface.BLOCK.get(new ResourceLocation(new_block_identifier[0], new_block_identifier[1]));
	
	
	
	
final Target RULE = Target.NATURAL_STONE; //gotta make this more configuarable later

	
	// final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CONFIG = ConfiguredFeatures.register("ore_amethyst", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, replacedBlock.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, replacedBlock.getDefaultState())), 4));
    // RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CONFIG = ConfiguredFeatures.register(name, Feature.ORE, new OreFeatureConfig(RULE, newBlock.getDefaultState(), node.get("parameters").get("size").asInt()));//I need to also include veriation in the future

	// final RegistryEntry<PlacedFeature> ORE_PLACED = PlacedFeatures.register(name+"_placed", ORE_CONFIG, modifiersWithCount(node.get("parameters").get("frequency").asInt(), HeightRangePlacementModifier.uniform(YOffset.fixed(node.get("parameters").get("minHeight").asInt()), YOffset.fixed(node.get("parameters").get("maxHeight").asInt()))));// YOffset.getBottom is for bottom


    WorldGenerationObjectConfiguration ORE_CONFIG = Biome.configureFeature(WorldDecorationGenerator.MINABLE,
			
 			new MineralDepositFeatureGenerator(RULE, newBlock.getDefaultState(), node.get("parameters").get("size").asInt()), DecoratorComponent.COUNT_RANGE, new RangeDecoratorConfiguration(node.get("parameters").get("frequency").asInt(),node.get("parameters").get("minHeight").asInt(), node.get("parameters").get("minHeight").asInt(), node.get("parameters").get("maxHeight").asInt()));
	
			
    Biomes.SMALL_END_ISLANDS.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.END_MIDLANDS.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.END_HIGHLANDS.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.END_BARRENS.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.WARM_OCEAN.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.LUKEWARM_OCEAN.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.COLD_OCEAN.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.DEEP_WARM_OCEAN.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.DEEP_LUKEWARM_OCEAN.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.DEEP_COLD_OCEAN.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.DEEP_FROZEN_OCEAN.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.THE_VOID.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.SUNFLOWER_PLAINS.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.DESERT_LAKES.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.GRAVELLY_MOUNTAINS.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.FLOWER_FOREST.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.TAIGA_MOUNTAINS.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.SWAMP_HILLS.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.ICE_SPIKES.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
  //  Biomes.var_unknown_116534.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
   // Biomes.var_unknown_116564.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.TALL_BIRCH_FOREST.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.TALL_BIRCH_HILLS.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.DARK_FOREST_HILLS.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.SNOWY_TAIGA_MOUNTAINS.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.GIANT_SPRUCE_TAIGA.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.GIANT_SPRUCE_TAIGA_HILLS.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.MODIFIED_GRAVELLY_MOUNTAINS.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.SHATTERED_SAVANNA.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.SHATTERED_SAVANNA_PLATEAU.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.ERODED_BADLANDS.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);
    Biomes.MODIFIED_BADLANDS_PLATEAU.addFeature(Feature.UNDERGROUND_ORES, ORE_CONFIG);








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
