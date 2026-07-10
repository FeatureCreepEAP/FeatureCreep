package featurecreep.api.bg.mc.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public final class DeferredHolder<T> {

    private final Registry<T> registry;
    private final ResourceLocation id;
    private final ResourceKey<T> key;

    private T value;
    private boolean resolved;

    DeferredHolder(Registry<T> registry, ResourceLocation id) {
        this.registry = registry;
        this.id = id;
        this.key = null;
    }

    DeferredHolder(Registry<T> registry, ResourceKey<T> key) {
        this.registry = registry;
        this.key = key;
        this.id = null;
    }

    void bind(T value) {
        this.value = value;
        this.resolved = true;
    }

    public T get() {
        if (!resolved) {
            throw new IllegalStateException("Deferred holder not resolved yet: " + id());
        }
        return value;
    }

    public boolean isResolved() {
        return resolved;
    }

    public Registry<T> registry() {
        return registry;
    }

    public ResourceLocation id() {
        return id != null ? id : key.registry();
    }

    public ResourceKey<T> key() {
        return key;
    }
}