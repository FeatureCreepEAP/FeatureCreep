package featurecreep.api.orespawn;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import org.jboss.dmr.ModelNode;

import featurecreep.FeatureCreep;
import net.minecraft.BlockState;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.DecoratedFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

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
	Block replacedBlock = Registry.Registry.get(new Identifier(block_identifier[0], block_identifier[1]));
	
	String new_block = node.get("blocks").get(0).get("name").asString();//I needa Do this as a List eventually to handle the Array
	
	
	
	
	System.out.println(getCorrectNameSpace(new_block));
	String[] new_block_identifier = getCorrectNameSpace(new_block).split(":");
	Block newBlock = Registry.Registry.get(new Identifier(new_block_identifier[0], new_block_identifier[1]));
	
	
	
	
	System.out.println(replacedBlock.getName());
	System.out.println(newBlock.getName());

    final Predicate<BlockState> RULE = OreFeatureConfig.field_77424; //gotta make this more configuarable later

	
	// final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CONFIG = ConfiguredFeatures.register("ore_amethyst", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, replacedBlock.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, replacedBlock.getDefaultState())), 4));
    // RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CONFIG = ConfiguredFeatures.register(name, Feature.ORE, new OreFeatureConfig(RULE, newBlock.getDefaultState(), node.get("parameters").get("size").asInt()));//I need to also include veriation in the future

	// final RegistryEntry<PlacedFeature> ORE_PLACED = PlacedFeatures.register(name+"_placed", ORE_CONFIG, modifiersWithCount(node.get("parameters").get("frequency").asInt(), HeightRangePlacementModifier.uniform(YOffset.fixed(node.get("parameters").get("minHeight").asInt()), YOffset.fixed(node.get("parameters").get("maxHeight").asInt()))));// YOffset.getBottom is for bottom

//Gotta update mappings on this version
		//	DecoratedFeature<OreFeatureConfig, RangeDecoratorConfig> ORE_CONFIG = Biome.method_8699(Feature.field_13517,
						
			//			new OreFeatureConfig(RULE, newBlock.getDefaultState(), node.get("parameters").get("size").asInt()), Biome.field_76785, new RangeDecoratorConfig(node.get("parameters").get("frequency").asInt(),node.get("parameters").get("minHeight").asInt(), node.get("parameters").get("maxHeight").asInt(), node.get("parameters").get("frequency").asInt()));
	DecoratedFeature ORE_CONFIG = Biome.method_8699(Feature.field_13517,
			
			new OreFeatureConfig(RULE, newBlock.getDefaultState(), node.get("parameters").get("size").asInt()), Biome.field_76785, new RangeDecoratorConfig(node.get("parameters").get("frequency").asInt(),node.get("parameters").get("minHeight").asInt(), node.get("parameters").get("minHeight").asInt(), node.get("parameters").get("maxHeight").asInt()));

			
			
			Biomes.OCEAN.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.DEFAULT.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.PLAINS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.DESERT.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.MOUNTAINS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.FOREST.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.TAIGA.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SWAMP.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.RIVER.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.NETHER.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.THE_END.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.FROZEN_OCEAN.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.FROZEN_RIVER.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SNOWY_TUNDRA.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SNOWY_MOUNTAINS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.MUSHROOM_FIELDS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.MUSHROOM_FIELD_SHORE.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.BEACH.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.DESERT_HILLS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.WOODED_HILLS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.TAIGA_HILLS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.MOUNTAIN_EDGE.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.JUNGLE.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.JUNGLE_HILLS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.JUNGLE_EDGE.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.DEEP_OCEAN.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.STONE_SHORE.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SNOWY_BEACH.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.BIRCH_FOREST.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.BIRCH_FOREST_HILLS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.DARK_FOREST.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SNOWY_TAIGA.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SNOWY_TAIGA_HILLS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.GIANT_TREE_TAIGA.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.GIANT_TREE_TAIGA_HILLS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.WOODED_MOUNTAINS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SAVANNA.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SAVANNA_PLATEAU.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.BADLANDS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.WOODED_BADLANDS_PLATEAU.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.BADLANDS_PLATEAU.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SMALL_END_ISLANDS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.END_MIDLANDS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.END_HIGHLANDS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.END_BARRENS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.WARM_OCEAN.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.LUKEWARM_OCEAN.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.COLD_OCEAN.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.DEEP_WARM_OCEAN.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.DEEP_LUKEWARM_OCEAN.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.DEEP_COLD_OCEAN.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.DEEP_FROZEN_OCEAN.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.THE_VOID.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SUNFLOWER_PLAINS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.DESERT_LAKES.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.GRAVELLY_MOUNTAINS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.FLOWER_FOREST.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.TAIGA_MOUNTAINS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SWAMP_HILLS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.ICE_SPIKES.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.MODIFIED_JUNGLE.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.MODIFIED_JUNGLE_EDGE.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.TALL_BIRCH_FOREST.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.TALL_BIRCH_HILLS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.DARK_FOREST_HILLS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SNOWY_TAIGA_MOUNTAINS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.GIANT_SPRUCE_TAIGA.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.GIANT_SPRUCE_TAIGA_HILLS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.MODIFIED_GRAVELLY_MOUNTAINS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SHATTERED_SAVANNA.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.SHATTERED_SAVANNA_PLATEAU.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.ERODED_BADLANDS.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
			Biomes.MODIFIED_BADLANDS_PLATEAU.method_8719(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
		// These 2 dont appear to exist	Biomes.BAMBOO_JUNGLE.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);
	//		Biomes.BAMBOO_JUNGLE_HILLS.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG);










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
