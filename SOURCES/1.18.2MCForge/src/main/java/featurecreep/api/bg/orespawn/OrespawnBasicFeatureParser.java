package featurecreep.api.bg.orespawn;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.dmr.ModelNode;

import featurecreep.FeatureCreep;
import game.BiomePlacementModifier;
import game.Block;
import game.BlockMatcher;
import game.CountGenerationAttribute;
import game.GenerationPlacement;
import game.HeightRangePlacementModifier;
import game.MapVerticleAnchor;
import game.MineralDepositFeatureGenerator;
import game.NudgerConfig;
import game.NudgerPlacements;
import game.PlacementModifier;
import game.RegistryEntry;
import game.GameRegistriesInterface;
import game.ResourceLocation;
import game.SquarePlacementModifier;
import game.StageGeneration.Feature;
import game.WorldDecorationGenerator;
import game.WorldGenerationObjectConfiguration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;

public class OrespawnBasicFeatureParser {

	
	  static List<RegistryEntry<GenerationPlacement>> placed = new ArrayList<RegistryEntry<GenerationPlacement>>();

	
	
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
	
	
	
	if(FeatureCreep.debug_mode) {
	System.out.println(getCorrectNameSpace(new_block));
	}
	String[] new_block_identifier = getCorrectNameSpace(new_block).split(":");
	Block newBlock = GameRegistriesInterface.BLOCK.get(new ResourceLocation(new_block_identifier[0], new_block_identifier[1]));
	
	
	
	if(FeatureCreep.debug_mode) {
	System.out.println(replacedBlock.getName());
	System.out.println(newBlock.getName());
}


	BlockMatcher RULE = new BlockMatcher(replacedBlock);


	
	// final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CONFIG = ConfiguredFeatures.register("ore_amethyst", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, replacedBlock.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, replacedBlock.getDefaultState())), 4));
    RegistryEntry<WorldGenerationObjectConfiguration<MineralDepositFeatureGenerator, ?>> ORE_CONFIG = NudgerConfig.register(name, WorldDecorationGenerator.MINABLE, new MineralDepositFeatureGenerator(RULE, newBlock.getDefaultState(), node.get("parameters").get("size").asInt()));//I need to also include veriation in the future

	 final RegistryEntry<GenerationPlacement> ORE_PLACED = NudgerPlacements.register(name+"_placed", ORE_CONFIG, modifiersWithCount(node.get("parameters").get("frequency").asInt(), HeightRangePlacementModifier.uniform(MapVerticleAnchor.fixed(node.get("parameters").get("minHeight").asInt()), MapVerticleAnchor.fixed(node.get("parameters").get("maxHeight").asInt()))));// YOffset.getBottom is for bottom

placed.add(ORE_PLACED);

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
	




public static void onBiomeLoadingEvent(final BiomeLoadingEvent event) {
	
	if(FeatureCreep.debug_mode) {
	System.out.println("Adding FCOres");
	}
	
	for (int f = 0; f < placed.size(); f++) {
		event.getGeneration().addFeature(Feature.UNDERGROUND_ORES, placed.get(f));
	}
		
}
	
	
}
