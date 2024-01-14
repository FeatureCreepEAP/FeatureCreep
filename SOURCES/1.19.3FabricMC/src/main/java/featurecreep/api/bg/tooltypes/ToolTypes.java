package featurecreep.api.bg.tooltypes;

import org.jboss.dmr.ModelNode;

import game.Axe;
import game.Hoe;
import game.Pickaxe;
import game.Spade;
import game.Sword;

public class ToolTypes {

	public static ToolType PICKAXE = new ToolType(Pickaxe.class);
	public static ToolType SHOVEL = new ToolType(Spade.class);
	public static ToolType HOE = new ToolType(Hoe.class);
	public static ToolType AXE = new ToolType(Axe.class);
	public static ToolType SWORD = new ToolType(Sword.class);
	public static ToolType BLANK = new ToolType(ModelNode.class);
	//public static ToolType HAND = new ToolType(AirItem.class);

	
}
