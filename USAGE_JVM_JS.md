## Using Lets-Plot Kotlin API in JVM and Kotlin/JS Applications

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
        <td>Lets-Plot (JVM)</td>
        <td>
            <i>lets-plot-swing</i><br>
            <i>lets-plot-batik</i> (obsolete)<br>
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

#### JVM/Swing

```Kotlin
plugins {
    kotlin("jvm")
}

dependencies {
    // Lets-Plot Kotlin API
    implementation("org.jetbrains.lets-plot:lets-plot-kotlin-jvm:4.13.0")
    // Lets-Plot (JVM)
    implementation("org.jetbrains.lets-plot:lets-plot-swing:4.9.0")
}
```

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
                implementation("org.jetbrains.lets-plot:lets-plot-kotlin-js:4.13.0")
            }
        }
    }
}
```

### Examples

See [Lets-Plot Kotlin Mini Apps (Demos)](https://github.com/alshan/lets-plot-mini-apps) GitHub repository:

- [JVM/Swing app examples](https://github.com/alshan/lets-plot-mini-apps/blob/main/jvm-swing-app)
- [Kotlin/JS app example](https://github.com/alshan/lets-plot-mini-apps/tree/main/js-frontend-app)
- [JVM plot export examples](https://github.com/alshan/lets-plot-mini-apps/tree/main/jvm-plot-export/src/main/kotlin):
  Runnable examples that show how to export plot to an SVG, HTML or PNG image using  
  `PlotSvgExport`, `PlotHtmlExport` or `PlotImageExport` utilities.


### Showing Plots (JVM/Swing)

1. Create a figure object using the Lets-Plot Kotlin API:

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

3. Create a plot Swing panel: [SwingPlotPanel](https://github.com/JetBrains/lets-plot/blob/master/platf-awt/src/main/kotlin/org/jetbrains/letsPlot/awt/plot/swing/SwingPlotPanel.kt):

```kotlin
val panel: JPanel = SwingPlotPanel(
    processedSpec = processedSpec,
    preserveAspectRatio = preserveAspectRatio,
    preferredSizeFromPlot = false,
    repaintDelay = 10,
) { messages ->
    for (message in messages) {
      println("[Example App] $message")
    }
}
```

### Showing Plots (Kotlin/JS)

1. Create a figure object using the Lets-Plot Kotlin API:

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

3. Generate HTML code:

    a) A code which you can embed in your HTML document, providing the Lets-Plot JS library is already loaded statically via a `<script>` tag in the document's `<head>` section.  
    ```kotlin
    val html: String = PlotHtmlHelper.getStaticDisplayHtmlForRawSpec(rawSpec)
    ```

    b) An `iframe` code which includes everything needed to render the plot.  
    ```kotlin
    val html: String = PlotHtmlExport.buildHtmlFromRawSpecs(
                                        plotSpec = rawSpec,
                                        scriptUrl = PlotHtmlHelper.scriptUrl(version="4.9.0"),
                                        iFrame = true
                                    )
    ```

4. Embed the generated HTML code in your document:

    This is the tricky part because you have to make sure that the JavaScript included in the generated HTML code is not just added to DOM but also is executed by the browser.


    For example, see: [JsFrontendUtil.createPlotDiv(figure)](https://github.com/JetBrains/lets-plot-kotlin/blob/fa20437a8d73381af2a1ecc80084c61640586385/plot-api/src/jsMain/kotlin/org/jetbrains/letsPlot/frontend/JsFrontendUtil.kt#L16).


> *Note*: In case you are using Android WebView to display the plot:
>   - enable JavaScript: `webView.settings.javaScriptEnabled = true`
>   - grant the internet access permission (AndroidManifest.xml): `<uses-permission android:name="android.permission.INTERNET" />`



### License

Code and documentation released under the [MIT license](https://github.com/JetBrains/lets-plot-kotlin/blob/master/LICENSE).
Copyright © 2019-2026, JetBrains s.r.o.
