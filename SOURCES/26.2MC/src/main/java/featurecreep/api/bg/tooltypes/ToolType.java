package featurecreep.api.bg.tooltypes;

import net.minecraft.world.item.ItemStack;

@Deprecated(forRemoval = true, since = "13")

public class ToolType {

	public Class get;

	public ToolType(Class clazz) {
		get = clazz;
	}

	public boolean matches(ItemStack stack) {
		return stack != null && !stack.isEmpty() && get.isInstance(stack.getItem());
	}

}
