package featurecreep.api.bg.blocknitem;

import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.world.item.CreativeModeTab;

@Deprecated(forRemoval = true, since = "13")
public class BlocknItemFieldHolder {

	public String public_modid;
	public String public_name;
	public int number_id;

	// Keep the logical tab reference so construction is safe before
	// CreativeModeTabs.bootstrap() has bound vanilla tab holders.
	public UnifiedItemGroupGetter default_tab_getter;
	public CreativeModeTab default_tab;
}
