# Lets-Plot for Kotlin Developers Guide 

### Building the project

Copy file `build_settings.template.yml` located in the project root directory to the file named `build_settings.yml` in the same location.

run `./gradlew build`

### Running demos

Aside from examples of Jupyter notebooks the project also contains a set of simple demos showing how plots can be built outside Jupyter notebook environment. Each such demo  has `main` method and is launched like any other simple JVM application.

The simple demos are in folders: 
```
lets-plot-kotlin/demo/jvm-javafx
lets-plot-kotlin/demo/browser
```

### Maven Artifacts

`Let-Plot` Kotlin API is published as `lets-plot-kotlin-api` JAR (only snapshot, not yet released)

[ ![Download](https://api.bintray.com/packages/jetbrains/lets-plot-maven/lets-plot-kotlin-api-jars/images/download.svg)](https://bintray.com/jetbrains/lets-plot-maven/lets-plot-kotlin-api-jars/_latestVersion)

To create plots in JVM-based application two other JARs are required: 
- `lets-plot-common`
- `lets-plot-jfx`
   
These artifacts are published by the main [Lets-Plot](https://github.com/JetBrains/lets-plot) project 
along with other artifacts as a part of `Lets-Plot` library release.  

[ ![Download](https://api.bintray.com/packages/jetbrains/lets-plot-maven/lets-plot-jars/images/download.svg)](https://bintray.com/jetbrains/lets-plot-maven/lets-plot-jars/_latestVersion)

All artifacts are published to the following Maven repository: `https://jetbrains.bintray.com/lets-plot-maven`

Additionally, `lets-plot-common` and `lets-plot-jfx` are included in [jcenter](https://bintray.com/bintray/jcenter) Maven repository (package `lets-plot-jars`).

Try an example of a **minimal JVM-base** application which creates a simple plot:
[minimalDemo/Main.kt](https://github.com/JetBrains/lets-plot-kotlin/blob/master/demo/jvm-javafx/src/main/kotlin/minimalDemo/Main.kt)


### Frontend context

`Frontend context` is required for method `show()` in `Figure` interface to work properly. Both `Plot` and `GGBunch` classes in `Lets-Plot` Kotlin API implement `Figure` interface. 
Calling method `show` is typical for interactive notebook-like applications.

To integrate `Lets-Plot` into such application:

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
val ver = .. // JS library version, for example: "1.1.dev2"
val html:String = PlotHtmlHelper.getDynamicConfigureHtml(scriptUrl(ver), false)
// include `configure html` in your document ...
```

Define `Lets-Plot` frontend context:
```
import jetbrains.letsPlot.GlobalSettings
...
GlobalSettings.frontendContext = MyNotebookContext()
```


### License

Code and documentation released under the [MIT license](https://github.com/JetBrains/lets-plot/blob/master/LICENSE).
Copyright 2019, JetBrains s.r.o.
