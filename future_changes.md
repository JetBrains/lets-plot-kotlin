## [4.2.0] - 2023-mm-dd

### Added

- Axis `position` parameter in position scales `scaleX*(), scaleY*()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.1/axis_position.ipynb).

- `angle` parameter in `elementText()` in `theme()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.1/axis_text_angle.ipynb).



### Changed

- **Breaking change** in `geomViolin()`: parameter `drawQuantiles` renamed to `quantiles` - and now it works as in the `geomAreaRidges()` geometry.

- `geomViolin()`: added `quantileLines` parameter - as in the `geomAreaRidges()` geometry. Also, it was added a `..quantile..` statistic variable.

- `geomDensity()`: added two new parameters - `quantiles` and `quantileLines` - as in the `geomAreaRidges()` geometry. Also, it was added a `..quantile..` statistic variable.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/quantile_parameters.ipynb).


### Fixed

