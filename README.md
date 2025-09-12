# Lets-Plot Kotlin API
A **Grammar of Graphics** for Kotlin.

[![official JetBrains project](http://jb.gg/badges/official-flat-square.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)
[![License MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/LICENSE)
[![Latest Release](https://img.shields.io/github/v/release/JetBrains/lets-plot-kotlin)](https://github.com/JetBrains/lets-plot-kotlin/releases/latest)

**Lets-Plot Kotlin API** is a <a href="https://lets-plot.org/kotlin">Kotlin API</a> for [Lets-Plot Multiplatform](https://github.com/JetBrains/lets-plot) 
 plotting library, \
which is built on the principles of layered graphics first described in the \
Leland Wilkinson work [The Grammar of Graphics](https://www.goodreads.com/book/show/2549408.The_Grammar_of_Graphics).

<table>
    <tr>
        <td>
            <a href="https://ggplot2-book.org/index.html" target="_blank"> 
               <img src="https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/images/ggplot2-elegant-graphics-for-data-analysis.jpeg" 
                    width="150" height="228" alt="book cover">
            </a>
        </td>
        <td>
            <p>Lets-Plot <a href="https://lets-plot.org/kotlin">Kotlin API</a> is largely based on the API<br>provided by 
            <a href="https://ggplot2.tidyverse.org/">ggplot2</a> package well-known to data scientists who use R.</p>
            <p>To learn more about the <i>Grammar of Graphics</i>,<br>we recommend an excellent book called<br> 
            <a href="https://ggplot2-book.org/index.html" target="_blank">“ggplot2: Elegant Graphics for Data Analysis”</a>.</p> 
            <p>This will be a good prerequisite for further exploration of the Lets-Plot library.</p>
        </td>  
    </tr>
</table>


<a id="quickstart"></a>
### Quickstart

Inside [Kotlin Notebook](https://plugins.jetbrains.com/plugin/16340-kotlin-notebook),
[Datalore](https://datalore.jetbrains.com/) or
[Jupyter with Kotlin Kernel](https://github.com/Kotlin/kotlin-jupyter#readme):

```
%use lets-plot
```     

```kotlin
val rand = java.util.Random()
val data = mapOf(
    "rating" to List(200) { rand.nextGaussian() } + List(200) { rand.nextGaussian() * 1.5 + 1.5 },
    "cond" to List(200) { "A" } + List(200) { "B" }
)

var p = letsPlot(data)
p += geomDensity(color = "dark_green", alpha = .3) { x = "rating"; fill = "cond" }
p + ggsize(700, 350)
```

<img src="https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/images/quickstart_notebook.png" alt="Couldn't load quickstart_notebook.png" width="523" height="261"/>
<br/>

See the "Quickstart" notebook in [Datalore](https://datalore.jetbrains.com/view/notebook/aTA9lQnPkRwdCzT6uy95GZ) or
[Jupyter nbviewer](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/quickstart.ipynb).


<a name="toc" id="toc"></a>
## Table of Contents
             
- [Usage](#usage)
  - [Notebooks](#in-notebook)
  - [Compose Multiplatform](#in-compose-multiplatform)
  - [JVM and Kotlin/JS](#in-jvm-js)
- [Documentation](#documentation)
- [What is new in 4.11.0](#new)
- [Recent Updates in the Gallery](#recent_gallery_updates)
- [Change Log](#change_log)
- [Code of Conduct](#CoC)
- [License](#license)
                          


<a id="usage"></a>
## Usage

<a id="in-notebook"></a>
### Notebooks

With the help of Lets-Plot Kotlin API you can easily create plots in [Kotlin Notebook](https://plugins.jetbrains.com/plugin/16340-kotlin-notebook),
[Datalore](https://datalore.jetbrains.com/), [Jupyter with Kotlin Kernel](https://github.com/Kotlin/kotlin-jupyter#readme) \
or any other notebook that supports `Kotlin Kernel`.


#### "Line Magics"

```
%use lets-plot
```  
This "line magic" will apply **Lets-Plot library descriptor** which adds to your notebook all the boilerplate code necessary to create plots.

By default, `library descriptor` is bundled with the Kotlin Jupyter Kernel installed in your environment. \
However, you can override the default settings using:
```
%useLatestDescriptors
```
In this case the latest `library descriptor` will be pulled from the [Kotlin Jupyter Libraries](https://github.com/Kotlin/kotlin-jupyter-libraries) repository.

#### Library Descriptor Parameters

```
%use lets-plot(v=4.11.2, isolatedFrame=false)
```                                                                 
- `v` - version of the Lets-Plot Kotlin API.
- `isolatedFrame` - If `false`: load JS just once per notebook (default in Jupyter).
  If `true`: include Lets-Plot JS in each output (default in [Datalore](https://datalore.jetbrains.com/) notebooks).


<a id="in-compose-multiplatform"></a>
### Compose Multiplatform
To learn how to embed Lets-Plot charts in [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform) applications, please check out the [Lets-Plot Skia Frontend](https://github.com/JetBrains/lets-plot-skia) project.
   

<a id="in-jvm-js"></a>
### JVM and Kotlin/JS

To learn more about creating plots in JVM or Kotlin/JS environment please read [USAGE_SWING_JFX_JS.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/USAGE_BATIK_JFX_JS.md). 
        
#### Examples
Examples of using of the Lets-Plot Kotlin API in JVM and Kotlin/JS applications are available in the [Lets-Plot Kotlin Mini Apps (Demos)](https://github.com/alshan/lets-plot-mini-apps) GitHub repository.

<a id="documentation"></a>
## Documentation

* _Lets-Plot Kotlin API_ documentation and API reference: [**Lets-Plot for Kotlin**](https://lets-plot.org/kotlin)

* A quick introduction to the _Grammar of Graphics_ and _Lets-Plot Kotlin API_: [Lets-Plot Usage Guide](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/guide/user_guide.ipynb) 


<a id="new"></a>
## What is new in 4.11.0

- #### Time Series Plotting
  - Support temporal data types from `kotlinx.datetime`, `java.time`, and `java.util`.
  - Support for timezone-aware `java.time.ZonedDateTime` and `java.time.OffsetDateTime` objects.

  <img src="https://raw.githubusercontent.com/JetBrains/lets-plot/master/docs/f-25b/images/time_date_datetime.png" alt="f-25b/images/time_date_datetime.png" width="400" height="237">
  
  See [Date-time](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/time_date_datetime.ipynb) cookbook.
  <br><br>
  <img src="https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/images/bitcoin_trading.png" alt="f-4.11.0/images/bitcoin_trading.png" width="400" height="237">
  
  See [Bitcoin trading](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/demo/trading_chart.ipynb) demo.
  <br><br>

- #### `geomSina()` Geometry

  <img src="https://raw.githubusercontent.com/JetBrains/lets-plot/master/docs/f-25b/images/geom_sina.png" alt="f-25b/images/geom_sina.png" width="400" height="276">

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/geom_sina.ipynb).

- #### `geomTextRepel()` and `geomLabelRepel()` Geometries

  <img src="https://raw.githubusercontent.com/JetBrains/lets-plot/master/docs/f-25b/images/geom_repel.png" alt="f-25b/images/geom_repel.png" width="400" height="232">

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/ggrepel.ipynb).

- #### `waterfallPlot()` Chart

  - Annotations support via `relativeLabels` and `absoluteLabels` parameters.
    <br><br>
    <img src="https://raw.githubusercontent.com/JetBrains/lets-plot/master/docs/f-25b/images/waterfall_plot_annotations.png" alt="f-25b/images/waterfall_plot_annotations.png" width="400" height="253">

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/waterfall_plot_annotations.ipynb).
    <br><br>
  - Support for combining waterfall bars with other geometry layers.
    <br><br>
    <img src="https://raw.githubusercontent.com/JetBrains/lets-plot/master/docs/f-25b/images/waterfall_plot_layers.png" alt="f-25b/images/waterfall_plot_layers.png" width="400" height="227">

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/waterfall_plot_layers.ipynb).
    
- #### Continuous Data on Discrete Scales

  Continuous data when used with discrete positional scales is no longer transformed to discrete data. <br>
  Instead, it remains continuous, allowing for precise positioning of continuous elements relative to discrete ones.
  <br><br>
  <img src="https://raw.githubusercontent.com/JetBrains/lets-plot/master/docs/f-25b/images/combo_discrete_continuous.png" alt="f-25b/images/combo_discrete_continuous.png" width="400" height="151">

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/numeric_data_on_discrete_scale.ipynb).

> [!TIP]
> New way of handling continuous data on discrete scales could potentially break existing plots.
> If you want to restore a broken plot to its original form, you can use the [`asDiscrete()`](https://lets-plot.org/kotlin/as-discrete.html) function to annotate continuous data as discrete.


- #### Plot Layout
  The default plot layout has been improved to better accommodate axis labels and titles. <br>
  Also, new `theme()` options `axisTextSpacing`, `axisTextSpacingX`, and `axisTextSpacingY` control spacing between axis ticks and labels.
  <br><br>
  <img src="https://raw.githubusercontent.com/JetBrains/lets-plot/master/docs/f-25b/images/plot_layout_diagram.png" alt="f-25b/images/plot_layout_diagram.png" width="400" height="175">

  See new [Plot Layout Diagrams](https://lets-plot.org/kotlin/presentation-options.html#plot-layout-diagrams) showing various layout options and their effects on plot appearance.


- #### And More

  See [CHANGELOG.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/CHANGELOG.md) for a full list of changes.


<a id="recent_gallery_updates"></a>
## Recent Updates in the [Gallery](https://lets-plot.org/kotlin/gallery.html)

  <a href="https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/demo/raincloud.ipynb">
    <img src="https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/Writerside/images/previews/square-raincloud.png" alt="Raincloud plot" width="128" height="128">
  </a>
  <a href="https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/demo/europe_capitals.ipynb">
    <img src="https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/Writerside/images/previews/square-europe_capitals.png" alt="Lets-Plot GeoTools with texts and labels" width="128" height="128">
  </a>
  <a href="https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/demo/trading_chart.ipynb">
    <img src="https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/Writerside/images/previews/square-trading_chart.png" alt="Bitcoin daily trend visualization" width="128" height="128">
  </a>
  <a href="https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/demo/magnifier_inset.ipynb">
    <img src="https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/Writerside/images/previews/square-magnifier_inset.png" alt="Creating magnifier inset effect with ggbunch()" width="128" height="128">
  </a>
  <a href="https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/demo/theme_legend_scheme.ipynb">
    <img src="https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/Writerside/images/previews/square-theme_legend_scheme.png" alt="Customize legend appearance" width="128" height="128">
  </a>
  <a href="https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/demo/interact_pan_zoom.ipynb">
    <img src="https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/Writerside/images/previews/square-interact_pan_zoom.png" alt="Zoom and Pan interactivity" width="128" height="128">
  </a>
  <a href="https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/demo/lp_verse.ipynb">
    <img src="https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/Writerside/images/previews/square-lp_verse.png" alt="The observable LP-verse" width="128" height="128">
  </a>
  <a href="https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/demo/sunshine_hours.ipynb">
    <img src="https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/Writerside/images/previews/square-sunshine_hours.png" alt="Sunshine hours" width="128" height="128">
  </a>


<a id="change_log"></a>
## Change Log

See [CHANGELOG.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/CHANGELOG.md).


<a id="CoC"></a>
## Code of Conduct

This project and the corresponding community are governed by the 
[JetBrains Open Source and Community Code of Conduct](https://confluence.jetbrains.com/display/ALL/JetBrains+Open+Source+and+Community+Code+of+Conduct). 
Please make sure you read it.

<a id="license"></a>
## License

Code and documentation released under
the [MIT license](https://github.com/JetBrains/lets-plot-kotlin/blob/master/LICENSE).
Copyright © 2019-2025, JetBrains s.r.o.
