package featurecreep.legacy;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;

public class EnchantedAxe extends AxeItem {
	
		public EnchantedAxe(ToolMaterial tier, int attackDamageIn, float attackSpeedIn, Settings builderIn) {
		super(tier, attackDamageIn, attackSpeedIn, builderIn);

	}
	
	public void onCreated(World worldIn, PlayerEntity player, ItemStack stack) {

			
			
			
			stack.addEnchantment(Enchantments.UNBREAKING, 5);
stack.addEnchantment(Enchantments.BANE_OF_ARTHROPODS, 5);
stack.addEnchantment(Enchantments.FIRE_ASPECT, 5);
stack.addEnchantment(Enchantments.FLAME, 5);
stack.addEnchantment(Enchantments.KNOCKBACK, 5);
stack.addEnchantment(Enchantments.MENDING, 5);
stack.addEnchantment(Enchantments.EFFICIENCY, 5);
stack.addEnchantment(Enchantments.FORTUNE, 5);
stack.addEnchantment(Enchantments.INFINITY, 5);
stack.addEnchantment(Enchantments.LUCK_OF_THE_SEA, 5);
stack.addEnchantment(Enchantments.LURE, 5);
stack.addEnchantment(Enchantments.POWER, 5);
stack.addEnchantment(Enchantments.RESPIRATION, 5);
stack.addEnchantment(Enchantments.SHARPNESS, 5);
stack.addEnchantment(Enchantments.SMITE, 5);
stack.addEnchantment(Enchantments.THORNS, 5);
stack.addEnchantment(Enchantments.SILK_TOUCH, 5);
stack.addEnchantment(Enchantments.LOOTING, 5);	
		
		
		}
	
	public void inventoryTick(ItemStack stack, World worldInD, Entity entityIn, int itemSlot, boolean isSelected) {
	     int lvl = EnchantmentHelper.getLevel(Enchantments.LOOTING, stack);
	     if (lvl <= 0) {
	    	 stack.addEnchantment(Enchantments.UNBREAKING, 5);
	 		stack.addEnchantment(Enchantments.BANE_OF_ARTHROPODS, 5);
	 		stack.addEnchantment(Enchantments.FIRE_ASPECT, 5);
	 		stack.addEnchantment(Enchantments.FLAME, 5);
	 		stack.addEnchantment(Enchantments.KNOCKBACK, 5);
	 		stack.addEnchantment(Enchantments.MENDING, 5);
	 		stack.addEnchantment(Enchantments.EFFICIENCY, 5);
	 		stack.addEnchantment(Enchantments.FORTUNE, 5);
	 		stack.addEnchantment(Enchantments.INFINITY, 5);
	 		stack.addEnchantment(Enchantments.LUCK_OF_THE_SEA, 5);
	 		stack.addEnchantment(Enchantments.LURE, 5);
	 		stack.addEnchantment(Enchantments.POWER, 5);
	 		stack.addEnchantment(Enchantments.RESPIRATION, 5);
	 		stack.addEnchantment(Enchantments.SHARPNESS, 5);
	 		stack.addEnchantment(Enchantments.SMITE, 5);
	 		stack.addEnchantment(Enchantments.THORNS, 5);
	 		stack.addEnchantment(Enchantments.SILK_TOUCH, 5);		     
	 		stack.addEnchantment(Enchantments.LOOTING, 5);		     
	 		
	   }
		
	}
	
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
	
}