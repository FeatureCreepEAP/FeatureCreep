package featurecreep.api.bg.mc.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.jboss.modules.Module;

import com.mojang.blaze3d.platform.NativeImage;

import featurecreep.loader.config.FCConfigEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.GuiPipeline;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.texture.TextureResources;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

/**
 * Lists the loaded FeatureCreep modules in a scrolling list on the left, with the
 * selected module's details on the right. Disabling a module renames its jar to
 * {@code <jar>.deactivation}, which takes effect on the next launch.
 */
public class FCModsScreen extends Screen {

    private static final int LIST_WIDTH = 180;
    private static final int LIST_TOP = 28;
    private static final int LIST_BOTTOM_RESERVE = 40;
    private static final int ROW_HEIGHT = 36;
    private static final int ROW_ICON = 32;
    private static final int DETAIL_ICON = 64;
    private static final int GAP = 8;
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 20;
    private static final int LINE_HEIGHT = 11;
    private static final int TEXT_COLOR = 0xFFFFFFFF;
    private static final int SUBTEXT_COLOR = 0xFFAAAAAA;
    private static final int WARNING_COLOR = 0xFFFFAA55;
    private static final Identifier FALLBACK_ICON = Identifier.withDefaultNamespace("textures/misc/unknown_pack.png");
    private static final int FALLBACK_ICON_SIZE = 128;
    private static final Map<String, Icon> ICONS = new HashMap<String, Icon>();

    private final Screen parent;
    private final List<ModRow> rows = new ArrayList<ModRow>();
    private int selected = -1;
    private String message = "";
    private Button settingsButton;
    private Button disableButton;

    public FCModsScreen(Screen parent) {
        super(Component.literal("FC Mods"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        rows.clear();
        for (Module module : FCMinecraftConfig.featureCreepModules()) {
            rows.add(ModRow.of(module));
        }

        int listHeight = Math.max(ROW_HEIGHT, this.height - LIST_TOP - LIST_BOTTOM_RESERVE);
        ModList list = new ModList(Minecraft.getInstance(), LIST_WIDTH, listHeight, LIST_TOP, ROW_HEIGHT);
        list.setX(GAP);
        for (int i = 0; i < rows.size(); i++) {
            list.addRow(i);
        }
        this.addRenderableWidget(list);

        int panelX = panelX();
        settingsButton = this.addRenderableWidget(Button.builder(Component.literal("Settings..."), b -> openSettings())
                .bounds(panelX, buttonsTop(), BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());
        disableButton = this.addRenderableWidget(Button.builder(Component.literal("Disable mod"), b -> disableSelected())
                .bounds(panelX, buttonsTop() + 26, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, b -> onClose())
                .bounds(this.width / 2 - 100, this.height - 24, 200, BUTTON_HEIGHT)
                .build());

        if (rows.isEmpty()) {
            updateButtons();
        } else {
            select(0);
        }
    }

    private void select(int index) {
        selected = index;
        message = "";
        updateButtons();
    }

    private ModRow selectedRow() {
        return selected >= 0 && selected < rows.size() ? rows.get(selected) : null;
    }

    private void updateButtons() {
        ModRow row = selectedRow();
        boolean hasRow = row != null;
        boolean active = hasRow && !row.disabled;
        settingsButton.active = active && row.configurable;
        disableButton.active = active;
    }

    private void openSettings() {
        ModRow row = selectedRow();
        if (row == null) return;
        Optional<Object> created = FCMinecraftConfig.api().createScreen(row.modId, this);
        if (created.isPresent() && created.get() instanceof Screen screen) {
            Minecraft.getInstance().gui.setScreen(screen);
        } else {
            System.err.println("FeatureCreep FC Mods: no config screen was produced for '" + row.modId + "'");
        }
    }

    private void disableSelected() {
        ModRow row = selectedRow();
        if (row == null) return;
        File jar = FCMinecraftConfig.moduleJar(row.module);
        if (jar == null) {
            message = "Could not find the jar for " + row.name + ".";
            updateButtons();
            return;
        }
        File target = new File(jar.getParentFile(), jar.getName() + ".deactivation");
        try {
            Files.move(jar.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
            row.disabled = true;
            message = row.name + " will be deactivated. Restart Minecraft to finish deactivating it.";
        } catch (IOException e) {
            message = "Could not disable " + row.name + ": " + e.getMessage();
        }
        updateButtons();
    }

    private int panelX() {
        return LIST_WIDTH + GAP * 2;
    }

    private int buttonsTop() {
        return this.height - 74;
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics) {
        super.extractRenderState(graphics);
        graphics.centeredText(this.font, "FC Mods", this.width / 2, 10, TEXT_COLOR);

        ModRow row = selectedRow();
        if (row == null) {
            graphics.centeredText(this.font, "No FeatureCreep modules are loaded.", this.width / 2, this.height / 2, SUBTEXT_COLOR);
            return;
        }

        int panelX = panelX();
        int panelWidth = this.width - GAP - panelX;
        drawIcon(graphics, row.icon, panelX, LIST_TOP, DETAIL_ICON);

        int textX = panelX + DETAIL_ICON + GAP;
        int textWidth = panelWidth - DETAIL_ICON - GAP;
        graphics.text(this.font, this.font.plainSubstrByWidth(row.name, textWidth), textX, LIST_TOP, TEXT_COLOR);
        graphics.text(this.font, "Version " + row.version, textX, LIST_TOP + 12, SUBTEXT_COLOR);
        graphics.text(this.font, row.statusLabel(), textX, LIST_TOP + 24, row.disabled ? WARNING_COLOR : SUBTEXT_COLOR);

        List<String> messageLines = wrap(message, panelWidth);
        int messageTop = buttonsTop() - 6 - LINE_HEIGHT * messageLines.size();
        int y = LIST_TOP + DETAIL_ICON + GAP;
        if (row.authors != null) {
            graphics.text(this.font, this.font.plainSubstrByWidth("Authors: " + row.authors, panelWidth), panelX, y, SUBTEXT_COLOR);
            y += LINE_HEIGHT + 4;
        }
        for (String line : wrap(row.description, panelWidth)) {
            if (y + LINE_HEIGHT > messageTop) break;
            graphics.text(this.font, line, panelX, y, TEXT_COLOR);
            y += LINE_HEIGHT;
        }
        for (int i = 0; i < messageLines.size(); i++) {
            graphics.text(this.font, messageLines.get(i), panelX, messageTop + LINE_HEIGHT * i, WARNING_COLOR);
        }
    }

    private List<String> wrap(String text, int width) {
        List<String> lines = new ArrayList<String>();
        if (text == null) return lines;
        String rest = text.trim();
        while (!rest.isEmpty()) {
            String line = this.font.plainSubstrByWidth(rest, width);
            if (line.isEmpty()) line = rest.substring(0, 1);
            lines.add(line);
            rest = rest.substring(line.length()).trim();
        }
        return lines;
    }

    private static void drawIcon(GuiGraphicsExtractor graphics, Icon icon, int x, int y, int size) {
        Identifier id = icon != null ? icon.id() : FALLBACK_ICON;
        int texWidth = icon != null ? icon.width() : FALLBACK_ICON_SIZE;
        int texHeight = icon != null ? icon.height() : FALLBACK_ICON_SIZE;
        graphics.blit(GuiPipeline.DEFAULT, id, x, y, 0, 0, size, size, texWidth, texHeight);
    }

    @Override
    public void onClose() {
        Minecraft.getInstance().gui.setScreen(parent);
    }

    private static Icon iconFor(Module module, String modId) {
        if (!ICONS.containsKey(modId)) {
            ICONS.put(modId, loadIcon(module, modId));
        }
        return ICONS.get(modId);
    }

    private static Icon loadIcon(Module module, String modId) {
        String path = FCMinecraftConfig.blankToNull(module.getProperty(FCMinecraftConfig.PROP_ICON));
        if (path == null) return null;
        try (InputStream in = module.getClassLoader().getResourceAsStream(path)) {
            if (in == null) {
                System.err.println("[FeatureCreep] Icon " + path + " not found in module " + modId);
                return null;
            }
            NativeImage image = NativeImage.read(in);
            Identifier id = Identifier.fromNamespaceAndPath("featurecreep", "module_icon/" + iconKey(modId));
            TextureResources texture = TextureResources.from2dImage(id::toString, image);
            Minecraft.getInstance().getTextureManager().register(id, texture);
            return new Icon(id, image.getWidth(), image.getHeight());
        } catch (IOException | RuntimeException e) {
            System.err.println("[FeatureCreep] Could not load icon " + path + " for module " + modId + ": " + e);
            return null;
        }
    }

    private static String iconKey(String modId) {
        return modId.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9_./-]", "_");
    }

    private record Icon(Identifier id, int width, int height) {
    }

    /** Display data for one module. {@code disabled} is set once its jar has been renamed. */
    private static final class ModRow {
        final Module module;
        final String modId;
        final String name;
        final String version;
        final String description;
        final String authors;
        final boolean configurable;
        final Icon icon;
        boolean disabled;

        private ModRow(Module module, String modId, String name, String version, String description,
                String authors, boolean configurable, Icon icon) {
            this.module = module;
            this.modId = modId;
            this.name = name;
            this.version = version;
            this.description = description;
            this.authors = authors;
            this.configurable = configurable;
            this.icon = icon;
        }

        static ModRow of(Module module) {
            String modId = FCMinecraftConfig.modId(module);
            Optional<FCConfigEntry> entry = FCMinecraftConfig.api().getEntry(modId);
            boolean configurable = entry.isPresent() && entry.get().hasScreen();
            String name = FCMinecraftConfig.blankToNull(module.getProperty(FCMinecraftConfig.PROP_NAME));
            String version = FCMinecraftConfig.blankToNull(module.getProperty(FCMinecraftConfig.PROP_MODULE_VERSION));
            String description = FCMinecraftConfig.blankToNull(module.getProperty(FCMinecraftConfig.PROP_DESCRIPTION));
            String authors = FCMinecraftConfig.blankToNull(module.getProperty(FCMinecraftConfig.PROP_AUTHORS));
            return new ModRow(module, modId,
                    name != null ? name : modId,
                    version != null ? version : "unknown",
                    description != null ? description : "",
                    authors,
                    configurable,
                    iconFor(module, modId));
        }

        String statusLabel() {
            return disabled ? "Deactivated, restart to apply" : "Active";
        }
    }

    private final class ModList extends ContainerObjectSelectionList<ModList.ModEntry> {

        ModList(Minecraft minecraft, int width, int height, int y, int itemHeight) {
            super(minecraft, width, height, y, itemHeight);
        }

        void addRow(int index) {
            this.addEntry(new ModEntry(index));
        }

        @Override
        public int getRowWidth() {
            return LIST_WIDTH - 12;
        }

        final class ModEntry extends ContainerObjectSelectionList.Entry<ModEntry> {
            private final int index;
            private final Button button;

            ModEntry(int index) {
                this.index = index;
                this.button = Button.builder(Component.empty(), b -> select(index))
                        .size(getRowWidth(), ROW_HEIGHT)
                        .build();
            }

            @Override
            public List<? extends GuiEventListener> children() {
                return List.of(button);
            }

            @Override
            public List<? extends NarratableEntry> narratables() {
                return List.of(button);
            }

            @Override
            public void extractContent(GuiGraphicsExtractor graphics, boolean hovered) {
                ModRow row = rows.get(index);
                int x = getContentX();
                int y = getContentY();
                button.setPosition(x, y);
                button.active = index != selected;
                button.extractRenderState(graphics);

                drawIcon(graphics, row.icon, x + 2, y + 2, ROW_ICON);
                int textX = x + ROW_ICON + 6;
                int textWidth = getRowWidth() - ROW_ICON - 10;
                graphics.text(font, font.plainSubstrByWidth(row.name, textWidth), textX, y + 2, TEXT_COLOR);
                graphics.text(font, "Version " + row.version, textX, y + 13, SUBTEXT_COLOR);
                graphics.text(font, row.disabled ? "Deactivated" : "Active", textX, y + 24,
                        row.disabled ? WARNING_COLOR : SUBTEXT_COLOR);
            }
        }
    }
}
