package featurecreep.api.bg.tooltypes;

import org.jboss.dmr.ModelNode;

import featurecreep.api.bg.items.tools.FCAxe;
import featurecreep.api.bg.items.tools.FCHoe;
import featurecreep.api.bg.items.tools.FCPickaxe;
import featurecreep.api.bg.items.tools.FCShovel;
import featurecreep.api.bg.items.tools.FCSword;

public class ToolTypes {

	// In the actual FC Versions these are all of the vanilla classes
	public static ToolType PICKAXE = new ToolType(FCPickaxe.class);
	public static ToolType SHOVEL = new ToolType(FCShovel.class);
	public static ToolType HOE = new ToolType(FCHoe.class);
	public static ToolType AXE = new ToolType(FCAxe.class);
	public static ToolType SWORD = new ToolType(FCSword.class);
	public static ToolType BLANK = new ToolType(ModelNode.class);
	// public static ToolType HAND = new ToolType(AirItem.class);

}
