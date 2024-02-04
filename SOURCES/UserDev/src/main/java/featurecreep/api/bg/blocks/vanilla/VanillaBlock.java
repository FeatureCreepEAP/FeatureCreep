package featurecreep.api.bg.blocks.vanilla;

import featurecreep.api.bg.blocks.FCBlockAPI;

/**
TRY TO AVOID THIS CLASS, USE IN THE WRONG CIRCUMSTANCES CAN LEAD TO CRASH/MOD NOT LOADING. STAY WITHIN DOCUMENTATION UNLESS YOU REALLY KNOW WHAT YOU ARE DOING
**/
public class VanillaBlock implements FCBlockAPI<VanillaBlock>{

		public featurecreep.api.bg.blocks.BlockFieldHolder holder = new featurecreep.api.bg.blocks.BlockFieldHolder();
	@Override public featurecreep.api.bg.blocks.BlockFieldHolder holder() {	return holder;	}
	
	
	//public Block vanilla_block; //BE VERY CAREFUL
	//public BlockState state; //BE EVEN MORE CAREFUL WITH THIS, MC SPECIFC TO NEW VERSIONS
	

	
	public VanillaBlock(FCBlockAPI block, String registry_name) //As a backup or for ported items
	{
//		this (block.get(), block.get().getDefaultState(),  registry_name);
	}
	
/*	Use Reflection or Mirror for these if you REALLY know what you are doing
	@Override
	public Block get()
	{
return 	vanilla_block; //BE VERY CAREFUL
	}

	public BlockState getFCBlockState() //BE EVEN MORE CAREFUL WITH THIS, MC SPECIFC TO NEW VERSIONS
	{
		return state;
	}
	
	*/
	//We'ss do all these later, they dont really matter for this right now

	

	@Override
	public VanillaBlock isSingleSided(boolean answer) {
		// TODO Auto-generated method stub
		holder().single_sided = answer;
		return this;
	}
	
}
