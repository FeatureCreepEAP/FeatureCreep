# Executable Minecraft bundle fix

The four current Minecraft projects still produce both artifacts on the normal Maven `package` phase:

- thin FeatureCreep/JBoss module JAR
- attached `jar-with-dependencies` runtime/installer JAR

The thin artifact keeps `featurecreep-bootstrap` as a `provided` dependency.
The attached shaded artifact uses Maven Shade 3.6.0 `extraArtifacts` to add
`com.asbestosstar:featurecreep-bootstrap:${featurecreep.version}` without changing
the thin module's dependency semantics.

The shaded manifest now includes:

- `Main-Class: asbestosstar.bootstrap.FeatureCreepMain`
- `Premain-Class: asbestosstar.bootstrap.FeatureCreepAgent`
- `Agent-Class: asbestosstar.bootstrap.FeatureCreepAgent`
- `Can-Redefine-Classes: true`
- `Can-Retransform-Classes: true`
- `MixinConfigs: featurecreep.mixins.json`
- `FeatureCreep-Artifact-Id: featurecreepmc-<minecraft-version>`

Therefore:

```sh
mvn clean package
java -jar target/*-jar-with-dependencies.jar
```

opens the FeatureCreep installer, while the thin JAR remains a normal module artifact.

The bootstrap resolver was also hardened so a Minecraft fat JAR that contains
Bootstrap classes is not mistaken for the standalone `featurecreep-bootstrap`
artifact when resolving installer dependencies.
