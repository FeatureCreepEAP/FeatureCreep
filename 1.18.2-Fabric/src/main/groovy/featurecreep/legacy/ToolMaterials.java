package featurecreep.legacy;

import java.util.function.Supplier;

import featurecreep.content.FCItems;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public enum ToolMaterials implements ToolMaterial {
	TOOL_EMERALD(9, 2000, 10.0F, 25.0F, 10, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{Items.EMERALD});
   }),
	TOOL_AMETHYST(8, 5000, 20.0F, 18.0F, 20, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FCItems.AMETHYST});
   }),
	TOOL_RUBY(12, 8000, 20.0F, 30.0F, 20, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.RUBY});
   }),
	TOOL_TIGERS_EYE( 10, 6000, 15.0F, 20.0F, 15, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.TIGERS_EYE});
   }),
	TOOL_ULTIMATE(30, 30000, 30.0F, 70.0F, 45, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.TITANIUM, (ItemConvertible)FeatureCreepMC.URANIUM});
   }),
	TOOL_SAPPHIRE(8, 5000, 20.0F, 18.0F, 20, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.SAPPHIRE});
   }),
	LEATHER(0, 59, 2.0F, 0.0F, 15, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Items.LEATHER});
	   }),
	CHAINMAIL(1, 131, 4.0F, 1.0F, 5, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Blocks.FIRE});
	   }),
	SAND(0, 17, 1.0F, 0.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Blocks.SAND});
	   }),
	RED_SAND(0, 17, 1.0F, 0.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Blocks.RED_SAND});
	   }),
	DIRT(0, 23, 1.0F, 0.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Blocks.DIRT});
	   }),
	COARSE_DIRT(1, 25, 5.0F, 1.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Blocks.COARSE_DIRT});
	   }),
	GRAVEL(1, 28, 5.0F, 1.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Blocks.GRAVEL});
	   }),
	CACTUS(1, 20, 5.0F, 25.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Blocks.CACTUS});
	   }),
	SUN_STONE(1, 30, 3.0F, 3.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.SUN_STONE});
	   }),
	BONE(1, 27, 3.0F, 4.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Items.BONE});
	   }),
	CRYSTAL_WOOD_PLANK(2, 160, 5.0F, 5.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.CRYSTAL_WOOD_PLANK});
	   }),
	WOOD_BLOCK(2, 175, 5.0F, 6.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Blocks.OAK_WOOD});
	   }),
	STONE(2, 200, 5.0F, 5.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Blocks.STONE});
	   }),
	NETHERRACK_BLOODSTONE(2, 145, 5.0F, 8.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Blocks.NETHERRACK});
	   }),
	CRYSTAL_WOOD(3, 165, 6.0F, 8.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.CRYSTAL_WOOD});
	   }),
	FLINT(3, 2500, 6.0F, 5.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Items.FLINT});
	   }),
	COAL(3, 2000, 8.0F, 7.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Items.COAL});
	   }),
	KYANITE(4, 2000, 6.0F, 8.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.KYANITE_INGOT});
	   }),
	WAX(4, 1000, 7.0F, 9.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.WAX_INGOT});
	   }),
	LAPIS(4,1100 , 7.0F, 9.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Items.LAPIS_LAZULI});
	   }),
	RED_STONE(5, 1200, 8.0F, 10.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Items.REDSTONE});
	   }),
	QUARTZ(5, 1500, 8.0F, 10.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Items.QUARTZ});
	   }),
	END_STONE(6, 1100, 9.0F, 12.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Items.END_STONE});
	   }),
	LEAD(6, 5000, 9.0F, 11.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.LEAD_INGOT});
	   }),
	CALCIUM(6, 1200, 9.0F, 15.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.CALCIUM_INGOT});
	   }),
	RED_STONE_BLOCK(7, 3000, 9.0F, 13.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Blocks.REDSTONE_BLOCK});
	   }),
	QUARTZ_BLOCK(8, 4500, 14.0F, 17.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Blocks.QUARTZ_BLOCK});
	   }),
	SAPPHIRE(8, 5000, 20.0F, 20.0F, 20, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.SAPPHIRE});
	   }),
	URANIUM(13, 4000, 30.0F, 23.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.URANIUM});
	   }),
	TITANIUM(13, 5500, 27.0F, 20.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.TITANIUM});
	   }),
	OBSIDIAN(13, 8000, 28.0F, 20.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Blocks.OBSIDIAN});
	   }),
	STEEL(13, 8000, 29.0F, 24.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.STEEL_INGOT});
	   }),
	CELESTIAL(35, 25000, 80.0F, 34.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Items.EMERALD});
	   }),
	EXTRA_CELESTIAL(50, 30000, 95.0F, 50.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Items.EMERALD});
	   }),
	RUBY_ON_RAILS(25, 25000, 60.0F, 30.0F, 25, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.RUBY});
	   }),
	EXPERIENCE(9, 2000, 10.0F, 25.0F, 10, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Items.EMERALD});
	   }),
	COPPER( 6, 3000, 9.0F, 10.0F, 15, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.COPPER_INGOT});
	   }),
	LAPIS_BLOCK( 6, 4000, 9.0F, 11.0F, 15, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Blocks.LAPIS_BLOCK});
	   }),
	ALUMINIUM( 6, 4000, 9.0F, 12.0F, 15, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.ALUMINIUM});
	   }),
	SILVER( 7, 4000, 10.0F, 13.0F, 15, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.SILVER_INGOT});
	   }),
	PLATINUM( 7, 5000, 10.0F, 14.0F, 15, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.PLATINUM_INGOT});
	   }),
	TIN( 9, 1000, 21.0F, 15.0F, 15, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.TIN_INGOT});
	   }),
	PEACOCK_FEATHER( 10, 3000, 23.0F, 18.0F, 15, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.PEACKOCK_FEATHER});
	   }),
	PINK_TOURMALINE( 10, 4000, 22.0F, 16.0F, 15, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.PINK_TOURMALINE});
	   }),
	WHITE_TOURMALINE( 10, 4000, 22.0F, 16.0F, 15, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.WHITE_TOURMALINE});
	   }),
	TOURMALINE( 10, 4000, 22.0F, 16.0F, 15, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.TOURMALINE});
	   }),
	LAVA_EEL(13, 3000, 30.0F, 25.0F, 20, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.LAVA_EEL});
	   }),
	MOTH_SCALE(14, 2000, 33.0F, 28.0F, 20, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.MothScale});
	   }),
	MOBZILLA_SCALE(40, 20000, 85.0F, 30.0F, 20, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.MobzillaScale});
	   }),
	APPLE(40, 20000, 85.0F, 30.0F, 20, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.EQUESTRIA_APPLE});
	   }),
	ROYAL_GUARDIAN(45, 30000, 90.0F, 45.0F, 20, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Items.EMERALD});
	   }),
ROYAL_GUARDIAN_MEGA(45, 30000, 90.0F, 750.0F, 20, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)Items.EMERALD});
	   }),
	QUEEN_SCALE(55, 20000, 100.0F, 55.0F, 20, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.QueenScale});
	   }),
	TOOL_OPTIMISED(250, 25000, 100.0F, 8.0F, 45, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.STEEL_INGOT});
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

