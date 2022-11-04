package featurecreep.legacy;

import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DepthAverageDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class ConfiguredFfeatures {
	public static final ConfiguredFeature<?, ?> ORE_RUBY_LAVA = register("ore_ruby_lava", Feature.ORE.configure(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.LAVA), FCBlocks.RUBY_ORE.get().getDefaultState(), 8)).decorate(Decorator.DEPTH_AVERAGE.configure(new DepthAverageDecoratorConfig(24, 24))).spreadHorizontally().repeat(10));
	public static final ConfiguredFeature<?, ?> ORE_AMETHYST = register("ore_amethyst", Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, States.AMETHYST_ORE, 4)).decorate(Decorator.DEPTH_AVERAGE.configure(new DepthAverageDecoratorConfig(28, 24))).spreadHorizontally().repeat(4));
	public static final ConfiguredFeature<?, ?> ORE_URANIUM = register("ore_uranium", Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, States.URANIUM_ORE, 3)).decorate(Decorator.DEPTH_AVERAGE.configure(new DepthAverageDecoratorConfig(12, 12))).spreadHorizontally().repeat(2));
	public static final ConfiguredFeature<?, ?> ORE_TITANIUM = register("ore_titanium", Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, States.TITANIUM_ORE, 3)).decorate(Decorator.DEPTH_AVERAGE.configure(new DepthAverageDecoratorConfig(12, 12))).spreadHorizontally().repeat(2));
	public static final ConfiguredFeature<?, ?> ORE_TIGERS_EYE = register("ore_tigers_eye", Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, States.TIGERS_EYE_ORE, 7)).decorate(Decorator.DEPTH_AVERAGE.configure(new DepthAverageDecoratorConfig(24, 24))).spreadHorizontally().repeat(5));
	public static final ConfiguredFeature<?, ?> ORE_SALT = register("ore_salt", Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, States.SALT_ORE, 8)).decorate(Decorator.DEPTH_AVERAGE.configure(new DepthAverageDecoratorConfig(48, 48))).spreadHorizontally().repeat(18));
	public static final ConfiguredFeature<?, ?> ORE_OIL = register("ore_oil", Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, States.OIL_ORE, 20)).decorate(Decorator.DEPTH_AVERAGE.configure(new DepthAverageDecoratorConfig(48, 48))).spreadHorizontally().repeat(20));
	public static final ConfiguredFeature<?, ?> ORE_GAS = register("ore_gas", Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, States.GAS_ORE, 10)).decorate(Decorator.DEPTH_AVERAGE.configure(new DepthAverageDecoratorConfig(48, 48))).spreadHorizontally().repeat(10));

//	private static ConfiguredFeature<?, ?> register(String string, ConfiguredFeature<?, ?> count) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
	
	private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
		return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, key, configuredFeature);
	}
	
	
	}
	

	

	
	

