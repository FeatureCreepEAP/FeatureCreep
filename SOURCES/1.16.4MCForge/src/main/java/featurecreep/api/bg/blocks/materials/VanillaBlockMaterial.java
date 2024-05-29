package featurecreep.api.bg.blocks.materials;

import game.BlockMaterial;

public class VanillaBlockMaterial implements UnifiedBlockMaterial{

	public BlockMaterial get;
	
	protected VanillaBlockMaterial (BlockMaterial material)
	{
	get = material;	
	}
	
	protected VanillaBlockMaterial ()
	{
		
	}

	@Override
	public BlockMaterial get() {
		// TODO Auto-generated method stub
		return get;
	}
	
}
