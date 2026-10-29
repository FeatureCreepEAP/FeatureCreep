package asbestosstar.bootstrap.sm.util;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

import asbestosstar.bootstrap.BootstrapCommon;

/**
 * Places the helper classes Mixin generates while transforming a class, such as
 * {@code org.spongepowered.asm.synthetic.args.Args$1} for {@code @ModifyArgs}. Mixin registers
 * these names and generates their bytes but never defines them. Each one is written to its own
 * jar and appended to the application class path, which needs no access to java.base internals.
 */
final class SyntheticClassDefiner {
    private static final byte[] PREFIX = "org/spongepowered/asm/synthetic/".getBytes(StandardCharsets.US_ASCII);
    private static final Set<String> PLACED = ConcurrentHashMap.newKeySet();

    private SyntheticClassDefiner() {}

    static void placeReferenced(byte[] transformed, Object transformer) {
        if (transformer == null) return;
        for (String name : referencedNames(transformed)) {
            if (!PLACED.add(name)) continue;
            try {
                place(name, transformer);
            } catch (Throwable t) {
                PLACED.remove(name);
                System.err.println("[FeatureCreep] Could not place Mixin synthetic class " + name + ": " + t);
            }
        }
    }

    static Set<String> referencedNames(byte[] bytes) {
        Set<String> names = new LinkedHashSet<>();
        int from = 0;
        int at;
        while ((at = indexOf(bytes, PREFIX, from)) >= 0) {
            int end = at;
            while (end < bytes.length && isNameByte(bytes[end])) end++;
            names.add(new String(bytes, at, end - at, StandardCharsets.US_ASCII).replace('/', '.'));
            from = end;
        }
        return names;
    }

    private static void place(String name, Object transformer) throws Exception {
        if (BootstrapCommon.instrument == null) throw new IllegalStateException("no Instrumentation");
        Class<?> environmentClass = SpongeMixinUtils.mixinClass("org.spongepowered.asm.mixin.MixinEnvironment");
        Object environment = environmentClass.getMethod("getCurrentEnvironment").invoke(null);
        Method generate = transformer.getClass().getMethod("generateClass", environmentClass, String.class);
        if (!generate.canAccess(transformer)) generate.setAccessible(true);
        byte[] bytes = (byte[]) generate.invoke(transformer, environment, name);
        if (bytes == null) throw new IllegalStateException("Mixin generated no bytes");

        Path dir = Path.of(System.getProperty("user.home", "."))
                .resolve(".featurecreep").resolve("cache").resolve("jars").resolve("synthetic");
        Files.createDirectories(dir);
        Path jar = dir.resolve(name + ".jar");
        try (JarOutputStream out = new JarOutputStream(Files.newOutputStream(jar))) {
            out.putNextEntry(new JarEntry(name.replace('.', '/') + ".class"));
            out.write(bytes);
            out.closeEntry();
        }
        BootstrapCommon.instrument.appendToSystemClassLoaderSearch(new JarFile(jar.toFile()));
        System.out.println("[FeatureCreep] Placed Mixin synthetic class " + name + " on the application class path");
    }

    private static boolean isNameByte(byte b) {
        return (b >= 'a' && b <= 'z') || (b >= 'A' && b <= 'Z') || (b >= '0' && b <= '9')
                || b == '_' || b == '$' || b == '/';
    }

    private static int indexOf(byte[] haystack, byte[] needle, int from) {
        search:
        for (int i = from; i <= haystack.length - needle.length; i++) {
            for (int j = 0; j < needle.length; j++) {
                if (haystack[i + j] != needle[j]) continue search;
            }
            return i;
        }
        return -1;
    }
}
