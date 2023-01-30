package featurecreep.api.bg.orespawn;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.jboss.dmr.ModelNode;

import featurecreep.FeatureCreep;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Block;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DepthAverageDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
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
	Block replacedBlock = Registry.BLOCK.get(new Identifier(block_identifier[0], block_identifier[1]));
	
	String new_block = node.get("blocks").get(0).get("name").asString();//I needa Do this as a List eventually to handle the Array
	
	
	
	
	System.out.println(getCorrectNameSpace(new_block));
	String[] new_block_identifier = getCorrectNameSpace(new_block).split(":");
	Block newBlock = Registry.BLOCK.get(new Identifier(new_block_identifier[0], new_block_identifier[1]));
	
	
	
	
	System.out.println(replacedBlock.getName());
	System.out.println(newBlock.getName());

    final RuleTest RULE = new BlockMatchRuleTest(replacedBlock);

	
	ConfiguredFeature<?, ?> ORE_CONFIG = Feature.ORE
			.configure(new OreFeatureConfig(
			  RULE,
			  newBlock.getDefaultState(),
			  node.get("parameters").get("size").asInt())) // Vein size
			.decorate(Decorator.DEPTH_AVERAGE.configure(new DepthAverageDecoratorConfig(node.get("parameters").get("minHeight").asInt(), node.get("parameters").get("maxHeight").asInt()))
			.spreadHorizontally()
			.repeat(node.get("parameters").get("frequency").asInt())); // Number of veins per chunk
	

    RegistryKey<ConfiguredFeature<?, ?>> ORE_CONFIG_KEY = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
  	      new Identifier("featurecreep", name));
    
  	    Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ORE_CONFIG_KEY.getValue(), ORE_CONFIG);
  	    BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_CONFIG_KEY);
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
