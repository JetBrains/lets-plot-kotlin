## [4.6.1] - 2024-mm-dd

### Added

- `geomCurve()`

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.6.1/geom_curve.ipynb).

- [**UNIQUE**] Visualizing graph-like data with `geomSegment()` and `geomCurve()`.

  - Aesthetics `sizeStart, sizeEnd, strokeStart` and `strokeEnd` enable better alignment of</br>
    segments/curves with nodes of the graph by considering the size of the nodes.

  - The `spacer` parameter allows for additional manual fine-tuning.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.6.1/graph_edges.ipynb).

- `alphaStroke` parameter in `geomLabel()` to enable the applying of `alpha` to `color` [[#1029](https://github.com/JetBrains/lets-plot/issues/1029)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.6.1/geom_label_alpha_stroke.ipynb).


### Changed

### Fixed
