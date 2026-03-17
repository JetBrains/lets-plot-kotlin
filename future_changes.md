## [4.12.2] - 2026-mm-dd

This release is 100% compatible with Lets-Plot [v 4.8.2](https://github.com/JetBrains/lets-plot/releases/tag/v4.8.2),
GeoTools [v 33.2](https://github.com/geotools/geotools/releases/tag/33.2)

### Added

      
* Plot Annotations:

    * New `labels` parameter in `geomSmooth()` designed to display statistical summaries of the fitted model directly on the plot.
      This parameter accepts a `smoothLabels()` object, which provides access to model-specific variables like $R^2$, the regression equation and others.

      See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.13.0/smooth_summary.html).

* Geometries:

    * New `geomBracket()`, `geomBracketDodge()` [[#1114](https://github.com/JetBrains/lets-plot/issues/1114)].

      See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.13.0/geom_bracket.html).

### Changed


* Changes affecting users on the JVM platform:

    * Upgraded the Kotlin version to 2.2.20 (was 1.9.25).
    * New artifact for JVM Swing applications: `org.jetbrains.lets-plot:lets-plot-swing`.
      This artifact provides the `SwingPlotPanel` class, which can be used to display plots in Swing applications instead of the now-obsolete `DefaultPlotPanelBatik`.
      For details, see the [jvm-swing-app](https://github.com/alshan/lets-plot-mini-apps/tree/main/jvm-swing-app) example in the "lets-plot-mini-apps" repository.
    * [**BREAKING**]: Removed JavaFX artifacts.
      The `org.jetbrains.lets-plot:lets-plot-jfx` artifact is no longer available.
      Replace it with new `org.jetbrains.lets-plot:lets-plot-swing` dependency and use `SwingPlotPanel` instead of `DefaultPlotPanelJfx`.
      For details, see the [jvm-javafx-app](https://github.com/alshan/lets-plot-mini-apps/tree/main/jvm-javafx-app) example in the "lets-plot-mini-apps" repository.
    * [**BREAKING**]: Removed `plot-image-export` module.
      The `org.jetbrains.lets-plot:lets-plot-image-export` artifact is no longer available.
      The `PlotImageExport` utility has been moved to the `platf-awt` module.
      The required `org.jetbrains.lets-plot:platf-awt` dependency is likely already present in your project.

### Fixed

* Drop commons-io dependency [[#1421](https://github.com/JetBrains/lets-plot/issues/1421)].
* Unexpected replacement of double curly brackets with a single curly bracket [[#1433](https://github.com/JetBrains/lets-plot/issues/1433)].
* geom_imshow: unclear error message when mixing transparencies [[#1088](https://github.com/JetBrains/lets-plot/issues/1088)].
* geom_imshow and scale_y_reverse [[#1210](https://github.com/JetBrains/lets-plot/issues/1210)].
* Nice to be able to get a list of colors from a color scale object [[#1444](https://github.com/JetBrains/lets-plot/issues/1444)].
* Allow grouped tooltips for plots with multiple univariate geoms [[#1460](https://github.com/JetBrains/lets-plot/issues/1460)].
* Fixed a regression in `geomTextRepel()` / `geomLabelRepel()`: points with empty labels were incorrectly skipped
  before building the repel obstacle set, so they were not included in collision avoidance and labels could overlap
  dense point clusters.

