## [4.1.0] - 2022-09-dd

### Added

- New theme: `themeBW()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/complete_themes.ipynb).

- Color schemes (flavors) applicable to existing themes:
  - `flavorDarcula()`
  - `flavorSolarizedLight()`
  - `flavorSolarizedDark()`
  - `flavorHigh_contrastLight()`
  - `flavorHigh_contrastDark()`

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/theme_flavors.ipynb).

- Viridis color scales: `scaleColorViridis()`, `scaleFillViridis()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/colors_viridis.ipynb).

- New parameters in theme's `elementText()`:
  - `size, family`
    ([example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/font_size_and_family.ipynb))
  - `hjust, vjust` for plot title, subtitle, caption, legend and axis titles
    ([example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/hjust_vjust.ipynb))
  - `margin` for plot title, subtitle, caption, axis titles and tick labels
    ([example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/text_margins.ipynb))

- Parameter `whiskerWidth` in `geomBoxplot()`.
  
  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/boxplot_whisker_width.ipynb).

### Changed

- New tooltip style after applying `coordFlip()`  [[#580](https://github.com/JetBrains/lets-plot/issues/580)].

### Fixed

- Density and area geoms: preserve the z-order when grouping [[#552](https://github.com/JetBrains/lets-plot/issues/552)].
- Boxplot, violin, crossbar: position dodge width=0.95 should be used by default [[#553](https://github.com/JetBrains/lets-plot/issues/553)].
- Unclear size unit of width [[#589](https://github.com/JetBrains/lets-plot/issues/589)].
- No tooltips for `geomBoxplot` with zero height [[#563](https://github.com/JetBrains/lets-plot/issues/563)].
- `geomText`: wrong label alignment with `hjust` 0 and 1 [[#592](https://github.com/JetBrains/lets-plot/issues/592)].
