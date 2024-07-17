# Get Started

A **Grammar of Graphics** for Kotlin.

<p>
    <a href="https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub">
        <img src="https://jb.gg/badges/official-flat-square.svg" alt="JB Official" style="inline"/>
    </a>
    <a href="https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/LICENSE">
        <img src="https://img.shields.io/github/license/JetBrains/lets-plot-kotlin?style=flat-square&color=yellow" alt="License MIT" style="inline"/>
    </a>
    <a href="https://github.com/JetBrains/lets-plot-kotlin/releases/latest">
        <img src="https://img.shields.io/github/v/release/JetBrains/lets-plot-kotlin" alt="Latest Release" style="inline"/>
    </a>
</p>

**Lets-Plot Kotlin API** is a [Kotlin API](%api_reference%) for [Lets-Plot Multiplatform](https://github.com/JetBrains/lets-plot) plotting library, which is built on the principles of layered graphics first described in the Leland Wilkinson work [The Grammar of Graphics](https://www.goodreads.com/book/show/2549408.The_Grammar_of_Graphics).

<table>
    <tr>
        <td>
            <a href="https://ggplot2-book.org/index.html"> 
               <img src="ggplot2-elegant-graphics-for-data-analysis.jpeg" alt="book cover" width="150" height="228" style="inline"/>
            </a>
        </td>
        <td>
            <p>
                Lets-Plot <a href="%api_reference%">Kotlin API</a> is largely based on the API<br/>
                provided by <a href="https://ggplot2.tidyverse.org/">ggplot2</a> package well-known to data scientists who use R.
            </p>
            <p>
                To learn more about the <i>Grammar of Graphics</i>,<br/>
                we recommend an excellent book called<br/>
                <a href="https://ggplot2-book.org/index.html">"ggplot2: Elegant Graphics for Data Analysis"</a>.
            </p> 
            <p>
                This will be a good prerequisite for further exploration of the Lets-Plot library.
            </p>
        </td>  
    </tr>
</table>


## Quick Start

Inside [Kotlin Notebook](https://plugins.jetbrains.com/plugin/16340-kotlin-notebook), [Datalore](https://datalore.jetbrains.com/) or [Jupyter with Kotlin Kernel](https://github.com/Kotlin/kotlin-jupyter#readme):

```
%use lets-plot
```

```kotlin
val rand = java.util.Random(37)
val data = mapOf (
    "rating" to List(200) { rand.nextGaussian() } + List(200) { rand.nextGaussian() * 1.5 + 1 },
    "cond" to List(200) { "A" } + List(200) { "B" }
)

letsPlot(data) { x = "rating"; fill = "cond" } + ggsize(700, 300) +
    geomDensity(color = "dark_green", alpha = .7) + scaleFillBrewer(type = "seq") +
    theme(panelGridMajorX = "blank")
```

![Quick Start](quickstart.png)

<p>
    <a href="%nb-nbviewer-quickstart%">
        <img alt="NBViewer" src="jupyter.svg" height="64" style="inline"/>
    </a>
    <a href="%nb-datalore-quickstart%">
        <img alt="Datalore" src="datalore.svg" height="64" style="inline"/>
    </a>
</p>

You can read more about running *Lets-Plot* for Kotlin in notebooks and on different platforms [here](https://github.com/JetBrains/lets-plot-kotlin#usage).