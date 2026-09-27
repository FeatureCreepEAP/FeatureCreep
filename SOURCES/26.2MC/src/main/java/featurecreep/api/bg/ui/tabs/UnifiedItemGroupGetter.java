package featurecreep.api.bg.ui.tabs;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

@Deprecated(forRemoval = true, since = "13")
public interface UnifiedItemGroupGetter {

	public CreativeModeTab get();

	/** Stable registry key when the backing implementation has one. */
	public default ResourceKey<CreativeModeTab> getKey() {
		return null;
	}

	/** Per-instance legacy metadata; never store this on the interface itself. */
	public ItemGroupHolder holder();

	public default void setID(int i) {
		holder().tab_id = i;
	}

	public default int getID() {
		return holder().tab_id;
	}

	public default void setTabName(String name) {
		holder().tab_name = name;
	}

	public default String getTabName() {
		return holder().tab_name;
	}
}
