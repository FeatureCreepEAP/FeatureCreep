package asbestosstar.bootstrap.installer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Resolves the official vanilla server JAR from Mojang launcher metadata. */
final class MinecraftServerResolver {
    private static final URI VERSION_MANIFEST = URI.create("https://piston-meta.mojang.com/mc/game/version_manifest_v2.json");
    private final HttpClient http = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL).build();

    Path ensureServerJar(Path root, String requestedVersion, String fileName) throws Exception {
        Path target = root.resolve(fileName);
        if (Files.isRegularFile(target) && Files.size(target) > 0) return target;

        String manifest = getText(VERSION_MANIFEST);
        String selected = null;
        URI metadata = null;
        for (String candidate : upstreamCandidates(requestedVersion)) {
            String url = findVersionUrl(manifest, candidate);
            if (url != null) {
                selected = candidate;
                metadata = URI.create(url);
                break;
            }
        }
        if (metadata == null) throw new IOException("Minecraft version not found in Mojang manifest: " + requestedVersion);

        String versionJson = getText(metadata);
        Download server = findServerDownload(versionJson);
        if (server == null) throw new IOException("No dedicated server download is published for Minecraft " + selected);

        Files.createDirectories(target.getParent());
        Path tmp = Files.createTempFile(target.getParent(), "minecraft-server-", ".download");
        try {
            HttpRequest request = HttpRequest.newBuilder(server.uri()).GET().header("User-Agent", "FeatureCreep-Installer/12").build();
            HttpResponse<Path> response = http.send(request, HttpResponse.BodyHandlers.ofFile(tmp));
            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new IOException("Minecraft server download failed (HTTP " + response.statusCode() + ")");
            }
            if (server.sha1() != null && !server.sha1().isBlank()) {
                String actual = sha1(tmp);
                if (!actual.equalsIgnoreCase(server.sha1())) {
                    throw new IOException("Minecraft server checksum mismatch: expected " + server.sha1() + ", got " + actual);
                }
            }
            Files.move(tmp, target, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
            return target;
        } finally {
            Files.deleteIfExists(tmp);
        }
    }

    private String getText(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(uri).GET().header("User-Agent", "FeatureCreep-Installer/12").build();
        HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("Could not read " + uri + " (HTTP " + response.statusCode() + ")");
        }
        return response.body();
    }

    private static List<String> upstreamCandidates(String requested) {
        if ("26.4".equals(requested)) return List.of("26.4-snapshot1", "26.4");
        if ("26.1.2".equals(requested)) return List.of("26.1.2", "26.1");
        return List.of(requested);
    }

    private static String findVersionUrl(String manifest, String id) {
        Pattern p = Pattern.compile("\\{\\s*\\\"id\\\"\\s*:\\s*\\\"" + Pattern.quote(id)
                + "\\\".*?\\\"url\\\"\\s*:\\s*\\\"([^\\\"]+)\\\"", Pattern.DOTALL);
        Matcher m = p.matcher(manifest);
        return m.find() ? unescape(m.group(1)) : null;
    }

    private static Download findServerDownload(String json) {
        Pattern block = Pattern.compile("\\\"server\\\"\\s*:\\s*\\{(.*?)\\}", Pattern.DOTALL);
        Matcher bm = block.matcher(json);
        if (!bm.find()) return null;
        String body = bm.group(1);
        String url = capture(body, "\\\"url\\\"\\s*:\\s*\\\"([^\\\"]+)\\\"");
        if (url == null) return null;
        String sha1 = capture(body, "\\\"sha1\\\"\\s*:\\s*\\\"([^\\\"]+)\\\"");
        return new Download(URI.create(unescape(url)), sha1);
    }

    private static String capture(String text, String regex) {
        Matcher m = Pattern.compile(regex, Pattern.DOTALL).matcher(text);
        return m.find() ? m.group(1) : null;
    }

    private static String unescape(String s) {
        return s.replace("\\/", "/").replace("\\u0026", "&");
    }

    private static String sha1(Path path) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        try (var in = Files.newInputStream(path)) {
            byte[] buf = new byte[64 * 1024];
            for (int n; (n = in.read(buf)) >= 0;) if (n > 0) md.update(buf, 0, n);
        }
        return HexFormat.of().formatHex(md.digest());
    }

    private record Download(URI uri, String sha1) {}
}
