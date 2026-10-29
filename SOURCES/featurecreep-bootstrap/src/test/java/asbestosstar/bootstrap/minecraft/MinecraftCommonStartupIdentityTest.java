package asbestosstar.bootstrap.minecraft;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MinecraftCommonStartupIdentityTest {

    @BeforeEach
    void resetState() {
        MinecraftCommonStartup.resetIdentityForTests();
    }

    @Test
    void listenerRegisteredBeforePublicationReceivesIdentityOnce() {
        List<MinecraftGameIdentity> seen = new ArrayList<>();
        MinecraftCommonStartup.addIdentityListener(seen::add);
        assertTrue(seen.isEmpty());

        MinecraftGameIdentity identity = new MinecraftGameIdentity("minecraft", "26.3", "vanilla", "unknown", "12");
        MinecraftCommonStartup.publishIdentity(identity);
        MinecraftCommonStartup.publishIdentity(identity);

        assertEquals(1, seen.size());
        assertEquals(identity, seen.get(0));
    }

    @Test
    void listenerRegisteredAfterPublicationIsInvokedImmediately() {
        MinecraftGameIdentity identity = new MinecraftGameIdentity("minecraft", "26.3", "vanilla", "unknown", "12");
        MinecraftCommonStartup.publishIdentity(identity);

        List<MinecraftGameIdentity> seen = new ArrayList<>();
        MinecraftCommonStartup.addIdentityListener(seen::add);
        assertEquals(List.of(identity), seen);
    }

    @Test
    void throwingListenerDoesNotBreakOtherListeners() {
        MinecraftGameIdentity identity = new MinecraftGameIdentity("minecraft", "26.3", "vanilla", "unknown", "12");
        List<MinecraftGameIdentity> seen = new ArrayList<>();
        MinecraftCommonStartup.addIdentityListener(value -> { throw new IllegalStateException("boom"); });
        MinecraftCommonStartup.addIdentityListener(seen::add);

        MinecraftCommonStartup.publishIdentity(identity);
        assertEquals(List.of(identity), seen);
    }

    @Test
    void identityAccessorIsNeverNull() {
        MinecraftGameIdentity identity = MinecraftCommonStartup.identity();
        assertNotNull(identity);
        assertNotNull(identity.gameId());
        assertNotNull(identity.loaderVersion());
    }
}
