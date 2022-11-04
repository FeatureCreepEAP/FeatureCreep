package featurecreep.legacy;

import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;

public class ConfiguredFfeatures {

	
	public static ConfiguredFeature<?, ?> ORE_AMETHYST = Feature.ORE
			.configure(new OreFeatureConfig(
			  OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
			  States.AMETHYST_ORE,
			  4)) // Vein size
			.range(new RangeDecoratorConfig(
			  // You can also use one of the other height providers if you don't want a uniform distribution
			  UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(64)))) // Inclusive min and max height
			.spreadHorizontally()
			.repeat(4); // Number of veins per chunk
//	private static ConfiguredFeature<?, ?> register(String string, ConfiguredFeature<?, ?> count) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	

	public static ConfiguredFeature<?, ?> ORE_RUBY_LAVA = Feature.ORE
.configure(new OreFeatureConfig(
  OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
  States.RUBY_ORE,
  8)) // Vein size
.range(new RangeDecoratorConfig(
  // You can also use one of the other height providers if you don't want a uniform distribution
  UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(64)))) // Inclusive min and max height
.spreadHorizontally()
.repeat(10); // Number of veins per chunk



	public static ConfiguredFeature<?, ?> ORE_ALUMINIUM_OVERWORLD = Feature.ORE
.configure(new OreFeatureConfig(
  OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
  States.ALUMINIUM_ORE,
  6)) // Vein size
.range(new RangeDecoratorConfig(
  // You can also use one of the other height providers if you don't want a uniform distribution
  UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(64)))) // Inclusive min and max height
.spreadHorizontally()
.repeat(12); // Number of veins per chunk



	public static ConfiguredFeature<?, ?> ORE_URANIUM = Feature.ORE
.configure(new OreFeatureConfig(
  OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
  States.URANIUM_ORE,
  3)) // Vein size
.range(new RangeDecoratorConfig(
  // You can also use one of the other height providers if you don't want a uniform distribution
  UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(64)))) // Inclusive min and max height
.spreadHorizontally()
.repeat(2); // Number of veins per chunk

	public static ConfiguredFeature<?, ?> ORE_TITANIUM = Feature.ORE
.configure(new OreFeatureConfig(
  OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
  States.TITANIUM_ORE,
  3)) // Vein size
.range(new RangeDecoratorConfig(
  // You can also use one of the other height providers if you don't want a uniform distribution
  UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(64)))) // Inclusive min and max height
.spreadHorizontally()
.repeat(2); // Number of veins per chunk


	public static ConfiguredFeature<?, ?> ORE_TIGERS_EYE = Feature.ORE
.configure(new OreFeatureConfig(
  OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
  States.TIGERS_EYE_ORE,
  7)) // Vein size
.range(new RangeDecoratorConfig(
  // You can also use one of the other height providers if you don't want a uniform distribution
  UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(64)))) // Inclusive min and max height
.spreadHorizontally()
.repeat(5); // Number of veins per chunk

	public static ConfiguredFeature<?, ?> ORE_SALT = Feature.ORE
.configure(new OreFeatureConfig(
  OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
  States.SALT_ORE,
  8)) // Vein size
.range(new RangeDecoratorConfig(
  // You can also use one of the other height providers if you don't want a uniform distribution
  UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(64)))) // Inclusive min and max height
.spreadHorizontally()
.repeat(18); // Number of veins per chunk


	public static ConfiguredFeature<?, ?> ORE_OIL = Feature.ORE
.configure(new OreFeatureConfig(
  OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
  States.OIL_ORE,
  8)) // Vein size
.range(new RangeDecoratorConfig(
  // You can also use one of the other height providers if you don't want a uniform distribution
  UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(64)))) // Inclusive min and max height
.spreadHorizontally()
.repeat(18); // Number of veins per chunk

	public static ConfiguredFeature<?, ?> ORE_GAS = Feature.ORE
.configure(new OreFeatureConfig(
  OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
  States.GAS_ORE,
  8)) // Vein size
.range(new RangeDecoratorConfig(
  // You can also use one of the other height providers if you don't want a uniform distribution
  UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(64)))) // Inclusive min and max height
.spreadHorizontally()
.repeat(18); // Number of veins per chunk

	
	
	private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
		return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, key, configuredFeature);
	}
	
	
	}
	

	

	
	

