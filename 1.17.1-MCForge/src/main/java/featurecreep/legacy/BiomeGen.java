package featurecreep.legacy;

import java.util.Objects;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.GenerationStep;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.registries.ForgeRegistries;


public class BiomeGen {

	public static void onBiomeLoadingEvent(final BiomeLoadingEvent event) {

		BiomeGenerationSettingsBuilder gen = event.getGeneration();

		RegistryKey<Biome> biome = RegistryKey.of(ForgeRegistries.Keys.BIOMES, Objects.requireNonNull(event.getName(), "Who registered null name biome, naming criticism!"));
		
		if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD)) {
		addOverworldOres(gen);
		} 

	}

	public static void addOverworldOres(BiomeGenerationSettingsBuilder gen) {
		gen.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFfeatures.ORE_RUBY_LAVA);
		gen.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFfeatures.ORE_AMETHYST);
		gen.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFfeatures.ORE_URANIUM);
		gen.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFfeatures.ORE_TITANIUM);
		gen.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFfeatures.ORE_TIGERS_EYE);
		gen.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFfeatures.ORE_SALT);
		gen.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFfeatures.ORE_OIL);
		gen.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFfeatures.ORE_GAS);
	}

	
}
