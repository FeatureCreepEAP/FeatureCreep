package featurecreep.api.bg.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import featurecreep.api.model.Pose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.ResolvableModel;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4fc;
import org.joml.Vector3fc;
import java.util.Optional;
import java.util.function.Consumer;

/** The {@code featurecreep:model} item model type. Items reference a model registered with {@link FCModelBridge}. */
public final class ModelItemModel {

    private ModelItemModel() {
    }

    public record Unbaked(Identifier model) implements ItemModel.Unbaked {

        public static final MapCodec<Unbaked> MAP_CODEC = Identifier.CODEC.fieldOf("model")
                .xmap(Unbaked::new, Unbaked::model);

        @Override
        public MapCodec<? extends ItemModel.Unbaked> type() {
            return MAP_CODEC;
        }

        @Override
        public void resolveDependencies(ResolvableModel.Resolver resolver) {
        }

        @Override
        public ItemModel bake(ItemModel.BakingContext context, Matrix4fc transform) {
            Optional<ModelDraw> found = FCModelBridge.itemModel(model);
            if (found.isEmpty()) {
                return (state, stack, resolver, displayContext, level, owner, seed) -> state.clear();
            }
            SpecialModelRenderer<Void> renderer = new Renderer(found.get());
            return (state, stack, resolver, displayContext, level, owner, seed) -> {
                state.clear();
                state.newLayer().setupSpecialModel(renderer, null);
            };
        }
    }

    private static final class Renderer implements SpecialModelRenderer<Void> {

        private final ModelDraw draw;
        private final Pose pose;
        private final ModelPart root;

        Renderer(ModelDraw draw) {
            this.draw = draw;
            this.pose = draw.restPose();
            this.root = draw.newRoot();
        }

        @Override
        public void submit(Void argument, PoseStack stack, SubmitNodeCollector collector, int light, int overlay,
                boolean foil, int outlineColor) {
            draw.draw(root, pose, stack, collector, light, overlay);
        }

        @Override
        public void getExtents(Consumer<Vector3fc> output) {
        }

        @Override
        public Void extractArgument(ItemStack stack) {
            return null;
        }
    }
}
