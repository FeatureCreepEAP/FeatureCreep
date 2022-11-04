package com.asbestosstar.featurecreep;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraft.item.ItemArmor.ArmorMaterial;

public class CZSlowArmour  extends ItemArmor implements IHadModels {

	
	public CZSlowArmour(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		// TODO Auto-generated constructor stub
		
			setUnlocalizedName(name);
			setRegistryName(name);
			setCreativeTab(CreativeTabs.COMBAT);
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
	
	
	
	
	
	
	
		
		@Override
		public void registerModels() {
			// TODO Auto-generated method stub
		 FeatureCreepMC.proxy.registerItemRenderer(this, 0, "inventory");
		}
	
	
	
	
	public void onArmorTick(World worldIn, EntityPlayer player, ItemStack stack) {
	//Line from CQ Repoured. Thank You
		player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 1, 2));

	
	}
	
	
	
}
