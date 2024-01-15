package featurecreep.api.bg.blocks.vanilla;

import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.ui.FCCreativeTabs;
import game.Block;
import game.BlockState;

/**
TRY TO AVOID THIS CLASS, USE IN THE WRONG CIRCUMSTANCES CAN LEAD TO CRASH/MOD NOT LOADING. STAY WITHIN DOCUMENTATION UNLESS YOU REALLY KNOW WHAT YOU ARE DOING
**/
public class VanillaBlock implements FCBlockAPI<VanillaBlock>{

	
		public featurecreep.api.bg.blocks.BlockFieldHolder holder = new featurecreep.api.bg.blocks.BlockFieldHolder();
	@Override public featurecreep.api.bg.blocks.BlockFieldHolder holder() {	return holder;	}
	
	public Block vanilla_block; //BE VERY CAREFUL
	public BlockState state; //BE EVEN MORE CAREFUL WITH THIS, MC SPECIFC TO NEW VERSIONS
	
	public VanillaBlock(Block block, BlockState state, String registry_name)
	{
		block	= vanilla_block; //Set the Item to be returned
		setModId(registry_name.split(":")[0]);
		setUnlocName(registry_name.split(":")[1]);
		//registerModels(); We do not need this at this time, maybe in the future we can do something with it
		setDefaultCreativeTab(FCCreativeTabs.TOOLS.get()); //May not work on all versions, we may need to remove this. Yay it works on 1.13 and newer, we will need to check about 1.19.3 though, 1.19.3 does not work but i can put a generic tab. Soon I will make a porting tab though
		setNumberID(block.getIdFromBlockState(state));//Will just be 0 in some versions most likely. Getting from States will be weird though Gotta Maybe find a way to incorperate those
	this.state = state;
	}
	
	
	
	public VanillaBlock(Block block, String registry_name)
	{
	this(block, block.getDefaultState(), registry_name);	
	}
	
	public VanillaBlock(FCBlockAPI block, BlockState state, String registry_name) //As a backup or for ported items
	{
		this (block.get(), state, registry_name);
	}
	
	public VanillaBlock(FCBlockAPI block, String registry_name) //As a backup or for ported items
	{
		this (block.get(), block.get().getDefaultState(),  registry_name);
	}
	
	
	@Override
	public Block get()
	{
return 	vanilla_block; //BE VERY CAREFUL
	}

	public BlockState getFCBlockState() //BE EVEN MORE CAREFUL WITH THIS, MC SPECIFC TO NEW VERSIONS
	{
		return state;
	}
	
	
	//We'ss do all these later, they dont really matter for this right now

	

	@Override
	public VanillaBlock isSingleSided(boolean answer) {
		// TODO Auto-generated method stub
		holder().single_sided = answer;
		return this;
	}
	
}
