package featurecreep.legacy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;

public class GenericPickaxe extends ItemPickaxe{

	protected GenericPickaxe(CreativeTabs tab, ToolMaterial material) {
		super(material);
		// TODO Auto-generated constructor stub
	this.setCreativeTab(tab);
	}

}
