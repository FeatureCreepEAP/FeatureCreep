package featurecreep.api.bg.ui;

import featurecreep.api.bg.ui.tabs.ItemGroupHolder;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.world.item.CreativeModeTab;

@Deprecated(forRemoval = true, since = "13")
public class FCCreativeTab implements UnifiedItemGroupGetter {

	public String id;
	private final ItemGroupHolder holder = new ItemGroupHolder();

	@Override
	public ItemGroupHolder holder() {
		return holder;
	}

	@Override
	public CreativeModeTab get() {
		return null;
	}
}
