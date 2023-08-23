## [4.4.2] - 2023-mm-dd

### Added

- Support for variadic line width and/or color in `geom_line()` and `geom_path()` [[LP-313](https://github.com/JetBrains/lets-plot/issues/313)].

  [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/aes_size_color_variadic_lines.ipynb).

- In tooltip customization API:\
  `disableSplitting()` function [[#189](https://github.com/JetBrains/lets-plot-kotlin/issues/189)].

  [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/tooltips_disable_splitting.ipynb).


- `themeVoid()`:
  [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/theme_void.ipynb)

- [ToDo] `geomFunction()` :
  [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/geom_function.ipynb).

- `statSummary()`:
  [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/stat_summary.ipynb).

- `statSummaryBin()`:
  [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/stat_summary_bin.ipynb).

- In `geomPie()`:
  - `stroke` and `color` aesthetics - the width and color of pie sector arcs.
  - `strokeSide` parameter - which arcs to show (inner, outer, both).
  - `spacerWidth` and `spacerColor` parameters - lines between sectors.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/geom_pie_stroke_and_spacers.ipynb).

  - `sizeUnit` parameter : [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/geom_pie_size_unit.ipynb).

- Flavor-aware colors: **pen**, **brush** and **paper**
  - By default, all geometries utilize new flavor-aware colors.
  - Theme `geom` parameter allows redefinition of "geom colors":  `theme(geom = elementGeom(pen, brush, paper))`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/geom_theme_colors.ipynb).


### Changed

- [BREAKING] Kotlin/JS LEGACY apps are no longer supported.

- [BREAKING] `geomBoxplot()` no longer support parameter `sampling`.

### Fixed
- ggsave: saving geomImshow() to SVG produces fuzzy picture [[#188](https://github.com/JetBrains/lets-plot-kotlin/issues/188)].
