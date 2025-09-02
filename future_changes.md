## [4.11.1] - 2025-09-dd

This release is 100% compatible with Lets-Plot [v 4.7.2](https://github.com/JetBrains/lets-plot/releases/tag/v4.7.2),
GeoTools [v 33.2](https://github.com/geotools/geotools/releases/tag/33.2)

### Added

- Facets Layout:
  - New `strip_spacing`, `strip_spacing_x`, and `strip_spacing_y` parameters in `theme()` to control spacing between the facet strip (title bar) and the plot panel.
  - New `panel_spacing`, `panel_spacing_x`, and `panel_spacing_y` parameters in `theme()` to control spacing between plot panels in faceted plots, [[#1380](https://github.com/JetBrains/lets-plot/issues/1380)].

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.1/facet_spacings.ipynb).


- `ggsave()`:
  - `w`, `h` and `unit` parameters support [[#281]https://github.com/JetBrains/lets-plot-kotlin/issues/281],
    [[#1368](https://github.com/JetBrains/lets-plot/issues/1368)].
  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.1/ggsave_image_size.ipynb).
  
### Changed

- `facetWrap()` now drops factor levels that do not appear in the data (i.e., empty panels) by default [[#1322](https://github.com/JetBrains/lets-plot/issues/1322)]. <br>
  To keep unused factor levels, use the new `drop` parameter, i.e., `drop=false`.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.1/facet_wrap_empty_panels.ipynb).
   

- Facets Layout:
  - Removed extra spacing between facet strips and plot panels in `facetWrap()` and `facetGrid()`.


- `ggsave()`: removed Batik dependency (now uses lets-plot's built-in rasterizer).

### Fixed

- Tooltip for a line have higher priority than points, even if the point is specified first in the layer list [[#1060](https://github.com/JetBrains/lets-plot/issues/1060)].
- `geomRibbon()`: tooltip appears in the wrong place on flipped ribbon [[#1334](https://github.com/JetBrains/lets-plot/issues/1334)].
- Coordinate limits do not work on reversed scales [[#1365](https://github.com/JetBrains/lets-plot/issues/1365)]
- Misaligned axis labels and ticks in polar coordinates.
- Display order of fill categories not being set correctly in stacked plots? [[#1367](https://github.com/JetBrains/lets-plot/issues/1367)]
- Unclear error when using `geomRect` with discrete scales [[#1287](https://github.com/JetBrains/lets-plot/issues/1287)]
- `xlim()` breaks default `scaleXDateTime()` [[#1348](https://github.com/JetBrains/lets-plot/issues/1348)]
- `scaleXReverse` breaks datetime formatting [[#1257](https://github.com/JetBrains/lets-plot/issues/1257)]
- `theme(plotTitle="blank")` doesn't work with `gggrid` [[#1349](https://github.com/JetBrains/lets-plot/issues/1349)]
- theme: error parsing color value pen [[#1216](https://github.com/JetBrains/lets-plot/issues/1216)]
- Incorrect appearance of stacked density plot in polar coordinates [[#1375](https://github.com/JetBrains/lets-plot/issues/1375)]

