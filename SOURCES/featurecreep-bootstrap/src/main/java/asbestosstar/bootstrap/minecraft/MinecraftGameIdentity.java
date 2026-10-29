package asbestosstar.bootstrap.minecraft;

/**
 * Immutable identity snapshot of the running game and the superloader hosting it.
 * Any value that cannot be detected is reported as {@link #UNKNOWN}, never null.
 */
public record MinecraftGameIdentity(
        String gameId,
        String gameVersion,
        String loaderId,
        String loaderVersion,
        String featureCreepVersion) {

    public static final String UNKNOWN = "unknown";

    public MinecraftGameIdentity {
        gameId = orUnknown(gameId);
        gameVersion = orUnknown(gameVersion);
        loaderId = orUnknown(loaderId);
        loaderVersion = orUnknown(loaderVersion);
        featureCreepVersion = orUnknown(featureCreepVersion);
    }

    private static String orUnknown(String value) {
        return value == null || value.isBlank() ? UNKNOWN : value.trim();
    }
}
