package featurecreep.api.bg.datapacks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jboss.dmr.ModelNode;

import featurecreep.api.bg.orespawn.OreSpawnBasicConfig;
import featurecreep.api.bg.orespawn.OrespawnBasicFeatureParser;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class OreFeatureGenerator {

	
	public static void createOreFeatures()
	{
		List<OreSpawnBasicConfig>  configs = OrespawnBasicFeatureParser.configs;
		
		for(int i=0; i<configs.size(); i++) {
			
			
			ModelNode configured = new ModelNode();
			configured.get("type").set("minecraft:ore");
			configured.get("config").get("discard_chance_on_air_exposure").set(0.0);
			configured.get("config").get("size").set(configs.get(i).size);
			ModelNode targets = new ModelNode();//Gotta change this for when we do multiple blocks and biomes and replacements
			targets.get("state").get("Name").set(Registries.BLOCK.getId(configs.get(i).block).toString());
			targets.get("target").get("predicate_type").set("minecraft:tag_match");
			targets.get("target").get("tag").set("minecraft:stone_ore_replaceables");
			configured.get("config").get("targets").add(targets);
			
			//configs.get(i);
			
		
			ModelNode placed = new ModelNode();
			placed.get("feature").set("orespawn:"+configs.get(i).name);
			ModelNode count = new ModelNode();
			count.get("type").set("minecraft:count");
			count.get("count").set(configs.get(i).frequency);
			placed.get("placement").add(count);
			ModelNode square = new ModelNode();
square.get("type").set("minecraft:in_square");
placed.get("placement").add(square);
ModelNode height = new ModelNode();
height.get("type").set("minecraft:height_range");
height.get("height").get("type").set("minecraft:trapezoid");
height.get("height").get("max_inclusive").get("absolute").set(configs.get(i).maxY);
height.get("height").get("min_inclusive").get("absolute").set(configs.get(i).minY);
placed.get("placement").add(height);
ModelNode type = new ModelNode();
type.get("type").set("minecraft:biome");
placed.get("placement").add(type);


System.out.println(configured.toJSONString(false));
System.out.println(placed.toJSONString(false));

File configedfile = new File(DataPackLoader.datapacklocation + "/data/" + "orespawn" + "/worldgen/configured_feature/" + configs.get(i).name + ".json");
File placedfile = new File(DataPackLoader.datapacklocation + "/data/" + "orespawn" + "/worldgen/placed_feature/" + configs.get(i).name + ".json");

configedfile.getParentFile().mkdirs();
placedfile.getParentFile().mkdirs();

try {
	FileWriter configwriter = new FileWriter(configedfile);
	configwriter.write(configured.toJSONString(false));
	configwriter.close();
	FileWriter placedwriter = new FileWriter(placedfile);
	placedwriter.write(placed.toJSONString(false));
	placedwriter.close();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

configedfile.deleteOnExit();
placedfile.deleteOnExit();

//https://github.com/Ayutac/fabric-example-worldgen/blob/1.19.3/src/main/java/net/fabricmc/example/ExampleMod.java
RegistryKey<PlacedFeature> MY_ORE_PF = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("orespawn", configs.get(i).name));

BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, MY_ORE_PF);


		}
		
		
		
	}
	
		
}
