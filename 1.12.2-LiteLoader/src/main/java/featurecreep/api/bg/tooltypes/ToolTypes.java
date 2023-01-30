package featurecreep.api.bg.tooltypes;

import org.jboss.dmr.ModelNode;

import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;

public class ToolTypes {

	public static ToolType PICKAXE = new ToolType(ItemPickaxe.class);
	public static ToolType SHOVEL = new ToolType(ItemSpade.class);
	public static ToolType HOE = new ToolType(ItemHoe.class);
	public static ToolType AXE = new ToolType(ItemAxe.class);
	public static ToolType SWORD = new ToolType(ItemSword.class);
	public static ToolType BLANK = new ToolType(ModelNode.class);
	//public static ToolType HAND = new ToolType(AirItem.class);

	
}
