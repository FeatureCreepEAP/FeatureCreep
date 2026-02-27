package featurecreep.api.bg.tooltypes;

import org.jboss.dmr.ModelNode;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;

public class ToolTypes {

	public static ToolType PICKAXE = new ToolType(Item.class);
	public static ToolType SHOVEL = new ToolType(ShovelItem.class);
	public static ToolType HOE = new ToolType(HoeItem.class);
	public static ToolType AXE = new ToolType(AxeItem.class);
	public static ToolType SWORD = new ToolType(Item.class);
	public static ToolType BLANK = new ToolType(ModelNode.class);
	// public static ToolType HAND = new ToolType(AirItem.class);

}