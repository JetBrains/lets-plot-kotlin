# Package org.jetbrains.letsPlot

## Examples

- [quickstart.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/quickstart.ipynb)

- [user_guide.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/guide/user_guide.ipynb)

- [error_bars.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/error_bars.ipynb)

More examples you can find [here](https://nbviewer.org/github/JetBrains/lets-plot-docs/tree/master/source/kotlin_examples/).

# Package org.jetbrains.letsPlot.bistro.corr

The `CorrPlot` class produces a correlation matrix plot.

## Examples

- [correlation_plot.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/correlation_plot.ipynb)

# Package org.jetbrains.letsPlot.bistro.joint

The `jointPlot()` function produces a joint plot that contains bivariate and univariate graphs at the same time.

## Examples

- [joint_plot.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/joint_plot.ipynb)

# Package org.jetbrains.letsPlot.bistro.qq

The `qqPlot()` function produces a Q-Q plot (quantile-quantile plot).

## Examples

- [qq_plots.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/qq_plots.ipynb)

# Package org.jetbrains.letsPlot.bistro.residual

The `residualPlot()` function produces a residual plot with an optional density summary on margins.

## Examples

- [residual_plot.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/residual_plot.ipynb)

# Package org.jetbrains.letsPlot.export

The `ggsave()` function is an easy way to export plot to a file in SVG, HTML or raster formats.

## Examples

- [export_to_file.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/export_to_file.ipynb)

# Package org.jetbrains.letsPlot.geom.extras

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

# Package org.jetbrains.letsPlot.facet

## Examples

- [facets.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/facets.ipynb)

- [facets_free_scales.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/facets_free_scales.ipynb)

# Package org.jetbrains.letsPlot.geom

## Examples

- `geomHistogram()`, `geomDensity()`, `geomVLine()`, `geomFreqpoly()`, `geomBoxplot()`: [distributions.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/distributions.ipynb)

- `geomViolin()`: [geom_violin.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_violin.ipynb)

- `geomDotplot()`: [geom_dotplot.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_dotplot.ipynb)

- `geomYDotplot()`: [geom_ydotplot.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_ydotplot.ipynb)

- `geomErrorBar()`, `geomLine()`, `geomPoint()`, `geomBar()`, `geomCrossbar()`, `geomLineRange()`, `geomPointRange()`: [error_bars.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/error_bars.ipynb)

- `geomPoint()`, `geomSmooth()` (`statSmooth()`): [scatter_plot.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/scatter_plot.ipynb), [geom_smooth.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_smooth.ipynb)

- `geomDensity2D()`, `geomDensity2DFilled()`, `geomBin2D()`, `geomPolygon()`, `geomPoint()`: [density_2d.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/density_2d.ipynb)

- `geomTile()`, `geomContour()`, `geomPolygon()` (`Stat.contour()`), `geomContourFilled()`: [contours.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/contours.ipynb)

- `geomText()`, `geomLabel()`: [text_geoms.ipynb](https://datalore.jetbrains.com/report/static/HZqq77cegYd.E7get_WnChZ/Lb69ZPTQWq3XbupPxnQ4Rh)

- `geomQQ()`, `geomQQLine()`, `geomQQ2()`, `geomQQ2Line()`, `qqPlot()`: [qq_plots.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/qq_plots.ipynb)

# Package org.jetbrains.letsPlot.label

See [formats.html](https://lets-plot.org/kotlin/formats.html).

## Examples

- [title_subtitle_caption.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/title_subtitle_caption.ipynb)

- [legend.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/legend.ipynb)

# Package org.jetbrains.letsPlot.sampling

Sampling options.                

Used as a value of the `sampling` parameter in `geom_xyz()` functions.


```kotlin
val n = 100
val m = 5
val rand = java.util.Random(42)
val data = mapOf("x" to List(n) { rand.nextInt(m) })
letsPlot(data) +
    geomBar(sampling = samplingRandomStratified(3, seed=42)) { x="x" }
```

For more information, see [sampling.html](https://lets-plot.org/kotlin/sampling.html).

## Examples

- [sampling_pick.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/sampling_pick.ipynb)

- [sampling_stratified.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/sampling_stratified.ipynb)

# Package org.jetbrains.letsPlot.scale

## Examples

- `scaleColorManual()`, `scaleFillManual()`: [error_bars.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/error_bars.ipynb)

- `scaleXContinuous()`, `scaleShapeManual()`: [scatter_plot.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/scatter_plot.ipynb)

- `scaleColorGradient()`: [density_2d.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/density_2d.ipynb)

- `scaleFillHue()`, `scaleFillGrey()`, `scaleColorGradient()`: [contours.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/contours.ipynb)

- `Viridis color scales` : [colors_viridis.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/colors_viridis.ipynb)

# Package org.jetbrains.letsPlot.spatial

[GeoTools](https://www.geotools.org/) is an open source Java GIS Toolkit, supported by the Lets-Plot library.

For more information, see [Geospatial Charts](https://lets-plot.org/kotlin/geospatial-charts.html).

## Examples

- [geotools_naturalearth.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/demo/geotools_naturalearth.ipynb)

# Package org.jetbrains.letsPlot.stat

## Examples

- [geom_smooth.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_smooth.ipynb)

# Package org.jetbrains.letsPlot.tooltips

See [tooltips.html](https://lets-plot.org/kotlin/tooltips.html).

## Examples

- [tooltip_config.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/tooltip_config.ipynb)

- [tooltip_title.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/tooltip_title.ipynb)

- [themes.ipynb](https://datalore.jetbrains.com/report/static/HZqq77cegYd.E7get_WnChZ/rjq52BpjPak2geihq3ol1h#Tooltips)

# Package org.jetbrains.letsPlot.annotations

## Examples

- [annotations_for_pie.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/annotations_for_pie.ipynb)

# Package org.jetbrains.letsPlot.pos

Position adjustment options.

Used as a value of the `position` parameter in `geom_xyz()` functions.

```kotlin
val n = 100
val m = 5
val k = 2
val rand = java.util.Random(42)
val data = mapOf(
    "v" to List(n) { rand.nextInt(m) },
    "c" to List(n) { rand.nextInt(k) }
)
letsPlot(data) +
    geomBar(position = positionDodge()) { x="v"; fill=asDiscrete("c") }
```

## Examples

- [error_bars.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/error_bars.ipynb)
