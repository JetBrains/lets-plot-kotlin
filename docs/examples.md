>
>
>
> # Warning:
>   This documentation page is outdated.
>
>   For up-to-date information, see the [Lets-Plot for Kotlin](https://lets-plot.org/kotlin) documentation website.
>
>
>

## Example Notebooks

Try the following tutorials and examples to learn and evaluate various features of the `Lets-Plot` library.

- [Quickstart and User Guide](#quickstart)
- [Geoms and Stats](#geoms_n_stats)
- [Correlation Plot](#corrplot)
- [Q-Q Plot](#qq-plot)
- [Marginal Plot](#marginal_plot)
- [Joint Plot](#joint_plot)
- [Residual Plot](#residual-plot)
- [Images](#imshow)
- [Position Adjustment](#pos)
- [Scales](#scales)
  - [Axis Position](#axis_pos)
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

* [quickstart.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/quickstart.ipynb)
* [user_guide.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/guide/user_guide.ipynb)

<a id="geoms_n_stats"></a>
#### Geoms and Stats

`geomHistogram, geomDensity, geomVLine, geomFreqpoly, geomBoxplot`:
* [distributions.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/distributions.ipynb)
* [boxplot_whisker_width.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/boxplot_whisker_width.ipynb) 
                             
`geomPie`:
* [geom_pie.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_pie.ipynb)
* [annotations_for_pie.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/annotations_for_pie.ipynb)
* [stat_count_2d.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/stat_count_2d.ipynb)

`geomLollipop`:
[geom_lollipop.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_lollipop.ipynb)

`geomViolin, geomAreaRidges`:
[geom_violin.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_violin.ipynb)
[ridgeline_plot.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/ridgeline_plot.ipynb)
[quantile_parameters.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/quantile_parameters.ipynb)

`geomDotplot`:
[geom_dotplot](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_dotplot.ipynb)

`geomYDotplot`:
[geom_ydotplot.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_ydotplot.ipynb)

`geomErrorBar, geomLine, geomPoint, geomBar, geomCrossbar, geomLineRange, geomPointRange`:
* [error_bars.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/error_bars.ipynb)
* [horizontal_error_bars.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/horizontal_error_bars.ipynb)

`geomPoint, geomSmooth (statSmooth)`:

 - [scatter_plot.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/scatter_plot.ipynb)
 - [geom_smooth.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_smooth.ipynb)
 - [aes_stroke.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/aes_stroke.ipynb)

`geomDensity2D, geomDensity2DFilled, geomBin2D, geomPolygon, geomPoint`:
[density_2d.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/density_2d.ipynb)

`geomTile, geomContour, geomPolygon (Stat.contour), geomContourFilled`:
[contours.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/contours.ipynb)

`geomText, geomLabel`, label format:
* [label_format.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/label_format.ipynb)
* [geom_label.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_label.ipynb)
* [geom_text_and_label_features.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_text_and_label_new_features.ipynb)

<a id="corrplot"></a>
#### Correlation Plot

* [correlation_plot.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/correlation_plot.ipynb)

<a id="qq-plot"></a>
#### Q-Q Plot

 - Geometries: `geomQQ, geomQQLine, geomQQ2, geomQQ2Line`
 - Quick Q-Q plot: `qqPlot()`

[qq_plots.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/qq_plots.ipynb)

<a id="marginal_plot"></a>
#### Marginal Plot, **ggmarginal()**

* [marginal_layers.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/marginal_layers.ipynb)

<a id="joint_plot"></a>
#### Joint Plot

* [joint_plot.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/joint_plot.ipynb)

* <a id="residual_plot"></a>
#### Residual Plot

* [residual_plot.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/residual_plot.ipynb)

<a id="imshow"></a>
#### Images

* [image_101.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/image_101.ipynb)
* [image_fisher_boat.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/image_fisher_boat.ipynb)
* [image_grayscale.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/image_grayscale.ipynb)
* [image_extent.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/image_extent.ipynb)

<a id="pos"></a>
#### Position Adjustment

* `positionDodge()` : [error_bars.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/error_bars.ipynb)
* `positionDodgeV()` : [horizontal_error_bars.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/horizontal_error_bars.ipynb)
* `positionJitter()` : [scatter_plot.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/scatter_plot.ipynb)
* `positionStack(), positionFill()` : [position_stack.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/position_stack.ipynb)

<a id="scales"></a>
#### Scales

* `scaleColorManual, scaleFillManual` :
  [error_bars.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/error_bars.ipynb)
* `scaleXContinuous, scaleShapeManual` :
  [scatter_plot.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/scatter_plot.ipynb)
* `scaleColorGradient` : [density_2d.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/density_2d.ipynb)
* `scaleFillHue, scaleFillGrey, scaleColorGradient` : [contours.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/contours.ipynb)
* `Viridis scales` : [colors_viridis.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/colors_viridis.ipynb)

<a id="axis_pos"></a>
##### Axis Position 
  * [axis_position.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/axis_position.ipynb)

<a id="time-series"></a>
#### Time Series

`scaleXDatetime, scaleYDatetime, scaleYTime, scaleYTime`

* [scale_time.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/scale_time.ipynb)
* [formatting_axes_etc.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/formatting_axes_etc.ipynb)


<a id="facets"></a>
#### Facets

`facetGrid(), facetWrap()`

* [facets.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/facets.ipynb)
* [facets_free_scales.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/facets_free_scales.ipynb)


<a id="coord-sys"></a>
#### Coordinate Systems

`coordCartesian(), coordFixed(), coordMap(), coordFlip()`

* [coord_flip.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/coord_flip.ipynb)


<a id="ggbunch"></a>
#### Grouping Plots

_GGBunch_

*GGBunch* allows to show a collection of plots on one figure. Each plot in the collection can have arbitrary location and
size. There is no automatic layout inside the bunch.

* [ggbunch.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/ggbunch.ipynb)
* [geom_smooth.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_smooth.ipynb)

_gggrid()_

A simple utility which you can use to arrange plots in a regular grid.

* [plot_grid.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/plot_grid.ipynb)
                         

<a id="as_discrete"></a>
#### Ordering categories, `asDiscrete()` function
                          
* [ordering_examples.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/ordering_examples.ipynb)
* [geom_smooth.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_smooth.ipynb)


<a id="export"></a>
#### Export to File

The `ggsave()` function is an easy way to export plot to a file in SVG, HTML or raster formats.

* [export_to_file.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/export_to_file.ipynb)
      

<a id="formatting"></a>
#### Formatting

* [formatting_axes_etc](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/formatting_axes_etc.ipynb)
* [label_format.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/label_format.ipynb)

<a id="theme"></a>
#### Themes
                   
Complete themes: `themeBW, themeGrey, themeLight, themeClassic, themeMinimal` and `themeMinimal2` (used by default).

[complete_themes.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/complete_themes.ipynb)

Use the `theme()` function to modify components of a theme.
                           
* [themes.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/themes.ipynb)
* [legend.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/legend.ipynb)
* [Title, subtitle, caption](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/title_subtitle_caption.ipynb)
* [font size and family](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/font_size_and_family.ipynb)
* [label hjust, vjust](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/hjust_vjust.ipynb)
* [label margins](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/text_margins.ipynb)
         
                        
Color schemes (flavors): `flavorDarcula, flavorSolarizedLight, flavorSolarizedDark, flavorHighContrastLight, flavorHighContrastDark`. 

[theme_flavors.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/theme_flavors.ipynb).


<a id="sampling"></a>
#### Data Sampling

Sampling is a special technique of data transformation, which helps dealing with large datasets and overplotting.

See: [Sampling in Lets-Plot](https://lets-plot.org/kotlin/sampling.html).

* Pick sampling on
  Bar-chart: [sampling_pick.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/sampling_pick.ipynb)
* Stratified
  sampling: [sampling_stratified.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/sampling_stratified.ipynb)
         

<a id="tooltip"></a>
#### Tooltip Customization

* [tooltip_config.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/tooltip_config.ipynb)
* [tooltips_theme.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/tooltips_theme.ipynb)
     

<a id="geotools"></a>
#### GeoTools Support

See [Geospatial Charts](https://lets-plot.org/kotlin/geospatial-charts.html).

* [geotools_naturalearth.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/demo/geotools_naturalearth.ipynb)
* [geom_label.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_label.ipynb)
* [projection_provided.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/projection_provided.ipynb)