package featurecreep.api.bg.orespawn;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import featurecreep.api.io.BasicIO;
import org.jboss.dmr.ModelNode;

import featurecreep.FeatureCreep;
import game.Block;
import game.BlockMatcher;
import game.MapVerticleAnchor;
import game.MineralDepositFeatureGenerator;
import game.RangeDecoratorConfiguration;
import game.GameRegistriesInterface;
import game.ResourceLocation;
import game.StageGeneration.Feature;
import game.UniformHeightProvider;
import game.WorldDecorationGenerator;
import game.WorldGenerationObjectConfiguration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;

public class OrespawnBasicFeatureParser {

	
	  static List<WorldGenerationObjectConfiguration> placed = new ArrayList<WorldGenerationObjectConfiguration>();

	
	
	//I gotta rewrite all of the Orespawn Module including this part once I get more time
	//Loads the contents from %GAMEDIR%/orespawn/config/
	public static void spawnOresFromDefaultConfig()
	{
	
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


/**
	 * Register from JSON or DMR Plaintext InputStream
	 * @param stream
	 * @param is_json is JSON  rather than DMR Plaintext
	 */
	public static void registerFromStream(InputStream stream, boolean is_json) {
		try {
			ModelNode binarynode;
			if(is_json) {
				binarynode= ModelNode.fromJSONStream(stream);
			}else {
				binarynode = new ModelNode();
			binarynode.readExternal(stream);
			}
			splitOS3Basic(binarynode);
		} catch (java.io.InvalidObjectException e) {
			splitOS3Basic(DMRStringtoNode(BasicIO.inputstreamToString(stream),is_json));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Parses a Plaintext DMR String or JSON String
	 * @param string
	 * @param is_json Is JSON rather than DMR plaintext
	 * @return
	 */
	public static ModelNode DMRStringtoNode(String string, boolean is_json) {
		ModelNode node;
		if(is_json) {node = ModelNode.fromJSONString(string);}
		else {node = ModelNode.fromString(string);}
		return node;
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
		Block replacedBlock = GameRegistriesInterface.BLOCK.get(new ResourceLocation(block_identifier[0], block_identifier[1]));
		
		String new_block = node.get("blocks").get(0).get("name").asString();//I needa Do this as a List eventually to handle the Array
		
		
		
		
		String[] new_block_identifier = OrespawnBasicFeatureParser.getCorrectNameSpace(new_block).split(":");
		Block newBlock = GameRegistriesInterface.BLOCK.get(new ResourceLocation(new_block_identifier[0], new_block_identifier[1]));
		
		
		
		
		//System.out.println(replacedBlock.getName());
		//System.out.println(newBlock.getName());

		BlockMatcher RULE = new BlockMatcher(replacedBlock);

	
	// final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CONFIG = ConfiguredFeatures.register("ore_amethyst", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, replacedBlock.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, replacedBlock.getDefaultState())), 4));
    // RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CONFIG = ConfiguredFeatures.register(name, Feature.ORE, new OreFeatureConfig(RULE, newBlock.getDefaultState(), node.get("parameters").get("size").asInt()));//I need to also include veriation in the future

	// final RegistryEntry<PlacedFeature> ORE_PLACED = PlacedFeatures.register(name+"_placed", ORE_CONFIG, modifiersWithCount(node.get("parameters").get("frequency").asInt(), HeightRangePlacementModifier.uniform(YOffset.fixed(node.get("parameters").get("minHeight").asInt()), YOffset.fixed(node.get("parameters").get("maxHeight").asInt()))));// YOffset.getBottom is for bottom

		WorldGenerationObjectConfiguration ORE_CONFIG = (WorldGenerationObjectConfiguration)((WorldGenerationObjectConfiguration)((WorldGenerationObjectConfiguration)WorldDecorationGenerator.MINABLE.config(new MineralDepositFeatureGenerator(RULE, newBlock.getDefaultState(), node.get("parameters").get("size").asInt())).createDecoratedFeature(new RangeDecoratorConfiguration(UniformHeightProvider.new_(MapVerticleAnchor.fixed(node.get("parameters").get("minHeight").asInt()), MapVerticleAnchor.fixed(node.get("parameters").get("maxHeight").asInt()))))).spreadHorizontally()).repeat(node.get("parameters").get("frequency").asInt());
/*
			ConfiguredFeature<?, ?> ORE_CONFIG = Feature.ORE
						.configure(new OreFeatureConfig(
						  RULE,
						  newBlock.getDefaultState(),
						  node.get("parameters").get("size").asInt())) // Vein size
						.range(new RangeDecoratorConfig(
						  // You can also use one of the other height providers if you don't want a uniform distribution
						net.minecraft.world.gen.heightprovider.UniformHeightProvider.create(YOffset.fixed(node.get("parameters").get("minHeight").asInt()), YOffset.fixed(node.get("parameters").get("maxHeight").asInt())))) // Inclusive min and max height
						.spreadHorizontally()
						.repeat(node.get("parameters").get("frequency").asInt()); // Number of veins per chunk
				*/
				
placed.add(ORE_CONFIG);

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


	




public static void onBiomeLoadingEvent(final BiomeLoadingEvent event) {
	
	if(FeatureCreep.debug_mode) {	
	System.out.println("Adding FCOres");
	}
	
	for (int f = 0; f < placed.size(); f++) {
		event.getGeneration().addFeature(Feature.UNDERGROUND_ORES, placed.get(f));
	}
		
}
	
	
}
