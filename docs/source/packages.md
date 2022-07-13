# Package jetbrains.letsPlot.geom

## Examples

- `geomHistogram()`, `geomDensity()`, `geomVLine()`, `geomFreqpoly()`, `geomBoxplot()`: [distributions.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/distributions.ipynb)

- `geomViolin()`: [geom_violin.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_violin.ipynb)

- `geomDotplot()`: [geom_dotplot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_dotplot.ipynb)

- `geomYDotplot()`: [geom_ydotplot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_ydotplot.ipynb)

- `geomErrorBar()`, `geomLine()`, `geomPoint()`, `geomBar()`, `geomCrossbar()`, `geomLineRange()`, `geomPointRange()`: [error_bars.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/error_bars.ipynb)

- `geomPoint()`, `geomSmooth()` (`statSmooth()`): [scatter_plot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/scatter_plot.ipynb), [geom_smooth.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_smooth.ipynb)

- `geomDensity2D()`, `geomDensity2DFilled()`, `geomBin2D()`, `geomPolygon()`, `geomPoint()`: [density_2d.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/density_2d.ipynb)

- `geomTile()`, `geomContour()`, `geomPolygon()` (`Stat.contour()`), `geomContourFilled()`: [contours.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/contours.ipynb)

- `geomText()`: [label_format.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/label_format.ipynb)

# Package jetbrains.letsPlot.geom.extras

Functions to pass as a value of a parameter of layer functions like:

```kotlin
val n = 16
val data = mapOf(
    "x1" to List(n) { cos(2.0 * PI * it / n) },
    "y1" to List(n) { sin(2.0 * PI * it / n) },
    "x2" to List(n) { cos(2.0 * PI * (it + 1) / n) },
    "y2" to List(n) { sin(2.0 * PI * (it + 1) / n) },
)
letsPlot(data) +
    geomSegment(arrow=arrow(angle = 15, type = "closed"))
        { x="x1"; y="y1"; xend="x2"; yend="y2" } +
    coordFixed()
```

# Package jetbrains.letsPlot.sampling

Sampling options to pass as a value of `sampling` parameter of layer functions like:

```kotlin
val n = 100
val m = 5
val rand = java.util.Random(42)
val data = mapOf("x" to List(n) { rand.nextInt(m) })
letsPlot(data) +
    geomBar(sampling = samplingRandomStratified(3, seed=42)) { x="x" }
```

# Package jetbrains.letsPlot.tooltips

## Examples

- [tooltip_config.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/tooltip_config.ipynb)

- [tooltip_title.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/tooltip_title.ipynb)

- [tooltips_theme.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/tooltips_theme.ipynb)