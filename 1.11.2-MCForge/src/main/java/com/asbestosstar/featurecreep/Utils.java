package com.asbestosstar.featurecreep;

import java.util.Random;


import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Utils {

	public static void generateOre(GemOre ore, World world, Random random, int x, int z)
	{
		for(int i = 0; i < ore.getTries(); i++)
		{
			BlockPos generationPos = new BlockPos(random.nextInt(16)+x, random.nextInt(ore.getMaximumY()-ore.getMinimumY())+ore.getMinimumY(), random.nextInt(16)+z);
			
			ore.getWorldGenMinable().generate(world, random, generationPos);
		}
	}
	public static void generateOre(IngotOre ore, World world, Random random, int x, int z)
	{
		for(int i = 0; i < ore.getTries(); i++)
		{
			BlockPos generationPos = new BlockPos(random.nextInt(16)+x, random.nextInt(ore.getMaximumY()-ore.getMinimumY())+ore.getMinimumY(), random.nextInt(16)+z);
			
			ore.getWorldGenMinable().generate(world, random, generationPos);
		}
	}
	
	public static void generateOre(DustOre ore, World world, Random random, int x, int z)
	{
		for(int i = 0; i < ore.getTries(); i++)
		{
			BlockPos generationPos = new BlockPos(random.nextInt(16)+x, random.nextInt(ore.getMaximumY()-ore.getMinimumY())+ore.getMinimumY(), random.nextInt(16)+z);
			
			ore.getWorldGenMinable().generate(world, random, generationPos);
		}
	}
	
}
