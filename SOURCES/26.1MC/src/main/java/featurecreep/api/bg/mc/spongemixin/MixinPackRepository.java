package featurecreep.api.bg.mc.spongemixin;

import java.util.Set;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import featurecreep.api.anti_encapsulation.GoogleCommonsImmutableMutaliser;
import featurecreep.api.bg.FCPackLoad;
import featurecreep.api.bg.mc.accessors.PackRepositoryExtension;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.server.packs.repository.RepositorySource;

@Mixin(PackRepository.class)
public abstract class MixinPackRepository implements PackRepositoryExtension {

    @Shadow
    private Set<RepositorySource> sources;

    @Override
    public void addResourcePackFinder(RepositorySource source) {
        // Check if the set is specifically a Google Guava RegularImmutableSet
        if (this.sources.getClass().getName().equals("com.google.common.collect.RegularImmutableSet")) {
            // It is a Google Immutable Set, use the reflection hack
            GoogleCommonsImmutableMutaliser.addToRegularImmutableSet(source, this.sources);
        } else {
            // It is a standard Set (HashSet, LinkedHashSet, etc.), use the normal add method
            this.sources.add(source);
        }
    }

    // Adding remap = false prevents the processor from looking for a refmap
    @Inject(method = "reload", at = @At("HEAD"), remap = false)
    private void onReload(CallbackInfo ci) {
        this.addResourcePackFinder(FCPackLoad.INSTANCE);
        System.out.println("Injected FCPack into PackRepository");
    }
}