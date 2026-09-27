package featurecreep.api.bg.ui.tabs.vanilla;

import featurecreep.api.bg.ui.tabs.ItemGroupHolder;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

@Deprecated(forRemoval = true, since = "13")
public class VanillaCreativeTab implements UnifiedItemGroupGetter {
	public String tabname;
	private final ItemGroupHolder holder = new ItemGroupHolder();

	@Override
	public ItemGroupHolder holder() {
		return holder;
	}

	public VanillaCreativeTab(String name) {
		tabname = name;
		// Do not resolve CreativeModeTab instances here. In 26.1+ the creative-tab
		// registry contains unbound holders during BuiltInRegistries bootstrap.
		setTabName(name);
		setID(0);
	}

	/**
	 * Returns the stable vanilla creative-tab key without touching the registry.
	 * This is safe during early bootstrap and should be preferred by new code.
	 */
	@Override
	public ResourceKey<CreativeModeTab> getKey() {
		return switch (tabname) {
		case "BUILDING_BLOCKS" -> vanillaKey("building_blocks");
		case "DECORATIONS" -> vanillaKey("functional_blocks");
		case "TRANSPORTATION" -> vanillaKey("tools_and_utilities");
		case "COMBAT" -> vanillaKey("combat");
		case "FOOD" -> vanillaKey("food_and_drinks");
		case "TOOLS" -> vanillaKey("tools_and_utilities");
		case "REDSTONE" -> vanillaKey("redstone_blocks");
		case "MATERIALS", "BREWING", "MISC" -> vanillaKey("ingredients");
		default -> null;
		};
	}

	/**
	 * The vanilla creative-tab keys are private in some 26.x mappings. Build the
	 * same ResourceKey from its stable vanilla identifier instead of referencing
	 * CreativeModeTabs' implementation-private fields. This also avoids resolving
	 * the registry value during early bootstrap.
	 */
	private static ResourceKey<CreativeModeTab> vanillaKey(String path) {
		return ResourceKey.create(
				BuiltInRegistries.CREATIVE_MODE_TAB.key(),
				Identifier.fromNamespaceAndPath("minecraft", path));
	}

	/**
	 * Legacy object lookup. The value is resolved lazily and returns null while
	 * the holder is still unbound instead of throwing during game bootstrap.
	 */
	public static CreativeModeTab getVanillaGroupFromString(VanillaCreativeTab groupname) {
		if (groupname == null) {
			return null;
		}

		ResourceKey<CreativeModeTab> key = groupname.getKey();
		if (key == null) {
			return null;
		}

		var holder = BuiltInRegistries.CREATIVE_MODE_TAB.get(key);
		if (holder.isEmpty() || !holder.get().isBound()) {
			return null;
		}
		return holder.get().value();
	}

	@Override
	public CreativeModeTab get() {
		return getVanillaGroupFromString(this);
	}
}
