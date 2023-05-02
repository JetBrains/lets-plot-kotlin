## Info for Lets-Plot Developers / Contributors

### Building the Project

Copy file `build_settings.template.yml` located in the project root directory to the file named `build_settings.yml` in
the same location.

run `./gradlew build`

### Running Demos

Aside from of Jupyter demo notebooks the project also contains a set of simple demos showing how plots can be built in a
JVM or Kotlin/JS environment.

Each JVM demo has `main` method and is launched like any other JVM application.

The Kotlin/JS demo app is launched using Gradle task: `./gradlew :js-frontend-app:browserDevelopmentRun`

The simple demos can be found in the following folders:

```
lets-plot-kotlin/demo/browser
lets-plot-kotlin/demo/jvm-batik
lets-plot-kotlin/demo/jvm-javafx
lets-plot-kotlin/demo/geotools-batik
lets-plot-kotlin/demo/js-frontend-app
```

### License

Code and documentation released under the [MIT license](https://github.com/JetBrains/lets-plot-kotlin/blob/master/LICENSE).
Copyright © 2019-2023, JetBrains s.r.o.
