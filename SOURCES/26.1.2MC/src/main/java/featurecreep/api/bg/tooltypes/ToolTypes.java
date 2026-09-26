package featurecreep.api.bg.tooltypes;

import featurecreep.api.bg.items.tools.FCAxe;
import featurecreep.api.bg.items.tools.FCHoe;
import featurecreep.api.bg.items.tools.FCPickaxe;
import featurecreep.api.bg.items.tools.FCShovel;
import featurecreep.api.bg.items.tools.FCSword;
import featurecreep.api.dmr.ModelNode;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Block;

@Deprecated(forRemoval = true, since = "13")
public class ToolTypes {

	/*
	 * Since Minecraft 26.1.x, axe/hoe/shovel/pickaxe/sword items are configured on
	 * Item.Properties rather than represented by dedicated Item subclasses. Match
	 * the TOOL component's mining-rule tags instead of testing the Java class.
	 */
	public static ToolType PICKAXE = new ToolType(FCPickaxe.class,
			stack -> hasToolRule(stack, BlockTags.MINEABLE_WITH_PICKAXE));
	public static ToolType SHOVEL = new ToolType(FCShovel.class,
			stack -> hasToolRule(stack, BlockTags.MINEABLE_WITH_SHOVEL));
	public static ToolType HOE = new ToolType(FCHoe.class,
			stack -> hasToolRule(stack, BlockTags.MINEABLE_WITH_HOE));
	public static ToolType AXE = new ToolType(FCAxe.class,
			stack -> hasToolRule(stack, BlockTags.MINEABLE_WITH_AXE));
	public static ToolType SWORD = new ToolType(FCSword.class,
			stack -> hasToolRule(stack, BlockTags.SWORD_INSTANTLY_MINES)
					|| hasToolRule(stack, BlockTags.SWORD_EFFICIENT));
	public static ToolType BLANK = new ToolType(ModelNode.class, stack -> false);

	private static boolean hasToolRule(ItemStack stack, TagKey<Block> miningTag) {
		Tool tool = stack.get(DataComponents.TOOL);
		if (tool == null) {
			return false;
		}

		for (Tool.Rule rule : tool.rules()) {
			if (rule.blocks().unwrapKey().map(miningTag::equals).orElse(false)) {
				return true;
			}
		}
		return false;
	}
}
