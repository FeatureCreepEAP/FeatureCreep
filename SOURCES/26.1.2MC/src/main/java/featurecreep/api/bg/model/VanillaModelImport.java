package featurecreep.api.bg.model;

import featurecreep.api.bg.model.accessors.VanillaHandles;
import featurecreep.api.model.Material;
import featurecreep.api.model.Mesh;
import featurecreep.api.model.Model;
import featurecreep.api.model.ModelCapabilities;
import featurecreep.api.model.Node;
import featurecreep.api.model.Quat;
import featurecreep.api.model.Transform;
import featurecreep.api.model.Vec3;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.client.model.geom.ModelPart;
import org.joml.Quaternionf;
import org.joml.Vector3f;

/**
 * Converts a vanilla {@link ModelPart} tree into a model that keeps the part hierarchy. Each part becomes a transform node
 * with its position, rotation and scale. Its cubes are child mesh nodes named {@code <path>/cube<index>} in part-local
 * pixels, and its child parts are child nodes named by path. The texture size is kept in metadata.
 */
public final class VanillaModelImport {

    private static final String ROOT = "root";

    private VanillaModelImport() {
    }

    public static Model fromModelPart(ModelPart root, int texWidth, int texHeight) {
        Map<String, String> metadata = Map.of(
                "texture_width", Integer.toString(texWidth),
                "texture_height", Integer.toString(texHeight));
        List<Material> materials = List.of(new Material("vanilla", "vanilla", Map.of()));
        return new Model(List.of(part(root, ROOT)), materials, List.of(), ModelCapabilities.any(), metadata, Map.of());
    }

    private static Node part(ModelPart part, String path) {
        List<Node> children = new ArrayList<>();
        List<ModelPart.Cube> cubes = VanillaHandles.cubes(part);
        for (int i = 0; i < cubes.size(); i++) {
            children.add(cubeNode(path + "/cube" + i, cubes.get(i)));
        }
        for (Map.Entry<String, ModelPart> child : VanillaHandles.children(part).entrySet()) {
            children.add(part(child.getValue(), path + "/" + child.getKey()));
        }
        return new Node(path, transform(part), children, null, part.visible, Map.of());
    }

    private static Transform transform(ModelPart part) {
        Quaternionf q = new Quaternionf().rotationZYX(part.zRot, part.yRot, part.xRot);
        return new Transform(
                new Vec3(part.x, part.y, part.z),
                new Quat(q.x, q.y, q.z, q.w),
                new Vec3(part.xScale, part.yScale, part.zScale));
    }

    private static Node cubeNode(String name, ModelPart.Cube cube) {
        List<Float> positions = new ArrayList<>();
        List<Float> normals = new ArrayList<>();
        List<Float> uvs = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        for (ModelPart.Polygon polygon : cube.polygons) {
            ModelPart.Vertex[] vertices = polygon.vertices();
            Vector3f normal = new Vector3f(polygon.normal());
            int base = positions.size() / 3;
            for (ModelPart.Vertex vertex : vertices) {
                positions.add(vertex.x());
                positions.add(vertex.y());
                positions.add(vertex.z());
                normals.add(normal.x);
                normals.add(normal.y);
                normals.add(normal.z);
                uvs.add(vertex.u());
                uvs.add(vertex.v());
            }
            for (int k = 1; k < vertices.length - 1; k++) {
                indices.add(base);
                indices.add(base + k);
                indices.add(base + k + 1);
            }
        }
        Mesh mesh = new Mesh(toArray(positions), toArray(normals), null, new float[][] {toArray(uvs)},
                List.of(new Mesh.Primitive(0, toIntArray(indices))), null);
        return new Node(name, Transform.IDENTITY, List.of(), mesh, true, Map.of());
    }

    private static float[] toArray(List<Float> values) {
        float[] out = new float[values.size()];
        for (int i = 0; i < out.length; i++) {
            out[i] = values.get(i);
        }
        return out;
    }

    private static int[] toIntArray(List<Integer> values) {
        int[] out = new int[values.size()];
        for (int i = 0; i < out.length; i++) {
            out[i] = values.get(i);
        }
        return out;
    }
}
