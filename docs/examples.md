## Example Notebooks

Try the following tutorials and examples to learn and evaluate various features of the `Lets-Plot` library.

- [Quickstart and User Guide](#quickstart)
- [Geoms and Stats](#geoms_n_stats)
- [Position Adjustment](#pos)
- [Scales](#scales)
- [Facets](#facets)
- [GGBunch](#ggbunch)
- [`as_discrete()` function](#as_discrete)
- [gggrid()](#gggrig)
- [Export to File](#export)
- [Theme](#theme)
- [Data Sampling](#sampling)
- [Tooltip Customization](#tooltip)
- [Correlation Plot](#corrplot)
- [GeoTools Support](#geotools)

<a id="quickstart"></a>
#### Quickstart and User Guide

* [quickstart.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/quickstart.ipynb)
* [user_guide.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/guide/user_guide.ipynb)

<a id="geoms_n_stats"></a>
#### Geoms and Stats

`geom_histogram, geom_density, geom_vline, geom_freqpoly, geom_boxplot`:

[distributions.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/distributions.ipynb)

`geom_errorbar, geom_line, geom_point, geom_bar, geom_crossbar, geom_linerange, geom_pointrange`:

[error_bars.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/error_bars.ipynb)

`geom_point, geom_smooth (stat_smooth)`:

[scatter_plot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/scatter_plot.ipynb)

[geom_smooth.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_smooth.ipynb) 

`geom_density2d, geom_density2df, geom_bin2d, geom_polygon, geom_point` :

[density_2d.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/density_2d.ipynb)

`geom_tile, geom_contour, geom_polygon (Stat.contour), geom_contourf` :

[contours.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/contours.ipynb)

`geom_text`, label format

[label_format.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/label_format.ipynb)


<a id="pos"></a>
#### Position Adjustment

* `position_dodge` : [error_bars.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/error_bars.ipynb)
* `position_jitter` : [scatter_plot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/scatter_plot.ipynb)


<a id="scales"></a>
#### Scales

* `scale_color_manual, scale_fill_manual` :
 [error_bars.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/error_bars.ipynb)
* `scale_x_continuous, scale_shape_manual` :
 [scatter_plot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/scatter_plot.ipynb)
* `scale_color_gradient` : [density_2d.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/density_2d.ipynb)
* `scale_fill_hue, scale_fill_grey, scale_color_gradient` : [contours.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/contours.ipynb)


<a id="facets"></a>
#### Facets

* `facet_grid`: 
[distributions.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/distributions.ipynb)

<a id="ggbunch"></a>

#### GGBunch

GGBunch allows to show a collection of plots on one figure. Each plot in the collection can have arbitrary location and
size. There is no automatic layout inside the bunch.

* [ggbunch.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/ggbunch.ipynb)
* [geom_smooth.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_smooth.ipynb)

<a id="gggrid"></a>

#### gggrid()

A simple utility which you can use to arrange plots in a regular grid.

* [correlation_plot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/correlation_plot.ipynb)

<a id="as_discrete"></a>

#### `as_discrete()` function

* [geom_smooth.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_smooth.ipynb)

<a id="export"></a>

#### Export to File

The `ggsave()` function is an easy way to export plot to a file in SVG, HTML or raster formats.

* [export_to_file.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/export_to_file.ipynb)


<a id="theme"></a>
#### Theme

Legend layout and axis presentation options : 
 
* [legend_and_axis.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/legend_and_axis.ipynb)

<a id="sampling"></a>
#### Data sampling 

Sampling is a special technique of data transformation, which helps dealing with large datasets and overplotting.

See: [Sampling in Lets-Plot](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/sampling.md).

* Pick sampling on
  Bar-chart: [sampling_pick.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/sampling_pick.ipynb)
* Stratified
  sampling: [sampling_stratified.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/sampling_stratified.ipynb)

<a id="tooltip"></a>

#### Tooltip Customization

* [tooltip_config.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/tooltip_config.ipynb)

<a id="corrplot"></a>

#### Correlation Plot

* [correlation_plot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/correlation_plot.ipynb)

<a id="geotools"></a>

#### GeoTools Support

See [GeoTools Support](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/geotools.md).

* [geotools_naturalearth.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geotools_naturalearth.ipynb)
