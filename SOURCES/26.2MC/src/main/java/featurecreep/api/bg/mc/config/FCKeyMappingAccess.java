package featurecreep.api.bg.mc.config;

/**
 * Implemented on {@code net.minecraft.client.Options} by a FeatureCreep mixin.
 *
 * <p>{@code Options.keyMappings} is a {@code public final} array, so mods cannot
 * append to it directly. The mixin implements this interface and rebuilds the
 * array from {@link FCKeyBridge}'s pending list.
 */
public interface FCKeyMappingAccess {

    /** Appends pending FeatureCreep key mappings that are not yet present. */
    void featurecreep$applyPendingKeyMappings();
}
