# lets-plot-kotlin changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [1.1.0] - 2020-11-xx
### Added
 - GeoTools support (see [geotools.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/geotools.md)).
 - API for tooltip customization (see [tooltips.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/tooltips.md)).
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
