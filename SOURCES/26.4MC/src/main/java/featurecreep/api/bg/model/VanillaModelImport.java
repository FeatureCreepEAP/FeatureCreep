package featurecreep.api.bg.model;

import featurecreep.api.model.Geometry;
import featurecreep.api.model.Material;
import featurecreep.api.model.Mesh;
import featurecreep.api.model.Model;
import featurecreep.api.model.ModelCapabilities;
import featurecreep.api.model.Node;
import featurecreep.api.model.Quat;
import featurecreep.api.model.Transform;
import featurecreep.api.model.Vec3;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import org.joml.Quaternionf;
import org.joml.Vector3fc;

/**
 * Converts a vanilla {@link ModelPart} tree into a featurecreep model using its bind pose. Cube polygons become triangle
 * meshes with normalised UVs. A part with one cube keeps its mesh on the node. A part with several cubes becomes a
 * transform node whose children are named {@code <part>/cube<i>}.
 */
public final class VanillaModelImport {

	private static final Material MATERIAL = new Material("vanilla", "default", Map.of());

	private VanillaModelImport() {
	}

	static void verify() {
		Fields.resolve();
	}

	public static Model fromModelPart(ModelPart root, int textureWidth, int textureHeight) {
		Fields fields = Fields.resolve();
		Node rootNode = convert(root, "root", fields);
		Map<String, String> metadata = Map.of(
				"texture_width", Integer.toString(textureWidth),
				"texture_height", Integer.toString(textureHeight));
		return new Model(List.of(rootNode), List.of(MATERIAL), List.of(), ModelCapabilities.any(), metadata, Map.of());
	}

	private static Node convert(ModelPart part, String path, Fields fields) {
		List<ModelPart.Cube> cubes = read(fields.cubes(), part);
		List<Node> children = new ArrayList<>();
		Geometry geometry = null;
		if (cubes.size() == 1) {
			geometry = cubeMesh(cubes.get(0));
		} else {
			for (int i = 0; i < cubes.size(); i++) {
				children.add(new Node(path + "/cube" + i, Transform.IDENTITY, List.of(), cubeMesh(cubes.get(i))));
			}
		}
		Map<String, ModelPart> parts = read(fields.children(), part);
		for (Map.Entry<String, ModelPart> child : new TreeMap<>(parts).entrySet()) {
			children.add(convert(child.getValue(), path + "/" + child.getKey(), fields));
		}
		return new Node(path, transform(part.getInitialPose()), List.copyOf(children), geometry);
	}

	private static Transform transform(PartPose pose) {
		Quaternionf rotation = new Quaternionf().rotationZYX(pose.zRot(), pose.yRot(), pose.xRot());
		return new Transform(
				new Vec3(pose.x(), pose.y(), pose.z()),
				new Quat(rotation.x, rotation.y, rotation.z, rotation.w),
				new Vec3(pose.xScale(), pose.yScale(), pose.zScale()));
	}

	private static Mesh cubeMesh(ModelPart.Cube cube) {
		int vertexCount = 0;
		int indexCount = 0;
		for (ModelPart.Polygon polygon : cube.polygons) {
			vertexCount += polygon.vertices().length;
			indexCount += (polygon.vertices().length - 2) * 3;
		}
		float[] positions = new float[vertexCount * 3];
		float[] normals = new float[vertexCount * 3];
		float[] uv = new float[vertexCount * 2];
		int[] indices = new int[indexCount];
		int v = 0;
		int index = 0;
		for (ModelPart.Polygon polygon : cube.polygons) {
			ModelPart.Vertex[] vertices = polygon.vertices();
			Vector3fc normal = polygon.normal();
			int base = v;
			for (ModelPart.Vertex vertex : vertices) {
				positions[v * 3] = vertex.x();
				positions[v * 3 + 1] = vertex.y();
				positions[v * 3 + 2] = vertex.z();
				normals[v * 3] = normal.x();
				normals[v * 3 + 1] = normal.y();
				normals[v * 3 + 2] = normal.z();
				uv[v * 2] = vertex.u();
				uv[v * 2 + 1] = vertex.v();
				v++;
			}
			for (int k = 1; k + 1 < vertices.length; k++) {
				indices[index++] = base;
				indices[index++] = base + k;
				indices[index++] = base + k + 1;
			}
		}
		return new Mesh(positions, normals, null, new float[][] {uv},
				List.of(new Mesh.Primitive(0, indices)), null);
	}

	@SuppressWarnings("unchecked")
	private static <T> T read(Field field, ModelPart part) {
		try {
			return (T) field.get(part);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException("cannot read ModelPart." + field.getName() + " on Minecraft "
					+ FCModelRenderers.VERSION, e);
		}
	}

	private record Fields(Field cubes, Field children) {

		static Fields resolve() {
			return new Fields(field("cubes"), field("children"));
		}

		private static Field field(String name) {
			try {
				Field field = ModelPart.class.getDeclaredField(name);
				field.setAccessible(true);
				return field;
			} catch (ReflectiveOperationException | RuntimeException e) {
				throw new IllegalStateException("Minecraft " + FCModelRenderers.VERSION
						+ " is missing ModelPart." + name, e);
			}
		}
	}
}
