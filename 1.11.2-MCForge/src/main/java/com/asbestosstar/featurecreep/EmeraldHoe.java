package com.asbestosstar.featurecreep;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;

public class EmeraldHoe extends ItemHoe implements IHadModels {

	public EmeraldHoe(String name, ToolMaterial material) {
		super(material);
		// TODO Auto-generated constructor stub
		
			setUnlocalizedName(name);
			setRegistryName(name);
			setCreativeTab(CreativeTabs.TOOLS);
			FeatureCreepMC.ITEMS.add(this);
	}
		
		
		@Override
		public void registerModels() {
			// TODO Auto-generated method stub
		 FeatureCreepMC.proxy.registerItemRenderer(this, 0, "inventory");
		}

	
	
	
	
	
	
	
	
	
	}

	
	
	
	
	
	
	

