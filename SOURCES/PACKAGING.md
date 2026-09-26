# FeatureCreep game-target Maven packaging

A normal game-target build now creates **both** artifacts again:

```sh
mvn clean package
```

For a Minecraft project such as 26.3 this produces:

```text
target/featurecreepmc-26.3-12.jar
target/featurecreepmc-26.3-12-jar-with-dependencies.jar
```

The first JAR is the thin game-specific API. The second is the attached runtime bundle.

## Minecraft / DangerZone runtime bundle

The bundled JAR contains the current project's game-specific API plus exactly the FeatureCreep runtime pieces that historically travelled with it:

- `org.jboss.modules:jboss-modules:2.3.0`
- `com.asbestosstar:featurecreep-loader:12`
- `com.asbestosstar:featurecreep-api:12`
- `com.github.vincentzhang96:DDS4J:1.0.2`
- `ar.com.hjg:pngj:2.1.0`

Minecraft/game libraries, Fabric/Forge/NeoForge/Sponge libraries, Guava/Gson and `featurecreep-bootstrap` remain external/provided. The bootstrap is intentionally not duplicated inside every game API JAR.

## Hearts of Iron IV

The HOI4 target gets the same thin + bundled build, containing JBoss Modules + Loader + API, but not DDS4J/PNGJ because that target does not use the DDS resource-pack conversion code.

The bundled artifact is attached by `maven-shade-plugin` during the normal `package` phase; no profile or second Maven command is required.

## Canonical build system

The current game projects are intentionally Maven-only. Legacy Gradle build files and wrappers have been removed from the Minecraft target projects. Dependency/source setup is performed by the FCDependencies Maven Mojo from each project's `pom.xml`.

Use:

```sh
mvn clean package
```

as the canonical local build command.

## Executable Minecraft runtime JAR

For Minecraft 26.1.2, 26.2, 26.3 and 26.4 the attached
`*-jar-with-dependencies.jar` is also the standalone FeatureCreep installer.
It includes `featurecreep-bootstrap` and its manifest contains:

- `Main-Class: asbestosstar.bootstrap.FeatureCreepMain`
- `Premain-Class: asbestosstar.bootstrap.FeatureCreepAgent`
- `Agent-Class: asbestosstar.bootstrap.FeatureCreepAgent`
- `MixinConfigs: featurecreep.mixins.json`

Therefore both artifacts are still produced by the normal build, but only the
bundled artifact is intended to be launched with `java -jar`:

```sh
mvn clean package
java -jar target/*-jar-with-dependencies.jar
```

The thin artifact remains the normal game-specific JBoss Modules/FeatureCreep
module and intentionally has no standalone installer `Main-Class`.
