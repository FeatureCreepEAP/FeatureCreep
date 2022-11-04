package featurecreep.api.orespawn;

import net.minecraft.block.Block;

public class OreSpawnBasicConfig {

public Block block;
public int size;
public int frequency;
public int minY;
public int maxY;
	
	public OreSpawnBasicConfig (Block block, int size, int frequency, int minY, int maxY)
	{
		frequency=frequency;
		block=block;
		size=size;
		minY=minY;
		maxY=maxY;
	}
	
	
	

}
