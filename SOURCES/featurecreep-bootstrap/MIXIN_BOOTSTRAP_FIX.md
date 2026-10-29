# Standalone Mixin service bootstrap

FeatureCreep ships a real Mixin host service for standalone (vanilla / Rift / Nil)
launches instead of patching service-loader resources at runtime.

## Why

The published `mixin-0.8.x.jar` only ships two host services:

    META-INF/services/org.spongepowered.asm.service.IMixinService
        org.spongepowered.asm.service.mojang.MixinServiceLaunchWrapper
        org.spongepowered.asm.service.modlauncher.MixinServiceModLauncher
    META-INF/services/org.spongepowered.asm.service.IGlobalPropertyService
        org.spongepowered.asm.service.mojang.Blackboard
        org.spongepowered.asm.service.modlauncher.Blackboard

`MixinService.initService()` picks the first service whose `isValid()` is true, but
the stock provider classes fail *linking* outside their hosts (they reference
`net.minecraft.launchwrapper.LaunchClassLoader` / ModLauncher types). The JDK
ServiceLoader only wraps `ClassNotFoundException` in a `ServiceConfigurationError`,
so the resulting raw `NoClassDefFoundError` escapes `hasNext()` and kills the
bootstrap before Mixin can skip the invalid entry.

## The fix

1. `asbestosstar.bootstrap.sm.mixinhost.StandaloneMixinService` - a real
   `IMixinService` (extends `MixinServiceAbstract`) with a class provider, a
   bytecode provider and a minimal platform agent for the game side name
   (`-Dfeaturecreep.side=CLIENT|DEDICATEDSERVER`).
2. `FeatureCreepGlobalPropertyService` - an in-process blackboard implementing
   `IGlobalPropertyService`, because the stock blackboard providers carry the same
   LaunchWrapper link-time landmine. Its constructor throws when the standalone
   runtime is not active, so `MixinService.initPropertyService()` skips it on hosts.
3. Both are registered in `META-INF/services/` in this jar.
4. `SpongeMixinUtils.installRuntimeJars` puts a **generated registration jar**
   (`~/.featurecreep/cache/jars/featurecreep-mixin-services.jar`, holding only the
   two `META-INF/services` entries) first on the standalone loader's URL list, so
   the ServiceLoader always enumerates FeatureCreep's registrations before the
   Mixin jar's stock entries and never parses the broken ones. The code source is
   consulted only for the `sm.mixinhost` implementation classes (child-first),
   never for service discovery, because launchers may attribute these classes to
   a fat bundle whose shade step strips `META-INF/services` from dependencies.
   The library root itself is discovered via the launcher's `libraryDirectory`
   system property, then `user.dir/libraries`, then the code source.

`StandaloneMixinService.isValid()` (and the property service constructor) only
claims the environment while `SpongeMixinUtils.isStandaloneRuntimeActive()` is
true, so host-owned loaders (Fabric/Forge/NeoForge/Sponge) keep their own Mixin
service even if this jar's services files are visible to them.

## Install vs. launch resolution

The launcher never resolves from Maven. `StandaloneMixinResolver` is strictly local:
it takes the runtime jars (`sponge-mixin` + the five ASM jars) from the library
directory the FeatureCreep installer already staged them into — derived from this
jar's own installed location — or, failing that, extracts copies bundled inside the
bootstrap jar itself. Only `MinecraftInstallSupport` (the installer) may download,
and it prefers artifacts bundled inside the installer jar over both the local Maven
cache and remote repositories. A launch without staged or bundled runtime jars fails
with an instruction to run the installer.

## Requirements

* Fabric's mixin fork `net.fabricmc:sponge-mixin:0.17.4+mixin.0.8.7` + ASM 9.8,
  resolved at launch by `StandaloneMixinResolver`. The fork declares ASM 9.8 and
  accepts newer class files than upstream 0.8.7's ASM 9.5 pairing. The fork keeps
  the same `org.spongepowered.asm` packages and the same mojang/modlauncher service
  registrations, so everything described above applies to it unchanged.
* No Guava needed from the host: unlike the upstream artifact, the Fabric fork
  shades Guava relocated to `org.spongepowered.include.com.google.common`.

The fork adds two abstract hooks to `IMixinService` that upstream 0.8.7 lacks —
`getAdviceProvider()` and `getFeatureValidator()` — consumed only by the
EnumExtension subtype. `StandaloneMixinService` returns the fork's built-in
`IAdviceProvider.GENERIC` constant and `null` for the validator (FeatureCreep
never produces enum-extension mixins), matching the stock services' behavior.

## Host behavior

Fabric/Forge/NeoForge/Sponge continue to use their host-owned Mixin runtime;
FeatureCreep never downloads a replacement for those hosts. Vanilla/Rift/Nil use
the standalone runtime plus the javaagent-installed `ClassFileTransformer`, with
`MixinRuntimeCoordinator` playing the LaunchWrapper tweaker role.
