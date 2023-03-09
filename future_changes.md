## [4.3.0] - 2023-03-dd

### Added

- `gggrid()` function, as a replacement for the earlier variant of [gggrid()](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot/gggrid.html).
  - sup-plots alignment
  - nested grids
  - works well with `ggsize()`

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/plot_grid.ipynb).
         

- `jointPlot()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/joint_plot.ipynb). 
                                      

- Axis `position` parameter in position scales `scaleX*(), scaleY*()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/axis_position.ipynb).


- `angle` parameter in `elementText()` in `theme()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/axis_text_angle.ipynb).


-  Drawing quantile lines and filling quantile areas in `geomViolin()` and `geomDensity()`.

   See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/quantile_parameters.ipynb).


- Additional "color" aesthetics: `paint_a, paint_b, paint_c`.

  These aesthetics are flexible and can be used as either "color" or "fill" as needed.
  
  See [Multiple Color Scales](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.1/multiple_color_scales.ipynb) demo.

  Also added a set of related "color scale" functions with the "aesthetic" parameter for configuring of additional color scales.

  See [New "Scale" Functions](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/scale_functions.ipynb) demo.
  

- `density2d` and `density2df` geometry types in `residualPlot()`.


### Changed

- [BREAKING] `geomViolin()` no longer supports parameter `drawQuantiles`. Use new `quantileLines` and `quantiles` parameters as needed.

- [BREAKING] `stack` and `fill` position adjustments now stack objects on top of each other only if these objects belong to different **groups**.
  
  If necessary, use `mode="all"` in `positionStack()` or `positionFill()` to stack objects regardless of their group.
  
  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/position_stack.ipynb).

- [BREAKING] The deprecation level raised to "ERROR" for all API that was deprecated in v. 4.2.0 and earlier.

- [DEPRECATED] The earlier variant of [gggrid()](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot/gggrid.html).
  
  From now-on please use the variant added in this release: [new gggrid()]( to do ).

- Sampling: drastically increased default N for "pick sampling" and for other types of sampling [[#687](https://github.com/JetBrains/lets-plot/issues/687)].

### Fixed

- Tooltip does not reflect `..quantile..` aesthetic change [[#658](https://github.com/JetBrains/lets-plot/issues/658)].
- 'map_join': variable is lost after "stat" [[#664](https://github.com/JetBrains/lets-plot/issues/664)].
- Error when tooltip has variable mapped to aesthetic used by stat [[#665](https://github.com/JetBrains/lets-plot/issues/665)].
- Groups not sorted similarly when `position='stack'` [[#673](https://github.com/JetBrains/lets-plot/issues/673)].
- Area ridges: fill overlaps geometry borders when colors are repeated [[#674](https://github.com/JetBrains/lets-plot/issues/674)].
- Sampling: increase the default N for "pick sampling" and for other types of sampling [[#687](https://github.com/JetBrains/lets-plot/issues/687)].
