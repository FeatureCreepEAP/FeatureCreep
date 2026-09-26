package asbestosstar.bootstrap.sm.util;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import asbestosstar.bootstrap.installer.ArtifactCoordinate;
import asbestosstar.bootstrap.installer.ArtifactResolver;

/**
 * Resolves a complete standalone Mixin runtime only for hosts which do not
 * provide one. Host-owned environments (Fabric/Forge/NeoForge/Sponge) must
 * never call this.
 */
final class StandaloneMixinResolver {
    static final String MIXIN_VERSION = "0.8.7";
    static final String ASM_VERSION = "9.5";

    private static final List<ArtifactCoordinate> RUNTIME = List.of(
            new ArtifactCoordinate("org.spongepowered", "mixin", MIXIN_VERSION, ArtifactResolver.SPONGE),
            new ArtifactCoordinate("org.ow2.asm", "asm", ASM_VERSION, ArtifactResolver.CENTRAL),
            new ArtifactCoordinate("org.ow2.asm", "asm-analysis", ASM_VERSION, ArtifactResolver.CENTRAL),
            new ArtifactCoordinate("org.ow2.asm", "asm-commons", ASM_VERSION, ArtifactResolver.CENTRAL),
            new ArtifactCoordinate("org.ow2.asm", "asm-tree", ASM_VERSION, ArtifactResolver.CENTRAL),
            new ArtifactCoordinate("org.ow2.asm", "asm-util", ASM_VERSION, ArtifactResolver.CENTRAL));

    private StandaloneMixinResolver() {}

    static boolean ensureAvailable() {
        if (SpongeMixinUtils.isStandaloneRuntimeComplete()) return true;
        try {
            ArtifactResolver resolver = new ArtifactResolver();
            List<Path> jars = new ArrayList<>(RUNTIME.size());
            for (ArtifactCoordinate coordinate : RUNTIME) jars.add(resolver.resolve(coordinate));
            SpongeMixinUtils.installRuntimeJars(jars);
            return SpongeMixinUtils.isStandaloneRuntimeComplete();
        } catch (Throwable t) {
            System.err.println("[FeatureCreep] Could not resolve standalone Sponge Mixin " + MIXIN_VERSION
                    + " + ASM " + ASM_VERSION + ": " + t);
            return false;
        }
    }
}
