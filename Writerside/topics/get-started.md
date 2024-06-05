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

<list columns="5">
    <li>
        <a href="%nb-nbviewer-quickstart%">
            <img alt="NBViewer" src="jupyter.svg" height="64"/>
        </a>
    </li>
    <li>
        <a href="%nb-datalore-quickstart%">
            <img alt="Datalore" src="datalore.svg" height="64"/>
        </a>
    </li>
</list>

You can read more about running Lets-Plot for Kotlin in notebooks and on different platforms [here](https://github.com/JetBrains/lets-plot-kotlin#usage).