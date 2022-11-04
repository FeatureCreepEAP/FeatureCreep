package featurecreep.legacy;


import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = FeatureCreepMC.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FCItems {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FeatureCreepMC.MODID);

/*	
	public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> TIGERS_EYE = ITEMS.register("tigers_eye", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> TITANIUM_NUGGET = ITEMS.register("titanium_nugget", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> URANIUM_INGOT = ITEMS.register("uranium_ingot", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> URANIUM_NUGGET = ITEMS.register("uranium_nugget", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> ALUMINUM_INGOT = ITEMS.register("aluminum_ingot", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> COPPER_INGOT = ITEMS.register("copper_ingot", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> PINK_TOURMALINE_INGOT = ITEMS.register("pink_tourmaline_ingot", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> PINK_TOURMALINE_NUGGET = ITEMS.register("pink_tourmaline_nugget", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));

	*/
	
	
	
	//public static final Item MINERS_DREAM = new MinersDream("miners_dream", -5, 5, 0, 5, 0, 50);
	//public static final Item LARGE_MINERS_DREAM = new MinersDream("large_miners_dream", -250, 250, -250, 250, -50, 250);
	public static final RegistryObject<MinersDream> MINERS_DREAM = ITEMS.register("miners_dream", () -> new MinersDream(new Item.Settings().group(ItemGroup.MATERIALS), -5, 5, 0, 5, 0, 50));
	public static final RegistryObject<MinersDream> LARGE_MINERS_DREAM = ITEMS.register("large_miners_dream", () -> new MinersDream(new Item.Settings().group(ItemGroup.MATERIALS), -250, 250, -250, 250, -50, 250));

	
	
//	public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> TIGERS_EYE = ITEMS.register("tigers_eye", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> TITANIUM = ITEMS.register("titanium_ingot", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> TITANIUM_NUGGET = ITEMS.register("titanium_nugget", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> URANIUM = ITEMS.register("uranium_ingot", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> URANIUM_NUGGET = ITEMS.register("uranium_nugget", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> ALUMINIUM = ITEMS.register("aluminium_ingot", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> COPPER_INGOT = ITEMS.register("copper_ingot", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> PINK_TOURMALINE = ITEMS.register("pink_tourmaline_ingot", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> PINK_TOURMALINE_NUGGET = ITEMS.register("pink_tourmaline_nugget", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));

	
	public static final RegistryObject<Item> Oil = ITEMS.register("oil", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> Salt = ITEMS.register("salt", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> MobzillaScale = ITEMS.register("mobzilla_scale", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> QueenScale = ITEMS.register("queen_scale", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> MothScale = ITEMS.register("moth_scale", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> PLATINUM_INGOT = ITEMS.register("platinum_ingot", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> LAVA_EEL = ITEMS.register("lava_eel", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> PEACKOCK_FEATHER = ITEMS.register("peackock_feather", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> GASOLINE_PETROL = ITEMS.register("gasoline_petrol", () -> new Item(new Item.Settings().group(ItemGroup.MATERIALS)));

	
    
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
//	par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//    par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 2));
//par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 4));
//par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.SPEED, 600, 2));
//par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 600, 2));
//par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.HASTE, 600, 2));
	
	
	//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	public static final RegistryObject<Item> RAW_BACON = ITEMS.register("raw_bacon", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_RAW_BACON)));
	public static final RegistryObject<Item> COOKED_BACON = ITEMS.register("cooked_bacon", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_COOKED_BACON)));
	public static final RegistryObject<Item> BUTTER_CANDY = ITEMS.register("butter_candy", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_BUTTER_CANDY)));
	public static final RegistryObject<Item> CRYSTAL_APPLE = ITEMS.register("crystal_apple", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_CRYSTAL_APPLE)));
	public static final RegistryObject<Item> LOVE_FOOD = ITEMS.register("love_food", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_LOVE_FOOD)));
	public static final RegistryObject<Item> POPCORN = ITEMS.register("popcorn", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_POPCORN)));
	public static final RegistryObject<Item> BUTTER_FOOD = ITEMS.register("butter_food", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_BUTTER_FOOD)));
	public static final RegistryObject<Item> CORN_DOG = ITEMS.register("corn_dog", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_CORN_DOG)));
	public static final RegistryObject<Item> COOKED_CORN_DOG = ITEMS.register("cooked_corn_dog", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_COOKED_CORN_DOG)));
	public static final RegistryObject<Item> RAW_CRAB_MEAT = ITEMS.register("raw_crab_meat", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_RAW_CRAB_MEAT)));

	public static final RegistryObject<Item> COOKED_CRAB_MEAT = ITEMS.register("cooked_crab_meat", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_COOKED_CRAB_MEAT)));
	public static final RegistryObject<Item> CHEESE = ITEMS.register("cheese", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_CHEESE)));
	public static final RegistryObject<Item> SALAD = ITEMS.register("salad", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_SALAD)));
	public static final RegistryObject<Item> BLT = ITEMS.register("blt", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_BLT)));
	public static final RegistryObject<Item> CRAB_PATTY = ITEMS.register("crab_patty", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_CRAB_PATTY)));
	public static final RegistryObject<Item> MAGIC_APPLE = ITEMS.register("magic_apple", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_MAGIC_APPLE)));
	public static final RegistryObject<Item> PEACH = ITEMS.register("peach", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_PEACH)));
	public static final RegistryObject<Item> RAW_PEACOCK = ITEMS.register("raw_peacock", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_RAW_PEACOCK)));
	public static final RegistryObject<Item> COOCKED_PEACOCK = ITEMS.register("coocked_peacock", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_COOCKED_PEACOCK)));
	public static final RegistryObject<Item> BLUE_FISH = ITEMS.register("blue_fish", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_BLUE_FISH)));

	
	
	public static final RegistryObject<Item> BUTTERED_POPCORN = ITEMS.register("buttered_popcorn", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_BUTTERED_POPCORN)));
	public static final RegistryObject<Item> SALTED_POPCORN = ITEMS.register("salted_popcorn", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_SALTED_POPCORN)));
	public static final RegistryObject<Item> BUTTERED_AND_SALTED_POPCORN = ITEMS.register("buttered_and_salted_popcorn", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_BUTTERED_AND_SALTED_POPCORN)));
	public static final RegistryObject<Item> CHERRY = ITEMS.register("cherry", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_CHERRY)));
	public static final RegistryObject<Item> CORN = ITEMS.register("corn", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_CORN)));
	public static final RegistryObject<Item> POPCORN_BAG = ITEMS.register("popcorn_bag", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_POPCORN_BAG)));
	public static final RegistryObject<Item> QUINOA = ITEMS.register("quinoa", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_QUINOA)));
	public static final RegistryObject<Item> RADISH = ITEMS.register("radish", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_RADISH)));
	public static final RegistryObject<Item> RICE = ITEMS.register("rice", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_RICE)));
	public static final RegistryObject<Item> ROCK_FISH = ITEMS.register("rock_fish", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_ROCK_FISH)));

	public static final RegistryObject<Item> FIRE_FISH = ITEMS.register("fire_fish", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_FIRE_FISH)));
	public static final RegistryObject<Item> SPARK_FISH = ITEMS.register("spark_fish", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_SPARK_FISH)));
	public static final RegistryObject<Item> GREEN_FISH = ITEMS.register("green_fish", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GREEN_FISH)));
	public static final RegistryObject<Item> GREY_FISH = ITEMS.register("grey_fish", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GREY_FISH)));
	public static final RegistryObject<Item> PINK_FISH = ITEMS.register("pink_fish", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_PINK_FISH)));
	public static final RegistryObject<Item> SUN_FISH = ITEMS.register("sun_fish", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_SUN_FISH)));
	public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_STRAWBERRY)));
	public static final RegistryObject<Item> LETTUCE = ITEMS.register("lettuce", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_LETTUCE)));
	public static final RegistryObject<Item> TOMATO = ITEMS.register("tomato", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_TOMATO)));
	public static final RegistryObject<Item> WOOD_FISH = ITEMS.register("wood_fish", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_WOOD_FISH)));
	public static final RegistryObject<Item> RAW_MOOSE_MEAT = ITEMS.register("raw_moose_meat", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_RAW_MOOSE_MEAT)));

	
	public static final RegistryObject<Item> DEAD_BUG = ITEMS.register("dead_bug", () -> new Item(new Item.Settings().group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> MAGIC_FROG_OF_STRENGTH = ITEMS.register("magic_frog_of_strength", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_MAGIC_FROG_OF_STRENGTH)));
	public static final RegistryObject<Item> MAGIC_FROG_OF_WEAKNESS = ITEMS.register("magic_frog_of_weakness", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_MAGIC_FROG_OF_WEAKNESS)));
	public static final RegistryObject<Item> MAGIC_FROG_OF_SPEED = ITEMS.register("magic_frog_of_speed", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_MAGIC_FROG_OF_SPEED)));
	public static final RegistryObject<Item> MAGIC_FROG_OF_SLOWNESS = ITEMS.register("magic_frog_of_slowness", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_MAGIC_FROG_OF_SLOWNESS)));
	public static final RegistryObject<Item> MAGIC_FROG_OF_REGENERATION = ITEMS.register("magic_frog_of_regeneration", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_MAGIC_FROG_OF_REGENERATION)));
	public static final RegistryObject<Item> MAGIC_FROG_OF_POISON = ITEMS.register("magic_frog_of_poison", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_MAGIC_FROG_OF_POISON)));
	public static final RegistryObject<Item> MAGIC_FROG_OF_MORPH = ITEMS.register("magic_frog_of_morph", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_MAGIC_FROG_OF_MORPH)));
	public static final RegistryObject<Item> MAGIC_FROG_OF_CONFUSION = ITEMS.register("magic_frog_of_confusion", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_MAGIC_FROG_OF_CONFUSION)));
	public static final RegistryObject<Item> COOKED_MOOSE_MEAT = ITEMS.register("cooked_moose_meat", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_COOKED_MOOSE_MEAT)));

	
	public static final RegistryObject<Item> CANDY_CANE = ITEMS.register("candy_cane", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_CANDY_CANE)));
	public static final RegistryObject<Item> GOLDEN_BREAD = ITEMS.register("golden_bread", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_BREAD)));
	public static final RegistryObject<Item> GOLDEN_CHICKEN = ITEMS.register("golden_chicken", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_CHICKEN)));
	public static final RegistryObject<Item> GOLDEN_TROPICAL_FISH = ITEMS.register("golden_tropical_fish", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_TROPICAL_FISH)));
	public static final RegistryObject<Item> GOLDEN_COD = ITEMS.register("golden_cod", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_COD)));
	public static final RegistryObject<Item> GOLDEN_PORKCHOP = ITEMS.register("golden_porkchop", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_PORKCHOP)));
	public static final RegistryObject<Item> WATERMELON_SLICE = ITEMS.register("watermelon_slice", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_WATERMELON_SLICE)));
	public static final RegistryObject<Item> GOLDEN_MUSHROOM_STEW = ITEMS.register("golden_mushroom_stew", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_MUSHROOM_STEW)));
	public static final RegistryObject<Item> GOLDEN_STEAK = ITEMS.register("golden_steak", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_STEAK)));
	public static final RegistryObject<Item> GOLDEN_COOKIE = ITEMS.register("golden_cookie", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_COOKIE)));

	public static final RegistryObject<Item> GOLDEN_POTATO = ITEMS.register("golden_potato", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_POTATO)));
	public static final RegistryObject<Item> GOLDEN_PUMPKIN_PIE = ITEMS.register("golden_pumpkin_pie", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_PUMPKIN_PIE)));
	public static final RegistryObject<Item> GOLDEN_ROTTON_FLESH = ITEMS.register("golden_rotton_flesh", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_ROTTON_FLESH)));
	public static final RegistryObject<Item> GOLDEN_CARROT = ITEMS.register("golden_carrot", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_CARROT)));
	public static final RegistryObject<Item> GOLDEN_PUFFERFISH = ITEMS.register("golden_pufferfish", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_PUFFERFISH)));
	public static final RegistryObject<Item> GOLDEN_SALMON = ITEMS.register("golden_salmon", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_SALMON)));
	public static final RegistryObject<Item> GOLDEN_CANDYCANE = ITEMS.register("golden_candycane", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_CANDYCANE)));
	public static final RegistryObject<Item> ULTIMATE_APPLE = ITEMS.register("ultimate_apple", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_ULTIMATE_APPLE)));
	public static final RegistryObject<Item> ENCHANTED_GOLDEN_CARROT = ITEMS.register("enchanted_golden_carrot", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_ENCHANTED_GOLDEN_CARROT)));
	public static final RegistryObject<Item> ENCHANTED_GOLDEN_STEAK = ITEMS.register("enchanted_golden_steak", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_ENCHANTED_GOLDEN_STEAK)));

	public static final RegistryObject<Item> ENCHANTED_GOLDEN_COD = ITEMS.register("enchanted_golden_cod", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_ENCHANTED_GOLDEN_COD)));
	public static final RegistryObject<Item> ENCHANTED_GOLDEN_COOKIE = ITEMS.register("enchanted_golden_cookie", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_GOLDEN_COOKIE)));
	public static final RegistryObject<Item> ENCHANTED_GOLDEN_CANDYCANE = ITEMS.register("enchanted_golden_candycane", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_ENCHANTED_GOLDEN_CANDYCANE)));
	public static final RegistryObject<Item> RADDISH_STEW = ITEMS.register("raddish_stew", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_RADDISH_STEW)));

	
	
	public static final RegistryObject<Item> DRINKABLE_GASOLINE_PETROL = ITEMS.register("drinkable_gasoline_petrol", () -> new Item(new Item.Settings().group(ItemGroup.FOOD).food(FOOD_DRINKABLE_GASOLINE_PETROL)));
	
	
	
	public static final RegistryObject<SwordItem> RUBY_SWORD = ITEMS.register("ruby_sword", () -> new SwordItem(ToolMaterials.TOOL_RUBY, 0, -2.4F, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<PickaxeItem> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe", () -> new PickaxeItem(ToolMaterials.TOOL_RUBY, 0, 0, new Item.Settings().group(ItemGroup.TOOLS)));
	public static final RegistryObject<ShovelItem> RUBY_SHOVEL = ITEMS.register("ruby_shovel", () -> new ShovelItem(ToolMaterials.TOOL_RUBY, 0, 0, new Item.Settings().group(ItemGroup.TOOLS)));
	public static final RegistryObject<HoeItem> RUBY_HOE = ITEMS.register("ruby_hoe", () -> new HoeItem(ToolMaterials.TOOL_RUBY, 0, 0, new Item.Settings().group(ItemGroup.TOOLS)));
	
	
	//public static final RegistryObject<SwordItem> AMETHYST_SWORD = ITEMS.register("emerald_sword", () -> new SwordItem(ToolMaterials.TOOL_AMETHYST, 0, -2.4F, new Item.Settings().group(ItemGroup.COMBAT)));
	//public static final RegistryObject<PickaxeItem> AMETHYST_PICKAXE = ITEMS.register("emerald_pickaxe", () -> new PickaxeItem(ToolMaterials.TOOL_AMETHYST, 0, 0, new Item.Settings().group(ItemGroup.TOOLS)));
	//public static final RegistryObject<ShovelItem> AMETHYST_SHOVEL = ITEMS.register("emerald_shovel", () -> new ShovelItem(ToolMaterials.TOOL_AMETHYST, 0, 0, new Item.Settings().group(ItemGroup.TOOLS)));
	//public static final RegistryObject<HoeItem> AMETHYST_HOE = ITEMS.register("emerald_hoe", () -> new HoeItem(ToolMaterials.TOOL_AMETHYST, 0, 0, new Item.Settings().group(ItemGroup.TOOLS)));
	
	
	
	
	public static final RegistryObject<SwordItem> TIGERS_EYE_SWORD = ITEMS.register("tigers_eye_sword", () -> new SwordItem(ToolMaterials.TOOL_TIGERS_EYE, 0, -2.4F, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<PickaxeItem> TIGERS_EYE_PICKAXE = ITEMS.register("tigers_eye_pickaxe", () -> new PickaxeItem(ToolMaterials.TOOL_TIGERS_EYE, 0, 0, new Item.Settings().group(ItemGroup.TOOLS)));
	public static final RegistryObject<ShovelItem> TIGERS_EYE_SHOVEL = ITEMS.register("tigers_eye_shovel", () -> new ShovelItem(ToolMaterials.TOOL_TIGERS_EYE, 0, 0, new Item.Settings().group(ItemGroup.TOOLS)));
	public static final RegistryObject<HoeItem> TIGERS_EYE_HOE = ITEMS.register("tigers_eye_hoe", () -> new HoeItem(ToolMaterials.TOOL_TIGERS_EYE, 0, 0, new Item.Settings().group(ItemGroup.TOOLS)));
	
	public static final RegistryObject<SwordItem> ULTIMATE_SWORD = ITEMS.register("ultimate_sword", () -> new EnchantedSword(ToolMaterials.TOOL_ULTIMATE, 0, -2.4F, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<EnchantedPic> ULTIMATE_PICKAXE = ITEMS.register("ultimate_pickaxe", () -> new EnchantedPic(ToolMaterials.TOOL_ULTIMATE, 0, 0, new Item.Settings().group(ItemGroup.TOOLS)));
	public static final RegistryObject<ShovelItem> ULTIMATE_SHOVEL = ITEMS.register("ultimate_shovel", () -> new EnchantedShovel(ToolMaterials.TOOL_ULTIMATE, 0, 0, new Item.Settings().group(ItemGroup.TOOLS)));
	public static final RegistryObject<EnchantedHoe> ULTIMATE_HOE = ITEMS.register("ultimate_hoe", () -> new EnchantedHoe(ToolMaterials.TOOL_ULTIMATE, 0, 0, new Item.Settings().group(ItemGroup.TOOLS)));
	
	public static final RegistryObject<EnchantedPic> OPTIMISED_PICKAXE = ITEMS.register("optimised_pickaxe", () -> new EnchantedPic(ToolMaterials.TOOL_OPTIMISED, 0, 0, new Item.Settings().group(ItemGroup.TOOLS)));
	public static final RegistryObject<ShovelItem> OPTIMISED_SHOVEL = ITEMS.register("optimised_shovel", () -> new EnchantedShovel(ToolMaterials.TOOL_OPTIMISED, 0, 0, new Item.Settings().group(ItemGroup.TOOLS)));

	
	
	
	
	
	
	
	//Armour
	public static final RegistryObject<ArmorItem> EMERALD_HELMET = ITEMS.register("emerald_helmet", () -> new ArmorItem(ArmourMaterials.EMERALD, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> EMERALD_CHESTPLATE = ITEMS.register("emerald_chestplate", () -> new ArmorItem(ArmourMaterials.EMERALD, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> EMERALD_LEGGINGS = ITEMS.register("emerald_leggings", () -> new ArmorItem(ArmourMaterials.EMERALD, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> EMERALD_BOOTS = ITEMS.register("emerald_boots", () -> new ArmorItem(ArmourMaterials.EMERALD, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
	
	
//	public static final RegistryObject<ArmorItem> AMETHYST_HELMET = ITEMS.register("amethyst_helmet", () -> new ArmorItem(ArmourMaterials.AMETHYST, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
//	public static final RegistryObject<ArmorItem> AMETHYST_CHESTPLATE = ITEMS.register("amethyst_chestplate", () -> new ArmorItem(ArmourMaterials.AMETHYST, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
//	public static final RegistryObject<ArmorItem> AMETHYST_LEGGINGS = ITEMS.register("amethyst_leggings", () -> new ArmorItem(ArmourMaterials.AMETHYST, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
//	public static final RegistryObject<ArmorItem> AMETHYST_BOOTS = ITEMS.register("amethyst_boots", () -> new ArmorItem(ArmourMaterials.AMETHYST, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
	
	
	
	
	
	public static final RegistryObject<ArmorItem> SAPPHIRE_HELMET = ITEMS.register("sapphire_helmet", () -> new ArmorItem(ArmourMaterials.SAPPHIRE, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> SAPPHIRE_CHESTPLATE = ITEMS.register("sapphire_chestplate", () -> new ArmorItem(ArmourMaterials.SAPPHIRE, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> SAPPHIRE_LEGGINGS = ITEMS.register("sapphire_leggings", () -> new ArmorItem(ArmourMaterials.SAPPHIRE, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> SAPPHIRE_BOOTS = ITEMS.register("sapphire_boots", () -> new ArmorItem(ArmourMaterials.SAPPHIRE, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
	
	
	
	
	
	public static final RegistryObject<ArmorItem> RUBY_HELMET = ITEMS.register("ruby_helmet", () -> new ArmorItem(ArmourMaterials.RUBY, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> RUBY_CHESTPLATE = ITEMS.register("ruby_chestplate", () -> new ArmorItem(ArmourMaterials.RUBY, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> RUBY_LEGGINGS = ITEMS.register("ruby_leggings", () -> new ArmorItem(ArmourMaterials.RUBY, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> RUBY_BOOTS = ITEMS.register("ruby_boots", () -> new ArmorItem(ArmourMaterials.RUBY, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
	
	public static final RegistryObject<ArmorItem> TIGERS_EYE_HELMET = ITEMS.register("tigers_eye_helmet", () -> new ArmorItem(ArmourMaterials.TIGERS_EYE, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> TIGERS_EYE_CHESTPLATE = ITEMS.register("tigers_eye_chestplate", () -> new ArmorItem(ArmourMaterials.TIGERS_EYE, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> TIGERS_EYE_LEGGINGS = ITEMS.register("tigers_eye_leggings", () -> new ArmorItem(ArmourMaterials.TIGERS_EYE, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> TIGERS_EYE_BOOTS = ITEMS.register("tigers_eye_boots", () -> new ArmorItem(ArmourMaterials.TIGERS_EYE, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
	
	
	
	public static final RegistryObject<ArmorItem> COPPER_HELMET = ITEMS.register("copper_helmet", () -> new ArmorItem(ArmourMaterials.COPPER, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate", () -> new ArmorItem(ArmourMaterials.COPPER, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> COPPER_LEGGINGS = ITEMS.register("copper_leggings", () -> new ArmorItem(ArmourMaterials.COPPER, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> COPPER_BOOTS = ITEMS.register("copper_boots", () -> new ArmorItem(ArmourMaterials.COPPER, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
	
	
	public static final RegistryObject<ArmorItem> PLATINUM_HELMET = ITEMS.register("platinum_helmet", () -> new ArmorItem(ArmourMaterials.PLATINUM, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> PLATINUM_CHESTPLATE = ITEMS.register("platinum_chestplate", () -> new ArmorItem(ArmourMaterials.PLATINUM, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> PLATINUM_LEGGINGS = ITEMS.register("platinum_leggings", () -> new ArmorItem(ArmourMaterials.PLATINUM, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> PLATNIUM_BOOTS = ITEMS.register("platinum_boots", () -> new ArmorItem(ArmourMaterials.PLATINUM, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
	
	
	
	public static final RegistryObject<ArmorItem> SILVER_HELMET = ITEMS.register("silver_helmet", () -> new ArmorItem(ArmourMaterials.SILVER, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> SILVER_CHESTPLATE = ITEMS.register("silver_chestplate", () -> new ArmorItem(ArmourMaterials.SILVER, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> SILVER_LEGGINGS = ITEMS.register("silver_leggings", () -> new ArmorItem(ArmourMaterials.SILVER, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> SILVER_BOOTS = ITEMS.register("silver_boots", () -> new ArmorItem(ArmourMaterials.SILVER, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
	
	public static final RegistryObject<ArmorItem> TIN_HELMET = ITEMS.register("tin_helmet", () -> new ArmorItem(ArmourMaterials.TIN, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> TIN_CHESTPLATE = ITEMS.register("tin_chestplate", () -> new ArmorItem(ArmourMaterials.TIN, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> TIN_LEGGINGS = ITEMS.register("tin_leggings", () -> new ArmorItem(ArmourMaterials.TIN, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> TIN_BOOTS = ITEMS.register("tin_boots", () -> new ArmorItem(ArmourMaterials.TIN, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
	
	public static final RegistryObject<ArmorItem> ALUMINIUM_HELMET = ITEMS.register("aluminium_helmet", () -> new ArmorItem(ArmourMaterials.ALUMINIUM, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> ALUMINIUM_CHESTPLATE = ITEMS.register("aluminium_chestplate", () -> new ArmorItem(ArmourMaterials.ALUMINIUM, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> ALUMINIUM_LEGGINGS = ITEMS.register("aluminium_leggings", () -> new ArmorItem(ArmourMaterials.ALUMINIUM, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> ALUMINIUM_BOOTS = ITEMS.register("aluminium_boots", () -> new ArmorItem(ArmourMaterials.ALUMINIUM, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
	
	public static final RegistryObject<ArmorItem> PINK_TOURMALINE_HELMET = ITEMS.register("pink_tourmaline_helmet", () -> new ArmorItem(ArmourMaterials.PINK_TOURMALINE, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> PINK_TOURMALINE_CHESTPLATE = ITEMS.register("pink_tourmaline_chestplate", () -> new ArmorItem(ArmourMaterials.PINK_TOURMALINE, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> PINK_TOURMALINE_LEGGINGS = ITEMS.register("pink_tourmaline_leggings", () -> new ArmorItem(ArmourMaterials.PINK_TOURMALINE, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> PINK_TOURMALINE_BOOTS = ITEMS.register("pink_tourmaline_boots", () -> new ArmorItem(ArmourMaterials.PINK_TOURMALINE, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
	
	
	
	
	public static final RegistryObject<ArmorItem> ULTIMATE_HELMET = ITEMS.register("ultimate_helmet", () -> new EnchantedArmour(ArmourMaterials.ULTIMATE, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> ULTIMATE_CHESTPLATE = ITEMS.register("ultimate_chestplate", () -> new EnchantedArmour(ArmourMaterials.ULTIMATE, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> ULTIMATE_LEGGINGS = ITEMS.register("ultimate_leggings", () -> new EnchantedArmour(ArmourMaterials.ULTIMATE, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> ULTIMATE_BOOTS = ITEMS.register("ultimate_boots", () -> new EnchantedArmour(ArmourMaterials.ULTIMATE, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
	
	public static final RegistryObject<ArmorItem> EXPERIENCE_HELMET = ITEMS.register("experience_helmet", () -> new EnchantedArmour(ArmourMaterials.EXPERIENCE, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> EXPERIENCE_CHESTPLATE = ITEMS.register("experience_chestplate", () -> new EnchantedArmour(ArmourMaterials.EXPERIENCE, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> EXPERIENCE_LEGGINGS = ITEMS.register("experience_leggings", () -> new EnchantedArmour(ArmourMaterials.EXPERIENCE, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> EXPERIENCE_BOOTS = ITEMS.register("experience_boots", () -> new EnchantedArmour(ArmourMaterials.EXPERIENCE, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
	
	
	public static final RegistryObject<ArmorItem> ROYAL_GUARDIAN_HELMET = ITEMS.register("royal_guardian_helmet", () -> new EnchantedArmour(ArmourMaterials.ROYAL_GUARDIAN, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> ROYAL_GUARDIAN_CHESTPLATE = ITEMS.register("royal_guardian_chestplate", () -> new EnchantedArmour(ArmourMaterials.ROYAL_GUARDIAN, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> ROYAL_GUARDIAN_LEGGINGS = ITEMS.register("royal_guardian_leggings", () -> new EnchantedArmour(ArmourMaterials.ROYAL_GUARDIAN, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> ROYAL_GUARDIAN_BOOTS = ITEMS.register("royal_guardian_boots", () -> new EnchantedArmour(ArmourMaterials.ROYAL_GUARDIAN, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
	

	public static final RegistryObject<ArmorItem> QUEEN_SCALE_HELMET = ITEMS.register("queen_scale_helmet", () -> new EnchantedArmour(ArmourMaterials.QUEEN_SCALE, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> QUEEN_SCALE_CHESTPLATE = ITEMS.register("queen_scale_chestplate", () -> new EnchantedArmour(ArmourMaterials.QUEEN_SCALE, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> QUEEN_SCALE_LEGGINGS = ITEMS.register("queen_scale_leggings", () -> new EnchantedArmour(ArmourMaterials.QUEEN_SCALE, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> QUEEN_SCALE_BOOTS = ITEMS.register("queen_scale_boots", () -> new EnchantedArmour(ArmourMaterials.QUEEN_SCALE, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
	
	public static final RegistryObject<ArmorItem> MOBZILLA_SCALE_HELMET = ITEMS.register("mobzilla_scale_helmet", () -> new EnchantedArmour(ArmourMaterials.MOBZILLA_SCALE, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> MOBZILLA_SCALE_CHESTPLATE = ITEMS.register("mobzilla_scale_chestplate", () -> new EnchantedArmour(ArmourMaterials.MOBZILLA_SCALE, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> MOBZILLA_SCALE_LEGGINGS = ITEMS.register("mobzilla_scale_leggings", () -> new EnchantedArmour(ArmourMaterials.MOBZILLA_SCALE, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> MOBZILLA_SCALE_BOOTS = ITEMS.register("mobzilla_scale_boots", () -> new EnchantedArmour(ArmourMaterials.MOBZILLA_SCALE, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
	
	public static final RegistryObject<ArmorItem> MOTH_SCALE_HELMET = ITEMS.register("moth_scale_helmet", () -> new EnchantedArmour(ArmourMaterials.MOTH_SCALE, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> MOTH_SCALE_CHESTPLATE = ITEMS.register("moth_scale_chestplate", () -> new EnchantedArmour(ArmourMaterials.MOTH_SCALE, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> MOTH_SCALE_LEGGINGS = ITEMS.register("moth_scale_leggings", () -> new EnchantedArmour(ArmourMaterials.MOTH_SCALE, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> MOTH_SCALE_BOOTS = ITEMS.register("moth_scale_boots", () -> new EnchantedArmour(ArmourMaterials.MOTH_SCALE, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
	
	public static final RegistryObject<ArmorItem> LAPIS_LAZULI_HELMET = ITEMS.register("lapis_block_helmet", () -> new EnchantedArmour(ArmourMaterials.LAPIS_BLOCK, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> LAPIS_LAZULI_CHESTPLATE = ITEMS.register("lapis_block_chestplate", () -> new EnchantedArmour(ArmourMaterials.LAPIS_BLOCK, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> LAPIS_LAZULI_LEGGINGS = ITEMS.register("lapis_block_leggings", () -> new EnchantedArmour(ArmourMaterials.LAPIS_BLOCK, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> LAPIS_LAZULI_BOOTS = ITEMS.register("lapis_block_boots", () -> new EnchantedArmour(ArmourMaterials.LAPIS_BLOCK, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
	
	public static final RegistryObject<ArmorItem> LAVA_EEL_HELMET = ITEMS.register("lava_eel_helmet", () -> new EnchantedArmour(ArmourMaterials.LAVA_EEL, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> LAVA_EEL_CHESTPLATE = ITEMS.register("lava_eel_chestplate", () -> new EnchantedArmour(ArmourMaterials.LAVA_EEL, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> LAVA_EEL_LEGGINGS = ITEMS.register("lava_eel_leggings", () -> new EnchantedArmour(ArmourMaterials.LAVA_EEL, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> LAVA_EEL_BOOTS = ITEMS.register("lava_eel_boots", () -> new EnchantedArmour(ArmourMaterials.LAVA_EEL, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
	
	
	public static final RegistryObject<ArmorItem> PEACOCK_FEATHER_HELMET = ITEMS.register("peacock_feather_helmet", () -> new EnchantedArmour(ArmourMaterials.PEACOCK_FEATHER, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> PEACOCK_FEATHER_CHESTPLATE = ITEMS.register("peacock_feather_chestplate", () -> new EnchantedArmour(ArmourMaterials.PEACOCK_FEATHER, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> PEACOCK_FEATHER_LEGGINGS = ITEMS.register("peacock_feather_leggings", () -> new EnchantedArmour(ArmourMaterials.PEACOCK_FEATHER, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> PEACOCK_FEATHER_BOOTS = ITEMS.register("peacock_feather_boots", () -> new EnchantedArmour(ArmourMaterials.PEACOCK_FEATHER, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
	
	
	public static final RegistryObject<ArmorItem> CZ_SLOW_BOOTS = ITEMS.register("cz_slow_boots", () -> new CZSlowArmour(ArmourMaterials.EXPERIENCE, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));

}