package featurecreep.legacy;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.Material;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.SwordItem;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.DiskFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MiscConfiguredFeatures;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

public class FeatureCreepMC {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final String MOD_ID = "featurecreep";
	public static final Logger LOGGER = LogManager.getLogger("modid");

	
   // public static final Item AMETHYST = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item RUBY = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item TIGERS_EYE = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item TITANIUM = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item TITANIUM_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item URANIUM = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item URANIUM_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item ALUMINIUM = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item COPPER_INGOT = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item TIN_INGOT = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item SILVER_INGOT = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item PINK_TOURMALINE = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item PINK_TOURMALINE_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item STEEL_INGOT = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item Oil = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item Salt = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item MobzillaScale = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item QueenScale = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item MothScale = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item PLATINUM_INGOT = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item LAVA_EEL = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item PEACKOCK_FEATHER = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item SAPPHIRE = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item GASOLINE_PETROL = new Item(new Item.Settings().group(ItemGroup.MATERIALS));

    public static final Item MINERS_DREAM = new MinersDream(new Item.Settings().group(ItemGroup.MISC), -5, 5, 0, 5, 0, 50);
    public static final Item LARGE_MINERS_DREAM = new MinersDream(new Item.Settings().group(ItemGroup.MISC), -250, 250, -250, 250, -50, 250);
    public static final Item DIGGER = new Digger(new Item.Settings().group(ItemGroup.MISC), -5, 5, 0, 5, 0, 50);

    
    public static final Item TIGERS_EYE_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));

    public static final Item CATS_EYE = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item CATS_EYE_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item WHITE_TOURMALINE = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item WHITE_TOURMALINE_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item TOURMALINE = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item TOURMALINE_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item RUBY_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item ALUMINIUM_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item COPPER_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item SILVER_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item PLATINUM_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item TIN_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item STEEL_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item SAPPHIRE_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item AMETHYST_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));

    
    
    
     public static final Item KYANITE_INGOT = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item KYANITE_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
     public static final Item CELESTIAL_INGOT = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item CELESTIAL_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
      public static final Item EXTRA_CELESTIAL_INGOT = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item EXTRA_CELESTIAL_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
      public static final Item WAX_INGOT = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item WAX_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
      public static final Item CALCIUM_INGOT = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item CALCIUM_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
      public static final Item LEAD_INGOT = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item LEAD_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    
    
    public static final Block CORN_PLANT = new FCSugarPlant(AbstractBlock.Settings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));
    public static final Block TOMATO_PLANT = new TomatoPlant(AbstractBlock.Settings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));
    public static final Block UNCONFINED_TOMATO_PLANT = new UnConfindedTomatoPlant(AbstractBlock.Settings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));
    public static final Block QUINOA_PLANT = new FCSugarPlant(AbstractBlock.Settings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));

    public static final Block RADDISH_PLANT = new RaddishPlant(AbstractBlock.Settings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));
    public static final Block STRAWBERRY_PLANT = new StrawberryPlant(AbstractBlock.Settings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));
    public static final Block LETTUCE_PLANT = new LettucePlant(AbstractBlock.Settings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));
    public static final Block RICE_PLANT = new RicePlant(AbstractBlock.Settings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));


    
public static FoodComponent FOOD_RAW_BACON= new FoodComponent.Builder().hunger(2).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 2), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F).build();
public static FoodComponent FOOD_COOKED_BACON= new FoodComponent.Builder().hunger(10).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 2), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 3000, 2), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F).build();
public static FoodComponent FOOD_BUTTER_CANDY= new FoodComponent.Builder().hunger(3).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 2000, 0), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 2000, 0), 1.0F).build();
public static FoodComponent FOOD_CRYSTAL_APPLE= new FoodComponent.Builder().hunger(10).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 3000, 0), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 3000, 0), 1.0F).build();
public static FoodComponent FOOD_EQUESTRIA_APPLE= new FoodComponent.Builder().hunger(10).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 3000, 0), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 3000, 0), 1.0F).build();
public static FoodComponent FOOD_LOVE_FOOD= new FoodComponent.Builder().hunger(15).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 2), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 6000, 3), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 1), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 5000, 2), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 5000, 0), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000, 2), 1.0F).build();
public static FoodComponent FOOD_POPCORN= new FoodComponent.Builder().hunger(3).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 2000, 0), 1.0F).build();
public static FoodComponent FOOD_BUTTER_FOOD= new FoodComponent.Builder().hunger(3).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 4), 1.0F).build();
public static FoodComponent FOOD_CORN_DOG= new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 4), 1.0F).build();
public static FoodComponent FOOD_COOKED_CORN_DOG = new FoodComponent.Builder().hunger(6).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 4000, 0), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 3000, 0), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 2000, 4), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 4), 1.0F).build();
public static FoodComponent FOOD_RAW_CRAB_MEAT= new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 2), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 2), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 6), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 600, 2), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 4), 1.0F).build();
public static FoodComponent FOOD_COOKED_CRAB_MEAT= new FoodComponent.Builder().hunger(10).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 3000, 3), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 4000, 3), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 2000, 4), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1000, 4), 1.0F).build();
public static FoodComponent FOOD_CHEESE= new FoodComponent.Builder().hunger(3).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 600, 4), 1.0F).build();
public static FoodComponent FOOD_SALAD= new FoodComponent.Builder().hunger(15).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 2), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 5000, 2), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 2), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 2000, 3), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 2000, 3), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 5), 1.0F).build();
public static FoodComponent FOOD_BLT= new FoodComponent.Builder().hunger(13).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 5000, 2), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 5000, 2), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 1000, 4), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20, 0), 1.0F).build();
public static FoodComponent FOOD_CRAB_PATTY= new FoodComponent.Builder().hunger(13).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 1), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 5000, 1), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 1), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 2000, 2), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 2000, 2), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 4), 1.0F).build();
public static FoodComponent FOOD_MAGIC_APPLE= new FoodComponent.Builder().hunger(15).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 5000, 1), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 5000, 1), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 1), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 2000, 1), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 2000, 1), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 3), 1.0F).build();
public static FoodComponent FOOD_PEACH= new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
.build();
public static FoodComponent FOOD_RAW_PEACOCK= new FoodComponent.Builder().hunger(8).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 3), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 5), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 5), 1.0F).build();
public static FoodComponent FOOD_COOCKED_PEACOCK= new FoodComponent.Builder().hunger(13).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 3000, 4), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 4000, 4), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 2000, 6), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 200, 2), 1.0F).build();
public static FoodComponent FOOD_BLUE_FISH= new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F)
.build();
public static FoodComponent FOOD_BUTTERED_POPCORN= new FoodComponent.Builder().hunger(10).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 1), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 1), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 5), 1.0F).build();
public static FoodComponent FOOD_SALTED_POPCORN= new FoodComponent.Builder().hunger(10).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 1), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 1), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 5), 1.0F).build();
public static FoodComponent FOOD_BUTTERED_AND_SALTED_POPCORN= new FoodComponent.Builder().hunger(15).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 2), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 3), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 10), 1.0F)
.build();
public static FoodComponent FOOD_CHERRY= new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
.build();
public static FoodComponent FOOD_CORN= new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
.build();
public static FoodComponent FOOD_POPCORN_BAG= new FoodComponent.Builder().hunger(15).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 4000, 2), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 4000, 2), 1.0F)
.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 1200, 10), 1.0F)
.build();
public static FoodComponent FOOD_QUINOA= new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
.build();
public static FoodComponent FOOD_RADISH= new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
	.build();
public static FoodComponent FOOD_RICE= new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
			.build();
public static FoodComponent FOOD_ROCK_FISH= new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F)
				.statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 4), 1.0F).build();
public static FoodComponent FOOD_FIRE_FISH= new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 600, 4), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F)
		.build();
public static FoodComponent FOOD_SPARK_FISH= new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
		.statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 600, 4), 1.0F)
		.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F)
		.build();
public static FoodComponent FOOD_GREEN_FISH = new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F).build();
public static FoodComponent FOOD_GREY_FISH = new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F).build();
public static FoodComponent FOOD_PINK_FISH = new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F).build();
public static FoodComponent FOOD_SUN_FISH = new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
		.statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 600, 4), 1.0F)
		.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F)
		.build();
public static FoodComponent FOOD_STRAWBERRY= new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
.build();
public static FoodComponent FOOD_LETTUCE= new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 1), 1.0F)
.build();
public static FoodComponent FOOD_TOMATO= new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 1), 1.0F)
.build();
public static FoodComponent FOOD_WOOD_FISH= new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 600, 4), 1.0F)
				.statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 4), 1.0F).build();
public static FoodComponent FOOD_RAW_MOOSE_MEAT= new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 2), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 2), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 4), 1.0F).build();
public static  FoodComponent FOOD_DEAD_BUG= new FoodComponent.Builder().hunger(2).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 10), 1.0F)
					.statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 1), 1.0F)
.build();
public static  FoodComponent FOOD_MAGIC_FROG_OF_STRENGTH= new FoodComponent.Builder()
			.hunger(5)
			.saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
.build();
public static  FoodComponent FOOD_MAGIC_FROG_OF_WEAKNESS= new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 2000, 0), 1.0F)
	.build();
public static  FoodComponent FOOD_MAGIC_FROG_OF_SPEED= new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
			.build();
public static FoodComponent FOOD_MAGIC_FROG_OF_SLOWNESS= new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 2000, 0), 1.0F)
		.build();
public static  FoodComponent FOOD_MAGIC_FROG_OF_REGENERATION= new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
		.build();
public static  FoodComponent FOOD_MAGIC_FROG_OF_POISON= new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
		.statusEffect(new StatusEffectInstance(StatusEffects.POISON, 2000, 0), 1.0F)
			.build();
public static  FoodComponent FOOD_MAGIC_FROG_OF_MORPH= new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
//Plans for later but for now just Strength		
.build();
public static  FoodComponent FOOD_MAGIC_FROG_OF_CONFUSION= new FoodComponent.Builder().hunger(5).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 2000, 0), 1.0F)
.build();
public static FoodComponent FOOD_COOKED_MOOSE_MEAT= new FoodComponent.Builder().hunger(10).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 3000, 3), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 4000, 3), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 2000, 4), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1000, 4), 1.0F).build();
public static  FoodComponent FOOD_CANDY_CANE= new FoodComponent.Builder().hunger(10).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
		.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0), 1.0F)
		.build();
public static  FoodComponent FOOD_GOLDEN_BREAD= new FoodComponent.Builder().hunger(10).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0), 1.0F)
		.build();
public static FoodComponent FOOD_GOLDEN_CHICKEN= new FoodComponent.Builder().hunger(10).saturationModifier(0.85F)
	.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
	.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0), 1.0F)
		.build();
public static  FoodComponent FOOD_GOLDEN_TROPICAL_FISH= new FoodComponent.Builder().hunger(10).saturationModifier(0.85F)
		.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
		.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0), 1.0F)
		.build();
public static FoodComponent FOOD_GOLDEN_COD= new FoodComponent.Builder().hunger(10).saturationModifier(0.85F)
		.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
		.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0), 1.0F)
		.build();
public static  FoodComponent FOOD_GOLDEN_PORKCHOP= new FoodComponent.Builder().hunger(10).saturationModifier(0.85F)
	.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
	.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0), 1.0F)
		.build();
public static  FoodComponent FOOD_WATERMELON_SLICE= new FoodComponent.Builder().hunger(10).saturationModifier(0.85F)
		.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
		.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0), 1.0F)
		.build();
public static  FoodComponent FOOD_GOLDEN_MUSHROOM_STEW = new FoodComponent.Builder().hunger(15).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F)
	.statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 4), 1.0F)
			.build();
public static  FoodComponent FOOD_GOLDEN_STEAK= new FoodComponent.Builder().hunger(10).saturationModifier(0.85F)
		.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
		.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F)
		.build();
public static  FoodComponent FOOD_GOLDEN_COOKIE= new FoodComponent.Builder().hunger(10).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0), 1.0F)
		.build();
public static  FoodComponent FOOD_GOLDEN_POTATO= new FoodComponent.Builder().hunger(10).saturationModifier(0.85F)
	.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
	.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0), 1.0F)
		.build();
public static  FoodComponent FOOD_GOLDEN_PUMPKIN_PIE= new FoodComponent.Builder().hunger(15).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000,0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0), 1.0F)
.build();
public static  FoodComponent FOOD_GOLDEN_ROTTON_FLESH= new FoodComponent.Builder().hunger(10).saturationModifier(0.85F)
	.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
	.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000,0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 600, 0), 1.0F)
.build();
public static  FoodComponent FOOD_GOLDEN_CARROT = new FoodComponent.Builder().hunger(15).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000,0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F).build();
public static  FoodComponent FOOD_GOLDEN_PUFFERFISH= new FoodComponent.Builder().hunger(15).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 600, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.POISON, 600, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 600, 0), 1.0F).build();
public static  FoodComponent FOOD_GOLDEN_SALMON = new FoodComponent.Builder().hunger(15).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 600, 0), 1.0F)
		.build();
public static  FoodComponent FOOD_GOLDEN_CANDYCANE = new FoodComponent.Builder().hunger(15).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 600, 0), 1.0F)
		.build();;
public static  FoodComponent FOOD_ULTIMATE_APPLE = new FoodComponent.Builder().hunger(15).saturationModifier(0.85F)
				.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
				.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 2), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 600, 0), 1.0F)
		.build();
public static  FoodComponent FOOD_ENCHANTED_GOLDEN_CARROT = new FoodComponent.Builder().hunger(15).saturationModifier(0.85F)
	.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 2), 1.0F)
	.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 600, 0), 1.0F)
		.build();
public static FoodComponent FOOD_ENCHANTED_GOLDEN_STEAK = new FoodComponent.Builder().hunger(15).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 600, 0), 1.0F)
		.build();
public static  FoodComponent FOOD_ENCHANTED_GOLDEN_COD = new FoodComponent.Builder().hunger(15).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 600, 0), 1.0F)
		.build();
public static  FoodComponent FOOD_ENCHANTED_GOLDEN_COOKIE = new FoodComponent.Builder().hunger(15).saturationModifier(0.85F)
	.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
	.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 600, 0), 1.0F)
		.build();
public static  FoodComponent FOOD_ENCHANTED_GOLDEN_CANDYCANE = new FoodComponent.Builder().hunger(15).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 600, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 0), 1.0F).build();
public static  FoodComponent FOOD_RADDISH_STEW = new FoodComponent.Builder().hunger(15).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 4), 1.0F)
		.build();
public static final FoodComponent FOOD_DRINKABLE_GASOLINE_PETROL = new FoodComponent.Builder().hunger(15).saturationModifier(0.85F)
			.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 2), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 5), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 2), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 600, 2), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 2), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2000, 10), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 2000, 2), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 2000, 2), 1.0F)
.build();

public static final Item RAW_BACON = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_RAW_BACON));
public static final Item COOKED_BACON = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_COOKED_BACON));
public static final Item BUTTER_CANDY = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_BUTTER_CANDY));
public static final Item CRYSTAL_APPLE = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_CRYSTAL_APPLE));
public static final Item EQUESTRIA_APPLE = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_EQUESTRIA_APPLE));
public static final Item LOVE_FOOD = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_LOVE_FOOD));
public static final Item POPCORN = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_POPCORN));
public static final Item BUTTER_FOOD = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_BUTTER_FOOD));
public static final Item CORN_DOG = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_CORN_DOG));
public static final Item COOKED_CORN_DOG = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_COOKED_CORN_DOG));
public static final Item RAW_CRAB_MEAT = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_RAW_CRAB_MEAT));
public static final Item COOKED_CRAB_MEAT = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_COOKED_CRAB_MEAT));
public static final Item CHEESE = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_CHEESE));
public static final Item SALAD = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_SALAD));
public static final Item BLT = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_BLT));
public static final Item CRAB_PATTY = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_CRAB_PATTY));
public static final Item MAGIC_APPLE = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_MAGIC_APPLE));
public static final Item PEACH = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_PEACH));
public static final Item RAW_PEACOCK = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_RAW_PEACOCK));
public static final Item COOCKED_PEACOCK = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_COOCKED_PEACOCK));
public static final Item BLUE_FISH = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_BLUE_FISH));
public static final Item BUTTERED_POPCORN = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_BUTTERED_POPCORN));
public static final Item SALTED_POPCORN = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_SALTED_POPCORN));
public static final Item BUTTERED_AND_SALTED_POPCORN = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_BUTTERED_AND_SALTED_POPCORN));
public static final Item CHERRY = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_CHERRY));
public static final Item CORN = new FCFruit(CORN_PLANT, new Item.Settings().group(ItemGroup.FOOD).food(FOOD_CORN));
public static final Item POPCORN_BAG = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_POPCORN_BAG));
public static final Item QUINOA = new FCFruit(QUINOA_PLANT, new Item.Settings().group(ItemGroup.FOOD).food(FOOD_QUINOA));
public static final Item RADISH = new FCFruit(RADDISH_PLANT, new Item.Settings().group(ItemGroup.FOOD).food(FOOD_RADISH));
public static final Item RICE = new FCFruit(RICE_PLANT, new Item.Settings().group(ItemGroup.FOOD).food(FOOD_RICE));
public static final Item ROCK_FISH = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_ROCK_FISH));
public static final Item FIRE_FISH = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_FIRE_FISH));
public static final Item SPARK_FISH = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_SPARK_FISH));
public static final Item GREEN_FISH = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GREEN_FISH));
public static final Item GREY_FISH = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GREY_FISH));
public static final Item PINK_FISH = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_PINK_FISH));
public static final Item SUN_FISH = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_SUN_FISH));
public static final Item STRAWBERRY = new FCFruit(STRAWBERRY_PLANT, new Item.Settings().group(ItemGroup.FOOD).food(FOOD_STRAWBERRY));
public static final Item LETTUCE = new FCFruit(LETTUCE_PLANT, new Item.Settings().group(ItemGroup.FOOD).food(FOOD_LETTUCE));
public static final Item TOMATO = new FCFruit(TOMATO_PLANT, new Item.Settings().group(ItemGroup.FOOD).food(FOOD_TOMATO));
public static final Item WOOD_FISH = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_WOOD_FISH));
public static final Item RAW_MOOSE_MEAT = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_RAW_MOOSE_MEAT));
public static final Item DEAD_BUG = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_DEAD_BUG));
public static final Item MAGIC_FROG_OF_STRENGTH = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_MAGIC_FROG_OF_STRENGTH));
public static final Item MAGIC_FROG_OF_WEAKNESS = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_MAGIC_FROG_OF_WEAKNESS));
public static final Item MAGIC_FROG_OF_SPEED = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_MAGIC_FROG_OF_SPEED));
public static final Item MAGIC_FROG_OF_SLOWNESS = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_MAGIC_FROG_OF_SLOWNESS));
public static final Item MAGIC_FROG_OF_REGENERATION = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_MAGIC_FROG_OF_REGENERATION));
public static final Item MAGIC_FROG_OF_POISON = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_MAGIC_FROG_OF_POISON));
public static final Item MAGIC_FROG_OF_MORPH = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_MAGIC_FROG_OF_MORPH));
public static final Item MAGIC_FROG_OF_CONFUSION = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_MAGIC_FROG_OF_CONFUSION));
public static final Item COOKED_MOOSE_MEAT = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_COOKED_MOOSE_MEAT));
public static final Item CANDY_CANE = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_CANDY_CANE));
public static final Item GOLDEN_BREAD = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_BREAD));
public static final Item GOLDEN_CHICKEN = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_CHICKEN));
public static final Item GOLDEN_TROPICAL_FISH = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_TROPICAL_FISH));
public static final Item GOLDEN_COD = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_COD));
public static final Item GOLDEN_PORKCHOP = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_PORKCHOP));
public static final Item WATERMELON_SLICE = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_WATERMELON_SLICE));
public static final Item GOLDEN_MUSHROOM_STEW = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_MUSHROOM_STEW));
public static final Item GOLDEN_STEAK = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_STEAK));
public static final Item GOLDEN_COOKIE = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_COOKIE));
public static final Item GOLDEN_POTATO = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_POTATO));
public static final Item GOLDEN_PUMPKIN_PIE = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_PUMPKIN_PIE));
public static final Item GOLDEN_ROTTON_FLESH = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_ROTTON_FLESH));
public static final Item GOLDEN_CARROT = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_CARROT));
public static final Item GOLDEN_PUFFERFISH = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_PUFFERFISH));
public static final Item GOLDEN_SALMON = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_SALMON));
public static final Item GOLDEN_CANDYCANE = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_CANDYCANE));
public static final Item ULTIMATE_APPLE = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_ULTIMATE_APPLE));
public static final Item ENCHANTED_GOLDEN_CARROT = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_ENCHANTED_GOLDEN_CARROT));
public static final Item ENCHANTED_GOLDEN_STEAK = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_ENCHANTED_GOLDEN_STEAK));
public static final Item ENCHANTED_GOLDEN_COD = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_ENCHANTED_GOLDEN_COD));
public static final Item ENCHANTED_GOLDEN_COOKIE = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_ENCHANTED_GOLDEN_COOKIE));
public static final Item ENCHANTED_GOLDEN_CANDYCANE = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_ENCHANTED_GOLDEN_CANDYCANE));
public static final Item RADDISH_STEW = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_RADDISH_STEW));
public static final Item DRINKABLE_GASOLINE_PETROL = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_DRINKABLE_GASOLINE_PETROL));

    
public static final Item EMERALD_HELMET = new ArmorItem(ArmourMaterials.EMERALD, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EMERALD_CHESTPLATE = new ArmorItem(ArmourMaterials.EMERALD, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EMERALD_LEGGINGS = new ArmorItem(ArmourMaterials.EMERALD, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EMERALD_BOOTS = new ArmorItem(ArmourMaterials.EMERALD, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EMERALD_SWORD = new SwordItem(ToolMaterials.TOOL_EMERALD, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EMERALD_PICKAXE = new GenericPickaxe(ToolMaterials.TOOL_EMERALD, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EMERALD_SHOVEL = new ShovelItem(ToolMaterials.TOOL_EMERALD, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EMERALD_HOE = new GenericHoe(ToolMaterials.TOOL_EMERALD, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EMERALD_AXE = new GenericAxe(ToolMaterials.TOOL_EMERALD, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));

 
public static final Item RUBY_HELMET = new ArmorItem(ArmourMaterials.RUBY, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RUBY_CHESTPLATE = new ArmorItem(ArmourMaterials.RUBY, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RUBY_LEGGINGS = new ArmorItem(ArmourMaterials.RUBY, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RUBY_BOOTS = new ArmorItem(ArmourMaterials.RUBY, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RUBY_SWORD = new SwordItem(ToolMaterials.TOOL_RUBY, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RUBY_PICKAXE = new GenericPickaxe(ToolMaterials.TOOL_RUBY, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RUBY_SHOVEL = new ShovelItem(ToolMaterials.TOOL_RUBY, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RUBY_HOE = new GenericHoe(ToolMaterials.TOOL_RUBY, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RUBY_AXE = new GenericAxe(ToolMaterials.TOOL_RUBY, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Item EXPERIENCE_HELMET = new EnchantedArmour(ArmourMaterials.EXPERIENCE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EXPERIENCE_CHESTPLATE = new EnchantedArmour(ArmourMaterials.EXPERIENCE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EXPERIENCE_LEGGINGS = new EnchantedArmour(ArmourMaterials.EXPERIENCE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EXPERIENCE_BOOTS = new EnchantedArmour(ArmourMaterials.EXPERIENCE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EXPERIENCE_SWORD = new EnchantedSword(ToolMaterials.EXPERIENCE, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EXPERIENCE_PICKAXE = new EnchantedPickaxe(ToolMaterials.EXPERIENCE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EXPERIENCE_SHOVEL = new EnchantedShovel(ToolMaterials.EXPERIENCE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EXPERIENCE_HOE = new EnchantedHoe(ToolMaterials.EXPERIENCE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EXPERIENCE_AXE = new EnchantedAxe(ToolMaterials.EXPERIENCE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));

 
//public static final Item AMETHYST_HELMET = new ArmorItem(ArmourMaterials.AMETHYST, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
//public static final Item AMETHYST_CHESTPLATE = new ArmorItem(ArmourMaterials.AMETHYST, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
//public static final Item AMETHYST_LEGGINGS = new ArmorItem(ArmourMaterials.AMETHYST, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
//public static final Item AMETHYST_BOOTS = new ArmorItem(ArmourMaterials.AMETHYST, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
//public static final Item AMETHYST_SWORD = new SwordItem(ToolMaterials.TOOL_AMETHYST, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
//public static final Item AMETHYST_PICKAXE = new GenericPickaxe(ToolMaterials.TOOL_AMETHYST, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
//public static final Item AMETHYST_SHOVEL = new ShovelItem(ToolMaterials.TOOL_AMETHYST, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
//public static final Item AMETHYST_HOE = new GenericHoe(ToolMaterials.TOOL_AMETHYST, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
//public static final Item AMETHYST_AXE = new GenericAxe(ToolMaterials.TOOL_AMETHYST, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));





public static final Item SAPPHIRE_HELMET = new ArmorItem(ArmourMaterials.SAPPHIRE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SAPPHIRE_CHESTPLATE = new ArmorItem(ArmourMaterials.SAPPHIRE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SAPPHIRE_LEGGINGS = new ArmorItem(ArmourMaterials.SAPPHIRE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SAPPHIRE_BOOTS = new ArmorItem(ArmourMaterials.SAPPHIRE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SAPPHIRE_SWORD = new SwordItem(ToolMaterials.TOOL_SAPPHIRE, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SAPPHIRE_PICKAXE = new GenericPickaxe(ToolMaterials.TOOL_SAPPHIRE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SAPPHIRE_SHOVEL = new ShovelItem(ToolMaterials.TOOL_SAPPHIRE, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SAPPHIRE_HOE = new GenericHoe(ToolMaterials.TOOL_SAPPHIRE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SAPPHIRE_AXE = new GenericAxe(ToolMaterials.TOOL_SAPPHIRE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Item TIGERS_EYE_HELMET = new ArmorItem(ArmourMaterials.TIGERS_EYE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TIGERS_EYE_CHESTPLATE = new ArmorItem(ArmourMaterials.TIGERS_EYE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TIGERS_EYE_LEGGINGS = new ArmorItem(ArmourMaterials.TIGERS_EYE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TIGERS_EYE_BOOTS = new ArmorItem(ArmourMaterials.TIGERS_EYE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TIGERS_EYE_SWORD = new SwordItem(ToolMaterials.TOOL_TIGERS_EYE, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TIGERS_EYE_PICKAXE = new GenericPickaxe(ToolMaterials.TOOL_TIGERS_EYE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TIGERS_EYE_SHOVEL = new ShovelItem(ToolMaterials.TOOL_TIGERS_EYE, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TIGERS_EYE_HOE = new GenericHoe(ToolMaterials.TOOL_TIGERS_EYE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TIGERS_EYE_AXE = new GenericAxe(ToolMaterials.TOOL_TIGERS_EYE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
 


public static final Item CATS_EYE_HELMET = new ArmorItem(ArmourMaterials.TIGERS_EYE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CATS_EYE_CHESTPLATE = new ArmorItem(ArmourMaterials.TIGERS_EYE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CATS_EYE_LEGGINGS = new ArmorItem(ArmourMaterials.TIGERS_EYE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CATS_EYE_BOOTS = new ArmorItem(ArmourMaterials.TIGERS_EYE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CATS_EYE_SWORD = new SwordItem(ToolMaterials.TOOL_TIGERS_EYE, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CATS_EYE_PICKAXE = new GenericPickaxe(ToolMaterials.TOOL_TIGERS_EYE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CATS_EYE_SHOVEL = new ShovelItem(ToolMaterials.TOOL_TIGERS_EYE, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CATS_EYE_HOE = new GenericHoe(ToolMaterials.TOOL_TIGERS_EYE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CATS_EYE_AXE = new GenericAxe(ToolMaterials.TOOL_TIGERS_EYE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));






    
public static final Item COPPER_HELMET = new ArmorItem(ArmourMaterials.COPPER, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COPPER_CHESTPLATE = new ArmorItem(ArmourMaterials.COPPER, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COPPER_LEGGINGS = new ArmorItem(ArmourMaterials.COPPER, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COPPER_BOOTS = new ArmorItem(ArmourMaterials.COPPER, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COPPER_SWORD = new SwordItem(ToolMaterials.COPPER, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COPPER_PICKAXE = new GenericPickaxe(ToolMaterials.COPPER, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COPPER_SHOVEL = new ShovelItem(ToolMaterials.COPPER, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COPPER_HOE = new GenericHoe(ToolMaterials.COPPER, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COPPER_AXE = new GenericAxe(ToolMaterials.COPPER, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));




public static final Item PLATINUM_HELMET = new ArmorItem(ArmourMaterials.PLATINUM, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PLATINUM_CHESTPLATE = new ArmorItem(ArmourMaterials.PLATINUM, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PLATINUM_LEGGINGS = new ArmorItem(ArmourMaterials.PLATINUM, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PLATINUM_BOOTS = new ArmorItem(ArmourMaterials.PLATINUM, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PLATINUM_SWORD = new SwordItem(ToolMaterials.PLATINUM, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PLATINUM_PICKAXE = new GenericPickaxe(ToolMaterials.PLATINUM, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PLATINUM_SHOVEL = new ShovelItem(ToolMaterials.PLATINUM, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PLATINUM_HOE = new GenericHoe(ToolMaterials.PLATINUM, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PLATINUM_AXE = new GenericAxe(ToolMaterials.PLATINUM, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));




public static final Item SILVER_HELMET = new ArmorItem(ArmourMaterials.SILVER, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SILVER_CHESTPLATE = new ArmorItem(ArmourMaterials.SILVER, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SILVER_LEGGINGS = new ArmorItem(ArmourMaterials.SILVER, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SILVER_BOOTS = new ArmorItem(ArmourMaterials.SILVER, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SILVER_SWORD = new SwordItem(ToolMaterials.SILVER, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SILVER_PICKAXE = new GenericPickaxe(ToolMaterials.SILVER, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SILVER_SHOVEL = new ShovelItem(ToolMaterials.SILVER, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SILVER_HOE = new GenericHoe(ToolMaterials.SILVER, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SILVER_AXE = new GenericAxe(ToolMaterials.SILVER, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item ALUMINIUM_HELMET = new ArmorItem(ArmourMaterials.ALUMINIUM, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ALUMINIUM_CHESTPLATE = new ArmorItem(ArmourMaterials.ALUMINIUM, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ALUMINIUM_LEGGINGS = new ArmorItem(ArmourMaterials.ALUMINIUM, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ALUMINIUM_BOOTS = new ArmorItem(ArmourMaterials.ALUMINIUM, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ALUMINIUM_SWORD = new SwordItem(ToolMaterials.ALUMINIUM, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ALUMINIUM_PICKAXE = new GenericPickaxe(ToolMaterials.ALUMINIUM, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ALUMINIUM_SHOVEL = new ShovelItem(ToolMaterials.ALUMINIUM, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ALUMINIUM_HOE = new GenericHoe(ToolMaterials.ALUMINIUM, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ALUMINIUM_AXE = new GenericAxe(ToolMaterials.ALUMINIUM, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item TIN_HELMET = new ArmorItem(ArmourMaterials.TIN, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TIN_CHESTPLATE = new ArmorItem(ArmourMaterials.TIN, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TIN_LEGGINGS = new ArmorItem(ArmourMaterials.TIN, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TIN_BOOTS = new ArmorItem(ArmourMaterials.TIN, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TIN_SWORD = new SwordItem(ToolMaterials.TIN, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TIN_PICKAXE = new GenericPickaxe(ToolMaterials.TIN, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TIN_SHOVEL = new ShovelItem(ToolMaterials.TIN, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TIN_HOE = new GenericHoe(ToolMaterials.TIN, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TIN_AXE = new GenericAxe(ToolMaterials.TIN, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item PINK_TOURMALINE_HELMET = new ArmorItem(ArmourMaterials.PINK_TOURMALINE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PINK_TOURMALINE_CHESTPLATE = new ArmorItem(ArmourMaterials.PINK_TOURMALINE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PINK_TOURMALINE_LEGGINGS = new ArmorItem(ArmourMaterials.PINK_TOURMALINE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PINK_TOURMALINE_BOOTS = new ArmorItem(ArmourMaterials.PINK_TOURMALINE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PINK_TOURMALINE_SWORD = new SwordItem(ToolMaterials.PINK_TOURMALINE, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PINK_TOURMALINE_PICKAXE = new GenericPickaxe(ToolMaterials.PINK_TOURMALINE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PINK_TOURMALINE_SHOVEL = new ShovelItem(ToolMaterials.PINK_TOURMALINE, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PINK_TOURMALINE_HOE = new GenericHoe(ToolMaterials.PINK_TOURMALINE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PINK_TOURMALINE_AXE = new GenericAxe(ToolMaterials.PINK_TOURMALINE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));




public static final Item OPTIMISED_PICKAXE = new EnchantedPickaxe(ToolMaterials.TOOL_OPTIMISED, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item OPTIMISED_SHOVEL = new EnchantedShovel(ToolMaterials.TOOL_OPTIMISED, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item ULTIMATE_HELMET = new EnchantedArmour(ArmourMaterials.ULTIMATE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ULTIMATE_CHESTPLATE = new EnchantedArmour(ArmourMaterials.ULTIMATE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ULTIMATE_LEGGINGS = new EnchantedArmour(ArmourMaterials.ULTIMATE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ULTIMATE_BOOTS = new EnchantedArmour(ArmourMaterials.ULTIMATE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ULTIMATE_SWORD = new EnchantedSword(ToolMaterials.TOOL_ULTIMATE, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ULTIMATE_PICKAXE = new EnchantedPickaxe(ToolMaterials.TOOL_ULTIMATE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ULTIMATE_SHOVEL = new EnchantedShovel(ToolMaterials.TOOL_ULTIMATE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ULTIMATE_HOE = new UltimateHoe(ToolMaterials.TOOL_ULTIMATE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ULTIMATE_AXE = new EnchantedAxe(ToolMaterials.TOOL_ULTIMATE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Item ROYAL_GUARDIAN_HELMET = new EnchantedArmour(ArmourMaterials.ROYAL_GUARDIAN, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ROYAL_GUARDIAN_CHESTPLATE = new EnchantedArmour(ArmourMaterials.ROYAL_GUARDIAN, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ROYAL_GUARDIAN_LEGGINGS = new EnchantedArmour(ArmourMaterials.ROYAL_GUARDIAN, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ROYAL_GUARDIAN_BOOTS = new EnchantedArmour(ArmourMaterials.ROYAL_GUARDIAN, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ROYAL_GUARDIAN_SWORD = new EnchantedSword(ToolMaterials.ROYAL_GUARDIAN, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ROYAL_GUARDIAN_MEGA_SWORD = new EnchantedSword(ToolMaterials.ROYAL_GUARDIAN_MEGA, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ROYAL_GUARDIAN_PICKAXE = new EnchantedPickaxe(ToolMaterials.ROYAL_GUARDIAN, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ROYAL_GUARDIAN_SHOVEL = new EnchantedShovel(ToolMaterials.ROYAL_GUARDIAN, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ROYAL_GUARDIAN_HOE = new EnchantedHoe(ToolMaterials.ROYAL_GUARDIAN, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ROYAL_GUARDIAN_AXE = new EnchantedAxe(ToolMaterials.ROYAL_GUARDIAN, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));



public static final Item QUEEN_SCALE_HELMET = new EnchantedArmour(ArmourMaterials.QUEEN_SCALE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUEEN_SCALE_CHESTPLATE = new EnchantedArmour(ArmourMaterials.QUEEN_SCALE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUEEN_SCALE_LEGGINGS = new EnchantedArmour(ArmourMaterials.QUEEN_SCALE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUEEN_SCALE_BOOTS = new EnchantedArmour(ArmourMaterials.QUEEN_SCALE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUEEN_SCALE_SWORD = new EnchantedSword(ToolMaterials.QUEEN_SCALE, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUEEN_SCALE_PICKAXE = new EnchantedPickaxe(ToolMaterials.QUEEN_SCALE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUEEN_SCALE_SHOVEL = new EnchantedShovel(ToolMaterials.QUEEN_SCALE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUEEN_SCALE_HOE = new EnchantedHoe(ToolMaterials.QUEEN_SCALE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUEEN_SCALE_AXE = new EnchantedAxe(ToolMaterials.QUEEN_SCALE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item QUEEN_SCALE_MEGA_BATTLE_AXE = new EnchantedAxe(ToolMaterials.QUEEN_SCALE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item MEGA_BATTLE_AXE = new EnchantedAxe(ToolMaterials.STEEL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));



public static final Item MOBZILLA_SCALE_HELMET = new EnchantedArmour(ArmourMaterials.MOBZILLA_SCALE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item MOBZILLA_SCALE_CHESTPLATE = new EnchantedArmour(ArmourMaterials.MOBZILLA_SCALE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item MOBZILLA_SCALE_LEGGINGS = new EnchantedArmour(ArmourMaterials.MOBZILLA_SCALE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item MOBZILLA_SCALE_BOOTS = new EnchantedArmour(ArmourMaterials.MOBZILLA_SCALE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item MOBZILLA_SCALE_SWORD = new EnchantedSword(ToolMaterials.MOBZILLA_SCALE, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item MOBZILLA_SCALE_PICKAXE = new EnchantedPickaxe(ToolMaterials.MOBZILLA_SCALE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item MOBZILLA_SCALE_SHOVEL = new EnchantedShovel(ToolMaterials.MOBZILLA_SCALE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item MOBZILLA_SCALE_HOE = new EnchantedHoe(ToolMaterials.MOBZILLA_SCALE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item MOBZILLA_SCALE_AXE = new EnchantedAxe(ToolMaterials.MOBZILLA_SCALE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item MOTH_SCALE_HELMET = new EnchantedArmour(ArmourMaterials.MOTH_SCALE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item MOTH_SCALE_CHESTPLATE = new EnchantedArmour(ArmourMaterials.MOTH_SCALE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item MOTH_SCALE_LEGGINGS = new EnchantedArmour(ArmourMaterials.MOTH_SCALE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item MOTH_SCALE_BOOTS = new EnchantedArmour(ArmourMaterials.MOTH_SCALE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item MOTH_SCALE_SWORD = new EnchantedSword(ToolMaterials.MOTH_SCALE, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item MOTH_SCALE_PICKAXE = new EnchantedPickaxe(ToolMaterials.MOTH_SCALE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item MOTH_SCALE_SHOVEL = new EnchantedShovel(ToolMaterials.MOTH_SCALE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item MOTH_SCALE_HOE = new EnchantedHoe(ToolMaterials.MOTH_SCALE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item MOTH_SCALE_AXE = new EnchantedAxe(ToolMaterials.MOTH_SCALE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));



public static final Item LAPIS_BLOCK_HELMET = new EnchantedArmour(ArmourMaterials.LAPIS_BLOCK, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAPIS_BLOCK_CHESTPLATE = new EnchantedArmour(ArmourMaterials.LAPIS_BLOCK, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAPIS_BLOCK_LEGGINGS = new EnchantedArmour(ArmourMaterials.LAPIS_BLOCK, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAPIS_BLOCK_BOOTS = new EnchantedArmour(ArmourMaterials.LAPIS_BLOCK, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAPIS_BLOCK_SWORD = new EnchantedSword(ToolMaterials.LAPIS_BLOCK, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAPIS_BLOCK_PICKAXE = new EnchantedPickaxe(ToolMaterials.LAPIS_BLOCK, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAPIS_BLOCK_SHOVEL = new EnchantedShovel(ToolMaterials.LAPIS_BLOCK, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAPIS_BLOCK_HOE = new EnchantedHoe(ToolMaterials.LAPIS_BLOCK, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAPIS_BLOCK_AXE = new EnchantedAxe(ToolMaterials.LAPIS_BLOCK, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));



public static final Item LAVA_EEL_HELMET = new EnchantedArmour(ArmourMaterials.LAVA_EEL, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAVA_EEL_CHESTPLATE = new EnchantedArmour(ArmourMaterials.LAVA_EEL, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAVA_EEL_LEGGINGS = new EnchantedArmour(ArmourMaterials.LAVA_EEL, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAVA_EEL_BOOTS = new EnchantedArmour(ArmourMaterials.LAVA_EEL, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAVA_EEL_SWORD = new EnchantedSword(ToolMaterials.LAVA_EEL, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAVA_EEL_PICKAXE = new EnchantedPickaxe(ToolMaterials.LAVA_EEL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAVA_EEL_SHOVEL = new EnchantedShovel(ToolMaterials.LAVA_EEL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAVA_EEL_HOE = new EnchantedHoe(ToolMaterials.LAVA_EEL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAVA_EEL_AXE = new EnchantedAxe(ToolMaterials.LAVA_EEL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item PEACOCK_FEATHER_HELMET = new EnchantedArmour(ArmourMaterials.PEACOCK_FEATHER, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PEACOCK_FEATHER_CHESTPLATE = new EnchantedArmour(ArmourMaterials.PEACOCK_FEATHER, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PEACOCK_FEATHER_LEGGINGS = new EnchantedArmour(ArmourMaterials.PEACOCK_FEATHER, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PEACOCK_FEATHER_BOOTS = new EnchantedArmour(ArmourMaterials.PEACOCK_FEATHER, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PEACOCK_FEATHER_SWORD = new EnchantedSword(ToolMaterials.PEACOCK_FEATHER, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PEACOCK_FEATHER_PICKAXE = new EnchantedPickaxe(ToolMaterials.PEACOCK_FEATHER, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PEACOCK_FEATHER_SHOVEL = new EnchantedShovel(ToolMaterials.PEACOCK_FEATHER, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PEACOCK_FEATHER_HOE = new EnchantedHoe(ToolMaterials.PEACOCK_FEATHER, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PEACOCK_FEATHER_AXE = new EnchantedAxe(ToolMaterials.PEACOCK_FEATHER, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item CZ_SLOW_BOOTS = new CZSlowArmour(ArmourMaterials.CZ_SLOW, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item RUBY_ON_RAILS_HELMET = new EnchantedArmour(ArmourMaterials.RUBY_ON_RAILS, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RUBY_ON_RAILS_CHESTPLATE = new EnchantedArmour(ArmourMaterials.RUBY_ON_RAILS, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RUBY_ON_RAILS_LEGGINGS = new EnchantedArmour(ArmourMaterials.RUBY_ON_RAILS, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RUBY_ON_RAILS_BOOTS = new EnchantedArmour(ArmourMaterials.RUBY_ON_RAILS, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RUBY_ON_RAILS_SWORD = new EnchantedSword(ToolMaterials.RUBY_ON_RAILS, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RUBY_ON_RAILS_PICKAXE = new EnchantedPickaxe(ToolMaterials.RUBY_ON_RAILS, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RUBY_ON_RAILS_SHOVEL = new EnchantedShovel(ToolMaterials.RUBY_ON_RAILS, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RUBY_ON_RAILS_HOE = new EnchantedHoe(ToolMaterials.RUBY_ON_RAILS, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RUBY_ON_RAILS_AXE = new EnchantedAxe(ToolMaterials.RUBY_ON_RAILS, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item WHITE_TOURMALINE_HELMET = new ArmorItem(ArmourMaterials.WHITE_TOURMALINE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WHITE_TOURMALINE_CHESTPLATE = new ArmorItem(ArmourMaterials.WHITE_TOURMALINE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WHITE_TOURMALINE_LEGGINGS = new ArmorItem(ArmourMaterials.WHITE_TOURMALINE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WHITE_TOURMALINE_BOOTS = new ArmorItem(ArmourMaterials.WHITE_TOURMALINE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WHITE_TOURMALINE_SWORD = new SwordItem(ToolMaterials.WHITE_TOURMALINE, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WHITE_TOURMALINE_PICKAXE = new GenericPickaxe(ToolMaterials.WHITE_TOURMALINE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WHITE_TOURMALINE_SHOVEL = new ShovelItem(ToolMaterials.WHITE_TOURMALINE, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WHITE_TOURMALINE_HOE = new GenericHoe(ToolMaterials.WHITE_TOURMALINE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WHITE_TOURMALINE_AXE = new GenericAxe(ToolMaterials.WHITE_TOURMALINE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item TOURMALINE_HELMET = new ArmorItem(ArmourMaterials.TOURMALINE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TOURMALINE_CHESTPLATE = new ArmorItem(ArmourMaterials.TOURMALINE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TOURMALINE_LEGGINGS = new ArmorItem(ArmourMaterials.TOURMALINE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TOURMALINE_BOOTS = new ArmorItem(ArmourMaterials.TOURMALINE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TOURMALINE_SWORD = new SwordItem(ToolMaterials.TOURMALINE, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TOURMALINE_PICKAXE = new GenericPickaxe(ToolMaterials.TOURMALINE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TOURMALINE_SHOVEL = new ShovelItem(ToolMaterials.TOURMALINE, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TOURMALINE_HOE = new GenericHoe(ToolMaterials.TOURMALINE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TOURMALINE_AXE = new GenericAxe(ToolMaterials.TOURMALINE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));



public static final Item DIRT_HELMET = new ArmorItem(ArmourMaterials.DIRT, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item DIRT_CHESTPLATE = new ArmorItem(ArmourMaterials.DIRT, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item DIRT_LEGGINGS = new ArmorItem(ArmourMaterials.DIRT, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item DIRT_BOOTS = new ArmorItem(ArmourMaterials.DIRT, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item DIRT_SWORD = new SwordItem(ToolMaterials.DIRT, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item DIRT_PICKAXE = new GenericPickaxe(ToolMaterials.DIRT, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item DIRT_SHOVEL = new ShovelItem(ToolMaterials.DIRT, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item DIRT_HOE = new GenericHoe(ToolMaterials.DIRT, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item DIRT_AXE = new GenericAxe(ToolMaterials.DIRT, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Item STONE_HELMET = new ArmorItem(ArmourMaterials.STONE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item STONE_CHESTPLATE = new ArmorItem(ArmourMaterials.STONE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item STONE_LEGGINGS = new ArmorItem(ArmourMaterials.STONE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item STONE_BOOTS = new ArmorItem(ArmourMaterials.STONE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item STONE_SWORD = new SwordItem(ToolMaterials.STONE, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item STONE_PICKAXE = new GenericPickaxe(ToolMaterials.STONE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item STONE_SHOVEL = new ShovelItem(ToolMaterials.STONE, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item STONE_HOE = new GenericHoe(ToolMaterials.STONE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item STONE_AXE = new GenericAxe(ToolMaterials.STONE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));



public static final Item COBBLE_STONE_HELMET = new ArmorItem(ArmourMaterials.COBBLE_STONE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COBBLE_STONE_CHESTPLATE = new ArmorItem(ArmourMaterials.COBBLE_STONE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COBBLE_STONE_LEGGINGS = new ArmorItem(ArmourMaterials.COBBLE_STONE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COBBLE_STONE_BOOTS = new ArmorItem(ArmourMaterials.COBBLE_STONE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Item WOOD_PLANK_HELMET = new ArmorItem(ArmourMaterials.WOOD_PLANK, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WOOD_PLANK_CHESTPLATE = new ArmorItem(ArmourMaterials.WOOD_PLANK, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WOOD_PLANK_LEGGINGS = new ArmorItem(ArmourMaterials.WOOD_PLANK, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WOOD_PLANK_BOOTS = new ArmorItem(ArmourMaterials.WOOD_PLANK, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Item TOURCH_LIGHT_WOOD_PLANK_HELMET = new NightVisionArmour(ArmourMaterials.WOOD_PLANK, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));



public static final Item WOOD_BLOCK_HELMET = new ArmorItem(ArmourMaterials.WOOD_BLOCK, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WOOD_BLOCK_CHESTPLATE = new ArmorItem(ArmourMaterials.WOOD_BLOCK, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WOOD_BLOCK_LEGGINGS = new ArmorItem(ArmourMaterials.WOOD_BLOCK, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WOOD_BLOCK_BOOTS = new ArmorItem(ArmourMaterials.WOOD_BLOCK, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WOOD_BLOCK_SWORD = new SwordItem(ToolMaterials.WOOD_BLOCK, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WOOD_BLOCK_PICKAXE = new GenericPickaxe(ToolMaterials.WOOD_BLOCK, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WOOD_BLOCK_SHOVEL = new ShovelItem(ToolMaterials.WOOD_BLOCK, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WOOD_BLOCK_HOE = new GenericHoe(ToolMaterials.WOOD_BLOCK, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WOOD_BLOCK_AXE = new GenericAxe(ToolMaterials.WOOD_BLOCK, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));





public static final Item NETHERRACK_BLOODSTONE_HELMET = new ArmorItem(ArmourMaterials.NETHERRACK_BLOODSTONE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item NETHERRACK_BLOODSTONE_CHESTPLATE = new ArmorItem(ArmourMaterials.NETHERRACK_BLOODSTONE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item NETHERRACK_BLOODSTONE_LEGGINGS = new ArmorItem(ArmourMaterials.NETHERRACK_BLOODSTONE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item NETHERRACK_BLOODSTONE_BOOTS = new ArmorItem(ArmourMaterials.NETHERRACK_BLOODSTONE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item NETHERRACK_BLOODSTONE_SWORD = new SwordItem(ToolMaterials.NETHERRACK_BLOODSTONE, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item NETHERRACK_BLOODSTONE_PICKAXE = new GenericPickaxe(ToolMaterials.NETHERRACK_BLOODSTONE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item NETHERRACK_BLOODSTONE_SHOVEL = new ShovelItem(ToolMaterials.NETHERRACK_BLOODSTONE, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item NETHERRACK_BLOODSTONE_HOE = new GenericHoe(ToolMaterials.NETHERRACK_BLOODSTONE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item NETHERRACK_BLOODSTONE_AXE = new GenericAxe(ToolMaterials.NETHERRACK_BLOODSTONE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item LIGHTNING_LAVA_SWORD = new LightningLavaSword(ToolMaterials.NETHERRACK_BLOODSTONE, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item LAPIS_HELMET = new ArmorItem(ArmourMaterials.LAPIS, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAPIS_CHESTPLATE = new ArmorItem(ArmourMaterials.LAPIS, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAPIS_LEGGINGS = new ArmorItem(ArmourMaterials.LAPIS, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAPIS_BOOTS = new ArmorItem(ArmourMaterials.LAPIS, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAPIS_SWORD = new SwordItem(ToolMaterials.LAPIS, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAPIS_PICKAXE = new GenericPickaxe(ToolMaterials.LAPIS, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAPIS_SHOVEL = new ShovelItem(ToolMaterials.LAPIS, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAPIS_HOE = new GenericHoe(ToolMaterials.LAPIS, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAPIS_AXE = new GenericAxe(ToolMaterials.LAPIS, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));



public static final Item COARSE_DIRT_HELMET = new RoughArmour(ArmourMaterials.COARSE_DIRT, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COARSE_DIRT_CHESTPLATE = new RoughArmour(ArmourMaterials.COARSE_DIRT, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COARSE_DIRT_LEGGINGS = new RoughArmour(ArmourMaterials.COARSE_DIRT, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COARSE_DIRT_BOOTS = new RoughArmour(ArmourMaterials.COARSE_DIRT, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COARSE_DIRT_SWORD = new SwordItem(ToolMaterials.COARSE_DIRT, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COARSE_DIRT_PICKAXE = new GenericPickaxe(ToolMaterials.COARSE_DIRT, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COARSE_DIRT_SHOVEL = new ShovelItem(ToolMaterials.COARSE_DIRT, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COARSE_DIRT_HOE = new GenericHoe(ToolMaterials.COARSE_DIRT, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COARSE_DIRT_AXE = new GenericAxe(ToolMaterials.COARSE_DIRT, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item CACTUS_HELMET = new RoughArmour(ArmourMaterials.CACTUS, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CACTUS_CHESTPLATE = new RoughArmour(ArmourMaterials.CACTUS, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CACTUS_LEGGINGS = new RoughArmour(ArmourMaterials.CACTUS, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CACTUS_BOOTS = new RoughArmour(ArmourMaterials.CACTUS, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CACTUS_SWORD = new SwordItem(ToolMaterials.CACTUS, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CACTUS_PICKAXE = new GenericPickaxe(ToolMaterials.CACTUS, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CACTUS_SHOVEL = new ShovelItem(ToolMaterials.CACTUS, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CACTUS_HOE = new GenericHoe(ToolMaterials.CACTUS, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CACTUS_AXE = new GenericAxe(ToolMaterials.CACTUS, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Item GRAVEL_HELMET = new RoughArmour(ArmourMaterials.GRAVEL, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item GRAVEL_CHESTPLATE = new RoughArmour(ArmourMaterials.GRAVEL, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item GRAVEL_LEGGINGS = new RoughArmour(ArmourMaterials.GRAVEL, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item GRAVEL_BOOTS = new RoughArmour(ArmourMaterials.GRAVEL, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item GRAVEL_SWORD = new SwordItem(ToolMaterials.GRAVEL, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item GRAVEL_PICKAXE = new GenericPickaxe(ToolMaterials.GRAVEL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item GRAVEL_SHOVEL = new ShovelItem(ToolMaterials.GRAVEL, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item GRAVEL_HOE = new GenericHoe(ToolMaterials.GRAVEL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item GRAVEL_AXE = new GenericAxe(ToolMaterials.GRAVEL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Item FLINT_HELMET = new ArmorItem(ArmourMaterials.FLINT, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item FLINT_CHESTPLATE = new ArmorItem(ArmourMaterials.FLINT, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item FLINT_LEGGINGS = new ArmorItem(ArmourMaterials.FLINT, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item FLINT_BOOTS = new ArmorItem(ArmourMaterials.FLINT, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item FLINT_SWORD = new SwordItem(ToolMaterials.FLINT, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item FLINT_PICKAXE = new GenericPickaxe(ToolMaterials.FLINT, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item FLINT_SHOVEL = new ShovelItem(ToolMaterials.FLINT, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item FLINT_HOE = new GenericHoe(ToolMaterials.FLINT, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item FLINT_AXE = new GenericAxe(ToolMaterials.FLINT, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item RED_STONE_HELMET = new ArmorItem(ArmourMaterials.RED_STONE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RED_STONE_CHESTPLATE = new ArmorItem(ArmourMaterials.RED_STONE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RED_STONE_LEGGINGS = new ArmorItem(ArmourMaterials.RED_STONE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RED_STONE_BOOTS = new ArmorItem(ArmourMaterials.RED_STONE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RED_STONE_SWORD = new SwordItem(ToolMaterials.RED_STONE, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RED_STONE_PICKAXE = new GenericPickaxe(ToolMaterials.RED_STONE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RED_STONE_SHOVEL = new ShovelItem(ToolMaterials.RED_STONE, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RED_STONE_HOE = new GenericHoe(ToolMaterials.RED_STONE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RED_STONE_AXE = new GenericAxe(ToolMaterials.RED_STONE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));



public static final Item RED_STONE_BLOCK_HELMET = new EnchantedArmour(ArmourMaterials.RED_STONE_BLOCK, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RED_STONE_BLOCK_CHESTPLATE = new EnchantedArmour(ArmourMaterials.RED_STONE_BLOCK, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RED_STONE_BLOCK_LEGGINGS = new EnchantedArmour(ArmourMaterials.RED_STONE_BLOCK, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RED_STONE_BLOCK_BOOTS = new EnchantedArmour(ArmourMaterials.RED_STONE_BLOCK, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RED_STONE_BLOCK_SWORD = new EnchantedSword(ToolMaterials.RED_STONE_BLOCK, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RED_STONE_BLOCK_PICKAXE = new EnchantedPickaxe(ToolMaterials.RED_STONE_BLOCK, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RED_STONE_BLOCK_SHOVEL = new EnchantedShovel(ToolMaterials.RED_STONE_BLOCK, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RED_STONE_BLOCK_HOE = new EnchantedHoe(ToolMaterials.RED_STONE_BLOCK, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RED_STONE_BLOCK_AXE = new EnchantedAxe(ToolMaterials.RED_STONE_BLOCK, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));



public static final Item QUARTZ_HELMET = new ArmorItem(ArmourMaterials.QUARTZ, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUARTZ_CHESTPLATE = new ArmorItem(ArmourMaterials.QUARTZ, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUARTZ_LEGGINGS = new ArmorItem(ArmourMaterials.QUARTZ, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUARTZ_BOOTS = new ArmorItem(ArmourMaterials.QUARTZ, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUARTZ_SWORD = new SwordItem(ToolMaterials.QUARTZ, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUARTZ_PICKAXE = new GenericPickaxe(ToolMaterials.QUARTZ, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUARTZ_SHOVEL = new ShovelItem(ToolMaterials.QUARTZ, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUARTZ_HOE = new GenericHoe(ToolMaterials.QUARTZ, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUARTZ_AXE = new GenericAxe(ToolMaterials.QUARTZ, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));



public static final Item QUARTZ_BLOCK_HELMET = new EnchantedArmour(ArmourMaterials.QUARTZ_BLOCK, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUARTZ_BLOCK_CHESTPLATE = new EnchantedArmour(ArmourMaterials.QUARTZ_BLOCK, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUARTZ_BLOCK_LEGGINGS = new EnchantedArmour(ArmourMaterials.QUARTZ_BLOCK, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUARTZ_BLOCK_BOOTS = new EnchantedArmour(ArmourMaterials.QUARTZ_BLOCK, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUARTZ_BLOCK_SWORD = new EnchantedSword(ToolMaterials.QUARTZ_BLOCK, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUARTZ_BLOCK_PICKAXE = new EnchantedPickaxe(ToolMaterials.QUARTZ_BLOCK, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUARTZ_BLOCK_SHOVEL = new EnchantedShovel(ToolMaterials.QUARTZ_BLOCK, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUARTZ_BLOCK_HOE = new EnchantedHoe(ToolMaterials.QUARTZ_BLOCK, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUARTZ_BLOCK_AXE = new EnchantedAxe(ToolMaterials.QUARTZ_BLOCK, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));





public static final Item SAND_HELMET = new ArmorItem(ArmourMaterials.SAND, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SAND_CHESTPLATE = new ArmorItem(ArmourMaterials.SAND, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SAND_LEGGINGS = new ArmorItem(ArmourMaterials.SAND, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SAND_BOOTS = new ArmorItem(ArmourMaterials.SAND, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SAND_SWORD = new SwordItem(ToolMaterials.SAND, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SAND_PICKAXE = new GenericPickaxe(ToolMaterials.SAND, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SAND_SHOVEL = new ShovelItem(ToolMaterials.SAND, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SAND_HOE = new GenericHoe(ToolMaterials.SAND, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SAND_AXE = new GenericAxe(ToolMaterials.SAND, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Item RED_SAND_HELMET = new ArmorItem(ArmourMaterials.RED_SAND, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RED_SAND_CHESTPLATE = new ArmorItem(ArmourMaterials.RED_SAND, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RED_SAND_LEGGINGS = new ArmorItem(ArmourMaterials.RED_SAND, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RED_SAND_BOOTS = new ArmorItem(ArmourMaterials.RED_SAND, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RED_SAND_SWORD = new SwordItem(ToolMaterials.RED_SAND, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RED_SAND_PICKAXE = new GenericPickaxe(ToolMaterials.RED_SAND, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RED_SAND_SHOVEL = new ShovelItem(ToolMaterials.RED_SAND, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RED_SAND_HOE = new GenericHoe(ToolMaterials.RED_SAND, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item RED_SAND_AXE = new GenericAxe(ToolMaterials.RED_SAND, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));



public static final Item COAL_HELMET = new ArmorItem(ArmourMaterials.COAL, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COAL_CHESTPLATE = new ArmorItem(ArmourMaterials.COAL, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COAL_LEGGINGS = new ArmorItem(ArmourMaterials.COAL, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COAL_BOOTS = new ArmorItem(ArmourMaterials.COAL, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COAL_SWORD = new SwordItem(ToolMaterials.COAL, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COAL_PICKAXE = new GenericPickaxe(ToolMaterials.COAL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COAL_SHOVEL = new ShovelItem(ToolMaterials.COAL, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COAL_HOE = new GenericHoe(ToolMaterials.COAL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COAL_AXE = new GenericAxe(ToolMaterials.COAL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item LEAD_HELMET = new ArmorItem(ArmourMaterials.LEAD, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LEAD_CHESTPLATE = new ArmorItem(ArmourMaterials.LEAD, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LEAD_LEGGINGS = new ArmorItem(ArmourMaterials.LEAD, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LEAD_BOOTS = new ArmorItem(ArmourMaterials.LEAD, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LEAD_SWORD = new SwordItem(ToolMaterials.LEAD, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LEAD_PICKAXE = new GenericPickaxe(ToolMaterials.LEAD, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LEAD_SHOVEL = new ShovelItem(ToolMaterials.LEAD, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LEAD_HOE = new GenericHoe(ToolMaterials.LEAD, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LEAD_AXE = new GenericAxe(ToolMaterials.LEAD, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item CALCIUM_HELMET = new ArmorItem(ArmourMaterials.CALCIUM, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CALCIUM_CHESTPLATE = new ArmorItem(ArmourMaterials.CALCIUM, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CALCIUM_LEGGINGS = new ArmorItem(ArmourMaterials.CALCIUM, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CALCIUM_BOOTS = new ArmorItem(ArmourMaterials.CALCIUM, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CALCIUM_SWORD = new SwordItem(ToolMaterials.CALCIUM, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CALCIUM_PICKAXE = new GenericPickaxe(ToolMaterials.CALCIUM, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CALCIUM_SHOVEL = new ShovelItem(ToolMaterials.CALCIUM, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CALCIUM_HOE = new GenericHoe(ToolMaterials.CALCIUM, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CALCIUM_AXE = new GenericAxe(ToolMaterials.CALCIUM, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));




public static final Item BETTER_BONE_HELMET = new EnchantedArmour(ArmourMaterials.BONE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item BETTER_BONE_CHESTPLATE = new EnchantedArmour(ArmourMaterials.BONE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item BETTER_BONE_LEGGINGS = new EnchantedArmour(ArmourMaterials.BONE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item BETTER_BONE_BOOTS = new EnchantedArmour(ArmourMaterials.BONE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item BETTER_BONE_SWORD = new EnchantedSword(ToolMaterials.BONE, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item BETTER_BONE_PICKAXE = new EnchantedPickaxe(ToolMaterials.BONE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item BETTER_BONE_SHOVEL = new EnchantedShovel(ToolMaterials.BONE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item BETTER_BONE_HOE = new EnchantedHoe(ToolMaterials.BONE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item BETTER_BONE_AXE = new EnchantedAxe(ToolMaterials.BONE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));




public static final Item BONE_HELMET = new ArmorItem(ArmourMaterials.BONE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item BONE_CHESTPLATE = new ArmorItem(ArmourMaterials.BONE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item BONE_LEGGINGS = new ArmorItem(ArmourMaterials.BONE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item BONE_BOOTS = new ArmorItem(ArmourMaterials.BONE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item BONE_SWORD = new SwordItem(ToolMaterials.BONE, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item BONE_PICKAXE = new GenericPickaxe(ToolMaterials.BONE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item BONE_SHOVEL = new ShovelItem(ToolMaterials.BONE, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item BONE_HOE = new GenericHoe(ToolMaterials.BONE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item BONE_AXE = new GenericAxe(ToolMaterials.BONE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Item STEEL_HELMET = new ArmorItem(ArmourMaterials.STEEL, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item STEEL_CHESTPLATE = new ArmorItem(ArmourMaterials.STEEL, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item STEEL_LEGGINGS = new ArmorItem(ArmourMaterials.STEEL, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item STEEL_BOOTS = new ArmorItem(ArmourMaterials.STEEL, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item STEEL_SWORD = new SwordItem(ToolMaterials.STEEL, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item STEEL_PICKAXE = new GenericPickaxe(ToolMaterials.STEEL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item STEEL_SHOVEL = new ShovelItem(ToolMaterials.STEEL, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item STEEL_HOE = new GenericHoe(ToolMaterials.STEEL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item STEEL_AXE = new GenericAxe(ToolMaterials.STEEL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Item TITANIUM_HELMET = new ArmorItem(ArmourMaterials.TITANIUM, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TITANIUM_CHESTPLATE = new ArmorItem(ArmourMaterials.TITANIUM, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TITANIUM_LEGGINGS = new ArmorItem(ArmourMaterials.TITANIUM, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TITANIUM_BOOTS = new ArmorItem(ArmourMaterials.TITANIUM, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TITANIUM_SWORD = new SwordItem(ToolMaterials.TITANIUM, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TITANIUM_PICKAXE = new GenericPickaxe(ToolMaterials.TITANIUM, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TITANIUM_SHOVEL = new ShovelItem(ToolMaterials.TITANIUM, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TITANIUM_HOE = new GenericHoe(ToolMaterials.TITANIUM, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TITANIUM_AXE = new GenericAxe(ToolMaterials.TITANIUM, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Item URANIUM_HELMET = new ArmorItem(ArmourMaterials.URANIUM, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item URANIUM_CHESTPLATE = new ArmorItem(ArmourMaterials.URANIUM, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item URANIUM_LEGGINGS = new ArmorItem(ArmourMaterials.URANIUM, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item URANIUM_BOOTS = new ArmorItem(ArmourMaterials.URANIUM, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item URANIUM_SWORD = new SwordItem(ToolMaterials.URANIUM, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item URANIUM_PICKAXE = new GenericPickaxe(ToolMaterials.URANIUM, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item URANIUM_SHOVEL = new ShovelItem(ToolMaterials.URANIUM, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item URANIUM_HOE = new GenericHoe(ToolMaterials.URANIUM, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item URANIUM_AXE = new GenericAxe(ToolMaterials.URANIUM, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Item WAX_HELMET = new ArmorItem(ArmourMaterials.WAX, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WAX_CHESTPLATE = new ArmorItem(ArmourMaterials.WAX, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WAX_LEGGINGS = new ArmorItem(ArmourMaterials.WAX, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WAX_BOOTS = new ArmorItem(ArmourMaterials.WAX, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WAX_SWORD = new SwordItem(ToolMaterials.WAX, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WAX_PICKAXE = new GenericPickaxe(ToolMaterials.WAX, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WAX_SHOVEL = new ShovelItem(ToolMaterials.WAX, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WAX_HOE = new GenericHoe(ToolMaterials.WAX, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item WAX_AXE = new GenericAxe(ToolMaterials.WAX, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Item OBSIDIAN_HELMET = new ArmorItem(ArmourMaterials.OBSIDIAN, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item OBSIDIAN_CHESTPLATE = new ArmorItem(ArmourMaterials.OBSIDIAN, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item OBSIDIAN_LEGGINGS = new ArmorItem(ArmourMaterials.OBSIDIAN, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item OBSIDIAN_BOOTS = new ArmorItem(ArmourMaterials.OBSIDIAN, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item OBSIDIAN_SWORD = new SwordItem(ToolMaterials.OBSIDIAN, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item OBSIDIAN_PICKAXE = new GenericPickaxe(ToolMaterials.OBSIDIAN, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item OBSIDIAN_SHOVEL = new ShovelItem(ToolMaterials.OBSIDIAN, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item OBSIDIAN_HOE = new GenericHoe(ToolMaterials.OBSIDIAN, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item OBSIDIAN_AXE = new GenericAxe(ToolMaterials.OBSIDIAN, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item CELESTIAL_HELMET = new EnchantedArmour(ArmourMaterials.CELESTIAL, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CELESTIAL_CHESTPLATE = new EnchantedArmour(ArmourMaterials.CELESTIAL, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CELESTIAL_LEGGINGS = new EnchantedArmour(ArmourMaterials.CELESTIAL, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CELESTIAL_BOOTS = new EnchantedArmour(ArmourMaterials.CELESTIAL, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CELESTIAL_SWORD = new EnchantedSword(ToolMaterials.CELESTIAL, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CELESTIAL_PICKAXE = new EnchantedPickaxe(ToolMaterials.CELESTIAL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CELESTIAL_SHOVEL = new EnchantedShovel(ToolMaterials.CELESTIAL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CELESTIAL_HOE = new EnchantedHoe(ToolMaterials.CELESTIAL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CELESTIAL_AXE = new EnchantedAxe(ToolMaterials.CELESTIAL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item EXTRA_CELESTIAL_HELMET = new EnchantedArmour(ArmourMaterials.EXTRA_CELESTIAL, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EXTRA_CELESTIAL_CHESTPLATE = new EnchantedArmour(ArmourMaterials.EXTRA_CELESTIAL, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EXTRA_CELESTIAL_LEGGINGS = new EnchantedArmour(ArmourMaterials.EXTRA_CELESTIAL, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EXTRA_CELESTIAL_BOOTS = new EnchantedArmour(ArmourMaterials.EXTRA_CELESTIAL, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EXTRA_CELESTIAL_SWORD = new EnchantedSword(ToolMaterials.EXTRA_CELESTIAL, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EXTRA_CELESTIAL_PICKAXE = new EnchantedPickaxe(ToolMaterials.EXTRA_CELESTIAL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EXTRA_CELESTIAL_SHOVEL = new EnchantedShovel(ToolMaterials.EXTRA_CELESTIAL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EXTRA_CELESTIAL_HOE = new EnchantedHoe(ToolMaterials.EXTRA_CELESTIAL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EXTRA_CELESTIAL_AXE = new EnchantedAxe(ToolMaterials.EXTRA_CELESTIAL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item SUN_STONE_HELMET = new ArmorItem(ArmourMaterials.SUN_STONE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SUN_STONE_CHESTPLATE = new ArmorItem(ArmourMaterials.SUN_STONE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SUN_STONE_LEGGINGS = new ArmorItem(ArmourMaterials.SUN_STONE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SUN_STONE_BOOTS = new ArmorItem(ArmourMaterials.SUN_STONE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SUN_STONE_SWORD = new SwordItem(ToolMaterials.SUN_STONE, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SUN_STONE_PICKAXE = new GenericPickaxe(ToolMaterials.SUN_STONE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SUN_STONE_SHOVEL = new ShovelItem(ToolMaterials.SUN_STONE, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SUN_STONE_HOE = new GenericHoe(ToolMaterials.SUN_STONE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SUN_STONE_AXE = new GenericAxe(ToolMaterials.SUN_STONE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));



public static final Item CRYSTAL_WOOD_PLANK_HELMET = new ArmorItem(ArmourMaterials.CRYSTAL_WOOD_PLANK, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CRYSTAL_WOOD_PLANK_CHESTPLATE = new ArmorItem(ArmourMaterials.CRYSTAL_WOOD_PLANK, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CRYSTAL_WOOD_PLANK_LEGGINGS = new ArmorItem(ArmourMaterials.CRYSTAL_WOOD_PLANK, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CRYSTAL_WOOD_PLANK_BOOTS = new ArmorItem(ArmourMaterials.CRYSTAL_WOOD_PLANK, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CRYSTAL_WOOD_PLANK_SWORD = new SwordItem(ToolMaterials.CRYSTAL_WOOD_PLANK, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CRYSTAL_WOOD_PLANK_PICKAXE = new GenericPickaxe(ToolMaterials.CRYSTAL_WOOD_PLANK, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CRYSTAL_WOOD_PLANK_SHOVEL = new ShovelItem(ToolMaterials.CRYSTAL_WOOD_PLANK, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CRYSTAL_WOOD_PLANK_HOE = new GenericHoe(ToolMaterials.CRYSTAL_WOOD_PLANK, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CRYSTAL_WOOD_PLANK_AXE = new GenericAxe(ToolMaterials.CRYSTAL_WOOD_PLANK, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item CRYSTAL_WOOD_HELMET = new ArmorItem(ArmourMaterials.CRYSTAL_WOOD, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CRYSTAL_WOOD_CHESTPLATE = new ArmorItem(ArmourMaterials.CRYSTAL_WOOD, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CRYSTAL_WOOD_LEGGINGS = new ArmorItem(ArmourMaterials.CRYSTAL_WOOD, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CRYSTAL_WOOD_BOOTS = new ArmorItem(ArmourMaterials.CRYSTAL_WOOD, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CRYSTAL_WOOD_SWORD = new SwordItem(ToolMaterials.CRYSTAL_WOOD, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CRYSTAL_WOOD_PICKAXE = new GenericPickaxe(ToolMaterials.CRYSTAL_WOOD, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CRYSTAL_WOOD_SHOVEL = new ShovelItem(ToolMaterials.CRYSTAL_WOOD, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CRYSTAL_WOOD_HOE = new GenericHoe(ToolMaterials.CRYSTAL_WOOD, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CRYSTAL_WOOD_AXE = new GenericAxe(ToolMaterials.CRYSTAL_WOOD, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item KYANITE_HELMET = new ArmorItem(ArmourMaterials.KYANITE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item KYANITE_CHESTPLATE = new ArmorItem(ArmourMaterials.KYANITE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item KYANITE_LEGGINGS = new ArmorItem(ArmourMaterials.KYANITE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item KYANITE_BOOTS = new ArmorItem(ArmourMaterials.KYANITE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item KYANITE_SWORD = new SwordItem(ToolMaterials.KYANITE, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item KYANITE_PICKAXE = new GenericPickaxe(ToolMaterials.KYANITE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item KYANITE_SHOVEL = new ShovelItem(ToolMaterials.KYANITE, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item KYANITE_HOE = new GenericHoe(ToolMaterials.KYANITE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item KYANITE_AXE = new GenericAxe(ToolMaterials.KYANITE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));



public static final Item END_STONE_HELMET = new ArmorItem(ArmourMaterials.END_STONE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item END_STONE_CHESTPLATE = new ArmorItem(ArmourMaterials.END_STONE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item END_STONE_LEGGINGS = new ArmorItem(ArmourMaterials.END_STONE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item END_STONE_BOOTS = new ArmorItem(ArmourMaterials.END_STONE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item END_STONE_SWORD = new SwordItem(ToolMaterials.END_STONE, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item END_STONE_PICKAXE = new GenericPickaxe(ToolMaterials.END_STONE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item END_STONE_SHOVEL = new ShovelItem(ToolMaterials.END_STONE, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item END_STONE_HOE = new GenericHoe(ToolMaterials.END_STONE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item END_STONE_AXE = new GenericAxe(ToolMaterials.END_STONE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item CHAINMAIL_SWORD = new SwordItem(ToolMaterials.CHAINMAIL, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CHAINMAIL_PICKAXE = new GenericPickaxe(ToolMaterials.CHAINMAIL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CHAINMAIL_SHOVEL = new ShovelItem(ToolMaterials.CHAINMAIL, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CHAINMAIL_HOE = new GenericHoe(ToolMaterials.CHAINMAIL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item CHAINMAIL_AXE = new GenericAxe(ToolMaterials.CHAINMAIL, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Item LEATHER_SWORD = new SwordItem(ToolMaterials.LEATHER, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LEATHER_PICKAXE = new GenericPickaxe(ToolMaterials.LEATHER, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LEATHER_SHOVEL = new ShovelItem(ToolMaterials.LEATHER, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LEATHER_HOE = new GenericHoe(ToolMaterials.LEATHER, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LEATHER_AXE = new GenericAxe(ToolMaterials.LEATHER, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item APPLE_SWORD = new AppleSword(ToolMaterials.APPLE, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item APPLE_PICKAXE = new GenericPickaxe(ToolMaterials.APPLE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item APPLE_SHOVEL = new ShovelItem(ToolMaterials.APPLE, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item APPLE_HOE = new UltimateHoe(ToolMaterials.APPLE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item APPLE_AXE = new GenericAxe(ToolMaterials.APPLE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));




public static final Block AMETHYST_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block RUBY_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block TIGERS_EYE_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block TITANIUM_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block URANIUM_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block ALUMINIUM_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block SALT_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block SALT_BLOCK = new BasicOre(Block.Settings.of(Material.SNOW_BLOCK).strength(1.0f));
public static final Block BLACK_SAND = new BasicOre(Block.Settings.of(Material.SOIL).strength(1.0f));
public static final Block SAPPHIRE_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block UNPROCESSED_OIL_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block GASOLINE_PETROL_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block AMETHYST_BLOCK = new Block(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block RUBY_BLOCK = new Block(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block TIGERS_EYE_BLOCK = new Block(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block TITANIUM_BLOCK = new Block(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block URANIUM_BLOCK = new Block(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block ALUMINIUM_BLOCK = new Block(Block.Settings.of(Material.METAL).strength(4.0f));

public static final Block KYANITE_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block CRYSTAL_WOOD_PLANK = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block CRYSTAL_WOOD = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block SUN_STONE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block CELESTIAL_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block EXTRA_CELESTIAL_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block WAX_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block LEAD_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block CALCIUM_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block TOURMALINE_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block WHITE_TOURMALINE_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block PINK_TOURMALINE_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));


public static final Block COPPER_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block SILVER_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block TIN_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block PLATINUM_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));







private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
    return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
}

private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
    return modifiers(CountPlacementModifier.of(count), heightModifier);

}




public static final EntityType<QasemSoleimaniEntity> QASEM_SOLEIMANI = Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier("featurecreep", "qasem_soleimani"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, QasemSoleimaniEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
);




public static final EntityType<WindigoEntity> WINDIGO = Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier("featurecreep", "windigo"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, WindigoEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
);






public static final EntityType<YellowWindigoEntity> YELLOW_WINDIGO = Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier("featurecreep", "yellow_windigo"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, YellowWindigoEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
);


public static final EntityType<MechKillerRobot1000Entity> MECH_KILLER_ROBOT_1000 = Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier("featurecreep", "mech_killer_robot_1000"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, MechKillerRobot1000Entity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
);




public static final Item WINDIGO_SPAWN_EGG = new SpawnEggItem(WINDIGO, 0X738940,0XA6A15E, new Item.Settings().group(ItemGroup.MISC));
public static final Item YELLOW_WINDIGO_SPAWN_EGG = new SpawnEggItem(YELLOW_WINDIGO, 0X738940,0XA6A15E, new Item.Settings().group(ItemGroup.MISC));
//public static final Item QASEM_SOLEIMANI_SPAWN_EGG = new SpawnEggItem(QASEM_SOLEIMANI, 0X738940,0XA6A15E, new Item.Settings().group(ItemGroup.MISC));
public static final Item MECH_KILLER_ROBOT_1000_SPAWN_EGG = new SpawnEggItem(MECH_KILLER_ROBOT_1000, 0X738940,0XA6A15E, new Item.Settings().group(ItemGroup.MISC));







	
	public static void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_AMETHYST_OVERWORLD = ConfiguredFeatures.register("ore_amethyst", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, AMETHYST_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, AMETHYST_ORE.getDefaultState())), 4));
 final RegistryEntry<PlacedFeature> OVERWORLD_AMETHYST_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_amethyst_placed", ORE_AMETHYST_OVERWORLD, modifiersWithCount(4, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));

 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_SAPPHIRE_OVERWORLD = ConfiguredFeatures.register("ore_sapphire", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, SAPPHIRE_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, SAPPHIRE_ORE.getDefaultState())), 4));
 final RegistryEntry<PlacedFeature> OVERWORLD_SAPPHIRE_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_sapphire_placed", ORE_SAPPHIRE_OVERWORLD, modifiersWithCount(4, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));


 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_RUBY_OVERWORLD = ConfiguredFeatures.register("ore_ruby", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, RUBY_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, RUBY_ORE.getDefaultState())), 8));
 final RegistryEntry<PlacedFeature> OVERWORLD_RUBY_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_ruby_placed", ORE_RUBY_OVERWORLD, modifiersWithCount(10, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));



 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_SILVER_OVERWORLD = ConfiguredFeatures.register("ore_silver", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, SILVER_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, SILVER_ORE.getDefaultState())), 9));
 final RegistryEntry<PlacedFeature> OVERWORLD_SILVER_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_silver_placed", ORE_SILVER_OVERWORLD, modifiersWithCount(5, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));

 
 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_TIN_OVERWORLD = ConfiguredFeatures.register("ore_tin", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, TIN_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, TIN_ORE.getDefaultState())), 12));
 final RegistryEntry<PlacedFeature> OVERWORLD_TIN_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_tin_placed", ORE_TIN_OVERWORLD, modifiersWithCount(6, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));

 
 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_COPPER_OVERWORLD = ConfiguredFeatures.register("ore_copper", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, COPPER_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, COPPER_ORE.getDefaultState())), 12));
 final RegistryEntry<PlacedFeature> OVERWORLD_COPPER_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_copper_placed", ORE_COPPER_OVERWORLD, modifiersWithCount(5, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));
 
 
 
 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_ALUMINIUM_OVERWORLD = ConfiguredFeatures.register("ore_aluminium", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, ALUMINIUM_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ALUMINIUM_ORE.getDefaultState())), 6));
 final RegistryEntry<PlacedFeature> OVERWORLD_ALUMINIUM_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_aluminium_placed", ORE_ALUMINIUM_OVERWORLD, modifiersWithCount(12, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));


 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_PLATINUM_OVERWORLD = ConfiguredFeatures.register("ore_platinum", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, PLATINUM_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, PLATINUM_ORE.getDefaultState())), 5));
 final RegistryEntry<PlacedFeature> OVERWORLD_PLATINUM_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_platinum_placed", ORE_PLATINUM_OVERWORLD, modifiersWithCount(5, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));


 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_URANIUM_OVERWORLD = ConfiguredFeatures.register("ore_uranium", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, URANIUM_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, URANIUM_ORE.getDefaultState())), 3));
 final RegistryEntry<PlacedFeature> OVERWORLD_URANIUM_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_uranium_placed", ORE_URANIUM_OVERWORLD, modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));




 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_TITANIUM_OVERWORLD = ConfiguredFeatures.register("ore_titanium", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, TITANIUM_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, TITANIUM_ORE.getDefaultState())), 3));
 final RegistryEntry<PlacedFeature> OVERWORLD_TITANIUM_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_titanium_placed", ORE_TITANIUM_OVERWORLD, modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));




 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_TIGERS_EYE_OVERWORLD = ConfiguredFeatures.register("ore_tigers_eye", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, TIGERS_EYE_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, TIGERS_EYE_ORE.getDefaultState())), 7));
 final RegistryEntry<PlacedFeature> OVERWORLD_TIGERS_EYE_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_tigers_eye_placed", ORE_TIGERS_EYE_OVERWORLD, modifiersWithCount(5, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));




 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_SALT_OVERWORLD = ConfiguredFeatures.register("ore_salt", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, SALT_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, SALT_ORE.getDefaultState())), 8));
 final RegistryEntry<PlacedFeature> OVERWORLD_SALT_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_salt_placed", ORE_SALT_OVERWORLD, modifiersWithCount(18, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));


 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_WAX_OVERWORLD = ConfiguredFeatures.register("ore_wax", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, WAX_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, WAX_ORE.getDefaultState())), 5));
 final RegistryEntry<PlacedFeature> OVERWORLD_WAX_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_wax_placed", ORE_WAX_OVERWORLD, modifiersWithCount(10, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));

 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CALCIUM_OVERWORLD = ConfiguredFeatures.register("ore_calcium", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, CALCIUM_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, CALCIUM_ORE.getDefaultState())), 4));
 final RegistryEntry<PlacedFeature> OVERWORLD_CALCIUM_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_calcium_placed", ORE_CALCIUM_OVERWORLD, modifiersWithCount(8, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));

 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_LEAD_OVERWORLD = ConfiguredFeatures.register("ore_lead", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, LEAD_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, LEAD_ORE.getDefaultState())), 8));
 final RegistryEntry<PlacedFeature> OVERWORLD_LEAD_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_lead_placed", ORE_LEAD_OVERWORLD, modifiersWithCount(18, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));

 
 
 
 

 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_OIL_OVERWORLD = ConfiguredFeatures.register("ore_unprocessed_oil", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, UNPROCESSED_OIL_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, UNPROCESSED_OIL_ORE.getDefaultState())), 8));
 final RegistryEntry<PlacedFeature> OVERWORLD_OIL_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_oil_placed", ORE_OIL_OVERWORLD, modifiersWithCount(18, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));


 final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_GASOLINE_PETROL_OVERWORLD = ConfiguredFeatures.register("ore_gasoline_petrol", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, GASOLINE_PETROL_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, GASOLINE_PETROL_ORE.getDefaultState())), 8));
 final RegistryEntry<PlacedFeature> OVERWORLD_GASOLINE_PETROL_ORE_PLACED_FEATURE = PlacedFeatures.register("ore_gasoline_petrol_placed", ORE_GASOLINE_PETROL_OVERWORLD, modifiersWithCount(18, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));

 

 
 final RegistryEntry<ConfiguredFeature<DiskFeatureConfig, ?>> ORE_SALT_BLOCK_OVERWORLD = ConfiguredFeatures.register("salt_block_ore", Feature.DISK, new DiskFeatureConfig(SALT_BLOCK.getDefaultState(), UniformIntProvider.create(2, 6), 2, List.of(Blocks.DIRT.getDefaultState(), Blocks.GRASS_BLOCK.getDefaultState())));
 final RegistryEntry<PlacedFeature> ORE_SALT_BLOCK_OVERWORLD_PLACED = PlacedFeatures.register("salt_block_ore", ORE_SALT_BLOCK_OVERWORLD, CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP, BiomePlacementModifier.of());

 final RegistryEntry<ConfiguredFeature<DiskFeatureConfig, ?>> ORE_BLACK_SAND_OVERWORLD = ConfiguredFeatures.register("black_sand_ore", Feature.DISK, new DiskFeatureConfig(BLACK_SAND.getDefaultState(), UniformIntProvider.create(2, 6), 2, List.of(Blocks.DIRT.getDefaultState(), Blocks.GRASS_BLOCK.getDefaultState())));
 final RegistryEntry<PlacedFeature> ORE_BLACK_SAND_OVERWORLD_PLACED = PlacedFeatures.register("black_sand_ore", ORE_BLACK_SAND_OVERWORLD, CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP, BiomePlacementModifier.of());

 
 

 BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_AMETHYST_ORE_PLACED_FEATURE.getKey().get());
 BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_SAPPHIRE_ORE_PLACED_FEATURE.getKey().get());

 
 BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.TOP_LAYER_MODIFICATION, ORE_SALT_BLOCK_OVERWORLD_PLACED.getKey().get());
 BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.TOP_LAYER_MODIFICATION, ORE_BLACK_SAND_OVERWORLD_PLACED.getKey().get());

 
 
 BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_RUBY_ORE_PLACED_FEATURE.getKey().get());
 BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_ALUMINIUM_ORE_PLACED_FEATURE.getKey().get());
 BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_TIN_ORE_PLACED_FEATURE.getKey().get());
 BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_COPPER_ORE_PLACED_FEATURE.getKey().get());
 BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_SILVER_ORE_PLACED_FEATURE.getKey().get());
 BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_PLATINUM_ORE_PLACED_FEATURE.getKey().get());

 BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_URANIUM_ORE_PLACED_FEATURE.getKey().get());
 BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_TITANIUM_ORE_PLACED_FEATURE.getKey().get());
 BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_TIGERS_EYE_ORE_PLACED_FEATURE.getKey().get());
 BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_SALT_ORE_PLACED_FEATURE.getKey().get());

 BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_WAX_ORE_PLACED_FEATURE.getKey().get());
 BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_CALCIUM_ORE_PLACED_FEATURE.getKey().get());
 BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_LEAD_ORE_PLACED_FEATURE.getKey().get());

 
 BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_OIL_ORE_PLACED_FEATURE.getKey().get());
 BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, OVERWORLD_GASOLINE_PETROL_ORE_PLACED_FEATURE.getKey().get());

 
 
 
 
 
 

 //Registry.register(Registry.ITEM, new Identifier("featurecreep", "amethyst"), AMETHYST);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ruby"), RUBY);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tigers_eye_ingot"), TIGERS_EYE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "titanium_ingot"), TITANIUM);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "titanium_nugget"), TITANIUM_NUGGET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "uranium_ingot"), URANIUM);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "uranium_nugget"), URANIUM_NUGGET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "aluminium_ingot"), ALUMINIUM);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "copper_ingot"), COPPER_INGOT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tin_ingot"), TIN_INGOT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "silver_ingot"), SILVER_INGOT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "pink_tourmaline_ingot"), PINK_TOURMALINE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "pink_tourmaline_nugget"), PINK_TOURMALINE_NUGGET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "steel_ingot"), STEEL_INGOT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "oil"), Oil);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "salt"), Salt);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "mobzilla_scale"), MobzillaScale);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "queen_scale"), QueenScale);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "moth_scale"), MothScale);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "platinum_ingot"), PLATINUM_INGOT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lava_eel"), LAVA_EEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "peackock_feather"), PEACKOCK_FEATHER);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sapphire"), SAPPHIRE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "gasoline_petrol"), GASOLINE_PETROL);

 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tigers_eye_nugget"), TIGERS_EYE_NUGGET);

 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cats_eye_ingot"), CATS_EYE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cats_eye_nugget"), CATS_EYE_NUGGET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "white_tourmaline_ingot"), WHITE_TOURMALINE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "white_tourmaline_nugget"), WHITE_TOURMALINE_NUGGET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ruby_nugget"), RUBY_NUGGET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "aluminium_nugget"), ALUMINIUM_NUGGET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "copper_nugget"), COPPER_NUGGET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "silver_nugget"), SILVER_NUGGET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "platinum_nugget"), PLATINUM_NUGGET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tin_nugget"), TIN_NUGGET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "steel_nugget"), STEEL_NUGGET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sapphire_nugget"), SAPPHIRE_NUGGET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "amethyst_nugget"), AMETHYST_NUGGET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tourmaline_ingot"), TOURMALINE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tourmaline_nugget"), TOURMALINE_NUGGET);


  Registry.register(Registry.ITEM, new Identifier("featurecreep", "calcium_ingot"), CALCIUM_INGOT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "calcium_nugget"), CALCIUM_NUGGET);

Registry.register(Registry.ITEM, new Identifier("featurecreep", "lead_ingot"), LEAD_INGOT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lead_nugget"), LEAD_NUGGET);

 
 


  Registry.register(Registry.ITEM, new Identifier("featurecreep", "kyanite_ingot"), KYANITE_INGOT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "kyanite_nugget"), KYANITE_NUGGET);

Registry.register(Registry.ITEM, new Identifier("featurecreep", "celestial_ingot"), CELESTIAL_INGOT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "celestial_nugget"), CELESTIAL_NUGGET);

 

  Registry.register(Registry.ITEM, new Identifier("featurecreep", "extra_celestial_ingot"), EXTRA_CELESTIAL_NUGGET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "extra_celestial_nugget"), EXTRA_CELESTIAL_INGOT);

Registry.register(Registry.ITEM, new Identifier("featurecreep", "wax_ingot"), WAX_INGOT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wax_nugget"), WAX_NUGGET);

 



 Registry.register(Registry.ITEM, new Identifier("featurecreep", "windigo_spawn_egg"), WINDIGO_SPAWN_EGG);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "yellow_windigo_spawn_egg"), YELLOW_WINDIGO_SPAWN_EGG);
//   Registry.register(Registry.ITEM, new Identifier("featurecreep", "qasem_soleimani_spawn_egg"), QASEM_SOLEIMANI_SPAWN_EGG);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "mech_killer_robot_1000_spawn_egg"), MECH_KILLER_ROBOT_1000_SPAWN_EGG);


 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lightning_lava_sword"), LIGHTNING_LAVA_SWORD);


 Registry.register(Registry.ITEM, new Identifier("featurecreep", "raw_bacon"), RAW_BACON);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cooked_bacon"), COOKED_BACON);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "butter_candy"), BUTTER_CANDY);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "crystal_apple"), CRYSTAL_APPLE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "love_food"), LOVE_FOOD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "popcorn"), POPCORN);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "butter_food"), BUTTER_FOOD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "corn_dog"), CORN_DOG);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cooked_corn_dog"), COOKED_CORN_DOG);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "raw_crab_meat"), RAW_CRAB_MEAT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cooked_crab_meat"), COOKED_CRAB_MEAT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cheese"), CHEESE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "salad"), SALAD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "blt"), BLT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "crab_patty"), CRAB_PATTY);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "magic_apple"), MAGIC_APPLE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "peach"), PEACH);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "raw_peacock"), RAW_PEACOCK);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "coocked_peacock"), COOCKED_PEACOCK);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "blue_fish"), BLUE_FISH);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "buttered_popcorn"), BUTTERED_POPCORN);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "salted_popcorn"), SALTED_POPCORN);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "buttered_and_salted_popcorn"), BUTTERED_AND_SALTED_POPCORN);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cherry"), CHERRY);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "corn"), CORN);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "popcorn_bag"), POPCORN_BAG);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "quinoa"), QUINOA);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "radish"), RADISH);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "rice"), RICE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "rock_fish"), ROCK_FISH);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "fire_fish"), FIRE_FISH);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "spark_fish"), SPARK_FISH);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "green_fish"), GREEN_FISH);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "grey_fish"), GREY_FISH);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "pink_fish"), PINK_FISH);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sun_fish"), SUN_FISH);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "strawberry"), STRAWBERRY);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lettuce"), LETTUCE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tomato"), TOMATO);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wood_fish"), WOOD_FISH);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "raw_moose_meat"), RAW_MOOSE_MEAT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "dead_bug"), DEAD_BUG);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "magic_frog_of_strength"), MAGIC_FROG_OF_STRENGTH);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "magic_frog_of_weakness"), MAGIC_FROG_OF_WEAKNESS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "magic_frog_of_speed"), MAGIC_FROG_OF_SPEED);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "magic_frog_of_slowness"), MAGIC_FROG_OF_SLOWNESS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "magic_frog_of_regeneration"), MAGIC_FROG_OF_REGENERATION);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "magic_frog_of_poison"), MAGIC_FROG_OF_POISON);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "magic_frog_of_morph"), MAGIC_FROG_OF_MORPH);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "magic_frog_of_confusion"), MAGIC_FROG_OF_CONFUSION);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cooked_moose_meat"), COOKED_MOOSE_MEAT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "candy_cane"), CANDY_CANE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "golden_bread"), GOLDEN_BREAD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "golden_chicken"), GOLDEN_CHICKEN);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "golden_tropical_fish"), GOLDEN_TROPICAL_FISH);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "golden_cod"), GOLDEN_COD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "golden_porkchop"), GOLDEN_PORKCHOP);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "watermelon_slice"), WATERMELON_SLICE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "golden_mushroom_stew"), GOLDEN_MUSHROOM_STEW);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "golden_steak"), GOLDEN_STEAK);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "golden_cookie"), GOLDEN_COOKIE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "golden_potato"), GOLDEN_POTATO);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "golden_pumpkin_pie"), GOLDEN_PUMPKIN_PIE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "golden_rotton_flesh"), GOLDEN_ROTTON_FLESH);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "golden_carrot"), GOLDEN_CARROT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "golden_pufferfish"), GOLDEN_PUFFERFISH);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "golden_salmon"), GOLDEN_SALMON);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "golden_candycane"), GOLDEN_CANDYCANE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ultimate_apple"), ULTIMATE_APPLE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "enchanted_golden_carrot"), ENCHANTED_GOLDEN_CARROT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "enchanted_golden_steak"), ENCHANTED_GOLDEN_STEAK);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "enchanted_golden_cod"), ENCHANTED_GOLDEN_COD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "enchanted_golden_cookie"), ENCHANTED_GOLDEN_COOKIE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "enchanted_golden_candycane"), ENCHANTED_GOLDEN_CANDYCANE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "raddish_stew"), RADDISH_STEW);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "drinkable_gasoline_petrol"), DRINKABLE_GASOLINE_PETROL);

   
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "emerald_helmet"), EMERALD_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "emerald_chestplate"), EMERALD_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "emerald_leggings"), EMERALD_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "emerald_boots"), EMERALD_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "emerald_sword"), EMERALD_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "emerald_pickaxe"), EMERALD_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "emerald_shovel"), EMERALD_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "emerald_hoe"), EMERALD_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "emerald_axe"), EMERALD_AXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ruby_helmet"), RUBY_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ruby_chestplate"), RUBY_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ruby_leggings"), RUBY_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ruby_boots"), RUBY_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ruby_sword"), RUBY_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ruby_pickaxe"), RUBY_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ruby_shovel"), RUBY_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ruby_hoe"), RUBY_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ruby_axe"), RUBY_AXE);
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "experience_helmet"), EXPERIENCE_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "experience_chestplate"), EXPERIENCE_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "experience_leggings"), EXPERIENCE_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "experience_boots"), EXPERIENCE_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "experience_sword"), EXPERIENCE_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "experience_pickaxe"), EXPERIENCE_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "experience_shovel"), EXPERIENCE_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "experience_hoe"), EXPERIENCE_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "experience_axe"), EXPERIENCE_AXE);
 
// Registry.register(Registry.ITEM, new Identifier("featurecreep", "amethyst_helmet"), AMETHYST_HELMET);
// Registry.register(Registry.ITEM, new Identifier("featurecreep", "amethyst_chestplate"), AMETHYST_CHESTPLATE);
 //Registry.register(Registry.ITEM, new Identifier("featurecreep", "amethyst_leggings"), AMETHYST_LEGGINGS);
 //Registry.register(Registry.ITEM, new Identifier("featurecreep", "amethyst_boots"), AMETHYST_BOOTS);
// Registry.register(Registry.ITEM, new Identifier("featurecreep", "amethyst_sword"), AMETHYST_SWORD);
// Registry.register(Registry.ITEM, new Identifier("featurecreep", "amethyst_pickaxe"), AMETHYST_PICKAXE);
// Registry.register(Registry.ITEM, new Identifier("featurecreep", "amethyst_shovel"), AMETHYST_SHOVEL);
// Registry.register(Registry.ITEM, new Identifier("featurecreep", "amethyst_hoe"), AMETHYST_HOE);
// Registry.register(Registry.ITEM, new Identifier("featurecreep", "amethyst_axe"), AMETHYST_AXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sapphire_helmet"), SAPPHIRE_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sapphire_chestplate"), SAPPHIRE_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sapphire_leggings"), SAPPHIRE_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sapphire_boots"), SAPPHIRE_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sapphire_sword"), SAPPHIRE_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sapphire_pickaxe"), SAPPHIRE_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sapphire_shovel"), SAPPHIRE_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sapphire_hoe"), SAPPHIRE_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sapphire_axe"), SAPPHIRE_AXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tigers_eye_helmet"), TIGERS_EYE_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tigers_eye_chestplate"), TIGERS_EYE_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tigers_eye_leggings"), TIGERS_EYE_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tigers_eye_boots"), TIGERS_EYE_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tigers_eye_sword"), TIGERS_EYE_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tigers_eye_pickaxe"), TIGERS_EYE_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tigers_eye_shovel"), TIGERS_EYE_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tigers_eye_hoe"), TIGERS_EYE_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tigers_eye_axe"), TIGERS_EYE_AXE);




 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cats_eye_helmet"), CATS_EYE_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cats_eye_chestplate"), CATS_EYE_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cats_eye_leggings"), CATS_EYE_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cats_eye_boots"), CATS_EYE_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cats_eye_sword"), CATS_EYE_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cats_eye_pickaxe"), CATS_EYE_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cats_eye_shovel"), CATS_EYE_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cats_eye_hoe"), CATS_EYE_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cats_eye_axe"), CATS_EYE_AXE);






 Registry.register(Registry.ITEM, new Identifier("featurecreep", "copper_helmet"), COPPER_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "copper_chestplate"), COPPER_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "copper_leggings"), COPPER_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "copper_boots"), COPPER_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "copper_sword"), COPPER_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "copper_pickaxe"), COPPER_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "copper_shovel"), COPPER_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "copper_hoe"), COPPER_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "copper_axe"), COPPER_AXE);
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "platinum_helmet"), PLATINUM_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "platinum_chestplate"), PLATINUM_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "platinum_leggings"), PLATINUM_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "platinum_boots"), PLATINUM_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "platinum_sword"), PLATINUM_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "platinum_pickaxe"), PLATINUM_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "platinum_shovel"), PLATINUM_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "platinum_hoe"), PLATINUM_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "platinum_axe"), PLATINUM_AXE);
 
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "silver_helmet"), SILVER_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "silver_chestplate"), SILVER_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "silver_leggings"), SILVER_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "silver_boots"), SILVER_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "silver_sword"), SILVER_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "silver_pickaxe"), SILVER_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "silver_shovel"), SILVER_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "silver_hoe"), SILVER_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "silver_axe"), SILVER_AXE);
 
 
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "aluminium_helmet"), ALUMINIUM_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "aluminium_chestplate"), ALUMINIUM_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "aluminium_leggings"), ALUMINIUM_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "aluminium_boots"), ALUMINIUM_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "aluminium_sword"), ALUMINIUM_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "aluminium_pickaxe"), ALUMINIUM_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "aluminium_shovel"), ALUMINIUM_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "aluminium_hoe"), ALUMINIUM_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "aluminium_axe"), ALUMINIUM_AXE);
 
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tin_helmet"), TIN_HELMET);        
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tin_chestplate"), TIN_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tin_leggings"), TIN_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tin_boots"), TIN_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tin_sword"), TIN_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tin_pickaxe"), TIN_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tin_shovel"), TIN_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tin_hoe"), TIN_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tin_axe"), TIN_AXE);
 
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "pink_tourmaline_helmet"), PINK_TOURMALINE_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "pink_tourmaline_chestplate"), PINK_TOURMALINE_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "pink_tourmaline_leggings"), PINK_TOURMALINE_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "pink_tourmaline_boots"), PINK_TOURMALINE_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "pink_tourmaline_sword"), PINK_TOURMALINE_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "pink_tourmaline_pickaxe"), PINK_TOURMALINE_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "pink_tourmaline_shovel"), PINK_TOURMALINE_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "pink_tourmaline_hoe"), PINK_TOURMALINE_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "pink_tourmaline_axe"), PINK_TOURMALINE_AXE);
 
 
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "optimised_pickaxe"), OPTIMISED_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "optimised_shovel"), OPTIMISED_SHOVEL);
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ultimate_helmet"), ULTIMATE_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ultimate_chestplate"), ULTIMATE_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ultimate_leggings"), ULTIMATE_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ultimate_boots"), ULTIMATE_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ultimate_sword"), ULTIMATE_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ultimate_pickaxe"), ULTIMATE_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ultimate_shovel"), ULTIMATE_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ultimate_hoe"), ULTIMATE_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ultimate_axe"), ULTIMATE_AXE);
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "royal_guardian_helmet"), ROYAL_GUARDIAN_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "royal_guardian_chestplate"), ROYAL_GUARDIAN_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "royal_guardian_leggings"), ROYAL_GUARDIAN_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "royal_guardian_boots"), ROYAL_GUARDIAN_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "royal_guardian_sword"), ROYAL_GUARDIAN_SWORD);
Registry.register(Registry.ITEM, new Identifier("featurecreep", "royal_guardian_mega_sword"), ROYAL_GUARDIAN_MEGA_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "royal_guardian_pickaxe"), ROYAL_GUARDIAN_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "royal_guardian_shovel"), ROYAL_GUARDIAN_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "royal_guardian_hoe"), ROYAL_GUARDIAN_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "royal_guardian_axe"), ROYAL_GUARDIAN_AXE);
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "queen_scale_helmet"), QUEEN_SCALE_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "queen_scale_chestplate"), QUEEN_SCALE_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "queen_scale_leggings"), QUEEN_SCALE_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "queen_scale_boots"), QUEEN_SCALE_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "queen_scale_sword"), QUEEN_SCALE_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "queen_scale_pickaxe"), QUEEN_SCALE_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "queen_scale_shovel"), QUEEN_SCALE_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "queen_scale_hoe"), QUEEN_SCALE_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "queen_scale_axe"), QUEEN_SCALE_AXE);
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "queen_scale_mega_battle_axe"), QUEEN_SCALE_MEGA_BATTLE_AXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "mega_battle_axe"), MEGA_BATTLE_AXE);

 
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "mobzilla_scale_helmet"), MOBZILLA_SCALE_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "mobzilla_scale_chestplate"), MOBZILLA_SCALE_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "mobzilla_scale_leggings"), MOBZILLA_SCALE_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "mobzilla_scale_boots"), MOBZILLA_SCALE_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "mobzilla_scale_sword"), MOBZILLA_SCALE_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "mobzilla_scale_pickaxe"), MOBZILLA_SCALE_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "mobzilla_scale_shovel"), MOBZILLA_SCALE_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "mobzilla_scale_hoe"), MOBZILLA_SCALE_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "mobzilla_scale_axe"), MOBZILLA_SCALE_AXE);
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "moth_scale_helmet"), MOTH_SCALE_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "moth_scale_chestplate"), MOTH_SCALE_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "moth_scale_leggings"), MOTH_SCALE_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "moth_scale_boots"), MOTH_SCALE_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "moth_scale_sword"), MOTH_SCALE_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "moth_scale_pickaxe"), MOTH_SCALE_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "moth_scale_shovel"), MOTH_SCALE_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "moth_scale_hoe"), MOTH_SCALE_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "moth_scale_axe"), MOTH_SCALE_AXE);
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lapis_block_helmet"), LAPIS_BLOCK_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lapis_block_chestplate"), LAPIS_BLOCK_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lapis_block_leggings"), LAPIS_BLOCK_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lapis_block_boots"), LAPIS_BLOCK_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lapis_block_sword"), LAPIS_BLOCK_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lapis_block_pickaxe"), LAPIS_BLOCK_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lapis_block_shovel"), LAPIS_BLOCK_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lapis_block_hoe"), LAPIS_BLOCK_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lapis_block_axe"), LAPIS_BLOCK_AXE);
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lava_eel_helmet"), LAVA_EEL_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lava_eel_chestplate"), LAVA_EEL_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lava_eel_leggings"), LAVA_EEL_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lava_eel_boots"), LAVA_EEL_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lava_eel_sword"), LAVA_EEL_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lava_eel_pickaxe"), LAVA_EEL_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lava_eel_shovel"), LAVA_EEL_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lava_eel_hoe"), LAVA_EEL_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lava_eel_axe"), LAVA_EEL_AXE);
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "peacock_feather_helmet"), PEACOCK_FEATHER_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "peacock_feather_chestplate"), PEACOCK_FEATHER_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "peacock_feather_leggings"), PEACOCK_FEATHER_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "peacock_feather_boots"), PEACOCK_FEATHER_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "peacock_feather_sword"), PEACOCK_FEATHER_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "peacock_feather_pickaxe"), PEACOCK_FEATHER_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "peacock_feather_shovel"), PEACOCK_FEATHER_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "peacock_feather_hoe"), PEACOCK_FEATHER_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "peacock_feather_axe"), PEACOCK_FEATHER_AXE);
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cz_slow_boots"), CZ_SLOW_BOOTS);
     
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wood_plank_helmet"), WOOD_PLANK_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wood_plank_chestplate"), WOOD_PLANK_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wood_plank_leggings"), WOOD_PLANK_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wood_plank_boots"), WOOD_PLANK_BOOTS);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tourch_light_wood_plank_helmet"), TOURCH_LIGHT_WOOD_PLANK_HELMET);

 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cobble_stone_helmet"), COBBLE_STONE_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cobble_stone_chestplate"), COBBLE_STONE_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cobble_stone_leggings"), COBBLE_STONE_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cobble_stone_boots"), COBBLE_STONE_BOOTS);
 
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "chainmail_sword"), CHAINMAIL_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "chainmail_pickaxe"), CHAINMAIL_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "chainmail_shovel"), CHAINMAIL_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "chainmail_hoe"), CHAINMAIL_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "chainmail_axe"), CHAINMAIL_AXE);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "leather_sword"), LEATHER_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "leather_pickaxe"), LEATHER_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "leather_shovel"), LEATHER_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "leather_hoe"), LEATHER_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "leather_axe"), LEATHER_AXE);
 
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "apple_sword"), APPLE_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "apple_pickaxe"), APPLE_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "apple_shovel"), APPLE_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "apple_hoe"), APPLE_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "apple_axe"), APPLE_AXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "equestria_apple"), EQUESTRIA_APPLE);

 
 
 
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ruby_on_rails_helmet"), RUBY_ON_RAILS_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ruby_on_rails_chestplate"), RUBY_ON_RAILS_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ruby_on_rails_leggings"), RUBY_ON_RAILS_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ruby_on_rails_boots"), RUBY_ON_RAILS_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ruby_on_rails_sword"), RUBY_ON_RAILS_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ruby_on_rails_pickaxe"), RUBY_ON_RAILS_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ruby_on_rails_shovel"), RUBY_ON_RAILS_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ruby_on_rails_hoe"), RUBY_ON_RAILS_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ruby_on_rails_axe"), RUBY_ON_RAILS_AXE);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "white_tourmaline_helmet"), WHITE_TOURMALINE_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "white_tourmaline_chestplate"), WHITE_TOURMALINE_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "white_tourmaline_leggings"), WHITE_TOURMALINE_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "white_tourmaline_boots"), WHITE_TOURMALINE_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "white_tourmaline_sword"), WHITE_TOURMALINE_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "white_tourmaline_pickaxe"), WHITE_TOURMALINE_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "white_tourmaline_shovel"), WHITE_TOURMALINE_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "white_tourmaline_hoe"), WHITE_TOURMALINE_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "white_tourmaline_axe"), WHITE_TOURMALINE_AXE);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tourmaline_helmet"), TOURMALINE_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tourmaline_chestplate"), TOURMALINE_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tourmaline_leggings"), TOURMALINE_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tourmaline_boots"), TOURMALINE_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tourmaline_sword"), TOURMALINE_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tourmaline_pickaxe"), TOURMALINE_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tourmaline_shovel"), TOURMALINE_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tourmaline_hoe"), TOURMALINE_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tourmaline_axe"), TOURMALINE_AXE);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_sand_helmet"), RED_SAND_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_sand_chestplate"), RED_SAND_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_sand_leggings"), RED_SAND_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_sand_boots"), RED_SAND_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_sand_sword"), RED_SAND_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_sand_pickaxe"), RED_SAND_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_sand_shovel"), RED_SAND_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_sand_hoe"), RED_SAND_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_sand_axe"), RED_SAND_AXE);
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sand_helmet"), SAND_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sand_chestplate"), SAND_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sand_leggings"), SAND_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sand_boots"), SAND_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sand_sword"), SAND_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sand_pickaxe"), SAND_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sand_shovel"), SAND_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sand_hoe"), SAND_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sand_axe"), SAND_AXE);
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_stone_helmet"), RED_STONE_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_stone_chestplate"), RED_STONE_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_stone_leggings"), RED_STONE_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_stone_boots"), RED_STONE_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_stone_sword"), RED_STONE_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_stone_pickaxe"), RED_STONE_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_stone_shovel"), RED_STONE_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_stone_hoe"), RED_STONE_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_stone_axe"), RED_STONE_AXE);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_stone_block_helmet"), RED_STONE_BLOCK_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_stone_block_chestplate"), RED_STONE_BLOCK_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_stone_block_leggings"), RED_STONE_BLOCK_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_stone_block_boots"), RED_STONE_BLOCK_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_stone_block_sword"), RED_STONE_BLOCK_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_stone_block_pickaxe"), RED_STONE_BLOCK_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_stone_block_shovel"), RED_STONE_BLOCK_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_stone_block_hoe"), RED_STONE_BLOCK_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "red_stone_block_axe"), RED_STONE_BLOCK_AXE);
 
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "quartz_helmet"), QUARTZ_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "quartz_chestplate"), QUARTZ_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "quartz_leggings"), QUARTZ_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "quartz_boots"), QUARTZ_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "quartz_sword"), QUARTZ_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "quartz_pickaxe"), QUARTZ_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "quartz_shovel"), QUARTZ_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "quartz_hoe"), QUARTZ_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "quartz_axe"), QUARTZ_AXE);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "quartz_block_helmet"), QUARTZ_BLOCK_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "quartz_block_chestplate"), QUARTZ_BLOCK_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "quartz_block_leggings"), QUARTZ_BLOCK_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "quartz_block_boots"), QUARTZ_BLOCK_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "quartz_block_sword"), QUARTZ_BLOCK_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "quartz_block_pickaxe"), QUARTZ_BLOCK_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "quartz_block_shovel"), QUARTZ_BLOCK_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "quartz_block_hoe"), QUARTZ_BLOCK_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "quartz_block_axe"), QUARTZ_BLOCK_AXE);
 
 
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "dirt_helmet"), DIRT_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "dirt_chestplate"), DIRT_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "dirt_leggings"), DIRT_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "dirt_boots"), DIRT_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "dirt_sword"), DIRT_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "dirt_pickaxe"), DIRT_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "dirt_shovel"), DIRT_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "dirt_hoe"), DIRT_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "dirt_axe"), DIRT_AXE);
 
 
 
 

 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "stone_helmet"), STONE_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "stone_chestplate"), STONE_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "stone_leggings"), STONE_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "stone_boots"), STONE_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "stone_sword"), STONE_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "stone_pickaxe"), STONE_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "stone_shovel"), STONE_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "stone_hoe"), STONE_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "stone_axe"), STONE_AXE);
 
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wood_block_helmet"), WOOD_BLOCK_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wood_block_chestplate"), WOOD_BLOCK_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wood_block_leggings"), WOOD_BLOCK_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wood_block_boots"), WOOD_BLOCK_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wood_block_sword"), WOOD_BLOCK_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wood_block_pickaxe"), WOOD_BLOCK_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wood_block_shovel"), WOOD_BLOCK_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wood_block_hoe"), WOOD_BLOCK_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wood_block_axe"), WOOD_BLOCK_AXE);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "netherrack_bloodstone_helmet"), NETHERRACK_BLOODSTONE_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "netherrack_bloodstone_chestplate"), NETHERRACK_BLOODSTONE_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "netherrack_bloodstone_leggings"), NETHERRACK_BLOODSTONE_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "netherrack_bloodstone_boots"), NETHERRACK_BLOODSTONE_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "netherrack_bloodstone_sword"), NETHERRACK_BLOODSTONE_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "netherrack_bloodstone_pickaxe"), NETHERRACK_BLOODSTONE_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "netherrack_bloodstone_shovel"), NETHERRACK_BLOODSTONE_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "netherrack_bloodstone_hoe"), NETHERRACK_BLOODSTONE_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "netherrack_bloodstone_axe"), NETHERRACK_BLOODSTONE_AXE);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lapis_helmet"), LAPIS_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lapis_chestplate"), LAPIS_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lapis_leggings"), LAPIS_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lapis_boots"), LAPIS_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lapis_sword"), LAPIS_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lapis_pickaxe"), LAPIS_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lapis_shovel"), LAPIS_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lapis_hoe"), LAPIS_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lapis_axe"), LAPIS_AXE);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "coarse_dirt_helmet"), COARSE_DIRT_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "coarse_dirt_chestplate"), COARSE_DIRT_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "coarse_dirt_leggings"), COARSE_DIRT_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "coarse_dirt_boots"), COARSE_DIRT_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "coarse_dirt_sword"), COARSE_DIRT_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "coarse_dirt_pickaxe"), COARSE_DIRT_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "coarse_dirt_shovel"), COARSE_DIRT_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "coarse_dirt_hoe"), COARSE_DIRT_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "coarse_dirt_axe"), COARSE_DIRT_AXE);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cactus_helmet"), CACTUS_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cactus_chestplate"), CACTUS_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cactus_leggings"), CACTUS_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cactus_boots"), CACTUS_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cactus_sword"), CACTUS_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cactus_pickaxe"), CACTUS_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cactus_shovel"), CACTUS_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cactus_hoe"), CACTUS_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "cactus_axe"), CACTUS_AXE);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "gravel_helmet"), GRAVEL_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "gravel_chestplate"), GRAVEL_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "gravel_leggings"), GRAVEL_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "gravel_boots"), GRAVEL_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "gravel_sword"), GRAVEL_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "gravel_pickaxe"), GRAVEL_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "gravel_shovel"), GRAVEL_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "gravel_hoe"), GRAVEL_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "gravel_axe"), GRAVEL_AXE);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "flint_helmet"), FLINT_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "flint_chestplate"), FLINT_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "flint_leggings"), FLINT_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "flint_boots"), FLINT_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "flint_sword"), FLINT_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "flint_pickaxe"), FLINT_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "flint_shovel"), FLINT_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "flint_hoe"), FLINT_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "flint_axe"), FLINT_AXE);
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "coal_helmet"), COAL_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "coal_chestplate"), COAL_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "coal_leggings"), COAL_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "coal_boots"), COAL_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "coal_sword"), COAL_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "coal_pickaxe"), COAL_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "coal_shovel"), COAL_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "coal_hoe"), COAL_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "coal_axe"), COAL_AXE);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lead_helmet"), LEAD_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lead_chestplate"), LEAD_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lead_leggings"), LEAD_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lead_boots"), LEAD_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lead_sword"), LEAD_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lead_pickaxe"), LEAD_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lead_shovel"), LEAD_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lead_hoe"), LEAD_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lead_axe"), LEAD_AXE);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "calcium_helmet"), CALCIUM_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "calcium_chestplate"), CALCIUM_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "calcium_leggings"), CALCIUM_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "calcium_boots"), CALCIUM_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "calcium_sword"), CALCIUM_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "calcium_pickaxe"), CALCIUM_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "calcium_shovel"), CALCIUM_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "calcium_hoe"), CALCIUM_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "calcium_axe"), CALCIUM_AXE);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "steel_helmet"), STEEL_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "steel_chestplate"), STEEL_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "steel_leggings"), STEEL_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "steel_boots"), STEEL_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "steel_sword"), STEEL_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "steel_pickaxe"), STEEL_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "steel_shovel"), STEEL_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "steel_hoe"), STEEL_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "steel_axe"), STEEL_AXE);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "bone_helmet"), BONE_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "bone_chestplate"), BONE_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "bone_leggings"), BONE_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "bone_boots"), BONE_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "bone_sword"), BONE_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "bone_pickaxe"), BONE_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "bone_shovel"), BONE_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "bone_hoe"), BONE_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "bone_axe"), BONE_AXE);
 
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "better_bone_helmet"), BETTER_BONE_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "better_bone_chestplate"), BETTER_BONE_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "better_bone_leggings"), BETTER_BONE_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "better_bone_boots"), BETTER_BONE_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "better_bone_sword"), BETTER_BONE_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "better_bone_pickaxe"), BETTER_BONE_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "better_bone_shovel"), BETTER_BONE_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "better_bone_hoe"), BETTER_BONE_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "better_bone_axe"), BETTER_BONE_AXE);
 
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "titanium_helmet"), TITANIUM_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "titanium_chestplate"), TITANIUM_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "titanium_leggings"), TITANIUM_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "titanium_boots"), TITANIUM_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "titanium_sword"), TITANIUM_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "titanium_pickaxe"), TITANIUM_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "titanium_shovel"), TITANIUM_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "titanium_hoe"), TITANIUM_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "titanium_axe"), TITANIUM_AXE);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "uranium_helmet"), URANIUM_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "uranium_chestplate"), URANIUM_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "uranium_leggings"), URANIUM_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "uranium_boots"), URANIUM_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "uranium_sword"), URANIUM_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "uranium_pickaxe"), URANIUM_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "uranium_shovel"), URANIUM_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "uranium_hoe"), URANIUM_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "uranium_axe"), URANIUM_AXE);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wax_helmet"), WAX_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wax_chestplate"), WAX_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wax_leggings"), WAX_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wax_boots"), WAX_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wax_sword"), WAX_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wax_pickaxe"), WAX_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wax_shovel"), WAX_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wax_hoe"), WAX_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wax_axe"), WAX_AXE);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "obsidian_helmet"), OBSIDIAN_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "obsidian_chestplate"), OBSIDIAN_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "obsidian_leggings"), OBSIDIAN_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "obsidian_boots"), OBSIDIAN_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "obsidian_sword"), OBSIDIAN_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "obsidian_pickaxe"), OBSIDIAN_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "obsidian_shovel"), OBSIDIAN_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "obsidian_hoe"), OBSIDIAN_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "obsidian_axe"), OBSIDIAN_AXE);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "celestial_helmet"), CELESTIAL_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "celestial_chestplate"), CELESTIAL_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "celestial_leggings"), CELESTIAL_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "celestial_boots"), CELESTIAL_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "celestial_sword"), CELESTIAL_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "celestial_pickaxe"), CELESTIAL_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "celestial_shovel"), CELESTIAL_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "celestial_hoe"), CELESTIAL_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "celestial_axe"), CELESTIAL_AXE);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "extra_celestial_helmet"), EXTRA_CELESTIAL_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "extra_celestial_chestplate"), EXTRA_CELESTIAL_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "extra_celestial_leggings"), EXTRA_CELESTIAL_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "extra_celestial_boots"), EXTRA_CELESTIAL_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "extra_celestial_sword"), EXTRA_CELESTIAL_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "extra_celestial_pickaxe"),EXTRA_CELESTIAL_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "extra_celestial_shovel"), EXTRA_CELESTIAL_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "extra_celestial_hoe"), EXTRA_CELESTIAL_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "extra_celestial_axe"), EXTRA_CELESTIAL_AXE);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sun_stone_helmet"), SUN_STONE_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sun_stone_chestplate"), SUN_STONE_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sun_stone_leggings"), SUN_STONE_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sun_stone_boots"), SUN_STONE_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sun_stone_sword"), SUN_STONE_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sun_stone_pickaxe"), SUN_STONE_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sun_stone_shovel"), SUN_STONE_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sun_stone_hoe"), SUN_STONE_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sun_stone_axe"), SUN_STONE_AXE);
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "crystal_wood_helmet"), CRYSTAL_WOOD_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "crystal_wood_chestplate"), CRYSTAL_WOOD_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "crystal_wood_leggings"), CRYSTAL_WOOD_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "crystal_wood_boots"), CRYSTAL_WOOD_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "crystal_wood_sword"), CRYSTAL_WOOD_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "crystal_wood_pickaxe"), CRYSTAL_WOOD_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "crystal_wood_shovel"), CRYSTAL_WOOD_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "crystal_wood_hoe"), CRYSTAL_WOOD_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "crystal_wood_axe"), CRYSTAL_WOOD_AXE);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "crystal_wood_plank_helmet"), CRYSTAL_WOOD_PLANK_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "crystal_wood_plank_chestplate"), CRYSTAL_WOOD_PLANK_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "crystal_wood_plank_leggings"), CRYSTAL_WOOD_PLANK_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "crystal_wood_plank_boots"), CRYSTAL_WOOD_PLANK_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "crystal_wood_plank_sword"), CRYSTAL_WOOD_PLANK_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "crystal_wood_plank_pickaxe"), CRYSTAL_WOOD_PLANK_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "crystal_wood_plank_shovel"), CRYSTAL_WOOD_PLANK_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "crystal_wood_plank_hoe"), CRYSTAL_WOOD_PLANK_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "crystal_wood_plank_axe"), CRYSTAL_WOOD_PLANK_AXE);
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "kyanite_helmet"), KYANITE_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "kyanite_chestplate"), KYANITE_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "kyanite_leggings"), KYANITE_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "kyanite_boots"), KYANITE_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "kyanite_sword"), KYANITE_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "kyanite_pickaxe"), KYANITE_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "kyanite_shovel"), KYANITE_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "kyanite_hoe"), KYANITE_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "kyanite_axe"), KYANITE_AXE);
 
 
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "end_stone_helmet"), END_STONE_HELMET);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "end_stone_chestplate"), END_STONE_CHESTPLATE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "end_stone_leggings"), END_STONE_LEGGINGS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "end_stone_boots"), END_STONE_BOOTS);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "end_stone_sword"), END_STONE_SWORD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "end_stone_pickaxe"), END_STONE_PICKAXE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "end_stone_shovel"), END_STONE_SHOVEL);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "end_stone_hoe"), END_STONE_HOE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "end_stone_axe"), END_STONE_AXE);
 
 
 
 
 
 
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "miners_dream"), MINERS_DREAM);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "large_miners_dream"), LARGE_MINERS_DREAM);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "digger"), DIGGER);

 
 
 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "amethyst_ore"), AMETHYST_ORE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "amethyst_ore"), new BlockItem(AMETHYST_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "ruby_ore"), RUBY_ORE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ruby_ore"), new BlockItem(RUBY_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "tigers_eye_ore"), TIGERS_EYE_ORE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tigers_eye_ore"), new BlockItem(TIGERS_EYE_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "titanium_ore"), TITANIUM_ORE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "titanium_ore"), new BlockItem(TITANIUM_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "uranium_ore"), URANIUM_ORE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "uranium_ore"), new BlockItem(URANIUM_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "aluminium_ore"), ALUMINIUM_ORE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "aluminium_ore"), new BlockItem(ALUMINIUM_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "salt_ore"), SALT_ORE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "salt_ore"), new BlockItem(SALT_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "salt_block"), SALT_BLOCK);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "salt_block"), new BlockItem(SALT_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "black_sand"), BLACK_SAND);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "black_sand"), new BlockItem(BLACK_SAND, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 
 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "unprocessed_oil_ore"), UNPROCESSED_OIL_ORE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "unprocessed_oil_ore"), new BlockItem(UNPROCESSED_OIL_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "gasoline_petrol_ore"), GASOLINE_PETROL_ORE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "gasoline_petrol_ore"), new BlockItem(GASOLINE_PETROL_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "ruby_block"), RUBY_BLOCK);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "ruby_block"), new BlockItem(RUBY_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 
 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "amethyst_block"), AMETHYST_BLOCK);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "amethyst_block"), new BlockItem(AMETHYST_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "tigers_eye_block"), TIGERS_EYE_BLOCK);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tigers_eye_block"), new BlockItem(TIGERS_EYE_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "titanium_block"), TITANIUM_BLOCK);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "titanium_block"), new BlockItem(TITANIUM_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "uranium_block"), URANIUM_BLOCK);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "uranium_block"), new BlockItem(URANIUM_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "aluminium_block"), ALUMINIUM_BLOCK);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "aluminium_block"), new BlockItem(ALUMINIUM_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 
 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "kyanite_ore"), KYANITE_ORE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "kyanite_ore"), new BlockItem(KYANITE_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "crystal_wood_plank"), CRYSTAL_WOOD_PLANK);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "crystal_wood_plank"), new BlockItem(CRYSTAL_WOOD_PLANK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "sun_stone"), SUN_STONE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sun_stone"), new BlockItem(SUN_STONE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "crystal_wood"), CRYSTAL_WOOD);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "crystal_wood"), new BlockItem(CRYSTAL_WOOD, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "celestial_ore"), CELESTIAL_ORE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "celestial_ore"), new BlockItem(CELESTIAL_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "extra_celestial_ore"), EXTRA_CELESTIAL_ORE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "extra_celestial_ore"), new BlockItem(EXTRA_CELESTIAL_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 
 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "wax_ore"), WAX_ORE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "wax_ore"), new BlockItem(WAX_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "lead_ore"), LEAD_ORE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lead_ore"), new BlockItem(LEAD_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "calcium_ore"), CALCIUM_ORE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "calcium_ore"), new BlockItem(CALCIUM_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "tourmaline_ore"), TOURMALINE_ORE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tourmaline_ore"), new BlockItem(TOURMALINE_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "white_tourmaline_ore"), WHITE_TOURMALINE_ORE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "white_tourmaline_ore"), new BlockItem(WHITE_TOURMALINE_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "pink_tourmaline_ore"), PINK_TOURMALINE_ORE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "pink_tourmaline_ore"), new BlockItem(PINK_TOURMALINE_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 


 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "tin_ore"), TIN_ORE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tin_ore"), new BlockItem(TIN_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "copper_ore"), COPPER_ORE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "copper_ore"), new BlockItem(COPPER_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 
 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "silver_ore"), SILVER_ORE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "silver_ore"), new BlockItem(SILVER_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 
 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "platinum_ore"), PLATINUM_ORE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "platinum_ore"), new BlockItem(PLATINUM_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 
 
 
     
 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "sapphire_ore"), SAPPHIRE_ORE);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "sapphire_ore"), new BlockItem(SAPPHIRE_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 
 
 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "corn_plant"), CORN_PLANT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "corn_plant"), new BlockItem(CORN_PLANT, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 
 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "tomato_plant"), TOMATO_PLANT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "tomato_plant"), new BlockItem(TOMATO_PLANT, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "unconfined_tomato_plant"), UNCONFINED_TOMATO_PLANT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "unconfined_tomato_plant"), new BlockItem(UNCONFINED_TOMATO_PLANT, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 
 
 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "quinoa_plant"), QUINOA_PLANT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "quinoa_plant"), new BlockItem(QUINOA_PLANT, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 
 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "raddish_plant"), RADDISH_PLANT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "raddish_plant"), new BlockItem(RADDISH_PLANT, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 
 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "rice_plant"), RICE_PLANT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "rice_plant"), new BlockItem(RICE_PLANT, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "strawberry_plant"), STRAWBERRY_PLANT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "strawberry_plant"), new BlockItem(STRAWBERRY_PLANT, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 
 Registry.register(Registry.BLOCK, new Identifier("featurecreep", "lettuce_plant"), LETTUCE_PLANT);
 Registry.register(Registry.ITEM, new Identifier("featurecreep", "lettuce_plant"), new BlockItem(LETTUCE_PLANT, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

 
 
 
 
 

 
 
  //Thanks to Charcoal Block mod (Tunacan) Fabric for this
 FuelRegistry.INSTANCE.add((ItemConvertible)Oil, Integer.valueOf(40));
 FuelRegistry.INSTANCE.add((ItemConvertible)GASOLINE_PETROL, Integer.valueOf(40));

 
 
 
 FabricDefaultAttributeRegistry.register(QASEM_SOLEIMANI, QasemSoleimaniEntity.createMobAttributes());
 FabricDefaultAttributeRegistry.register(WINDIGO, WindigoEntity.createMobAttributes());
 FabricDefaultAttributeRegistry.register(YELLOW_WINDIGO, YellowWindigoEntity.createMobAttributes());
 FabricDefaultAttributeRegistry.register(MECH_KILLER_ROBOT_1000, MechKillerRobot1000Entity.createMobAttributes());

 
EntityAttributeListener.registerAttributes();               
 

	}
	

	


}
