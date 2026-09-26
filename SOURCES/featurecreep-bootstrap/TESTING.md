# FeatureCreep v12 tests

Each project now uses JUnit 5 and Maven Surefire.

Run locally in dependency order:

```bash
cd featurecreep-loader
mvn clean install

cd ../featurecreep-api
mvn clean install

cd ../featurecreep-bootstrap
mvn clean test
```

The loader suite includes an integration test that creates temporary FeatureCreep mod JARs at runtime and verifies:

- public FeatureCreep mods can resolve one another automatically;
- FeatureCreep mods can resolve host/game classes;
- nested dependencies are private by default;
- two mods can load different classes with the same dependency package/class name without sharing class identity;
- `module.xml` metadata, Mixin configuration properties, resource roots, services and dependency flags are parsed correctly.

Normal `package`, `test`, and `install` do not perform GPG signing. Central signing remains under `-Pcentral-release`.

The bootstrap also verifies that the FeatureCreep API direct attach class is linked. Full unrestricted attach integration coverage lives in `featurecreep-api`, where a plain child JVM is attached without `jdk.attach` enabling flags.

## Minecraft host/installer tests added for v12

- `MinecraftHostPolicyTest` checks that Fabric/Forge/NeoForge/Sponge are host-owned Mixin environments and Rift/Nil expose transformer pipelines without claiming host-owned Mixin.
- `InstallerMetadataTest` checks that `featurecreepimpl.mixins.json` is published directly to Fabric/Forge/NeoForge, Fabric exposes the `preLaunch` bootstrap entrypoint, and Sponge metadata exists.
- `FeatureCreepMainTest` checks explicit FeatureCreep runtime suppression of the standalone GUI.

The local validation environment has JDK 21 and no Maven, so Java-25 Maven execution must still be run on a JDK 25 machine. Installer-only and host-detection source was compiled locally with `javac -Xlint:all` where dependencies permitted.
