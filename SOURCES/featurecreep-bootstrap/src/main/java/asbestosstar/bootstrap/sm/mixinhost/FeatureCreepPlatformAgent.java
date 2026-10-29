package asbestosstar.bootstrap.sm.mixinhost;

import java.util.Collection;
import java.util.List;

import org.spongepowered.asm.launch.platform.IMixinPlatformAgent;
import org.spongepowered.asm.launch.platform.IMixinPlatformServiceAgent;
import org.spongepowered.asm.launch.platform.MixinPlatformManager;
import org.spongepowered.asm.launch.platform.container.IContainerHandle;
import org.spongepowered.asm.mixin.MixinEnvironment.Phase;
import org.spongepowered.asm.util.Constants;
import org.spongepowered.asm.util.IConsumer;

/**
 * Minimal platform agent for the standalone host. Supplies the game side name
 * ({@code -Dfeaturecreep.side=CLIENT|DEDICATEDSERVER}) so Mixin's side checks behave
 * like they do on LaunchWrapper, without pulling in any platform machinery.
 */
public final class FeatureCreepPlatformAgent implements IMixinPlatformServiceAgent {
    public static final String SIDE_PROPERTY = "featurecreep.side";

    @Override
    public AcceptResult accept(MixinPlatformManager manager, IContainerHandle handle) {
        return AcceptResult.REJECTED;
    }

    @Override
    public String getPhaseProvider() {
        return null;
    }

    @Override
    public void prepare() {}

    @Override
    public void initPrimaryContainer() {}

    @Override
    public void inject() {}

    @Override
    public void init() {}

    @Override
    public Collection<IContainerHandle> getMixinContainers() {
        return List.of();
    }

    @Deprecated
    @Override
    public void wire(Phase phase, IConsumer<Phase> phaseConsumer) {}

    @Deprecated
    @Override
    public void unwire() {}

    @Override
    public String getSideName() {
        String side = System.getProperty(SIDE_PROPERTY);
        if (side != null && !side.isBlank()) return side.trim();
        if (classExists("net.minecraft.client.Minecraft")) return "CLIENT";
        if (classExists("net.minecraft.server.dedicated.DedicatedServer")) return "DEDICATEDSERVER";
        return Constants.SIDE_UNKNOWN;
    }

    private static boolean classExists(String name) {
        try {
            Class.forName(name, false, FeatureCreepPlatformAgent.class.getClassLoader());
            return true;
        } catch (ClassNotFoundException | LinkageError e) {
            return false;
        }
    }
}
