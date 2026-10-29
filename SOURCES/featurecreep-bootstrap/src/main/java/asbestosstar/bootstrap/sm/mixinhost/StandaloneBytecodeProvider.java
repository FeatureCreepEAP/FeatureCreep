package asbestosstar.bootstrap.sm.mixinhost;

import java.io.IOException;
import java.io.InputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.service.IClassBytecodeProvider;

/**
 * Reads class bytecode through the standalone runtime's resource view (standalone
 * jars, application classpath, FeatureCreep modules). Raw bytes are returned: the
 * vanilla bootstrap has no legacy transformer chain to replay, and replaying the
 * Mixin transformer here would recurse.
 */
final class StandaloneBytecodeProvider implements IClassBytecodeProvider {
    @Override
    public ClassNode getClassNode(String name) throws ClassNotFoundException, IOException {
        return this.getClassNode(name, false, ClassReader.EXPAND_FRAMES);
    }

    @Override
    public ClassNode getClassNode(String name, boolean runTransformers) throws ClassNotFoundException, IOException {
        return this.getClassNode(name, runTransformers, ClassReader.EXPAND_FRAMES);
    }

    @Override
    public ClassNode getClassNode(String name, boolean runTransformers, int readerFlags)
            throws ClassNotFoundException, IOException {
        String resource = name.replace('.', '/') + ".class";
        if (Boolean.getBoolean("featurecreep.debug") && name.startsWith("featurecreep.")) {
            System.out.println("[FeatureCreep] Mixin bytecode request " + name + " resource=" + resource);
        }
        byte[] bytes = findResource(resource);
        if (bytes == null) {
            System.err.println("[FeatureCreep] Mixin bytecode missing " + name + " resource=" + resource);
            throw new ClassNotFoundException(name);
        }
        ClassNode node = new ClassNode();
        new ClassReader(bytes).accept(node, readerFlags);
        return node;
    }

    // Without -javaagent the application classpath may not contain module jars that the
    // standalone loader can see, so fall back through the other visible class loaders.
    private static byte[] findResource(String resource) throws IOException {
        ClassLoader[] loaders = {
                StandaloneMixinService.class.getClassLoader(),
                Thread.currentThread().getContextClassLoader(),
                ClassLoader.getSystemClassLoader()
        };
        for (ClassLoader loader : loaders) {
            if (loader == null) continue;
            try (InputStream in = loader.getResourceAsStream(resource)) {
                if (in != null) return in.readAllBytes();
            }
        }
        return null;
    }
}
