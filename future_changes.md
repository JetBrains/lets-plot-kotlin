## [4.12.0] - 2025-11-dd

This release is 100% compatible with Lets-Plot [v 4.8.0](https://github.com/JetBrains/lets-plot/releases/tag/v4.7.3),
GeoTools [v 33.2](https://github.com/geotools/geotools/releases/tag/33.2)

### Added

- Geometries:
  - `geomPointDensity()` [[#1370](https://github.com/JetBrains/lets-plot/issues/1370)].

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.12.0/geom_pointdensity.ipynb).

  - Geoms with 1-to-1 statistics (such as `geomQQ()`, `geomSina()`) preserve the mapping to original data after statistical transformation.

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.12.0/stat_data_bijection.ipynb).

- Plot Layout:
  - The legend automatically wraps to prevent overlap - up to 15 rows for vertical legends and 5 columns for horizontal ones [[#1235](https://github.com/JetBrains/lets-plot/issues/1235)].

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.12.0/legend_wrap.ipynb).

  - `gggrid()`: support for shared legends (parameter `guides`).

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.12.0/gggrid_legend_collect.ipynb).

- Plot Theme:
  - `flavorStandard()` sets the theme's default color scheme [[#1277](https://github.com/JetBrains/lets-plot/issues/1277)]. <br>
    Use to override other flavors or to make defaults explicit.

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.12.0/flavor_standard.ipynb).

  - `themeGray()` as an alias for `themeGrey()`.

  - New `theme` functions for setting legend justification: `legendJustificationTop()`, `legendJustificationRight()`, <br>
    `legendJustificationBottom()`, and `legendJustificationLeft()`.

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.12.0/legend_justification.ipynb).

  - Support for inward axis ticks.

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.12.0/axis_tick_direction.ipynb).


### Changed



### Fixed
