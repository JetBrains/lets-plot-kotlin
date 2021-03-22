# lets-plot-kotlin changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres
to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.3.0] - 2021-03-22

### Added

- `facet_wrap()` function.
- in `facet_grid()` function:
  - Ascending/descending ordering of faceting values.
  - Formatting of faceting values.

  See: [Facets demo](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/facets.ipynb)

- The `format` parameter in positional scales: formatting tick labels on X/Y axis. Supported types are `number`
  and `date/time`.

  Example:
   ```
   scale_x_datetime(format="%b %Y")
   scale_x_continuous(format="is {.2f}")
   ```

  Demo: [Formatting demo](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/formatting_axes_etc.ipynb)

  See also: [Formatting](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)


- The `guides()` function [[#52](https://github.com/JetBrains/lets-plot/issues/52)].

- In tooltip customization API:
  - option `color` overrides the default tooltip color:
      ```
      geom_xxx(tooltips=layer_tooltips().color("red"))
      ```

  See: [Tooltip Customization](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/tooltips.md).

### Changed

- Built with Lets-Plot v2.0.1 (was v1.5.6).
- All Java Swing demos were updated to use new `plot components` (new in Lets-Plot v2.0.1).
- The "Minimal demo" was updated:
  - [`Main.kt` (Batik)](https://github.com/JetBrains/lets-plot-kotlin/tree/master/demo/jvm-batik/src/main/kotlin/minimalDemo)
  - [`Main.kt` (JavaFX)](https://github.com/JetBrains/lets-plot-kotlin/tree/master/demo/jvm-javafx/src/main/kotlin/minimalDemo)

### Fixed

- `show()` should actually show a plot [[#51](https://github.com/JetBrains/lets-plot-kotlin/issues/51)]
- Facet grid truncated in jupyter [[#28](https://github.com/JetBrains/lets-plot-kotlin/issues/28)].

The majority of fixes in the core Lets-Plot vv 2.0.0, 2.0.1 are also applicable to this release.

See the Lets-Plot [CHANGELOG.md](https://github.com/JetBrains/lets-plot/blob/master/CHANGELOG.md).

## [1.2.0] - 2021-01-15

### Added

- Correlation plot builder.

  Example: [correlation_plot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/correlation_plot.ipynb)

- The `gggrid()` plot layout utility

  Example: [correlation_plot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/correlation_plot.ipynb)

- In tooltip customization API:
    - options: `center` and `middle` (anchor).
    - option 'minWidth'.

  Example: [tooltip_config.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/tooltip_config.ipynb)

- The `naText` parameter in `geom_text()`

### Changed

- Built with Lets-Plot v1.5.6 (was v1.5.4).

  See Lets-Plot [CHANGELOG.md](https://github.com/JetBrains/lets-plot/blob/master/CHANGELOG.md) for changes and fixes in
  1.5.5, 1.5.6.

## [1.1.0] - 2020-11-20

### Added

- GeoTools support (see [geotools.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/geotools.md)).
- API for tooltip customization (
  see [tooltips.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/tooltips.md)).
- `geom_map()`.
- `labelFormat` parameter in `geom_text()`.
- `reverse` parameter in `scale_x/y_discrete()`.
- `scale_x_discrete_reversed()`, `scale_y_discrete_reversed()`

### Changed
 - Built with Lets-Plot v1.5.4 (was v1.5.2).
  
    See Lets-Plot [CHANGELOG.md](https://github.com/JetBrains/lets-plot/blob/master/CHANGELOG.md) for changes and fixes in 1.5.3, 1.5.4.
 
### Fixed
 - `ggsave()` ignores raster format file extension.
 - `as_discrete()`: if more than one is used in the same mapping block, then all but one of them are ignored. 

## [1.0.0] - 2020-08-13
### Added
 - The first public release.

### Changed
 - Maven artifact __group id__ has changed.
    - was before: `org.jetbrains.lets-plot`
    - since now : `org.jetbrains.lets-plot-kotlin` 
