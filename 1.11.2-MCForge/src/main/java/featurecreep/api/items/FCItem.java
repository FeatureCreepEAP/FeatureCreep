package featurecreep.api.items;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jboss.dmr.ModelNode;

import featurecreep.api.ui.tabs.vanilla.VanillaCreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class FCItem extends Item implements FCItemAPI<FCItem>
{
	
		public ItemFieldHolder holder = new ItemFieldHolder();
	@Override public ItemFieldHolder holder() {	return holder;	}
	
	
	
	public FCItem(int id, String modid, String name, CreativeTabs group)
	{
		super();
initialise(id, modid, name, group);
	}

	
	public FCItem(int id, String modid, String name, VanillaCreativeTab group)
	{this(id, modid, name, VanillaCreativeTab.getVanillaGroupFromString(group));}
	
	
	
	
	
}
