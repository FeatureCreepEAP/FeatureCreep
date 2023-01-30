package featurecreep.api.bg.blocks.materials;

import net.minecraft.block.Material;

public class VanillaBlockMaterial implements UnifiedBlockMaterial{

	public Material get;
	
	protected VanillaBlockMaterial (Material material)
	{
	get = material;	
	}
	
	protected VanillaBlockMaterial ()
	{
		
	}

	@Override
	public Material get() {
		// TODO Auto-generated method stub
		return get;
	}
	
}
