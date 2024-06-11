# Charts

## Data

Every layer may have some data associated with it.
The "data" refers to a table of data where each row contains an observation
and each column represents a variable that describes some property of each observation.

Data in this format is sometimes referred to as tidy data, flat data, primary data, atomic data, and unit record data.

You can pass tidy data to Lets-Plot in form of a Kotlin `Map`.

## Basic Building Blocks

Points:
[`points`](%api_geom%/geom-point/index.html),
[`jittered points`](%api_geom%/geom-jitter/index.html)

Lines:
[`line`](%api_geom%/geom-line/index.html),
[`path`](%api_geom%/geom-path/index.html),
[`diagonal line`](%api_geom%/geom-a-b-line/index.html),
[`horizontal line`](%api_geom%/geom-h-line/index.html),
[`vertical line`](%api_geom%/geom-v-line/index.html),
[`segment`](%api_geom%/geom-segment/index.html),
[`curve`](%api_geom%/geom-curve/index.html),
[`spoke`](%api_geom%/geom-spoke/index.html),
[`step-function`](%api_geom%/geom-step/index.html)

Areas:
[`area`](%api_geom%/geom-area/index.html),
[`ribbon`](%api_geom%/geom-ribbon/index.html)

Polygons:
[`polygon`](%api_geom%/geom-polygon/index.html),
[`map`](%api_geom%/geom-map/index.html)

Tiles:
[`tiles`](%api_geom%/geom-tile/index.html),
[`rectangles`](%api_geom%/geom-rect/index.html),
[`raster plot`](%api_geom%/geom-raster/index.html)

Text:
[`text`](%api_geom%/geom-text/index.html),
[`label`](%api_geom%/geom-label/index.html)

Examples:

- [Inset map of Kotlin island](%nb-spatialdataset_kotlin_isl%)
- [Drawing graph edges](%nb-graph_edges%)
- [Formatting labels on plots](%nb-formatting_axes_etc%)
- [Variadic lines in `geomPath()` and `geomLine()`](%nb-aes_size_color_variadic_lines%)
- [Spoke geometry](%nb-geom_spoke%)
- [Curve geometry](%nb-geom_curve%)
- [Jittered points](%nb-jittered_points%)
- [Lines in Lets-Plot](%nb-lines%)
- [Ribbon geometry](%nb-ribbon%)
- [How to draw curve fast](%nb-algebraic_curve%)
- [`stroke` aesthetic](%nb-aes_stroke%)


## Discrete

[`bar`](%api_geom%/geom-bar/index.html),
[`pie`](%api_geom%/geom-pie/index.html),
[`lollipop`](%api_geom%/geom-lollipop/index.html),
[`boxplot`](%api_geom%/geom-boxplot.html),
[`count`](%api_geom%/geom-count/index.html)/[`sum`](%api_stat%/stat-sum/index.html)

Examples:

- [Pie](%nb-geom_pie%)
- [Annotation labels on pie-chart](%nb-annotations_for_pie%)
- [Annotated barchart](%nb-bar_annotations%)
- ['boxplot_outlier' statistics](%nb-stat_boxplot_outlier%)
- [`whiskerWidth` parameter of `geomBoxplot()`](%nb-boxplot_whisker_width%)
- [Lollipop plot](%nb-geom_lollipop%)
- [Count stat](%nb-stat_count_2d%)
- [Handling an overplotting on a scatter plot: `geomCount()`/`statSum()`](%nb-geom_count%)
- [Viridis colors](%nb-colors_viridis%)


## Ordering Categories, `asDiscrete()`

[`asDiscrete`](%api_lets_plot%/as-discrete.html)

Examples:

- [Guide to ordering](%nb-ordering_examples%)
- [Factor levels](%nb-factor_levels%)


## Contours

[`contours`](%api_geom%/geom-contour/index.html),
[`filled contours`](%api_geom%/geom-contour-filled/index.html)

Examples:

- [Contours](%nb-contours%)


## Visualization of Distribution

[`histogram`](%api_geom%/geom-histogram/index.html),
[`density`](%api_geom%/geom-density/index.html),
[`dotplot`](%api_geom%/geom-dotplot/index.html),
[`y-dotplot`](%api_geom%/geom-y-dotplot/index.html),
[`violin`](%api_geom%/geom-violin/index.html),
[`ridgeline`](%api_geom%/geom-area-ridges/index.html),
[`frequency polygon`](%api_geom%/geom-freqpoly/index.html)

Examples:

- [Distributions](%nb-distributions%)
- [Dotplot geometry](%nb-geom_dotplot%)
- [Y-Dotplot geometry](%nb-geom_ydotplot%)
- [Violin plot](%nb-geom_violin%)
- [Ridgeline plot](%nb-ridgeline_plot%)
- [The `orientation` parameter](%nb-y_orientation%)


## Stats

[`statECDF()`](%api_stat%/stat-e-c-d-f/index.html),
[`statSummary()`](%api_stat%/stat-summary/index.html),
[`statSummaryBin()`](%api_stat%/stat-summary-bin/index.html)

Examples:

- [ECDF stat](%nb-stat_ecdf%)
- [Summary stat](%nb-stat_summary%)
- [Binned summary stat](%nb-stat_summary_bin%)


## Function

[`function`](%api_geom%/geom-function/index.html)

Examples:

- [Function geometry](%nb-geom_function%)


## Marginal Plots

[`ggmarginal`](%api_lets_plot%/ggmarginal.html)

Examples:

- [Marginal plots](%nb-marginal_layers%)


## Visualization of Errors

[`crossbar`](%api_geom%/geom-crossbar/index.html),
[`errorbar`](%api_geom%/geom-error-bar/index.html),
[`linerange`](%api_geom%/geom-line-range/index.html),
[`pointrange`](%api_geom%/geom-point-range/index.html)

Examples:

- [Plotting means and error ranges](%nb-error_bars%)
- [Horizontal error bars and vertical "dodge"](%nb-horizontal_error_bars%)
- [Geometries with dual orientation](%nb-horizontal_geoms%)


## Smoothing

[`smoothing line`](%api_geom%/geom-smooth/index.html)

Examples:

- [Smoothing](%nb-geom_smooth%)
- [Draw a scatter plot](%nb-scatter_plot%)


## Bivariate Distribution

[`2d bins`](%api_geom%/geom-bin2-d/index.html),
[`2d density`](%api_geom%/geom-density2-d/index.html),
[`filled 2d density`](%api_geom%/geom-density2-d-filled/index.html)

Examples:

- [2d density](%nb-density_2d%)


## Time Series

[`scaleXDatetime()`](%api_scale%/scale-x-date-time.html),
[`scaleYDatetime()`](%api_scale%/scale-y-date-time.html),
[`scaleXTime()`](%api_scale%/scale-x-time.html),
[`scaleYTime()`](%api_scale%/scale-y-time.html)

Examples:

- [Scale time](%nb-scale_time%)


## Images

[`geomImshow()`](%api_geom%/geom-imshow.html)

Examples:

- [`geomImshow()`](%nb-image_101%)
- [The `extent` parameter](%nb-image_extent%)
- [Parameters `norm`, `vmain` and `vmax`](%nb-image_grayscale%)
- ["Fisher boat": `geomImshow()` and raster data](%nb-image_fisher_boat%)


## Coordinate Systems

[`coordCartesian()`](%api_coord%/coord-cartesian.html),
[`coordFixed()`](%api_coord%/coord-fixed.html),
[`coordPolar()`](%api_coord%/coord-polar.html),
[`coordFlip()`](%api_coord%/coord-flip.html),
[`coordMap()`](%api_coord%/coord-map.html)

Examples:

- [Flipped coordinates](%nb-coord_flip%)
- [Polar coordinate system](%nb-coord_polar%)