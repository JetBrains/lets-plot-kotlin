# Lets-Plot for Kotlin

<a href="https://opensource.org/licenses/MIT">
<img src="https://img.shields.io/badge/License-MIT-yellow.svg" alt="Couldn't load MIT license svg"/>
</a>

### Overview

**Lets-Plot** for Kotlin is a Kotlin API for [Lets-Plot](https://github.com/JetBrains/lets-plot) library - an open-source plotting library for statistical data which is written entirely in the [Kotlin programming language](https://kotlinlang.org/). 

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

```python
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


