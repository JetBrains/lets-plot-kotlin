# Package org.jetbrains.letsPlot

## Examples

- [quickstart.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/quickstart.ipynb)

- [user_guide.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/guide/user_guide.ipynb)

- [error_bars.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/error_bars.ipynb)

More examples you can find [here](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/examples.md).

# Package org.jetbrains.letsPlot.bistro.corr

The `CorrPlot` class produces a correlation matrix plot.

## Examples

- [correlation_plot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/correlation_plot.ipynb)

# Package org.jetbrains.letsPlot.bistro.qq

The `qqPlot()` function produces a Q-Q plot (quantile-quantile plot).

## Examples

- [qq_plots.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/qq_plots.ipynb)

# Package org.jetbrains.letsPlot.export

The `ggsave()` function is an easy way to export plot to a file in SVG, HTML or raster formats.

## Examples

- [export_to_file.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/export_to_file.ipynb)

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

- [facets.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/facets.ipynb)

- [facets_free_scales.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/facets_free_scales.ipynb)

# Package org.jetbrains.letsPlot.geom

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

- `geomLabel()`: [geom_label.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_label.ipynb)

- `geomQQ()`, `geomQQLine()`, `geomQQ2()`, `geomQQ2Line()`, `qqPlot()`: [qq_plots.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/qq_plots.ipynb)

# Package org.jetbrains.letsPlot.label

See [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md).

## Examples

- [title_subtitle_caption.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/title_subtitle_caption.ipynb)

- [legend_and_axis.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/legend_and_axis.ipynb)

# Package org.jetbrains.letsPlot.sampling

Sampling options to pass as a value of `sampling` parameter of layer functions like:

```kotlin
val n = 100
val m = 5
val rand = java.util.Random(42)
val data = mapOf("x" to List(n) { rand.nextInt(m) })
letsPlot(data) +
    geomBar(sampling = samplingRandomStratified(3, seed=42)) { x="x" }
```

For more information, see [sampling.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/sampling.md).

## Examples

- [sampling_pick.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/sampling_pick.ipynb)

- [sampling_stratified.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/sampling_stratified.ipynb)

# Package org.jetbrains.letsPlot.scale

## Examples

- `scaleColorManual()`, `scaleFillManual()`: [error_bars.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/error_bars.ipynb)

- `scaleXContinuous()`, `scaleShapeManual()`: [scatter_plot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/scatter_plot.ipynb)

- `scaleColorGradient()`: [density_2d.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/density_2d.ipynb)

- `scaleFillHue()`, `scaleFillGrey()`, `scaleColorGradient()`: [contours.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/contours.ipynb)

- `Viridis color scales` : [colors_viridis.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/colors_viridis.ipynb)

# Package org.jetbrains.letsPlot.spatial

[GeoTools](https://www.geotools.org/) is an open source Java GIS Toolkit, supported by the Lets-Plot library.

For more information, see [geotools.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/geotools.md).

## Examples

- [geotools_naturalearth.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geotools_naturalearth.ipynb)

# Package org.jetbrains.letsPlot.stat

## Examples

- [geom_smooth.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_smooth.ipynb)

# Package org.jetbrains.letsPlot.tooltips

See [tooltips.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/tooltips.md).

## Examples

- [tooltip_config.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/tooltip_config.ipynb)

- [tooltip_title.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/tooltip_title.ipynb)

- [tooltips_theme.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/tooltips_theme.ipynb)