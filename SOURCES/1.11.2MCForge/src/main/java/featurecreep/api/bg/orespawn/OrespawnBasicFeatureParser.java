package featurecreep.api.bg.orespawn;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jboss.dmr.ModelNode;

import featurecreep.FeatureCreep;
import game.Block;
import game.OreGen;
import game.ResourceLocation;
import game.World;
import game.WorldBuilder;
import game.WorldDecorationGenerator;

public class OrespawnBasicFeatureParser {

	

	public static List<OreSpawnBasicConfig> configs = new ArrayList<OreSpawnBasicConfig>();
	
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
	Block replacedBlock = Block.registry.fromRL(new ResourceLocation(block_identifier[0], block_identifier[1]));
	
	String new_block = node.get("blocks").get(0).get("name").asString();//I needa Do this as a List eventually to handle the Array
	
	
	
	
	System.out.println(getCorrectNameSpace(new_block));
	String[] new_block_identifier = getCorrectNameSpace(new_block).split(":");
	Block newBlock = Block.registry.fromRL(new ResourceLocation(new_block_identifier[0], new_block_identifier[1]));
	
	
	
	
	System.out.println(replacedBlock.getUnlocalisedName());
	System.out.println(newBlock.getUnlocalisedName());

    OreSpawnBasicConfig config = new OreSpawnBasicConfig(name, newBlock, node.get("parameters").get("size").asInt(), node.get("parameters").get("frequency").asInt(), node.get("parameters").get("minHeight").asInt(), node.get("parameters").get("maxHeight").asInt());
configs.add(config);

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


	
public static void applyOres(World world, Random rand, WorldBuilder dec)
{
	for (int j = 0; j < configs.size(); j++) {

	//Mirror.of(dec).method("func_76795_a", World.class, Random.class, int.class, WorldGenerator.class, int.class, int.class).invoke(world, rand, configs.get(j).frequency, new WorldGenMinable(configs.get(j).block.getDefaultState(), configs.get(j).size), configs.get(j).minY, configs.get(j).maxY);;	
	//	accs.invoke(world, rand, configs.get(j).frequency, new WorldGenMinable(configs.get(j).block.getDefaultState(), configs.get(j).size), configs.get(j).minY, configs.get(j).maxY);
		//Mirror having issues
		try {
		Method meth	= dec.getClass().getDeclaredMethod("func_76795_a", World.class, Random.class, int.class, WorldDecorationGenerator.class, int.class, int.class);
	 meth.setAccessible(true);
	 meth.invoke(dec, world, rand, configs.get(j).frequency, new OreGen(configs.get(j).block.defaultBlockstate(), configs.get(j).size), configs.get(j).minY, configs.get(j).maxY);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}	
	
}
	
	
}
