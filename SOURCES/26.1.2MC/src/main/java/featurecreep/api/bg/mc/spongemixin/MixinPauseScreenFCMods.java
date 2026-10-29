package featurecreep.api.bg.mc.spongemixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import featurecreep.api.bg.mc.config.FCModsScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/**
 * Adds the "FC Mods" button to the pause menu, top-left, clear of the centred menu grid.
 * Skipped when the screen is opened without the pause menu (showPauseMenu == false).
 */
@Mixin(PauseScreen.class)
public class MixinPauseScreenFCMods extends Screen {

    @Shadow
    @Final
    private boolean showPauseMenu;

    protected MixinPauseScreenFCMods(Component title) {
        super(title);
    }

    @Inject(method = "init", at = @At("RETURN"), remap = false)
    private void featurecreep$addFcModsButton(CallbackInfo ci) {
        if (!this.showPauseMenu) {
            return;
        }
        Screen self = this;
        this.addRenderableWidget(Button.builder(Component.literal("FC Mods"),
                        button -> Minecraft.getInstance().setScreen(new FCModsScreen(self)))
                .bounds(4, 4, 80, 20)
                .build());
    }
}
