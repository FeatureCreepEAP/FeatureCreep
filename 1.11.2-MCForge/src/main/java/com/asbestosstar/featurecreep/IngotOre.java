package com.asbestosstar.featurecreep;

import java.util.Random;

import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.storage.loot.LootTableList;

public class IngotOre extends Block{

	
	
	private DimensionType dimension;
	private int minimumY;
	private int maximumY;
	private int group;
	private int tries;
	private Predicate<IBlockState> blockReplaced;
	private WorldGenMinable worldGenMinable;
	
	
	
	public IngotOre(String name, Material material, float hardness, float resistance, int miningLevel, String tool, DimensionType dimension, int minY, int maxY, int group, int tries, Predicate<IBlockState> blockReplaced)
	{
		super(material);
	
	
		
		this.dimension = dimension;
		this.minimumY = minY;
		this.maximumY = maxY;
		this.group = group;
		this.tries = tries;
		this.blockReplaced = blockReplaced;
		this.worldGenMinable = new WorldGenMinable(this.getDefaultState(), tries, blockReplaced);
		this.setCreativeTab(CreativeTabs.MATERIALS);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
//this.getItemDropped(getDefaultState(), RANDOM, 1);
		
		
		FeatureCreepMC.INGOT_ORE.add(this);
		FeatureCreepMC.BLOCKS.add(this);
		FeatureCreepMC.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		//LootTableList.register(new ResourceLocation(Reference.MODID, name));

		
		
		
		
		
	}
	

	

	public DimensionType getDimension()
	{
		return dimension;
	}
	
	public int getMinimumY()
	{
		return minimumY;
	}
	
	public int getMaximumY()
	{
		return maximumY;
	}
	
	public int getSize()
	{
		return group;
	}
	
	public int getTries()
	{
		return tries;
	}
	
	public WorldGenMinable getWorldGenMinable()
	{
		return worldGenMinable;
	}
	
	public void registerModels() {
		// TODO Auto-generated method stub
	 FeatureCreepMC.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	
	
	
	
	
	
	
}
