package featurecreep.api.bg.mc.spongemixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

import featurecreep.api.bg.mc.config.FCModsScreen;
import featurecreep.api.bg.mc.config.FCTitleButton;
import featurecreep.api.bg.mc.config.FCTitleIcon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;

/** Adds the FC Mods button to the title screen's vanilla icon row, next to the other icon buttons. */
@Mixin(TitleScreen.class)
public abstract class MixinTitleScreenFCMods extends Screen {

    @Shadow
    protected abstract int getHorizontalPosition(int currentButton, int numberOfButtons, int buttonWidth);

    @Unique
    private Button featurecreep$fcModsButton;

    protected MixinTitleScreenFCMods(Component title) {
        super(title);
    }

    @Definition(id = "numberOfButtons", local = @Local(type = int.class, name = "numberOfButtons"))
    @Expression("numberOfButtons = ?")
    @Inject(method = "init", at = @At(value = "MIXINEXTRAS:EXPRESSION", shift = At.Shift.AFTER), remap = false)
    private void featurecreep$reserveIconSlot(CallbackInfo ci, @Local(name = "numberOfButtons") LocalIntRef numberOfButtons) {
        numberOfButtons.set(numberOfButtons.get() + 1);
    }

    @Inject(method = "init", at = @At(value = "INVOKE", shift = At.Shift.AFTER, ordinal = 1,
            target = "Lnet/minecraft/client/gui/components/SpriteIconButton;setPosition(II)V"), remap = false)
    private void featurecreep$addFcModsButton(CallbackInfo ci, @Local(name = "numberOfButtons") int numberOfButtons,
            @Local(name = "currentButton") LocalIntRef currentButton, @Local(name = "topPos") int topPos) {
        Screen self = this;
        currentButton.set(currentButton.get() + 1);
        boolean hasIcon = FCTitleIcon.get() != null;
        this.featurecreep$fcModsButton = this.addRenderableWidget(new FCTitleButton(0, 0, 20, 20,
                Component.literal(hasIcon ? "" : "FC"),
                button -> Minecraft.getInstance().gui.setScreen(new FCModsScreen(self))));
        this.featurecreep$fcModsButton.setPosition(this.getHorizontalPosition(currentButton.get(), numberOfButtons, 20), topPos);
        featurecreep.api.bg.mc.config.FCMixinDiagnostics.markFcModsButtonAdded();
    }
}
