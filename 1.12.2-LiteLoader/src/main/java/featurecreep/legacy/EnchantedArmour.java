package featurecreep.legacy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EnchantedArmour extends GenericArmour {
	
	

	public EnchantedArmour(CreativeTabs tab, ArmorMaterial materialIn, int renderIndexIn,
			EntityEquipmentSlot equipmentSlotIn) {
		super(tab, materialIn, renderIndexIn, equipmentSlotIn);
		// TODO Auto-generated constructor stub
	}
	public void onCreated(World worldIn, EntityPlayer player, ItemStack stack) {
		//Line from CQ Repoured. Thank You
	stack.addEnchantment(Enchantments.UNBREAKING, 5);
	stack.addEnchantment(Enchantments.FEATHER_FALLING, 5);
	stack.addEnchantment(Enchantments.AQUA_AFFINITY, 5);
	stack.addEnchantment(Enchantments.BANE_OF_ARTHROPODS, 5);
	stack.addEnchantment(Enchantments.BLAST_PROTECTION, 5);
	stack.addEnchantment(Enchantments.FIRE_PROTECTION, 5);
	stack.addEnchantment(Enchantments.PROJECTILE_PROTECTION, 5);
	stack.addEnchantment(Enchantments.PROTECTION, 5);
	stack.addEnchantment(Enchantments.RESPIRATION, 5);
			
			
			
			}
	public void inventoryTick(ItemStack stack, World worldInD, Entity entityIn, int itemSlot, boolean isSelected) {
	     int lvl = EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, stack);
	     if (lvl <= 0) {	
	    		stack.addEnchantment(Enchantments.UNBREAKING, 5);
	    		stack.addEnchantment(Enchantments.FEATHER_FALLING, 5);
	    		stack.addEnchantment(Enchantments.AQUA_AFFINITY, 5);
	    		stack.addEnchantment(Enchantments.BANE_OF_ARTHROPODS, 5);
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