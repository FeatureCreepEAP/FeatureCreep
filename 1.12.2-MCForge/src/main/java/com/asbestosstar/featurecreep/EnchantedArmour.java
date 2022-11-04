package com.asbestosstar.featurecreep;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EnchantedArmour extends ItemArmor implements IHadModels {
	public EnchantedArmour(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		// TODO Auto-generated constructor stub
		
			setUnlocalizedName(name);
			setRegistryName(name);
	//		setCreativeTab(CreativeTabs.COMBAT);
			FeatureCreepMC.ITEMS.add(this);
	armourName = name;
	}
	public String armourName;	
	
	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
	{
		if(stack.toString().contains("leggings"))
		{
			return "featurecreep:" + armourName + "_2.png";
		}
	if(stack.toString().contains("Leggings")) //if (itemID == ExampleMod.EmeraldLeggings.item);
	{
		return "featurecreep:" + armourName + "_2.png";

		
	}
	return "featurecreep:" + armourName + "_1.png";

	}
	
	public void onArmorTick(World worldIn, EntityPlayer player, ItemStack stack) {
	//Line from CQ Repoured. Thank You
		if(!stack.isItemEnchanted()) {
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


	}
	
	
	
	
	
		
		@Override
		public void registerModels() {
			// TODO Auto-generated method stub
		 FeatureCreepMC.proxy.registerItemRenderer(this, 0, "inventory");
		}
}
