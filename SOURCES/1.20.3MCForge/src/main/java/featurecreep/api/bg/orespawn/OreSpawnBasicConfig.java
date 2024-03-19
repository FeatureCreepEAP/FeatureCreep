package featurecreep.api.bg.orespawn;

import game.Block;

public class OreSpawnBasicConfig {

	public Block block;
	public int size;
	public int frequency;
	public int minY;
	public int maxY;
	public String name;

	public OreSpawnBasicConfig(String name, Block block, int size, int frequency, int minY, int maxY) {
		this.frequency = frequency;
		this.block = block;
		this.size = size;
		this.minY = minY;
		this.maxY = maxY;
		this.name = name;
	}

}