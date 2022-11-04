package featurecreep.legacy;

import java.util.function.Supplier;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;


public enum ToolMaterials implements ToolMaterial {
    TOOL_EMERALD(2, 2000, 10.0F, 9.0F, 10, () -> Ingredient.ofItems(Items.EMERALD.asItem())),
    TOOL_AMETHYST(8, 5000, 20.0F, 18.0F, 20, () -> Ingredient.ofItems(featurecreep.content.FCItems.AMETHYST)),
    TOOL_RUBY(12, 8000, 20.0F, 30.0F, 30, () -> Ingredient.ofItems(FCItems.RUBY.get())),
    TOOL_TIGERS_EYE( 5, 3500, 15.0F, 10.0F, 15, () -> Ingredient.ofItems(FCItems.TIGERS_EYE.get())),
    TOOL_OPTIMISED(15, 13000, 75.0F, 8.0F, 45, () -> Ingredient.ofItems(FCItems.URANIUM.get())),
    TOOL_ULTIMATE(15, 15000, 30.0F, 50.0F, 45, () -> Ingredient.ofItems(FCItems.TITANIUM.get(), FCItems.URANIUM.get())),
    TOOL_SAPPHIRE(8, 5000, 20.0F, 18.0F, 20, () -> Ingredient.ofItems(FCItems.SAPPHIRE.get()));

	
	
	
    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairMaterial;

    ToolMaterials(int harvestLevel, int maxUses, float efficiency, float damage, int enchantability, Supplier<Ingredient> repairMaterial) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = damage;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public float getEfficiency() {
        return this.efficiency;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    public int getMaxUses() {
        return this.maxUses;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

	@Override
	public int getDurability() {
		// TODO Auto-generated method stub
        return this.maxUses;
	}

	@Override
	public int getMiningLevel() {
		// TODO Auto-generated method stub
        return this.harvestLevel;
	}

	@Override
	public float getMiningSpeedMultiplier() {
		// TODO Auto-generated method stub
        return this.efficiency;
	}
}