# lets-plot-kotlin changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [0.0.26-SNAPSHOT] - 2020-08-04 (Not released)
## Added
- ggsave(): exports a plot to a file. Supported formats: svg, html, png, jpeg, tiff.  

## [0.0.25-SNAPSHOT] - 2020-07-31 (Not released)
### Added
- scale_x_reverse, scale_y_reverse 
- scale_x_sqrt, scale_y_sqrt

## [0.0.24-SNAPSHOT] - 2020-07-27 (Not released)
### Added
- scale_x_log10, scale_y_log10
- scale_alpha

### Changed
- Lets-Plot core version upgraded to 1.5.1-SNAPSHOT

### Fixed 
 - NPE on geom_tile when data contains null-s.
 - The order of values in the `limits` parameter on discrete scales was ignored.

## [0.0.23-SNAPSHOT] - 2020-07-06 (Bundled with Kotlin Kernel)
### Added
- Parameter `expand` in positional scales.
- Demo notebooks (GGBunch, Legends & Axis) 

### Changed
- Lets-Plot core version upgraded to 1.4.4-SNAPSHOT - now without dependencies on Kotlin-test, junit.

### Fixed
- Loading Kotlin-numpy when opening demo notebooks at `mybinder.org`.

## [0.0.22-SNAPSHOT] - 2020-07-01 (Bundled with Kotlin Kernel)
### Added
- as_discrete()

### Changed
- Lets-Plot core version upgraded to 1.4.3-SNAPSHOT
- scale_color_grey(), scale_fill_grey() : arguments `start`, `end` are now in range [0,1] (before was [0,100]).

## [0.0.21-SNAPSHOT] - 2020-06-29 (Not released)
### Added
- scale_shape()

### Changed
- Parameter `mapping` (lambda) has been moved to the last position to allow the value to be outside parentheses.

## [0.0.20-SNAPSHOT] - 2020-06-25
### Added
- facet_grid() 
- coord_fixed()
- labs(), xlab(), ylab()
- lims(), xlim(), ylim()
- Geoms:
    - geom_jitter()
    - geom_bin2d(), stat_bin2d()
    - geom_contour(), stat_contour()
    - geom_contourf()
    - geom_density2d(), stat_density2d()
    - geom_density2df()
    - geom_smooth(), stat_smooth()
    - stat_bin()
- Manual scales:
    - scale_fill_manual(), scale_color_manual()
    - scale_size_manual()
    - scale_shape_manual()
    - scale_linetype_manual()
    - scale_alpha_manual()
- Identity scales:
    - scale_color_identity(), scale_fill_identity()
    - scale_shape_identity()
    - scale_linetype_identity()
    - scale_alpha_identity()
    - scale_size_identity()
- Positional scales:
    - scale_x_continuous(), scale_y_continuous()
    - scale_x_discrete(), scale_y_discrete()
- Brewer color scales:
    - scale_color_brewer(), scale_fill_brewer()
 
### Changed
- theme() is now a fluent interface.

### Fixed:
- theme composition wasn't working.

## [0.0.18-SNAPSHOT] - 2020-06-10
### Added
- Geoms:
    - geom_crossbar()
    - geom_errorbar()
    - geom_freqpoly()
    - geom_linerange()
    - geom_pointrange()
    - geom_polygon()
    - geom_ribbon()
    - geom_step()    
- Standardize input data series as List<*>:
    - Iterable<*>
    - Sequence<*>
    - Array<*>
    - ByteArray
    - ShortArray
    - IntArray
    - LongArray
    - FloatArray
    - DoubleArray
    - CharArray
- Standardize values in the input data series:
    - Date, Instant and ZonedDateTime as the number of milliseconds from the epoch of 1970-01-01T00:00:00Z.
    - Char as string

### Fixed
- geom_density: 
    - defaults: alpha=0, fill=white
    - support `weight` aesthetic

- geom_histogram: added 'bin' parameters: binCount, binWidth, center, boundary.

## [0.0.17-SNAPSHOT] - 2020-06-02
### Changed
- Upgraded Lets-Plot dependency to v.1.4.2. Now bundled with Jupyter Kotlin kernel. 

### Added
- Support for Kotlin kernel in Datalore.
- New initialization parameters: `isolatedFrame` (bool), `apiVersion` (str), `libraryVersion` (str).
- `LetsPlot.showInfo()`.
 

## [0.0.11-SNAPSHOT] - 2020-05-19
### Changed
- Upgrade Lets-Plot Maven artifact dependency to v.1.4.0 (lets-plot-common.jar etc.)
- More slick shape for tooltips on the axis.

### Fixed
 - Severe performance degradation when using discrete scales [[#119](https://github.com/JetBrains/lets-plot/issues/119)].


## [0.0.10-SNAPSHOT] - 2020-03-26 (not published)
### Changed
- Upgrade Lets-Plot Maven artifact dependency to v.1.3.1 (lets-plot-common.jar, lets-plot-jfx.jar)
  
  What's new is 1.3.1:
    - SVG export: function `MonolithicAwt.buildSvgImagesFromRawSpecs` was deprecated and replaced with  
    `PlotSvgExport.buildSvgImageFromRawSpecs`. PlotSvgExport utility now generates single SVG image from `GGBunch` 
    (before it was separate SVG per plot in the bunch).
    - HTML export: New `PlotHtmlExport.buildHtmlFromRawSpecs` utility exports plot as a dynamic HTML page optionally
      wrapped into iframe (see `iFrame` parameter). 
    
    See updated demos in `exportSvgDemo` and `exportHtmlDemo` packages.  
    
    - `lets-plot-common` and `lets-plot-jfx` artifacts are included in [jcenter](https://bintray.com/bintray/jcenter) Maven repository.


## [0.0.9-SNAPSHOT] - 2020-01-21
### Added
- *GGBunch*. Combines several different plots into one graphical object.
