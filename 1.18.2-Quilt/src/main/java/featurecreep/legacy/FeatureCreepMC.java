package featurecreep.legacy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

import org.quiltmc.loader.api.ModContainer;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

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
public static final Item CORN = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_CORN));
public static final Item POPCORN_BAG = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_POPCORN_BAG));
public static final Item QUINOA = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_QUINOA));
public static final Item RADISH = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_RADISH));
public static final Item RICE = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_RICE));
public static final Item ROCK_FISH = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_ROCK_FISH));
public static final Item FIRE_FISH = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_FIRE_FISH));
public static final Item SPARK_FISH = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_SPARK_FISH));
public static final Item GREEN_FISH = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GREEN_FISH));
public static final Item GREY_FISH = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GREY_FISH));
public static final Item PINK_FISH = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_PINK_FISH));
public static final Item SUN_FISH = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_SUN_FISH));
public static final Item STRAWBERRY = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_STRAWBERRY));
public static final Item LETTUCE = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_LETTUCE));
public static final Item TOMATO = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_TOMATO));
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

public static final Item EXPERIENCE_HELMET = new ArmorItem(ArmourMaterials.EXPERIENCE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EXPERIENCE_CHESTPLATE = new ArmorItem(ArmourMaterials.EXPERIENCE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EXPERIENCE_LEGGINGS = new ArmorItem(ArmourMaterials.EXPERIENCE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item EXPERIENCE_BOOTS = new ArmorItem(ArmourMaterials.EXPERIENCE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));

 
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
 
    
public static final Item COPPER_HELMET = new ArmorItem(ArmourMaterials.COPPER, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COPPER_CHESTPLATE = new ArmorItem(ArmourMaterials.COPPER, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COPPER_LEGGINGS = new ArmorItem(ArmourMaterials.COPPER, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item COPPER_BOOTS = new ArmorItem(ArmourMaterials.COPPER, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Item PLATINUM_HELMET = new ArmorItem(ArmourMaterials.PLATINUM, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PLATINUM_CHESTPLATE = new ArmorItem(ArmourMaterials.PLATINUM, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PLATINUM_LEGGINGS = new ArmorItem(ArmourMaterials.PLATINUM, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PLATINUM_BOOTS = new ArmorItem(ArmourMaterials.PLATINUM, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Item SILVER_HELMET = new ArmorItem(ArmourMaterials.SILVER, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SILVER_CHESTPLATE = new ArmorItem(ArmourMaterials.SILVER, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SILVER_LEGGINGS = new ArmorItem(ArmourMaterials.SILVER, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item SILVER_BOOTS = new ArmorItem(ArmourMaterials.SILVER, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Item ALUMINIUM_HELMET = new ArmorItem(ArmourMaterials.ALUMINIUM, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ALUMINIUM_CHESTPLATE = new ArmorItem(ArmourMaterials.ALUMINIUM, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ALUMINIUM_LEGGINGS = new ArmorItem(ArmourMaterials.ALUMINIUM, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ALUMINIUM_BOOTS = new ArmorItem(ArmourMaterials.ALUMINIUM, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item TIN_HELMET = new ArmorItem(ArmourMaterials.TIN, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TIN_CHESTPLATE = new ArmorItem(ArmourMaterials.TIN, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TIN_LEGGINGS = new ArmorItem(ArmourMaterials.TIN, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item TIN_BOOTS = new ArmorItem(ArmourMaterials.TIN, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Item PINK_TOURMALINE_HELMET = new ArmorItem(ArmourMaterials.PINK_TOURMALINE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PINK_TOURMALINE_CHESTPLATE = new ArmorItem(ArmourMaterials.PINK_TOURMALINE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PINK_TOURMALINE_LEGGINGS = new ArmorItem(ArmourMaterials.PINK_TOURMALINE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PINK_TOURMALINE_BOOTS = new ArmorItem(ArmourMaterials.PINK_TOURMALINE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Item OPTIMISED_PICKAXE = new EnchantedPickaxe(ToolMaterials.TOOL_OPTIMISED, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item OPTIMISED_SHOVEL = new EnchantedShovel(ToolMaterials.TOOL_OPTIMISED, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item ULTIMATE_HELMET = new ArmorItem(ArmourMaterials.ULTIMATE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ULTIMATE_CHESTPLATE = new ArmorItem(ArmourMaterials.ULTIMATE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ULTIMATE_LEGGINGS = new ArmorItem(ArmourMaterials.ULTIMATE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ULTIMATE_BOOTS = new ArmorItem(ArmourMaterials.ULTIMATE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ULTIMATE_SWORD = new SwordItem(ToolMaterials.TOOL_ULTIMATE, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ULTIMATE_PICKAXE = new GenericPickaxe(ToolMaterials.TOOL_ULTIMATE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ULTIMATE_SHOVEL = new ShovelItem(ToolMaterials.TOOL_ULTIMATE, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ULTIMATE_HOE = new GenericHoe(ToolMaterials.TOOL_ULTIMATE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ULTIMATE_AXE = new GenericAxe(ToolMaterials.TOOL_ULTIMATE, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Item ROYAL_GUARDIAN_HELMET = new ArmorItem(ArmourMaterials.ROYAL_GUARDIAN, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ROYAL_GUARDIAN_CHESTPLATE = new ArmorItem(ArmourMaterials.ROYAL_GUARDIAN, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ROYAL_GUARDIAN_LEGGINGS = new ArmorItem(ArmourMaterials.ROYAL_GUARDIAN, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item ROYAL_GUARDIAN_BOOTS = new ArmorItem(ArmourMaterials.ROYAL_GUARDIAN, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Item QUEEN_SCALE_HELMET = new ArmorItem(ArmourMaterials.QUEEN_SCALE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUEEN_SCALE_CHESTPLATE = new ArmorItem(ArmourMaterials.QUEEN_SCALE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUEEN_SCALE_LEGGINGS = new ArmorItem(ArmourMaterials.QUEEN_SCALE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item QUEEN_SCALE_BOOTS = new ArmorItem(ArmourMaterials.QUEEN_SCALE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item MOBZILLA_SCALE_HELMET = new ArmorItem(ArmourMaterials.MOBZILLA_SCALE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item MOBZILLA_SCALE_CHESTPLATE = new ArmorItem(ArmourMaterials.MOBZILLA_SCALE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item MOBZILLA_SCALE_LEGGINGS = new ArmorItem(ArmourMaterials.MOBZILLA_SCALE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item MOBZILLA_SCALE_BOOTS = new ArmorItem(ArmourMaterials.MOBZILLA_SCALE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Item MOTH_SCALE_HELMET = new ArmorItem(ArmourMaterials.MOTH_SCALE, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item MOTH_SCALE_CHESTPLATE = new ArmorItem(ArmourMaterials.MOTH_SCALE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item MOTH_SCALE_LEGGINGS = new ArmorItem(ArmourMaterials.MOTH_SCALE, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item MOTH_SCALE_BOOTS = new ArmorItem(ArmourMaterials.MOTH_SCALE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Item LAPIS_BLOCK_HELMET = new ArmorItem(ArmourMaterials.LAPIS_BLOCK, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAPIS_BLOCK_CHESTPLATE = new ArmorItem(ArmourMaterials.LAPIS_BLOCK, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAPIS_BLOCK_LEGGINGS = new ArmorItem(ArmourMaterials.LAPIS_BLOCK, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAPIS_BLOCK_BOOTS = new ArmorItem(ArmourMaterials.LAPIS_BLOCK, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item LAVA_EEL_HELMET = new ArmorItem(ArmourMaterials.LAVA_EEL, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAVA_EEL_CHESTPLATE = new ArmorItem(ArmourMaterials.LAVA_EEL, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAVA_EEL_LEGGINGS = new ArmorItem(ArmourMaterials.LAVA_EEL, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item LAVA_EEL_BOOTS = new ArmorItem(ArmourMaterials.LAVA_EEL, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item PEACOCK_FEATHER_HELMET = new ArmorItem(ArmourMaterials.PEACOCK_FEATHER, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PEACOCK_FEATHER_CHESTPLATE = new ArmorItem(ArmourMaterials.PEACOCK_FEATHER, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PEACOCK_FEATHER_LEGGINGS = new ArmorItem(ArmourMaterials.PEACOCK_FEATHER, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT));
public static final Item PEACOCK_FEATHER_BOOTS = new ArmorItem(ArmourMaterials.PEACOCK_FEATHER, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));


public static final Item CZ_SLOW_BOOTS = new CZSlowArmour(ArmourMaterials.CZ_SLOW, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));

public static final Block AMETHYST_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block RUBY_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block TIGERS_EYE_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block TITANIUM_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block URANIUM_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block ALUMINIUM_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block SALT_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block UNPROCESSED_OIL_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block GASOLINE_PETROL_ORE = new BasicOre(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block AMETHYST_BLOCK = new Block(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block RUBY_BLOCK = new Block(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block TIGERS_EYE_BLOCK = new Block(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block TITANIUM_BLOCK = new Block(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block URANIUM_BLOCK = new Block(Block.Settings.of(Material.METAL).strength(4.0f));
public static final Block ALUMINIUM_BLOCK = new Block(Block.Settings.of(Material.METAL).strength(4.0f));



	public static void onInitialize(ModContainer mod) {
		// TODO Auto-generated method stub
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
	
	
	
      //  Registry.register(Registry.ITEM, new Identifier("featurecreep", "amethyst"), AMETHYST);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "ruby"), RUBY);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "tigers_eye"), TIGERS_EYE);
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
 //       Registry.register(Registry.ITEM, new Identifier("featurecreep", "amethyst_helmet"), AMETHYST_HELMET);
  //      Registry.register(Registry.ITEM, new Identifier("featurecreep", "amethyst_chestplate"), AMETHYST_CHESTPLATE);
   //     Registry.register(Registry.ITEM, new Identifier("featurecreep", "amethyst_leggings"), AMETHYST_LEGGINGS);
     //   Registry.register(Registry.ITEM, new Identifier("featurecreep", "amethyst_boots"), AMETHYST_BOOTS);
        //Registry.register(Registry.ITEM, new Identifier("featurecreep", "amethyst_sword"), AMETHYST_SWORD);
        //Registry.register(Registry.ITEM, new Identifier("featurecreep", "amethyst_pickaxe"), AMETHYST_PICKAXE);
        //Registry.register(Registry.ITEM, new Identifier("featurecreep", "amethyst_shovel"), AMETHYST_SHOVEL);
        //Registry.register(Registry.ITEM, new Identifier("featurecreep", "amethyst_hoe"), AMETHYST_HOE);
        //Registry.register(Registry.ITEM, new Identifier("featurecreep", "amethyst_axe"), AMETHYST_AXE);
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
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "copper_helmet"), COPPER_HELMET);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "copper_chestplate"), COPPER_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "copper_leggings"), COPPER_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "copper_boots"), COPPER_BOOTS);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "platinum_helmet"), PLATINUM_HELMET);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "platinum_chestplate"), PLATINUM_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "platinum_leggings"), PLATINUM_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "platinum_boots"), PLATINUM_BOOTS);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "silver_helmet"), SILVER_HELMET);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "silver_chestplate"), SILVER_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "silver_leggings"), SILVER_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "silver_boots"), SILVER_BOOTS);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "aluminium_helmet"), ALUMINIUM_HELMET);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "aluminium_chestplate"), ALUMINIUM_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "aluminium_leggings"), ALUMINIUM_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "aluminium_boots"), ALUMINIUM_BOOTS);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "tin_helmet"), TIN_HELMET);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "tin_chestplate"), TIN_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "tin_leggings"), TIN_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "tin_boots"), TIN_BOOTS);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "pink_tourmaline_helmet"), PINK_TOURMALINE_HELMET);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "pink_tourmaline_chestplate"), PINK_TOURMALINE_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "pink_tourmaline_leggings"), PINK_TOURMALINE_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "pink_tourmaline_boots"), PINK_TOURMALINE_BOOTS);
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
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "queen_scale_helmet"), QUEEN_SCALE_HELMET);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "queen_scale_chestplate"), QUEEN_SCALE_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "queen_scale_leggings"), QUEEN_SCALE_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "queen_scale_boots"), QUEEN_SCALE_BOOTS);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "mobzilla_scale_helmet"), MOBZILLA_SCALE_HELMET);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "mobzilla_scale_chestplate"), MOBZILLA_SCALE_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "mobzilla_scale_leggings"), MOBZILLA_SCALE_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "mobzilla_scale_boots"), MOBZILLA_SCALE_BOOTS);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "moth_scale_helmet"), MOTH_SCALE_HELMET);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "moth_scale_chestplate"), MOTH_SCALE_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "moth_scale_leggings"), MOTH_SCALE_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "moth_scale_boots"), MOTH_SCALE_BOOTS);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "lapis_block_helmet"), LAPIS_BLOCK_HELMET);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "lapis_block_chestplate"), LAPIS_BLOCK_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "lapis_block_leggings"), LAPIS_BLOCK_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "lapis_block_boots"), LAPIS_BLOCK_BOOTS);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "lava_eel_helmet"), LAVA_EEL_HELMET);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "lava_eel_chestplate"), LAVA_EEL_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "lava_eel_leggings"), LAVA_EEL_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "lava_eel_boots"), LAVA_EEL_BOOTS);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "peacock_feather_helmet"), PEACOCK_FEATHER_HELMET);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "peacock_feather_chestplate"), PEACOCK_FEATHER_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "peacock_feather_leggings"), PEACOCK_FEATHER_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "peacock_feather_boots"), PEACOCK_FEATHER_BOOTS);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "cz_slow_boots"), CZ_SLOW_BOOTS);
            
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "miners_dream"), MINERS_DREAM);
        Registry.register(Registry.ITEM, new Identifier("featurecreep", "large_miners_dream"), LARGE_MINERS_DREAM);

        
        
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
    
        
	}
	




}
