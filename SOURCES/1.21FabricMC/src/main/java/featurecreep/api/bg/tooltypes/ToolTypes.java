package featurecreep.api.bg.tooltypes;

import featurecreep.api.dmr.ModelNode;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;

@Deprecated(forRemoval = true, since = "13")

public class ToolTypes {

	public static ToolType PICKAXE = new ToolType(PickaxeItem.class);
	public static ToolType SHOVEL = new ToolType(ShovelItem.class);
	public static ToolType HOE = new ToolType(HoeItem.class);
	public static ToolType AXE = new ToolType(AxeItem.class);
	public static ToolType SWORD = new ToolType(SwordItem.class);
	public static ToolType BLANK = new ToolType(ModelNode.class);
	// public static ToolType HAND = new ToolType(AirItem.class);

}