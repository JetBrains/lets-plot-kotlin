## Using Lets-Plot Kotlin API in JVM and Kotlin/JS Applications

<a id="artifacts"></a>
### Library Artifacts

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


<a id="examples"></a>
### Code Examples

The [lets-plot-mini-apps](https://github.com/alshan/lets-plot-mini-apps) GitHub repository contains few examples of using
the Lets-Plot Kotlin API in JVM and Kotlin/JS projects:

- #### [jvm-swing-batik-app](https://github.com/alshan/lets-plot-mini-apps/blob/main/jvm-swing-batik-app/src/main/kotlin/Main.kt):
  A JVM/Swing app which uses [Apache Batik SVG Toolkit](https://xmlgraphics.apache.org/batik/) for plot rendering.

- #### [jvm-swing-javafx-app](https://github.com/alshan/lets-plot-mini-apps/blob/main/jvm-swing-javafx-app/src/main/kotlin/Main.kt):
  A JVM/Swing app which renders plot to  [JavaFX](https://en.wikipedia.org/wiki/JavaFX) scene inside `javafx.embed.swing.JFXPanel`.

- #### [jvm-javafx-app](https://github.com/alshan/lets-plot-mini-apps/tree/main/jvm-javafx-app):
  A  [JavaFX](https://en.wikipedia.org/wiki/JavaFX) app which renders plot to JavaFX scene inside `javafx.embed.swing.JFXPanel`.

  The Swing panel with a plot in it then embedded into a JavaFX application via `javafx.embed.swing.SwingNode`.

- #### [js-frontend-app](https://github.com/alshan/lets-plot-mini-apps/tree/main/js-frontend-app):
  A Kotlin/JS app (Kotlin LEGACY compiler).

- #### [js-ir-frontend-app](https://github.com/alshan/lets-plot-mini-apps/tree/main/js-ir-frontend-app):
  A Kotlin/JS IR app (Kotlin IR compiler).


<a id="dependencies"></a>
### Project Dependencies

The following is how you configure a Gradle (Groovy) project:

```groovy
repositories {
  mavenCentral()
}
```

#### JVM/Swing/Batik application

```groovy
dependencies {
    implementation "org.jetbrains.lets-plot:lets-plot-batik:3.1.0"
    implementation "org.jetbrains.lets-plot:lets-plot-kotlin-jvm:4.3.0"
}
```

#### JVM/Swing/JavaFX application

```groovy
dependencies {
  implementation "org.jetbrains.lets-plot:lets-plot-jfx:3.1.0"
  implementation "org.jetbrains.lets-plot:lets-plot-kotlin-jvm:4.3.0"
}
```

> *Note*: Depending on which JRE you are using, you might need to add JavaFX dependencies as well.

#### JVM/other:

If your JVM app doesn't use either frontend, you can provide just "lets-plot-common" dependency:

```groovy
dependencies {
  implementation "org.jetbrains.lets-plot:lets-plot-common:3.1.0"
  implementation "org.jetbrains.lets-plot:lets-plot-kotlin-jvm:4.3.0"
}
```

#### Kotlin/JS application:

```groovy
dependencies {
  implementation "org.jetbrains.lets-plot:lets-plot-kotlin-js:4.3.0"
}
```

<a id="showing-plots-jvm"></a>
### Showing Plots (JVM)
                        
1. Create a figure object using the Lets-Plot Kotlin API

```kotlin
val figure = letsPlot(data) + geomDensity(
                                    color = "dark-green",
                                    fill = "green",
                                    alpha = .3,
                                    size = 2.0) { x = "x" }
```

2. Convert the figure object to a map of plot specification parameters:

```kotlin
val rawSpec = figure.toSpec()
val processedSpec = MonolithicCommon.processRawSpecs(rawSpec, frontendOnly = false)
```
                            
3. Create the plot Swing panel using one of the provided classes: [DefaultPlotPanelBatik](https://github.com/JetBrains/lets-plot/blob/master/vis-swing-batik/src/jvmMain/kotlin/jetbrains/datalore/vis/swing/batik/DefaultPlotPanelBatik.kt)
   or  [DefaultPlotPanelJfx](https://github.com/JetBrains/lets-plot/blob/master/vis-swing-jfx/src/jvmMain/kotlin/jetbrains/datalore/vis/swing/jfx/DefaultPlotPanelJfx.kt).
         
```kotlin
val panel: JPanel = DefaultPlotPanelBatik(
    processedSpec = processedSpec,
    preserveAspectRatio = preserveAspectRadio,
    preferredSizeFromPlot = false,
    repaintDelay = 10,
) { messages ->
    for (message in messages) {
      println("[Example App] $message")
    }
}
```


<a id="showing-plots-js"></a>
### Showing Plots (Kotlin/JS)

1. Create a figure object using the Lets-Plot Kotlin API

```kotlin
val figure = letsPlot(data) + geomDensity(
                                    color = "dark-green",
                                    fill = "green",
                                    alpha = .3,
                                    size = 2.0) { x = "x" }
```

2. Convert the figure object to a map of plot specification parameters:

```kotlin
val rawSpec = figure.toSpec()
```
      
3. Generate HTML code

    a) A code which you can embed in your HTML document, providing the Lets-Plot JS library is already loaded statically via a `<script>` tag in the document's `<head>` section.  
    ```kotlin
    val html: String = PlotHtmlHelper.getStaticDisplayHtmlForRawSpec(rawSpec)
    ```                  

    b) An `iframe` code which includes everything needed to render the plot.  
    ```kotlin
    val html: String = PlotHtmlExport.buildHtmlFromRawSpecs(
                                        plotSpec = rawSpec,
                                        scriptUrl = PlotHtmlHelper.scriptUrl(version="3.1.0"),
                                        iFrame = true    
                                    )
    ```
            
4. Embed the generated HTML code in your document.
    
    This is the tricky part because you have to make sure that the JavaScript included in the generated HTML code is not just added to DOM but also is executed by the browser. 
    
    For example, see: [JsFrontendUtil.createPlotDiv(figure)](https://github.com/JetBrains/lets-plot-kotlin/blob/ba7df25c6eed4cb4f4c3806e42dc0f818f759b6f/plot-api/src/jsMain/kotlin/org/jetbrains/letsPlot/frontend/JsFrontendUtil.kt#LL16C15-L16C15).



### License

Code and documentation released under the [MIT license](https://github.com/JetBrains/lets-plot-kotlin/blob/master/LICENSE).
Copyright © 2019-2023, JetBrains s.r.o.
