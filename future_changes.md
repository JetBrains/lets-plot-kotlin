## [4.3.0] - 2023-03-dd

### Added

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
  
- See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/position_stack.ipynb).


### Fixed

