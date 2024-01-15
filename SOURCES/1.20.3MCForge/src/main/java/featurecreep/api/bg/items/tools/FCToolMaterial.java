package featurecreep.api.bg.items.tools;

import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.items.tools.FCIngredient;
import game.Item;
import game.ItemConvertible;
import game.ToolMaterial;
import game.ToolRepairIngredient;

public class FCToolMaterial
implements ToolMaterial {
    public int harvest;
    public int durability;
    public int speed;
    public int attack;
    public int enchantness;
    public ToolRepairIngredient repair;
    public Item repair_item;

    private FCToolMaterial(int harvestLevel, int maxUses, int efficiency, int damage, int enchantability, ToolRepairIngredient repairMaterial) {
        this.harvest = harvestLevel;
        this.durability = maxUses;
        this.speed = efficiency;
        this.attack = damage;
        this.enchantness = enchantability;
        this.repair = repairMaterial;
    }

    public FCToolMaterial(int harvestLevel, int maxUses, int efficiency, int damage, int enchantability, FCItemAPI repairItem) {
        this(harvestLevel, maxUses, efficiency, damage, enchantability, FCIngredient.ingredientFromItem(repairItem.get()));
    }

    public FCToolMaterial(int harvestLevel, int maxUses, int efficiency, int damage, int enchantability, FCBlockAPI repairItem) {
        this(harvestLevel, maxUses, efficiency, damage, enchantability, FCIngredient.ingredientFromItem(repairItem.get()));
    }

    public int getToolMaxUses() {
        return this.durability;
    }

    public int getToolEfficiency() {
        return this.speed;
    }

    public int getToolAttackDamage() {
        return this.attack;
    }

    public int getToolHarvestLevel() {
        return this.harvest;
    }

    public int getToolEnchantability() {
        return this.enchantness;
    }

    private ToolRepairIngredient getToolRepairIngredient() {
        return this.repair;
    }

    private ItemConvertible getToolRepairItem() {
        return this.repair_item;
    }

    @Override
    public float getAttackDamage() {
        return this.getToolAttackDamage();
    }

    @Override
    public int getHarvestLevel() {
        return this.getToolHarvestLevel();
    }

    @Override
    public int getEnchantability() {
        return this.getToolEnchantability();
    }

    @Override
    public ToolRepairIngredient getRepairIngredient() {
        return this.getToolRepairIngredient();
    }

    @Override
    public int getMaxUses() {
        return this.getToolMaxUses();
    }

    @Override
    public float getEfficiency() {
        return this.getToolEfficiency();
    }
}
