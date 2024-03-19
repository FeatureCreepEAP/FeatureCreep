package featurecreep.api.bg.registries;

import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.blocks.vanilla.VanillaBlock;
import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.items.vanilla.VanillaItem;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import featurecreep.api.bg.ui.tabs.vanilla.VanillaCreativeTab;
import game.Block;
import game.BlockAsItem;
import game.BuiltInRegistries;
import game.ChunkStatus;
import game.CommandArgParser;
import game.CreativeTab;
import game.EntityActivity;
import game.Item;
import game.ResourceLocation;
import game.TileEntites;
import game.UnitModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class UniversalRegistryGettersAndSetters {

	
	public static Item getItembyName(String registry_name)
	{
		if (GlobalRegistries.getItemByName(registry_name) != null)
		{
			return GlobalRegistries.getItemByName(registry_name).get();
		}else {
			return GameRegistries.getItemFromGameRegistries(registry_name);
		}
				
	}
	
	public static Item getItembyID(int id)
	{
		if (GlobalRegistries.getItemByID(id) != null)
		{
			return GlobalRegistries.getItemByID(id).get();
		}else {
			return GameRegistries.getItemFromGameRegistries(id);
		}
				
	}
	
	
	public static FCItemAPI getFCItembyName(String registry_name)
	{
		if (GlobalRegistries.getItemByName(registry_name) != null)
		{
			return GlobalRegistries.getItemByName(registry_name);
		}else {
			return new VanillaItem(GameRegistries.getItemFromGameRegistries(registry_name), registry_name);
		}
				
	}
	
	public static FCItemAPI getFCItembyID(int id)
	{
		if (GlobalRegistries.getItemByID(id) != null)
		{
			return GlobalRegistries.getItemByID(id);
		}else {
			return new VanillaItem(GameRegistries.getItemFromGameRegistries(id), BuiltInRegistries.ITEMS.getName(GameRegistries.getItemFromGameRegistries(id)).toString());
		}
				
	}

	public static void registerItem(Item item, String registry_name, CreativeTab default_tab, int id)
	{
		
	    ForgeRegistries.ITEMS.register(new ResourceLocation(registry_name), item);
    }
	
	public static void registerItem(FCItemAPI item)
	{System.out.println("Registering "+item.getFCRegistryName());
		registerItem(item.get(),item.getFCRegistryName(), item.getDefaultCreativeTab(), item.getNumberID());
	}
	
	public static CreativeTab getItemGroupByName(String registry_name)
	{
		if (GlobalRegistries.getItemGroupByName(registry_name) != null)
		{
			return GlobalRegistries.getItemGroupByName(registry_name).get();
		}else {
			return GameRegistries.getItemGroupByName(registry_name);
		}
				
	}
	
	public static CreativeTab getItemGroupbyID(int id)
	{
		if (GlobalRegistries.getItemGroupByID(id) != null)
		{
			return GlobalRegistries.getItemGroupByID(id).get();
		}else {
			return GameRegistries.getItemGroupByID(id);
		}
				
	}
	
	
	public static UnifiedItemGroupGetter getFCItemGroupbyName(String registry_name)
	{
		if (GlobalRegistries.getItemGroupByName(registry_name) != null)
		{
			return GlobalRegistries.getItemGroupByName(registry_name);
		}else {
			return new VanillaCreativeTab(registry_name);
		}
				
	}
	
	public static UnifiedItemGroupGetter getFCItemGroupbyID(int id)
	{
		if (GlobalRegistries.getItemGroupByID(id) != null)
		{
			return GlobalRegistries.getItemGroupByID(id);
		}else {
			return new VanillaCreativeTab(getItemGroupbyID(id).getUnlocalisedName().getString());
		}
				
	}

	public static void registerItemGroup(CreativeTab tab, String registry_name, int id)
	{
		
	}
	
	public static void registerItemGroup(UnifiedItemGroupGetter tab)
	{
		registerItemGroup(tab.get(),tab.getTabName(), tab.getID());
	}
	
	
	
	
	
	
	
	
	
	
	public static Block getBlockbyName(String registry_name)
	{
		if (GlobalRegistries.getBlockByName(registry_name) != null)
		{
			return GlobalRegistries.getBlockByName(registry_name).get();
		}else {
			return GameRegistries.getBlockFromGameRegistries(registry_name);
		}
				
	}
	
	public static Block getBlockbyID(int id)
	{
		if (GlobalRegistries.getBlockByID(id) != null)
		{
			return GlobalRegistries.getBlockByID(id).get();
		}else {
			return GameRegistries.getBlockFromGameRegistries(id);
		}
				
	}
	
	
	public static FCBlockAPI getFCBlockbyName(String registry_name)
	{
		if (GlobalRegistries.getBlockByName(registry_name) != null)
		{
			return GlobalRegistries.getBlockByName(registry_name);
		}else {
			return new VanillaBlock(GameRegistries.getBlockFromGameRegistries(registry_name), registry_name);
		}
				
	}
	
	public static FCBlockAPI getFCBlockbyID(int id)
	{
		if (GlobalRegistries.getBlockByID(id) != null)
		{
			return GlobalRegistries.getBlockByID(id);
		}else {
			return new VanillaBlock(GameRegistries.getBlockFromGameRegistries(id), BuiltInRegistries.block.getName(GameRegistries.getBlockFromGameRegistries(id)).toString() );
		}
				
	}

	public static void registerBlock(Block block, String registry_name, CreativeTab default_tab, int id)
	{
		ForgeRegistries.BLOCKS.register(new ResourceLocation(registry_name), block);	
	    ForgeRegistries.ITEMS.register(registry_name, new BlockAsItem(block, new Item.Info()));

	}
	
	public static void registerBlock(FCBlockAPI block)
	{
		registerBlock(block.get(),block.getFCRegistryName(), block.getDefaultCreativeTab(), block.getNumberID());
	}
	
	
	public static void vainillaRegister(game.GameRegistries registry, ResourceLocation rl, Object Entry) {
	//To complete
if(registry.equals(BuiltInRegistries.ITEMS)) {
	ForgeRegistries.ITEMS.register(rl, (Item)Entry);
}else if(registry.equals(BuiltInRegistries.block)){
	ForgeRegistries.BLOCKS.register(rl, (Block)Entry);
}else if(registry.equals(BuiltInRegistries.UNIT_MODIFER)){
	ForgeRegistries.ATTRIBUTES.register(rl, (UnitModifier) Entry);
}else if(registry.equals(BuiltInRegistries.ENTITY_ACTIVITY)){
		ForgeRegistries.ACTIVITIES.register(rl, (EntityActivity) Entry);
}else if(registry.equals(BuiltInRegistries.TILE_ENTITY)){
		ForgeRegistries.BLOCK_ENTITY_TYPES.register(rl, (TileEntites<?>) Entry);
}else if(registry.equals(BuiltInRegistries.CHUNK_STATUS)){
	ForgeRegistries.CHUNK_STATUS.register(rl, (ChunkStatus) Entry);
}else if(registry.equals(BuiltInRegistries.COMMAND_ARGUMENT_TYPE)){
		ForgeRegistries.COMMAND_ARGUMENT_TYPES.register(rl, (CommandArgParser<?, ?>) Entry);
}else {
	game.GameRegistries.register(registry, rl, Entry);
}
		//	ForgeRegistries.CONDITION_SERIALIZERS.register(rl, (Item)Entry);
//	ForgeRegistries.DISPLAY_CONTEXTS.register(rl, (Item)Entry);

	//	ForgeRegistries.BIOME_MODIFIER_SERIALIZERS.register(rl, (Item)Entry);
	//ForgeRegistries.BIOMES.register(rl, (Item)Entry);
//	ForgeRegistries.BLOCK_STATE_PROVIDER_TYPES.register(rl, (Item)Entry);

	
	
}
	
	//Converts NeoForge format to MCForge format
	public static DeferredRegister deferredRegistryConverter(game.GameRegistries registry, String name) {
		if(registry.equals(BuiltInRegistries.ITEMS)) {
			return DeferredRegister.create(ForgeRegistries.ITEMS, name);
		}else if(registry.equals(BuiltInRegistries.block)){
			return DeferredRegister.create(ForgeRegistries.BLOCKS, name);
		}else if(registry.equals(BuiltInRegistries.UNIT_MODIFER)){
			return DeferredRegister.create(ForgeRegistries.ATTRIBUTES, name);
		}else if(registry.equals(BuiltInRegistries.ENTITY_ACTIVITY)){
			return DeferredRegister.create(ForgeRegistries.ACTIVITIES, name);
		}else if(registry.equals(BuiltInRegistries.TILE_ENTITY)){
			return DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, name);
		}else if(registry.equals(BuiltInRegistries.CHUNK_STATUS)){
			return DeferredRegister.create(ForgeRegistries.CHUNK_STATUS, name);
		}else if(registry.equals(BuiltInRegistries.COMMAND_ARGUMENT_TYPE)){
			return DeferredRegister.create(ForgeRegistries.COMMAND_ARGUMENT_TYPES, name);
		}else {
			return null; //At least this will let me debug thought it should be avoided
		}
		
	}
	
	
	
	
	
}
