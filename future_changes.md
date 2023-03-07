## [4.2.0] - 2023-mm-dd

### Added

- `jointPlot()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.1/joint_plot.ipynb). 

- Axis `position` parameter in position scales `scaleX*(), scaleY*()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.1/axis_position.ipynb).

- `angle` parameter in `elementText()` in `theme()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.1/axis_text_angle.ipynb).

-  Drawing quantile lines and filling quantile areas in `geomViolin()` and `geomDensity()`.

   See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.1/quantile_parameters.ipynb).

- `density2d` and `density2df` geometry types in `residualPlot()`.


### Changed

- [BREAKING] `geomViolin()` no longer supports parameter `drawQuantiles`. Use new `quantileLines` and `quantiles` parameters as needed.


- [BREAKING] `stack` and `fill` position adjustments now stack objects on top of each other only if these objects belong to different **groups**.
  If necessary, use `mode="all"` in `positionStack()` or `positionFill()` to stack objects regardless of their group.
  
- See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.1/position_stack.ipynb).


### Fixed

