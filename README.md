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
- [What is new in 4.12.0](#new)
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
%use lets-plot(v=4.12.0, isolatedFrame=false, output="js, ktnb, svg")
```                                                                 
- `v` - version of the Lets-Plot Kotlin API.
- `isolatedFrame` - If `false`: load JS just once per notebook (default in Jupyter).  
  If `true`: include Lets-Plot JS in each output (default in [Datalore](https://datalore.jetbrains.com/) notebooks).
- `output` - comma-separated list of output types to store in notebook cells (default: `"js, ktnb, svg"`). \  
  Available types:
    - `js` - Classic Web output: HTML+JS
    - `ktnb` - Kotlin Notebook Swing-based rendering
    - `svg` - Static SVG output
    - `png` - Static PNG output

  **Note:** Static images (SVG/PNG) are hidden when `js` or `ktnb` outputs are present, and only displayed in environments where JavaScript is not executed (e.g., GitHub).

  This option can be helpful when file size becomes a problem. For example, storing only static output (SVG or PNG) can significantly reduce file size when working with large datasets where plot interactivity is not a priority.

<a id="in-compose-multiplatform"></a>
### Compose Multiplatform
To learn how to embed Lets-Plot charts in [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform) applications, please check out the [Lets-Plot Compose Frontend](https://github.com/JetBrains/lets-plot-compose) project at GitHub.
   

<a id="in-jvm-js"></a>
### JVM and Kotlin/JS

To learn more about creating plots in JVM or Kotlin/JS environment, please read [USAGE_SWING_JFX_JS.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/USAGE_BATIK_JFX_JS.md). 
        
#### Examples
Examples of using the Lets-Plot Kotlin API in JVM and Kotlin/JS applications are available in the [Lets-Plot Kotlin Mini Apps (Demos)](https://github.com/alshan/lets-plot-mini-apps) GitHub repository.

<a id="documentation"></a>
## Documentation

* _Lets-Plot Kotlin API_ documentation and API reference: [**Lets-Plot for Kotlin**](https://lets-plot.org/kotlin)

* A quick introduction to the _Grammar of Graphics_ and _Lets-Plot Kotlin API_: [Lets-Plot Usage Guide](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/guide/user_guide.ipynb) 


<a id="new"></a>
## What is new in 4.12.0

- #### `geomPointDensity()` Geometry

  <img src="https://raw.githubusercontent.com/JetBrains/lets-plot/master/docs/f-25e/images/geom_pointdensity.png" alt="f-25e/images/geom_pointdensity.png" width="400" height="246">

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/geom_pointdensity.html).

- #### Explicit `group` aesthetic now overrides default grouping behavior instead of combining with it

> [!IMPORTANT]
> **BREAKING CHANGE:**
>
> Previously, setting `group='variable'` would group by both the explicit variable AND any discrete
> aesthetics (color, shape, etc.). \
> Now it groups ONLY by the explicit variable, matching `ggplot2` behavior. \
> Use `group=[var1, var2, ...]` to group by multiple variables explicitly, \
> and `group=[]` to disable any grouping.

  <img src="https://raw.githubusercontent.com/JetBrains/lets-plot/master/docs/f-25e/images/group_override_defaults.png" alt="f-25e/images/group_override_defaults.png" width="400" height="263">

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/group_override_defaults.html).

- #### `gggrid()`: support for shared legends (parameter `guides`)

  <img src="https://raw.githubusercontent.com/JetBrains/lets-plot/master/docs/f-25e/images/gggrid_legend_collect.png" alt="f-25e/images/group_override_defaults.png" width="500" height="172">

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/gggrid_legend_collect.html).

- #### Better handling of missing values in `geomLine(), geomPath(), geomRibbon()`, and `geomArea()`

  <img src="https://raw.githubusercontent.com/JetBrains/lets-plot/master/docs/f-25e/images/missing_values_ribbon.png" alt="f-25e/images/missing_values_ribbon.png" width="500" height="192">

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/missing_values_line_path_area_ribbon.html).

- #### `geomHistogram()`: custom bin bounds (parameter `breaks`)

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/geom_histogram_param_breaks.html).

- #### Legend automatically wraps to prevent overlap — up to 15 rows for vertical legends and 5 columns for horizontal ones

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/legend_wrap.html).

- #### `flavorStandard()` resets the theme's default color scheme
  Use to override other flavors or make defaults explicit.

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/flavor_standard.html).

- #### `theme` methods controlling legend justification: `legendJustificationTop()`, `legendJustificationRight()`, `legendJustificationBottom()`, and `legendJustificationLeft()`

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/legend_justification.html).

- #### `ggtb()`: Added `sizeZoomin` and `sizeBasis` parameters to control point size scaling behavior when zooming (works with `geomPoint` and related layers).

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/ggtb_size_zoomin.html).


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
