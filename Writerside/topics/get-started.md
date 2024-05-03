# Get Started


## Quick Start

```kotlin
val rand = java.util.Random()
val data = mapOf (
    "rating" to List(200) { rand.nextGaussian() } + List(200) { rand.nextGaussian() * 1.5 + 1.5 },
    "cond" to List(200) { "A" } + List(200) { "B" }
)
letsPlot(data) +
    geomDensity(color = "dark_green", alpha = .3) { x = "rating"; fill = "cond" } +
    ggsize(700, 350)
```

![Quick Start](quickstart.png)

<list columns="5">
    <li>
        <a href="https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/quickstart.ipynb">
            <img alt="NBViewer" src="jupyter.svg" height="64"/>
        </a>
    </li>
    <li>
        <a href="https://datalore.jetbrains.com/report/static/HZqq77cegYd.E7get_WnChZ/aTA9lQnPkRwdCzT6uy95GZ">
            <img alt="Datalore" src="datalore.svg" height="64"/>
        </a>
    </li>
</list>


## User Guide

[![Coding for Economists](coding_for_economists.png) { width=340 }](https://aeturrell.github.io/coding-for-economists)

*Coding for Economists* by Arthur Turrell

- [Easy Data Visualisation for Tidy Data with Lets-Plot](https://aeturrell.github.io/coding-for-economists/vis-letsplot.html) - how to make plots quickly using the declarative plotting.

[![Python4DS](Python4DS.png) { width=340 }](https://aeturrell.github.io/python4DS/)

*Python4DS* by Arthur Turrell

- [Data Visualisation](https://aeturrell.github.io/python4DS/data-visualise.html) - will teach you how to visualise your data using using Lets-Plot.

- [Layers](https://aeturrell.github.io/python4DS/vis-layers.html) - a deeper dive into aesthetic mappings, geometric objects, and facets.

- [Exploratory Data Analysis](https://aeturrell.github.io/python4DS/exploratory-data-analysis.html) - search for answers by visualising, transforming, and modelling your data.


## Gallery

Visit the [gallery of examples](gallery.md).