import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/** Minimal Java-only Maven Central downloader used by the repository launchers. */
public final class FPMBuildMavenCentralBootstrap {
    private FPMBuildMavenCentralBootstrap() {}

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: FPMBuildMavenCentralBootstrap <url> <destination>");
            System.exit(2);
        }

        URI uri = URI.create(args[0]);
        Path destination = Path.of(args[1]).toAbsolutePath().normalize();
        Files.createDirectories(destination.getParent());

        Path temp = Files.createTempFile(destination.getParent(), destination.getFileName().toString(), ".part");
        try {
            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .build();
            HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
            HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(temp));
            if (response.statusCode() != 200) {
                throw new IllegalStateException("Maven Central returned HTTP " + response.statusCode() + " for " + uri);
            }
            if (Files.size(temp) == 0) {
                throw new IllegalStateException("Downloaded FPMBuild JAR is empty: " + uri);
            }
            try {
                Files.move(temp, destination, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
            } catch (AtomicMoveNotSupportedException e) {
                Files.move(temp, destination, StandardCopyOption.REPLACE_EXISTING);
            }
        } finally {
            Files.deleteIfExists(temp);
        }
    }
}
