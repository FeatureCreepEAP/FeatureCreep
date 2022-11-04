package featurecreep.legacy;

import java.io.File;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;
import com.mumfrey.liteloader.Configurable;
import com.mumfrey.liteloader.Tickable;
import com.mumfrey.liteloader.modconfig.ConfigPanel;
import com.mumfrey.liteloader.modconfig.ConfigStrategy;
import com.mumfrey.liteloader.modconfig.ExposableOptions;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraft.util.registry.RegistryNamespacedDefaultedByKey;
/**
 * The example mod listener, as defined in the riftmod.json file
 * 
 * As many more as wanted can be made by adding their full names in the riftmod.json file
 * 
 * @author Reisse, Chocohead
 */

//@ExposableOptions(strategy = ConfigStrategy.Versioned, filename="featurecreep.json")
public class LiteModFeatureCreepMC{

	public static int MinBlockID = 2700;
	public static int MinItemID = 9000;
	
	
	  public static final Item AMETHYST = new GenericItem(CreativeTabs.MATERIALS).setUnlocalizedName("amethyst");
	    public static final Item RUBY = new GenericItem(CreativeTabs.MATERIALS).setUnlocalizedName("ruby");
	    public static final Item TIGERS_EYE = new GenericItem(CreativeTabs.MATERIALS).setUnlocalizedName("tigers_eye");
	    public static final Item TITANIUM = new GenericItem(CreativeTabs.MATERIALS).setUnlocalizedName("titanium_ingot");
	    public static final Item TITANIUM_NUGGET = new GenericItem(CreativeTabs.MATERIALS).setUnlocalizedName("titanium_nugget");
	    public static final Item URANIUM = new GenericItem(CreativeTabs.MATERIALS).setUnlocalizedName("uranium_ingot");
	    public static final Item URANIUM_NUGGET = new GenericItem(CreativeTabs.MATERIALS).setUnlocalizedName("uranium_nugget");
	    public static final Item ALUMINIUM = new GenericItem(CreativeTabs.MATERIALS).setUnlocalizedName("aluminium_ingot");
	    public static final Item COPPER_INGOT = new GenericItem(CreativeTabs.MATERIALS).setUnlocalizedName("ruby_copper_ingot");
	    public static final Item TIN_INGOT = new GenericItem(CreativeTabs.MATERIALS).setUnlocalizedName("tin_ingot");
	    public static final Item SILVER_INGOT = new GenericItem(CreativeTabs.MATERIALS).setUnlocalizedName("silver_ingot");
	    public static final Item PINK_TOURMALINE = new GenericItem(CreativeTabs.MATERIALS).setUnlocalizedName("pink_tourmaline_ingot");
	    public static final Item PINK_TOURMALINE_NUGGET = new GenericItem(CreativeTabs.MATERIALS).setUnlocalizedName("pink_tourmaline_nugget");
	    public static final Item STEEL_INGOT = new GenericItem(CreativeTabs.MATERIALS).setUnlocalizedName("steel_ingot");
	    public static final Item Oil = new GenericItem(CreativeTabs.MATERIALS).setUnlocalizedName("oil");
	    public static final Item Salt = new GenericItem(CreativeTabs.MATERIALS).setUnlocalizedName("salt");
	    public static final Item MobzillaScale = new GenericItem(CreativeTabs.MATERIALS).setUnlocalizedName("mobzilla_scale");
	    public static final Item QueenScale = new GenericItem(CreativeTabs.MATERIALS).setUnlocalizedName("queen_scale");
	    public static final Item MothScale = new GenericItem(CreativeTabs.MATERIALS).setUnlocalizedName("moth_scale");
    public static final Item PLATINUM_INGOT = new GenericItem(CreativeTabs.MATERIALS).setUnlocalizedName("platinum_ingot");
	    public static final Item LAVA_EEL = new GenericItem(CreativeTabs.MATERIALS).setUnlocalizedName("lava_eel");
	    public static final Item PEACKOCK_FEATHER = new GenericItem(CreativeTabs.MATERIALS).setUnlocalizedName("peacock_feather");
	    public static final Item SAPPHIRE = new GenericItem(CreativeTabs.MATERIALS).setUnlocalizedName("sapphire");
	    public static final Item GASOLINE_PETROL = new GenericItem(CreativeTabs.MATERIALS).setUnlocalizedName("gasoline_petrol");

	    public static final Item MINERS_DREAM = new MinersDream(CreativeTabs.MISC, -5, 5, 0, 5, 0, 50).setUnlocalizedName("miners_dream");
	    public static final Item LARGE_MINERS_DREAM = new MinersDream(CreativeTabs.MISC, -250, 250, -250, 250, -50, 250).setUnlocalizedName("large_miners_dream");
	
	    
	    
	    public static final Item RAW_BACON = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("bacon");
	    public static final Item COOKED_BACON = new FCFood(10, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("coocked_bacon");
	    public static final Item BUTTER_CANDY = new FCFood(3, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("butter_candy");
	    public static final Item CRYSTAL_APPLE = new FCFood(10, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("crystal_apple");
	    public static final Item LOVE_FOOD = new FCFood(15, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("love_food");
	    public static final Item POPCORN = new FCFood(3, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("popcorn");
	    public static final Item BUTTER_FOOD = new FCFood(3, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("butter_food");
	    public static final Item CORN_DOG = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("corndog");
	    public static final Item COOKED_CORN_DOG = new FCFood(6, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("cooked_corndog");
	    public static final Item RAW_CRAB_MEAT = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("crab_meat");
	    public static final Item COOKED_CRAB_MEAT = new FCFood(10, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("raw_crab_meat");
	    public static final Item CHEESE = new FCFood(3, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("cheese");
	    public static final Item SALAD = new FCFood(15, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("salad");
	    public static final Item BLT = new FCFood(13, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("blt");
	    public static final Item CRAB_PATTY = new FCFood(13, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("crab_patty");
	    public static final Item MAGIC_APPLE = new FCFood(15, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("magic_apple");
	    public static final Item PEACH = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("peach");
	    public static final Item RAW_PEACOCK = new FCFood(8, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("raw_peacock");
	    public static final Item COOCKED_PEACOCK = new FCFood(13, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("cooked_peacock");
	    public static final Item BLUE_FISH = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("blue_fish");
	    public static final Item BUTTERED_POPCORN = new FCFood(10, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("buttered_popcorn");
	    public static final Item SALTED_POPCORN = new FCFood(10, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("salted_popcorn");
	    public static final Item BUTTERED_AND_SALTED_POPCORN = new FCFood(15, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("buttered_and_salted_popcorn");
	    public static final Item CHERRY = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("cherry");
	    public static final Item CORN = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("corn");
	    public static final Item POPCORN_BAG = new FCFood(15, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("popcorn_bag");
	    public static final Item QUINOA = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("quinoa");
	    public static final Item RADISH = new FCFood( 5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("radish");
	    public static final Item RICE = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("rice");
	    public static final Item ROCK_FISH = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("rock_fish");
	    public static final Item FIRE_FISH = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("fire_fish");
	    public static final Item SPARK_FISH = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("spark_fish");
	    public static final Item GREEN_FISH = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("green_fish");
	    public static final Item GREY_FISH = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("grey_fish");
	    public static final Item PINK_FISH = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("pink_fish");
	    public static final Item SUN_FISH = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("sun_fish");
	    public static final Item STRAWBERRY = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("strawberry");
	    public static final Item LETTUCE = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("lettuce");
	    public static final Item TOMATO = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("tomato");
	    public static final Item WOOD_FISH = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("wood_fish");
	    public static final Item RAW_MOOSE_MEAT = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("moose_meat");
	    //public static final Item COOKED_MOOSE_MEAT = new FCFood("raw_bacon", 2, true);
	    public static final Item DEAD_BUG = new FCFood(2, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("dead_bug");
	    public static final Item MAGIC_FROG_OF_STRENGTH = new FCFood( 5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("magic_frog_of_strength");
	    public static final Item MAGIC_FROG_OF_WEAKNESS = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("magic_frog_of_weakness");
	    public static final Item MAGIC_FROG_OF_SPEED = new FCFood( 5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("magic_frog_of_speed");
	    public static final Item MAGIC_FROG_OF_SLOWNESS = new FCFood( 5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("magic_frog_of_slowness");
	    public static final Item MAGIC_FROG_OF_REGENERATION = new FCFood( 5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("magic_frog_of_regeneration");
	    public static final Item MAGIC_FROG_OF_POISON = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("magic_frog_of_poison");
	    public static final Item MAGIC_FROG_OF_MORPH = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("magic_frog_of_morph");
	    public static final Item MAGIC_FROG_OF_CONFUSION = new FCFood(5, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("magic_frog_of_confusion");
	    public static final Item COOKED_MOOSE_MEAT = new FCFood(10, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("cooked_moose_meat");
	    public static final Item CANDY_CANE = new FCFood( 10, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("candy_cane");
	    public static final Item GOLDEN_BREAD = new FCFood( 10, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("golden_bread");
	    public static final Item GOLDEN_CHICKEN = new FCFood( 10, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("golden_chicken");
	    public static final Item GOLDEN_TROPICAL_FISH = new FCFood( 10, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("golden_tropical_fish");
	    public static final Item GOLDEN_COD = new FCFood(10, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("golden_cod");
	    public static final Item GOLDEN_PORKCHOP = new FCFood( 10, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("golden_porkchop");
	    public static final Item WATERMELON_SLICE = new FCFood( 10, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("watermelon_slice");
	    public static final Item GOLDEN_MUSHROOM_STEW = new FCFood( 15, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("golden_mushroom_stew");
	    public static final Item GOLDEN_STEAK = new FCFood(15, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("golden_steak");
	    public static final Item GOLDEN_COOKIE = new FCFood(10, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("golden_cookie");
	    public static final Item GOLDEN_POTATO = new FCFood(10, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("golden_potato");
	    public static final Item GOLDEN_PUMPKIN_PIE = new FCFood(15, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("golden_pumpkin_pie");
	    public static final Item GOLDEN_ROTTON_FLESH = new FCFood(10, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("golden_rotton_flesh");
	    public static final Item GOLDEN_CARROT = new FCFood(10, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("golden_carrot");
	    public static final Item GOLDEN_PUFFERFISH = new FCFood(10, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("golden_pufferfish");
	    public static final Item GOLDEN_SALMON = new FCFood(10, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("golden_salmon");
	    public static final Item GOLDEN_CANDYCANE = new FCFood(15, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("golden_candycane");
	    public static final Item ULTIMATE_APPLE = new FCFood(15, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("ultimate_apple");
	    public static final Item ENCHANTED_GOLDEN_CARROT = new FCFood(15, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("enchanted_golden_carrot");
	    public static final Item ENCHANTED_GOLDEN_STEAK = new FCFood(15, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("enchanted_golden_steak");
	    public static final Item ENCHANTED_GOLDEN_COD = new FCFood(15, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("enchanted_golden_cod");
	    public static final Item ENCHANTED_GOLDEN_COOKIE = new FCFood(15, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("enchanted_golden_cookie");
	    public static final Item ENCHANTED_GOLDEN_CANDYCANE = new FCFood(15, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("enchanted_golden_candycane");
	    public static final Item RADDISH_STEW = new FCFood(15, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("raddish_stew");
	    public static final Item DRINKABLE_GASOLINE_PETROL = new FCFood(15, 0.85F, true, CreativeTabs.FOOD).setUnlocalizedName("drinkable_gasoline_petrol");


	    public static final ArmorMaterial SAND_ARMOUR = EnumHelper.addArmorMaterial("SAND", "featurecreep:sand", 3, new int[] {1, 1, 1, 1}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial RED_SAND_ARMOUR = EnumHelper.addArmorMaterial("RED_SAND", "featurecreep:red_sand", 3, new int[] {1, 1, 1, 1}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial DIRT_ARMOUR = EnumHelper.addArmorMaterial("DIRT", "featurecreep:dirt", 5, new int[] {1, 2, 2, 1}, 5, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial COARSE_DIRT_ARMOUR = EnumHelper.addArmorMaterial("COARSE_DIRT", "featurecreep:coarse_dirt",  6, new int[] {1, 2, 2, 1}, 5, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F ); 
	    public static final ArmorMaterial GRAVEL_ARMOUR = EnumHelper.addArmorMaterial("GRAVEL", "featurecreep:gravel", 8, new int[] {1, 2, 2, 1}, 7, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial CACTUS_ARMOUR = EnumHelper.addArmorMaterial("CACTUS", "featurecreep:cactus", 4, new int[] {2, 2, 2, 1}, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial COBBLE_STONE_ARMOUR = EnumHelper.addArmorMaterial("COBBLE_STONE", "featurecreep:cobble_stone", 15, new int[] {2, 3, 3, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial SUN_STONE_ARMOUR = EnumHelper.addArmorMaterial("SUN_STONE", "featurecreep:sun_stone", 8, new int[] {2, 3, 2, 2}, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial CRYSTAL_WOOD_PLANK_ARMOUR = EnumHelper.addArmorMaterial("CRYSTAL_WOOD_PLANK", "featurecreep:crystal_wood_plank",  20, new int[] {2, 3, 3, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial BONE_ARMOUR = EnumHelper.addArmorMaterial("BONE", "featurecreep:bone",  7, new int[] {2, 3, 2, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial WOOD_PLANK_ARMOUR = EnumHelper.addArmorMaterial("WOOD_PLANK", "featurecreep:wood_plank", 12, new int[] {2, 2, 2, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial WOOD_BLOCK_ARMOUR = EnumHelper.addArmorMaterial("WOOD_BLOCK", "featurecreep:wood_block", 20, new int[] {3, 3, 3, 2}, 11, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial NETHERRACK_BLOODSTONE_ARMOUR = EnumHelper.addArmorMaterial("NETHERRACK_BLOODSTONE", "featurecreep:netherrack_bloodstone", 17, new int[] {3, 4, 4, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial STONE_ARMOUR = EnumHelper.addArmorMaterial("STONE", "featurecreep:stone", 24, new int[] {3, 4, 3, 3}, 13, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial CRYSTAL_WOOD_ARMOUR = EnumHelper.addArmorMaterial("CRYSTAL_WOOD", "featurecreep:crystal_wood", 20, new int[] {4, 4, 4, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial FLINT_ARMOUR = EnumHelper.addArmorMaterial("FLINT", "featurecreep:flint",  25, new int[] {4, 4, 4, 3}, 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F ); 
	    public static final ArmorMaterial COAL_ARMOUR = EnumHelper.addArmorMaterial("COAL", "featurecreep:coal", 17, new int[] {4, 4, 4, 3}, 17, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial KYANITE_ARMOUR = EnumHelper.addArmorMaterial("KYANITE", "featurecreep:kyanite", 21, new int[] {4, 5, 4, 4}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F ); 
	    public static final ArmorMaterial WAX_ARMOUR = EnumHelper.addArmorMaterial("WAX", "featurecreep:wax", 17, new int[] {4, 5, 5, 4}, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial LAPIS_ARMOUR = EnumHelper.addArmorMaterial("LAPIS", "featurecreep:lapis", 17, new int[] {5, 5, 5, 5}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial RED_STONE_ARMOUR = EnumHelper.addArmorMaterial("RED_STONE", "featurecreep:red_stone", 15, new int[] {5, 6, 5, 5}, 35, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial RED_STONE_BLOCK_ARMOUR = EnumHelper.addArmorMaterial("RED_STONE_BLOCK", "featurecreep:red_stone_block", 22, new int[] {10, 10, 10, 10}, 40, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial QUARTZ_ARMOUR = EnumHelper.addArmorMaterial("QUARTZ", "featurecreep:quartz", 22, new int[] {6, 7, 7, 6}, 43, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial QUARTZ_BLOCK_ARMOUR = EnumHelper.addArmorMaterial("QUARTZ_BLOCK", "featurecreep:quartz_block", 30, new int[] {11, 12, 11, 11}, 45, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial END_STONE_ARMOUR = EnumHelper.addArmorMaterial("END_STONE", "featurecreep:end_stone", 20, new int[] {8, 8, 8, 7}, 50, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial LEAD_ARMOUR = EnumHelper.addArmorMaterial("LEAD", "featurecreep:lead",  30, new int[] {8, 8, 8, 8}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial CALCIUM_ARMOUR = EnumHelper.addArmorMaterial("CALCIUM", "featurecreep:calcium", 30, new int[] {8, 9, 9, 8}, 32, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial URANIUM_ARMOUR = EnumHelper.addArmorMaterial("URANIUM", "featurecreep:uranium", 25, new int[] {21, 22, 21, 21}, 35, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial TITANIUM_ARMOUR = EnumHelper.addArmorMaterial("TITANIUM", "featurecreep:titanium",  35, new int[] {22, 23, 23, 22}, 27, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial CELESTIAL_ARMOUR = EnumHelper.addArmorMaterial("CELESTIAL", "featurecreep:celestial", 35, new int[] {75, 75, 75, 75}, 50, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial EXTRA_CELESTIAL_ARMOUR = EnumHelper.addArmorMaterial("EXTRA_CELESTIAL", "featurecreep:extra_celestial", 50, new int[] {150, 150, 150, 150}, 40, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial STEEL_ARMOUR = EnumHelper.addArmorMaterial("STEEL", "featurecreep:steel", 45, new int[] {25, 25, 25, 25}, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial OBSIDIAN_ARMOUR = EnumHelper.addArmorMaterial("OBSIDIAN", "featurecreep:obsidian", 43, new int[] {22, 23, 23, 22}, 40, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial ArmourSetOSEmerald = EnumHelper.addArmorMaterial("EMERALD", "featurecreep:emerald", 25, new int[] {15, 25, 25, 15}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial ArmourSetAmethyst = EnumHelper.addArmorMaterial("AMETHYST", "featurecreep:amethyst", 25, new int[] {10, 20, 20, 10}, 200, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial RubyArmour = EnumHelper.addArmorMaterial("RUBY", "featurecreep:ruby", 50, new int[] {15, 25, 25, 15}, 60, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial RUBY_ON_RAILS_Armour= EnumHelper.addArmorMaterial("RUBY_ON_RAILS", "featurecreep:ruby_on_rails", 55, new int[] {30, 70, 70, 30}, 60, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F ); 
	    public static final ArmorMaterial TigerEyeArmour = EnumHelper.addArmorMaterial("TIGERS_EYE", "featurecreep:tigers_eye", 35, new int[] {15, 20, 20, 15}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial UltimateArmour = EnumHelper.addArmorMaterial("ULTIMATE", "featurecreep:ultimate", 35, new int[] {50, 75, 75, 50}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial ArmourExperience = EnumHelper.addArmorMaterial("EXPERIENCE", "featurecreep:experience", 25, new int[] {15, 25, 25, 15}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial ROYAL_GUARDIAN_ARMOUR = EnumHelper.addArmorMaterial("ROYAL_GUARDIAN", "featurecreep:royal_guardian", 35, new int[] {100, 150, 150, 100}, 17, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial QUEEN_SCALE_ARMOUR = EnumHelper.addArmorMaterial("QUEEN_SCALE", "featurecreep:queen_scale", 20, new int[] {150, 200, 200, 150}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial MOBZILLA_SCALE_ARMOUR = EnumHelper.addArmorMaterial("MOBZILLA_SCALE", "featurecreep:mobzilla_scale", 20, new int[] {75, 100, 100, 75}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial LAPIS_LAZUI_ARMOUR = EnumHelper.addArmorMaterial("LAPIS_BLOCK", "featurecreep:lapis_block", 25, new int[] {5, 10, 10, 5}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial LAVA_Eel_ARMOUR = EnumHelper.addArmorMaterial("LAVA_EEL", "featurecreep:lava_eel", 35, new int[] {20, 25, 25, 20}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial PEACOCK_FEATHER_ARMOUR = EnumHelper.addArmorMaterial("PEACOCK", "featurecreep:peacock_feather", 50, new int[] {15, 25, 25, 15}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial ArmourPinkTourmaline = EnumHelper.addArmorMaterial("PINK_TOURMALINE", "featurecreep:pink_tourmaline", 35, new int[] {15, 20, 20, 15}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial ArmourTourmaline = EnumHelper.addArmorMaterial("TOURMALINE", "featurecreep:tourmaline", 35, new int[] {15, 20, 20, 15}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial ArmourWhiteTourmaline = EnumHelper.addArmorMaterial("WHITE_TOURMALINE", "featurecreep:white_tourmaline", 35, new int[] {15, 20, 20, 15}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial ArmourCopper = EnumHelper.addArmorMaterial("COPPER", "featurecreep:copper", 15, new int[] {2, 5, 15, 10}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial ArmourSilver = EnumHelper.addArmorMaterial("SILVER", "featurecreep:silver", 20, new int[] {4, 15, 15, 10}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial ArmourAluminium = EnumHelper.addArmorMaterial("ALUMINIUM", "featurecreep:aluminium", 18, new int[] {3, 13, 13, 8}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial ArmourPlatinum = EnumHelper.addArmorMaterial("PLATINUM", "featurecreep:platinum", 20, new int[] {4, 15, 15, 10}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial MOTH_SCALE_Armour = EnumHelper.addArmorMaterial("MOTH_SCALE", "featurecreep:moth_scale", 18, new int[] {20, 30, 30, 20}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial ArmourSetSapphire = EnumHelper.addArmorMaterial("SAPPHIRE", "featurecreep:sapphire", 25, new int[] {10, 20, 20, 10}, 200, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial CZArmourSlow = EnumHelper.addArmorMaterial("CZArmourSlow", "featurecreep:czslow", 25, new int[] {2, 3, 3, 2}, 50, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
	    public static final ArmorMaterial ArmourTin = EnumHelper.addArmorMaterial("TIN", "featurecreep:tin", 35, new int[] {15, 25, 25, 15}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );


	    

public static final ToolMaterial LEATHERTool = EnumHelper.addToolMaterial("LEATHER", 0, 59, 2.0F, 0.0F, 15);
public static final ToolMaterial CHAINMAILTool = EnumHelper.addToolMaterial("CHAINMAIL", 1, 131, 4.0F, 1.0F, 5);
public static final ToolMaterial SANDTool = EnumHelper.addToolMaterial("SAND", 0, 17, 1.0F, 0.0F, 10);
public static final ToolMaterial RED_SANDTool = EnumHelper.addToolMaterial("RED_SAND", 0, 17, 1.0F, 0.0F, 10);
public static final ToolMaterial DIRTTool = EnumHelper.addToolMaterial("DIRT", 0, 23, 1.0F, 0.0F, 10);
public static final ToolMaterial COARSE_DIRTTool = EnumHelper.addToolMaterial("COARSE", 1, 25, 5.0F, 1.0F, 10);
public static final ToolMaterial GRAVELTool = EnumHelper.addToolMaterial("GRAVEL", 1, 28, 5.0F, 1.0F, 10);
public static final ToolMaterial CACTUSTool = EnumHelper.addToolMaterial("CACTUS", 1, 20, 5.0F, 25.0F, 10);
public static final ToolMaterial SUN_STONETool = EnumHelper.addToolMaterial("SUN_STONE", 1, 30, 3.0F, 3.0F, 10);
public static final ToolMaterial BONETool = EnumHelper.addToolMaterial("BONE", 1, 27, 3.0F, 4.0F, 10);
public static final ToolMaterial CRYSTAL_WOOD_PLANKTool = EnumHelper.addToolMaterial("CRYSTAL_WOOD_PLANK", 2, 160, 5.0F, 5.0F, 10);
public static final ToolMaterial WOOD_BLOCKTool = EnumHelper.addToolMaterial("WOOD", 2, 175, 5.0F, 6.0F, 10);
public static final ToolMaterial STONETool = EnumHelper.addToolMaterial("STONE", 2, 200, 5.0F, 5.0F, 10);
public static final ToolMaterial NETHERRACK_BLOODSTONETool = EnumHelper.addToolMaterial("NETHERRACK_BLOODSTONE", 2, 145, 5.0F, 8.0F, 10);
public static final ToolMaterial CRYSTAL_WOODTool = EnumHelper.addToolMaterial("CRYSTAL_WOOD", 3, 165, 6.0F, 8.0F, 10);
public static final ToolMaterial FLINTTool = EnumHelper.addToolMaterial("FLINT", 3, 2500, 6.0F, 5.0F, 10);
public static final ToolMaterial COALTool = EnumHelper.addToolMaterial("COAL", 3, 2000, 8.0F, 7.0F, 10);
public static final ToolMaterial KYANITETool = EnumHelper.addToolMaterial("KYANITE", 4, 2000, 6.0F, 8.0F, 10);
public static final ToolMaterial WAXTool = EnumHelper.addToolMaterial("WAX", 4, 1000, 7.0F, 9.0F, 10);
public static final ToolMaterial LAPISTool = EnumHelper.addToolMaterial("LAPIS", 4,1100 , 7.0F, 9.0F, 10);
public static final ToolMaterial RED_STONETool = EnumHelper.addToolMaterial("RED_STONE", 5, 1200, 8.0F, 10.0F, 10);
public static final ToolMaterial QUARTZTool = EnumHelper.addToolMaterial("QUARTZ", 5, 1500, 8.0F, 10.0F, 10);
public static final ToolMaterial END_STONETool = EnumHelper.addToolMaterial("END_STONE", 6, 1100, 9.0F, 12.0F, 10);
public static final ToolMaterial LEADTool = EnumHelper.addToolMaterial("LEAD", 6, 5000, 9.0F, 11.0F, 10);
public static final ToolMaterial CALCIUMTool = EnumHelper.addToolMaterial("CALCIUM", 6, 1200, 9.0F, 15.0F, 10);
public static final ToolMaterial RED_STONE_BLOCKTool = EnumHelper.addToolMaterial("RED_STONE", 7, 3000, 9.0F, 13.0F, 10);
public static final ToolMaterial QUARTZ_BLOCKTool = EnumHelper.addToolMaterial("QUARTZ", 8, 4500, 14.0F, 17.0F, 10);
public static final ToolMaterial URANIUMTool = EnumHelper.addToolMaterial("URANIUM", 13, 4000, 30.0F, 23.0F, 10);
public static final ToolMaterial TITANIUMTool = EnumHelper.addToolMaterial("TITANIUM", 13, 5500, 27.0F, 20.0F, 10);
public static final ToolMaterial OBSIDIANTool = EnumHelper.addToolMaterial("OBSIDIAN", 13, 8000, 28.0F, 20.0F, 10);
public static final ToolMaterial STEELTool = EnumHelper.addToolMaterial("STEEL", 13, 8000, 29.0F, 24.0F, 10);
public static final ToolMaterial CELESTIALTool = EnumHelper.addToolMaterial("CELESTIAL", 35, 25000, 80.0F, 34.0F, 10);
public static final ToolMaterial EXTRA_CELESTIALTool = EnumHelper.addToolMaterial("EXTRA_CELESTIAL", 50, 30000, 95.0F, 50.0F, 10);

public static final ToolMaterial OSTOOLEmerald = EnumHelper.addToolMaterial("EMERALD", 9, 2000, 10.0F, 25.0F, 10);
public static final ToolMaterial AmethystTool = EnumHelper.addToolMaterial("AMETHYST", 8, 5000, 20.0F, 18.0F, 20);
public static final ToolMaterial RubyTool = EnumHelper.addToolMaterial("RUBY", 12, 8000, 20.0F, 30.0F, 20);
public static final ToolMaterial RUBY_ON_RAILSTool = EnumHelper.addToolMaterial("RUBY_ON_RAILS", 25, 25000, 60.0F, 30.0F, 25);
public static final ToolMaterial EXPERIENCETool = EnumHelper.addToolMaterial("EXPERIENCE", 9, 2000, 10.0F, 25.0F, 10);
public static final ToolMaterial SapphireTool = EnumHelper.addToolMaterial("SAPPHIRE", 8, 5000, 20.0F, 18.0F, 20);
public static final ToolMaterial TigerEyeTool = EnumHelper.addToolMaterial("TIGERS_EYE",  10, 6000, 15.0F, 20.0F, 15);
public static final ToolMaterial COPPERTool = EnumHelper.addToolMaterial("COPPER",  6, 3000, 9.0F, 10.0F, 15);
public static final ToolMaterial LAPIS_BLOCKTool = EnumHelper.addToolMaterial("LAPIS_BLOCK",  6, 4000, 9.0F, 11.0F, 15);
public static final ToolMaterial ALUMINIUMTool = EnumHelper.addToolMaterial("ALUMINIUM",  6, 4000, 9.0F, 12.0F, 15);
public static final ToolMaterial SILVERTool = EnumHelper.addToolMaterial("SILVER",  7, 4000, 10.0F, 13.0F, 15);
public static final ToolMaterial  PLATINUMTool = EnumHelper.addToolMaterial("PLATINUM",  7, 5000, 10.0F, 14.0F, 15);
public static final ToolMaterial  TINTool = EnumHelper.addToolMaterial("TIN",  9, 1000, 21.0F, 15.0F, 15);
public static final ToolMaterial PEACOCK_FEATHERTool = EnumHelper.addToolMaterial("PEACOCK_FEATHER",  10, 3000, 23.0F, 18.0F, 15);
public static final ToolMaterial PINK_TOURMALINETool = EnumHelper.addToolMaterial("PINK_TOURMALINE",  10, 4000, 22.0F, 16.0F, 15);
public static final ToolMaterial WHITE_TOURMALINETool = EnumHelper.addToolMaterial("WHITE_TOURMALINE",  10, 4000, 22.0F, 16.0F, 15);
public static final ToolMaterial TOURMALINETool = EnumHelper.addToolMaterial("TOURMALINE",  10, 4000, 22.0F, 16.0F, 15);
public static final ToolMaterial UltimateTool = EnumHelper.addToolMaterial("ULTIMATE", 30, 30000, 30.0F, 70.0F, 45);
public static final ToolMaterial LAVA_EELTool = EnumHelper.addToolMaterial("LAVA_EEL", 13, 3000, 30.0F, 25.0F, 20);
public static final ToolMaterial MOTH_SCALETool = EnumHelper.addToolMaterial("MOTH_SCALE", 14, 2000, 33.0F, 28.0F, 20);
public static final ToolMaterial MOBZILLA_SCALETool = EnumHelper.addToolMaterial("MOBZILLA_SCALE", 40, 20000, 85.0F, 30.0F, 20);
public static final ToolMaterial ROYAL_GUARDIANTool = EnumHelper.addToolMaterial("ROYAL_GUARDIAN", 45, 30000, 90.0F, 45.0F, 20);
public static final ToolMaterial QUEEN_SCALETool = EnumHelper.addToolMaterial("QUEEN_SCALE", 55, 20000, 100.0F, 55.0F, 20);
public static final ToolMaterial OptimisedTool = EnumHelper.addToolMaterial("OPTIMISED", 250, 25000, 100.0F, 8.0F, 45);


	    
	    
	    
	    
	    public static final Item EMERALD_PICKAXE = new GenericPickaxe(CreativeTabs.TOOLS, OSTOOLEmerald).setUnlocalizedName("emerald_pickaxe");
	    public static final Item EMERALD_HOLE = new GenericHoe(CreativeTabs.TOOLS, OSTOOLEmerald).setUnlocalizedName("emerald_hoe");
	    public static final Item EMERALD_SWORD = new GenericSword(CreativeTabs.TOOLS, OSTOOLEmerald).setUnlocalizedName("emerald_sword");
	    public static final Item EMERALD_SHOVEL = new GenericShovel(CreativeTabs.TOOLS, OSTOOLEmerald).setUnlocalizedName("emerald_shovel");
	    public static final Item EMERALD_HELMET = new GenericArmour(CreativeTabs.COMBAT, ArmourSetOSEmerald, 1, EntityEquipmentSlot.HEAD).setUnlocalizedName("emerald_helmet");
	    public static final Item EMERALD_CHESTPLATE = new GenericArmour(CreativeTabs.COMBAT, ArmourSetOSEmerald, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("emerald_chestplate");
	    public static final Item EMERALD_LEGGINGS = new GenericArmour(CreativeTabs.COMBAT, ArmourSetOSEmerald, 2, EntityEquipmentSlot.LEGS).setUnlocalizedName("emerald_leggings");
	    public static final Item EMERALD_BOOTS = new GenericArmour(CreativeTabs.COMBAT, ArmourSetOSEmerald, 1, EntityEquipmentSlot.FEET).setUnlocalizedName("emerald_boots");
	    public static final Item EMERALD_AXE = new GenericAxe(CreativeTabs.TOOLS, OSTOOLEmerald).setUnlocalizedName("emerald_axe");

	    //same properties as Emeralds
	    public static final Item AMETHYST_PICKAXE = new GenericPickaxe(CreativeTabs.TOOLS, AmethystTool).setUnlocalizedName("amethyst_pickaxe");
	    public static final Item AMETHYST_HOE = new GenericHoe(CreativeTabs.TOOLS, AmethystTool).setUnlocalizedName("amethyst_hoe");
	    public static final Item AMETHYST_SWORD = new GenericSword(CreativeTabs.COMBAT, AmethystTool).setUnlocalizedName("amethyst_sword");
	    public static final Item AMETHYST_SHOVEL = new GenericShovel(CreativeTabs.TOOLS, AmethystTool).setUnlocalizedName("amethyst_shovel");
	    public static final Item AMETHYST_HELMET = new GenericArmour(CreativeTabs.COMBAT, ArmourSetAmethyst, 1, EntityEquipmentSlot.HEAD).setUnlocalizedName("amethyst_helmet");
	    public static final Item AMETHYST_CHESTPLATE = new GenericArmour(CreativeTabs.COMBAT, ArmourSetAmethyst, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("amethyst_chestplate");
	    public static final Item AMETHYST_LEGGINGS = new GenericArmour(CreativeTabs.COMBAT, ArmourSetAmethyst, 2, EntityEquipmentSlot.LEGS).setUnlocalizedName("amethyst_leggings");
	    public static final Item AMETHYST_BOOTS = new GenericArmour(CreativeTabs.COMBAT, ArmourSetAmethyst, 1, EntityEquipmentSlot.FEET).setUnlocalizedName("amethyst_boots");
	    public static final Item AMETHYST_AXE = new GenericAxe(CreativeTabs.TOOLS, AmethystTool).setUnlocalizedName("amethyst_axe");


	    //same properties as AMETHYST
	    public static final Item SAPPHIRE_PICKAXE = new GenericPickaxe(CreativeTabs.TOOLS, SapphireTool).setUnlocalizedName("sapphire_pickaxe");
	    public static final Item SAPPHIRE_HOE = new GenericHoe(CreativeTabs.TOOLS, SapphireTool).setUnlocalizedName("sapphire_hoe");
	    public static final Item SAPPHIRE_SWORD = new GenericSword(CreativeTabs.COMBAT, SapphireTool).setUnlocalizedName("sapphire_sword");
	    public static final Item SAPPHIRE_SHOVEL = new GenericShovel(CreativeTabs.TOOLS, SapphireTool).setUnlocalizedName("sapphire_shovel");
	    public static final Item SAPPHIRE_HELMET = new GenericArmour(CreativeTabs.COMBAT, ArmourSetSapphire, 1, EntityEquipmentSlot.HEAD).setUnlocalizedName("sapphire_helmet");
	    public static final Item SAPPHIRE_CHESTPLATE = new GenericArmour(CreativeTabs.COMBAT, ArmourSetSapphire, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("sapphire_chestplate");
	    public static final Item SAPPHIRE_LEGGINGS = new GenericArmour(CreativeTabs.COMBAT, ArmourSetSapphire, 2, EntityEquipmentSlot.LEGS).setUnlocalizedName("sapphire_leggings");
	    public static final Item SAPPHIRE_BOOTS = new GenericArmour(CreativeTabs.COMBAT, ArmourSetSapphire, 1, EntityEquipmentSlot.FEET).setUnlocalizedName("sapphire_boots");
	    public static final Item SAPPHIRE_AXE = new GenericAxe(CreativeTabs.TOOLS, SapphireTool).setUnlocalizedName("sapphire_axe");




	    //Same Properties as Emeralds
	    public static final Item RUBY_PICKAXE = new GenericPickaxe(CreativeTabs.TOOLS, RubyTool).setUnlocalizedName("ruby_pickaxe");
	    public static final Item RUBY_HOE = new GenericHoe(CreativeTabs.TOOLS, RubyTool).setUnlocalizedName("ruby_hoe");
	    public static final Item RUBY_SWORD = new GenericSword(CreativeTabs.COMBAT, RubyTool).setUnlocalizedName("ruby_sword");
	    public static final Item RUBY_SHOVEL = new GenericShovel(CreativeTabs.TOOLS, RubyTool).setUnlocalizedName("ruby_shovel");
	    public static final Item RUBY_HELMET = new GenericArmour(CreativeTabs.COMBAT, RubyArmour, 1, EntityEquipmentSlot.HEAD).setUnlocalizedName("ruby_helmet");
	    public static final Item RUBY_CHESTPLATE = new GenericArmour(CreativeTabs.COMBAT, RubyArmour, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("ruby_chestplate");
	    public static final Item RUBY_LEGGINGS = new GenericArmour(CreativeTabs.COMBAT, RubyArmour, 2, EntityEquipmentSlot.LEGS).setUnlocalizedName("ruby_leggings");
	    public static final Item RUBY_BOOTS = new GenericArmour(CreativeTabs.COMBAT, RubyArmour, 1, EntityEquipmentSlot.FEET).setUnlocalizedName("ruby_boots");
	    public static final Item RUBY_AXE = new GenericAxe(CreativeTabs.TOOLS, RubyTool).setUnlocalizedName("ruby_axe");


	    //Same Properties as Emeralds
	    public static final Item TIGERS_EYE_PICKAXE = new GenericPickaxe(CreativeTabs.TOOLS, TigerEyeTool).setUnlocalizedName("tigers_eye_pickaxe");
	    public static final Item TIGERS_EYE_HOE = new GenericHoe(CreativeTabs.TOOLS, TigerEyeTool).setUnlocalizedName("tigers_eye_hoe");
	    public static final Item TIGERS_EYE_SWORD = new GenericSword(CreativeTabs.COMBAT, TigerEyeTool).setUnlocalizedName("tigers_eye_sword");
	    public static final Item TIGERS_EYE_SHOVEL = new GenericShovel(CreativeTabs.TOOLS, TigerEyeTool).setUnlocalizedName("tigers_eye_shovel");
	    public static final Item TIGERS_EYE_HELMET = new GenericArmour(CreativeTabs.COMBAT, TigerEyeArmour, 1, EntityEquipmentSlot.HEAD).setUnlocalizedName("tigers_eye_helmet");
	    public static final Item TIGERS_EYE_CHESTPLATE = new GenericArmour(CreativeTabs.COMBAT, TigerEyeArmour, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("tigers_eye_chestplate");
	    public static final Item TIGERS_EYE_LEGGINGS = new GenericArmour(CreativeTabs.COMBAT, TigerEyeArmour, 2, EntityEquipmentSlot.LEGS).setUnlocalizedName("tigers_eye_leggings");
	    public static final Item TIGERS_EYE_BOOTS = new GenericArmour(CreativeTabs.COMBAT, TigerEyeArmour, 1, EntityEquipmentSlot.FEET).setUnlocalizedName("tigers_eye_boots");
	    public static final Item TIGERS_EYE_AXE = new GenericAxe(CreativeTabs.TOOLS, TigerEyeTool).setUnlocalizedName("tigers_eye_axe");

	    //Same Properties as Emeralds
	    public static final Item OPTIMISED_PICKAXE = new EnchantedPickaxe(CreativeTabs.TOOLS, OptimisedTool).setUnlocalizedName("optimised_pickaxe");
	    public static final Item OPTIMISED_SHOVEL = new EnchantedShovel(CreativeTabs.TOOLS, OptimisedTool).setUnlocalizedName("optimised_shovel");


	    public static final Item ULTIMATE_HELMET = new EnchantedArmour(CreativeTabs.COMBAT, UltimateArmour, 1, EntityEquipmentSlot.HEAD).setUnlocalizedName("ultimate_helmet");
	    public static final Item ULTIMATE_CHESTPLATE = new EnchantedArmour(CreativeTabs.COMBAT, UltimateArmour, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("ultimate_chestplate");
	    public static final Item ULTIMATE_LEGGINGS = new EnchantedArmour(CreativeTabs.COMBAT, UltimateArmour, 2, EntityEquipmentSlot.LEGS).setUnlocalizedName("ultimate_leggings");
	    public static final Item ULTIMATE_BOOTS = new EnchantedArmour(CreativeTabs.COMBAT, UltimateArmour, 1, EntityEquipmentSlot.FEET).setUnlocalizedName("ultimate_boots");
	    public static final Item ULTIMATE_PICKAXE = new EnchantedPickaxe(CreativeTabs.TOOLS, UltimateTool).setUnlocalizedName("ultimate_pickaxe");
	    public static final Item ULTIMATE_HOE = new EnchantedHoe(CreativeTabs.TOOLS, UltimateTool).setUnlocalizedName("ultimate_hoe");
	    public static final Item ULTIMATE_SWORD = new EnchantedSword(CreativeTabs.TOOLS, UltimateTool).setUnlocalizedName("ultimate_sword");
	    public static final Item ULTIMATE_SHOVEL = new EnchantedShovel(CreativeTabs.TOOLS, UltimateTool).setUnlocalizedName("ultimate_shovel");
	    public static final Item ULTIMATE_AXE = new EnchantedAxe(CreativeTabs.TOOLS, UltimateTool).setUnlocalizedName("ultimate_axe");





	    public static final Item TIN_HELMET = new GenericArmour(CreativeTabs.COMBAT, ArmourTin, 1, EntityEquipmentSlot.HEAD).setUnlocalizedName("tin_helmet");
	    public static final Item TIN_CHESTPLATE = new GenericArmour(CreativeTabs.COMBAT, ArmourTin, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("tin_chestplate");
	    public static final Item TIN_LEGGINGS = new GenericArmour(CreativeTabs.COMBAT, ArmourTin, 2, EntityEquipmentSlot.LEGS).setUnlocalizedName("tin_leggings");
	    public static final Item TIN_BOOTS = new GenericArmour(CreativeTabs.COMBAT, ArmourTin, 1, EntityEquipmentSlot.FEET).setUnlocalizedName("tin_boots");

	    public static final Item COPPER_HELMET = new GenericArmour(CreativeTabs.COMBAT, ArmourCopper, 1, EntityEquipmentSlot.HEAD).setUnlocalizedName("copper_helmet");
	    public static final Item COPPER_CHESTPLATE = new GenericArmour(CreativeTabs.COMBAT, ArmourCopper, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("copper_chestplate");
	    public static final Item COPPER_LEGGINGS = new GenericArmour(CreativeTabs.COMBAT, ArmourCopper, 2, EntityEquipmentSlot.LEGS).setUnlocalizedName("copper_leggings");
	    public static final Item COPPER_BOOTS = new GenericArmour(CreativeTabs.COMBAT, ArmourCopper, 1, EntityEquipmentSlot.FEET).setUnlocalizedName("copper_boots");
	 

	    public static final Item PLATINUM_HELMET = new GenericArmour(CreativeTabs.COMBAT, ArmourPlatinum, 1, EntityEquipmentSlot.HEAD).setUnlocalizedName("platinum_helmet");
	    public static final Item PLATINUM_CHESTPLATE = new GenericArmour(CreativeTabs.COMBAT, ArmourPlatinum, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("platinum_chestplate");
	    public static final Item PLATNIUM_LEGGINGS = new GenericArmour(CreativeTabs.COMBAT, ArmourPlatinum, 2, EntityEquipmentSlot.LEGS).setUnlocalizedName("platinum_leggings");
	    public static final Item PLATINUM_BOOTS = new GenericArmour(CreativeTabs.COMBAT, ArmourPlatinum, 1, EntityEquipmentSlot.FEET).setUnlocalizedName("platinum_boots");


	    public static final Item SILVER_HELMET = new GenericArmour(CreativeTabs.COMBAT, ArmourSilver, 1, EntityEquipmentSlot.HEAD).setUnlocalizedName("silver_helmet");
	    public static final Item SILVER_CHESTPLATE = new GenericArmour(CreativeTabs.COMBAT, ArmourSilver, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("silver_chestplate");
	    public static final Item SILVER_LEGGINGS = new GenericArmour(CreativeTabs.COMBAT, ArmourSilver, 2, EntityEquipmentSlot.LEGS).setUnlocalizedName("silver_leggings");
	    public static final Item SILVER_BOOTS = new GenericArmour(CreativeTabs.COMBAT, ArmourSilver, 1, EntityEquipmentSlot.FEET).setUnlocalizedName("silver_boots");


	    public static final Item ALUMINIUM_HELMET = new GenericArmour(CreativeTabs.COMBAT, ArmourAluminium, 1, EntityEquipmentSlot.HEAD).setUnlocalizedName("aluminium_helmet");
	    public static final Item ALUMINIUM_CHESTPLATE = new GenericArmour(CreativeTabs.COMBAT, ArmourAluminium, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("aluminium_chestplate");
	    public static final Item ALUMINIUM_LEGGINGS = new GenericArmour(CreativeTabs.COMBAT, ArmourAluminium, 2, EntityEquipmentSlot.LEGS).setUnlocalizedName("aluminium_leggings");
	    public static final Item ALUMINIUM_BOOTS = new GenericArmour(CreativeTabs.COMBAT, ArmourAluminium, 1, EntityEquipmentSlot.FEET).setUnlocalizedName("aluminium_boots");


	    public static final Item EXPERIENCE_HELMET = new EnchantedArmour(CreativeTabs.COMBAT, ArmourExperience, 1, EntityEquipmentSlot.HEAD).setUnlocalizedName("experience_helmet");
	    public static final Item EXPERIENCE_CHESTPLATE = new EnchantedArmour(CreativeTabs.COMBAT, ArmourExperience, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("experience_chestplate");
	    public static final Item EXPERIENCE_LEGGINGS = new EnchantedArmour(CreativeTabs.COMBAT, ArmourExperience, 2, EntityEquipmentSlot.LEGS).setUnlocalizedName("experience_leggings");
	    public static final Item EXPERIENCE_BOOTS = new EnchantedArmour(CreativeTabs.COMBAT, ArmourExperience, 1, EntityEquipmentSlot.FEET).setUnlocalizedName("experience_boots");

	    public static final Item ROYAL_GUARDIAN_HELMET = new EnchantedArmour(CreativeTabs.COMBAT, ROYAL_GUARDIAN_ARMOUR, 1, EntityEquipmentSlot.HEAD).setUnlocalizedName("royal_guardian_helmet");
	    public static final Item ROYAL_GUARDIAN_CHESTPLATE = new EnchantedArmour(CreativeTabs.COMBAT, ROYAL_GUARDIAN_ARMOUR, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("royal_guardian_chestplate");
	    public static final Item ROYAL_GUARDIAN_LEGGINGS = new EnchantedArmour(CreativeTabs.COMBAT, ROYAL_GUARDIAN_ARMOUR, 2, EntityEquipmentSlot.LEGS).setUnlocalizedName("royal_guardian_leggings");
	    public static final Item ROYAL_GUARDIAN_BOOTS = new EnchantedArmour(CreativeTabs.COMBAT, ROYAL_GUARDIAN_ARMOUR, 1, EntityEquipmentSlot.FEET).setUnlocalizedName("royal_guardian_boots");

	    public static final Item QUEEN_SCALE_HELMET = new EnchantedArmour(CreativeTabs.COMBAT, QUEEN_SCALE_ARMOUR, 1, EntityEquipmentSlot.HEAD).setUnlocalizedName("queen_scale_helmet");
	    public static final Item QUEEN_SCALE_CHESTPLATE = new EnchantedArmour(CreativeTabs.COMBAT, QUEEN_SCALE_ARMOUR, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("queen_scale_chestplate");
	    public static final Item QUEEN_SCALE_LEGGINGS = new EnchantedArmour(CreativeTabs.COMBAT, QUEEN_SCALE_ARMOUR, 2, EntityEquipmentSlot.LEGS).setUnlocalizedName("queen_scale_leggings");
	    public static final Item QUEEN_SCALE_BOOTS = new EnchantedArmour(CreativeTabs.COMBAT, QUEEN_SCALE_ARMOUR, 1, EntityEquipmentSlot.FEET).setUnlocalizedName("queen_scale_boots");

	    public static final Item MOBZILLA_SCALE_HELMET = new EnchantedArmour(CreativeTabs.COMBAT, MOBZILLA_SCALE_ARMOUR, 1, EntityEquipmentSlot.HEAD).setUnlocalizedName("mobzilla_scale_helmet");
	    public static final Item MOBZILLA_SCALE_CHESTPLATE = new EnchantedArmour(CreativeTabs.COMBAT, MOBZILLA_SCALE_ARMOUR, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("mobzilla_scale_chestplate");
	    public static final Item MOBZILLA_SCALE_LEGGINGS = new EnchantedArmour(CreativeTabs.COMBAT, MOBZILLA_SCALE_ARMOUR, 2, EntityEquipmentSlot.LEGS).setUnlocalizedName("mobzilla_scale_leggings");
	    public static final Item MOBZILLA_SCALE_BOOTS = new EnchantedArmour(CreativeTabs.COMBAT, MOBZILLA_SCALE_ARMOUR, 1, EntityEquipmentSlot.FEET).setUnlocalizedName("mobzilla_scale_boots");

	    public static final Item LAPIS_LAZUI_HELMET = new EnchantedArmour(CreativeTabs.COMBAT, LAPIS_LAZUI_ARMOUR, 1, EntityEquipmentSlot.HEAD).setUnlocalizedName("lapis_block_helmet");
	    public static final Item LAPIS_LAZUI_CHESTPLATE = new EnchantedArmour(CreativeTabs.COMBAT, LAPIS_LAZUI_ARMOUR, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("lapis_block_chestplate");
	    public static final Item LAPIS_LAZUI_LEGGINGS = new EnchantedArmour(CreativeTabs.COMBAT, LAPIS_LAZUI_ARMOUR, 2, EntityEquipmentSlot.LEGS).setUnlocalizedName("lapis_block_leggings");
	    public static final Item LAPIS_LAZUI_BOOTS = new EnchantedArmour(CreativeTabs.COMBAT, LAPIS_LAZUI_ARMOUR, 1, EntityEquipmentSlot.FEET).setUnlocalizedName("lapis_block_boots");
	


	    public static final Item LAVA_EEL_HELMET = new EnchantedArmour(CreativeTabs.COMBAT, LAVA_Eel_ARMOUR, 1, EntityEquipmentSlot.HEAD).setUnlocalizedName("lava_eel_helmet");
	    public static final Item LAVA_EEL_CHESTPLATE = new EnchantedArmour(CreativeTabs.COMBAT, LAVA_Eel_ARMOUR, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("lava_eel_chestplate");
	    public static final Item LAVA_EEL_LEGGINGS = new EnchantedArmour(CreativeTabs.COMBAT, LAVA_Eel_ARMOUR, 2, EntityEquipmentSlot.LEGS).setUnlocalizedName("lava_eel_leggings");
	    public static final Item LAVA_EEL_BOOTS = new EnchantedArmour(CreativeTabs.COMBAT, LAVA_Eel_ARMOUR, 1, EntityEquipmentSlot.FEET).setUnlocalizedName("lava_eel_boots");


	    public static final Item PEACOCK_FEATHER_HELMET = new EnchantedArmour(CreativeTabs.COMBAT, PEACOCK_FEATHER_ARMOUR, 1, EntityEquipmentSlot.HEAD).setUnlocalizedName("peacock_helmet");
	    public static final Item PEACOCK_FEATHER_CHESTPLATE = new EnchantedArmour(CreativeTabs.COMBAT, PEACOCK_FEATHER_ARMOUR, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("peacock_chestplate");
	    public static final Item PEACOCK_FEATHER_LEGGINGS = new EnchantedArmour(CreativeTabs.COMBAT, PEACOCK_FEATHER_ARMOUR, 2, EntityEquipmentSlot.LEGS).setUnlocalizedName("peacock_leggings");
	    public static final Item PEACOCK_FEATHER_BOOTS = new EnchantedArmour(CreativeTabs.COMBAT, PEACOCK_FEATHER_ARMOUR, 1, EntityEquipmentSlot.FEET).setUnlocalizedName("peacock_boots");


	    public static final Item PINK_TOURMALINE_HELMET = new GenericArmour(CreativeTabs.COMBAT, ArmourPinkTourmaline, 1, EntityEquipmentSlot.HEAD).setUnlocalizedName("pink_tourmaline_helmet");
	    public static final Item PINK_TOURMALINE_CHESTPLATE = new GenericArmour(CreativeTabs.COMBAT, ArmourPinkTourmaline, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("pink_tourmaline_chestplate");
	    public static final Item PINK_TOURMALINE_LEGGINGS = new GenericArmour(CreativeTabs.COMBAT, ArmourPinkTourmaline, 2, EntityEquipmentSlot.LEGS).setUnlocalizedName("pink_tourmaline_leggings");
	    public static final Item PINK_TOURMALINE_BOOTS = new GenericArmour(CreativeTabs.COMBAT, ArmourPinkTourmaline, 1, EntityEquipmentSlot.FEET).setUnlocalizedName("pink_tourmaline_boots");



	    public static final Item MOTH_SCALE_HELMET = new EnchantedArmour(CreativeTabs.COMBAT, MOTH_SCALE_Armour, 1, EntityEquipmentSlot.HEAD).setUnlocalizedName("moth_scale_helmet");
	    public static final Item MOTH_SCALE_CHESTPLATE = new EnchantedArmour(CreativeTabs.COMBAT, MOTH_SCALE_Armour, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("moth_scale_chestplate");
	    public static final Item MOTH_SCALE_LEGGINGS = new EnchantedArmour(CreativeTabs.COMBAT, MOTH_SCALE_Armour, 2, EntityEquipmentSlot.LEGS).setUnlocalizedName("moth_scale_leggings");
	    public static final Item MOTH_SCALE_BOOTS = new EnchantedArmour(CreativeTabs.COMBAT, MOTH_SCALE_Armour, 1, EntityEquipmentSlot.FEET).setUnlocalizedName("moth_scale_boots");


	    public static final Item CZ_SLOW_BOOTS = new CZSlowArmour(CreativeTabs.COMBAT, CZArmourSlow, 1, EntityEquipmentSlot.FEET).setUnlocalizedName("cz_slow_boots");



	    public static final Block AMETHYST_ORE = new GenericBlock(Material.IRON, CreativeTabs.BUILDING_BLOCKS).setUnlocalizedName("amethyst_ore");
	    public static final Block RUBY_ORE = new GenericBlock(Material.IRON, CreativeTabs.BUILDING_BLOCKS).setUnlocalizedName("ruby_ore");
	    public static final Block TIGERS_EYE_ORE = new GenericBlock(Material.IRON, CreativeTabs.BUILDING_BLOCKS).setUnlocalizedName("tigers_eye_ore");
	    public static final Block TITANIUM_ORE = new GenericBlock(Material.IRON, CreativeTabs.BUILDING_BLOCKS).setUnlocalizedName("titanium_ore");
	    public static final Block URANIUM_ORE = new GenericBlock(Material.IRON, CreativeTabs.BUILDING_BLOCKS).setUnlocalizedName("uranium_ore");
	    public static final Block ALUMINIUM_ORE = new GenericBlock(Material.IRON, CreativeTabs.BUILDING_BLOCKS).setUnlocalizedName("aluminium_ore");
	    public static final Block SALT_ORE = new GenericBlock(Material.IRON, CreativeTabs.BUILDING_BLOCKS).setUnlocalizedName("salt_ore");
	    public static final Block UNPROCESSED_OIL_ORE = new GenericBlock(Material.IRON, CreativeTabs.BUILDING_BLOCKS).setUnlocalizedName("unprocessed_oil_ore");
	    public static final Block GASOLINE_PETROL_ORE = new GenericBlock(Material.IRON, CreativeTabs.BUILDING_BLOCKS).setUnlocalizedName("gasoline_petrol_ore");
	    public static final Block AMETHYST_BLOCK = new GenericBlock(Material.IRON, CreativeTabs.BUILDING_BLOCKS).setUnlocalizedName("amethyst_block");
	    public static final Block RUBY_BLOCK = new GenericBlock(Material.IRON, CreativeTabs.BUILDING_BLOCKS).setUnlocalizedName("ruby_ore");
	    public static final Block TIGERS_EYE_BLOCK = new GenericBlock(Material.IRON, CreativeTabs.BUILDING_BLOCKS).setUnlocalizedName("tigers_eye_block");
	    public static final Block TITANIUM_BLOCK = new GenericBlock(Material.IRON, CreativeTabs.BUILDING_BLOCKS).setUnlocalizedName("titanium_block");
	    public static final Block URANIUM_BLOCK = new GenericBlock(Material.IRON, CreativeTabs.BUILDING_BLOCKS).setUnlocalizedName("uranium_block");
	    public static final Block ALUMINIUM_BLOCK = new GenericBlock(Material.IRON, CreativeTabs.BUILDING_BLOCKS).setUnlocalizedName("aluminium_block");


	
	
	

	
	    public LiteModFeatureCreepMC()
	    {
	    }
	

	/**
	 * init() is called very early in the initialisation cycle, before the game is fully initialised, this
	 * means that it is important that your mod does not interact with the game in any way at this point.
	 * 
	 * @see com.mumfrey.liteloader.LiteMod#init(java.io.File)
	 */
	
	
	 
	    public static final ResourceLocation AIR_ID = new ResourceLocation("air");

	 //   public static final RegistryNamespacedDefaultedByKey<ResourceLocation, Block> BLOCK_REGISTRY = new RegistryNamespacedDefaultedByKey<ResourceLocation, Block>(AIR_ID);

	//    public static final RegistryNamespaced<ResourceLocation, Item> ITEM_REGISTRY = new RegistryNamespaced<ResourceLocation, Item>();

	    
	   public static void registerBlock(int id, ResourceLocation textualID, Block block_)
	    {
		   Block.REGISTRY.register(id, textualID, block_);
	    }
	
	   public static void registerBlock(int id, String textualID, Block block_)
	    {
	        registerBlock(id, new ResourceLocation(textualID), block_);
	    }
	   
	   


	    /**
	     * Register the given Item as the ItemBlock for the given Block.
	     */
	


	    public static void registerItem(int id, String textualID, Item itemIn)
	    {
	        registerItem(id, new ResourceLocation(textualID), itemIn);
	    }

	    public static void registerItem(int id, ResourceLocation textualID, Item itemIn)
	    {
	    	Item.REGISTRY.register(id, textualID, itemIn);
	    }
	   
	  public static final Map<Block, Item> BLOCK_TO_ITEM = Maps.<Block, Item>newHashMap();

	    
	    private static void registerItemBlock(Block blockIn)
	    {
	        registerItemBlock(blockIn, new ItemBlock(blockIn));
	    }

	    /**
	     * Register the given Item as the ItemBlock for the given Block.
	     */
	    protected static void registerItemBlock(Block blockIn, Item itemIn)
	    {
	        registerItem(Block.getIdFromBlock(blockIn), Block.REGISTRY.getNameForObject(blockIn), itemIn);
	        BLOCK_TO_ITEM.put(blockIn, itemIn);
	    }
	    
	    
	    
	   
	    public static void initilise(File configPath)
	{
		// The key binding declared above won't do anything unless we register it, ModUtilties provides 
		// a convenience method for this
		
		// TODO Auto-generated method stub
	     //   Block.register(new ModelIdentifier("amethyst_block"), AMETHYST_BLOCK);
	       
		
		

	    	
	    	
		registerBlock(MinBlockID + 110, "featurecreep:amethyst_block", AMETHYST_BLOCK);
      //  registerItem(Block.getIdFromBlock(AMETHYST_BLOCK), Block.REGISTRY.getNameForObject(AMETHYST_BLOCK), AMETHYST_BLOCK);
        registerItem(Block.getIdFromBlock(AMETHYST_BLOCK), "featurecreep:amethyst_block", new ItemBlock(AMETHYST_BLOCK).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));

		
		registerBlock(MinBlockID + 109, "featurecreep:ruby_block", RUBY_BLOCK);
        registerItem(Block.getIdFromBlock(RUBY_BLOCK), "featurecreep:ruby_block", new ItemBlock(RUBY_BLOCK));

		registerBlock(MinBlockID + 218, "featurecreep:tigers_eye_block", TIGERS_EYE_BLOCK);
//        registerItemBlock(TIGERS_EYE_BLOCK);
        registerItem(Block.getIdFromBlock(TIGERS_EYE_BLOCK), "featurecreep:tigers_eye_block", new ItemBlock(TIGERS_EYE_BLOCK));


		registerBlock(MinBlockID + 108, "featurecreep:titanium_block", TITANIUM_BLOCK);
        registerItemBlock(TITANIUM_BLOCK);

		registerBlock(1601, "featurecreep:aluminium_block", ALUMINIUM_BLOCK);
        registerItemBlock(ALUMINIUM_BLOCK);

		
		registerBlock(MinBlockID + 107, "featurecreep:uranium_block", URANIUM_BLOCK);
        registerItemBlock(URANIUM_BLOCK);

		
		registerBlock(MinBlockID + 103, "featurecreep:amethyst_ore", AMETHYST_ORE);
        registerItemBlock(AMETHYST_ORE);

		
		registerBlock(MinBlockID + 104, "featurecreep:ruby_ore", RUBY_ORE);
        registerItemBlock(RUBY_ORE);

		
		registerBlock(MinBlockID + 101, "featurecreep:uranium_ore", URANIUM_ORE);
        registerItemBlock(URANIUM_ORE);

		
		registerBlock(MinBlockID + 102, "featurecreep:titanium_ore", TITANIUM_ORE);
        registerItemBlock(TITANIUM_ORE);

		
		registerBlock(MinBlockID + 100, "featurecreep:salt_ore", SALT_ORE);
        registerItemBlock(SALT_ORE);

		
		registerBlock(1607, "featurecreep:tigers_eye_ore", TIGERS_EYE_ORE);
        registerItemBlock(TIGERS_EYE_ORE);

		
		registerBlock(1606, "featurecreep:aluminium_ore", ALUMINIUM_ORE);
        registerItemBlock(ALUMINIUM_ORE);

		
		registerBlock(1605, "featurecreep:unprocessed_oil_ore", UNPROCESSED_OIL_ORE);
        registerItemBlock(UNPROCESSED_OIL_ORE);

		
		registerBlock(1615, "featurecreep:gasoline_petrol_ore", GASOLINE_PETROL_ORE);
        registerItemBlock(GASOLINE_PETROL_ORE);


	    
	        
	        //Registry.Registry.register(new ModelIdentifier("featurecreep:amethyst_block"), AMETHYST_BLOCK);

	       
		

	    	
			
			//method_73186(new Identifier("featurecreep:amethyst"), AMETHYST);
	
	//		registerItem(MinItemID + 260, new ResourceLocation ("featurecreep", "amethyst"), AMETHYST);
			registerItem(MinItemID + 270, new ResourceLocation ("featurecreep", "ruby"), RUBY);
			registerItem(MinItemID + 358, new ResourceLocation ("featurecreep", "tigers_eye"), TIGERS_EYE);

			registerItem(MinItemID + 151, new ResourceLocation ("featurecreep", "titanium_ingot"), TITANIUM);
			registerItem(MinItemID + 153, new ResourceLocation ("featurecreep", "titanium_nugget"), TITANIUM_NUGGET);
			registerItem(MinItemID + 152, new ResourceLocation ("featurecreep", "uranium_ingot"), URANIUM);
			registerItem(MinItemID + 150, new ResourceLocation ("featurecreep", "uranium_nugget"), URANIUM_NUGGET);
			registerItem(MinItemID + 1602, new ResourceLocation ("featurecreep", "aluminium_ingot"), ALUMINIUM);
			registerItem(6028, new ResourceLocation ("featurecreep", "oil"), Oil);
			registerItem(6029, new ResourceLocation ("featurecreep", "mobzilla_scale"), MobzillaScale);
			registerItem(MinItemID + 453, new ResourceLocation ("featurecreep", "queen_scale"), QueenScale);
			registerItem(MinItemID + 156, new ResourceLocation ("featurecreep", "moth_scale"), MothScale);

			registerItem(6030, new ResourceLocation ("featurecreep", "tin_ingot"), TIN_INGOT);
			registerItem(6031, new ResourceLocation ("featurecreep", "silver_ingot"), SILVER_INGOT);
			registerItem(6032, new ResourceLocation ("featurecreep", "platinum_ingot"), PLATINUM_INGOT);
			registerItem(6033, new ResourceLocation ("featurecreep", "copper_ingot"), COPPER_INGOT);


			registerItem(MinItemID + 348, new ResourceLocation ("featurecreep", "pink_tourmaline_ingot"), PINK_TOURMALINE);
			registerItem(MinItemID + 157, new ResourceLocation ("featurecreep", "lava_eel"), LAVA_EEL);
			registerItem(MinItemID + 255, new ResourceLocation ("featurecreep", "peacock_feather"), PEACKOCK_FEATHER);
		//	registerItem(MinItemID + 240, new ResourceLocation ("featurecreep", "thunder_staff"), THUNDER_STAFF);


			registerItem(6072, new ResourceLocation ("featurecreep", "sapphire"), SAPPHIRE);
			registerItem(6073, new ResourceLocation ("featurecreep", "steel_ingot"), STEEL_INGOT);
			registerItem(MinItemID + 178, new ResourceLocation ("featurecreep", "salt"), Salt);
			registerItem(6084, new ResourceLocation ("featurecreep", "gasoline_petrol"), GASOLINE_PETROL);
			registerItem(MinItemID + 237, new ResourceLocation ("featurecreep", "miners_dream"), MINERS_DREAM);
			registerItem(6085, new ResourceLocation ("featurecreep", "large_miners_dream"), LARGE_MINERS_DREAM);


			registerItem(MinItemID + 190, new ResourceLocation ("featurecreep", "raw_bacon"), RAW_BACON);
			registerItem(MinItemID + 189, new ResourceLocation ("featurecreep", "cooked_bacon"), COOKED_BACON);
			registerItem(MinItemID + 188, new ResourceLocation ("featurecreep", "butter_candy"), BUTTER_CANDY);
			registerItem(MinItemID + 208, new ResourceLocation ("featurecreep", "crystal_apple"), CRYSTAL_APPLE);
			registerItem(MinItemID + 485, new ResourceLocation ("featurecreep", "love_food"), LOVE_FOOD);
			registerItem(MinItemID + 179, new ResourceLocation ("featurecreep", "popcorn"), POPCORN);
			registerItem(MinItemID + 183, new ResourceLocation ("featurecreep", "butter_food"), BUTTER_FOOD);
			registerItem(MinItemID + 187, new ResourceLocation ("featurecreep", "corn_dog"), CORN_DOG);
			registerItem(MinItemID + 186, new ResourceLocation ("featurecreep", "cooked_corn_dog"), COOKED_CORN_DOG);
			registerItem(MinItemID + 479, new ResourceLocation ("featurecreep", "raw_crab_meat"), RAW_CRAB_MEAT);
			registerItem(MinItemID + 480, new ResourceLocation ("featurecreep", "cooked_crab_meat"), COOKED_CRAB_MEAT);
			registerItem(MinItemID + 205, new ResourceLocation ("featurecreep", "cheese"), CHEESE);
			registerItem(MinItemID + 200, new ResourceLocation ("featurecreep", "salad"), SALAD);
			registerItem(MinItemID + 201, new ResourceLocation ("featurecreep", "blt"), BLT);
			registerItem(MinItemID + 481, new ResourceLocation ("featurecreep", "crab_patty"), CRAB_PATTY);
			registerItem(MinItemID + 236, new ResourceLocation ("featurecreep", "magic_apple"), MAGIC_APPLE);
			registerItem(MinItemID + 203, new ResourceLocation ("featurecreep", "peach"), PEACH);
			registerItem(MinItemID + 206, new ResourceLocation ("featurecreep", "raw_peacock"), RAW_PEACOCK);
			registerItem(MinItemID + 207, new ResourceLocation ("featurecreep", "coocked_peacock"), COOCKED_PEACOCK);
			registerItem(MinItemID + 192, new ResourceLocation ("featurecreep", "blue_fish"), BLUE_FISH);

			
			registerItem(MinItemID + 180, new ResourceLocation ("featurecreep", "buttered_popcorn"), BUTTERED_POPCORN);
			registerItem(6034, new ResourceLocation ("featurecreep", "salted_popcorn"), SALTED_POPCORN);
			registerItem(MinItemID + 181, new ResourceLocation ("featurecreep", "buttered_and_salted_popcorn"), BUTTERED_AND_SALTED_POPCORN);
			registerItem(MinItemID + 202, new ResourceLocation ("featurecreep", "cherry"), CHERRY);
			registerItem(MinItemID + 185, new ResourceLocation ("featurecreep", "corn"), CORN);
			registerItem(MinItemID + 182, new ResourceLocation ("featurecreep", "popcorn_bag"), POPCORN_BAG);
			registerItem(MinItemID + 224, new ResourceLocation ("featurecreep", "quinoa"), QUINOA);
			registerItem(MinItemID + 199, new ResourceLocation ("featurecreep", "radish"), RADISH);
			registerItem(MinItemID + 209, new ResourceLocation ("featurecreep", "rice"), RICE);
			registerItem(MinItemID + 194, new ResourceLocation ("featurecreep", "rock_fish"), ROCK_FISH);
			registerItem(MinItemID + 175, new ResourceLocation ("featurecreep", "fire_fish"), FIRE_FISH);
			registerItem(MinItemID + 177, new ResourceLocation ("featurecreep", "spark_fish"), SPARK_FISH);
			registerItem(MinItemID + 191, new ResourceLocation ("featurecreep", "green_fish"), GREEN_FISH);
			registerItem(MinItemID + 196, new ResourceLocation ("featurecreep", "grey_fish"), GREY_FISH);
			registerItem(MinItemID + 193, new ResourceLocation ("featurecreep", "pink_fish"), PINK_FISH);
			registerItem(MinItemID + 176, new ResourceLocation ("featurecreep", "sun_fish"), SUN_FISH);
			registerItem(MinItemID + 184, new ResourceLocation ("featurecreep", "strawberry"), STRAWBERRY);
			registerItem(MinItemID + 198, new ResourceLocation ("featurecreep", "lettuce"), LETTUCE);
			registerItem(MinItemID + 197, new ResourceLocation ("featurecreep", "tomato"), TOMATO);
			registerItem(MinItemID + 195, new ResourceLocation ("featurecreep", "wood_fish"), WOOD_FISH);


			
			registerItem(6035, new ResourceLocation ("featurecreep", "raw_moose_meat"), RAW_MOOSE_MEAT);
			registerItem(6036, new ResourceLocation ("featurecreep", "dead_bug"), DEAD_BUG);
			registerItem(6037, new ResourceLocation ("featurecreep", "magic_frog_of_strength"), MAGIC_FROG_OF_STRENGTH);
			registerItem(6038, new ResourceLocation ("featurecreep", "magic_frog_of_weakness"), MAGIC_FROG_OF_WEAKNESS);
			registerItem(6039, new ResourceLocation ("featurecreep", "magic_frog_of_speed"), MAGIC_FROG_OF_SPEED);
			registerItem(6040, new ResourceLocation ("featurecreep", "magic_frog_of_slowness"), MAGIC_FROG_OF_SLOWNESS);
			registerItem(6041, new ResourceLocation ("featurecreep", "magic_frog_of_regeneration"), MAGIC_FROG_OF_REGENERATION);
			registerItem(6042, new ResourceLocation ("featurecreep", "magic_frog_of_poison"), MAGIC_FROG_OF_POISON);
			registerItem(6043, new ResourceLocation ("featurecreep", "magic_frog_of_morph"), MAGIC_FROG_OF_MORPH);
			registerItem(6044, new ResourceLocation ("featurecreep", "magic_frog_of_confusion"), MAGIC_FROG_OF_CONFUSION);
			registerItem(6045, new ResourceLocation ("featurecreep", "cooked_moose_meat"), COOKED_MOOSE_MEAT);
			registerItem(6046, new ResourceLocation ("featurecreep", "candy_cane"), CANDY_CANE);
			registerItem(6047, new ResourceLocation ("featurecreep", "golden_bread"), GOLDEN_BREAD);
			registerItem(6048, new ResourceLocation ("featurecreep", "golden_chicken"), GOLDEN_CHICKEN);
			registerItem(6049, new ResourceLocation ("featurecreep", "golden_tropical_fish"), GOLDEN_TROPICAL_FISH);
			registerItem(6050, new ResourceLocation ("featurecreep", "golden_cod"), GOLDEN_COD);
			registerItem(6051, new ResourceLocation ("featurecreep", "golden_porkchop"), GOLDEN_PORKCHOP);
			registerItem(6052, new ResourceLocation ("featurecreep", "watermelon_slice"), WATERMELON_SLICE);

			
			//Food


			registerItem(6053, new ResourceLocation ("featurecreep", "golden_mushroom_stew"), GOLDEN_MUSHROOM_STEW);
			registerItem(6054, new ResourceLocation ("featurecreep", "golden_steak"), GOLDEN_STEAK);
			registerItem(6055, new ResourceLocation ("featurecreep", "golden_cookie"), GOLDEN_COOKIE);
			registerItem(6056, new ResourceLocation ("featurecreep", "golden_potato"), GOLDEN_POTATO);
			registerItem(6057, new ResourceLocation ("featurecreep", "golden_pumpkin_pie"), GOLDEN_PUMPKIN_PIE);
			registerItem(6058, new ResourceLocation ("featurecreep", "golden_rotton_flesh"), GOLDEN_ROTTON_FLESH);
			registerItem(6059, new ResourceLocation ("featurecreep", "golden_carrot"), GOLDEN_CARROT);
			registerItem(6060, new ResourceLocation ("featurecreep", "golden_pufferfish"), GOLDEN_PUFFERFISH);
			registerItem(6061, new ResourceLocation ("featurecreep", "golden_salmon"), GOLDEN_SALMON);
			registerItem(6062, new ResourceLocation ("featurecreep", "golden_candycane"), GOLDEN_CANDYCANE);
			registerItem(6063, new ResourceLocation ("featurecreep", "ultimate_apple"), ULTIMATE_APPLE);
			registerItem(6064, new ResourceLocation ("featurecreep", "enchanted_golden_carrot"), ENCHANTED_GOLDEN_CARROT);
			registerItem(6066, new ResourceLocation ("featurecreep", "enchanted_golden_steak"), ENCHANTED_GOLDEN_STEAK);
			registerItem(6067, new ResourceLocation ("featurecreep", "enchanted_golden_cod"), ENCHANTED_GOLDEN_COD);
			registerItem(6068, new ResourceLocation ("featurecreep", "enchanted_golden_cookie"), ENCHANTED_GOLDEN_COOKIE);
			registerItem(6069, new ResourceLocation ("featurecreep", "enchanted_golden_candycane"), ENCHANTED_GOLDEN_CANDYCANE);
			registerItem(6070, new ResourceLocation ("featurecreep", "raddish_stew"), RADDISH_STEW);
			registerItem(6071, new ResourceLocation ("featurecreep", "drinkable_gasoline_petrol"), DRINKABLE_GASOLINE_PETROL);



			
			registerItem(6003, new ResourceLocation ("featurecreep", "optimised_pickaxe"), OPTIMISED_PICKAXE);
			registerItem(6004, new ResourceLocation ("featurecreep", "optimised_shovel"), OPTIMISED_SHOVEL);

			registerItem(MinItemID + 285, new ResourceLocation ("featurecreep", "emerald_helmet"), EMERALD_HELMET);
			registerItem(MinItemID + 286, new ResourceLocation ("featurecreep", "emerald_chestplate"), EMERALD_CHESTPLATE);
			registerItem(MinItemID + 287, new ResourceLocation ("featurecreep", "emerald_leggings"), EMERALD_LEGGINGS);
			registerItem(MinItemID + 288, new ResourceLocation ("featurecreep", "emerald_boots"), EMERALD_BOOTS);
			registerItem(MinItemID + 280, new ResourceLocation ("featurecreep", "emerald_sword"), EMERALD_SWORD);
			registerItem(MinItemID + 281, new ResourceLocation ("featurecreep", "emerald_pickaxe"), EMERALD_PICKAXE);
			registerItem(MinItemID + 282, new ResourceLocation ("featurecreep", "emerald_shovel"), EMERALD_SHOVEL);
			registerItem(MinItemID + 283, new ResourceLocation ("featurecreep", "emerald_hoe"), EMERALD_HOLE);
			registerItem(MinItemID + 284, new ResourceLocation ("featurecreep", "emerald_axe"), EMERALD_AXE);

			registerItem(MinItemID + 266, new ResourceLocation ("featurecreep", "amethyst_helmet"), AMETHYST_HELMET);
			registerItem(MinItemID + 267, new ResourceLocation ("featurecreep", "amethyst_chestplate"), AMETHYST_CHESTPLATE);
			registerItem(MinItemID + 268, new ResourceLocation ("featurecreep", "amethyst_leggings"), AMETHYST_LEGGINGS);
			registerItem(MinItemID + 269, new ResourceLocation ("featurecreep", "amethyst_boots"), AMETHYST_BOOTS);
			registerItem(MinItemID + 261, new ResourceLocation ("featurecreep", "amethyst_sword"), AMETHYST_SWORD);
			registerItem(MinItemID + 262, new ResourceLocation ("featurecreep", "amethyst_pickaxe"), AMETHYST_PICKAXE);
			registerItem(MinItemID + 263, new ResourceLocation ("featurecreep", "amethyst_shovel"), AMETHYST_SHOVEL);
			registerItem(MinItemID + 264, new ResourceLocation ("featurecreep", "amethyst_hoe"), AMETHYST_HOE);
			registerItem(MinItemID + 265, new ResourceLocation ("featurecreep", "amethyst_axe"), AMETHYST_AXE);

			
			registerItem(6080, new ResourceLocation ("featurecreep", "sapphire_helmet"), SAPPHIRE_HELMET);
			registerItem(6081, new ResourceLocation ("featurecreep", "sapphire_chestplate"), SAPPHIRE_CHESTPLATE);
			registerItem(6082, new ResourceLocation ("featurecreep", "sapphire_leggings"), SAPPHIRE_LEGGINGS);
			registerItem(6083, new ResourceLocation ("featurecreep", "sapphire_boots"), SAPPHIRE_BOOTS);
			registerItem(6075, new ResourceLocation ("featurecreep", "sapphire_sword"), SAPPHIRE_SWORD);
			registerItem(6076, new ResourceLocation ("featurecreep", "sapphire_pickaxe"), SAPPHIRE_PICKAXE);
			registerItem(6077, new ResourceLocation ("featurecreep", "sapphire_shovel"), SAPPHIRE_SHOVEL);
			registerItem(6078, new ResourceLocation ("featurecreep", "sapphire_hoe"), SAPPHIRE_HOE);
			registerItem(6079, new ResourceLocation ("featurecreep", "sapphire_axe"), SAPPHIRE_AXE);

			

			registerItem(MinItemID + 354, new ResourceLocation ("featurecreep", "tigers_eye_helmet"), TIGERS_EYE_HELMET);
			registerItem(MinItemID + 355, new ResourceLocation ("featurecreep", "tigers_eye_chestplate"), TIGERS_EYE_CHESTPLATE);
			registerItem(MinItemID + 356, new ResourceLocation ("featurecreep", "tigers_eye_leggings"), TIGERS_EYE_LEGGINGS);
			registerItem(MinItemID + 357, new ResourceLocation ("featurecreep", "tigers_eye_boots"), TIGERS_EYE_BOOTS);
			registerItem(MinItemID + 349, new ResourceLocation ("featurecreep", "tigers_eye_sword"), TIGERS_EYE_SWORD);
			registerItem(MinItemID + 350, new ResourceLocation ("featurecreep", "tigers_eye_pickaxe"), TIGERS_EYE_PICKAXE);
			registerItem(MinItemID + 351, new ResourceLocation ("featurecreep", "tigers_eye_shovel"), TIGERS_EYE_SHOVEL);
			registerItem(MinItemID + 352, new ResourceLocation ("featurecreep", "tigers_eye_hoe"), TIGERS_EYE_HOE);
			registerItem(MinItemID + 353, new ResourceLocation ("featurecreep", "tigers_eye_axe"), TIGERS_EYE_AXE);

			
			
			
			registerItem(MinItemID + 276, new ResourceLocation ("featurecreep", "ruby_helmet"), RUBY_HELMET);
			registerItem(MinItemID + 277, new ResourceLocation ("featurecreep", "ruby_chestplate"), RUBY_CHESTPLATE);
			registerItem(MinItemID + 278, new ResourceLocation ("featurecreep", "ruby_leggings"), RUBY_LEGGINGS);
			registerItem(MinItemID + 279, new ResourceLocation ("featurecreep", "ruby_boots"), RUBY_BOOTS);
			registerItem(MinItemID + 271, new ResourceLocation ("featurecreep", "ruby_sword"), RUBY_SWORD);
			registerItem(MinItemID + 272, new ResourceLocation ("featurecreep", "ruby_pickaxe"), RUBY_PICKAXE);
			registerItem(MinItemID + 273, new ResourceLocation ("featurecreep", "ruby_shovel"), RUBY_SHOVEL);
			registerItem(MinItemID + 274, new ResourceLocation ("featurecreep", "ruby_hoe"), RUBY_HOE);
			registerItem(MinItemID + 275, new ResourceLocation ("featurecreep", "ruby_axe"), RUBY_AXE);

			

			registerItem(MinItemID + 309, new ResourceLocation ("featurecreep", "ultimate_helmet"), ULTIMATE_HELMET);
			registerItem(MinItemID + 310, new ResourceLocation ("featurecreep", "ultimate_chestplate"), ULTIMATE_CHESTPLATE);
			registerItem(MinItemID + 311, new ResourceLocation ("featurecreep", "ultimate_leggings"), ULTIMATE_LEGGINGS);
			registerItem(MinItemID + 312, new ResourceLocation ("featurecreep", "ultimate_boots"), ULTIMATE_BOOTS);
			registerItem(MinItemID + 302, new ResourceLocation ("featurecreep", "ultimate_sword"), ULTIMATE_SWORD);
			registerItem(MinItemID + 305, new ResourceLocation ("featurecreep", "ultimate_pickaxe"), ULTIMATE_PICKAXE);
			registerItem(MinItemID + 306, new ResourceLocation ("featurecreep", "ultimate_shovel"), ULTIMATE_SHOVEL);
			registerItem(MinItemID + 307, new ResourceLocation ("featurecreep", "ultimate_hoe"), ULTIMATE_HOE);
			registerItem(MinItemID + 308, new ResourceLocation ("featurecreep", "ultimate_axe"), ULTIMATE_AXE);

			
			registerItem(6008, new ResourceLocation ("featurecreep", "tin_helmet"), TIN_HELMET);
			registerItem(6009, new ResourceLocation ("featurecreep", "tin_chestplate"), TIN_CHESTPLATE);
			registerItem(6010, new ResourceLocation ("featurecreep", "tin_leggings"), TIN_LEGGINGS);
			registerItem(6011, new ResourceLocation ("featurecreep", "tin_boots"), TIN_BOOTS);

			registerItem(6012, new ResourceLocation ("featurecreep", "copper_helmet"), COPPER_HELMET);
			registerItem(6013, new ResourceLocation ("featurecreep", "copper_chestplate"), COPPER_CHESTPLATE);
			registerItem(6014, new ResourceLocation ("featurecreep", "copper_leggings"), COPPER_LEGGINGS);
			registerItem(6015, new ResourceLocation ("featurecreep", "copper_boots"), COPPER_BOOTS);


			registerItem(6016, new ResourceLocation ("featurecreep", "platinum_helmet"), PLATINUM_HELMET);
			registerItem(6017, new ResourceLocation ("featurecreep", "platinum_chestplate"), PLATINUM_CHESTPLATE);
			registerItem(6018, new ResourceLocation ("featurecreep", "platinum_leggings"), PLATNIUM_LEGGINGS);
			registerItem(6019, new ResourceLocation ("featurecreep", "platinum_boots"), PLATINUM_BOOTS);

			registerItem(6020, new ResourceLocation ("featurecreep", "silver_helmet"), SILVER_HELMET);
			registerItem(6021, new ResourceLocation ("featurecreep", "silver_chestplate"), SILVER_CHESTPLATE);
			registerItem(6022, new ResourceLocation ("featurecreep", "silver_leggings"), SILVER_LEGGINGS);
			registerItem(6023, new ResourceLocation ("featurecreep", "silver_boots"), SILVER_BOOTS);
			registerItem(6024, new ResourceLocation ("featurecreep", "aluminium_helmet"), ALUMINIUM_HELMET);
			registerItem(6025, new ResourceLocation ("featurecreep", "aluminium_chestplate"), ALUMINIUM_CHESTPLATE);
			registerItem(6026, new ResourceLocation ("featurecreep", "aluminium_leggings"), ALUMINIUM_LEGGINGS);
			registerItem(6027, new ResourceLocation ("featurecreep", "aluminium_boots"), ALUMINIUM_BOOTS);


			registerItem(MinItemID + 290, new ResourceLocation ("featurecreep", "experience_helmet"), EXPERIENCE_HELMET);
			registerItem(MinItemID + 291, new ResourceLocation ("featurecreep", "experience_chestplate"), EXPERIENCE_CHESTPLATE);
			registerItem(MinItemID + 292, new ResourceLocation ("featurecreep", "experience_leggings"), EXPERIENCE_LEGGINGS);
			registerItem(MinItemID + 293, new ResourceLocation ("featurecreep", "experience_boots"), EXPERIENCE_BOOTS);

			registerItem(MinItemID + 402, new ResourceLocation ("featurecreep", "royal_guardian_helmet"), ROYAL_GUARDIAN_HELMET);
			registerItem(MinItemID + 403, new ResourceLocation ("featurecreep", "royal_guardian_chestplate"), ROYAL_GUARDIAN_CHESTPLATE);
			registerItem(MinItemID + 404, new ResourceLocation ("featurecreep", "royal_guardian_leggings"), ROYAL_GUARDIAN_LEGGINGS);
			registerItem(MinItemID + 405, new ResourceLocation ("featurecreep", "royal_guardian_boots"), ROYAL_GUARDIAN_BOOTS);

			registerItem(MinItemID + 450, new ResourceLocation ("featurecreep", "lapis_block_helmet"), LAPIS_LAZUI_HELMET);
			registerItem(MinItemID + 451, new ResourceLocation ("featurecreep", "lapis_block_chestplate"), LAPIS_LAZUI_CHESTPLATE);
			registerItem(MinItemID + 452, new ResourceLocation ("featurecreep", "lapis_block_leggings"), LAPIS_LAZUI_LEGGINGS);
			registerItem(MinItemID + 453, new ResourceLocation ("featurecreep", "lapis_block_boots"), LAPIS_LAZUI_BOOTS);

	
			registerItem(MinItemID + 298, new ResourceLocation ("featurecreep", "lave_eel_helmet"), LAVA_EEL_HELMET);
			registerItem(MinItemID + 299, new ResourceLocation ("featurecreep", "lava_eel_chestplate"), LAVA_EEL_CHESTPLATE);
			registerItem(MinItemID + 300, new ResourceLocation ("featurecreep", "lava_eel_leggings"), LAVA_EEL_LEGGINGS);
			registerItem(MinItemID + 301, new ResourceLocation ("featurecreep", "lava_eel_boots"), LAVA_EEL_BOOTS);

			registerItem(MinItemID + 359, new ResourceLocation ("featurecreep", "peacock_feather_helmet"), PEACOCK_FEATHER_HELMET);
			registerItem(MinItemID + 360, new ResourceLocation ("featurecreep", "peacock_feather_chestplate"), PEACOCK_FEATHER_CHESTPLATE);
			registerItem(MinItemID + 361, new ResourceLocation ("featurecreep", "peacock_feather_leggings"), PEACOCK_FEATHER_LEGGINGS);
			registerItem(MinItemID + 362, new ResourceLocation ("featurecreep", "peacock_feather_boots"), PEACOCK_FEATHER_BOOTS);


			registerItem(MinItemID + 344, new ResourceLocation ("featurecreep", "pink_tourmaline_helmet"), PINK_TOURMALINE_HELMET);
			registerItem(MinItemID + 345, new ResourceLocation ("featurecreep", "pink_tourmaline_chestplate"), PINK_TOURMALINE_CHESTPLATE);
			registerItem(MinItemID + 346, new ResourceLocation ("featurecreep", "pink_tourmaline_leggings"), PINK_TOURMALINE_LEGGINGS);
			registerItem(MinItemID + 347, new ResourceLocation ("featurecreep", "pink_tourmaline_boots"), PINK_TOURMALINE_BOOTS);

			registerItem(MinItemID + 294, new ResourceLocation ("featurecreep", "moth_scale_helmet"), MOTH_SCALE_HELMET);
			registerItem(MinItemID + 295, new ResourceLocation ("featurecreep", "moth_scale_chestplate"), MOTH_SCALE_CHESTPLATE);
			registerItem(MinItemID + 296, new ResourceLocation ("featurecreep", "moth_scale_leggings"), MOTH_SCALE_LEGGINGS);
			registerItem(MinItemID + 297, new ResourceLocation ("featurecreep", "moth_scale_boots"), MOTH_SCALE_BOOTS);


			registerItem(MinItemID + 454, new ResourceLocation ("featurecreep", "queen_scale_helmet"), QUEEN_SCALE_HELMET);
			registerItem(MinItemID + 455, new ResourceLocation ("featurecreep", "queen_scale_chestplate"), QUEEN_SCALE_CHESTPLATE);
			registerItem(MinItemID + 456, new ResourceLocation ("featurecreep", "queen_scale_leggings"), QUEEN_SCALE_LEGGINGS);
			registerItem(MinItemID + 457, new ResourceLocation ("featurecreep", "queen_scale_boots"), QUEEN_SCALE_BOOTS);

			registerItem(MinItemID + 395, new ResourceLocation ("featurecreep", "mobzilla_scale_helmet"), MOBZILLA_SCALE_HELMET);
			registerItem(MinItemID + 396, new ResourceLocation ("featurecreep", "mobzilla_scale_chestplate"), MOBZILLA_SCALE_CHESTPLATE);
			registerItem(MinItemID + 397, new ResourceLocation ("featurecreep", "mobzilla_scale_leggings"), MOBZILLA_SCALE_LEGGINGS);
			registerItem(MinItemID + 398, new ResourceLocation ("featurecreep", "mobzilla_scale_boots"), MOBZILLA_SCALE_BOOTS);	
			
		//	method_73186(new Identifier("featurecreep:ruby"), RUBY);
			//Registry.ITEM.register(AMETHYST_BLOCK_ITEM, ItemGroup.BUILDING_BLOCKS);
		
		
			
			
			
			
			/*

	     method_73186(new Identifier("featurecreep", "amethyst"), AMETHYST);
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
	     method_73186(new Identifier("featurecreep", "amethyst_helmet"), AMETHYST_HELMET);
	     method_73186(new Identifier("featurecreep", "amethyst_chestplate"), AMETHYST_CHESTPLATE);
	     method_73186(new Identifier("featurecreep", "amethyst_leggings"), AMETHYST_LEGGINGS);
	     method_73186(new Identifier("featurecreep", "amethyst_boots"), AMETHYST_BOOTS);
	     method_73186(new Identifier("featurecreep", "amethyst_sword"), AMETHYST_SWORD);
	     method_73186(new Identifier("featurecreep", "amethyst_pickaxe"), AMETHYST_PICKAXE);
	     method_73186(new Identifier("featurecreep", "amethyst_shovel"), AMETHYST_SHOVEL);
	     method_73186(new Identifier("featurecreep", "amethyst_hoe"), AMETHYST_HOE);
	     method_73186(new Identifier("featurecreep", "amethyst_axe"), AMETHYST_AXE);
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
*/
		
	}
































}