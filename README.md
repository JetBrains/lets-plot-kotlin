# Lets-Plot for Kotlin [![official JetBrains project](http://jb.gg/badges/official-flat-square.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)

<table>
    <tr>
        <td>Latest Lets-Plot Kotlin API Version</td>
        <td>
            <a href="https://github.com/JetBrains/lets-plot-kotlin/releases/latest"/>
            <img src="https://img.shields.io/github/v/release/JetBrains/lets-plot-kotlin"/>
        </td>
    </tr>
    <tr>
        <td>Latest Lets-Plot Version</td>
        <td>
            <a href="https://github.com/JetBrains/lets-plot/releases/latest"/>
            <img src="https://img.shields.io/github/v/release/JetBrains/lets-plot"/>
        </td>
    </tr>
    <tr>
        <td>License</td>
        <td>
            <a href="https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/LICENSE"/>
            <img src="https://img.shields.io/badge/License-MIT-yellow.svg"/>
        </td>
    </tr>
</table>


**Lets-Plot for Kotlin** is a <a href="https://lets-plot.org/kotlin">Kotlin API</a> for the [Lets-Plot](https://github.com/JetBrains/lets-plot) library - an
open-source plotting library for statistical data,<br> which is built on the principles of layered graphics first described in the Leland Wilkinson
work [The Grammar of Graphics](https://www.goodreads.com/book/show/2549408.The_Grammar_of_Graphics).

<table>
    <tr>
        <td>
            <a href="https://ggplot2-book.org/index.html" target="_blank"> 
               <img src="https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/readme-update/docs/images/ggplot2-elegant-graphics-for-data-analysis.jpeg" 
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
## Quickstart

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

<img src="https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/readme-update/docs/images/quickstart_notebook.png" alt="Couldn't load quickstart_notebook.png" width="523" height="261"/>
<br/>

See the "Quickstart" notebook in [Datalore](https://datalore.jetbrains.com/view/notebook/aTA9lQnPkRwdCzT6uy95GZ) or
[Jupyter nbviewer](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/quickstart.ipynb).


<a name="toc" id="toc"></a>
## Table of Contents

- [Lets-Plot in Notebook](#in-notebook)
- [Lets-Plot in JVM and Kotlin/JS Application](#in-jvm-js)
- [Documentation](#documentation)

- [What is new in 4.3.0](#new)
- [Migrating from v3 to v4](#migrating4)
- [Change Log](#change_log)
- [Code of Conduct](#CoC)
- [License](#license)
                      

<a id="in-notebook"></a>
## Lets-Plot in Notebook

#### "Line Magics"

Include all the necessary Lets-Plot boilerplate code to a notebook using the following "line magic":

```
%use lets-plot
```  

This will apply the lets-plot `library descriptor` bundled with the Kotlin Jupyter Kernel installed in your environment.

The `%useLatestDescriptors` line magic will force Kotlin Kernel to pull and apply **all** the latest
`library descriptors` from the [Kotlin Jupyter Libraries](https://github.com/Kotlin/kotlin-jupyter-libraries) repository.

You can override lets-plot `library descriptor` settings using the lets-plot line magic parameters:

```
%use lets-plot(api=1.1.0, lib=1.5.4, js=1.5.4, isolatedFrame=false)
```                                                                 
- `api` - the version of Lets-Plot Kotlin API.
- `lib` - the version of Lets-Plot library (JAR-s).
- `js`  - the version of Lets-PLot JavaScript bundle.
- `isolatedFrame` - If `false`: load JS just once per notebook (default in Jupyter).
  If `true`: include Lets-Plot JS in each output (default in [Datalore](https://datalore.jetbrains.com/) notebooks).


<a id="in-jvm-js"></a>
## Lets-Plot in JVM and Kotlin/JS Application

To learn more about creating plots in JVM or Kotlin/JS environment
read [README_DEV.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/README_DEV.md). 

In the [lets-plot-mini-apps](https://github.com/alshan/lets-plot-mini-apps) GitHub repository you will find examples of
using Lets-Plot Kotlin API in JVM and Kotlin/JS projects.

<a id="documentation"></a>
## Documentation

* A quick introduction to the _Grammar of Graphics_ and Lets-Plot Kotlin API: [Lets-Plot Usage Guide](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/guide/user_guide.ipynb) 

* Lets-Plot Kotlin API docs: [docs/README.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/README.md)

* Lets-Plot Kotlin API reference: https://lets-plot.org/kotlin

* The "Example Notebooks" reference: [examples.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/examples.md)

* Example notebooks in the Binder: [mybinder.org](https://mybinder.org/v2/gh/JetBrains/lets-plot-kotlin/v4.3.0demos?filepath=docs/examples/jupyter-notebooks/)


<a id="new"></a>
## What is new in 4.3.0

- ### `gggrid()` ([docs](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot/gggrid.html)), as a replacement for earlier variant of [gggrid()](https://github.com/JetBrains/lets-plot-kotlin/blob/master/plot-api/src/commonMain/kotlin/org/jetbrains/letsPlot/gggrid_deprecated.kt).
  <br>
  <img src="https://raw.githubusercontent.com/JetBrains/lets-plot/master/docs/f-23a/images/plot_grid.png" alt="f-23a/images/plot_grid.png" width="400" height="200">

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/plot_grid.ipynb).


- ### `jointPlot()`
  <br>
  <img src="https://raw.githubusercontent.com/JetBrains/lets-plot/master/docs/f-23a/images/joint_plot.png" alt="f-23a/images/joint_plot.png" width="400" height="267">

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/joint_plot.ipynb).


- ### Configuring Axis Position
  <br>
  <img src="https://raw.githubusercontent.com/JetBrains/lets-plot/master/docs/f-23a/images/axis_position.png" alt="f-23a/images/axis_position.png" width="300" height="200">

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/axis_position.ipynb).


- ### Showing Quantiles on Density Plots
  <br>
  <img src="https://raw.githubusercontent.com/JetBrains/lets-plot/master/docs/f-23a/images/density_quantiles.png" alt="f-23a/images/density_quantiles.png" width="400" height="150">

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/quantile_parameters.ipynb).


- ### Additional "color" aesthetics: `paint_a, paint_b, paint_c`.
  <br>
  <img src="https://raw.githubusercontent.com/JetBrains/lets-plot/master/docs/f-23a/images/additional_color_aes.png" alt="f-23a/images/additional_color_aes.png" width="400" height="300">

  See [Multiple Color Scales](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/multiple_color_scales.ipynb) demo.

  Also added a set of related "color scale" functions with the "aesthetic" parameter for configuring of additional color scales.

  See [New "Scale" Functions](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/scale_functions.ipynb) demo.


- ### Rotation of Axis Tick Labels

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/axis_text_angle.ipynb).


- ### Other improvements and fixes
  See [CHANGELOG.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/CHANGELOG.md#430---2023-03-09)
  for details.


<a id="migrating4"></a>
## Migrating from v3 to v4
                                        
For migration instructions see [Migrating to 4.0.0](https://github.com/JetBrains/lets-plot-kotlin/blob/master/CHANGELOG.md#migrating-to-400) section in the CHANGELOG.


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
Copyright © 2019-2023, JetBrains s.r.o.
