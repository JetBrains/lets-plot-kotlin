# Lets-Plot for Kotlin Developers Guide 

### Build

Copy file `build_settings.template.yml` located in the project root directory to the file named `build_settings.yml` in the same location

run `./gradlew build`

### Demos

Aside from examples of Jupyter notebooks the project also contains a set of simple demos showing how plots can be built outside Jupyter notebook environment. Each such demo  has `main` method and is launched like any other simple JVM application.

The simple demos are in folders: 
```
lets-plot-kotlin/demo/jvm-javafx
lets-plot-kotlin/demo/browser
```

### Maven Artifacts

To use `lets-plot` and `lets-plot-kotlin` maven artifacts in your own project add the following dependencies: 
```
org.jetbrains.lets-plot:lets-plot-common:1.1.2-SNAPSHOT
org.jetbrains.lets-plot:lets-plot-jfx:1.1.2-SNAPSHOT
org.jetbrains.lets-plot:lets-plot-kotlin-api:0.0.9-SNAPSHOT
```

Please note that said maven artifacts are not yet released and are only available as snapshots.

### License

Code and documentation released under the [MIT license](https://github.com/JetBrains/lets-plot/blob/master/LICENSE).
Copyright 2019, JetBrains s.r.o.
