package featurecreep.legacy;

import java.util.function.Supplier;

import featurecreep.content.FCItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public enum ArmourMaterials implements ArmorMaterial {
	SAND("sand", 3, new int[] {1, 1, 1, 1}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{Blocks.SAND});
   }),
	RED_SAND("red_sand", 3, new int[] {1, 1, 1, 1}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Blocks.RED_SAND});
	   }),
	DIRT("dirt", 5, new int[] {1, 2, 2, 1}, 5, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Blocks.DIRT});
	   }),
	COARSE_DIRT("coarse_dirt", 6, new int[] {1, 2, 2, 1}, 5, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Blocks.COARSE_DIRT});
	   }),
	GRAVEL("gravel", 8, new int[] {1, 2, 2, 1}, 7, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Blocks.GRAVEL});
	   }),
	CACTUS("cactus", 4, new int[] {2, 2, 2, 1}, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Blocks.CACTUS});
	   }),
	COBBLE_STONE("cobble_stone", 15, new int[] {2, 3, 3, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Blocks.COBBLESTONE});
	   }),
	SUN_STONE("sun_stone", 8, new int[] {2, 3, 2, 2}, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Items.EMERALD});
	   }),
	CRYSTAL_WOOD_PLANK("crystal_wood_plank", 20, new int[] {2, 3, 3, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.CRYSTAL_WOOD_PLANK});
	   }),
	BONE("bone", 7, new int[] {2, 3, 2, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Items.BONE});
	   }),
	WOOD_PLANK("wood_plank", 12, new int[] {2, 2, 2, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Blocks.OAK_PLANKS});
	   }),
	WOOD_BLOCK("wood_block", 20, new int[] {3, 3, 3, 2}, 11, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Items.OAK_LOG});
	   }),
	NETHERRACK_BLOODSTONE("netherrack_bloodstone", 17, new int[] {3, 4, 4, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Blocks.NETHERRACK});
	   }),
	STONE("stone", 24, new int[] {3, 4, 3, 3}, 13, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Blocks.STONE});
	   }),
	CRYSTAL_WOOD("crystal_wood", 20, new int[] {4, 4, 4, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.CRYSTAL_WOOD});
	   }),
	FLINT("flint", 25, new int[] {4, 4, 4, 3}, 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Items.FLINT});
	   }),
	COAL("coal", 17, new int[] {4, 4, 4, 3}, 17, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Items.COAL});
	   }),
	KYANITE("kyanite", 21, new int[] {4, 5, 4, 4}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.KYANITE_INGOT});
	   }),
	WAX("wax", 17, new int[] {4, 5, 5, 4}, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.WAX_INGOT});
	   }),
	LAPIS("lapis", 17, new int[] {5, 5, 5, 5}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Items.LAPIS_LAZULI});
	   }),
	RED_STONE("red_stone", 15, new int[] {5, 6, 5, 5}, 35, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Items.REDSTONE});
	   }),
	RED_STONE_BLOCK("red_stone_block", 22, new int[] {10, 10, 10, 10}, 40, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Blocks.REDSTONE_BLOCK});
	   }),
	QUARTZ("quartz", 22, new int[] {6, 7, 7, 6}, 43, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Items.QUARTZ});
	   }),
	QUARTZ_BLOCK("quartz_block", 30, new int[] {11, 12, 11, 11}, 45, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Blocks.QUARTZ_BLOCK});
	   }),
	END_STONE("end_stone", 20, new int[] {8, 8, 8, 7}, 50, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Blocks.END_STONE});
	   }),
	LEAD("lead", 30, new int[] {8, 8, 8, 8}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.LEAD_INGOT});
	   }),
	CALCIUM("calcium", 30, new int[] {8, 9, 9, 8}, 32, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.CALCIUM_INGOT});
	   }),
	URANIUM("uranium", 25, new int[] {21, 22, 21, 21}, 35, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.URANIUM});
	   }),
	TITANIUM("titanium", 35, new int[] {22, 23, 23, 22}, 27, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.TITANIUM});
	   }),
	CELESTIAL("celestial", 35, new int[] {75, 75, 75, 75}, 50, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Items.EMERALD});
	   }),
	EXTRA_CELESTIAL("extra_celestial", 50, new int[] {150, 150, 150, 150}, 40, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Items.EMERALD});
	   }),
	STEEL("steel", 45, new int[] {25, 25, 25, 25}, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.STEEL_INGOT});
	   }),
	OBSIDIAN("obsidian", 43, new int[] {22, 23, 23, 22}, 40, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Blocks.OBSIDIAN});
	   }),
	EMERALD("emerald", 25, new int[] {15, 25, 25, 15}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Items.EMERALD});
	   }),
	AMETHYST("amethyst", 25, new int[]  {10, 20, 20, 10}, 200, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FCItems.AMETHYST});
	   }),
	RUBY("ruby", 50, new int[] {15, 25, 25, 15}, 60, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.RUBY});
	   }),
	RUBY_ON_RAILS("ruby_on_rails", 55, new int[] {30, 70, 70, 30}, 60, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.RUBY});
	   }),
	TIGERS_EYE("tigers_eye", 35, new int[] {15, 20, 20, 15}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.TIGERS_EYE});
	   }),
	ULTIMATE("ultimate", 35, new int[] {50, 75, 75, 50}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.URANIUM});
	   }),
	EXPERIENCE("experience", 25, new int[] {15, 25, 25, 15}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Items.EMERALD});
	   }),
	ROYAL_GUARDIAN("royal_guardian", 35, new int[] {100, 150, 150, 100}, 17, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Items.EMERALD});
	   }),
	QUEEN_SCALE("queen_scale", 20, new int[] {150, 200, 200, 150}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.QueenScale});
	   }),
	MOBZILLA_SCALE("mobzilla_scale", 20, new int[] {75, 100, 100, 75}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.MobzillaScale});
	   }),
	LAPIS_BLOCK("lapis_block", 25, new int[] {5, 10, 10, 5}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Blocks.LAPIS_BLOCK});
	   }),
	LAVA_EEL("lava_eel", 35, new int[] {20, 25, 25, 20}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.LAVA_EEL});
	   }),
	PEACOCK_FEATHER("peacock_feather", 50, new int[] {15, 25, 25, 15}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.PEACKOCK_FEATHER});
	   }),
	PINK_TOURMALINE("pink_tourmaline", 35, new int[] {15, 25, 25, 15}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.PINK_TOURMALINE});
	   }),
	TOURMALINE("tourmaline", 35, new int[] {15, 25, 25, 15}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.TOURMALINE});
	   }),
	WHITE_TOURMALINE("white_tourmaline", 35, new int[] {15, 25, 25, 15}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.WHITE_TOURMALINE});
	   }),
	COPPER("copper", 15, new int[] {2, 5, 15, 10}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.COPPER_INGOT});
	   }),
	SILVER("silver", 20, new int[] {4, 15, 15, 10}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.SILVER_INGOT});
	   }),
	ALUMINIUM("aluminium", 18, new int[] {3, 13, 13, 8}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.ALUMINIUM});
	   }),
	PLATINUM("platinum", 20, new int[] {4, 15, 15, 10}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.PLATINUM_INGOT});
	   }),
	MOTH_SCALE("moth_scale", 18, new int[] {20, 30, 30, 20}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.MothScale});
	   }),
	SAPPHIRE("sapphire", 25, new int[] {10, 20, 20, 10}, 200, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{FeatureCreepMC.SAPPHIRE});
	   }),
	CZ_SLOW("cz_slow", 25, new int[] {2, 3, 3, 2}, 50, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Items.EMERALD});
	   }),
	APPLE_JACK("apple_jack", 50, new int[] {200, 250, 250, 200}, 60, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	      return Ingredient.ofItems(new ItemConvertible[]{Items.EMERALD});
	   }),
   TIN("tin", 35, new int[] {15, 25, 25, 15}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.TIN_INGOT});
   });

	
	
	 private final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
	   private final String name;
	   private final int durability;
	   private final int[] damageReductionAmountArray;
	   private final int enchantability;
	   private final SoundEvent soundOnEquip;
	   private final float toughness;
	   private final float knockbackResistance;
	   private final Supplier<Ingredient> repairMaterial;

	   private ArmourMaterials(String nameIn, int durabilityIn, int[] damageReductionAmountArrayIn, int enchantabilityIn, SoundEvent soundOnEquip, float toughnessIn, float knockbackResistanceIn, Supplier<Ingredient> repairMaterialIn) {
	      this.name = nameIn;
	      this.durability = durabilityIn;
	      this.damageReductionAmountArray = damageReductionAmountArrayIn;
	      this.enchantability = enchantabilityIn;
	      this.soundOnEquip = soundOnEquip;
	      this.toughness = toughnessIn;
	      this.knockbackResistance = knockbackResistanceIn;
	      this.repairMaterial = repairMaterialIn;
	   }
	
	
	
	
	
	
@Override
public int getDurability(EquipmentSlot var1) {
	// TODO Auto-generated method stub
    return this.MAX_DAMAGE_ARRAY[var1.getEntitySlotId()] * this.durability;
}

@Override
public int getProtectionAmount(EquipmentSlot var1) {
	// TODO Auto-generated method stub
    return this.damageReductionAmountArray[var1.getEntitySlotId()];
    }

@Override
public int getEnchantability() {
	// TODO Auto-generated method stub
    return this.enchantability;
    }

@Override
public SoundEvent getEquipSound() {
	// TODO Auto-generated method stub
    return this.soundOnEquip;
}

@Override
public Ingredient getRepairIngredient() {
	// TODO Auto-generated method stub
	   return (Ingredient)this.repairMaterial.get();
	   
}

@Override
public String getName() {
	// TODO Auto-generated method stub
    return this.name;
    
}

@Override
public float getToughness() {
	// TODO Auto-generated method stub
    return this.toughness;
    
}

@Override
public float getKnockbackResistance() {
	// TODO Auto-generated method stub
    return this.knockbackResistance;
    
}


}
