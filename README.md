# Lets-Plot for Kotlin [![official JetBrains project](http://jb.gg/badges/official-flat-square.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)

<table>
    <tr>
        <td>Latest API Version</td>
        <td>
            <a href="https://bintray.com/jetbrains/lets-plot-maven/lets-plot-kotlin-api-jars-dev/_latestVersion"/>
            <img src="https://api.bintray.com/packages/jetbrains/lets-plot-maven/lets-plot-kotlin-api-jars-dev/images/download.svg"/>
        </td>
    </tr>
    <tr>
        <td>Latest Lets-Plot Version</td>
        <td>
            <a href="https://bintray.com/jetbrains/lets-plot-maven/lets-plot-jars/_latestVersion"/>
            <img src="https://api.bintray.com/packages/jetbrains/lets-plot-maven/lets-plot-jars/images/download.svg"/>
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
    - [Quick start with Jupyter](#start)
    - [Example notebooks](#jupyter-examples)
    - [Resources](#resources)
   
- [Lets-Plot in JVM-based application](#jvm)   

- [Lets-Plot Kotlin API](#api)
    - [User guide and API reference](#guide)
    - [Data sampling](#sampling)
    - [GGBunch](#ggbunch)
    - [Export plot to file](#export)
    
- [License](#license)    


<a name="Overview" id="overview"></a>
### Overview

**Lets-Plot** for Kotlin is a Kotlin API for [Lets-Plot](https://github.com/JetBrains/lets-plot) library - an open-source plotting library for statistical data which is written entirely in the [Kotlin programming language](https://kotlinlang.org/). 

[Here](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/guide/user_guide.ipynb) you can find a documentation with examples in form of Jupyter notebook using Kotlin kernel.

Lets-Plot Kotlin API was built on the principals of layered graphics first described in the Leland Wilkinson work [The Grammar of Graphics](https://www.goodreads.com/book/show/2549408.The_Grammar_of_Graphics)
and later implemented in the [ggplot2](https://ggplot2.tidyverse.org/) package for R.


<a id="jupyter"></a>
### Lets-Plot in Jupyter with Kotlin Kernel

<a id="inst"></a>
#### Installation

in Jupyter notebook with Kotlin Kernel Lets-Plot library is available out-of-the-box. 
 
To install Kotlin Kernel and OpenJdk into Conda environment run the following command:

```shell script
conda install kotlin-jupyter-kernel -c jetbrains
```                                             

To find more information about Jupyter Kotlin kernel check the [Kotlin kernel for Jupyter/iPython](https://github.com/Kotlin/kotlin-jupyter) project.

<a id="start"></a>
#### Quickstart in Jupyter

In Jupyter create a new notebook and choose the kernel - `kotlin`.

To evaluate the plotting capabilities of Lets-Plot, add the following code to a Jupyter notebook:

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

<img src="https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/images/quickstart.png" alt="Couldn't load quickstart.png" width="500" height="270"/>
<br/>
<a href="https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/quickstart.ipynb" 
   target="_parent"> 
   <img src="https://raw.githubusercontent.com/jupyter/design/master/logos/Badges/nbviewer_badge.png" 
        width="109" height="20">
</a>
<br/>

<a id="jupyter-examples"></a>
#### Example notebooks

Try the following [examples](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/examples.md) to study features of the `Lets-Plot` library.

<a id="resources"></a>
#### Resources

* [Kotlin for Data Science](https://kotlinlang.org/docs/reference/data-science-overview.html) : overview of Kotlin Jupyter kernel etc.
* [Using Kotlin for Data Science](https://www.youtube.com/watch?v=APnyDVye4JA&list=PLQ176FUIyIUY6SKGl3Cj9yeYibBuRr3Hl&index=39&t=0s) : recording of the talk at KotlinConf-19. 

<a id="jvm"></a>
### Lets-Plot in JVM-based application

Besides Jupyter notebooks, Lets-Plot library and Kotlin API enables embedding plots into a JVM-based application.

See [README_DEV.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/README_DEV.md) to learn more about creating plots in a JVM environment.

<a id="api"></a>
### Lets-Plot Kotlin API

<a id="guide"></a>
#### User guide and API reference
The User Guide in the form of Jupyter notebook: [user_guide.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/guide/user_guide.ipynb)

Lets-Plot Kotlin API [reference](https://htmlpreview.github.io/?https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/api-reference/plot-api/index.html).

<a id="sampling"></a>
#### Data sampling 

Sampling is a special technique of data transformation, which helps dealing with large datasets and overplotting.

[Learn more](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/sampling.md) about sampling in Lets-Plot. 

<a id="ggbunch"></a>
#### GGBunch

GGBunch allows to show a collection of plots on one figure. Each plot in the collection can have arbitrary location and size. There is no automatic layout inside the bunch.

Examples:

* [ggbunch.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/ggbunch.ipynb) 
* [geom_smooth.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_smooth.ipynb) 


<a id="export"></a>
#### Export plot to file

[TBD]

<a id="license"></a>
### License

Code and documentation released under the [MIT license](https://github.com/JetBrains/lets-plot/blob/master/LICENSE).
Copyright © 2019-2020, JetBrains s.r.o.
