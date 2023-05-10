## [4.3.1] - 2023-mm-dd

### Added

- The 'newline' character (`\n`) now works as `line break` in legend text.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.1/legend_text_multiline.ipynb).
 
- Horizontal error bars and vertical "dodge".

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.1/horizontal_error_bars.ipynb).

- `geomLollipop()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.1/geom_lollipop.ipynb).

- Aesthetic `linewidth` (for `geomLollipop()`) and its scales `scaleLinewidth()`, `scaleLinewidthIdentity()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.1/geom_lollipop.ipynb).

- Aesthetic `stroke` and its scales `scaleStroke()`, `scaleStrokeIdentity()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.1/aes_stroke.ipynb).


### Changed

- [BREAKING] `geomDotplot()` and `geomYDotplot()` no longer support parameter `stat`.


### Fixed

- Support multiple subdirectories in `ggsave` path [[contribution by David Phillips]](https://github.com/JetBrains/lets-plot-kotlin/pull/163).
- `scaleXDiscrete` doesn't make scale discrete [[#165](https://github.com/JetBrains/lets-plot-kotlin/issues/165)].