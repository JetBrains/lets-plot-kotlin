# lets-plot-kotlin changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

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
