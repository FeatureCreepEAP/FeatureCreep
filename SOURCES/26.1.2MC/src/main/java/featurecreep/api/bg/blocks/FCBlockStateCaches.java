package featurecreep.api.bg.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public final class FCBlockStateCaches {

    private static volatile boolean vanillaBlocksRegistered;

    private FCBlockStateCaches() {
    }

    public static void vanillaBlocksRegistered() {
        vanillaBlocksRegistered = true;
    }

    public static void onBlockAdded(Block block) {
        if (!vanillaBlocksRegistered) {
            return;
        }
        for (BlockState state : block.getStateDefinition().getPossibleStates()) {
            Block.BLOCK_STATE_REGISTRY.add(state);
            state.initCache();
        }
    }
}
