package featurecreep.api.orespawn;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.jboss.dmr.ModelNode;

import featurecreep.FeatureCreep;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.Target;

public class OrespawnBasicFeatureParser {

	

	
	
	//I gotta rewrite all of the Orespawn Module including this part once I get more time
	//Loads the contents from %GAMEDIR%/orespawn/config/
	public static void spawnOresFromDefaultConfig()
	{
	String orespawn_dir = new String (FeatureCreep.gamepath.toString() +  ("/orespawn/config/"));
	File file = new File(orespawn_dir);
		
		String contents[] = file.list();
		System.out.println("List of files and directories in the specified directory:");
		//I need to make this multicore



		if (contents != null) {
		for(int i=0; i<contents.length; i++) {
		   
			System.out.println("FeatureCreep is trying to load "+contents[i]);

			System.out.println(orespawn_dir + contents[i] + "/");
			splitOS3Basic(getModelNodesFromFile(orespawn_dir + contents[i] + "/"));

		}
		
		}else {
			FeatureCreep.LOGGER.info("No DMR Items Found");
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
		System.out.println(rowNode.asString().split("\"")[1]);
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
	Block replacedBlock = Registry.BLOCK.get(new Identifier(block_identifier[0], block_identifier[1]));
	
	String new_block = node.get("blocks").get(0).get("name").asString();//I needa Do this as a List eventually to handle the Array
	
	
	
	
	System.out.println(getCorrectNameSpace(new_block));
	String[] new_block_identifier = getCorrectNameSpace(new_block).split(":");
	Block newBlock = Registry.BLOCK.get(new Identifier(new_block_identifier[0], new_block_identifier[1]));
	
	
	
	
	System.out.println(replacedBlock.getName());
	System.out.println(newBlock.getName());

    final Target RULE = OreFeatureConfig.Target.NATURAL_STONE; //gotta make this more configuarable later

	
	// final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CONFIG = ConfiguredFeatures.register("ore_amethyst", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, replacedBlock.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, replacedBlock.getDefaultState())), 4));
    // RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CONFIG = ConfiguredFeatures.register(name, Feature.ORE, new OreFeatureConfig(RULE, newBlock.getDefaultState(), node.get("parameters").get("size").asInt()));//I need to also include veriation in the future

	// final RegistryEntry<PlacedFeature> ORE_PLACED = PlacedFeatures.register(name+"_placed", ORE_CONFIG, modifiersWithCount(node.get("parameters").get("frequency").asInt(), HeightRangePlacementModifier.uniform(YOffset.fixed(node.get("parameters").get("minHeight").asInt()), YOffset.fixed(node.get("parameters").get("maxHeight").asInt()))));// YOffset.getBottom is for bottom


	ConfiguredFeature<?, ?> ORE_CONFIG = Feature.ORE
			.configure(new OreFeatureConfig(
			  RULE,
			  newBlock.getDefaultState(),
			  node.get("parameters").get("size").asInt())) // Vein size						
			.createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(node.get("parameters").get("frequency").asInt(),node.get("parameters").get("minHeight").asInt(), node.get("parameters").get("minHeight").asInt(), node.get("parameters").get("maxHeight").asInt()))); // Number of veins per chunk
	
			

			Biomes.OCEAN.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.DEFAULT.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.PLAINS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.DESERT.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.MOUNTAINS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.FOREST.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.TAIGA.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SWAMP.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.RIVER.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.NETHER.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.THE_END.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.FROZEN_OCEAN.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.FROZEN_RIVER.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SNOWY_TUNDRA.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SNOWY_MOUNTAINS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.MUSHROOM_FIELDS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.MUSHROOM_FIELD_SHORE.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.BEACH.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.DESERT_HILLS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.WOODED_HILLS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.TAIGA_HILLS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.MOUNTAIN_EDGE.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.JUNGLE.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.JUNGLE_HILLS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.JUNGLE_EDGE.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.DEEP_OCEAN.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.STONE_SHORE.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SNOWY_BEACH.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.BIRCH_FOREST.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.BIRCH_FOREST_HILLS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.DARK_FOREST.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SNOWY_TAIGA.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SNOWY_TAIGA_HILLS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.GIANT_TREE_TAIGA.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.GIANT_TREE_TAIGA_HILLS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.WOODED_MOUNTAINS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SAVANNA.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SAVANNA_PLATEAU.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.BADLANDS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.WOODED_BADLANDS_PLATEAU.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.BADLANDS_PLATEAU.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SMALL_END_ISLANDS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.END_MIDLANDS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.END_HIGHLANDS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.END_BARRENS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.WARM_OCEAN.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.LUKEWARM_OCEAN.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.COLD_OCEAN.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.DEEP_WARM_OCEAN.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.DEEP_LUKEWARM_OCEAN.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.DEEP_COLD_OCEAN.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.DEEP_FROZEN_OCEAN.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.THE_VOID.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SUNFLOWER_PLAINS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.DESERT_LAKES.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.GRAVELLY_MOUNTAINS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.FLOWER_FOREST.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.TAIGA_MOUNTAINS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SWAMP_HILLS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.ICE_SPIKES.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.MODIFIED_JUNGLE.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.MODIFIED_JUNGLE_EDGE.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.TALL_BIRCH_FOREST.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.TALL_BIRCH_HILLS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.DARK_FOREST_HILLS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SNOWY_TAIGA_MOUNTAINS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.GIANT_SPRUCE_TAIGA.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.GIANT_SPRUCE_TAIGA_HILLS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.MODIFIED_GRAVELLY_MOUNTAINS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SHATTERED_SAVANNA.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SHATTERED_SAVANNA_PLATEAU.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.ERODED_BADLANDS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.MODIFIED_BADLANDS_PLATEAU.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.BAMBOO_JUNGLE.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.BAMBOO_JUNGLE_HILLS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);










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
