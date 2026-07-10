package featurecreep.api.bg.mc.spongemixin;

import java.util.Set;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
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

    @Mutable
    @Final
    @Shadow
    private Set<RepositorySource> sources;

    @Override
    public void addResourcePackFinder(RepositorySource source) {
        this.sources = GoogleCommonsImmutableMutaliser.addToSet(source, this.sources);
    }

    @Inject(method = "reload", at = @At("HEAD"), remap = false)
    private void onReload(CallbackInfo ci) {
        this.addResourcePackFinder(FCPackLoad.INSTANCE);
        System.out.println("Injected FCPack into PackRepository");
    }
}
