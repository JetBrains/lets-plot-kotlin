# Lets-Plot for Kotlin Developers Guide

### Building the project

Copy file `build_settings.template.yml` located in the project root directory to the file named `build_settings.yml` in
the same location.

run `./gradlew build`

### Running demos

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

### Lets-Plot in JVM and Kotlin/JS applications

Lets-Plot library enables embedding plots into a JVM or Kotlin/JS application.

When in JVM environment the Lets-Plot library offers a choice between [JavaFX](https://en.wikipedia.org/wiki/JavaFX)
graphics and rendering powered by [Apache Batik SVG Toolkit](https://xmlgraphics.apache.org/batik/).

#### Artifacts

All artifacts are available at [Maven Central](https://search.maven.org/search?q=lets-plot).

<table>
    <tr>
        <td>Lets-Plot Kotlin API</td>
        <td>
            <i>lets-plot-kotlin-jvm</i><br>
            <i>lets-plot-kotlin-js</i><br>
            <i>lets-plot-kotlin-geotools</i>
        </td>
        <td>
            <a href="https://search.maven.org/search?q=lets-plot"/>
            <img src="https://img.shields.io/maven-central/v/org.jetbrains.lets-plot/lets-plot-kotlin?color=blue&label=Maven%20Central"/>
        </td>
        <td>
            Published by this project.
        </td>
    </tr>
    <tr>
        <td>Lets-Plot library</td>
        <td>
            <i>lets-plot-batik</i><br>
            <i>lets-plot-jfx</i><br>
            <i>lets-plot-common</i>
        </td>
        <td>
            <a href="https://search.maven.org/search?q=lets-plot"/>
            <img src="https://img.shields.io/maven-central/v/org.jetbrains.lets-plot/lets-plot-common?color=blue&label=Maven%20Central"/>
        </td>
        <td>
            Published by the <a href="https://github.com/JetBrains/lets-plot">Lets-Plot library</a> project.
        </td>
    </tr>
</table>

#### Project dependencies

The following is how you configure a Gradle (Groovy) project:

```groovy
repositories {
  mavenCentral()
}
```

- JVM/Swing/Batik application:

```groovy
dependencies {
    implementation "org.jetbrains.lets-plot:lets-plot-batik:2.4.0"
    implementation "org.jetbrains.lets-plot:lets-plot-kotlin-jvm:4.0.0"
}
```

- JVM/Swing/JavaFX application:

```groovy
dependencies {
  implementation "org.jetbrains.lets-plot:lets-plot-jfx:2.4.0"
  implementation "org.jetbrains.lets-plot:lets-plot-kotlin-jvm:4.0.0"
}
```

> *Note*: Depending on which JRE you are using, you might need to add JavaFX dependencies as well.

- JVM/other:

If your JVM app doesn't use either frontend, you can provide just "lets-plot-common" dependency:

```groovy
dependencies {
  implementation "org.jetbrains.lets-plot:lets-plot-common:2.4.0"
  implementation "org.jetbrains.lets-plot:lets-plot-kotlin-jvm:4.0.0"
}
```

- Kotlin/JS application:

```groovy
dependencies {
  implementation "org.jetbrains.lets-plot:lets-plot-kotlin-js:4.0.0"
}
```

#### Example applications

The [lets-plot-mini-apps](https://github.com/alshan/lets-plot-mini-apps) GitHub repository contains examples of using
the Lets-Plot Kotlin API in JVM and Kotlin/JS projects.

You will also find similar "minimal" apps in this repository.

- JVM/Swing/Apache Batik:
  [minimalDemo/Main.kt](https://github.com/JetBrains/lets-plot-kotlin/blob/master/demo/jvm-batik/src/main/kotlin/minimalDemo/Main.kt)

- JVM/Swing/JavaFX Scene:
  [minimalDemo/Main.kt](https://github.com/JetBrains/lets-plot-kotlin/blob/master/demo/jvm-javafx/src/main/kotlin/minimalDemo/Main.kt)

- Kotlin/JS:
  [js-frontend-app](https://github.com/JetBrains/lets-plot-kotlin/tree/master/demo/js-frontend-app)

- Kotlin/JS IR:
  [js-ir-frontend-app](https://github.com/JetBrains/lets-plot-kotlin/tree/master/demo/js-ir-frontend-app)


### Frontend context

`Frontend context` is required for method `show()` in `Figure` interface to work properly. Both `Plot` and `GGBunch`
classes in `Lets-Plot` Kotlin API implement `Figure` interface. Calling method `show` is typical for interactive
notebook-like applications.

The following steps describe how to use `Lets-Plot` JVM "backend" in "browser" frontend context.

#### Add dependencies on maven artifacts

```
org.jetbrains.lets-plot:lets-plot-common:2.4.0
org.jetbrains.lets-plot-kotlin:lets-plot-kotlin-jvm:4.0.0
```

#### Implement the `jetbrains.letsPlot.FrontendContext` interface:

```
import jetbrains.datalore.plot.PlotHtmlHelper
import org.jetbrains.letsPlot.FrontendContext

class MyNotebookContext: FrontendContext {
    override fun display(plotSpecRaw: MutableMap<String, Any>) {
        val html = PlotHtmlHelper.getDynamicDisplayHtmlForRawSpec(plotSpecRaw)
        // Append this `html` to your document ...
    }
}
```

#### Initialize `lets-plot` at runtime

Load `Lets-Plot` JS library to the user agent:
```
import jetbrains.datalore.plot.PlotHtmlHelper
import jetbrains.datalore.plot.PlotHtmlHelper.scriptUrl

val ver = .. // JS library version, for example: "1.2.3"
val html:String = PlotHtmlHelper.getDynamicConfigureHtml(scriptUrl(ver), false)
// include `configure html` in your document ...
```

Define `Lets-Plot` frontend context:
```
import org.jetbrains.letsPlot.LetsPlot
...
LetsPlot.frontendContext = MyNotebookContext()
```


### License

Code and documentation released under the [MIT license](https://github.com/JetBrains/lets-plot-kotlin/blob/master/LICENSE).
Copyright © 2019-2022, JetBrains s.r.o.
