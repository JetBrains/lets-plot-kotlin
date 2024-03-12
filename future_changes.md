## [4.6.1] - 2024-mm-dd

### Added
- `coordPolar()`

  The polar coordinate system is most commonly used for pie charts, but </br>
  it can also be used for constructing **Spyder or Radar charts** using the `flat` option.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.6.1/coord_polar.ipynb).

- In the `theme()` function:
  - `panelInset`  parameter - primarily used for plots with polar coordinates.

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.6.1/theme_panel_inset.ipynb).

  - `panelBorderOntop` parameter - enables the drawing of panel border on top of the plot geoms.
  - `panelGridOntop, panelGridOntopX, panelGridOntopY` parameters - enable the drawing of grid lines on top of the plot geoms.


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
