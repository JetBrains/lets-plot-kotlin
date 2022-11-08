## [4.1.1] - 2022-mm-dd

### Added

- `geomText(), geomLabel()`:

  - the 'newline' character (`\n`) now works as `line break` 
  - `lineheight` aesthetic
  - `nudgeX, nudgeY` parameters
  - special text alignments (`vjust` and `hjust`): `"inward"` and `"outward"`
    
  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_text_and_label_new_features.ipynb).

- `vjust` parameter in `positionStack()` and `positionFill()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/position_stack.ipynb).

### Changed

- Deprecated API:
  - `positionStack`, new usage: `positionStack()`
  - `positionFill`, new usage: `positionFill()`

- Upgraded Kotlin version to 1.7.20 (was 1.6.21).                    
- Upgraded Lets-Plot version to 2.5.1 (was 2.5.0).

  See Lets-Plot [What is new in 2.5.1](https://github.com/JetBrains/lets-plot#what-is-new-in-251)
  for more details.

### Fixed

