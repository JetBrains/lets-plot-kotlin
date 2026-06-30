## [4.15.0] - 2026-06-dd

This release is 100% compatible with Lets-Plot [v 4.11.0](https://github.com/JetBrains/lets-plot/releases/tag/v4.11.0),
GeoTools [v 33.2](https://github.com/geotools/geotools/releases/tag/33.2)

### Added

- `theme()`: new `tooltipMerge` and `tooltipMaxCount` parameters to combine the general tooltips of multiple targets into a single tooltip.

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/master/docs/examples/jupyter-notebooks/f-4.15.0/tooltip_merge.html).

- Text halos improve readability on varied backgrounds. New `haloWidth` and `haloColor` parameters are supported in `geomText()`/`geomTextRepel()` and by labels in `corrPlot()`.

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/master/docs/examples/jupyter-notebooks/f-4.15.0/text_halo.html).

- `SpatialDataset` objects can be displayed as compact preview tables in Kotlin notebooks [[LPK-274](https://github.com/JetBrains/lets-plot-kotlin/issues/274)].

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/master/docs/examples/jupyter-notebooks/f-4.15.0/spatial_dataset_presentation.html).

### Changed

### Fixed

- Incorrect `hjust`/`vjust`/`angle` justification of vertical-axis tick labels (`theme(axisTextY=elementText(...))`).
- Rotated vertical-axis tick labels could be wrongly dropped or kept by overlap-based break filtering.
- Misaligned or mismatched axis tick labels when some breaks are dropped after layout.
