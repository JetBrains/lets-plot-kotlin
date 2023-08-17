## [4.4.2] - 2023-mm-dd

### Added

- Support for variadic line width and/or color in `geom_line()` and `geom_path()` [[LP-313](https://github.com/JetBrains/lets-plot/issues/313)].

  [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/aes_size_color_variadic_lines.ipynb).

- In tooltip customization API:\
  `disableSplitting()` function [[#189](https://github.com/JetBrains/lets-plot-kotlin/issues/189)].

  [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/tooltips_disable_splitting.ipynb).


-ToDo:    themeVoid()             

### Changed

- [BREAKING] Kotlin/JS LEGACY apps are no longer supported.

### Fixed
- ggsave: saving geomImshow() to SVG produces fuzzy picture [[#188](https://github.com/JetBrains/lets-plot-kotlin/issues/188)].
