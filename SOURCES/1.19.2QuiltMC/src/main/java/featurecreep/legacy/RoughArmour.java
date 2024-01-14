package featurecreep.legacy;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.world.World;

public class RoughArmour extends ArmorItem {
	
	
	public RoughArmour(ArmorMaterial materialIn, EquipmentSlot slot, Item.Settings builderIn) {
		super(materialIn, slot, builderIn);
		
	}
	
	public void onCreated(World worldIn, PlayerEntity player, ItemStack stack) {
	
	stack.addEnchantment(Enchantments.THORNS, 5);
			
			
			
			}
	public void inventoryTick(ItemStack stack, World worldInD, Entity entityIn, int itemSlot, boolean isSelected) {
	     int lvl = EnchantmentHelper.getLevel(Enchantments.THORNS, stack);
	     if (lvl <= 0) {	
	    		stack.addEnchantment(Enchantments.THORNS, 5);
	 
	    	 
	    	 
	     }}
	

	
	
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
	
}