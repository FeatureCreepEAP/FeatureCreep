package featurecreep.api.bg.registries;

import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.blocks.vanilla.VanillaBlock;
import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.items.vanilla.VanillaItem;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import featurecreep.api.bg.ui.tabs.vanilla.VanillaCreativeTab;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class UniversalRegistryGettersAndSetters {

	public static Item getItembyName(String registry_name) {
		return GlobalRegistries.getItemByName(registry_name) != null
				? GlobalRegistries.getItemByName(registry_name).get()
				: GameRegistries.getItemFromGameRegistries(registry_name);
	}

	public static Item getItembyID(int id) {
		return GlobalRegistries.getItemByID(id) != null ? GlobalRegistries.getItemByID(id).get()
				: GameRegistries.getItemFromGameRegistries(id);
	}

	public static FCItemAPI getFCItembyName(String registry_name) {
		return (FCItemAPI) (GlobalRegistries.getItemByName(registry_name) != null
				? GlobalRegistries.getItemByName(registry_name)
				: new VanillaItem(GameRegistries.getItemFromGameRegistries(registry_name), registry_name));
	}

	public static FCItemAPI getFCItembyID(int id) {
		return (FCItemAPI) (GlobalRegistries.getItemByID(id) != null ? GlobalRegistries.getItemByID(id)
				: new VanillaItem(GameRegistries.getItemFromGameRegistries(id),
						BuiltInRegistries.ITEM.getKey(GameRegistries.getItemFromGameRegistries(id)).toString()));
	}

	public static void registerItem(Item item, String registry_name, CreativeModeTab default_tab, int id) {
		ForgeRegistries.ITEMS.register(ResourceLocation.parse(registry_name), item);
	}

	public static void registerItem(FCItemAPI item) {
		registerItem(item.get(), item.getFCRegistryName(), item.getDefaultCreativeTab(), item.getNumberID());
	}

	public static CreativeModeTab getItemGroupByName(String registry_name) {
		return GlobalRegistries.getItemGroupByName(registry_name) != null
				? GlobalRegistries.getItemGroupByName(registry_name).get()
				: GameRegistries.getItemGroupByName(registry_name);
	}

	public static CreativeModeTab getItemGroupbyID(int id) {
		return GlobalRegistries.getItemGroupByID(id) != null ? GlobalRegistries.getItemGroupByID(id).get()
				: GameRegistries.getItemGroupByID(id);
	}

	public static UnifiedItemGroupGetter getFCItemGroupbyName(String registry_name) {
		return (UnifiedItemGroupGetter) (GlobalRegistries.getItemGroupByName(registry_name) != null
				? GlobalRegistries.getItemGroupByName(registry_name)
				: new VanillaCreativeTab(registry_name));
	}

	public static UnifiedItemGroupGetter getFCItemGroupbyID(int id) {
		return (UnifiedItemGroupGetter) (GlobalRegistries.getItemGroupByID(id) != null
				? GlobalRegistries.getItemGroupByID(id)
				: new VanillaCreativeTab(getItemGroupbyID(id).getDisplayName().getString()));
	}

	public static void registerItemGroup(CreativeModeTab tab, String registry_name, int id) {
	}

	public static void registerItemGroup(UnifiedItemGroupGetter tab) {
		registerItemGroup(tab.get(), tab.getTabName(), tab.getID());
	}

	public static Block getBlockbyName(String registry_name) {
		return GlobalRegistries.getBlockByName(registry_name) != null
				? GlobalRegistries.getBlockByName(registry_name).get()
				: GameRegistries.getBlockFromGameRegistries(registry_name);
	}

	public static Block getBlockbyID(int id) {
		return GlobalRegistries.getBlockByID(id) != null ? GlobalRegistries.getBlockByID(id).get()
				: GameRegistries.getBlockFromGameRegistries(id);
	}

	public static FCBlockAPI getFCBlockbyName(String registry_name) {
		return (FCBlockAPI) (GlobalRegistries.getBlockByName(registry_name) != null
				? GlobalRegistries.getBlockByName(registry_name)
				: new VanillaBlock(GameRegistries.getBlockFromGameRegistries(registry_name), registry_name));
	}

	public static FCBlockAPI getFCBlockbyID(int id) {
		return (FCBlockAPI) (GlobalRegistries.getBlockByID(id) != null ? GlobalRegistries.getBlockByID(id)
				: new VanillaBlock(GameRegistries.getBlockFromGameRegistries(id),
						BuiltInRegistries.BLOCK.getKey(GameRegistries.getBlockFromGameRegistries(id)).toString()));
	}

	public static void registerBlock(Block block, String registry_name, CreativeModeTab default_tab, int id) {
		ForgeRegistries.BLOCKS.register(ResourceLocation.parse(registry_name), block);
		ForgeRegistries.ITEMS.register(registry_name, new BlockItem(block, new Properties()));
	}

	public static void registerBlock(FCBlockAPI block) {
		registerBlock(block.get(), block.getFCRegistryName(), block.getDefaultCreativeTab(), block.getNumberID());
	}

	public static void vainillaRegister(Registry registry, ResourceLocation rl, Object Entry) {
		if (registry.equals(BuiltInRegistries.ITEM)) {
			ForgeRegistries.ITEMS.register(rl, (Item) Entry);
		} else if (registry.equals(BuiltInRegistries.BLOCK)) {
			ForgeRegistries.BLOCKS.register(rl, (Block) Entry);
		} else if (registry.equals(BuiltInRegistries.ATTRIBUTE)) {
			ForgeRegistries.ATTRIBUTES.register(rl, (Attribute) Entry);
		} else if (registry.equals(BuiltInRegistries.ACTIVITY)) {
			ForgeRegistries.ACTIVITIES.register(rl, (Activity) Entry);
		} else if (registry.equals(BuiltInRegistries.BLOCK_ENTITY_TYPE)) {
			ForgeRegistries.BLOCK_ENTITY_TYPES.register(rl, (BlockEntityType) Entry);
		} else if (registry.equals(BuiltInRegistries.CHUNK_STATUS)) {
			ForgeRegistries.CHUNK_STATUS.register(rl, (ChunkStatus) Entry);
		} else if (registry.equals(BuiltInRegistries.COMMAND_ARGUMENT_TYPE)) {
			ForgeRegistries.COMMAND_ARGUMENT_TYPES.register(rl, (ArgumentTypeInfo) Entry);
		} else {
			Registry.register(registry, rl, Entry);
		}

	}

	public static DeferredRegister deferredRegistryConverter(Registry registry, String name) {
		if (registry.equals(BuiltInRegistries.ITEM)) {
			return DeferredRegister.create(ForgeRegistries.ITEMS, name);
		} else if (registry.equals(BuiltInRegistries.BLOCK)) {
			return DeferredRegister.create(ForgeRegistries.BLOCKS, name);
		} else if (registry.equals(BuiltInRegistries.ATTRIBUTE)) {
			return DeferredRegister.create(ForgeRegistries.ATTRIBUTES, name);
		} else if (registry.equals(BuiltInRegistries.ACTIVITY)) {
			return DeferredRegister.create(ForgeRegistries.ACTIVITIES, name);
		} else if (registry.equals(BuiltInRegistries.BLOCK_ENTITY_TYPE)) {
			return DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, name);
		} else if (registry.equals(BuiltInRegistries.CHUNK_STATUS)) {
			return DeferredRegister.create(ForgeRegistries.CHUNK_STATUS, name);
		} else {
			return registry.equals(BuiltInRegistries.COMMAND_ARGUMENT_TYPE)
					? DeferredRegister.create(ForgeRegistries.COMMAND_ARGUMENT_TYPES, name)
					: null;
		}
	}
}