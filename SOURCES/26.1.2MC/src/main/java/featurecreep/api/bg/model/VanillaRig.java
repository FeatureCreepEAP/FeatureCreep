package featurecreep.api.bg.model;

import featurecreep.api.bg.model.accessors.VanillaHandles;
import featurecreep.api.model.Cuboid;
import featurecreep.api.model.Geometry;
import featurecreep.api.model.Model;
import featurecreep.api.model.Node;
import featurecreep.api.model.Pose;
import featurecreep.api.model.Quat;
import featurecreep.api.model.Transform;
import featurecreep.api.model.bridge.BoxSpec;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

/**
 * Builds and poses a vanilla part tree for models whose geometry is all box cuboids. Each node becomes a part with its
 * transform as the part pose, and a cuboid node adds its box to that part. Pose values are written to the part fields.
 */
final class VanillaRig {

    private VanillaRig() {
    }

    static boolean eligible(Model model) {
        for (Node node : model.nodes()) {
            Geometry geometry = node.geometry();
            if (geometry == null) {
                continue;
            }
            if (!(geometry instanceof Cuboid cuboid) || BoxSpec.of(cuboid).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    static LayerDefinition layer(Model model, int texWidth, int texHeight) {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        for (Node node : model.roots()) {
            addNode(root, node);
        }
        return LayerDefinition.create(mesh, texWidth, texHeight);
    }

    private static void addNode(PartDefinition parent, Node node) {
        CubeListBuilder cubes = CubeListBuilder.create();
        if (node.geometry() instanceof Cuboid cuboid) {
            BoxSpec box = BoxSpec.of(cuboid).orElseThrow();
            cubes.texOffs(box.texU(), box.texV())
                    .mirror(box.mirror())
                    .addBox(box.x(), box.y(), box.z(), box.dx(), box.dy(), box.dz(),
                            new CubeDeformation(box.inflate()));
        }
        PartDefinition part = parent.addOrReplaceChild(node.name(), cubes, partPose(node.transform()));
        for (Node child : node.children()) {
            addNode(part, child);
        }
    }

    static void apply(ModelPart root, Model model, Pose pose) {
        for (Node node : model.roots()) {
            applyNode(VanillaHandles.children(root).get(node.name()), node, model, pose);
        }
    }

    private static void applyNode(ModelPart part, Node node, Model model, Pose pose) {
        int i = model.indexOf(node.name()).getAsInt();
        float[] t = pose.translations();
        float[] r = pose.rotations();
        float[] s = pose.scales();
        float[] angles = eulerZYX(new Quat(r[i * 4], r[i * 4 + 1], r[i * 4 + 2], r[i * 4 + 3]));
        part.x = t[i * 3];
        part.y = t[i * 3 + 1];
        part.z = t[i * 3 + 2];
        part.xRot = angles[0];
        part.yRot = angles[1];
        part.zRot = angles[2];
        part.xScale = s[i * 3];
        part.yScale = s[i * 3 + 1];
        part.zScale = s[i * 3 + 2];
        part.visible = pose.visible()[i];
        for (Node child : node.children()) {
            applyNode(VanillaHandles.children(part).get(child.name()), child, model, pose);
        }
    }

    static PartPose partPose(Transform transform) {
        float[] angles = eulerZYX(transform.rotation());
        return new PartPose(
                transform.translation().x(), transform.translation().y(), transform.translation().z(),
                angles[0], angles[1], angles[2],
                transform.scale().x(), transform.scale().y(), transform.scale().z());
    }

    /**
     * Returns {roll, pitch, yaw} for a quaternion decomposed as Rz(yaw) * Ry(pitch) * Rx(roll), which is the order
     * {@code ModelPart.translateAndRotate} applies. The result is in radians.
     */
    static float[] eulerZYX(Quat q) {
        float x = q.x();
        float y = q.y();
        float z = q.z();
        float w = q.w();
        float roll = (float) Math.atan2(2 * (w * x + y * z), 1 - 2 * (x * x + y * y));
        float pitch = (float) Math.asin(Math.max(-1d, Math.min(1d, 2 * (w * y - z * x))));
        float yaw = (float) Math.atan2(2 * (w * z + x * y), 1 - 2 * (y * y + z * z));
        return new float[] {roll, pitch, yaw};
    }
}
