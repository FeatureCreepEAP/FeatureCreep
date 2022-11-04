package featurecreep.legacy;

import org.dimdev.rift.listener.BlockAdder;
import org.dimdev.rift.listener.ItemAdder;

import net.minecraft.block.Block;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.registry.Registry;

public class GenericBlock extends Block implements BlockAdder, ItemAdder{

	public String translation_key;

	
	public GenericBlock(String name, Settings settings) {
		super(settings);
		// TODO Auto-generated constructor stub
		translation_key=name;

	}

	@Override
	public void registerItems() {
		// TODO Auto-generated method stub
	}

	@Override
	public void registerBlocks() {
		// TODO Auto-generated method stub
  //      Block.register(new ModelIdentifier("halflogs:" + translation_key), this);
    //    Registry.Registry.register(new ModelIdentifier("halflogs:" + translation_key), this);
	}

	
     
  
}
