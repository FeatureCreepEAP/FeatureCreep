package featurecreep.api.bg.mc.config;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

/** The title screen's FC button. Draws the FeatureCreep logo in the button's own render pass, like a vanilla icon button. */
public final class FCTitleButton extends Button.Plain {

    private static final int INSET = 2;
    private static final int ICON_SIZE = 16;

    public FCTitleButton(int x, int y, int width, int height, Component message, Button.OnPress onPress) {
        super(x, y, width, height, message, onPress, DEFAULT_NARRATION);
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics) {
        super.extractContents(graphics);
        FCTitleIcon.Icon icon = FCTitleIcon.get();
        if (icon == null) return;
        int x = getX() + INSET;
        int y = getY() + INSET;
        graphics.blit(icon.id(), x, y, x + ICON_SIZE, y + ICON_SIZE, 0f, 1f, 0f, 1f);
    }
}
