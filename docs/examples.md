## Example Notebooks

Try the following tutorials and examples to learn and evaluate various features of the `Lets-Plot` library.

- [Quickstart and User Guide](#quickstart)
- [Geoms and Stats](#geoms_n_stats)
- [Correlation Plot](#corrplot)
- [Q-Q Plot](#qq-plot)
- [Marginal Plot](#marginal_plot)
- [Position Adjustment](#pos)
- [Scales](#scales)
- [Time Series](#time-series)
- [Facets](#facets)
- [Coordinate Systems](#coord-sys)
- [Grouping Plots](#ggbunch)
- [Ordering categories, `asDiscrete()` function](#as_discrete)
- [Export to File](#export)
- [Formatting](#formatting)
- [Themes](#theme)
- [Data Sampling](#sampling)
- [Tooltip Customization](#tooltip)
- [GeoTools Support](#geotools)

<a id="quickstart"></a>
#### Quickstart and User Guide

* [quickstart.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/quickstart.ipynb)
* [user_guide.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/guide/user_guide.ipynb)

<a id="geoms_n_stats"></a>
#### Geoms and Stats

`geomHistogram, geomDensity, geomVLine, geomFreqpoly, geomBoxplot`:
[distributions.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/distributions.ipynb)

`geomViolin`:
[geom_violin.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_violin.ipynb)

`geomDotplot`:
[geom_dotplot](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_dotplot.ipynb)

`geomYDotplot`:
[geom_ydotplot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_ydotplot.ipynb)

`geomErrorBar, geomLine, geomPoint, geomBar, geomCrossbar, geomLineRange, geomPointRange`:
[error_bars.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/error_bars.ipynb)

`geomPoint, geomSmooth (statSmooth)`:

 - [scatter_plot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/scatter_plot.ipynb)
 - [geom_smooth.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_smooth.ipynb)

`geomDensity2D, geomDensity2DFilled, geomBin2D, geomPolygon, geomPoint`:
[density_2d.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/density_2d.ipynb)

`geomTile, geomContour, geomPolygon (Stat.contour), geomContourFilled`:
[contours.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/contours.ipynb)

`geomText`, label format:
[label_format.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/label_format.ipynb)

<a id="corrplot"></a>
#### Correlation Plot

* [correlation_plot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/correlation_plot.ipynb)

<a id="qq-plot"></a>
#### Q-Q Plot

 - Geometries: `geomQQ, geomQQLine, geomQQ2, geomQQ2Line`
 - Quick Q-Q plot: `qqPlot()`

[qq_plots.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/qq_plots.ipynb)

<a id="marginal_plot"></a>
#### Marginal Plot, **ggmarginal()**

* [marginal_layers.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/marginal_layers.ipynb)

<a id="pos"></a>
#### Position Adjustment

* `positionDodge` : [error_bars.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/error_bars.ipynb)
* `positionJitter` : [scatter_plot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/scatter_plot.ipynb)


<a id="scales"></a>
#### Scales

* `scaleColorManual, scaleFillManual` :
  [error_bars.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/error_bars.ipynb)
* `scaleXContinuous, scaleShapeManual` :
  [scatter_plot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/scatter_plot.ipynb)
* `scaleColorGradient` : [density_2d.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/density_2d.ipynb)
* `scaleFillHue, scaleFillGrey, scaleColorGradient` : [contours.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/contours.ipynb)


<a id="time-series"></a>
#### Time Series

`scaleXDatetime, scaleYDatetime, scaleYTime, scaleYTime`

* [scale_time.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/scale_time.ipynb)
* [formatting_axes_etc.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/formatting_axes_etc.ipynb)


<a id="facets"></a>
#### Facets

`facetGrid(), facetWrap()`

* [facets.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/facets.ipynb)
* [facets_free_scales.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/facets_free_scales.ipynb)


<a id="coord-sys"></a>

#### Coordinate Systems

`coordCartesian(), coordFixed(), coordMap(), coordFlip()`

* [coord_flip.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/coord_flip.ipynb)


<a id="ggbunch"></a>
#### Grouping Plots

_GGBunch_

*GGBunch* allows to show a collection of plots on one figure. Each plot in the collection can have arbitrary location and
size. There is no automatic layout inside the bunch.

* [ggbunch.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/ggbunch.ipynb)
* [geom_smooth.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_smooth.ipynb)

_gggrid()_

A simple utility which you can use to arrange plots in a regular grid.

* [correlation_plot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/correlation_plot.ipynb)
                         

<a id="as_discrete"></a>

#### Ordering categories, `asDiscrete()` function
                          
* [ordering_examples.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/ordering_examples.ipynb)
* [geom_smooth.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_smooth.ipynb)


<a id="export"></a>

#### Export to File

The `ggsave()` function is an easy way to export plot to a file in SVG, HTML or raster formats.

* [export_to_file.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/export_to_file.ipynb)

<a id="formatting"></a>

#### Formatting

* [formatting_axes_etc](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/formatting_axes_etc.ipynb)
* [label_format.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/label_format.ipynb)

<a id="theme"></a>

#### Themes
                   
Named themes: `themeGrey, themeLight, themeClassic, themeMinimal` and `themeMinimal2` (used by default).
Use the `theme()` function to modify components of a theme.

* [themes.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/themes.ipynb)
* [legend_and_axis.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/legend_and_axis.ipynb)
* [Title, subtitle, caption](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/title_subtitle_caption.ipynb)

<a id="sampling"></a>

#### Data Sampling

Sampling is a special technique of data transformation, which helps dealing with large datasets and overplotting.

See: [Sampling in Lets-Plot](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/sampling.md).

* Pick sampling on
  Bar-chart: [sampling_pick.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/sampling_pick.ipynb)
* Stratified
  sampling: [sampling_stratified.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/sampling_stratified.ipynb)

<a id="tooltip"></a>

#### Tooltip Customization

* [tooltip_config.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/tooltip_config.ipynb)
* [tooltips_theme.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/tooltips_theme.ipynb)

<a id="geotools"></a>

#### GeoTools Support

See [GeoTools Support](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/geotools.md).

* [geotools_naturalearth.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geotools_naturalearth.ipynb)
