package featurecreep.api.bg.mc.spongemixin;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import com.google.common.collect.ImmutableMap;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import featurecreep.api.bg.FCPackLoad;
import featurecreep.api.bg.mc.accessors.PackRepositoryExtension;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.server.packs.repository.RepositorySource;

/**
 * Adds FeatureCreep repository sources without modifying Minecraft's own
 * PackRepository.sources field.
 *
 * Minecraft 26.1+ stores that field as an immutable Guava set. Forge/Fabric and
 * other loaders/mods may also transform or replace it, so mutating or replacing
 * the field is intentionally avoided. Instead, extra sources are evaluated after
 * vanilla/loader discovery has completed and their packs are merged into the
 * discovered map.
 */
@Mixin(PackRepository.class)
public abstract class MixinPackRepository implements PackRepositoryExtension {

    /**
     * FeatureCreep-owned sources only. This is deliberately separate from
     * PackRepository.sources so loader/mod ownership of that field is untouched.
     * Lazily initialized to avoid relying on mixin constructor/field-initializer
     * behavior across loaders.
     */
    @Unique
    private Set<RepositorySource> featurecreep$additionalSources;

    @Unique
    private Set<RepositorySource> featurecreep$getAdditionalSources() {
        if (this.featurecreep$additionalSources == null) {
            this.featurecreep$additionalSources = new LinkedHashSet<>();
        }
        return this.featurecreep$additionalSources;
    }

    @Override
    public void addResourcePackFinder(RepositorySource source) {
        this.featurecreep$getAdditionalSources().add(Objects.requireNonNull(source, "source"));
    }

    /**
     * Minecraft's discoverAvailable() already asks every vanilla/loader source to
     * populate a local TreeMap and returns an immutable copy. Add FeatureCreep's
     * sources to a copy of that result instead of touching the source field.
     *
     * Injecting at RETURN also composes with loaders/mods that add their own
     * RepositorySource instances: whatever they discovered is already present in
     * cir.getReturnValue() before FeatureCreep adds its packs.
     */
    @Inject(method = "discoverAvailable", at = @At("RETURN"), cancellable = true, remap = false)
    private void featurecreep$includeAdditionalSources(CallbackInfoReturnable<Map<String, Pack>> cir) {
        Map<String, Pack> base = cir.getReturnValue();
        if (base == null) {
            return;
        }

        Set<RepositorySource> sources = this.featurecreep$getAdditionalSources();
        sources.add(FCPackLoad.INSTANCE);

        if (sources.isEmpty()) {
            return;
        }

        TreeMap<String, Pack> discovered = new TreeMap<>(base);
        for (RepositorySource source : sources) {
            source.loadPacks(pack -> {
                if (pack != null) {
                    // Match vanilla PackRepository semantics: later sources win
                    // when two sources expose the same pack id.
                    discovered.put(pack.getId(), pack);
                }
            });
        }

        if (!discovered.equals(base)) {
            cir.setReturnValue(ImmutableMap.copyOf(discovered));
        }
    }
}
