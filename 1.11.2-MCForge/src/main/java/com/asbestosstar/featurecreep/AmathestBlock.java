package com.asbestosstar.featurecreep;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class AmathestBlock extends Block implements IHadModels {

	public AmathestBlock(String name, Material material, float hardness, float resistance, int miningLevel, String tool) {
		super(material);
		// TODO Auto-generated constructor stub
	setUnlocalizedName(name);
	setRegistryName(name);
	setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	
	FeatureCreepMC.BLOCKS.add(this);
	FeatureCreepMC.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels() {
		// TODO Auto-generated method stub
	 FeatureCreepMC.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	
//	public AmathestBlock(String name, Material material, float hardness, float resistance, Item drop)
//	{
//		this(name, material, CreativeTabs.BUILDING_BLOCKS, hardness, resistance, drop, SoundType.STONE);
//	}
//	
}
