package featurecreep.legacy;


import java.util.List;
import java.util.function.Predicate;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.PlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class BiomeGen{
	



    final List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    final List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }
	

	public static void onBiomeLoadingEvent(final BiomeLoadingEvent event) {
		
		GenerationSettings.Builder builder = event.getGeneration();
		
	final ConfiguredFeature<?, ?> ORE_AMETHYST_OVERWORLD = Feature.ORE
				.configure(new OreFeatureConfig(
						OreConfiguredFeatures.BASE_STONE_OVERWORLD,
				  States.AMETHYST_ORE,
				  4)); // Vein size
				final PlacedFeature OVERWORLD_AMETHYST_ORE_PLACED_FEATURE = ORE_AMETHYST_OVERWORLD.withPlacement(
				    CountPlacementModifier.of(4), // number of veins per chunk
				    SquarePlacementModifier.of(), // spreading horizontally
				    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))); // height

		

		
		
		

	final ConfiguredFeature<?, ?> ORE_SAPPHIRE_OVERWORLD = Feature.ORE
	.configure(new OreFeatureConfig(
			OreConfiguredFeatures.BASE_STONE_OVERWORLD,
	  States.SAPPHIRE_ORE,
	  4)); // Vein size
	final PlacedFeature OVERWORLD_SAPPHIRE_ORE_PLACED_FEATURE = ORE_SAPPHIRE_OVERWORLD.withPlacement(
	    CountPlacementModifier.of(4), // number of veins per chunk
	    SquarePlacementModifier.of(), // spreading horizontally
	    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))); // height

		


	final ConfiguredFeature<?, ?> ORE_RUBY_OVERWORLD = Feature.ORE
	.configure(new OreFeatureConfig(
	  OreConfiguredFeatures.BASE_STONE_OVERWORLD,
	  States.RUBY_ORE,
	  8)); // Vein size
	final PlacedFeature OVERWORLD_RUBY_ORE_PLACED_FEATURE = ORE_RUBY_OVERWORLD.withPlacement(
		    CountPlacementModifier.of(10), // number of veins per chunk
		    SquarePlacementModifier.of(), // spreading horizontally
		    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))); // height

	final ConfiguredFeature<?, ?> ORE_ALUMINIUM_OVERWORLD = Feature.ORE
	.configure(new OreFeatureConfig(
	  OreConfiguredFeatures.BASE_STONE_OVERWORLD,
	  States.ALUMINIUM_ORE,
	  6)); // Vein size
	final PlacedFeature OVERWORLD_ALUMINIUM_ORE_PLACED_FEATURE = ORE_ALUMINIUM_OVERWORLD.withPlacement(
		    CountPlacementModifier.of(12), // number of veins per chunk
		    SquarePlacementModifier.of(), // spreading horizontally
		    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))); // height
	final ConfiguredFeature<?, ?> ORE_URANIUM_OVERWORLD = Feature.ORE
	.configure(new OreFeatureConfig(
	  OreConfiguredFeatures.BASE_STONE_OVERWORLD,
	  States.URANIUM_ORE,
	  3)); // Vein size
	final PlacedFeature OVERWORLD_URANIUM_ORE_PLACED_FEATURE = ORE_URANIUM_OVERWORLD.withPlacement(
		    CountPlacementModifier.of(2), // number of veins per chunk
		    SquarePlacementModifier.of(), // spreading horizontally
		    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))); // height
	final ConfiguredFeature<?, ?> ORE_TITANIUM_OVERWORLD = Feature.ORE
	.configure(new OreFeatureConfig(
	  OreConfiguredFeatures.BASE_STONE_OVERWORLD,
	  States.TITANIUM_ORE,
	  3)); // Vein size
	final PlacedFeature OVERWORLD_TITANIUM_ORE_PLACED_FEATURE = ORE_TITANIUM_OVERWORLD.withPlacement(
		    CountPlacementModifier.of(2), // number of veins per chunk
		    SquarePlacementModifier.of(), // spreading horizontally
		    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))); // height

	final ConfiguredFeature<?, ?> ORE_TIGERS_EYE_OVERWORLD = Feature.ORE
	.configure(new OreFeatureConfig(
	  OreConfiguredFeatures.BASE_STONE_OVERWORLD,
	  States.TIGERS_EYE_ORE,
	  7)); // Vein size
	final PlacedFeature OVERWORLD_TIGERS_EYE_ORE_PLACED_FEATURE = ORE_TIGERS_EYE_OVERWORLD.withPlacement(
		    CountPlacementModifier.of(5), // number of veins per chunk
		    SquarePlacementModifier.of(), // spreading horizontally
		    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))); // height

	final ConfiguredFeature<?, ?> ORE_SALT_OVERWORLD = Feature.ORE
	.configure(new OreFeatureConfig(
	  OreConfiguredFeatures.BASE_STONE_OVERWORLD,
	  States.SALT_ORE,
	  8)); // Vein size
	final PlacedFeature OVERWORLD_SALT_ORE_PLACED_FEATURE = ORE_SALT_OVERWORLD.withPlacement(
		    CountPlacementModifier.of(18), // number of veins per chunk
		    SquarePlacementModifier.of(), // spreading horizontally
		    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))); // height


	final ConfiguredFeature<?, ?> ORE_OIL_OVERWORLD = Feature.ORE
	.configure(new OreFeatureConfig(
	  OreConfiguredFeatures.BASE_STONE_OVERWORLD,
	  States.OIL_ORE,
	  8)); // Vein size
	final PlacedFeature OVERWORLD_OIL_ORE_PLACED_FEATURE = ORE_OIL_OVERWORLD.withPlacement(
		    CountPlacementModifier.of(18), // number of veins per chunk
		    SquarePlacementModifier.of(), // spreading horizontally
		    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))); // height
	final ConfiguredFeature<?, ?> ORE_GASOLINE_PETROL_OVERWORLD = Feature.ORE
	.configure(new OreFeatureConfig(
	  OreConfiguredFeatures.BASE_STONE_OVERWORLD,
	  States.GAS_ORE,
	  8)); // Vein size
	final PlacedFeature OVERWORLD_GASOLINE_PETROL_ORE_PLACED_FEATURE = ORE_GASOLINE_PETROL_OVERWORLD.withPlacement(
		    CountPlacementModifier.of(18), // number of veins per chunk
		    SquarePlacementModifier.of(), // spreading horizontally
		    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))); // height


		
	
	
	
    
        		   
                   Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("featurecreep", "overworld_amethyst_ore"), ORE_AMETHYST_OVERWORLD);
                   Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("featurecreep", "overworld_amethyst_ore"), OVERWORLD_AMETHYST_ORE_PLACED_FEATURE);
                   builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_AMETHYST_ORE_PLACED_FEATURE);
    
                   Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("featurecreep", "overworld_ruby_ore"), ORE_RUBY_OVERWORLD);
                   Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("featurecreep", "overworld_ruby_ore"), OVERWORLD_RUBY_ORE_PLACED_FEATURE);
                   builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_RUBY_ORE_PLACED_FEATURE);
    

                   Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("featurecreep", "overworld_aluminium_ore"), ORE_ALUMINIUM_OVERWORLD);
                   Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("featurecreep", "overworld_aluminium_ore"), OVERWORLD_ALUMINIUM_ORE_PLACED_FEATURE);
                   builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_ALUMINIUM_ORE_PLACED_FEATURE);
               
                   Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("featurecreep", "overworld_titanium_ore"), ORE_TITANIUM_OVERWORLD);
                   Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("featurecreep", "overworld_titanium_ore"), OVERWORLD_TITANIUM_ORE_PLACED_FEATURE);
                   builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_TITANIUM_ORE_PLACED_FEATURE);
    
    
                   
                   Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("featurecreep", "overworld_sapphire_ore"), ORE_SAPPHIRE_OVERWORLD);
                   Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("featurecreep", "overworld_sapphire_ore"), OVERWORLD_SAPPHIRE_ORE_PLACED_FEATURE);
                   builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_SAPPHIRE_ORE_PLACED_FEATURE);
    
                   Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("featurecreep", "overworld_uranium_ore"), ORE_URANIUM_OVERWORLD);
                   Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("featurecreep", "overworld_uranium_ore"), OVERWORLD_URANIUM_ORE_PLACED_FEATURE);
                   builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_URANIUM_ORE_PLACED_FEATURE);
    
                       
                   Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("featurecreep", "overworld_salt_ore"), ORE_SALT_OVERWORLD);
                   Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("featurecreep", "overworld_salt_ore"), OVERWORLD_SALT_ORE_PLACED_FEATURE);
                   builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_SALT_ORE_PLACED_FEATURE);
    
                   
                   Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("featurecreep", "overworld_oil_ore"), ORE_OIL_OVERWORLD);
                   Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("featurecreep", "overworld_oil_ore"), OVERWORLD_OIL_ORE_PLACED_FEATURE);
                   builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_OIL_ORE_PLACED_FEATURE);
    
                   Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("featurecreep", "overworld_gasoline_petrol_ore"), ORE_GASOLINE_PETROL_OVERWORLD);
                   Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("featurecreep", "overworld_gasoline_petrol_ore"), OVERWORLD_GASOLINE_PETROL_ORE_PLACED_FEATURE);
                   builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_GASOLINE_PETROL_ORE_PLACED_FEATURE);
                   
                   
                   Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("featurecreep", "overworld_tigers_eye_ore"), ORE_TIGERS_EYE_OVERWORLD);
                   Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("featurecreep", "overworld_tigers_eye_ore"), OVERWORLD_TIGERS_EYE_ORE_PLACED_FEATURE);
                   builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_TIGERS_EYE_ORE_PLACED_FEATURE);
    
    
                       		
		
	}	
	
	
	
	
}
