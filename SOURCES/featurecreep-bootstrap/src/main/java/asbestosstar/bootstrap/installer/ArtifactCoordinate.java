package asbestosstar.bootstrap.installer;

/** Minimal Maven coordinate used by the installer. */
public record ArtifactCoordinate(String groupId, String artifactId, String version, String repository) {
    public String relativePath() {
        return groupId.replace('.', '/') + "/" + artifactId + "/" + version + "/" + artifactId + "-" + version + ".jar";
    }
    public String notation() { return groupId + ":" + artifactId + ":" + version; }
}
