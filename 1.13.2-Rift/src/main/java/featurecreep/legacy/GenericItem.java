package featurecreep.legacy;

import org.dimdev.rift.listener.BlockAdder;
import org.dimdev.rift.listener.ItemAdder;

import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class GenericItem extends Item  implements BlockAdder, ItemAdder {

	public String translation_key;
	
	public GenericItem(String name, Settings settings) {
		super(settings);
		// TODO Auto-generated constructor stub
	translation_key=name;
	}

	@Override
	public void registerItems() {
		// TODO Auto-generated method stub
      //  Item.register(this, ItemGroup.BUILDING_BLOCKS);
	//Registry.ITEM.register(new Identifier(translation_key), this);
	}

	@Override
	public void registerBlocks() {
		// TODO Auto-generated method stub
		
	}

}
