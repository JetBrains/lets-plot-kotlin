# lets-plot-kotlin changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [1.0.1-dev-1] - 2020-10-07
### Added
 - Parameters `map`, `mapJoin` in `geom_polygon, geom_path, geom_point, geom_text`
 - Converter from `org.geotools.data.simple.SimpleFeatureCollection` to `jetbrains.letsPlot.spatial.SpatialDataset`
 
### Fixed
 - `ggsave()` ignores raster format file extension.
 - `as_discrete()`: if more than one is used in the same mapping block then all but one of them are ignored. 

## [1.0.0] - 2020-08-13
### Added
 - The first public release.

### Changed
 - Maven artifact __group id__ has changed.
    - was before: `org.jetbrains.lets-plot`
    - since now : `org.jetbrains.lets-plot-kotlin` 
