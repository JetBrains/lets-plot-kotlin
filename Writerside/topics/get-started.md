# Get Started

<a href="https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub">
    <img src="https://jb.gg/badges/official-flat-square.svg" alt="JB Official" />
</a>
<a href="https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/LICENSE">
    <img src="https://img.shields.io/github/license/JetBrains/lets-plot-kotlin?style=flat-square&color=yellow" alt="License MIT" />
</a>
<a href="https://github.com/JetBrains/lets-plot-kotlin/releases/latest">
    <img src="https://img.shields.io/github/v/release/JetBrains/lets-plot-kotlin" alt="Latest Release" />
</a>


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