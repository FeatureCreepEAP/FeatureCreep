# v12 direct implementation Mixin configuration

FeatureCreep now uses `featurecreepimpl.mixins.json` as the canonical Mixin configuration everywhere. The file lives in each Minecraft-version implementation module.

There is no separate bootstrap Mixin JSON config. Superloader metadata, module metadata, runtime fallback registration, and shaded manifests all point directly at the implementation config.

## Host behavior

- Fabric: `fabric.mod.json` exposes `featurecreepimpl.mixins.json` and a `preLaunch` entrypoint (`FabricBootstrapEntrypoint`).
- Forge/NeoForge: `[[mixins]]` advertises `featurecreepimpl.mixins.json` directly.
- Sponge: the Sponge plugin entrypoint remains, and the JAR manifest advertises `MixinConfigs: featurecreepimpl.mixins.json`.
- Vanilla/Nil/Rift: when FeatureCreep explicitly bootstraps a Mixin runtime, it registers `featurecreepimpl.mixins.json` programmatically.

Fabric/Forge/NeoForge/Sponge continue to use their host-owned Mixin runtime. FeatureCreep does not download a replacement Mixin runtime for those hosts.
