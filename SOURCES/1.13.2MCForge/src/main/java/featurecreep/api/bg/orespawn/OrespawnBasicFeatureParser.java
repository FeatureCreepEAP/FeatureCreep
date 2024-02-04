package featurecreep.api.bg.orespawn;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Predicate;

import org.jboss.dmr.ModelNode;

import featurecreep.FeatureCreep;
import game.Biome;
import game.Biomes;
import game.Block;
import game.CompositeFeature;
import game.GameRegistries;
import game.IBlockstate;
import game.OreGen;
import game.RangeDecoratorConfiguration;
import game.ResourceConfig;
import game.ResourceLocation;
import game.WorldGenFeature;
import obf.class_unknown_1069.Feature;

public class OrespawnBasicFeatureParser {
    public static void spawnOresFromDefaultConfig() {
        String string1 = new String(FeatureCreep.gamepath.toString() + "/orespawn/config/");
        File file2 = new File(string1);
        String[] contents = file2.list();
        System.out.println("List of files and directories in the specified directory:");
        if (contents != null) {
            for (int i = 0; i < contents.length; ++i) {
                System.out.println("FeatureCreep is trying to load " + contents[i]);
                System.out.println(string1 + contents[i] + "/");
                splitOS3Basic(getModelNodesFromFile(string1 + contents[i] + "/"));
            }
        }
        else {
            FeatureCreep.LOGGER.info("No DMR Items Found");
        }
    }
    
    public static ModelNode getModelNodesFromFile(String string) {
        if (string.contains(".json")) {
            try {
                return ModelNode.fromJSONStream((InputStream)new FileInputStream(string));
            }
            catch (final IOException e) {
                e.printStackTrace();
                return new ModelNode();
            }
        }
        try {
            return ModelNode.fromStream((InputStream)new FileInputStream(string));
        }
        catch (final IOException e) {
            e.printStackTrace();
            return new ModelNode();
        }
    }
    
    public static void splitOS3Basic(ModelNode modelNode) {
        List<ModelNode> list2 = modelNode.get("spawns").asList();
        for (final ModelNode rowNode : list2) {
            System.out.println(rowNode.asString().split("\"")[1]);
            parseOS3Basic(rowNode.get(0), rowNode.asString().split("\"")[1]);
        }
    }
    
    public static void parseOS3Basic(ModelNode modelNode, String string) {
        if (modelNode.get("enabled").asBoolean()) {
            String string3 = new String();
            if (modelNode.get("replaces").asString().equals("default")) {
                string3 = "minecraft:stone";
            }
            else {
                string3 = modelNode.get("replaces").asString();
            }
            string3 = getCorrectNameSpace(string3);
            String[] block_identifier = string3.split(":");
            Block block5 = GameRegistries.BLOCKS.get(new ResourceLocation(block_identifier[0], block_identifier[1]));
            String string6 = modelNode.get("blocks").get(0).get("name").asString();
            System.out.println(getCorrectNameSpace(string6));
            String[] new_block_identifier = getCorrectNameSpace(string6).split(":");
            Block block8 = GameRegistries.BLOCKS.get(new ResourceLocation(new_block_identifier[0], new_block_identifier[1]));
            System.out.println(block5.getLocalisedNameAsTextObject());
            System.out.println(block8.getLocalisedNameAsTextObject());
            Predicate<IBlockstate> predicate9 = ResourceConfig.var_unknown_117567;
            CompositeFeature compositeFeature10 = Biome.<ResourceConfig, RangeDecoratorConfiguration>def_unknown_138509(WorldGenFeature.var_unknown_117484, new ResourceConfig(predicate9, block8.getDefaultState(), modelNode.get("parameters").get("size").asInt()), Biome.var_unknown_116468, new RangeDecoratorConfiguration(modelNode.get("parameters").get("frequency").asInt(), modelNode.get("parameters").get("minHeight").asInt(), modelNode.get("parameters").get("minHeight").asInt(), modelNode.get("parameters").get("maxHeight").asInt()));
            Biomes.SMALL_END_ISLANDS.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.END_MIDLANDS.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.END_HIGHLANDS.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.END_BARRENS.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.WARM_OCEAN.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.LUKEWARM_OCEAN.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.COLD_OCEAN.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.DEEP_WARM_OCEAN.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.DEEP_LUKEWARM_OCEAN.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.DEEP_COLD_OCEAN.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.DEEP_FROZEN_OCEAN.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.THE_VOID.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.SUNFLOWER_PLAINS.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.DESERT_LAKES.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.GRAVELLY_MOUNTAINS.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.FLOWER_FOREST.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.TAIGA_MOUNTAINS.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.SWAMP_HILLS.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.ICE_SPIKES.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.var_unknown_116534.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.var_unknown_116564.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.TALL_BIRCH_FOREST.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.TALL_BIRCH_HILLS.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.DARK_FOREST_HILLS.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.SNOWY_TAIGA_MOUNTAINS.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.GIANT_SPRUCE_TAIGA.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.GIANT_SPRUCE_TAIGA_HILLS.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.MODIFIED_GRAVELLY_MOUNTAINS.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.SHATTERED_SAVANNA.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.SHATTERED_SAVANNA_PLATEAU.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.ERODED_BADLANDS.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
            Biomes.MODIFIED_BADLANDS_PLATEAU.def_unknown_138511(Feature.UNDERGROUND_ORES, compositeFeature10);
        }
    }
    
    public static String getCorrectNameSpace(String string) {
        String string2 = new String(string);
        if (string2.contains("vanilla:")) {
            string2 = string2.replace("vanilla:", "minecraft:");
        }
        if (string2.contains("dangerzone:")) {
            string2 = string2.replace("dangerzone:", "minecraft:");
        }
        return string2;
    }
}
