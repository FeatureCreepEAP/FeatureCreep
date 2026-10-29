package featurecreep.api.bg.mc.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;

/**
 * Registry for FeatureCreep-owned key mappings.
 *
 * <p>Mappings registered before the client's {@link Options} exists are merged the
 * first time options are loaded, which is when saved bindings ({@code key_<name>})
 * are read. Mappings registered after that are appended to the live options
 * immediately, but their saved binding is only applied on the next options load
 * (next client start). Both paths use the same name-based de-duplication.
 */
public final class FCKeyBridge {

    private static final List<KeyMapping> pending = new ArrayList<KeyMapping>();

    private FCKeyBridge() {
    }

    /**
     * Registers a key mapping for the controls screen and for input handling.
     *
     * @return {@code false} if a mapping with the same name is already registered
     */
    public static boolean register(KeyMapping mapping) {
        Objects.requireNonNull(mapping, "mapping");
        synchronized (pending) {
            for (KeyMapping existing : pending) {
                if (existing.getName().equals(mapping.getName())) {
                    return false;
                }
            }
            pending.add(mapping);
        }
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft != null && minecraft.options instanceof FCKeyMappingAccess access) {
            access.featurecreep$applyPendingKeyMappings();
        }
        return true;
    }

    /**
     * Returns {@code current} with any pending mappings whose names are missing
     * appended. Returns the same array instance when nothing needs to change.
     * Called from the Options mixin, not by mods.
     */
    public static KeyMapping[] merge(KeyMapping[] current) {
        List<KeyMapping> missing = new ArrayList<KeyMapping>();
        synchronized (pending) {
            for (KeyMapping candidate : pending) {
                if (!containsName(current, candidate.getName())) {
                    missing.add(candidate);
                }
            }
        }
        if (missing.isEmpty()) {
            return current;
        }
        KeyMapping[] merged = Arrays.copyOf(current, current.length + missing.size());
        for (int i = 0; i < missing.size(); i++) {
            merged[current.length + i] = missing.get(i);
        }
        return merged;
    }

    /** Snapshot of registered mappings, for diagnostics and tests. */
    public static List<KeyMapping> registered() {
        synchronized (pending) {
            return List.copyOf(pending);
        }
    }

    private static boolean containsName(KeyMapping[] mappings, String name) {
        for (KeyMapping mapping : mappings) {
            if (mapping != null && name.equals(mapping.getName())) {
                return true;
            }
        }
        return false;
    }
}
