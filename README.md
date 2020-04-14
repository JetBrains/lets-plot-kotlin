# Lets-Plot for Kotlin

<a href="https://opensource.org/licenses/MIT">
<img src="https://img.shields.io/badge/License-MIT-yellow.svg" alt="Couldn't load MIT license svg"/>
</a>

### Overview

**Lets-Plot** for Kotlin is a Kotlin API for [Lets-Plot](https://github.com/JetBrains/lets-plot) library - an open-source plotting library for statistical data which is written entirely in the [Kotlin programming language](https://kotlinlang.org/). 

[Here](docs/guide/user_guide.ipynb) you can find a documentation with examples in form of Jupyter notebook using Kotlin kernel.

Lets-Plot Kotlin API was built on the principals of layered graphics first described in the Leland Wilkinson work [The Grammar of Graphics](https://www.goodreads.com/book/show/2549408.The_Grammar_of_Graphics)
and later implemented in the [`ggplot2`](https://ggplot2.tidyverse.org/) package for R.

### Lets-Plot in Jupyter with Kotlin Kernel

#### Installation

Install Kotlin Kernel and OpenJdk in Conda environment:

```shell script
conda install kotlin-jupyter-kernel -c jetbrains
```                                             

#### Quickstart in Jupyter

In Jupyter create new notebook, choose kernel - `kotlin`.

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
<br/>
<a href="https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/quickstart.ipynb" 
   target="_parent"> 
   <img src="https://raw.githubusercontent.com/jupyter/design/master/logos/Badges/nbviewer_badge.png" 
        width="109" height="20">
</a>

#### Resources

* [Kotlin for Data Science](https://kotlinlang.org/docs/reference/data-science-overview.html) : overview of Kotlin Jupyter kernel etc.
* [Using Kotlin for Data Science](https://www.youtube.com/watch?v=APnyDVye4JA&list=PLQ176FUIyIUY6SKGl3Cj9yeYibBuRr3Hl&index=39&t=0s) : recording of the talk at KotlinConf-19. 

### `Lets-Plot` in JVM-based application

Besides Jupyter notebooks, Lets-Plot library and Kotlin API enables embedding plots into a JVM-based application.

See [README_DEV.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/README_DEV.md) to learn more about creating plots in a JVM environment.

### License

Code and documentation released under the [MIT license](https://github.com/JetBrains/lets-plot/blob/master/LICENSE).
Copyright 2019, JetBrains s.r.o.
