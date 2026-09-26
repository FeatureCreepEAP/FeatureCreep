package featurecreep.api.bg.mc.accessors;

import net.minecraft.server.packs.repository.RepositorySource;

public interface PackRepositoryExtension {
    void addResourcePackFinder(RepositorySource source);
}
