package featurecreep.api.bg.orespawn;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.dmr.ModelNode;

import featurecreep.FeatureCreep;
import game.BiomeGenerationSettings;
import game.BiomePlacementModifier;
import game.Block;
import game.BlockMatcher;
import game.BuiltInRegistries;
import game.CountGenerationAttribute;
import game.GenerationPlacement;
import game.PlacementModifier;
import game.RegistryKey;
import game.ResourceLocation;
import game.SquarePlacementModifier;
import game.StageGeneration.Feature;

public class OrespawnBasicFeatureParser {

	
	public static List<OreSpawnBasicConfig> configs = new ArrayList<OreSpawnBasicConfig>();
	
	
	public  static List<RegistryKey<GenerationPlacement>> placed = new ArrayList<RegistryKey<GenerationPlacement>>();

	//public  static	  RegistryObject<Codec<ExampleBiomeModifier>> EXAMPLE_CODEC;

	
	//I gotta rewrite all of the Orespawn Module including this part once I get more time
	//Loads the contents from %GAMEDIR%/orespawn/config/
	public static void spawnOresFromDefaultConfig()
	{
	
		
//		
//		EXAMPLE_CODEC =	FCForgeRegistries.BIOME_MODIFIER_SERIALIZERS.register("fccodec", () -> RecordCodecBuilder.create(builder -> builder.group(
//		        // declare fields
//		        Biome.REGISTRY_ENTRY_LIST_CODEC.fieldOf("biomes").forGetter(ExampleBiomeModifier::biomes),
//		        PlacedFeature.REGISTRY_CODEC.fieldOf("feature").forGetter(ExampleBiomeModifier::feature)
//		      // declare constructor
//		      ).apply(builder, ExampleBiomeModifier::new)));
//		
//		FCForgeRegistries.BIOME_MODIFIER_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
		
		
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
	Block replacedBlock = BuiltInRegistries.block.get(new ResourceLocation(block_identifier[0], block_identifier[1]));
	
	String new_block = node.get("blocks").get(0).get("name").asString();//I needa Do this as a List eventually to handle the Array
	
	
	
	if(FeatureCreep.debug_mode) {
	System.out.println(getCorrectNameSpace(new_block));
	}
	String[] new_block_identifier = getCorrectNameSpace(new_block).split(":");
	Block newBlock = BuiltInRegistries.block.get(new ResourceLocation(new_block_identifier[0], new_block_identifier[1]));
	
	
	
	if(FeatureCreep.debug_mode) {
	System.out.println(replacedBlock.getName());
	System.out.println(newBlock.getName());
	}
     BlockMatcher RULE = new BlockMatcher(replacedBlock);

	
    
    OreSpawnBasicConfig config = new OreSpawnBasicConfig(name, newBlock, node.get("parameters").get("size").asInt(), node.get("parameters").get("frequency").asInt(), node.get("parameters").get("minHeight").asInt(), node.get("parameters").get("maxHeight").asInt());

    configs.add(config);
    
    
    
	// final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CONFIG = ConfiguredFeatures.register("ore_amethyst", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, replacedBlock.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, replacedBlock.getDefaultState())), 4));
 //    RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CONFIG = ConfiguredFeatures.(name, Feature.ORE, new OreFeatureConfig(RULE, newBlock.getDefaultState(), node.get("parameters").get("size").asInt()));//I need to also include veriation in the future

    
    
//	 final RegistryEntry<PlacedFeature> ORE_PLACED = PlacedFeatures.register(name+"_placed", ORE_CONFIG, modifiersWithCount(node.get("parameters").get("frequency").asInt(), HeightRangePlacementModifier.uniform(YOffset.fixed(node.get("parameters").get("minHeight").asInt()), YOffset.fixed(node.get("parameters").get("maxHeight").asInt()))));// YOffset.getBottom is for bottom
//placed.add(ORE_PLACED);
	// BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_PLACED.getKey().get()); No longer seems to work

//	 BiomeModifications.create(ORE_PLACED.getKey().get().getValue()).add(ModificationPhase.ADDITIONS, BiomeSelectors.foundInOverworld(), context -> {
//			context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_PLACED.value());
//		});
	 

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

private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
    return List.of(countModifier, SquarePlacementModifier.basic(), heightModifier, BiomePlacementModifier.standard());
}

private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
    return modifiers(CountGenerationAttribute.count(count), heightModifier);

}
	
	public static void spawnOre(BiomeGenerationSettings.Builder builder)
	{
		
		
		for (int f = 0; f < placed.size(); f++) {

	builder.addFeature(Feature.UNDERGROUND_ORES, placed.get(f));
	//(class_unknown_1069.Feature.UNDERGROUND_ORES, placed.get(f));

	}
		
	
	}
	
	
	
	
	/*public record ExampleBiomeModifier(RegistryEntryList<Biome> biomes, RegistryEntry<PlacedFeature> feature) implements BiomeModifier
	{


	  public Codec<? extends BiomeModifier> codec()
	  {
	    return EXAMPLE_CODEC.get();
	  }


	public void modify(RegistryEntry<Biome> biome, Phase phase, Builder builder) {
		// TODO Auto-generated method stub

		 // add a feature to all specified biomes
	    if (phase == Phase.ADD) // all biomes currently, this system sucks
	    {
	    
	    	
	    	for (int f = 0; f < placed.size(); f++) {
	    		RegistryEntryLookup<PlacedFeature> placedFeatureLookup = null;
	    		builder.getGenerationSettings().feature(GenerationStep.Feature.UNDERGROUND_ORES, placedFeatureLookup.getOrThrow(placed.get(f)) );
			}	
	    	
	    
	    
	    
	    }
		
	}


	}*/
	
	
	
	
	
}
