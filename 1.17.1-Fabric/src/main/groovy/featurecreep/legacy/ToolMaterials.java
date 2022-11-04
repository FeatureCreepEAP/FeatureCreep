package featurecreep.legacy;

import java.util.function.Supplier;

import featurecreep.content.FCItems;
import net.minecraft.item.Items;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public enum ToolMaterials implements ToolMaterial {
   TOOL_EMERALD(2, 2000, 10.0F, 9.0F, 10, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{Items.EMERALD});
   }),
   TOOL_AMETHYST(8, 5000, 20.0F, 18.0F, 20, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FCItems.AMETHYST});
   }),
   TOOL_RUBY(12, 8000, 20.0F, 30.0F, 30, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.RUBY});
   }),
   TOOL_TIGERS_EYE(5, 3500, 15.0F, 10.0F, 15, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.TIGERS_EYE});
   }),
   TOOL_OPTIMISED(15, 13000, 75.0F, 8.0F, 45, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.URANIUM});
   }),
   TOOL_ULTIMATE(15, 15000, 30.0F, 50.0F, 45, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.TITANIUM, (ItemConvertible)FeatureCreepMC.URANIUM});
   }),
   TOOL_SAPPHIRE(8, 5000, 20.0F, 18.0F, 20, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.SAPPHIRE});
   });

   private final int harvestLevel;
   private final int maxUses;
   private final float efficiency;
   private final float attackDamage;
   private final int enchantability;
   private final Supplier<Ingredient> repairMaterial;

   private ToolMaterials(int harvestLevel, int maxUses, float efficiency, float damage, int enchantability, Supplier<Ingredient> repairMaterial) {
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

   public float getMiningSpeedMultiplier() {
      return this.efficiency;
   }

   public int getEnchantability() {
      return this.enchantability;
   }

   public int getMiningLevel() {
      return this.harvestLevel;
   }

   public int getDurability() {
      return this.maxUses;
   }

   public Ingredient getRepairIngredient() {
      return (Ingredient)this.repairMaterial.get();
   }
}

