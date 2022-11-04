package com.asbestosstar.featurecreep;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class EmeraldArmour extends ItemArmor implements IHadModels {
	public EmeraldArmour(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
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
}
