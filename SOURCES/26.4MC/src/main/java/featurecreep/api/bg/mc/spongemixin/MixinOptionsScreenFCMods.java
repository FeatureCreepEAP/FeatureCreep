package featurecreep.api.bg.mc.spongemixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import featurecreep.api.bg.mc.config.FCModsScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import net.minecraft.network.chat.Component;

/** Adds the "FC Mods" button to the options screen, bottom-left, beside the centred Done button. */
@Mixin(OptionsScreen.class)
public class MixinOptionsScreenFCMods extends Screen {

    protected MixinOptionsScreenFCMods(Component title) {
        super(title);
    }

    @Inject(method = "init", at = @At("RETURN"), remap = false)
    private void featurecreep$addFcModsButton(CallbackInfo ci) {
        Screen self = this;
        this.addRenderableWidget(Button.builder(Component.literal("FC Mods"),
                        button -> Minecraft.getInstance().gui.setScreen(new FCModsScreen(self)))
                .bounds(4, this.height - 24, 80, 20)
                .build());
    }
}
