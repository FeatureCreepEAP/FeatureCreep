package asbestosstar.bootstrap.sm.mixinhost;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.spongepowered.asm.service.IGlobalPropertyService;
import org.spongepowered.asm.service.IPropertyKey;

import asbestosstar.bootstrap.sm.util.SpongeMixinUtils;

/**
 * In-process blackboard backing Mixin's global property service on standalone hosts.
 * Registered ahead of Mixin's own LaunchWrapper/ModLauncher blackboard providers so
 * ServiceLoader iteration never attempts to load a provider that references
 * {@code net.minecraft.launchwrapper.Launch}.
 *
 * <p>On hosts with their own Mixin runtime the constructor throws, which
 * {@code MixinService.initPropertyService()} treats as an unusable provider and
 * continues past, so the host's blackboard is used instead.</p>
 */
public final class FeatureCreepGlobalPropertyService implements IGlobalPropertyService {
    private static final Map<Key, Object> BOARD = new ConcurrentHashMap<>();

    public FeatureCreepGlobalPropertyService() {
        this(true);
    }

    FeatureCreepGlobalPropertyService(boolean requireStandaloneRuntime) {
        if (requireStandaloneRuntime && !SpongeMixinUtils.isStandaloneRuntimeActive()) {
            throw new IllegalStateException(
                    "FeatureCreep standalone Mixin runtime is not active; deferring to the host global property service");
        }
    }

    @Override
    public IPropertyKey resolveKey(String name) {
        return new Key(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getProperty(IPropertyKey key) {
        return (T) BOARD.get(cast(key));
    }

    @Override
    public void setProperty(IPropertyKey key, Object value) {
        BOARD.put(cast(key), value);
    }

    @Override
    public <T> T getProperty(IPropertyKey key, T defaultValue) {
        T value = this.getProperty(key);
        return value != null ? value : defaultValue;
    }

    @Override
    public String getPropertyString(IPropertyKey key, String defaultValue) {
        Object value = BOARD.get(cast(key));
        return value != null ? value.toString() : defaultValue;
    }

    private static Key cast(IPropertyKey key) {
        return key instanceof Key own ? own : new Key(String.valueOf(key));
    }

    private record Key(String name) implements IPropertyKey {}
}
