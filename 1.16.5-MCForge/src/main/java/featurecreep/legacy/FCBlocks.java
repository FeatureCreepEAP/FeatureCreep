package featurecreep.legacy;


import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = FeatureCreepMC.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FCBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FeatureCreepMC.MODID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FeatureCreepMC.MODID);


	public static final RegistryObject<BasicOre> AMETHYST_ORE = registerBlock("amethyst_ore", () -> new BasicOre(Block.Settings.copy(Blocks.DIAMOND_ORE)), ItemGroup.MATERIALS);
	public static final RegistryObject<BasicOre> RUBY_ORE = registerBlock("ruby_ore", () -> new BasicOre(Block.Settings.copy(Blocks.DIAMOND_ORE)), ItemGroup.MATERIALS);
	public static final RegistryObject<BasicOre> TIGERS_EYE_ORE = registerBlock("tigers_eye_ore", () -> new BasicOre(Block.Settings.copy(Blocks.DIAMOND_ORE)), ItemGroup.MATERIALS);
	public static final RegistryObject<BasicOre> TITANIUM_ORE = registerBlock("titanium_ore", () -> new BasicOre(Block.Settings.copy(Blocks.DIAMOND_ORE)), ItemGroup.MATERIALS);
	public static final RegistryObject<BasicOre> URANIUM_ORE = registerBlock("uranium_ore", () -> new BasicOre(Block.Settings.copy(Blocks.DIAMOND_ORE)), ItemGroup.MATERIALS);
	public static final RegistryObject<BasicOre> ALUMINIUM_ORE = registerBlock("aluminium_ore", () -> new BasicOre(Block.Settings.copy(Blocks.IRON_ORE)), ItemGroup.MATERIALS);
	public static final RegistryObject<BasicOre> SALT_ORE = registerBlock("salt_ore", () -> new BasicOre(Block.Settings.copy(Blocks.COAL_ORE)), ItemGroup.MATERIALS);
	//public static final RegistryObject<BasicOre> COPPER_ORE = registerBlock("copper_ore", () -> new BasicOre(Block.Settings.copy(Blocks.COAL_ORE)), ItemGroup.MATERIALS);
	//public static final RegistryObject<BasicOre> TIN_ORE = registerBlock("tin_ore", () -> new BasicOre(Block.Settings.copy(Blocks.IRON_ORE)),ItemGroup.MATERIALS);
	//public static final RegistryObject<BasicOre> SILVER_ORE = registerBlock("silver_ore", () -> new BasicOre(Block.Settings.copy(Blocks.IRON_ORE)), ItemGroup.MATERIALS);
	//public static final RegistryObject<BasicOre> PLATINUM_ORE = registerBlock("platinum_ore", () -> new BasicOre(Block.Settings.copy(Blocks.DIAMOND_ORE)), ItemGroup.MATERIALS);
	public static final RegistryObject<BasicOre> UNPROCESSED_OIL_ORE = registerBlock("unprocessed_oil_ore", () -> new BasicOre(Block.Settings.copy(Blocks.COAL_ORE)), ItemGroup.MATERIALS);
	public static final RegistryObject<BasicOre> GASOLINE_PETROL_ORE = registerBlock("gasoline_petrol_ore", () -> new BasicOre(Block.Settings.copy(Blocks.COAL_ORE)), ItemGroup.MATERIALS);

	public static final RegistryObject<Block> AMETHYST_BLOCK = registerBlock("amethyst_block", () -> new Block(Block.Settings.copy(Blocks.DIAMOND_BLOCK)), ItemGroup.MATERIALS);
	public static final RegistryObject<Block> RUBY_BLOCK = registerBlock("ruby_block", () -> new Block(Block.Settings.copy(Blocks.DIAMOND_BLOCK)), ItemGroup.MATERIALS);
	public static final RegistryObject<Block> TIGERS_EYE_BLOCK = registerBlock("tigers_eye_block", () -> new Block(Block.Settings.copy(Blocks.DIAMOND_BLOCK)), ItemGroup.MATERIALS);
	public static final RegistryObject<Block> TITANIUM_BLOCK = registerBlock("titanium_block", () -> new Block(Block.Settings.copy(Blocks.DIAMOND_BLOCK)), ItemGroup.MATERIALS);
	public static final RegistryObject<Block> URANIUM_BLOCK = registerBlock("uranium_block", () -> new Block(Block.Settings.copy(Blocks.DIAMOND_BLOCK)), ItemGroup.MATERIALS);
	public static final RegistryObject<Block> ALUMINIUM_BLOCK = registerBlock("aluminium_block", () -> new Block(Block.Settings.copy(Blocks.DIAMOND_BLOCK)), ItemGroup.MATERIALS);
	//public static final RegistryObject<Block> COPPER_BLOCK = registerBlock("copper_block", () -> new Block(Block.Settings.copy(Blocks.IRON_BLOCK)), ItemGroup.MATERIALS);
	//public static final RegistryObject<Block> TIN_BLOCK = registerBlock("tin_block", () -> new Block(Block.Settings.copy(Blocks.IRON_BLOCK)), ItemGroup.MATERIALS);
	//public static final RegistryObject<Block> SILVER_BLOCK = registerBlock("silver_block", () -> new Block(Block.Settings.copy(Blocks.IRON_BLOCK)), ItemGroup.MATERIALS);
	//public static final RegistryObject<Block> PLATINUM_BLOCK = registerBlock("platinum_block", () -> new Block(Block.Settings.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.NETHERITE)), ItemGroup.MATERIALS);
//	public static final RegistryObject<Block> PINK_TOURMALINE_BLOCK = registerBlock("pink_tourmaline_block", () -> new Block(Block.Settings.copy(Blocks.IRON_BLOCK).setOpaque(FCBlocks::_false).notSolid()), ItemGroup.MATERIALS);







	
	public static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<? extends B> supplier, ItemGroup itemGroup) {
		return registerBlock(name,supplier,itemGroup,true);
	}

	public static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<? extends B> supplier, ItemGroup itemGroup, boolean generateItem) {
		RegistryObject<B> block = FCBlocks.BLOCKS.register(name, supplier);
		if (generateItem) ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Settings().group(itemGroup)));
		return block;
	}

	private static boolean _false(BlockState state, BlockView reader, BlockPos pos) {
		return false;
	}

	private static boolean _true(BlockState state, BlockView reader, BlockPos pos) {
		return true;
	}

	private static ToIntFunction<BlockState> getLightValueLit(int lightValue) {
		return (state) -> state.get(Properties.LIT) ? lightValue : 0;
	}

	
}