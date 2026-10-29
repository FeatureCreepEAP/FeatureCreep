package featurecreep.api.bg.mc.config;

/** Runtime markers used to verify FeatureCreep mixin application. */
public final class FCMixinDiagnostics {

    private static volatile boolean titleScreenTestMixinApplied;
    private static volatile boolean fcModsButtonAdded;

    private FCMixinDiagnostics() {
    }

    public static void markTitleScreenTestMixinApplied() {
        if (!titleScreenTestMixinApplied) {
            titleScreenTestMixinApplied = true;
            System.out.println("[FeatureCreep] Test mixin applied to TitleScreen");
        }
    }

    public static void markFcModsButtonAdded() {
        if (!fcModsButtonAdded) {
            fcModsButtonAdded = true;
            System.out.println("[FeatureCreep] FC Mods button added to TitleScreen");
        }
    }

    public static boolean isTitleScreenTestMixinApplied() {
        return titleScreenTestMixinApplied;
    }

    public static boolean isFcModsButtonAdded() {
        return fcModsButtonAdded;
    }
}
