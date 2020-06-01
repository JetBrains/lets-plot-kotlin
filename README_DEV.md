# Lets-Plot for Kotlin Developers Guide 

### Building the project

Copy file `build_settings.template.yml` located in the project root directory to the file named `build_settings.yml` in the same location.

run `./gradlew build`

### Running demos

Aside from examples of Jupyter notebooks the project also contains a set of simple demos showing how plots can be built outside Jupyter notebook environment. Each such demo  has `main` method and is launched like any other simple JVM application.

The simple demos can be found in the following folders: 
```
lets-plot-kotlin/demo/browser
lets-plot-kotlin/demo/jvm-batik
lets-plot-kotlin/demo/jvm-javafx
```

### Creating plots in JVM-based applications

Lets-Plot library enables embedding plots into a JVM-based application.

It also offers a choice between [JavaFX](https://en.wikipedia.org/wiki/JavaFX) graphics and rendering powered by [Apache Batik SVG Toolkit](https://xmlgraphics.apache.org/batik/). 

#### Required Maven artifacts published by the main [Lets-Plot](https://github.com/JetBrains/lets-plot) project

- `lets-plot-common`
- `lets-plot-batik` or `lets-plot-jfx` (choose one) 

[ ![Download](https://api.bintray.com/packages/jetbrains/lets-plot-maven/lets-plot-jars/images/download.svg)](https://bintray.com/jetbrains/lets-plot-maven/lets-plot-jars/_latestVersion)

These artifacts are available at [jcenter](https://bintray.com/bintray/jcenter) Maven repository (package `lets-plot-jars`).


#### Optional but indispensable Kotlin API artifact (published by this project)

- `lets-plot-kotlin-api`

This artifact is not yet released and only available as a SNAPSHOT.

[ ![Download](https://api.bintray.com/packages/jetbrains/lets-plot-maven/lets-plot-kotlin-api-jars/images/download.svg)](https://bintray.com/jetbrains/lets-plot-maven/lets-plot-kotlin-api-jars/_latestVersion)

Maven repository: `https://jetbrains.bintray.com/lets-plot-maven`

The following is an example fragment of a Gradle project that uses `lets-plot-kotlin-api` artifact:

```groovy
dependencies {
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
    api "org.jetbrains.lets-plot:lets-plot-kotlin-api:0.0.10-SNAPSHOT"
}
```

```groovy
repositories {
    maven {
        url "https://jetbrains.bintray.com/lets-plot-maven"
    }
}
```


#### An example of a **minimal JVM-based** application

- Using Apache Batik: 
    [minimalDemo/Main.kt](https://github.com/JetBrains/lets-plot-kotlin/blob/master/demo/jvm-batik/src/main/kotlin/minimalDemo/Main.kt)

- Using JavaFX: 
    [minimalDemo/Main.kt](https://github.com/JetBrains/lets-plot-kotlin/blob/master/demo/jvm-javafx/src/main/kotlin/minimalDemo/Main.kt)


### Frontend context

`Frontend context` is required for method `show()` in `Figure` interface to work properly. Both `Plot` and `GGBunch` classes in `Lets-Plot` Kotlin API implement `Figure` interface. 
Calling method `show` is typical for interactive notebook-like applications.

The following steps describe how to use `Lets-Plot` JVM "backend" in "browser" frontend context.

#### Add dependencies on maven artifacts

```
org.jetbrains.lets-plot:lets-plot-common:1.3.1
org.jetbrains.lets-plot:lets-plot-kotlin-api:0.0.9-SNAPSHOT
```

#### Implement interface `jetbrains.letsPlot.FrontendContext` like: 
```
import jetbrains.datalore.plot.PlotHtmlHelper
import jetbrains.letsPlot.FrontendContext

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

val ver = .. // JS library version, for example: "1.1.dev2"
val html:String = PlotHtmlHelper.getDynamicConfigureHtml(scriptUrl(ver), false)
// include `configure html` in your document ...
```

Define `Lets-Plot` frontend context:
```
import jetbrains.letsPlot.LetsPlot
...
LetsPlot.frontendContext = MyNotebookContext()
```


### License

Code and documentation released under the [MIT license](https://github.com/JetBrains/lets-plot/blob/master/LICENSE).
Copyright 2019, JetBrains s.r.o.
