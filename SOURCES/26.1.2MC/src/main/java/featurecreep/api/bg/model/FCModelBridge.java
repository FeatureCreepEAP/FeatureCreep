package featurecreep.api.bg.model;

import featurecreep.api.bg.model.accessors.VanillaHandles;
import featurecreep.api.model.Model;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

/**
 * Entry point for drawing featurecreep models in Minecraft 26.1.2. Call {@link #install()} during client setup, before
 * entity renderers are created and before item definitions are decoded. The bind methods put renderers into vanilla's
 * registration maps, so they must run before those maps are consumed.
 */
public final class FCModelBridge {

    public static final Identifier ITEM_TYPE = Identifier.fromNamespaceAndPath("featurecreep", "model");

    private static final Map<Identifier, ModelDraw> ITEM_MODELS = new ConcurrentHashMap<>();
    private static boolean installed;

    private FCModelBridge() {
    }

    /** Registers the {@code featurecreep:model} item type. Idempotent. Throws if a reflected vanilla member is missing. */
    public static synchronized void install() {
        if (installed) {
            return;
        }
        VanillaHandles.resolve().itemTypes().put(ITEM_TYPE, ModelItemModel.Unbaked.MAP_CODEC);
        installed = true;
    }

    public static void bindEntity(EntityType<? extends Entity> type, Model model) {
        bindEntity(type, model, ModelEntityRenderer.REST);
    }

    public static void bindEntity(EntityType<? extends Entity> type, Model model, ModelEntityRenderer.PoseSource pose) {
        ModelDraw draw = new ModelDraw(model);
        EntityRendererProvider<Entity> provider = context -> new ModelEntityRenderer(context, draw, pose);
        VanillaHandles.resolve().entityProviders().put(type, provider);
    }

    public static void bindBlockEntity(BlockEntityType<? extends BlockEntity> type, Model model) {
        ModelDraw draw = new ModelDraw(model);
        BlockEntityRendererProvider<BlockEntity, ModelBlockEntityRenderer.State> provider =
                context -> new ModelBlockEntityRenderer(draw);
        VanillaHandles.resolve().blockEntityProviders().put(type, provider);
    }

    public static void bindItemModel(Identifier id, Model model) {
        ITEM_MODELS.put(id, new ModelDraw(model));
    }

    static Optional<ModelDraw> itemModel(Identifier id) {
        return Optional.ofNullable(ITEM_MODELS.get(id));
    }
}
