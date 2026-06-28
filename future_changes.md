## [4.14.2] - 20yy-mm-dd

This release is 100% compatible with Lets-Plot [v 4.10.3](https://github.com/JetBrains/lets-plot/releases/tag/v4.10.3),
GeoTools [v 33.2](https://github.com/geotools/geotools/releases/tag/33.2)

### Added

- `theme()`: new `tooltipMerge` and `tooltipMaxCount` parameters to combine the general tooltips of multiple targets into a single tooltip.

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/master/docs/examples/jupyter-notebooks/f-4.15.0/tooltip_merge.html).

- Text halos improve readability on varied backgrounds. New `haloWidth` and `haloColor` parameters are supported in `geomText()`/`geomTextRepel()` and by labels in `corrPlot()`.

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/master/docs/examples/jupyter-notebooks/f-4.15.0/text_halo.html).

### Changed

### Fixed
