## [4.3.1] - 2023-mm-dd

### Added

- `stroke` aesthetic for `geomPoint()`, `geomJitter()`, `geomQQ()`, `geomQQ2()`, `geomPointRange()`, `geomDotplot()`,
  `geomYDotplot()` and `outlierStroke` parameter for `geomBoxplot()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.1/aes_stroke.ipynb).


### Changed

### Fixed

- Support multiple subdirectories in `ggsave` path [[contribution by David Phillips]](https://github.com/JetBrains/lets-plot-kotlin/pull/163).
- `scaleXDiscrete` doesn't make scale discrete [[#165](https://github.com/JetBrains/lets-plot-kotlin/issues/165)].