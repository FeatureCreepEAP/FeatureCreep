package featurecreep.api.tooltypes;

import org.jboss.dmr.ModelNode;

import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;

public class ToolTypes {

	public static ToolType PICKAXE = new ToolType(PickaxeItem.class);
	public static ToolType SHOVEL = new ToolType(ShovelItem.class);
	public static ToolType HOE = new ToolType(HoeItem.class);
	public static ToolType AXE = new ToolType(AxeItem.class);
	public static ToolType SWORD = new ToolType(SwordItem.class);
	public static ToolType BLANK = new ToolType(ModelNode.class);
	//public static ToolType HAND = new ToolType(AirItem.class);

	
}
