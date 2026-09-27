# FeatureCreep v12 cleanup and test-suite update

## Deprecated / obsolete code removed

- Removed the unused `ExitManager` SecurityManager implementation.
- Removed the unused privileged `GetURLConnectionAction` helper.
- Removed `AccessController`, `AccessControlContext`, SecurityManager checks, and privileged-action wrappers from the path resource loader.
- Removed retired `FCLoaderBasic` compatibility methods:
  - `addNeededPackages(...)`
  - `getFeatureCreepJar()`
  - loader-level `setInstrumentation(...)`
  - `getCombinedDepSpecs(...)`
  - `combineModuleDepSpecs()`
  - `getContext()`
- Removed deprecated EventViewer reflection-registration convenience wrappers. `ReflectionEventListener` itself remains available.
- Made the old two-argument `FileSystemResourceLoader` constructor internal instead of exposing a deprecated public constructor.

## Warning cleanup

- Added explicit UTF-8 build/reporting encodings.
- Javadoc now keeps normal doclint checks while excluding only the legacy `missing` category (`all,-missing`).
- Removed raw `Class[]` signatures in `FCInstrumentation`.
- Reworked R9 initialization so `this` no longer escapes from partially initialized fields/constructors.
- Loader main sources compile cleanly with `javac --release 11 -Xlint:all`.
- Loader Javadocs complete cleanly with `-Xdoclint:all,-missing`.
- Removed duplicate API imports and stopped `PKZipUtils` from swallowing declared I/O failures.
- `FCLoaderObtainer` now uses `Class<?>` instead of a raw `Class`.
- FCAPI Maven baseline is now Java 11, matching its existing use of `ProcessHandle` and other post-Java-8 APIs.

## Test suite

JUnit 5 + Maven Surefire were added to Loader, API, and Bootstrap.

Validation coverage includes:

- module property parsing and side/visibility behavior;
- `MixinConfigs`, `SpongeMixinConfig`, and FeatureCreep Mixin config merging;
- `module.xml` parsing for dependencies, resource roots, services, main class, version and properties;
- path-resource loading without SecurityManager APIs;
- host class/resource bridging;
- a real JBoss Modules integration test with two temporary FeatureCreep mods;
- automatic public mod-to-mod visibility;
- host/game class visibility;
- different private nested dependency versions per mod with separate class identity;
- MD5 and SHA-256 known vectors;
- ModelNode JSON behavior;
- PKZip utility behavior;
- Minecraft game-provider launch/classloader/path defaults;
- Bootstrap class-existence checks without invoking agent attachment.

Local validation result: 20 test methods passed (11 Loader, 5 API, 4 Bootstrap).

## Maven commands

Normal development does not sign or publish:

```bash
mvn clean test
mvn clean package
mvn clean install
```

Central release remains explicit:

```bash
mvn clean deploy -Pcentral-release
```

## v12 Java 25 / attach alignment
- Bootstrap now targets Java 25 because FCAPI 12 is Java 25+.
- Removed JNA/JNA-platform dependencies and shade exclusions.
- Removed the `jdk.attach` self-attach path; Bootstrap now uses `featurecreep.attach.Attach` directly.
- Added a compile-time dependency on `featurecreep-api:12` and a bootstrap-side linkage test.

## Host-aware Mixin / installer update

- Fabric, MinecraftForge, NeoForge and SpongePowered are host-owned Mixin environments; FeatureCreep never resolves/downloads a replacement Mixin runtime for them.
- Standardized on the version-specific `featurecreepimpl.mixins.json` config everywhere. Superloader metadata and shaded manifests now advertise it directly; `SpongeMixinConfig` remains only as a compatibility helper for older integrations.
- The bootstrap manifest again carries `MixinConfigs: featurecreepimpl.mixins.json`; Fabric and Forge/NeoForge metadata also advertise that config. The compile-time `org.spongepowered:mixin` dependency is `provided`, so it is not a second runtime bundled into host-owned loaders.
- Fabric uses a `preLaunch` entrypoint (`FabricBootstrapEntrypoint`) that calls `MinecraftCommonStartup.bootstrap()`. This performs loader/module discovery and Mixin setup early, but intentionally defers `runMods()` until the game-side registry hook calls `FeatureCreepMC.init()`. Fabric still supplies the host Mixin runtime.
- Added SpongePowered, RiftLoader and NilLoader `GameProvider` targets.
- Rift integrates Mixin through its LaunchWrapper transformer path; NilLoader uses `nilloader.api.ClassTransformer`; neither path requires FeatureCreep Instrumentation by default.
- Vanilla resolves/boots standalone Mixin only when FC modules declare Mixin configs; the agent is not attached during ordinary startup.
- Added the standalone Swing installer and executable `Main-Class` with JBoss/FeatureCreep runtime suppression.
- Installer resolution order is embedded/current JAR, local Maven repository, then remote repository.
- Added optional `standalone-installer-bundle` Maven profile to embed Loader/API/Mixin artifact JARs as installer resources.
