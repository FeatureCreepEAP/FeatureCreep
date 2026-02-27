package featurecreep.api.bg.items.tools;

import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.items.FCItemAPI;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class FCToolMaterial {

	public int harvest;
	public int durability;
	public int speed;
	public int attack;
	public int enchantness;
	public Ingredient repair;
	public Item repair_item;

	private FCToolMaterial(int harvestLevel, int maxUses, int efficiency, int damage, int enchantability,
			Ingredient repairMaterial) {
		harvest = harvestLevel;
		durability = maxUses;
		speed = (int) efficiency;
		attack = (int) damage;
		enchantness = enchantability;
		repair = repairMaterial;
		// repair = new Lazy<Ingredient>(repairMaterial);

	}

	public FCToolMaterial(int harvestLevel, int maxUses, int efficiency, int damage, int enchantability,
			FCItemAPI repairItem) {

		this(harvestLevel, maxUses, efficiency, damage, enchantability,
				FCIngredient.ingredientFromItem(repairItem.get()));
	}

	public FCToolMaterial(int harvestLevel, int maxUses, int efficiency, int damage, int enchantability,
			FCBlockAPI repairItem) {

		this(harvestLevel, maxUses, efficiency, damage, enchantability,
				FCIngredient.ingredientFromItem(repairItem.get()));
	}

	public int getToolMaxUses() {
		// TODO Auto-generated method stub
		return durability;
	}

	public int getToolEfficiency() {
		// TODO Auto-generated method stub
		return speed;
	}

	public int getToolAttackDamage() {
		// TODO Auto-generated method stub
		return attack;
	}

	public int getToolHarvestLevel() {
		// TODO Auto-generated method stub
		return harvest;
	}

	public int getToolEnchantability() {
		// TODO Auto-generated method stub
		return enchantness;
	}

	// TODO make these return stuff eventually and public
	private Ingredient getToolRepairIngredient() {
		// TODO Auto-generated method stub
		return repair;
	}

	private ItemLike getToolRepairItem() {
		// TODO Auto-generated method stub
		return repair_item;
	}




	// En FCToolMaterial
	public ToolMaterial toMinecraftToolMaterial() {
	    // Asumimos que "harvest" indica el tipo de material
	    TagKey<Block> incorrectBlocks;
	    TagKey<Item> repairTag;

	    // Inferimos el tag de bloques incorrectos por el nivel de harvest
	    if (repair_item != null) {
	        // Intentamos deducir el tag por el ítem de reparación
	        if (repair_item.builtInRegistryHolder().is(ItemTags.WOODEN_TOOL_MATERIALS)) {
	            incorrectBlocks = BlockTags.INCORRECT_FOR_WOODEN_TOOL;
	            repairTag = ItemTags.WOODEN_TOOL_MATERIALS;
	        } else if (repair_item.builtInRegistryHolder().is(ItemTags.STONE_TOOL_MATERIALS)) {
	            incorrectBlocks = BlockTags.INCORRECT_FOR_STONE_TOOL;
	            repairTag = ItemTags.STONE_TOOL_MATERIALS;
	        } else if (repair_item.builtInRegistryHolder().is(ItemTags.IRON_TOOL_MATERIALS)) {
	            incorrectBlocks = BlockTags.INCORRECT_FOR_IRON_TOOL;
	            repairTag = ItemTags.IRON_TOOL_MATERIALS;
	        } else if (repair_item.builtInRegistryHolder().is(ItemTags.DIAMOND_TOOL_MATERIALS)) {
	            incorrectBlocks = BlockTags.INCORRECT_FOR_DIAMOND_TOOL;
	            repairTag = ItemTags.DIAMOND_TOOL_MATERIALS;
	        } else if (repair_item.builtInRegistryHolder().is(ItemTags.GOLD_TOOL_MATERIALS)) {
	            incorrectBlocks = BlockTags.INCORRECT_FOR_GOLD_TOOL;
	            repairTag = ItemTags.GOLD_TOOL_MATERIALS;
	        } else if (repair_item.builtInRegistryHolder().is(ItemTags.NETHERITE_TOOL_MATERIALS)) {
	            incorrectBlocks = BlockTags.INCORRECT_FOR_NETHERITE_TOOL;
	            repairTag = ItemTags.NETHERITE_TOOL_MATERIALS;
	        } else {
	            // Por defecto: piedra
	            incorrectBlocks = BlockTags.INCORRECT_FOR_STONE_TOOL;
	            repairTag = ItemTags.STONE_TOOL_MATERIALS;
	        }
	    } else {
	        // Si no hay repair_item, usamos un tag genérico (esto no es ideal)
	        incorrectBlocks = BlockTags.MINEABLE_WITH_SHOVEL; // fallback
	        repairTag = ItemTags.PLANKS; // fallback
	    }

	    return new ToolMaterial(
	        incorrectBlocks,
	        this.durability,
	        this.speed,
	        this.attack, // attackDamageBonus
	        this.enchantness,
	        repairTag
	    );
	}
	
	
}