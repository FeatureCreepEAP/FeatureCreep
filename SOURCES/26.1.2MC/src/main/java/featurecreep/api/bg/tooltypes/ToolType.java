package featurecreep.api.bg.tooltypes;

import java.util.Objects;
import java.util.function.Predicate;

import net.minecraft.world.item.ItemStack;

@Deprecated(forRemoval = true, since = "13")
public class ToolType {

	/**
	 * Legacy representative class retained for source/binary compatibility with the
	 * old FeatureCreep API. Minecraft 26.1.x no longer models the vanilla tool kind
	 * by subclasses such as AxeItem/HoeItem/ShovelItem, so new code should use
	 * {@link #matches(ItemStack)} instead.
	 */
	public Class<?> get;

	private final Predicate<ItemStack> matcher;

	public ToolType(Class<?> clazz) {
		this(clazz, stack -> stack != null && !stack.isEmpty()
				&& clazz.isAssignableFrom(stack.getItem().getClass()));
	}

	public ToolType(Class<?> clazz, Predicate<ItemStack> matcher) {
		this.get = Objects.requireNonNull(clazz, "clazz");
		this.matcher = Objects.requireNonNull(matcher, "matcher");
	}

	/**
	 * Tests an item using the modern Minecraft item-component representation.
	 */
	public boolean matches(ItemStack stack) {
		return stack != null && !stack.isEmpty() && matcher.test(stack);
	}
}
