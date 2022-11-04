package com.asbestosstar.featurecreep;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;


public class OreGen implements IWorldGenerator
{

	public OreGen() {
	//	tutorialOverworldGen = new WorldGenMinable(ModBlocks.FIRST_BLOCK.getDefaultState(), 15, BlockMatcher.forBlock(Blocks.STONE));
		
	}
	
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		for(GemOre ore : FeatureCreepMC.GEM_ORE)
		{	if(world.provider.getDimensionType().equals(ore.getDimension()))
			{						Utils.generateOre(ore, world, random, chunkX << 4, chunkZ << 4);

			
			}
		}
		for(IngotOre ore : FeatureCreepMC.INGOT_ORE)
		{	if(world.provider.getDimensionType().equals(ore.getDimension()))
			{	
			Utils.generateOre(ore, world, random, chunkX << 4, chunkZ << 4);

		
			}	}
		for(DustOre ore : FeatureCreepMC.DUST_ORE)
		{	if(world.provider.getDimensionType().equals(ore.getDimension()))
			{					Utils.generateOre(ore, world, random, chunkX << 4, chunkZ << 4);

			}	}

}

	
	
	private void genStandard(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int spawnTries, int minHeight, int maxHeight) {
	    if(minHeight < 0) minHeight = 0;
	    if(maxHeight > 255) maxHeight = 255;
	 
	    if(maxHeight < minHeight) {
	        int i = minHeight;
	        minHeight = maxHeight;
	        maxHeight = i;
	    } else if(maxHeight == minHeight) {
	        if(maxHeight < 255) {
	            maxHeight++;
	        } else minHeight--;
	    }
	 
	    BlockPos chunkPosAsBlockPos = new BlockPos(chunkX << 4, 0, chunkZ << 4);
	    int heightDiff = maxHeight - minHeight + 1;
	 
	    for (int i = 0; i < spawnTries; i++) {
	        generator.generate(world, random, 
	        	chunkPosAsBlockPos.add(
	        		random.nextInt(16),
	        		minHeight + random.nextInt(heightDiff),
	        		random.nextInt(16)
	        	)
	        );
	    }
	}




}
