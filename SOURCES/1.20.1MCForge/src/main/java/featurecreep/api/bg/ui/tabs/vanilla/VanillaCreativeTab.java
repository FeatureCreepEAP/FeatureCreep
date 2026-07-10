package featurecreep.api.bg.ui.tabs.vanilla;

import featurecreep.api.bg.ui.FCCreativeTab;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;

@Deprecated(forRemoval = true, since = "13")
public class VanillaCreativeTab extends FCCreativeTab {

    private final String legacyName;
    private ResourceKey<CreativeModeTab> cachedKey;

    public VanillaCreativeTab(String legacyName) {
        this.legacyName = legacyName;
        this.setTabName(legacyName);
    }

    @Override
    public CreativeModeTab get() {
        ResourceKey<CreativeModeTab> key = getKey();
        return BuiltInRegistries.CREATIVE_MODE_TAB.getOrThrow(key);
    }

    public ResourceKey<CreativeModeTab> getKey() {
        if (cachedKey == null) {
            cachedKey = mapLegacyName(legacyName);
        }
        return cachedKey;
    }

    private static ResourceKey<CreativeModeTab> mapLegacyName(String name) {
        String path = switch (name) {
            case "BUILDING_BLOCKS" -> "building_blocks";
            case "BREWING" -> "brewing";
            case "COMBAT" -> "combat";
            case "DECORATIONS" -> "colored_blocks"; // adjust if your target MC version differs
            case "FOOD" -> "food_and_drinks";
            case "MATERIALS" -> "ingredients";
            case "MISC" -> "spawn_eggs"; // or another mapping if your legacy meaning differs
            case "REDSTONE" -> "redstone_blocks";
            case "TOOLS" -> "tools_and_utilities";
            case "TRANSPORTATION" -> "functional_blocks"; // adjust if needed
            default -> throw new IllegalArgumentException("Unknown legacy creative tab: " + name);
        };

        return ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            ResourceLocation.withDefaultNamespace(path)
        );
    }
}
