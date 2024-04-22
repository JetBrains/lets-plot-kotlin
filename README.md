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
[Jupyter nbviewer](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/quickstart.ipynb).


<a name="toc" id="toc"></a>
## Table of Contents
             
- [Usage](#usage)
  - [Notebooks](#in-notebook)
  - [Compose Multiplatform](#in-compose-multiplatform)
  - [JVM and Kotlin/JS](#in-jvm-js)
- [Documentation](#documentation)
- [What is new in 4.7.0](#new)
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
%use lets-plot(api=4.7.1, lib=4.3.1, js=4.3.1, isolatedFrame=false)
```                                                                 
- `api` - version of the Lets-Plot Kotlin API.
- `lib` - version of the Lets-Plot Multiplatform (JARs).
- `js`  - version of the Lets-PLot Multiplatform JavaScript bundle.
- `isolatedFrame` - If `false`: load JS just once per notebook (default in Jupyter).
  If `true`: include Lets-Plot JS in each output (default in [Datalore](https://datalore.jetbrains.com/) notebooks).


<a id="in-compose-multiplatform"></a>
### Compose Multiplatform
To learn how to embed Lets-Plot charts in [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform) applications, please check out the [Lets-Plot Skia Frontend](https://github.com/JetBrains/lets-plot-skia) project.
   

<a id="in-jvm-js"></a>
### JVM and Kotlin/JS

To learn more about creating plots in JVM or Kotlin/JS environment please read [USAGE_SWING_JFX_JS.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/USAGE_SWING_JFX_JS.md). 
        
#### Examples
Examples of using of the Lets-Plot Kotlin API in JVM and Kotlin/JS applications are available in the [Lets-Plot Kotlin Mini Apps (Demos)](https://github.com/alshan/lets-plot-mini-apps) GitHub repository.

<a id="documentation"></a>
## Documentation

* A quick introduction to the _Grammar of Graphics_ and Lets-Plot Kotlin API: [Lets-Plot Usage Guide](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/guide/user_guide.ipynb) 

* Lets-Plot Kotlin API docs: [docs/README.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/README.md)

* Lets-Plot Kotlin API reference: https://lets-plot.org/kotlin

* The "Example Notebooks" reference: [examples.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/examples.md)

* Example notebooks in the Binder: [mybinder.org](https://mybinder.org/v2/gh/JetBrains/lets-plot-kotlin/HEAD?labpath=docs%2Fexamples%2Fjupyter-notebooks)


<a id="new"></a>
## What is new in 4.7.0

- #### `coordPolar()`

  The polar coordinate system is most commonly used for pie charts, but</br>
  it can also be used for constructing **Spider or Radar charts** using the `flat` option.

    <br>
    <img src="https://raw.githubusercontent.com/JetBrains/lets-plot/master/docs/f-24a/images/polar_coord_pie.png" alt="f-24a/images/polar_coord_pie.png" width="256" height="214">
    <img src="https://raw.githubusercontent.com/JetBrains/lets-plot/master/docs/f-24a/images/radar_chart.png" alt="f-24a/images/radar_chart.png" width="256" height="196">

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.7.0/coord_polar.ipynb).

- #### In the `theme()`:

  - `panelInset`  parameter - primarily used for plots with polar coordinates.
    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.7.0/theme_panel_inset.ipynb).

  - `panelBorderOntop` parameter - enables the drawing of panel border on top of the plot geoms.
  - `panelGridOntop, panelGridOntopX, panelGridOntopY` parameters - enable the drawing of grid lines on top of the plot geoms.

- #### `geomCurve()`

    <br>
    <img src="https://raw.githubusercontent.com/JetBrains/lets-plot/master/docs/f-24a/images/curve_annotation.png" alt="f-24a/images/curve_annotation.png" width="338" height="296">

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.7.0/geom_curve.ipynb).

- #### [**UNIQUE**] Visualizing Graph-like Data with `geomSegment()` and `geomCurve()`

  - Aesthetics `sizeStart, sizeEnd, strokeStart` and `strokeEnd` enable better alignment of</br>
    segments/curves with nodes of the graph by considering the size of the nodes.

  - The `spacer` parameter allows for additional manual fine-tuning.

    <br>
    <img src="https://raw.githubusercontent.com/JetBrains/lets-plot/master/docs/f-24a/images/graph_simple.png" alt="f-24a/images/graph_simple.png" width="384" height="252">

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.7.0/graph_edges.ipynb).

- #### The `alphaStroke` Parameter in `geomLabel()`

  Use the `alphaStroke` parameter to apply `alpha` to entire `label`. By default, `alpha` is only applied to the label background.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.7.0/geom_label_alpha_stroke.ipynb).


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
Copyright © 2019-2024, JetBrains s.r.o.
