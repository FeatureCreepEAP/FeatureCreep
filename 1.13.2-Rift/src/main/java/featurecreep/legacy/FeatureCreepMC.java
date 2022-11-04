package featurecreep.legacy;

import java.util.Map;

import org.dimdev.rift.listener.BlockAdder;
import org.dimdev.rift.listener.ItemAdder;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

/**
 * The example mod listener, as defined in the riftmod.json file
 * 
 * As many more as wanted can be made by adding their full names in the riftmod.json file
 * 
 * @author Reisse, Chocohead
 */

public class FeatureCreepMC {

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
	
	    
	    
	    public static final Item RAW_BACON = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item COOKED_BACON = new FCFood(10, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item BUTTER_CANDY = new FCFood(3, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item CRYSTAL_APPLE = new FCFood(10, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item LOVE_FOOD = new FCFood(15, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item POPCORN = new FCFood(3, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item BUTTER_FOOD = new FCFood(3, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item CORN_DOG = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item COOKED_CORN_DOG = new FCFood(6, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item RAW_CRAB_MEAT = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item COOKED_CRAB_MEAT = new FCFood(10, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item CHEESE = new FCFood(3, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item SALAD = new FCFood(15, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item BLT = new FCFood(13, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item CRAB_PATTY = new FCFood(13, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item MAGIC_APPLE = new FCFood(15, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item PEACH = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item RAW_PEACOCK = new FCFood(8, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item COOCKED_PEACOCK = new FCFood(13, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item BLUE_FISH = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item BUTTERED_POPCORN = new FCFood(10, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item SALTED_POPCORN = new FCFood(10, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item BUTTERED_AND_SALTED_POPCORN = new FCFood(15, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item CHERRY = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item CORN = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item POPCORN_BAG = new FCFood(15, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item QUINOA = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item RADISH = new FCFood( 5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item RICE = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item ROCK_FISH = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item FIRE_FISH = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item SPARK_FISH = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item GREEN_FISH = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item GREY_FISH = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item PINK_FISH = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item SUN_FISH = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item STRAWBERRY = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item LETTUCE = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item TOMATO = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item WOOD_FISH = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item RAW_MOOSE_MEAT = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    //public static final Item COOKED_MOOSE_MEAT = new FCFood("raw_bacon", 2, true);
	    public static final Item DEAD_BUG = new FCFood(2, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item MAGIC_FROG_OF_STRENGTH = new FCFood( 5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item MAGIC_FROG_OF_WEAKNESS = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item MAGIC_FROG_OF_SPEED = new FCFood( 5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item MAGIC_FROG_OF_SLOWNESS = new FCFood( 5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item MAGIC_FROG_OF_REGENERATION = new FCFood( 5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item MAGIC_FROG_OF_POISON = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item MAGIC_FROG_OF_MORPH = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item MAGIC_FROG_OF_CONFUSION = new FCFood(5, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item COOKED_MOOSE_MEAT = new FCFood(10, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item CANDY_CANE = new FCFood( 10, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item GOLDEN_BREAD = new FCFood( 10, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item GOLDEN_CHICKEN = new FCFood( 10, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item GOLDEN_TROPICAL_FISH = new FCFood( 10, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item GOLDEN_COD = new FCFood(10, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item GOLDEN_PORKCHOP = new FCFood( 10, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item WATERMELON_SLICE = new FCFood( 10, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item GOLDEN_MUSHROOM_STEW = new FCFood( 15, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item GOLDEN_STEAK = new FCFood(15, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item GOLDEN_COOKIE = new FCFood(10, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item GOLDEN_POTATO = new FCFood(10, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item GOLDEN_PUMPKIN_PIE = new FCFood(15, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item GOLDEN_ROTTON_FLESH = new FCFood(10, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item GOLDEN_CARROT = new FCFood(10, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item GOLDEN_PUFFERFISH = new FCFood(10, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item GOLDEN_SALMON = new FCFood(10, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item GOLDEN_CANDYCANE = new FCFood(15, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item ULTIMATE_APPLE = new FCFood(15, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item ENCHANTED_GOLDEN_CARROT = new FCFood(15, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item ENCHANTED_GOLDEN_STEAK = new FCFood(15, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item ENCHANTED_GOLDEN_COD = new FCFood(15, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item ENCHANTED_GOLDEN_COOKIE = new FCFood(15, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item ENCHANTED_GOLDEN_CANDYCANE = new FCFood(15, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item RADDISH_STEW = new FCFood(15, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));
	    public static final Item DRINKABLE_GASOLINE_PETROL = new FCFood(15, 0.85F, true, new Item.Settings().group(ItemGroup.FOOD));




	        
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
	 //   public static final Item AMETHYST_SWORD = new SwordItem(ToolMaterials.TOOL_AMETHYST, 0, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
	  //  public static final Item AMETHYST_PICKAXE = new GenericPickaxe(ToolMaterials.TOOL_AMETHYST, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
	   // public static final Item AMETHYST_SHOVEL = new ShovelItem(ToolMaterials.TOOL_AMETHYST, 0.0F, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
	   // public static final Item AMETHYST_HOE = new GenericHoe(ToolMaterials.TOOL_AMETHYST, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
	   // public static final Item AMETHYST_AXE = new GenericAxe(ToolMaterials.TOOL_AMETHYST, 0, 0.0F, (new Item.Settings()).group(ItemGroup.COMBAT));


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


	    public static final Item CZ_SLOW_BOOTS = new CZSlowArmour(ArmourMaterials.PEACOCK_FEATHER, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT));

	    public static final Block AMETHYST_ORE = new Block(Block.Settings.of(Material.METAL));
	    public static final Block RUBY_ORE = new Block(Block.Settings.of(Material.METAL));
	    public static final Block TIGERS_EYE_ORE = new Block(Block.Settings.of(Material.METAL));
	    public static final Block TITANIUM_ORE = new Block(Block.Settings.of(Material.METAL));
	    public static final Block URANIUM_ORE = new Block(Block.Settings.of(Material.METAL));
	    public static final Block ALUMINIUM_ORE = new Block(Block.Settings.of(Material.METAL));
	    public static final Block SALT_ORE = new Block(Block.Settings.of(Material.METAL));
	    public static final Block UNPROCESSED_OIL_ORE = new Block(Block.Settings.of(Material.METAL));
	    public static final Block GASOLINE_PETROL_ORE = new Block(Block.Settings.of(Material.METAL));
	    public static final Block AMETHYST_BLOCK = new Block(Block.Settings.of(Material.METAL));
	    public static final Block RUBY_BLOCK = new Block(Block.Settings.of(Material.METAL));
	    public static final Block TIGERS_EYE_BLOCK = new Block(Block.Settings.of(Material.METAL));
	    public static final Block TITANIUM_BLOCK = new Block(Block.Settings.of(Material.METAL));
	    public static final Block URANIUM_BLOCK = new Block(Block.Settings.of(Material.METAL));
	    public static final Block ALUMINIUM_BLOCK = new Block(Block.Settings.of(Material.METAL));


	    
	    
	public static void registerItems() {
		// TODO Auto-generated method stub
	//	Registry.ITEM.register(new Identifier("featurecreep:amethyst"), AMETHYST);
	//	Registry.ITEM.register(new Identifier("featurecreep:ruby"), RUBY);

/*		register(AMETHYST_BLOCK, ItemGroup.BUILDING_BLOCKS);

	
		
	//	method_73186(new Identifier("featurecreep:amethyst"), AMETHYST);
		method_73186(new Identifier("featurecreep:ruby"), RUBY);
		//Registry.ITEM.register(AMETHYST_BLOCK_ITEM, ItemGroup.BUILDING_BLOCKS);
	
	
		
		
		
		
		*/

  //   method_73186(new Identifier("featurecreep", "amethyst"), AMETHYST);
     method_73186(new Identifier("featurecreep", "ruby"), RUBY);
     method_73186(new Identifier("featurecreep", "tigers_eye"), TIGERS_EYE);
     method_73186(new Identifier("featurecreep", "titanium_ingot"), TITANIUM);
     method_73186(new Identifier("featurecreep", "titanium_nugget"), TITANIUM_NUGGET);
     method_73186(new Identifier("featurecreep", "uranium_ingot"), URANIUM);
     method_73186(new Identifier("featurecreep", "uranium_nugget"), URANIUM_NUGGET);
     method_73186(new Identifier("featurecreep", "aluminium_ingot"), ALUMINIUM);
     method_73186(new Identifier("featurecreep", "copper_ingot"), COPPER_INGOT);
     method_73186(new Identifier("featurecreep", "tin_ingot"), TIN_INGOT);
     method_73186(new Identifier("featurecreep", "silver_ingot"), SILVER_INGOT);
     method_73186(new Identifier("featurecreep", "pink_tourmaline_ingot"), PINK_TOURMALINE);
     method_73186(new Identifier("featurecreep", "pink_tourmaline_nugget"), PINK_TOURMALINE_NUGGET);
     method_73186(new Identifier("featurecreep", "steel_ingot"), STEEL_INGOT);
     method_73186(new Identifier("featurecreep", "oil"), Oil);
     method_73186(new Identifier("featurecreep", "salt"), Salt);
     method_73186(new Identifier("featurecreep", "mobzilla_scale"), MobzillaScale);
     method_73186(new Identifier("featurecreep", "queen_scale"), QueenScale);
     method_73186(new Identifier("featurecreep", "moth_scale"), MothScale);
     method_73186(new Identifier("featurecreep", "platinum_ingot"), PLATINUM_INGOT);
     method_73186(new Identifier("featurecreep", "lava_eel"), LAVA_EEL);
     method_73186(new Identifier("featurecreep", "peackock_feather"), PEACKOCK_FEATHER);
     method_73186(new Identifier("featurecreep", "sapphire"), SAPPHIRE);
     method_73186(new Identifier("featurecreep", "gasoline_petrol"), GASOLINE_PETROL);


     method_73186(new Identifier("featurecreep", "bacon"), RAW_BACON);
     method_73186(new Identifier("featurecreep", "cooked_bacon"), COOKED_BACON);
     method_73186(new Identifier("featurecreep", "butter_candy"), BUTTER_CANDY);
     method_73186(new Identifier("featurecreep", "crystal_apple"), CRYSTAL_APPLE);
     method_73186(new Identifier("featurecreep", "love_food"), LOVE_FOOD);
     method_73186(new Identifier("featurecreep", "popcorn"), POPCORN);
     method_73186(new Identifier("featurecreep", "butter_food"), BUTTER_FOOD);
     method_73186(new Identifier("featurecreep", "corndog"), CORN_DOG);
     method_73186(new Identifier("featurecreep", "cooked_corndog"), COOKED_CORN_DOG);
     method_73186(new Identifier("featurecreep", "raw_crab_meat"), RAW_CRAB_MEAT);
     method_73186(new Identifier("featurecreep", "cooked_crab_meat"), COOKED_CRAB_MEAT);
     method_73186(new Identifier("featurecreep", "cheese"), CHEESE);
     method_73186(new Identifier("featurecreep", "salad"), SALAD);
     method_73186(new Identifier("featurecreep", "blt"), BLT);
     method_73186(new Identifier("featurecreep", "crab_patty"), CRAB_PATTY);
     method_73186(new Identifier("featurecreep", "magic_apple"), MAGIC_APPLE);
     method_73186(new Identifier("featurecreep", "peach"), PEACH);
     method_73186(new Identifier("featurecreep", "raw_peacock"), RAW_PEACOCK);
     method_73186(new Identifier("featurecreep", "coocked_peacock"), COOCKED_PEACOCK);
     method_73186(new Identifier("featurecreep", "blue_fish"), BLUE_FISH);
     method_73186(new Identifier("featurecreep", "buttered_popcorn"), BUTTERED_POPCORN);
     method_73186(new Identifier("featurecreep", "salted_popcorn"), SALTED_POPCORN);
     method_73186(new Identifier("featurecreep", "buttered_and_salted_popcorn"), BUTTERED_AND_SALTED_POPCORN);
     method_73186(new Identifier("featurecreep", "cherry"), CHERRY);
     method_73186(new Identifier("featurecreep", "corn"), CORN);
     method_73186(new Identifier("featurecreep", "popcorn_bag"), POPCORN_BAG);
     method_73186(new Identifier("featurecreep", "quinoa"), QUINOA);
     method_73186(new Identifier("featurecreep", "radish"), RADISH);
     method_73186(new Identifier("featurecreep", "rice"), RICE);
     method_73186(new Identifier("featurecreep", "rock_fish"), ROCK_FISH);
     method_73186(new Identifier("featurecreep", "fire_fish"), FIRE_FISH);
     method_73186(new Identifier("featurecreep", "spark_fish"), SPARK_FISH);
     method_73186(new Identifier("featurecreep", "green_fish"), GREEN_FISH);
     method_73186(new Identifier("featurecreep", "grey_fish"), GREY_FISH);
     method_73186(new Identifier("featurecreep", "pink_fish"), PINK_FISH);
     method_73186(new Identifier("featurecreep", "sun_fish"), SUN_FISH);
     method_73186(new Identifier("featurecreep", "strawberry"), STRAWBERRY);
     method_73186(new Identifier("featurecreep", "lettuce"), LETTUCE);
     method_73186(new Identifier("featurecreep", "tomato"), TOMATO);
     method_73186(new Identifier("featurecreep", "wood_fish"), WOOD_FISH);
     method_73186(new Identifier("featurecreep", "raw_moose_meat"), RAW_MOOSE_MEAT);
     method_73186(new Identifier("featurecreep", "dead_bug"), DEAD_BUG);
     method_73186(new Identifier("featurecreep", "magic_frog_of_strength"), MAGIC_FROG_OF_STRENGTH);
     method_73186(new Identifier("featurecreep", "magic_frog_of_weakness"), MAGIC_FROG_OF_WEAKNESS);
     method_73186(new Identifier("featurecreep", "magic_frog_of_speed"), MAGIC_FROG_OF_SPEED);
     method_73186(new Identifier("featurecreep", "magic_frog_of_slowness"), MAGIC_FROG_OF_SLOWNESS);
     method_73186(new Identifier("featurecreep", "magic_frog_of_regeneration"), MAGIC_FROG_OF_REGENERATION);
     method_73186(new Identifier("featurecreep", "magic_frog_of_poison"), MAGIC_FROG_OF_POISON);
     method_73186(new Identifier("featurecreep", "magic_frog_of_morph"), MAGIC_FROG_OF_MORPH);
     method_73186(new Identifier("featurecreep", "magic_frog_of_confusion"), MAGIC_FROG_OF_CONFUSION);
     method_73186(new Identifier("featurecreep", "cooked_moose_meat"), COOKED_MOOSE_MEAT);
     method_73186(new Identifier("featurecreep", "candy_cane"), CANDY_CANE);
     method_73186(new Identifier("featurecreep", "golden_bread"), GOLDEN_BREAD);
     method_73186(new Identifier("featurecreep", "golden_chicken"), GOLDEN_CHICKEN);
     method_73186(new Identifier("featurecreep", "golden_tropical_fish"), GOLDEN_TROPICAL_FISH);
     method_73186(new Identifier("featurecreep", "golden_cod"), GOLDEN_COD);
     method_73186(new Identifier("featurecreep", "golden_porkchop"), GOLDEN_PORKCHOP);
     method_73186(new Identifier("featurecreep", "watermelon_slice"), WATERMELON_SLICE);
     method_73186(new Identifier("featurecreep", "golden_mushroom_stew"), GOLDEN_MUSHROOM_STEW);
     method_73186(new Identifier("featurecreep", "golden_steak"), GOLDEN_STEAK);
     method_73186(new Identifier("featurecreep", "golden_cookie"), GOLDEN_COOKIE);
     method_73186(new Identifier("featurecreep", "golden_potato"), GOLDEN_POTATO);
     method_73186(new Identifier("featurecreep", "golden_pumpkin_pie"), GOLDEN_PUMPKIN_PIE);
     method_73186(new Identifier("featurecreep", "golden_rotton_flesh"), GOLDEN_ROTTON_FLESH);
     method_73186(new Identifier("featurecreep", "golden_carrot"), GOLDEN_CARROT);
     method_73186(new Identifier("featurecreep", "golden_pufferfish"), GOLDEN_PUFFERFISH);
     method_73186(new Identifier("featurecreep", "golden_salmon"), GOLDEN_SALMON);
     method_73186(new Identifier("featurecreep", "golden_candycane"), GOLDEN_CANDYCANE);
     method_73186(new Identifier("featurecreep", "ultimate_apple"), ULTIMATE_APPLE);
     method_73186(new Identifier("featurecreep", "enchanted_golden_carrot"), ENCHANTED_GOLDEN_CARROT);
     method_73186(new Identifier("featurecreep", "enchanted_golden_steak"), ENCHANTED_GOLDEN_STEAK);
     method_73186(new Identifier("featurecreep", "enchanted_golden_cod"), ENCHANTED_GOLDEN_COD);
     method_73186(new Identifier("featurecreep", "enchanted_golden_cookie"), ENCHANTED_GOLDEN_COOKIE);
     method_73186(new Identifier("featurecreep", "enchanted_golden_candycane"), ENCHANTED_GOLDEN_CANDYCANE);
     method_73186(new Identifier("featurecreep", "raddish_stew"), RADDISH_STEW);
     method_73186(new Identifier("featurecreep", "drinkable_gasoline_petrol"), DRINKABLE_GASOLINE_PETROL);

           
     method_73186(new Identifier("featurecreep", "emerald_helmet"), EMERALD_HELMET);
     method_73186(new Identifier("featurecreep", "emerald_chestplate"), EMERALD_CHESTPLATE);
     method_73186(new Identifier("featurecreep", "emerald_leggings"), EMERALD_LEGGINGS);
     method_73186(new Identifier("featurecreep", "emerald_boots"), EMERALD_BOOTS);
     method_73186(new Identifier("featurecreep", "emerald_sword"), EMERALD_SWORD);
     method_73186(new Identifier("featurecreep", "emerald_pickaxe"), EMERALD_PICKAXE);
     method_73186(new Identifier("featurecreep", "emerald_shovel"), EMERALD_SHOVEL);
     method_73186(new Identifier("featurecreep", "emerald_hoe"), EMERALD_HOE);
     method_73186(new Identifier("featurecreep", "emerald_axe"), EMERALD_AXE);
     method_73186(new Identifier("featurecreep", "ruby_helmet"), RUBY_HELMET);
     method_73186(new Identifier("featurecreep", "ruby_chestplate"), RUBY_CHESTPLATE);
     method_73186(new Identifier("featurecreep", "ruby_leggings"), RUBY_LEGGINGS);
     method_73186(new Identifier("featurecreep", "ruby_boots"), RUBY_BOOTS);
     method_73186(new Identifier("featurecreep", "ruby_sword"), RUBY_SWORD);
     method_73186(new Identifier("featurecreep", "ruby_pickaxe"), RUBY_PICKAXE);
     method_73186(new Identifier("featurecreep", "ruby_shovel"), RUBY_SHOVEL);
     method_73186(new Identifier("featurecreep", "ruby_hoe"), RUBY_HOE);
     method_73186(new Identifier("featurecreep", "ruby_axe"), RUBY_AXE);
     method_73186(new Identifier("featurecreep", "experience_helmet"), EXPERIENCE_HELMET);
     method_73186(new Identifier("featurecreep", "experience_chestplate"), EXPERIENCE_CHESTPLATE);
     method_73186(new Identifier("featurecreep", "experience_leggings"), EXPERIENCE_LEGGINGS);
     method_73186(new Identifier("featurecreep", "experience_boots"), EXPERIENCE_BOOTS);
     //method_73186(new Identifier("featurecreep", "amethyst_helmet"), AMETHYST_HELMET);
     //method_73186(new Identifier("featurecreep", "amethyst_chestplate"), AMETHYST_CHESTPLATE);
     //method_73186(new Identifier("featurecreep", "amethyst_leggings"), AMETHYST_LEGGINGS);
     //method_73186(new Identifier("featurecreep", "amethyst_boots"), AMETHYST_BOOTS);
   //  method_73186(new Identifier("featurecreep", "amethyst_sword"), AMETHYST_SWORD);
   //  method_73186(new Identifier("featurecreep", "amethyst_pickaxe"), AMETHYST_PICKAXE);
    // method_73186(new Identifier("featurecreep", "amethyst_shovel"), AMETHYST_SHOVEL);
   //  method_73186(new Identifier("featurecreep", "amethyst_hoe"), AMETHYST_HOE);
  //   method_73186(new Identifier("featurecreep", "amethyst_axe"), AMETHYST_AXE);
     method_73186(new Identifier("featurecreep", "sapphire_helmet"), SAPPHIRE_HELMET);
     method_73186(new Identifier("featurecreep", "sapphire_chestplate"), SAPPHIRE_CHESTPLATE);
     method_73186(new Identifier("featurecreep", "sapphire_leggings"), SAPPHIRE_LEGGINGS);
     method_73186(new Identifier("featurecreep", "sapphire_boots"), SAPPHIRE_BOOTS);
     method_73186(new Identifier("featurecreep", "sapphire_sword"), SAPPHIRE_SWORD);
     method_73186(new Identifier("featurecreep", "sapphire_pickaxe"), SAPPHIRE_PICKAXE);
     method_73186(new Identifier("featurecreep", "sapphire_shovel"), SAPPHIRE_SHOVEL);
     method_73186(new Identifier("featurecreep", "sapphire_hoe"), SAPPHIRE_HOE);
     method_73186(new Identifier("featurecreep", "sapphire_axe"), SAPPHIRE_AXE);
     method_73186(new Identifier("featurecreep", "tigers_eye_helmet"), TIGERS_EYE_HELMET);
     method_73186(new Identifier("featurecreep", "tigers_eye_chestplate"), TIGERS_EYE_CHESTPLATE);
     method_73186(new Identifier("featurecreep", "tigers_eye_leggings"), TIGERS_EYE_LEGGINGS);
     method_73186(new Identifier("featurecreep", "tigers_eye_boots"), TIGERS_EYE_BOOTS);
     method_73186(new Identifier("featurecreep", "tigers_eye_sword"), TIGERS_EYE_SWORD);
     method_73186(new Identifier("featurecreep", "tigers_eye_pickaxe"), TIGERS_EYE_PICKAXE);
     method_73186(new Identifier("featurecreep", "tigers_eye_shovel"), TIGERS_EYE_SHOVEL);
     method_73186(new Identifier("featurecreep", "tigers_eye_hoe"), TIGERS_EYE_HOE);
     method_73186(new Identifier("featurecreep", "tigers_eye_axe"), TIGERS_EYE_AXE);
     method_73186(new Identifier("featurecreep", "copper_helmet"), COPPER_HELMET);
     method_73186(new Identifier("featurecreep", "copper_chestplate"), COPPER_CHESTPLATE);
     method_73186(new Identifier("featurecreep", "copper_leggings"), COPPER_LEGGINGS);
     method_73186(new Identifier("featurecreep", "copper_boots"), COPPER_BOOTS);
     method_73186(new Identifier("featurecreep", "platinum_helmet"), PLATINUM_HELMET);
     method_73186(new Identifier("featurecreep", "platinum_chestplate"), PLATINUM_CHESTPLATE);
     method_73186(new Identifier("featurecreep", "platinum_leggings"), PLATINUM_LEGGINGS);
     method_73186(new Identifier("featurecreep", "platinum_boots"), PLATINUM_BOOTS);
     method_73186(new Identifier("featurecreep", "silver_helmet"), SILVER_HELMET);
     method_73186(new Identifier("featurecreep", "silver_chestplate"), SILVER_CHESTPLATE);
     method_73186(new Identifier("featurecreep", "silver_leggings"), SILVER_LEGGINGS);
     method_73186(new Identifier("featurecreep", "silver_boots"), SILVER_BOOTS);
     method_73186(new Identifier("featurecreep", "aluminium_helmet"), ALUMINIUM_HELMET);
     method_73186(new Identifier("featurecreep", "aluminium_chestplate"), ALUMINIUM_CHESTPLATE);
     method_73186(new Identifier("featurecreep", "aluminium_leggings"), ALUMINIUM_LEGGINGS);
     method_73186(new Identifier("featurecreep", "aluminium_boots"), ALUMINIUM_BOOTS);
     method_73186(new Identifier("featurecreep", "tin_helmet"), TIN_HELMET);
     method_73186(new Identifier("featurecreep", "tin_chestplate"), TIN_CHESTPLATE);
     method_73186(new Identifier("featurecreep", "tin_leggings"), TIN_LEGGINGS);
     method_73186(new Identifier("featurecreep", "tin_boots"), TIN_BOOTS);
     method_73186(new Identifier("featurecreep", "pink_tourmaline_helmet"), PINK_TOURMALINE_HELMET);
     method_73186(new Identifier("featurecreep", "pink_tourmaline_chestplate"), PINK_TOURMALINE_CHESTPLATE);
     method_73186(new Identifier("featurecreep", "pink_tourmaline_leggings"), PINK_TOURMALINE_LEGGINGS);
     method_73186(new Identifier("featurecreep", "pink_tourmaline_boots"), PINK_TOURMALINE_BOOTS);
     method_73186(new Identifier("featurecreep", "optimised_pickaxe"), OPTIMISED_PICKAXE);
     method_73186(new Identifier("featurecreep", "optimised_shovel"), OPTIMISED_SHOVEL);
     method_73186(new Identifier("featurecreep", "ultimate_helmet"), ULTIMATE_HELMET);
     method_73186(new Identifier("featurecreep", "ultimate_chestplate"), ULTIMATE_CHESTPLATE);
     method_73186(new Identifier("featurecreep", "ultimate_leggings"), ULTIMATE_LEGGINGS);
     method_73186(new Identifier("featurecreep", "ultimate_boots"), ULTIMATE_BOOTS);
     method_73186(new Identifier("featurecreep", "ultimate_sword"), ULTIMATE_SWORD);
     method_73186(new Identifier("featurecreep", "ultimate_pickaxe"), ULTIMATE_PICKAXE);
     method_73186(new Identifier("featurecreep", "ultimate_shovel"), ULTIMATE_SHOVEL);
     method_73186(new Identifier("featurecreep", "ultimate_hoe"), ULTIMATE_HOE);
     method_73186(new Identifier("featurecreep", "ultimate_axe"), ULTIMATE_AXE);
     method_73186(new Identifier("featurecreep", "royal_guardian_helmet"), ROYAL_GUARDIAN_HELMET);
     method_73186(new Identifier("featurecreep", "royal_guardian_chestplate"), ROYAL_GUARDIAN_CHESTPLATE);
     method_73186(new Identifier("featurecreep", "royal_guardian_leggings"), ROYAL_GUARDIAN_LEGGINGS);
     method_73186(new Identifier("featurecreep", "royal_guardian_boots"), ROYAL_GUARDIAN_BOOTS);
     method_73186(new Identifier("featurecreep", "queen_scale_helmet"), QUEEN_SCALE_HELMET);
     method_73186(new Identifier("featurecreep", "queen_scale_chestplate"), QUEEN_SCALE_CHESTPLATE);
     method_73186(new Identifier("featurecreep", "queen_scale_leggings"), QUEEN_SCALE_LEGGINGS);
     method_73186(new Identifier("featurecreep", "queen_scale_boots"), QUEEN_SCALE_BOOTS);
     method_73186(new Identifier("featurecreep", "mobzilla_scale_helmet"), MOBZILLA_SCALE_HELMET);
     method_73186(new Identifier("featurecreep", "mobzilla_scale_chestplate"), MOBZILLA_SCALE_CHESTPLATE);
     method_73186(new Identifier("featurecreep", "mobzilla_scale_leggings"), MOBZILLA_SCALE_LEGGINGS);
     method_73186(new Identifier("featurecreep", "mobzilla_scale_boots"), MOBZILLA_SCALE_BOOTS);
     method_73186(new Identifier("featurecreep", "moth_scale_helmet"), MOTH_SCALE_HELMET);
     method_73186(new Identifier("featurecreep", "moth_scale_chestplate"), MOTH_SCALE_CHESTPLATE);
     method_73186(new Identifier("featurecreep", "moth_scale_leggings"), MOTH_SCALE_LEGGINGS);
     method_73186(new Identifier("featurecreep", "moth_scale_boots"), MOTH_SCALE_BOOTS);
     method_73186(new Identifier("featurecreep", "lapis_block_helmet"), LAPIS_BLOCK_HELMET);
     method_73186(new Identifier("featurecreep", "lapis_block_chestplate"), LAPIS_BLOCK_CHESTPLATE);
     method_73186(new Identifier("featurecreep", "lapis_block_leggings"), LAPIS_BLOCK_LEGGINGS);
     method_73186(new Identifier("featurecreep", "lapis_block_boots"), LAPIS_BLOCK_BOOTS);
     method_73186(new Identifier("featurecreep", "lava_eel_helmet"), LAVA_EEL_HELMET);
     method_73186(new Identifier("featurecreep", "lava_eel_chestplate"), LAVA_EEL_CHESTPLATE);
     method_73186(new Identifier("featurecreep", "lava_eel_leggings"), LAVA_EEL_LEGGINGS);
     method_73186(new Identifier("featurecreep", "lava_eel_boots"), LAVA_EEL_BOOTS);
     method_73186(new Identifier("featurecreep", "peacock_feather_helmet"), PEACOCK_FEATHER_HELMET);
     method_73186(new Identifier("featurecreep", "peacock_feather_chestplate"), PEACOCK_FEATHER_CHESTPLATE);
     method_73186(new Identifier("featurecreep", "peacock_feather_leggings"), PEACOCK_FEATHER_LEGGINGS);
     method_73186(new Identifier("featurecreep", "peacock_feather_boots"), PEACOCK_FEATHER_BOOTS);
     method_73186(new Identifier("featurecreep", "cz_slow_boots"), CZ_SLOW_BOOTS);
            
     method_73186(new Identifier("featurecreep", "miners_dream"), MINERS_DREAM);
     method_73186(new Identifier("featurecreep", "large_miners_dream"), LARGE_MINERS_DREAM);

       
        
     method_73186(new Identifier("featurecreep:amethyst_ore"), new BlockItem(AMETHYST_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
     method_73186(new Identifier("featurecreep:ruby_ore"), new BlockItem(RUBY_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
     method_73186(new Identifier("featurecreep:tigers_eye_ore"), new BlockItem(TIGERS_EYE_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
     method_73186(new Identifier("featurecreep:titanium_ore"), new BlockItem(TITANIUM_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
     method_73186(new Identifier("featurecreep:uranium_ore"), new BlockItem(URANIUM_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
     method_73186(new Identifier("featurecreep:aluminium_ore"), new BlockItem(ALUMINIUM_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
     method_73186(new Identifier("featurecreep:salt_ore"), new BlockItem(SALT_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
     method_73186(new Identifier("featurecreep:unprocessed_oil_ore"), new BlockItem(UNPROCESSED_OIL_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
     method_73186(new Identifier("featurecreep:gasoline_petrol_ore"), new BlockItem(GASOLINE_PETROL_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
     method_73186(new Identifier("featurecreep:ruby_block"), new BlockItem(RUBY_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
     method_73186(new Identifier("featurecreep:amethyst_block"), new BlockItem(AMETHYST_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
     method_73186(new Identifier("featurecreep:tigers_eye_block"), new BlockItem(TIGERS_EYE_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
     method_73186(new Identifier("featurecreep:titanium_block"), new BlockItem(TITANIUM_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
     method_73186(new Identifier("featurecreep:uranium_block"), new BlockItem(URANIUM_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
     method_73186(new Identifier("featurecreep:aluminium_block"), new BlockItem(ALUMINIUM_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

     
        //Thanks to Charcoal Block mod (Tunacan) Fabric for this
  //      FuelRegistry.INSTANCE.add((ItemConvertible)Oil, Integer.valueOf(40));
  //      FuelRegistry.INSTANCE.add((ItemConvertible)GASOLINE_PETROL, Integer.valueOf(40));


		
	
		
		
		
		
		
		
	}

	
	

	
	
	
    private static void addFuel(Map<Item, Integer> fuelTimes, Tag<Item> tag, int fuelTime) {
        for (Item item : tag.values()) {
            fuelTimes.put(item, fuelTime);
        }
    }
	
	
	public static void registerBlocks() {
		// TODO Auto-generated method stub
     //   Block.register(new ModelIdentifier("amethyst_block"), AMETHYST_BLOCK);
        Registry.Registry.register(new ModelIdentifier("featurecreep:amethyst_block"), AMETHYST_BLOCK);
	
        
        
        Registry.Registry.register(new ModelIdentifier("featurecreep:amethyst_ore"), AMETHYST_ORE);

        Registry.Registry.register(new ModelIdentifier("featurecreep:ruby_ore"), RUBY_ORE);

        Registry.Registry.register(new ModelIdentifier("featurecreep:tigers_eye_ore"), TIGERS_EYE_ORE);

        Registry.Registry.register(new ModelIdentifier("featurecreep:titanium_ore"), TITANIUM_ORE);

        Registry.Registry.register(new ModelIdentifier("featurecreep:uranium_ore"), URANIUM_ORE);

        Registry.Registry.register(new ModelIdentifier("featurecreep:aluminium_ore"), ALUMINIUM_ORE);
    
        Registry.Registry.register(new ModelIdentifier("featurecreep:salt_ore"), SALT_ORE);
    
        Registry.Registry.register(new ModelIdentifier("featurecreep:unprocessed_oil_ore"), UNPROCESSED_OIL_ORE);
    
        Registry.Registry.register(new ModelIdentifier("featurecreep:gasoline_petrol_ore"), GASOLINE_PETROL_ORE);
    
        Registry.Registry.register(new ModelIdentifier("featurecreep:ruby_block"), RUBY_BLOCK);
    
        
        //Registry.Registry.register(new ModelIdentifier("featurecreep:amethyst_block"), AMETHYST_BLOCK);

        Registry.Registry.register(new ModelIdentifier("featurecreep:tigers_eye_block"), TIGERS_EYE_BLOCK);
    
        Registry.Registry.register(new ModelIdentifier("featurecreep:titanium_block"), TITANIUM_BLOCK);
    
        Registry.Registry.register(new ModelIdentifier("featurecreep:uranium_block"), URANIUM_BLOCK);
    
        Registry.Registry.register(new ModelIdentifier("featurecreep:aluminium_block"), ALUMINIUM_BLOCK);
    

	
	
	}

	



	 public static void register(Block blockIn, ItemGroup group) {
	        registerItemBlock(new BlockItem(blockIn, new Settings().group(group)));
	    }

	    public static void registerItemBlock(BlockItem item) {
	        register(item.getBlock(), (Item)item);
	    }

	    public static void register(Block block, Item item) {
	        method_73186(Registry.Registry.getId(block), item);
	    }

	    public static void register(String name, Item item) {
	        method_73186(new Identifier(name), item);
	    }

	    public static void method_73186(Identifier identifier, Item item) {
	        if (item instanceof BlockItem) {
	            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
	        }
	        Registry.ITEM.register(identifier, item);
	    }
































//public class ExampleListener implements MinecraftStartListener, BootstrapListener {
//frtg	private static final Logger LOGGER = LogManager.getLogger();

	//@Override
//	public void onMinecraftStart() {
		//Minecraft has started but hasn't registered any blocks or items
		//Prime time for loading a config if you need one
	//	LOGGER.info("Minecraft starting");

//	}

	//Blocks can be added by implementing BlockAdder, Items from ItemAdder etc.
	//See open sourced mods such as HalfLogs for reference


	
	
	//@Override
//public void afterVanillaBootstrap() {
		//Minecraft has now finished Bootstrap so all blocks and items are registered
		//You probably won't need to listen to this normally.
	//	LOGGER.info("Minecraft loaded: " + Items);
//	}

}
