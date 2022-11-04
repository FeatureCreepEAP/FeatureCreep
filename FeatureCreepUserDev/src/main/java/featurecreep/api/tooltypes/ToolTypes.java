package featurecreep.api.tooltypes;

import org.jboss.dmr.ModelNode;

import featurecreep.api.items.tools.FCAxe;
import featurecreep.api.items.tools.FCHoe;
import featurecreep.api.items.tools.FCPickaxe;
import featurecreep.api.items.tools.FCShovel;
import featurecreep.api.items.tools.FCSword;

public class ToolTypes {

	//In the actual FC Versions these are all of the vanilla classes
	public static ToolType PICKAXE = new ToolType(FCPickaxe.class);
	public static ToolType SHOVEL = new ToolType(FCShovel.class);
	public static ToolType HOE = new ToolType(FCHoe.class);
	public static ToolType AXE = new ToolType(FCAxe.class);
	public static ToolType SWORD = new ToolType(FCSword.class);
	public static ToolType BLANK = new ToolType(ModelNode.class);
	//public static ToolType HAND = new ToolType(AirItem.class);

	
}
