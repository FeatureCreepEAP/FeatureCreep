package featurecreep.api.bg.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import featurecreep.api.model.Pose;
import java.util.function.Consumer;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.ResolvableModel;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector3fc;

/** Item model type {@code featurecreep:model}. Its {@code model} field names a model registered in {@link ModelRegistry}. */
public final class FCModelItem {

	public static final Identifier TYPE = Identifier.fromNamespaceAndPath("featurecreep", "model");
	public static final MapCodec<Unbaked> CODEC = Identifier.CODEC.fieldOf("model")
			.xmap(Unbaked::new, Unbaked::model);

	private static final Vector3fc[] UNIT_CORNERS = unitCorners();

	private FCModelItem() {
	}

	private static Vector3fc[] unitCorners() {
		Vector3fc[] corners = new Vector3fc[8];
		for (int i = 0; i < corners.length; i++) {
			corners[i] = new Vector3f(i & 1, (i >> 1) & 1, (i >> 2) & 1);
		}
		return corners;
	}

	public record Unbaked(Identifier model) implements ItemModel.Unbaked {

		@Override
		public MapCodec<? extends ItemModel.Unbaked> type() {
			return CODEC;
		}

		@Override
		public void resolveDependencies(ResolvableModel.Resolver resolver) {
		}

		@Override
		public ItemModel bake(ItemModel.BakingContext context, Matrix4fc matrix) {
			ModelSpec spec = ModelRegistry.get(model).orElse(null);
			if (spec == null) {
				return context.missingItemModel(matrix);
			}
			return new Baked(new Special(spec));
		}
	}

	public record Baked(Special renderer) implements ItemModel {

		@Override
		public void update(ItemStackRenderState state, ItemStack stack, ItemModelResolver resolver,
				ItemDisplayContext context, ClientLevel level, ItemOwner owner, int seed) {
			ItemStackRenderState.LayerRenderState layer = state.newLayer();
			layer.setupSpecialModel(renderer, renderer.extractArgument(stack));
			if (renderer.spec().animator() != null) {
				state.setAnimated();
			}
		}
	}

	public record Special(ModelSpec spec) implements SpecialModelRenderer<Double> {

		@Override
		public void submit(Double seconds, PoseStack poseStack, SubmitNodeCollector collector, int light, int overlay,
				boolean foil, int outlineColor) {
			poseStack.pushPose();
			ModelRig.applyBlockBase(poseStack);
			Pose pose = ModelRig.pose(spec, seconds);
			ModelDraw draw = ModelDraw.of(spec);
			draw.submit(spec, draw.newRoot(), pose, poseStack, collector, light, overlay);
			poseStack.popPose();
		}

		@Override
		public void getExtents(Consumer<Vector3fc> output) {
			for (Vector3fc corner : UNIT_CORNERS) {
				output.accept(corner);
			}
		}

		@Override
		public Double extractArgument(ItemStack stack) {
			return Util.getMillis() / 1000.0;
		}
	}
}
