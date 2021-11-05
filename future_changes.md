## [3.0.3] - 2021-??-??

### Added

- `coord_flip()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/coord_flip.ipynb).

- Date-time formatting support:
    - using date-time format pattern in tooltip format();
    - date/time scales apply date-time formatting to the `breaks`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/datetime_formatting.ipynb).

- Pre-configured themes:
  - Standard ggplot2 themes: `themeGrey(), themeLight(), themeClassic(), themeMinimal()`;
  - Other themes: `themeMinimal2()` - the default theme, `themeNone()`.

- Theme modification: more parameters were added to the `theme()` function.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/themes.ipynb).

> Note: fonts size, family and face still can not be configured.

- `corr_plot()` function now also accepts pre-computed correlation coefficients.

### Changed

- The size of fonts on plot was slightly increased all across the board.
- The default plot size was increased by 20%, it's now 600x400 px.
- **Deprecated API**: all `Theme.xxxBlank()` functions. Please use corresponding parameters in `theme()`.


### Fixed

