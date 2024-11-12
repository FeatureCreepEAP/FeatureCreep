package featurecreep.api.bg.datapacks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jboss.dmr.ModelNode;

import featurecreep.FeatureCreep;
import featurecreep.api.bg.PackLoader;
import featurecreep.api.bg.orespawn.OreSpawnBasicConfig;
import featurecreep.api.bg.orespawn.OrespawnBasicFeatureParser;
import featurecreep.api.io.BasicIO;
import game.BuiltInRegistries;
import game.GenerationPlacement;
import game.RegistryKey;
import game.RegistryKeys;
import game.ResourceLocation;
import game.StageGeneration.Feature;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;

public class OreFeatureGenerator {

	public static void createOreFeatures() {
		List<OreSpawnBasicConfig> configs = OrespawnBasicFeatureParser.configs;

		for (int i = 0; i < configs.size(); i++) {

			ModelNode configured = new ModelNode();
			configured.get("type").set("minecraft:ore");
			configured.get("config").get("discard_chance_on_air_exposure").set(0.0);
			configured.get("config").get("size").set(configs.get(i).size);
			ModelNode targets = new ModelNode();// Gotta change this for when we do multiple blocks and biomes and
												// replacements
			targets.get("state").get("Name").set(BuiltInRegistries.block.getName(configs.get(i).block).toString());
			targets.get("target").get("predicate_type").set("minecraft:tag_match");
			targets.get("target").get("tag").set("minecraft:stone_ore_replaceables");
			configured.get("config").get("targets").add(targets);

			// configs.get(i);

			ModelNode placed = new ModelNode();
			placed.get("feature").set("orespawn:" + configs.get(i).name);
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

			if (FeatureCreep.debug_mode) {
				System.out.println(configured.toJSONString(false));
				System.out.println(placed.toJSONString(false));
			}

			
			
			
			String configedfile = new String("data/" + "orespawn"
					+ "/worldgen/configured_feature/" + configs.get(i).name + ".json");
			String placedfile = new String("data/" + "orespawn"
					+ "/worldgen/placed_feature/" + configs.get(i).name + ".json");

PackLoader.entries.put(configedfile, BasicIO.stringToByteArray(configured.toJSONString(false)));
PackLoader.entries.put(placedfile, BasicIO.stringToByteArray(placed.toJSONString(false)));

//https://github.com/Ayutac/fabric-example-worldgen/blob/1.19.3/src/main/java/net/fabricmc/example/ExampleMod.java
			RegistryKey<GenerationPlacement> MY_ORE_PF = RegistryKey.of(RegistryKeys.GENERATION_PLACEMENT,
					ResourceLocation.fromSeperated("orespawn", configs.get(i).name));

			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), Feature.UNDERGROUND_ORES, MY_ORE_PF);

		}

	}

}
