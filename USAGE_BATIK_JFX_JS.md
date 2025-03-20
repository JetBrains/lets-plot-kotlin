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
        <td>Lets-Plot Multiplatform</td>
        <td>
            <i>lets-plot-batik</i><br>
            <i>lets-plot-jfx</i><br>
        </td>
        <td>
            <a href="https://search.maven.org/search?q=lets-plot"/>
            <img src="https://img.shields.io/maven-central/v/org.jetbrains.lets-plot/lets-plot-common?color=blue&label=Maven%20Central"/>
        </td>
        <td>
            Published by the <a href="https://github.com/JetBrains/lets-plot">Lets-Plot</a> project.
        </td>
    </tr>
</table>

<a id="dependencies"></a>
### Using as Dependency

The following is how you configure a Gradle project with Kotlin DSL.

<a id="deps-swing-batik"></a>
#### JVM-Swing-Batik

```Kotlin
plugins {
    kotlin("jvm")
}

dependencies {
    // Lets-Plot Kotlin API 
    implementation("org.jetbrains.lets-plot:lets-plot-kotlin-jvm:4.10.0")
    // Lets-Plot Multiplatform (Batik rendering)
    implementation("org.jetbrains.lets-plot:lets-plot-batik:4.6.1")
}
```

<a id="deps-jfx"></a>
#### JVM-JavaFX (+Swing)
In this configuration, the plot is always rendered to [JavaFX](https://en.wikipedia.org/wiki/JavaFX) `scene` inside a `JFXPanel`, \
but then there are two options to embed the plot into your application:
- In a Java Swing application you can embed the plot's `JFXPanel` directly into a Swing container.
- In a JavaFX application you can embed the plot's `JFXPanel` into a JavaFX `SwingNode` and then embed the `SwingNode` into a JavaFX container.
                                                                                              
The dependencies are the same in both cases.
```Kotlin
plugins {
    kotlin("jvm")
}

    ...

dependencies {
    // Lets-Plot Kotlin API 
    implementation("org.jetbrains.lets-plot:lets-plot-kotlin-jvm:4.10.0")
    // Lets-Plot Multiplatform (JFX Scene rendering)
    implementation("org.jetbrains.lets-plot:lets-plot-jfx:4.6.1")
}
```

<a id="deps-kotlin-js"></a>
#### Kotlin/JS

```Kotlin
plugins {
    kotlin("multiplatform")
}

kotlin {
    ...
    sourceSets {
        named("jsMain") {
            dependencies {
                // Lets-Plot Kotlin API 
                implementation("org.jetbrains.lets-plot:lets-plot-kotlin-js:4.10.0")
            }
        }
    }
}
```


<a id="examples"></a>
### Examples

See [Lets-Plot Kotlin Mini Apps (Demos)](https://github.com/alshan/lets-plot-mini-apps) GitHub repository:

- #### [jvm-swing-batik-app](https://github.com/alshan/lets-plot-mini-apps/blob/main/jvm-swing-batik-app/src/main/kotlin/Main.kt):
  A JVM/Swing app which uses [Apache Batik SVG Toolkit](https://xmlgraphics.apache.org/batik/) for plot rendering.

- #### [jvm-swing-javafx-app](https://github.com/alshan/lets-plot-mini-apps/blob/main/jvm-swing-javafx-app/src/main/kotlin/Main.kt):
  A JVM/Swing app which renders plot to  [JavaFX](https://en.wikipedia.org/wiki/JavaFX) scene inside a `javafx.embed.swing.JFXPanel`.

- #### [jvm-javafx-app](https://github.com/alshan/lets-plot-mini-apps/tree/main/jvm-javafx-app):
  A [JavaFX](https://en.wikipedia.org/wiki/JavaFX) app which renders plot to JavaFX scene inside a `javafx.embed.swing.JFXPanel`, \
  then embeds it into a JavaFX application using a `javafx.embed.swing.SwingNode`.

- #### [js-frontend-app](https://github.com/alshan/lets-plot-mini-apps/tree/main/js-frontend-app):
  A Kotlin/JS app.

- #### [jvm-plot-export](https://github.com/alshan/lets-plot-mini-apps/tree/main/jvm-plot-export/src/main/kotlin):
  Runnable examples that show how to export plot to an SVG, HTML or PNG image using  
  `PlotSvgExport`, `PlotHtmlExport` or `PlotImageExport` utilities.


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
                                        scriptUrl = PlotHtmlHelper.scriptUrl(version="4.6.1"),
                                        iFrame = true    
                                    )
    ```
            
4. Embed the generated HTML code in your document.
    
    This is the tricky part because you have to make sure that the JavaScript included in the generated HTML code is not just added to DOM but also is executed by the browser. 
    
    For example, see: [JsFrontendUtil.createPlotDiv(figure)](https://github.com/JetBrains/lets-plot-kotlin/blob/ba7df25c6eed4cb4f4c3806e42dc0f818f759b6f/plot-api/src/jsMain/kotlin/org/jetbrains/letsPlot/frontend/JsFrontendUtil.kt#LL16C15-L16C15).


> *Note*: In case you are using Android WebView to display the plot:
>   - enable JavaScript: `webView.settings.javaScriptEnabled = true` 
>   - grant the internet access permission (AndroidManifest.xml): `<uses-permission android:name="android.permission.INTERNET" />`



### License

Code and documentation released under the [MIT license](https://github.com/JetBrains/lets-plot-kotlin/blob/master/LICENSE).
Copyright © 2019-2025, JetBrains s.r.o.
