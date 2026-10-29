package featurecreep.api.bg.model.accessors;

import com.mojang.serialization.MapCodec;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.ItemModels;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntityType;

/**
 * Reads private members of vanilla 26.1.2 through method handles. Registration maps are read and then written through
 * their public {@code put} methods; {@link ModelPart}'s cube and child fields are only read.
 */
public final class VanillaHandles {

    public static final String VERSION = "26.1.2";

    private static Targets resolved;

    private VanillaHandles() {
    }

    public record Targets(
            Map<EntityType<?>, EntityRendererProvider<?>> entityProviders,
            Map<BlockEntityType<?>, BlockEntityRendererProvider<?, ?>> blockEntityProviders,
            ExtraCodecs.LateBoundIdMapper<Identifier, MapCodec<? extends ItemModel.Unbaked>> itemTypes,
            MethodHandle partChildren,
            MethodHandle partCubes) {
    }

    /** Resolves every reflected member once. Throws {@link IllegalStateException} naming the first member that is missing. */
    @SuppressWarnings("unchecked")
    public static synchronized Targets resolve() {
        if (resolved == null) {
            resolved = new Targets(
                    staticValue(EntityRenderers.class, "PROVIDERS", Map.class),
                    staticValue(BlockEntityRenderers.class, "PROVIDERS", Map.class),
                    staticValue(ItemModels.class, "ID_MAPPER", ExtraCodecs.LateBoundIdMapper.class),
                    instanceGetter(ModelPart.class, "children", Map.class),
                    instanceGetter(ModelPart.class, "cubes", List.class));
        }
        return resolved;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, ModelPart> children(ModelPart part) {
        return (Map<String, ModelPart>) read(resolve().partChildren(), part);
    }

    @SuppressWarnings("unchecked")
    public static List<ModelPart.Cube> cubes(ModelPart part) {
        return (List<ModelPart.Cube>) read(resolve().partCubes(), part);
    }

    private static <T> T staticValue(Class<?> owner, String name, Class<T> type) {
        try {
            MethodHandle getter = privateLookup(owner).findStaticGetter(owner, name, type);
            return type.cast(getter.invoke());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw missing(owner, name, type, e);
        } catch (Throwable t) {
            throw new IllegalStateException(
                    "featurecreep model bridge " + VERSION + ": reading " + owner.getName() + "." + name + " failed", t);
        }
    }

    private static MethodHandle instanceGetter(Class<?> owner, String name, Class<?> type) {
        try {
            return privateLookup(owner).findGetter(owner, name, type);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw missing(owner, name, type, e);
        }
    }

    private static MethodHandles.Lookup privateLookup(Class<?> owner) throws IllegalAccessException {
        return MethodHandles.privateLookupIn(owner, MethodHandles.lookup());
    }

    private static Object read(MethodHandle getter, Object target) {
        try {
            return getter.invoke(target);
        } catch (Throwable t) {
            throw new IllegalStateException("featurecreep model bridge " + VERSION + ": field read failed", t);
        }
    }

    private static IllegalStateException missing(Class<?> owner, String name, Class<?> type, Exception cause) {
        return new IllegalStateException(
                "featurecreep model bridge " + VERSION + ": missing " + owner.getName() + "." + name
                        + " of type " + type.getName(), cause);
    }
}
