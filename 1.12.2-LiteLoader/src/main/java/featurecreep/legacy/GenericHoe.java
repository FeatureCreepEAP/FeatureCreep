package featurecreep.legacy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemHoe;

public class GenericHoe extends ItemHoe{

	protected GenericHoe(CreativeTabs tab, ToolMaterial material) {
		super(material);
		// TODO Auto-generated constructor stub
this.setCreativeTab(tab);
	}

}
