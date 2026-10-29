package asbestosstar.bootstrap.installer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.net.URI;

import org.junit.jupiter.api.Test;

class MinecraftServerResolverTest {
    private static final String MANIFEST = """
            {"latest":{"release":"26.4","snapshot":"26.4-snapshot1"},"versions":[
              {"id":"26.4-snapshot1","type":"snapshot","url":"https://piston-meta.mojang.com/v1/packages/aaa/26.4-snapshot1.json","time":"t","releaseTime":"t"},
              {"id":"26.4","type":"release","url":"https:\\/\\/piston-meta.mojang.com\\/v1\\/packages\\/bbb\\/26.4.json","time":"t","releaseTime":"t"},
              {"id":"26.1","type":"release","url":"https://piston-meta.mojang.com/v1/packages/ccc/26.1.json","time":"t","releaseTime":"t"}
            ]}""";

    @Test
    void findsVersionUrlByExactId() {
        assertEquals("https://piston-meta.mojang.com/v1/packages/aaa/26.4-snapshot1.json",
                MinecraftServerResolver.findVersionUrl(MANIFEST, "26.4-snapshot1"));
        assertEquals("https://piston-meta.mojang.com/v1/packages/ccc/26.1.json",
                MinecraftServerResolver.findVersionUrl(MANIFEST, "26.1"));
    }

    @Test
    void doesNotMatchPrefixOfAnotherVersionId() {
        assertNull(MinecraftServerResolver.findVersionUrl(MANIFEST, "26.4-snap"));
        assertNull(MinecraftServerResolver.findVersionUrl(MANIFEST, "26.2"));
    }

    @Test
    void unescapesVersionUrl() {
        assertEquals("https://piston-meta.mojang.com/v1/packages/bbb/26.4.json",
                MinecraftServerResolver.findVersionUrl(MANIFEST, "26.4"));
    }

    @Test
    void readsServerDownloadAndChecksumOnly() {
        String json = """
                {"downloads":{
                  "client":{"sha1":"clientsha","size":1,"url":"https://piston-data.mojang.com/client.jar"},
                  "server":{"sha1":"serversha","size":2,"url":"https:\\/\\/piston-data.mojang.com\\/v1\\/objects\\/abc\\/server.jar"}
                }}""";
        MinecraftServerResolver.Download download = MinecraftServerResolver.findServerDownload(json);

        assertEquals(URI.create("https://piston-data.mojang.com/v1/objects/abc/server.jar"), download.uri());
        assertEquals("serversha", download.sha1());
    }

    @Test
    void reportsMissingServerDownload() {
        String json = "{\"downloads\":{\"client\":{\"sha1\":\"x\",\"url\":\"https://piston-data.mojang.com/client.jar\"}}}";
        assertNull(MinecraftServerResolver.findServerDownload(json));
    }
}
