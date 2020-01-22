# Lets-Plot for Kotlin Developers Guide 

`Let-Plot for Kotlin` project depends on maven artifacts (`lets-plot-common`, `lets-plot-jfx`) built by the main `Lets-Plot` project, see: [Lets-Plot](https://github.com/JetBrains/lets-plot)  

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

###Frontend context

`Frontend context` is required for method `show()` in `Figure` interface to work properly. Both `Plot` and `GGBunch` classes in `lets-plot` Kotlin API implement `Figure` interface. Calling method `show` is typical for interactive notebook-like applications.

To integrate `lets-plot` into such application:

####Add dependencies on maven artifacts

```
org.jetbrains.lets-plot:lets-plot-common:1.1.2-SNAPSHOT
org.jetbrains.lets-plot:lets-plot-kotlin-api:0.0.9-SNAPSHOT
```

####Implement interface `jetbrains.letsPlot.FrontendContext` like: 
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

####Initialize `lets-plot` at runtime

Load `lets-plot` JS library to the user agent:
```
val ver = .. // JS library version, for example: "1.1.dev2"
val html:String = PlotHtmlHelper.getDynamicConfigureHtml(scriptUrl(ver), false)
// include `configure html` in your document ...
```

Define `lets-plot` frontend context:
```
import jetbrains.letsPlot.GlobalSettings
...
GlobalSettings.frontendContext = MyNotebookContext()
```


### License

Code and documentation released under the [MIT license](https://github.com/JetBrains/lets-plot/blob/master/LICENSE).
Copyright 2019, JetBrains s.r.o.
