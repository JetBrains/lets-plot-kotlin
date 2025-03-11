## [4.9.4] - 2025-mm-dd

This release is 100% compatible with Lets-Plot [v 4.5.2](https://github.com/JetBrains/lets-plot/releases/tag/v4.5.2),
GeoTools [v 32.1](https://github.com/geotools/geotools/releases/tag/32.0)

### Added

- Geometries:

  - `geomHex()` [[#556](https://github.com/JetBrains/lets-plot/issues/556)].

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.10.0/geom_hex.ipynb).

  - Parameters `widthUnit` and `heightUnit` in `geomErrorBar()`, `geomBoxplot()`, `geomCrossbar()`, `geomTile()` and `geomHex()` [[#1288](https://github.com/JetBrains/lets-plot/issues/1288)]:

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.10.0/param_width_unit.ipynb).

  - Parameters `start` and `direction` in `geomPie()` [[#1280](https://github.com/JetBrains/lets-plot/issues/1280)].  

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.10.0/geom_pie_params.ipynb).

- Texts and labels:

  - `hjust` and `vjust` parameters for axis labels [[#1227](https://github.com/JetBrains/lets-plot/issues/1227)],[[#1230](https://github.com/JetBrains/lets-plot/issues/1230)].

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.10.0/axis_label_justification.ipynb).
  
  - multiline support for axis labels [[#948](https://github.com/JetBrains/lets-plot/issues/948)].

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.10.0/multiline_axis_labels.ipynb).

  - Markdown support for plot **title**, **subtitle**, **caption**, and axis labels [[#1256](https://github.com/JetBrains/lets-plot/issues/1256)].

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.10.0/markdown.ipynb).

### Changed

- [**BREAKING**] The `height` parameter has been deprecated for the `geomErrorBar`.

- [**BREAKING**] `geomTile`, `geomBin2D`, `geomContour`, `geomContourFilled`, `geomDensity2D`, `geomDensity2DFilled` : default coordinate system changed from 'fixed' to 'cartesian'.

### Fixed
