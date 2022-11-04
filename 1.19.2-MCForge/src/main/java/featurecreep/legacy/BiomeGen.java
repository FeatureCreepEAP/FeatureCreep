package featurecreep.legacy;


import java.util.List;

import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

public class BiomeGen{
	




private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
    return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
}

private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
    return modifiers(CountPlacementModifier.of(count), heightModifier);

}


	
/*
	public static void onBiomeLoadingEvent(final BiomeLoadingEvent event) {
		
		GenerationSettings.Builder builder = event.getGeneration();
		

		
	
		final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_AMETHYST_OVERWORLD = ConfiguredFeatures.register("ore_amethyst", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, States.AMETHYST_ORE), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, States.AMETHYST_ORE)), 4));
		 final RegistryEntry<PlacedFeature> OVERWORLD_AMETHYST_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_amethyst_placed", ORE_AMETHYST_OVERWORLD, modifiersWithCount(4, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));
         builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_AMETHYST_ORE_PLACED_FEATURE);

         
			final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_SAPPHIRE_OVERWORLD = ConfiguredFeatures.register("ore_sapphire", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, States.SAPPHIRE_ORE), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, States.SAPPHIRE_ORE)), 4));
			 final RegistryEntry<PlacedFeature> OVERWORLD_SAPPHIRE_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_sapphire_placed", ORE_SAPPHIRE_OVERWORLD, modifiersWithCount(4, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));
             builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_SAPPHIRE_ORE_PLACED_FEATURE);




		 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_RUBY_OVERWORLD = ConfiguredFeatures.register("ore_ruby", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, States.RUBY_ORE), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, States.RUBY_ORE)), 8));
		 final RegistryEntry<PlacedFeature> OVERWORLD_RUBY_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_ruby_placed", ORE_RUBY_OVERWORLD, modifiersWithCount(10, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));
         builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_RUBY_ORE_PLACED_FEATURE);


		 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_ALUMINIUM_OVERWORLD = ConfiguredFeatures.register("ore_aluminium", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, States.ALUMINIUM_ORE), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, States.ALUMINIUM_ORE)), 6));
		 final RegistryEntry<PlacedFeature> OVERWORLD_ALUMINIUM_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_aluminium_placed", ORE_ALUMINIUM_OVERWORLD, modifiersWithCount(12, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));
         builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_ALUMINIUM_ORE_PLACED_FEATURE);




		 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_URANIUM_OVERWORLD = ConfiguredFeatures.register("ore_uranium", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, States.URANIUM_ORE), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, States.URANIUM_ORE)), 3));
		 final RegistryEntry<PlacedFeature> OVERWORLD_URANIUM_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_uranium_placed", ORE_URANIUM_OVERWORLD, modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));
         builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_URANIUM_ORE_PLACED_FEATURE);




		 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_TITANIUM_OVERWORLD = ConfiguredFeatures.register("ore_titanium", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, States.TITANIUM_ORE), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, States.TITANIUM_ORE)), 3));
		 final RegistryEntry<PlacedFeature> OVERWORLD_TITANIUM_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_titanium_placed", ORE_TITANIUM_OVERWORLD, modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));
         builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_TITANIUM_ORE_PLACED_FEATURE);




		 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_TIGERS_EYE_OVERWORLD = ConfiguredFeatures.register("ore_tigers_eye", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, States.TIGERS_EYE_ORE), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, States.TIGERS_EYE_ORE)), 7));
		 final RegistryEntry<PlacedFeature> OVERWORLD_TIGERS_EYE_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_tigers_eye_placed", ORE_TIGERS_EYE_OVERWORLD, modifiersWithCount(5, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));
         builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_TIGERS_EYE_ORE_PLACED_FEATURE);




		 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_SALT_OVERWORLD = ConfiguredFeatures.register("ore_salt", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, States.SALT_ORE), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, States.SALT_ORE)), 8));
		 final RegistryEntry<PlacedFeature> OVERWORLD_SALT_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_salt_placed", ORE_AMETHYST_OVERWORLD, modifiersWithCount(18, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));
         builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_SALT_ORE_PLACED_FEATURE);



		 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_OIL_OVERWORLD = ConfiguredFeatures.register("ore_unprocessed_oil", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, States.OIL_ORE), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, States.OIL_ORE)), 8));
		 final RegistryEntry<PlacedFeature> OVERWORLD_OIL_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_oil_placed", ORE_OIL_OVERWORLD, modifiersWithCount(18, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));
         builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_OIL_ORE_PLACED_FEATURE);


		 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_GASOLINE_PETROL_OVERWORLD = ConfiguredFeatures.register("ore_gasoline_petrol", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, States.GAS_ORE), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, States.GAS_ORE)), 8));
		 final RegistryEntry<PlacedFeature> OVERWORLD_GASOLINE_PETROL_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_gasoline_petrol_placed", ORE_GASOLINE_PETROL_OVERWORLD, modifiersWithCount(18, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));
         builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_GASOLINE_PETROL_ORE_PLACED_FEATURE);

	
    
        		   
               
    
                  
    

               
                  
    
    
                   
                   
    
                 
    
                       
                  
    
              
    
                   
                   
                   
    
    
                       		
		
	}	
	*/
	
	
	
}
