package featurecreep.api.bg.model;

import com.mojang.blaze3d.vertex.PoseStack;
import featurecreep.api.model.Cuboid;
import featurecreep.api.model.Cuboids;
import featurecreep.api.model.Geometry;
import featurecreep.api.model.Mesh;
import featurecreep.api.model.Node;
import featurecreep.api.model.Parametric;
import featurecreep.api.model.Pose;
import featurecreep.api.model.bridge.MeshBuffers;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;
import org.joml.Quaternionf;

final class ModelRig {

	private static final Map<ModelSpec, float[][]> CORNERS = new WeakHashMap<>();

	private ModelRig() {
	}

	static Pose pose(ModelSpec spec, double seconds) {
		Pose pose = Pose.rest(spec.model());
		if (spec.animator() != null) {
			spec.animator().apply(spec.model(), seconds, pose);
		}
		return pose;
	}

	static void applyBlockBase(PoseStack poseStack) {
		poseStack.translate(1f, 1f, 0f);
		poseStack.scale(-1f, -1f, 1f);
	}

	static void applyEntityBase(PoseStack poseStack) {
		poseStack.translate(0f, 1.5f, 0f);
		poseStack.scale(-1f, -1f, 1f);
	}

	static void submit(ModelSpec spec, Pose pose, PoseStack poseStack, SubmitNodeCollector collector, int light,
			int overlay) {
		Frame frame = new Frame(pose, corners(spec), ModelDraw.renderType(spec.texture()), collector, light, overlay);
		int[] next = {0};
		for (Node root : spec.model().roots()) {
			drawNode(root, frame, poseStack, next);
		}
	}

	private record Frame(Pose pose, float[][] corners, RenderType type, SubmitNodeCollector collector, int light,
			int overlay) {
	}

	private static void drawNode(Node node, Frame frame, PoseStack poseStack, int[] next) {
		int i = next[0]++;
		Pose pose = frame.pose();
		if (!pose.visible()[i]) {
			next[0] += subtreeSize(node) - 1;
			return;
		}
		float[] t = pose.translations();
		float[] r = pose.rotations();
		float[] s = pose.scales();
		poseStack.pushPose();
		poseStack.translate(t[i * 3] / 16f, t[i * 3 + 1] / 16f, t[i * 3 + 2] / 16f);
		poseStack.rotate(new Quaternionf(r[i * 4], r[i * 4 + 1], r[i * 4 + 2], r[i * 4 + 3]));
		poseStack.scale(s[i * 3], s[i * 3 + 1], s[i * 3 + 2]);
		float[] corners = frame.corners()[i];
		if (corners != null) {
			emit(corners, frame, poseStack);
		}
		for (Node child : node.children()) {
			drawNode(child, frame, poseStack, next);
		}
		poseStack.popPose();
	}

	private static void emit(float[] corners, Frame frame, PoseStack poseStack) {
		frame.collector().submitCustomGeometry(poseStack, frame.type(), (entry, consumer) -> {
			for (int v = 0; v < corners.length; v += MeshBuffers.FLOATS_PER_CORNER) {
				consumer.addVertex(entry, corners[v] / 16f, corners[v + 1] / 16f, corners[v + 2] / 16f)
						.setColor(-1)
						.setUv(corners[v + 6], corners[v + 7])
						.setOverlay(frame.overlay())
						.setLight(frame.light())
						.setNormal(entry, corners[v + 3], corners[v + 4], corners[v + 5]);
			}
		});
	}

	private static int subtreeSize(Node node) {
		int size = 1;
		for (Node child : node.children()) {
			size += subtreeSize(child);
		}
		return size;
	}

	private static float[][] corners(ModelSpec spec) {
		synchronized (CORNERS) {
			return CORNERS.computeIfAbsent(spec, ModelRig::build);
		}
	}

	private static float[][] build(ModelSpec spec) {
		List<Node> nodes = spec.model().nodes();
		float[][] out = new float[nodes.size()][];
		for (int i = 0; i < nodes.size(); i++) {
			Geometry geometry = nodes.get(i).geometry();
			if (geometry != null) {
				out[i] = triangles(geometry, spec);
			}
		}
		return out;
	}

	private static float[] triangles(Geometry geometry, ModelSpec spec) {
		Mesh mesh = switch (geometry) {
			case Cuboid cuboid -> Cuboids.toMesh(cuboid, spec.textureWidth(), spec.textureHeight(), 0);
			case Mesh m -> m;
			case Parametric p -> throw new IllegalArgumentException("parametric geometry must be tessellated first");
		};
		float[][] perPrimitive = new float[mesh.primitives().size()][];
		int total = 0;
		for (int p = 0; p < perPrimitive.length; p++) {
			perPrimitive[p] = MeshBuffers.triangleCorners(mesh, p);
			total += perPrimitive[p].length;
		}
		float[] out = new float[total];
		int offset = 0;
		for (float[] primitive : perPrimitive) {
			System.arraycopy(primitive, 0, out, offset, primitive.length);
			offset += primitive.length;
		}
		return out;
	}
}
