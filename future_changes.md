## [4.11.0] - 2025-mm-dd

This release is 100% compatible with Lets-Plot [v 4.7.0](https://github.com/JetBrains/lets-plot/releases/tag/v4.7.0),
GeoTools [v 33.2](https://github.com/geotools/geotools/releases/tag/33.2)

### Added

- Geometries:
  - `geomTextRepel()` and `geomLabelRepel()` [[#1092](https://github.com/JetBrains/lets-plot/issues/1092)].  
   See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/ggrepel.ipynb).

- Layer Labels (Annotations):
  - Support in `geomCrossbar()`

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/geom_crossbar_annotation.ipynb).
  
- Support in `waterfallPlot()` via `relativeLabels` and `absoluteLabels` parameters.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/waterfall_plot_annotations.ipynb).

  - New `inheritColor()` option in annotations configuration (see example notebooks above)

- `waterfallPlot()` - support for combining waterfall bars with other geometry layers [[#1344](https://github.com/JetBrains/lets-plot/issues/1344)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/waterfall_plot_layers.ipynb).

- Plot Layout:

    - New `axisTextSpacing`, `axisTextSpacingX`, and `axisTextSpacingY` parameters in `theme()` to control spacing between axis ticks and labels.
    - See new [plot layout diagram](https://nbviewer.org/github/JetBrains/lets-plot/blob/master/docs/f-25b/plot_layout_scheme.ipynb) notebook showing various layout options and their effects on plot appearance.

- More variants to specify a color by name:

    - all HTML/CSS colors;
    - various naming styles, e.g., `dark-gray`, `darkgrey`, `dark_grey`, `DARKGRAY`, etc.;
    - grayscale colors from `gray0` (black) to `gray100` (white);

  See [the complete list of named colors](https://lets-plot.org/python/pages/named_colors.html).


### Changed

- Plot layout: reduced margins and spacing for title, caption, axes, and legend.
- Updated RGB values for `lightgray` and `green`. To restore the previous colors, use `gray75` and `lime`, respectively.


### Fixed

- Multiline legend labels were not vertically centered with their keys [[#1331](https://github.com/JetBrains/lets-plot/issues/1331)].
- Poor alignment in legend between columns [[#1332](https://github.com/JetBrains/lets-plot/issues/1332)].

