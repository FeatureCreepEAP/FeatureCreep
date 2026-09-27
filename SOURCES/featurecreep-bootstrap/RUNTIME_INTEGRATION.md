# Minecraft runtime integration (FeatureCreep 12)

FeatureCreep distinguishes host-owned Mixin runtimes from standalone transformation hosts.

| Host | Mixin source | Transformation path | FeatureCreep agent by default? |
| --- | --- | --- | --- |
| Fabric | Fabric's existing Mixin | Knot/Mixin | No |
| MinecraftForge | Forge's existing Mixin | ModLauncher/FML | No |
| NeoForge | NeoForge's existing Mixin | ModLauncher/FML | No |
| SpongePowered | Sponge's existing Mixin | Sponge host | No |
| RiftLoader | Existing Rift Mixin when present; standalone resolver otherwise | LaunchWrapper/Rift transformer chain | No |
| NilLoader | Standalone Mixin resolver when needed | `nilloader.api.ClassTransformer` | No |
| Vanilla | Standalone Mixin resolver when a module declares `MixinConfigs` | FeatureCreep transformer via Instrumentation | Only when required |

The FeatureCreep agent is attached only when the loaded module set declares a transformation/hotswap requirement, or when vanilla Minecraft has module-declared Mixin configurations and therefore has no host transformer to use. Merely starting FeatureCreep does not attach an agent.

## Mixin configuration

The canonical Minecraft Mixin configuration is `featurecreepimpl.mixins.json`. It lives in each Minecraft-version implementation module and contains the implementation mixins that provide FeatureCreep's early Minecraft hooks.

Fabric, Forge, NeoForge, module metadata, and shaded JAR manifests all advertise that implementation config directly. There is no second bootstrap Mixin JSON layer. `SpongeMixinConfig` remains only as an idempotent compatibility helper for older integrations that may still instantiate the plugin class.

Fabric additionally uses a `preLaunch` entrypoint (`FabricBootstrapEntrypoint`) that calls `MinecraftCommonStartup.bootstrap()`. This performs loader/module discovery and Mixin setup at prelaunch without running content modules before Minecraft registries are ready. `FeatureCreepMC.init()` later calls the idempotent `MinecraftCommonStartup.start()` from the game-side registry hook. Fabric still owns the active Mixin runtime.

FeatureCreep does **not** download or embed a second Mixin runtime on Fabric/Forge/NeoForge/Sponge. The Maven Mixin dependency is `provided`; Vanilla/Nil/Rift can still use the standalone resolver when their transformation path requires it.

## Standalone installer

`asbestosstar.bootstrap.FeatureCreepMain` is the executable installer main class. It does not open Swing when running under a JBoss Modules/FeatureCreep classloader or when `featurecreep.runtime=true`.

The installer supports Minecraft client profiles compatible with the vanilla launcher/TLauncher directory format and a dedicated-server layout. Artifact resolution is:

1. embedded artifacts inside the running installer JAR,
2. `~/.m2/repository`,
3. the artifact repository (Maven Central for FeatureCreep artifacts; Sponge's repository for standalone Mixin).

Build with `-Pstandalone-installer-bundle` to place FeatureCreep Loader/API and standalone Mixin JARs under `META-INF/featurecreep/artifacts` so offline/current-JAR resolution can succeed first.
