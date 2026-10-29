package asbestosstar.bootstrap.installer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import asbestosstar.bootstrap.FeatureCreepMain;

/**
 * Resolves installer artifacts in this order: current FeatureCreep JAR resources,
 * local Maven repository, then the coordinate's remote Maven repository.
 */
public final class ArtifactResolver {
    public static final String CENTRAL = "https://repo1.maven.org/maven2/";
    public static final String SPONGE = "https://repo.spongepowered.org/repository/maven-public/";
    public static final String FABRIC = "https://maven.fabricmc.net/";

    private final Path m2;
    private final HttpClient http;

    public ArtifactResolver() {
        this(Path.of(System.getProperty("user.home"), ".m2", "repository"));
    }

    public ArtifactResolver(Path m2) {
        this.m2 = m2.toAbsolutePath().normalize();
        this.http = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL).build();
    }

    public Path resolve(ArtifactCoordinate c) throws IOException, InterruptedException {
        Path local = m2.resolve(c.relativePath());
        Files.createDirectories(local.getParent());

        // Resolution order is deliberate: the running installer may embed an exact
        // compatible artifact set, which takes precedence over a stale local cache.
        if (extractFromCurrentJar(c, local)) return local;
        // The bootstrap coordinate keeps version 12 across rebuilds, so a plain
        // existence check would reuse a stale cached jar from an earlier install
        // forever. Compare a canary class against the running code and refresh the
        // cache when it no longer matches what is actually running.
        if (Files.isRegularFile(local) && Files.size(local) > 0 && !isStaleBootstrap(c, local)) return local;

        URI uri = URI.create(urlOf(c));
        Path tmp = Files.createTempFile(local.getParent(), c.artifactId(), ".download");
        try {
            HttpRequest request = HttpRequest.newBuilder(uri).GET().header("User-Agent", "FeatureCreep-Installer/12").build();
            HttpResponse<Path> response = http.send(request, HttpResponse.BodyHandlers.ofFile(tmp));
            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new IOException("Could not download " + c.notation() + " from " + uri + " (HTTP " + response.statusCode() + ")");
            }
            Files.move(tmp, local, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
            return local;
        } finally {
            Files.deleteIfExists(tmp);
        }
    }

    private boolean extractFromCurrentJar(ArtifactCoordinate c, Path target) throws IOException {
        String[] resources = {
                "/META-INF/featurecreep/artifacts/" + c.artifactId() + "-" + c.version() + ".jar",
                "/jars/" + c.artifactId() + "-" + c.version() + ".jar"
        };
        for (String resource : resources) {
            try (InputStream in = FeatureCreepMain.class.getResourceAsStream(resource)) {
                if (in != null) {
                    Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
                    return true;
                }
            }
        }

        // The running JAR itself is a valid source for the bootstrap coordinate ONLY
        // when the outer artifact really is featurecreep-bootstrap. Minecraft's
        // jar-with-dependencies also contains the bootstrap classes so that `java -jar`
        // can open the installer, but copying that whole game bundle into the Maven
        // location for featurecreep-bootstrap would be incorrect and would duplicate
        // Loader/API/game classes on the installed launcher class path.
        if ("featurecreep-bootstrap".equals(c.artifactId())) {
            try {
                Path self = Path.of(FeatureCreepMain.class.getProtectionDomain().getCodeSource().getLocation().toURI());
                if (Files.isRegularFile(self) && isBootstrapArtifact(self)) {
                    Files.copy(self, target, StandardCopyOption.REPLACE_EXISTING);
                    return true;
                }
            } catch (Exception ignored) {
            }
        }
        return false;
    }

    private static final String BOOTSTRAP_CANARY = "asbestosstar/bootstrap/BootstrapCommon.class";

    private boolean isStaleBootstrap(ArtifactCoordinate c, Path local) {
        if (!"featurecreep-bootstrap".equals(c.artifactId())) return false;
        try (InputStream running = FeatureCreepMain.class.getClassLoader().getResourceAsStream(BOOTSTRAP_CANARY);
             JarFile jf = new JarFile(local.toFile())) {
            var entry = jf.getJarEntry(BOOTSTRAP_CANARY);
            if (running == null || entry == null) return false;
            if (Arrays.equals(running.readAllBytes(), jf.getInputStream(entry).readAllBytes())) return false;
            System.err.println("[FeatureCreep] Cached " + c.notation() + " does not match the running installer; refreshing it.");
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    static boolean isBootstrapArtifact(Path jar) {
        try (JarFile jf = new JarFile(jar.toFile())) {
            Manifest mf = jf.getManifest();
            if (mf != null) {
                String id = mf.getMainAttributes().getValue("FeatureCreep-Artifact-Id");
                if ("featurecreep-bootstrap".equals(id)) return true;
                if (id != null && !id.isBlank()) return false;
            }
        } catch (IOException ignored) {
        }
        String name = jar.getFileName().toString();
        return name.startsWith("featurecreep-bootstrap-") && name.endsWith(".jar");
    }

    public static String urlOf(ArtifactCoordinate c) {
        String base = c.repository() == null || c.repository().isBlank() ? CENTRAL : c.repository();
        return base.endsWith("/") ? base + c.relativePath() : base + "/" + c.relativePath();
    }

    public Path m2Repository() { return m2; }
}
