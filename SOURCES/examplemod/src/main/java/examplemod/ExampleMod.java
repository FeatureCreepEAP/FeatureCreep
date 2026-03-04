package examplemod;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;

public class ExampleMod {

    public static final String MODID = "example";

    /* ---------------------------------------------------------
       BASIC ITEMS
    ---------------------------------------------------------- */

    public static final Item EXAMPLE_ITEM = new Item(new Item.Properties());
    public static final Item EXAMPLE_ITEM_2 = new Item(new Item.Properties());
    public static final Item EXAMPLE_GEM = new Item(new Item.Properties());
    public static final Item EXAMPLE_STICK = new Item(new Item.Properties());
    public static final Item EXAMPLE_FOOD = new Item(new Item.Properties().food(Foods.APPLE));

    /* ---------------------------------------------------------
       TOOL TIER
    ---------------------------------------------------------- */

    public static final Tier EXAMPLE_TIER = new Tier() {
        @Override public int getUses() { return 5000; }
        @Override public float getSpeed() { return 20.0F; }
        @Override public float getAttackDamageBonus() { return 8.0F; }
        @Override public int getLevel() { return 8; }
        @Override public int getEnchantmentValue() { return 20; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.of(EXAMPLE_ITEM); }
    };

    /* ---------------------------------------------------------
       TOOLS
    ---------------------------------------------------------- */

    public static final Item EXAMPLE_PICKAXE = new PickaxeItem(EXAMPLE_TIER, 1, -2.8F, new Item.Properties());
    public static final Item EXAMPLE_SHOVEL  = new ShovelItem(EXAMPLE_TIER, 1.5F, -3.0F, new Item.Properties());
    public static final Item EXAMPLE_HOE     = new HoeItem(EXAMPLE_TIER, -4, 0.0F, new Item.Properties());
    public static final Item EXAMPLE_SWORD   = new SwordItem(EXAMPLE_TIER, 3, -2.4F, new Item.Properties());
    public static final Item EXAMPLE_AXE     = new AxeItem(EXAMPLE_TIER, 5.0F, -3.0F, new Item.Properties());

    /* ---------------------------------------------------------
       ARMOR MATERIAL
    ---------------------------------------------------------- */

    public static final ArmorMaterial EXAMPLE_ARMOR_MATERIAL = new ArmorMaterial() {

        @Override
        public int getDurabilityForType(Type type) {
            return 500;
        }

        @Override
        public int getDefenseForType(Type type) {
            return switch (type) {
                case HELMET -> 5;
                case CHESTPLATE -> 10;
                case LEGGINGS -> 8;
                case BOOTS -> 5;
            };
        }

        @Override public int getEnchantmentValue() { return 20; }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_DIAMOND;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(EXAMPLE_ITEM);
        }

        @Override
        public String getName() {
            return MODID + ":example";
        }

        @Override public float getToughness() { return 2.0F; }
        @Override public float getKnockbackResistance() { return 0.0F; }
    };

    public static final Item EXAMPLE_HELMET     = new ArmorItem(EXAMPLE_ARMOR_MATERIAL, Type.HELMET, new Item.Properties());
    public static final Item EXAMPLE_CHESTPLATE = new ArmorItem(EXAMPLE_ARMOR_MATERIAL, Type.CHESTPLATE, new Item.Properties());
    public static final Item EXAMPLE_LEGGINGS   = new ArmorItem(EXAMPLE_ARMOR_MATERIAL, Type.LEGGINGS, new Item.Properties());
    public static final Item EXAMPLE_BOOTS      = new ArmorItem(EXAMPLE_ARMOR_MATERIAL, Type.BOOTS, new Item.Properties());

    /* ---------------------------------------------------------
       VEHICLES
    ---------------------------------------------------------- */

    public static final Item EXAMPLE_MINECART =
            new MinecartItem(AbstractMinecart.Type.RIDEABLE, new Item.Properties().stacksTo(1));

    public static final Item EXAMPLE_CHEST_MINECART =
            new MinecartItem(AbstractMinecart.Type.CHEST, new Item.Properties().stacksTo(1));

    public static final Item EXAMPLE_BOAT =
            new BoatItem(false, Boat.Type.OAK, new Item.Properties().stacksTo(1));

    public static final Item EXAMPLE_CHEST_BOAT =
            new BoatItem(true, Boat.Type.OAK, new Item.Properties().stacksTo(1));

    /* ---------------------------------------------------------
       UTILITIES
    ---------------------------------------------------------- */

    public static final Item EXAMPLE_BUCKET =
            new BucketItem(Fluids.EMPTY, new Item.Properties().stacksTo(16));

    public static final Item EXAMPLE_FLINT_AND_STEEL =
            new FlintAndSteelItem(new Item.Properties().durability(64));

    public static final Item EXAMPLE_BOW =
            new BowItem(new Item.Properties().durability(384));

    /* ---------------------------------------------------------
       BLOCKS
    ---------------------------------------------------------- */

    public static final Block EXAMPLE_BLOCK = new Block(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(3.0F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()
    );

    public static final Block EXAMPLE_ORE_BLOCK = new DropExperienceBlock(
            UniformInt.of(0,2),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(3.0F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()
    );

    public static final Block EXAMPLE_STAIRS =
            new StairBlock(EXAMPLE_BLOCK.defaultBlockState(),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE)
                            .strength(3.0F)
                            .requiresCorrectToolForDrops());

    public static final Block EXAMPLE_SLAB =
            new SlabBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(3.0F));

    public static final Block EXAMPLE_FENCE =
            new FenceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(3.0F));

    public static final Block EXAMPLE_DOOR =
            new DoorBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(3.0F)
                    .noOcclusion(), BlockSetType.OAK);

    /* ---------------------------------------------------------
       REGISTRATION
    ---------------------------------------------------------- */

    public static void registerAll() {

        /* Items */

        Registry.register(BuiltInRegistries.ITEM, id("example_item"), EXAMPLE_ITEM);
        Registry.register(BuiltInRegistries.ITEM, id("example_item_2"), EXAMPLE_ITEM_2);
        Registry.register(BuiltInRegistries.ITEM, id("example_gem"), EXAMPLE_GEM);
        Registry.register(BuiltInRegistries.ITEM, id("example_stick"), EXAMPLE_STICK);
        Registry.register(BuiltInRegistries.ITEM, id("example_food"), EXAMPLE_FOOD);

        Registry.register(BuiltInRegistries.ITEM, id("example_pickaxe"), EXAMPLE_PICKAXE);
        Registry.register(BuiltInRegistries.ITEM, id("example_shovel"), EXAMPLE_SHOVEL);
        Registry.register(BuiltInRegistries.ITEM, id("example_hoe"), EXAMPLE_HOE);
        Registry.register(BuiltInRegistries.ITEM, id("example_sword"), EXAMPLE_SWORD);
        Registry.register(BuiltInRegistries.ITEM, id("example_axe"), EXAMPLE_AXE);

        Registry.register(BuiltInRegistries.ITEM, id("example_helmet"), EXAMPLE_HELMET);
        Registry.register(BuiltInRegistries.ITEM, id("example_chestplate"), EXAMPLE_CHESTPLATE);
        Registry.register(BuiltInRegistries.ITEM, id("example_leggings"), EXAMPLE_LEGGINGS);
        Registry.register(BuiltInRegistries.ITEM, id("example_boots"), EXAMPLE_BOOTS);

        Registry.register(BuiltInRegistries.ITEM, id("example_minecart"), EXAMPLE_MINECART);
        Registry.register(BuiltInRegistries.ITEM, id("example_chest_minecart"), EXAMPLE_CHEST_MINECART);

        Registry.register(BuiltInRegistries.ITEM, id("example_boat"), EXAMPLE_BOAT);
        Registry.register(BuiltInRegistries.ITEM, id("example_chest_boat"), EXAMPLE_CHEST_BOAT);

        Registry.register(BuiltInRegistries.ITEM, id("example_bucket"), EXAMPLE_BUCKET);
        Registry.register(BuiltInRegistries.ITEM, id("example_flint_and_steel"), EXAMPLE_FLINT_AND_STEEL);
        Registry.register(BuiltInRegistries.ITEM, id("example_bow"), EXAMPLE_BOW);

        /* Blocks */

        Registry.register(BuiltInRegistries.BLOCK, id("example_block"), EXAMPLE_BLOCK);
        Registry.register(BuiltInRegistries.BLOCK, id("example_ore"), EXAMPLE_ORE_BLOCK);
        Registry.register(BuiltInRegistries.BLOCK, id("example_stairs"), EXAMPLE_STAIRS);
        Registry.register(BuiltInRegistries.BLOCK, id("example_slab"), EXAMPLE_SLAB);
        Registry.register(BuiltInRegistries.BLOCK, id("example_fence"), EXAMPLE_FENCE);
        Registry.register(BuiltInRegistries.BLOCK, id("example_door"), EXAMPLE_DOOR);

        /* Block Items */

        Registry.register(BuiltInRegistries.ITEM, id("example_block"),
                new BlockItem(EXAMPLE_BLOCK, new Item.Properties()));

        Registry.register(BuiltInRegistries.ITEM, id("example_ore"),
                new BlockItem(EXAMPLE_ORE_BLOCK, new Item.Properties()));

        Registry.register(BuiltInRegistries.ITEM, id("example_stairs"),
                new BlockItem(EXAMPLE_STAIRS, new Item.Properties()));

        Registry.register(BuiltInRegistries.ITEM, id("example_slab"),
                new BlockItem(EXAMPLE_SLAB, new Item.Properties()));

        Registry.register(BuiltInRegistries.ITEM, id("example_fence"),
                new BlockItem(EXAMPLE_FENCE, new Item.Properties()));

        Registry.register(BuiltInRegistries.ITEM, id("example_door"),
                new BlockItem(EXAMPLE_DOOR, new Item.Properties()));
    }

    private static ResourceLocation id(String path) {
        return new ResourceLocation(MODID, path);
    }

    /* ---------------------------------------------------------
       ENTRYPOINT
    ---------------------------------------------------------- */

    public static void main(String[] main) {
        System.out.println("ExampleMod loaded (Vanilla registry style)");
        registerAll();
    }
}