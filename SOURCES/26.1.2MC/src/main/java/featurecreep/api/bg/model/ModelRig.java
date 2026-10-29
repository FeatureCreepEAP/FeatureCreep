package featurecreep.api.bg.model;

import com.mojang.blaze3d.vertex.PoseStack;
import featurecreep.api.model.Cuboid;
import featurecreep.api.model.Cuboids;
import featurecreep.api.model.Geometry;
import featurecreep.api.model.Material;
import featurecreep.api.model.Mesh;
import featurecreep.api.model.Model;
import featurecreep.api.model.Node;
import featurecreep.api.model.Pose;
import featurecreep.api.model.bridge.MeshBuffers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

/**
 * Draws a model's node tree with its pose. Node transforms are applied through the pose stack, and each primitive is
 * submitted as custom geometry. Model units are pixels, so translations are divided by 16 to reach block units.
 */
final class ModelRig {

    private static final float PIXELS_PER_BLOCK = 16f;
    private static final Identifier DEFAULT_TEXTURE = Identifier.withDefaultNamespace("textures/misc/white.png");

    private ModelRig() {
    }

    static void submit(Model model, Pose pose, PoseStack stack, SubmitNodeCollector collector, int light, int overlay) {
        float texWidth = metadataFloat(model, "texture_width");
        float texHeight = metadataFloat(model, "texture_height");
        for (Node root : model.roots()) {
            draw(model, pose, root, stack, collector, light, overlay, texWidth, texHeight);
        }
    }

    private static void draw(Model model, Pose pose, Node node, PoseStack stack, SubmitNodeCollector collector,
            int light, int overlay, float texWidth, float texHeight) {
        int i = model.indexOf(node.name()).getAsInt();
        if (!pose.visible()[i]) {
            return;
        }
        float[] t = pose.translations();
        float[] r = pose.rotations();
        float[] s = pose.scales();
        stack.pushPose();
        stack.translate(t[i * 3] / PIXELS_PER_BLOCK, t[i * 3 + 1] / PIXELS_PER_BLOCK, t[i * 3 + 2] / PIXELS_PER_BLOCK);
        stack.mulPose(new Matrix4f().rotate(new Quaternionf(r[i * 4], r[i * 4 + 1], r[i * 4 + 2], r[i * 4 + 3])));
        stack.scale(s[i * 3], s[i * 3 + 1], s[i * 3 + 2]);
        Mesh mesh = toMesh(node.geometry(), texWidth, texHeight);
        if (mesh != null) {
            for (int p = 0; p < mesh.primitives().size(); p++) {
                submitPrimitive(model, mesh, p, stack, collector, light, overlay);
            }
        }
        for (Node child : node.children()) {
            draw(model, pose, child, stack, collector, light, overlay, texWidth, texHeight);
        }
        stack.popPose();
    }

    private static Mesh toMesh(Geometry geometry, float texWidth, float texHeight) {
        if (geometry == null) {
            return null;
        }
        if (geometry instanceof Cuboid cuboid) {
            return Cuboids.toMesh(cuboid, texWidth, texHeight, 0);
        }
        if (geometry instanceof Mesh mesh) {
            return mesh;
        }
        throw new IllegalArgumentException("parametric geometry must be tessellated before drawing: " + geometry);
    }

    private static void submitPrimitive(Model model, Mesh mesh, int primitive, PoseStack stack,
            SubmitNodeCollector collector, int light, int overlay) {
        float[] corners = MeshBuffers.triangleCorners(mesh, primitive);
        RenderType type = RenderTypes.entityCutoutCull(texture(model, mesh.primitives().get(primitive).material()));
        collector.submitCustomGeometry(stack, type, (last, vc) -> {
            for (int o = 0; o < corners.length; o += MeshBuffers.FLOATS_PER_CORNER) {
                vc.addVertex(last, corners[o], corners[o + 1], corners[o + 2])
                        .setNormal(last, corners[o + 3], corners[o + 4], corners[o + 5])
                        .setColor(-1)
                        .setUv(corners[o + 6], corners[o + 7])
                        .setLight(light)
                        .setOverlay(overlay);
            }
        });
    }

    static Identifier texture(Model model, int material) {
        if (material < 0 || material >= model.materials().size()) {
            return DEFAULT_TEXTURE;
        }
        Material m = model.materials().get(material);
        String path = m.textures().get("diffuse");
        if (path == null) {
            path = m.textures().values().stream().findFirst().orElse(null);
        }
        Identifier id = path == null ? null : Identifier.tryParse(path);
        return id == null ? DEFAULT_TEXTURE : id;
    }

    private static float metadataFloat(Model model, String key) {
        String value = model.metadata().get(key);
        return value == null ? 16f : Float.parseFloat(value);
    }
}
