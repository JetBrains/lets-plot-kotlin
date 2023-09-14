## [4.4.3] - 2023-mm-dd

### Added

- `geomCount()`/`statSum()` [[#821](https://github.com/JetBrains/lets-plot/issues/821)].  
  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.3/geom_count.ipynb).
-  `plotMessage` parameter in `theme(...)`  [[#863](https://github.com/JetBrains/lets-plot/issues/863)].  
  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.3/theme_plot_message.ipynb).

### Changed
- If layer transparency is set via the alpha-channel in the colors RGBA specification and via the `alpha` aesthetic, \
  then the `alpha` aesthetic overrides the alpha-channel in the color. Previousely it was the opposite.


- `geomPie()` defaults:
  - "stroke" is visible and `strokeSide="both"` (was `stroke_side="outer"`).
  - the "hole" is not created automatically when `stroke_side = "both"/"inner"` (was created automatically).

- `geomBar()` now has solid outline color by default (was transparent).

- `geomTile()`, `geomBin2d()` now have solid outline color by default (was transparent).
  - however, by default the `size` is 0 (i.e. tiles outline initially is not visible).

### Fixed

- `themeVoid()` + `flavorXxx()`: no expected plot background [[#858](https://github.com/JetBrains/lets-plot/issues/858)].
- `geomTile()`, `geomBin2d()` : the `alpha` aesthetic is applied to the tiles outline.
- `scaleXDateTime()`: error building plot for early dates [[#346](https://github.com/JetBrains/lets-plot/issues/346)].
- Inconsistent color in legend when using `paint_a/paint_b/paint_c` [[#867](https://github.com/JetBrains/lets-plot/issues/867)].
