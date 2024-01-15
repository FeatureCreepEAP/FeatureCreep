package featurecreep.api.bg.tooltypes;

import org.jboss.dmr.ModelNode;

import dangerzone.items.ItemAxe;
import dangerzone.items.ItemHoe;
import dangerzone.items.ItemPickAxe;
import dangerzone.items.ItemShovel;
import dangerzone.items.ItemSword;

public class ToolTypes {

	public static ToolType PICKAXE = new ToolType(ItemPickAxe.class);
	public static ToolType SHOVEL = new ToolType(ItemShovel.class);
	public static ToolType HOE = new ToolType(ItemHoe.class);
	public static ToolType AXE = new ToolType(ItemAxe.class);
	public static ToolType SWORD = new ToolType(ItemSword.class);
	public static ToolType BLANK = new ToolType(ModelNode.class);
	//public static ToolType HAND = new ToolType(AirItem.class);

	
}
