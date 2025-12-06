package featurecreep.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.client.Options;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.server.packs.repository.PackRepository;

@Mixin({OverworldBiomes.class, PackRepository.class, Options.class})
public class OverworldBiomeCreatorBlank {
//Leave blank, Javassist is much easier to use, this only exists so the SpongeMixinPlugin puts this in the transformer, i plan to auto gen these eventually
}
