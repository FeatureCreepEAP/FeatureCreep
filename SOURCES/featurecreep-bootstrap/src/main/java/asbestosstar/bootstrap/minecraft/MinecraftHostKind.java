package asbestosstar.bootstrap.minecraft;

/** Minecraft launch environment detected by the FeatureCreep bootstrap. */
public enum MinecraftHostKind {
    FABRIC(true, true),
    FORGE(true, true),
    NEOFORGE(true, true),
    SPONGE(true, true),
    RIFT(false, true),
    NIL(false, true),
    VANILLA(false, false);

    private final boolean hostOwnsMixin;
    private final boolean hostHasTransformerPipeline;

    MinecraftHostKind(boolean hostOwnsMixin, boolean hostHasTransformerPipeline) {
        this.hostOwnsMixin = hostOwnsMixin;
        this.hostHasTransformerPipeline = hostHasTransformerPipeline;
    }

    /** True when FeatureCreep must use the Mixin installation already supplied by the host. */
    public boolean hostOwnsMixin() { return hostOwnsMixin; }

    /** True when a host transformation pipeline can be used instead of JVM Instrumentation. */
    public boolean hostHasTransformerPipeline() { return hostHasTransformerPipeline; }
}
