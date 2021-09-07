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
            <a href="https://opensource.org/licenses/MIT"/>
            <img src="https://img.shields.io/badge/License-MIT-yellow.svg"/>
        </td>
    </tr>
</table>


- [Overview](#overview)
- [Lets-Plot in Jupyter with Kotlin Kernel](#jupyter)
    - [Installation](#inst)
    - ["Line Magics"](#line-magics)
    - [Quick start with Jupyter](#start)
    - [Example notebooks](#jupyter-examples)
    - [Resources](#resources)

- [Lets-Plot-Kotlin in Datalore notebooks](#datalore)

- [Lets-Plot in JVM and Kotlin/JS application](#jvm)

- [Lets-Plot Kotlin API](#api)
  - [User guide and API reference](#guide)
  - [Data sampling](#sampling)
  - [GGBunch](#ggbunch)
  - [Saving plot to file](#export)
  - [Tooltip customization](#tooltip-customization)
  - [GeoTools support](#geotools)

- [What is new in 3.0.2](#new)
- [Change log](#change_log)
- [License](#license)    


<a name="Overview" id="overview"></a>
### Overview

**Lets-Plot for Kotlin** is a Kotlin API for the [Lets-Plot](https://github.com/JetBrains/lets-plot) library - an
open-source plotting library for statistical data which is written entirely in
the [Kotlin programming language](https://kotlinlang.org/).

Lets-Plot Kotlin API is built on the principles of layered graphics first described in the Leland Wilkinson
work [The Grammar of Graphics](https://www.goodreads.com/book/show/2549408.The_Grammar_of_Graphics)
and later implemented in the [ggplot2](https://ggplot2.tidyverse.org/) package for R.

> This grammar [...] is made up of a set of independent components that can be composed in many different ways. This makes [it] very powerful because you are not limited to a set of pre-specified graphics, but you can create new graphics that are precisely tailored for your problem.
> - Hadley Wickham, "ggplot2: [Elegant Graphics for Data Analysis](https://www.goodreads.com/book/show/6829192-ggplot2)"

Read [Lets-Plot Usage Guide](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/guide/user_guide.ipynb)
for quick introduction to the _Grammar of Graphics_ and _Lets-Plot Kotlin API_. 

<a id="jupyter"></a>
### Lets-Plot in Jupyter with Kotlin Kernel

<a id="inst"></a>
#### Installation

In Jupyter notebook with a Kotlin Kernel, Lets-Plot library is available out-of-the-box. To install Kotlin Kernel and OpenJDK into a Conda environment, run the following command:

```shell script
conda install kotlin-jupyter-kernel -c jetbrains
```                                             

For more information about Jupyter Kotlin kernel, see the [Kotlin kernel for Jupyter/iPython](https://github.com/Kotlin/kotlin-jupyter) project.

<a id="line-magics"></a>
#### "Line Magics" 

You can include all the necessary Lets-Plot boilerplate code to a notebook using the following "line magic":
```
%use lets-plot
```  
This will apply the lets-plot `library descriptor` bundled with the Kotlin Jupyter Kernel installed in your environment.

The `%useLatestDescriptors` line magic will force Kotlin Kernel to pull and apply the latest 
repository version of all `library descriptors`.

You can override lets-plot `library descriptor` settings using the lets-plot line magic parameters, like:
```
%use lets-plot(api=1.1.0, lib=1.5.4, js=1.5.4, isolatedFrame=false)
```                                                                 
Where: 
- `api` - version of Lets-Plot Kotlin API.
- `lib` - version of Lets-Plot library (JAR-s).
- `js`  - version of Lets-PLot JavaScript bundle.
- `isolatedFrame` - If `false`: load JS just once per notebook (default in Jupyter).
                    If `true`: include Lets-Plot JS in each output (default in [Datalore notebooks](#datalore))    
                    
See: [Line Magics](https://github.com/Kotlin/kotlin-jupyter#line-magics) documentation in the Kotlin Jupyter project for more details.                    


<a id="start"></a>
#### Quickstart in Jupyter

- In [Jupyter](https://jupyter-notebook.readthedocs.io/en/stable/index.html), create a new notebook and choose the Kotlin kernel (see the [instructions](https://jupyter-notebook.readthedocs.io/en/stable/notebook.html?highlight=new#creating-a-new-notebook-document) for more details on how to select a kernel).

- Add the following code to a Jupyter notebook:
```
%use lets-plot
```     

```
val rand = java.util.Random()
val data = mapOf<String, Any>(
    "rating" to List(200) { rand.nextGaussian() } + List(200) { rand.nextGaussian() * 1.5 + 1.5 },
    "cond" to List(200) { "A" } + List(200) { "B" }
)

var p = lets_plot(data)
p += geom_density(color="dark_green", alpha=.3) {x="rating"; fill="cond"}
p + ggsize(500, 250)
```
- Execute the added code to evaluate the plotting capabilities of Lets-Plot.

<img src="https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/images/quickstart.png" alt="Couldn't load quickstart.png" width="500" height="270"/>
<br/>
<a href="https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/quickstart.ipynb" 
   target="_parent"> 
   <img src="https://raw.githubusercontent.com/jupyter/design/master/logos/Badges/nbviewer_badge.png" 
        width="109" height="20">
</a>
<br/>

<a id="jupyter-examples"></a>
#### Example of notebooks

Try the following [examples](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/examples.md) to study features of the `Lets-Plot` library.

<a id="resources"></a>
#### Resources

* [Kotlin for Data Science](https://kotlinlang.org/docs/reference/data-science-overview.html) : overview of Kotlin Jupyter kernel etc.
* [Using Kotlin for Data Science](https://www.youtube.com/watch?v=APnyDVye4JA&list=PLQ176FUIyIUY6SKGl3Cj9yeYibBuRr3Hl&index=39&t=0s) : recording of the talk at KotlinConf-19. 

<a id="datalore"></a>
### Lets-Plot-Kotlin in Datalore notebooks

[Datalore](https://datalore.jetbrains.com/) is an online data science notebook by JetBrains.

In Datalore notebook you can run Kotlin code directly in your browser. Many popular Kotlin libraries are preinstalled and readily available
(see the list of [supported Kotlin libraries](https://github.com/Kotlin/kotlin-jupyter#supported-libraries)).

See [Quickstart in Datalore](https://view.datalore.io/notebook/Ybcyrh7ifkvTQVxbTMxaTp) example notebook to learn more
about Kotlin support in Datalore.

Watch the [Datalore Getting Started Tutorial](https://youtu.be/MjvFQxqNSe0) video for a quick introduction to Datalore.

<a id="jvm"></a>

### Lets-Plot in JVM and Kotlin/JS application

Apart from Jupyter notebooks, Lets-Plot library and Kotlin API enables embedding plots into a JVM and a Kotlin/JS
application.

See [README_DEV.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/README_DEV.md) to learn more about
creating plots in JVM or Kotlin/JS environment.

In the [lets-plot-mini-apps](https://github.com/alshan/lets-plot-mini-apps) GitHub repository you will find examples of
using Lets-Plot Kotlin API in JVM and Kotlin/JS projects.

<a id="api"></a>

### Lets-Plot Kotlin API

<a id="guide"></a>

#### User guide and API reference

- The User Guide in the form of Jupyter
  notebook: [user_guide.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/guide/user_guide.ipynb)

- Lets-Plot Kotlin
  API [reference](https://htmlpreview.github.io/?https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/api-reference/index.html)
  .

<a id="sampling"></a>
#### Data sampling 

Sampling is a special technique of data transformation, which helps to deal with large datasets and overplotting.

Learn more: [Data Sampling](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/sampling.md). 

<a id="ggbunch"></a>
#### GGBunch

GGBunch allows to show a collection of plots on one figure. Each plot in the collection can have arbitrary location and size. There is no automatic layout inside the bunch.

Examples:

* [ggbunch.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/ggbunch.ipynb) 
* [geom_smooth.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_smooth.ipynb) 


<a id="export"></a>
#### Saving plot to a file

The `ggsave()` function is a convenient way of saving a plot or a GGBunch object to a file.

The supported export formats are: `SVG, HTML, PNG, JPEG and TIFF`.

For example, the code below will save plot as a PNG image to the file `<user dir>//lets-plot-images/density.png`:

```
%use lets-plot

val rand = java.util.Random(123)
val n = 400
val data = mapOf (
    "rating" to List(n/2) { rand.nextGaussian() } + List(n/2) { rand.nextGaussian() * 1.5 + 1.5 },
    "cond" to List(n/2) { "A" } + List(n/2) { "B" }
)

var p = lets_plot(data) +
        geom_density { x = "rating"; color = "cond" } + ggsize(500, 250)
        
ggsave(p, "density.png")        
``` 
<img src="https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/images/ggsave_demo.png" alt="Couldn't load ggsave_demo.png" width="500" height="250"/>
<br/>

See `ggsave()` [documentation](https://htmlpreview.github.io/?https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/api-reference/plot-api/jetbrains.letsPlot.export/ggsave.html)
for more information about the function arguments and default values.

<a id="tooltip-customization"></a>
#### Tooltip customization

You can customize the content of tooltips for the layer by using the parameter `tooltips` of `geom` functions.

Learn more: [Tooltip Customization](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/tooltips.md). 

<a id="geotools"></a>
#### GeoTools support

[GeoTools](https://www.geotools.org/) is an open source Java GIS Toolkit.

Lets-Plot supports visualization of a set of `SimpleFeature`-s stored in `SimpleFeatureCollection`, as well as
individual `Geometry` and `ReferencedEnvelope` objects.

Learn more: [GeoTools Support](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/geotools.md).

<a id="new"></a>

## What is new in 3.0.2

- Ordering categories:

  New parameters added to the `asDiscrete()` function:
  - `orderBy` - name of the variable by which the ordering will be performed;
  - `order` - ordering direction: 1 for ascending direction and -1 for descending (default).

  See: [as_discrete.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/as_discrete.md).

- Other improvements and fixes -
  see [CHANGELOG.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/CHANGELOG.md)
  for details.

<a id="change_log"></a>

## Change Log

See [CHANGELOG.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/CHANGELOG.md).

<a id="license"></a>
### License

Code and documentation released under the [MIT license](https://github.com/JetBrains/lets-plot/blob/master/LICENSE).
Copyright © 2019-2021, JetBrains s.r.o.
