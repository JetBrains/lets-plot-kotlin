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

### Fixed