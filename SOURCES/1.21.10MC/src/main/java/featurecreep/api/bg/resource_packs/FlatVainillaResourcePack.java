package featurecreep.api.bg.resource_packs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import featurecreep.api.dmr.ModelNode;
import featurecreep.loader.flat.FlatModMetadata;

// Implementation for Flat Mods loaded via URLClassLoader
public class FlatVainillaResourcePack implements VainillaResourcePack {

    public FlatModMetadata meta;
    public ClassLoader classLoader;

    public FlatVainillaResourcePack(FlatModMetadata meta, ClassLoader classLoader) {
        this.meta = meta;
        this.classLoader = classLoader;
    }

    @Override
    public Supplier<InputStream> getStream(String location) {
        try {
            // Attempt to find the resource in the classpath
            URL url = classLoader.getResource(location);
            if (url != null) {
                InputStream stream = url.openStream();
                return () -> stream;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<String> getEntries(String prefix) {
        Set<String> entries = new HashSet<>();

        // 1. Try to use the optimized method from our custom FlatClassLoader if available
        if (classLoader.getClass().getName().contains("FlatClassLoader")) {
            try {
                // Use reflection to access the 'collectEntries' method we added to the inner class
                Method collectMethod = classLoader.getClass().getMethod("collectEntries", String.class, Set.class);
                collectMethod.invoke(classLoader, prefix, entries);
                return entries;
            } catch (Exception e) {
                // Reflection failed, fallback to manual scanning
                if (Boolean.parseBoolean(System.getProperty("fc.debug", "false"))) {
                    System.out.println("[FlatVainillaResourcePack] Could not invoke collectEntries via reflection, falling back to JAR scan.");
                }
            }
        }

        // 2. Fallback: Manual scanning via JarFile inspection (Standard Java URLClassLoader logic)
        // This works if the ClassLoader is a URLClassLoader and we can access the URLs.
        if (classLoader instanceof URLClassLoader) {
            URLClassLoader ucl = (URLClassLoader) classLoader;
            for (URL url : ucl.getURLs()) {
                scanUrlForEntries(url, prefix, entries);
            }
        } else {
            // 3. Last Resort: Try to scan the source file from metadata if the CL isn't a URLClassLoader
            // (This handles cases where the classloader might be wrapped or isolated)
            if (meta.getSourceFile() != null) {
                try {
					scanUrlForEntries(meta.getSourceFile().toURI().toURL(), prefix, entries);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }

        return entries;
    }

    private void scanUrlForEntries(URL url, String prefix, Set<String> entries) {
        try {
            File file = new File(url.toURI());
            if (file.isFile() && (file.getName().endsWith(".jar") || file.getName().endsWith(".fpm"))) {
                try (JarFile jar = new JarFile(file)) {
                    Enumeration<JarEntry> jarEntries = jar.entries();
                    while (jarEntries.hasMoreElements()) {
                        JarEntry entry = jarEntries.nextElement();
                        if (!entry.isDirectory()) {
                            String name = entry.getName();
                            if (name.startsWith(prefix)) {
                                entries.add(name);
                            }
                        }
                    }
                }
            }
            // Note: Directory scanning could be added here for folder-based mods if necessary
        } catch (Exception ignored) {
            // Ignore errors for individual URLs
        }
    }

    @Override
    public FCPackMCMeta getPackMCMetaInfo() {
        Supplier<InputStream> stream = getStream("pack.mcmeta");
        if (stream != null) {
            InputStream gotten = stream.get();
            if (gotten != null) {
                try {
                    return FCPackMCMeta.fromModelNode(ModelNode.fromJSONStream(gotten));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return new FCPackMCMeta(41, getPackName());
    }

    @Override
    public String getPackName() {
        return this.meta.getModId();
    }

    @Override
    public void closeStreams() {
        // No persistent streams to close
    }

    @Override
    public String getOverlay() {
        return ""; // Flat mods do not support overlays in this basic implementation
    }

    @Override
    public VainillaResourcePack getVainillaResourcePack(String overlay) {
        if (overlay.isEmpty()) {
            return this;
        }
        return null;
    }

    @Override
    public Supplier<InputStream> getPackPng() {
        return getStream("pack.png");
    }
}
