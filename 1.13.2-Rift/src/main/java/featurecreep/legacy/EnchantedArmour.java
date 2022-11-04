package featurecreep.legacy;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.world.World;

public class EnchantedArmour extends ArmorItem {
	
	
	public EnchantedArmour(ArmorMaterial materialIn, EquipmentSlot slot, Item.Settings builderIn) {
		super(materialIn, slot, builderIn);
		
	}
	
	public void onCreated(World worldIn, PlayerEntity player, ItemStack stack) {
		//Line from CQ Repoured. Thank You
	stack.addEnchantment(Enchantments.UNBREAKING, 5);
	stack.addEnchantment(Enchantments.FEATHER_FALLING, 5);
	stack.addEnchantment(Enchantments.AQUA_AFFINITY, 5);
	stack.addEnchantment(Enchantments.BANE_OF_ANTHROPODS, 5);
	stack.addEnchantment(Enchantments.BLAST_PROTECTION, 5);
	stack.addEnchantment(Enchantments.FIRE_PROTECTION, 5);
	stack.addEnchantment(Enchantments.PROJECTILE_PROTECTION, 5);
	stack.addEnchantment(Enchantments.PROTECTION, 5);
	stack.addEnchantment(Enchantments.RESPIRATION, 5);
			
			
			
			}
	public void inventoryTick(ItemStack stack, World worldInD, Entity entityIn, int itemSlot, boolean isSelected) {
	     int lvl = EnchantmentHelper.getLevel(Enchantments.LOOTING, stack);
	     if (lvl <= 0) {	
	    		stack.addEnchantment(Enchantments.UNBREAKING, 5);
	    		stack.addEnchantment(Enchantments.FEATHER_FALLING, 5);
	    		stack.addEnchantment(Enchantments.AQUA_AFFINITY, 5);
	    		stack.addEnchantment(Enchantments.BANE_OF_ANTHROPODS, 5);
	    		stack.addEnchantment(Enchantments.BLAST_PROTECTION, 5);
	    		stack.addEnchantment(Enchantments.FIRE_PROTECTION, 5);
	    		stack.addEnchantment(Enchantments.PROJECTILE_PROTECTION, 5);
	    		stack.addEnchantment(Enchantments.PROTECTION, 5);
	    		stack.addEnchantment(Enchantments.RESPIRATION, 5);
	
	    	 
	    	 
	    	 
	     }}
	

	
	
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
	
}