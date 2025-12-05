# Lets-Plot Kotlin API Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres
to [Semantic Versioning](https://semver.org/spec/v2.0.0.html). All scales should have the 'format' parameter.

## [4.12.0] - 2025-12-02

This release is 100% compatible with Lets-Plot [v 4.8.1](https://github.com/JetBrains/lets-plot/releases/tag/v4.8.0),
GeoTools [v 33.2](https://github.com/geotools/geotools/releases/tag/33.2)

### Added

- Geometries:
    - `geomPointDensity()` [[#1370](https://github.com/JetBrains/lets-plot/issues/1370)].

      See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/geom_pointdensity.html).

    - Geoms with 1-to-1 statistics (such as `geomQQ()`, `geomSina()`) preserve the mapping to original data after statistical transformation.

      See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/stat_data_bijection.html).

    - `geomHistogram()`: custom bin bounds (parameter `breaks`) [[#1382](https://github.com/JetBrains/lets-plot/issues/1382)].

      See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/geom_histogram_param_breaks.html).

- Plot Layout:
    - The legend automatically wraps to prevent overlap - up to 15 rows for vertical legends and 5 columns for horizontal ones [[#1235](https://github.com/JetBrains/lets-plot/issues/1235)].

      See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/legend_wrap.html).

    - `gggrid()`: support for shared legends (parameter `guides`).

      See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/gggrid_legend_collect.html).

- Plot Theme:
    - `flavorStandard()` sets the theme's default color scheme [[#1277](https://github.com/JetBrains/lets-plot/issues/1277)]. <br>
      Use to override other flavors or to make defaults explicit.

      See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/flavor_standard.html).

    - `themeGray()` as an alias for `themeGrey()`.

    - New `theme` functions for setting legend justification: `legendJustificationTop()`, `legendJustificationRight()`, <br>
      `legendJustificationBottom()`, and `legendJustificationLeft()`.

      See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/legend_justification.html).

    - Support for inward axis ticks.

      See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/axis_tick_direction.html).

- Markdown:
    - Support for `target` attribute for links.
    - Links now open in a new tab by default [[#1397](https://github.com/JetBrains/lets-plot/issues/1397)].

- `ggtb()`: `sizeZoomin` and `sizeBasis` parameters for geometry scaling [[#1369](https://github.com/JetBrains/lets-plot/issues/1369)].

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/ggtb_size_zoomin.html).

- New `output` parameter for the library descriptor to control output types stored in notebook cells [[LPK-277](https://github.com/JetBrains/lets-plot-kotlin/issues/277)].\
  Thanks to [AndreiKingsley](https://github.com/AndreiKingsley) for the contribution!
  ```
    %use lets-plot(output="js, png")
  ```

  Available output types:
    - `js` - Classic Web output: HTML+JS
    - `ktnb` - Kotlin Notebook Swing-based rendering
    - `svg` - Static SVG output
    - `png` - Static PNG output

  Default: `"js, ktnb, svg"`

  **Note:** Static images (SVG/PNG) are hidden when `js` or `ktnb` outputs are present, and only displayed in environments where JavaScript is not executed (e.g., GitHub).

  This option can be helpful when a Kotlin Notebook file size becomes a problem. \
  For example, when working with large datasets where plot interactivity is not a priority, storing only static output (SVG or PNG) can significantly reduce file size.

- [`CenteredPlotPanel`](https://github.com/JetBrains/lets-plot/blob/master/platf-awt/src/main/kotlin/org/jetbrains/letsPlot/awt/plot/component/CenteredPlotPanel.kt) helper class for displaying plots centered in Swing applications.

### Changed

- [**BREAKING**] Explicit `group` aesthetic now overrides default grouping behavior instead of combining with it [[#1401](https://github.com/JetBrains/lets-plot/issues/1401)].

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/group_override_defaults.html).

> [!IMPORTANT]
> Previously, setting `group="variable"` would group by both the explicit variable AND any discrete
> aesthetics (color, shape, etc.). \
> Now it groups ONLY by the explicit variable, matching `ggplot2` behavior. \
> Use `group=listOf(var1, var2, ...)` to group by multiple variables explicitly, \
> and `group=emptyList<Any>()` to disable any grouping.

- Missing values in `geomLine(), geomPath(), geomRibbon()`, and `geomArea()` create gaps in geometries instead of being interpolated over [[LPK-269](https://github.com/JetBrains/lets-plot-kotlin/issues/269)],[[#818](https://github.com/JetBrains/lets-plot/issues/818)], [[#1406](https://github.com/JetBrains/lets-plot/issues/1406)].

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/missing_values_line_path_area_ribbon.html).

- `theme`: the `exponentFormat` default value changed to `"pow"` - superscript powers of 10 (was e-notation).

- The multi-layer line plot now shows tooltips for each series simultaneously, in the same way that a single-layer plot with color mapped to series does.


### Fixed

- Group by multiple columns [[LPK-136](https://github.com/JetBrains/lets-plot-kotlin/issues/136)].
- `geomPie` on geospatial plot with `mapJoin` failes to render without explicit `group` aesthetic.
- `geomDensity2D`: NullPointerException when weight aesthetic contains None values [[#1399](https://github.com/JetBrains/lets-plot/issues/1399)].
- Tooltip shows duplicate lines when as_discrete is applied twice to the same var [[#1400](https://github.com/JetBrains/lets-plot/issues/1400)].
- `geomSina`: incorrect shape in legend [[#1403](https://github.com/JetBrains/lets-plot/issues/1403)].
- `geomDensity2D`: Incorrect processing of weighted statistics when None value occurs in the x or y column.
- `facetWrap`: indescriptive error when the specified facet variable is not present in the dataset [[#1409](https://github.com/JetBrains/lets-plot/issues/1409)].
- Integer numbers in facet strip titles are displayed as float [[#1386](https://github.com/JetBrains/lets-plot/issues/1386)].
- Error when using `scaleIdentity(aesthetic="shape")` [[#1212](https://github.com/JetBrains/lets-plot/issues/1212)].
- `ggsave`: theme option `face="italic"` doesn't work [[#1391](https://github.com/JetBrains/lets-plot/issues/1391)].
- Fail early if string format is incorrect [[#1410](https://github.com/JetBrains/lets-plot/issues/1410)].
- `statECDF()` takes a very long time for even moderately sized datasets [[#1424](https://github.com/JetBrains/lets-plot/issues/1424)].
- inconsistencies in theme/flavor inheritance in `gggrid()` subplots.


## [4.11.2] - 2025-09-12

This release is 100% compatible with Lets-Plot [v 4.7.3](https://github.com/JetBrains/lets-plot/releases/tag/v4.7.3),
GeoTools [v 33.2](https://github.com/geotools/geotools/releases/tag/33.2)

### Changed

- `ggsave()`: large plot dimensions without units now require explicit `unit` specification. <br>
  When plot size exceeds 20 without specifying units (e.g., `ggsave(p, w=300, h=400)`), <br>
  we ask to specify units explicitly: <br>
  `ggsave(p, w=300, h=400, unit="px")` or `ggsave(p, w=3, h=4, unit="in")`.

### Fixed

- `ggtb()` support in Swing/Batik frontend [[LPK-265](https://github.com/JetBrains/lets-plot-kotlin/issues/265)].
- Multiline support for axis labels in polar coordinates.
- When the plot size in `ggsave()` is specified in pixels, `dpi` now affects <br>
  only the physical size, not the pixel dimensions as before.
- Blocking `SwingUtilities.invokeAndWait()`  call on plot image export (JVM app)

### Internal Memo

Lets-Plot VL spec transformer removed.


## [4.11.1] - 2025-09-02

This release is 100% compatible with Lets-Plot [v 4.7.2](https://github.com/JetBrains/lets-plot/releases/tag/v4.7.2),
GeoTools [v 33.2](https://github.com/geotools/geotools/releases/tag/33.2)

### Added

- Facets Layout:
    - New `strip_spacing`, `strip_spacing_x`, and `strip_spacing_y` parameters in `theme()` to control spacing between the facet strip (title bar) and the plot panel.
    - New `panel_spacing`, `panel_spacing_x`, and `panel_spacing_y` parameters in `theme()` to control spacing between plot panels in faceted plots, [[#1380](https://github.com/JetBrains/lets-plot/issues/1380)].

      See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.1/facet_spacings.ipynb).


- `ggsave()`:
    - `w`, `h` and `unit` parameters support [[#281](https://github.com/JetBrains/lets-plot-kotlin/issues/281)],
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


## [4.11.0] - 2025-07-25

This release is 100% compatible with Lets-Plot [v 4.7.0](https://github.com/JetBrains/lets-plot/releases/tag/v4.7.0),
GeoTools [v 33.2](https://github.com/geotools/geotools/releases/tag/33.2)

### Added

- Time Series Plotting [[#278](https://github.com/JetBrains/lets-plot-kotlin/issues/278)],
  [[discussion](https://github.com/JetBrains/lets-plot-kotlin/discussions/92#discussioncomment-12976040)],
  [[#678](https://github.com/JetBrains/lets-plot/issues/678)],
  [[LPK-129](https://github.com/JetBrains/lets-plot-kotlin/issues/129)]:
  - Support temporal data types from `kotlinx.datetime`, `java.time`, and `java.util`.
  - Support for timezone-aware `java.time.ZonedDateTime` and `java.time.OffsetDateTime` objects.

  See examples: 
    - [Date-time cookbook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/time_date_datetime.ipynb)
    - [Bitcoin trading](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/demo/trading_chart.ipynb).

- Geometries:

  - `geomSina()` [[#1298](https://github.com/JetBrains/lets-plot/issues/1298)].

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/geom_sina.ipynb).

  - `geomTextRepel()` and `geomLabelRepel()` [[#1092](https://github.com/JetBrains/lets-plot/issues/1092)].

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/ggrepel.ipynb).

- Layer Labels (Annotations):

  - Support in `geomCrossbar()`

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/geom_crossbar_annotation.ipynb).

  - Support in `waterfallPlot()` via `relativeLabels` and `absoluteLabels` parameters.

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/waterfall_plot_annotations.ipynb).

  - New `inheritColor()` option in annotations' configuration (see example notebooks above)

- `waterfallPlot()` - support for combining waterfall bars with other geometry layers [[#1344](https://github.com/JetBrains/lets-plot/issues/1344)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/waterfall_plot_layers.ipynb).

- Plot Layout:

  - New `axisTextSpacing`, `axisTextSpacingX`, and `axisTextSpacingY` parameters in `theme()` to control spacing between axis ticks and labels.
  - See new [Plot Layout Diagrams](https://lets-plot.org/kotlin/presentation-options.html#plot-layout-diagrams) showing various layout options and their effects on plot appearance.

- More variants to specify a color by name:

  - all HTML/CSS colors;
  - various naming styles, e.g., `"dark-gray"`, `"darkgrey"`, `"dark_grey"`, `"DARKGRAY"`, etc.;
  - grayscale colors from `"gray0"` (black) to `"gray100"` (white);

  See [the complete list of named colors](https://lets-plot.org/kotlin/named-colors.html).

- `sizeUnit` parameter in `geomPoint()`, `geomText/Label()` and `geomPie()` accepts two new values: `"min"` and `"max"` [[#260](https://github.com/JetBrains/lets-plot/issues/260)].

  `"min"` sets the size unit to the smaller of the unit steps along the x and y axes, while `"max"` sets it to the larger. <br>
  This allows for more flexible relative sizing of points, pies and text in plots.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/size_unit_min_max.ipynb).


### Changed

- Continuous data on discrete scales:

  Continuous data when used with discrete positional scales is no longer transformed to discrete data. <br>
  Instead, it remains continuous, allowing for precise positioning of continuous elements relative to discrete ones. <br>
  This resolves issues where combining discrete and continuous data in the same plot was difficult or impossible: [[#1279](https://github.com/JetBrains/lets-plot/issues/1279)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/numeric_data_on_discrete_scale.ipynb).

> [!TIP]
> New way of handling continuous data on discrete scales could potentially break existing plots.
> If you want to restore a broken plot to its original form, you can use the [`asDiscrete()`](https://lets-plot.org/kotlin/as-discrete.html) function to annotate continuous data as discrete.

- [**BREAKING**] `geomBoxplot()`: when y-oriented, it now uses aesthetics `xlower`/`xmiddle`/`xupper` instead of  `lower`/`middle`/`upper` [[#1319](https://github.com/JetBrains/lets-plot/issues/1319)].
- [**BREAKING**] `waterfallPlot()`: special "flow_type" value for `label=elementText(color=...)` replaced with "inherit". See `label` in the [documentation](https://lets-plot.org/kotlin/api-reference/-lets--plot--kotlin/org.jetbrains.letsPlot.bistro.waterfall/waterfall-plot.html).
- [**DEPRECATED**] The `positionDodgeV()` function and the `"dodgev"` value for the `position` parameter are deprecated and will be removed in future releases.
- Plot layout: reduced margins and spacing for title, caption, axes, and legend.
- Updated RGB values for `"lightgray"` and `"green"`. To restore the previous colors, use `"gray75"` and `"lime"`, respectively.
- `waterfallPlot()`: the appearance of the legend has been improved.
- `geomViolin()`: tooltips are not shown in the centerline of the violin if `showHalf != 0`.
- `geomCrossbar()`: the midline is not shown in the legend when `fatten` is set to 0, or when there is no mapping for it.
- `geomPointrange()`: the midpoint will not be drawn if the `y` aesthetic is set to `null`.
- `geomBand()`: the `alpha` aesthetic only affects the inner part of the geometry, as in `geomRect()`.
- `geomBand()`: show tooltip over the whole band, not just at the edges.


### Fixed

- AWT: plot prevents wheel events from bubbling up to the parent component.
- `geomBoxplot`: unable to draw a y-oriented plot with `stat = Stat.identity` [[#1319](https://github.com/JetBrains/lets-plot/issues/1319)].
- Can't add a layer which uses continuous data to a plot where other layers use discrete input [[#1323](https://github.com/JetBrains/lets-plot/issues/1323)].
- Multiline legend labels were not vertically centered with their keys [[#1331](https://github.com/JetBrains/lets-plot/issues/1331)].
- Poor alignment in legend between columns [[#1332](https://github.com/JetBrains/lets-plot/issues/1332)].
- Ordered data was re-ordered by `geomBoxplot` [[#1342](https://github.com/JetBrains/lets-plot/issues/1342)].
- Sec: CVE-2024-47554 (commons-io) [[#1231](https://github.com/JetBrains/lets-plot/issues/1231)]
- java.util.zip.ZipException: duplicate entry: letsPlotKotlinAPI/version.properties [[#279](https://github.com/JetBrains/lets-plot-kotlin/issues/279)]


## [4.10.0] - 2025-03-20

This release is 100% compatible with Lets-Plot [v 4.6.1](https://github.com/JetBrains/lets-plot/releases/tag/v4.6.1),
GeoTools [v 32.1](https://github.com/geotools/geotools/releases/tag/32.1)

### Added

- Grouping plots:
  - `ggbunch()` function: combining plots with custom layout.

    See demos:
    - [Magnifier Inset Effect](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.10.0/magnifier_inset.ipynb).
    - [Map of Kotlin Island with Inset](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.10.0/ggbunch_kotlin_isl.ipynb).

  - Support for plot **title, subtitle, caption, margins** and **insets** in `gggrid()` and `ggbunch()`.

- Geometries:
  - `geomHex()` [[#556](https://github.com/JetBrains/lets-plot/issues/556)].

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.10.0/geom_hex.ipynb).

  - Parameters 
    - `widthUnit` in `geomErrorBar()`, `geomBoxplot()`, `geomCrossbar()` [[#1288](https://github.com/JetBrains/lets-plot/issues/1288)].
    - `widthUnit` and `heightUnit` in `geomTile()` and `geomHex()`.

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.10.0/param_width_unit.ipynb).

  - Parameters `start` and `direction` in `geomPie()` [[#1280](https://github.com/JetBrains/lets-plot/issues/1280)].

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.10.0/geom_pie_params.ipynb).

- Texts and labels:
  - `hjust` and `vjust` parameters for axis labels [[#1227](https://github.com/JetBrains/lets-plot/issues/1227)],[[#1230](https://github.com/JetBrains/lets-plot/issues/1230)].

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.10.0/axis_label_justification.ipynb).

  - Multiline support for axis labels [[#948](https://github.com/JetBrains/lets-plot/issues/948)].

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.10.0/multiline_axis_labels.ipynb).

  - Markdown support for plot **title**, **subtitle**, **caption**, and axis labels [[#1256](https://github.com/JetBrains/lets-plot/issues/1256)].

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.10.0/markdown.ipynb).
  
- In the `nudge` position adjustment -
  the ability to specify absolute shift values for `nudge`:
  - `unit` parameter in `positionNudge()`.
  - `nudgeUnit` parameter in `geomText()` and `geomLabel()`

  See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.10.0/position_nudge_unit.ipynb).

- Theme:
  - Parameters `legendTicks` and `legendTicksLength` for fine-grained control over colorbar tick marks [[#1262](https://github.com/JetBrains/lets-plot/issues/1262)].

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.10.0/theme_legend_ticks.ipynb).

- Aliases for transparent color: `""` (blank string), `"blank"` and `"transparent"` color [[#1281](https://github.com/JetBrains/lets-plot/issues/1281)].

### Changed

- [**DEPRECATED**] class `GGBunch` is deprecated. Please use `ggbunch()` function instead.
- [**BREAKING**] The `height` parameter has been deprecated for the `geomErrorBar`.
- Axis breaks: the minimum distance between axis labels was reduced to avoid unsuitable layouts.
- Axis breaks: changed default `lower_exp_bound` to -7 and `upper_exp_bound` to 6 (same as in `theme(...)`).
- Axis labels: changed default justification for rotated labels.
- Axis labels: changed orientation of automatic vertical labels.
- [**BREAKING**] geoms `tile, bin2d, contour, contourf, density2d, density2df` : default coordinate system changed from 'fixed' to 'cartesian'.
- [**BREAKING**] tiles in `geomBin2d()` are sized according to the `binwidth` value, not the maximum possible.
- [**BREAKING**] In Lets-Plot JS API v 4.6.0 (only Lets-Plot/JS users): signature of "buildPlotFromXXX" functions changed, see current Kotlin/JS declarations:
  - [`buildPlotFromRawSpecs()`](https://github.com/JetBrains/lets-plot/blob/38d0b24ecb3c74420814da8a66a91819d84bedd7/js-package/src/jsMain/kotlin/MonolithicJs.kt#L81)
  - [`buildPlotFromProcessedSpecs()`](https://github.com/JetBrains/lets-plot/blob/38d0b24ecb3c74420814da8a66a91819d84bedd7/js-package/src/jsMain/kotlin/MonolithicJs.kt#L158)
- [**BREAKING**] In Lets-Plot v 4.6.0, Maven artifacts:
  - artifacts `platf-awt`, `platf-batik`, `platf-jfx-swing` are no longer published with a "-jvm" suffix.
    Before, these artifacts could be used in dependencies either with or without the "-jvm" suffix. Now only without suffix.
  - artifact "org.jetbrains.lets-plot:deprecated-in-v4-jvm" is removed.

### Fixed
- Incorrectly rendered Area chart [[#1295](https://github.com/JetBrains/lets-plot/issues/1295)].
- Tooltip should not cover and hide the geometry that it provides info for [[#1275](https://github.com/JetBrains/lets-plot/issues/1275)].
- General purpose `scaleContinuous`: can't use the `expand` parameter [[#1285](https://github.com/JetBrains/lets-plot/issues/1285)].
- Error when using `stat='summary'` if the data contains NaN values [[#1301](https://github.com/JetBrains/lets-plot/issues/1301)].
- Broken `plotBackground` in `gggrid` [[#1124](https://github.com/JetBrains/lets-plot/issues/1124)].
- `plotBackground` not inheriting from `rect` [[#1278](https://github.com/JetBrains/lets-plot/issues/1278)]
- `gggrid`: allow title and other labels for the entire figure [[#715](https://github.com/JetBrains/lets-plot/issues/715)].
- `ggbunch`: overall title [[#321](https://github.com/JetBrains/lets-plot/issues/321)].
- Expand discrete axis according to tile size with `geomTile` [[#1284](https://github.com/JetBrains/lets-plot/issues/1284)].


## [4.9.3] - 2024-12-16

This release is 100% compatible with Lets-Plot [v 4.5.2](https://github.com/JetBrains/lets-plot/releases/tag/v4.5.2),
GeoTools [v 32.1](https://github.com/geotools/geotools/releases/tag/32.0)

### Changed

- Number formatting:
  - We have aligned our specifications with D3.js (rather than Python): `g` format type with `~` (e.g. `~g`) now truncates trailing zeros, and without `~` it doesn't.
  - The default number formatter now respects `theme(exponent_format=...)` settings and does not use 's' format type for large numbers.
- Legends with more than 200 items are now not rendered.

### Fixed

- Bad precision in the default tooltip format when using coord limits [[#1134](https://github.com/JetBrains/lets-plot/issues/1134)].
- Display integer values without fractional part in tooltips [[#1186](https://github.com/JetBrains/lets-plot/issues/1186)].
- Suboptimal tooltip positioning in facets [[#1187](https://github.com/JetBrains/lets-plot/issues/1187)].
- Incorrect Y-axis layout with facets and panel_inset [[#1194](https://github.com/JetBrains/lets-plot/issues/1194)].
- Grouped plot hits the limit of 1000 and doesn't render with not-friendly exception [[#1224](https://github.com/JetBrains/lets-plot/issues/1224)].
- Kandy toPNG reports NullPointerException [[#1228](https://github.com/JetBrains/lets-plot/issues/1228)]
- Wrong formatting when type='g' for small values [[#1238](https://github.com/JetBrains/lets-plot/issues/1238)].
- Formatting when type='g' for large values throws exception [[#1239](https://github.com/JetBrains/lets-plot/issues/1239)].
- Wrong formatting when type='s' with explicit precision [[#1240](https://github.com/JetBrains/lets-plot/issues/1240)].
- Extra trim in formatted number when type='g' [[#1241](https://github.com/JetBrains/lets-plot/issues/1241)].
- Axis breaks are badly formatted if explicitly set [[#1245](https://github.com/JetBrains/lets-plot/issues/1245)].
- Badly formatted zero break for the "~g" format [[#1246](https://github.com/JetBrains/lets-plot/issues/1246)].
- How to adjust the vertical position of geom_text when using position_dodge [[#1248](https://github.com/JetBrains/lets-plot/issues/1248)].
- Incorrect result for format(9.999, ".2f") [[#1251](https://github.com/JetBrains/lets-plot/issues/1251)].
- Tooltips overlapping when not enough vertical space for them [[#1254](https://github.com/JetBrains/lets-plot/issues/1254)].
- Plot limited to 1000 lines (Internal error: IllegalStateException : Too many groups: 1099) [[#1261](https://github.com/JetBrains/lets-plot/issues/1261)].
- Nice to have tooltip to inherit formatting configured for axis [[LPK-229](https://github.com/JetBrains/lets-plot-kotlin/issues/229)].
     

## [4.9.2] - 2024-11-19

Same as 4.9.1, added missing Maven artifacts.

## [4.9.1] - 2024-11-18

This release is 100% compatible with Lets-Plot [v 4.5.1](https://github.com/JetBrains/lets-plot/releases/tag/v4.5.1),
GeoTools [v 32.1](https://github.com/geotools/geotools/releases/tag/32.0)

### Changed

- Migrated to new Kotlin Jupyter integration.


## [4.9.0] - 2024-11-01

This release is 100% compatible with Lets-Plot [v 4.5.1](https://github.com/JetBrains/lets-plot/releases/tag/v4.5.1).

### Added

- `ggtb()`: enable **zoom/pan** interactivity on plot [[#38](https://github.com/JetBrains/lets-plot-kotlin/issues/38)],[[#983](https://github.com/JetBrains/lets-plot/issues/983)],[[#1019](https://github.com/JetBrains/lets-plot/issues/1019)]

  See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.9.0/interact_pan_zoom.ipynb).

- Interactive **links** in tooltips/labels/texts [[#1091](https://github.com/JetBrains/lets-plot/issues/1091)].

  See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.9.0/lp_verse.ipynb).

- Formatting:
  - **LaTeX** support: superscript, subscript ([[#861](https://github.com/JetBrains/lets-plot/issues/861)]) and Greek letters ([[#960](https://github.com/JetBrains/lets-plot/issues/960)]).

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.9.0/latex_support.ipynb).

  - Scientific notation: **compact form**. Enable the compact form using the  `exponentFormat` parameter in `theme()` [[#1071](https://github.com/JetBrains/lets-plot/issues/1071)].

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.9.0/superscript_exponent.ipynb).

- In `theme()`:
  - parameters `legendMargin, legendSpacing, legendSpacingX, legendSpacingY, legendBoxSpacing`,

    and functions `legendBoxHorizontal/Vertical(), legendBoxJustificationLeft/Right/Top/Bottom/Center()` [[#1180](https://github.com/JetBrains/lets-plot/issues/1180)].

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.9.0/theme_legend_margins.ipynb).

  - parameters `legendKey, legendKeySize/Width/Height/Spacing/SpacingX/SpacingY`[[#1181](https://github.com/JetBrains/lets-plot/issues/1181)].

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.9.0/theme_legend_key.ipynb).

  - parameters `stripBackgroundX/Y, stripTextX/Y` [[#1195](https://github.com/JetBrains/lets-plot/issues/1195)].

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.9.0/theme_facet_strip_xy.ipynb).

- Custom `linetype` patterns [[#1198](https://github.com/JetBrains/lets-plot/issues/1198)]:
  - a list specifying the pattern of dashes and gaps used to draw the line: `listOf(dash, gap, dash, gap, ...)`;
  - a list with a specified offset: `listOf(offset, listOf(dash, gap, dash, gap, ...))`;
  - a string of an even number (up to eight) of hexadecimal digits specifying the lengths in consecutive positions in the string.

  See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.9.0/linetype_custom.ipynb).

- Geometries:
  - `geomBlank()` [[#831](https://github.com/JetBrains/lets-plot/issues/831)].

  - `base` parameter in `waterfallPlot()` [[#1159](https://github.com/JetBrains/lets-plot/issues/1159)].

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.9.0/waterfall_plot_base.ipynb).

  - `checkOverlap` parameter in `geomText()` and `geomLabel()`.

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/tree/master/docs/examples/jupyter-notebooks/f-4.9.0/check_overlap.ipynb).

  - `marginal` parameter in `qqPlot()`:

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/tree/master/docs/examples/jupyter-notebooks/f-4.9.0/qq_plot_marginal.ipynb).

  - `inheritAes` parameter in layers [[#1172](https://github.com/JetBrains/lets-plot/issues/1172)].

- `expandLimits()` [[#820](https://github.com/JetBrains/lets-plot/issues/820)].

  See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/tree/master/docs/examples/jupyter-notebooks/f-4.9.0/expand_limits.ipynb).

- Support for 3-character hex color codes


### Changed

- Parameters `labwidth` in `facetWrap()`, `xLabwidth, yLabwidth` in `facetGrid()`: the maximum label length is applied after splitting at `\n` separators, if present.

- `themeLight()` is now the default theme in `qqPlot()`.

- [**BREAKING**] For API deprecated in v4.0 the deprecation level raised to "Error".
                    

### Fixed

- waterfallPlot: faceting doesn't work without a measure mapping [[#1152](https://github.com/JetBrains/lets-plot/issues/1152)].
- waterfallPlot: tooltips don't work with column names from original dataset [[#1153](https://github.com/JetBrains/lets-plot/issues/1153)].
- Legend icon background is not transparent when `legendBackground` is set to "blank" in `theme` [[#1167](https://github.com/JetBrains/lets-plot/issues/1167)].
- Unable to display or save graph when using geomPath [[#1168](https://github.com/JetBrains/lets-plot/issues/1168)].
- Legend icon background should inherit the fill color of the plot panel (i.e. grey when `themeGrey()` is used).
- Vertex sampling uses different tolerances for objects within the same plot [[#1174](https://github.com/JetBrains/lets-plot/issues/1174)].
- `samplingVertexVW()` doesn't work as expected [[#1175](https://github.com/JetBrains/lets-plot/issues/1175)].
- `samplingVertexDP()` may break rings [[#1176](https://github.com/JetBrains/lets-plot/issues/1176)].
- ggmarginal: bottom boxplot is broken [[#1189](https://github.com/JetBrains/lets-plot/issues/1189)].
- Offscreen cells are sometimes not rendered in JupyterLab.
- `legendJustification()` doesn't work if `legendPositionTop()` [[#1031](https://github.com/JetBrains/lets-plot/issues/1031)].
- coordPolar: geomPoint tooltips should take in account point size [[#1214](https://github.com/JetBrains/lets-plot/issues/1214)].


## [4.8.0] - 2024-08-20

This release is 100% compatible with Lets-Plot [v 4.4.0](https://github.com/JetBrains/lets-plot/releases/tag/v4.4.0),  [v 4.4.1](https://github.com/JetBrains/lets-plot/releases/tag/v4.4.1).

### Added

- Waterfall plot [[#975](https://github.com/JetBrains/lets-plot/issues/975)]:

  See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.8.0/waterfall_plot.ipynb).

- `geomBand()` [[#733](https://github.com/JetBrains/lets-plot/issues/733)]:

  See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.8.0/us_unemployment.ipynb).

- Custom legends [[#774](https://github.com/JetBrains/lets-plot/issues/774)]:
  - `manualKey` parameter in plot layer
  - `layerKey()` function

  See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.8.0/manual_legend.ipynb).

- In legends:
  - `title` parameter in `guideLegend()` and `guideColorbar()` functions

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.8.0/legend_title.ipynb).

  - override aesthetics in the `guideLegend()` function [[#807](https://github.com/JetBrains/lets-plot/issues/807)]:

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.8.0/legend_override_aes.ipynb).

- `plotTitlePosition` and `plotCaptionPosition` parameters in `theme()` [[#1027](https://github.com/JetBrains/lets-plot/issues/1027)].

  See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.8.0/theme_plot_title_position.ipynb).

- `threshold` parameter in `geomHistogram()` [[#1122](https://github.com/JetBrains/lets-plot/issues/1122)].

  See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.8.0/geom_histogram_threshold.ipynb).

### Changed

- The triangular point shape is now anchored to the data point via the centroid

### Fixed

- ggsave (.svg) transforms geom_text integer to float [[#626](https://github.com/JetBrains/lets-plot/issues/626)].
- Int DataFrame column names are being converted to float string representation [[#901](https://github.com/JetBrains/lets-plot/issues/901)].
- `linetype` = 0 ("blank") should make lines invisible [[#712](https://github.com/JetBrains/lets-plot/issues/712)].
- `geomDensity2D, geomDensity2DFilled`: support weight aesthetic [[#791](https://github.com/JetBrains/lets-plot/issues/791)].
- Discrete axis labels unnecessarily rotate 90 degrees when applying coord system limits.
- Axis title via `labs()` breaks the date-time scale [[#1113](https://github.com/JetBrains/lets-plot/issues/1113)].
- JavaFX IllegalArgumentException: Unsupported attribute `display` in Pane.
                             

## [4.7.3] - 2024-05-30

This release is 100% compatible with [Lets-Plot v 4.3.3](https://github.com/JetBrains/lets-plot/releases/tag/v4.3.3).

### Added

- Support for `angle` aesthetic in `geomPoint()` [[#736](https://github.com/JetBrains/lets-plot/issues/736)].  
  See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.7.3/geom_point_angle.ipynb).

### Fixed

- Undesired vertical scroller when displaying `gggrid` in Jupyter notebook.
- Memory leak in SVG observable model (thanks to [contribution](https://github.com/JetBrains/lets-plot/commit/9c85d1ef39e3d6022880f068ce68e97b1b7ba2f1) by Ilya Muradyan).
- GeoJson structure breaks if the ring start label occurs several times [[#1086](https://github.com/JetBrains/lets-plot/issues/1086)].
- `theme`: left margin doesn't work for the `plot_title` parameter [[#1101](https://github.com/JetBrains/lets-plot/issues/1101)].
- Improve border line type experience [[LPK-220](https://github.com/JetBrains/lets-plot-kotlin/issues/220)].

## [4.7.2] - 2024-04-25

This release is 100% compatible with [Lets-Plot v 4.3.2](https://github.com/JetBrains/lets-plot/releases/tag/v4.3.2).

### Fixed

- LP occasionally crashes when drawing polygons [[#1084](https://github.com/JetBrains/lets-plot/issues/1084)].
- Regression of issue [[#966](https://github.com/JetBrains/lets-plot/issues/966)].
- Linetype doesn't work for `geomTile()` [[LPK-241](https://github.com/JetBrains/lets-plot-kotlin/issues/241)].
                

## [4.7.1] - 2024-04-22

This release is 100% compatible with [Lets-Plot v 4.3.1](https://github.com/JetBrains/lets-plot/releases/tag/v4.3.1).

### Added

- Parameter `labWidth` in `facetWrap()` and `xLabWidth/yLabWidth` in `facetGrid()` [[LPK-237](https://github.com/JetBrains/lets-plot-kotlin/issues/237)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.7.1/facet_multiline_titles.ipynb).

- Parameter `linetype` in `elementLine()` and `elementRect()` in `theme()` [[LPK-235](https://github.com/JetBrains/lets-plot-kotlin/issues/235)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.7.1/theme_linetype.ipynb).

- Parameter `arrow` in `geomSpoke()` [[#986](https://github.com/JetBrains/lets-plot/issues/986)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.7.1/geom_spoke_arrow.ipynb).

- Parameter `sizeUnit` in `geomPoint()`, `geomText()` and `geomLabel()`.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.7.1/param_size_unit.ipynb).

- Auto-detection of `orientation="y"` in bar-chart etc. [[#558](https://github.com/JetBrains/lets-plot/issues/558)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.7.1/auto_rotate.ipynb).

- Parameter `plotInset` in `theme()`.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.7.1/theme_plot_inset.ipynb).


### Changed

- `LetsPlot.theme` global property now accepts a sum of theme/flavor features [[#657](https://github.com/JetBrains/lets-plot/issues/657)].

### Fixed

- ggmarginal(): broken coloring [[#760](https://github.com/JetBrains/lets-plot/issues/760)].
- Incorrect 'plot_background' area (with empty space capture) [[#918](https://github.com/JetBrains/lets-plot/issues/918)].
- geom_density2df: uneven borders [[#941](https://github.com/JetBrains/lets-plot/issues/941)].
- Line segments in geom_density2df() do not get interpolated when used with coord_polar() [[#1037](https://github.com/JetBrains/lets-plot/issues/1037)].
- arrow on segment: reduce arrow size for short segments [[#1040](https://github.com/JetBrains/lets-plot/issues/1040)].
- arrow on curve sometimes looks weird [[#1041](https://github.com/JetBrains/lets-plot/issues/1041)].
- Error when build geom_smooth() with se=False [[#1050](https://github.com/JetBrains/lets-plot/issues/1050)].
- Add tooltips for `geom_curve()` [[#1053](https://github.com/JetBrains/lets-plot/issues/1053)].
- Incorrect position for bar annotations when specifying `scale_x_reverse()/scale_y_reverse()` [[#1057](https://github.com/JetBrains/lets-plot/issues/1057)].
- Missing outer bar annotations when specifying `scale_x_reverse()/scale_y_reverse()` [[#1058](https://github.com/JetBrains/lets-plot/issues/1058)].
- Plot layout breaks when marginal layers are used [[#1074](https://github.com/JetBrains/lets-plot/issues/1074)].
- Discrete scale doesn't work for datetime data [[LPK-231](https://github.com/JetBrains/lets-plot-kotlin/issues/231)].
- Add `linetype` parameter in `elementLine()` and `elementRect()` [[LPK-235](https://github.com/JetBrains/lets-plot-kotlin/issues/235)].
- Any way to line-wrap facet labels? [[LPK-237](https://github.com/JetBrains/lets-plot-kotlin/issues/237)].
- Missing marginal gridlines.
- Cryptic error message on geom_boxplot with orientation="y" [[#600](https://github.com/JetBrains/lets-plot/issues/600)].


## [4.7.0] - 2024-03-15

### Added
- `coordPolar()`

  The polar coordinate system is most commonly used for pie charts, but </br>
  it can also be used for constructing **Spider or Radar charts** using the `flat` option.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.7.0/coord_polar.ipynb).

- In the `theme()` function:
  - `panelInset`  parameter - primarily used for plots with polar coordinates.

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.7.0/theme_panel_inset.ipynb).

  - `panelBorderOntop` parameter - enables the drawing of panel border on top of the plot geoms.
  - `panelGridOntop, panelGridOntopX, panelGridOntopY` parameters - enable the drawing of grid lines on top of the plot geoms.

- `geomCurve()`

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.7.0/geom_curve.ipynb).

- [**UNIQUE**] Visualizing graph-like data with `geomSegment()` and `geomCurve()`.

  - Aesthetics `sizeStart, sizeEnd, strokeStart` and `strokeEnd` enable better alignment of</br>
    segments/curves with nodes of the graph by considering the size of the nodes.

  - The `spacer` parameter allows for additional manual fine-tuning.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.7.0/graph_edges.ipynb).

- `alphaStroke` parameter in `geomLabel()` to enable the applying of `alpha` to `color` [[#1029](https://github.com/JetBrains/lets-plot/issues/1029)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.7.0/geom_label_alpha_stroke.ipynb).

### Changed

- Parameter `axisOntop` in `theme()` is now `true` by default.

### Fixed
   
- Bug with Tooltips in Swing/Batik [[LPK-225](https://github.com/JetBrains/lets-plot-kotlin/issues/225)].
- coordMap() should distort tiles size to account for different unit size at different latitudes [[#331](https://github.com/JetBrains/lets-plot/issues/331)].
- geomErrorbar(): wrong positioning of tooltips [[#992](https://github.com/JetBrains/lets-plot/issues/992)].
- geomPath(): tooltip position interpolation [[#855](https://github.com/JetBrains/lets-plot/issues/855)].
- Stacked bar-chart annotation: labels go out of the plot when zooming-in using coord_cartesian(xlim, ylim) [[#981](https://github.com/JetBrains/lets-plot/issues/981)].
- Facets: "free scales" options are ignored by discrete axis [[#955](https://github.com/JetBrains/lets-plot/issues/955)].
- Bar width is too large when x-domain is defined via x-scale limits [[#1013](https://github.com/JetBrains/lets-plot/issues/1013)].
- How to hide only main tooltip? [[LPK-#232](https://github.com/JetBrains/lets-plot-kotlin/issues/232)].
- Make middle strip in `geomCrossbar()` optional [[LPK-233](https://github.com/JetBrains/lets-plot-kotlin/issues/233)].
- Can't set `null` for coord limit [[#486](https://github.com/JetBrains/lets-plot/issues/486)].
- Scale limits don't work for bars/area [[LPK-219](https://github.com/JetBrains/lets-plot-kotlin/issues/219)], [[#978](https://github.com/JetBrains/lets-plot/issues/978)].
- No gridlines when axisOntop=True [[#1012](https://github.com/JetBrains/lets-plot/issues/1012)].
- Displaying tooltips on a multilayer plot [[#1030](https://github.com/JetBrains/lets-plot/issues/1030)].
- Make segment geometry better suited for graphs visualization [[#572](https://github.com/JetBrains/lets-plot/issues/572)].

## [4.6.0] - 2024-01-10

### Added

- The `levels` parameter in `asDiscrete()` function [[#931](https://github.com/JetBrains/lets-plot/issues/931)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.6.0/factor_levels.ipynb).

- Support for superscript for numbers in scientific notation [[#743](https://github.com/JetBrains/lets-plot/issues/743)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.6.0/superscript_exponent.ipynb).

- Sharing of X,Y-scale limits between subplots in `gggrid()` [[#718](https://github.com/JetBrains/lets-plot/issues/718)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.6.0/gggrid_scale_share.ipynb).

- `geomSpoke()` [[#738](https://github.com/JetBrains/lets-plot/issues/738)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.6.0/geom_spoke.ipynb).

- `scaleXLog2(), scaleYLog2()` [[#922](https://github.com/JetBrains/lets-plot/issues/922)].

- New variables computed by `'count'` and `'count2d'` statistics: `'..sumprop..'`, `'..sumpct..'` [[#936](https://github.com/JetBrains/lets-plot/issues/936)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.6.0/new_stat_count_vars.ipynb).

- Support using dictionaries for breaks/labels/values customization in `scaleXxx()` functions [[#169](https://github.com/JetBrains/lets-plot/issues/169)], [[#882](https://github.com/JetBrains/lets-plot/issues/882)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.6.0/scale_params_with_dict.ipynb).

- The `lablim` parameter for `scaleXxx()` functions [[#939](https://github.com/JetBrains/lets-plot/issues/939), [#946](https://github.com/JetBrains/lets-plot/issues/946)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.6.0/scale_lablim.ipynb).

- `labelText` parameter in `theme()` for annotation text settings [[#930](https://github.com/JetBrains/lets-plot/issues/930)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.6.0/theme_label_text.ipynb).



### Changed

- **[BREAKING]** Function `margin()` is deprecated and will be removed in future releases. <br/>
  Please replace all existing usages, i.e. `theme(plotMargin=margin(..))` and `elementText(margin=margin(..))` <br/>
  with a list or with just a number:
  - a number or list of one number - the same margin it applied to **all four sides**;
  - a list of two numbers - the first margin applies to the **top and bottom**, the second - to the **left and right**;
  - a list of three numbers -  the first margin applies to the **top**, the second - to the **right and left**,
    the third - to the **bottom**;
  - a list of four numbers - the margins are applied to the **top, right, bottom and left** in that order.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.6.0/margins.ipynb).

- Geotools: migrate to v30 [[#217](https://github.com/JetBrains/lets-plot-kotlin/issues/217)]. <br/>
  **[BREAKING]** GeoTools v30 and up is not backward compatible with v29 and below due to <br/>
  renaming of all "org.opengis" packages into "org.geotools.api" ones. <br/>
  See release notes: http://geotoolsnews.blogspot.com/2023/09/geotools-30-rc-released.html

- Upgraded Apache Batik to version 1.17 [[#887](https://github.com/JetBrains/lets-plot/issues/887)]


### Fixed

- Jitter reproducibility in geomJitter, positionJitter, positionJitterDodge [[#911](https://github.com/JetBrains/lets-plot/issues/911)].
- Bug with Tooltips in Swing/Batik [[#225](https://github.com/JetBrains/lets-plot-kotlin/issues/225)].
- Facets: order = 0 doesn't work as expected [[#923](https://github.com/JetBrains/lets-plot/issues/923)].
- Enormous CPU / Time/ Memory consumption on some data [[#932](https://github.com/JetBrains/lets-plot/issues/932)].
- gggrid: composite plot is not visible if saved with ggsave [[#942](https://github.com/JetBrains/lets-plot/issues/942)].
- gggrid doesn't override global theme [[#966](https://github.com/JetBrains/lets-plot/issues/966)].
- Marginal box-plots aren't shown when requested on more than 1 plot side.
- Marginal plot: use "pen" as default color for marginal layers.
- `gggrid()` doesn't use global theme settings.
- NumberFormat: `g` format doesn't use e-notation for small numbers [[#965](https://github.com/JetBrains/lets-plot/issues/965)].
- Tooltips: graphical artifacts and bad performance in multi-line plot in Batik [[#967](https://github.com/JetBrains/lets-plot/issues/967)].
- Wrong tooltip position on `geom_segment()` with position adjustment [[#963](https://github.com/JetBrains/lets-plot/issues/963)].
- `geomBoxplot()`: use `outlierAlpha` to boxplot outliers (not apply `alpha`).


## [4.5.0] - 2023-11-06

### Added

- Annotations in Barchart.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.5.0/bar_annotations.ipynb).


- Common theme support in subplots (i.e. `gggrid()`) [[LPK-#197](https://github.com/JetBrains/lets-plot-kotlin/issues/197)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.5.0/gggrid_theme.ipynb).


- `HCL` and `CIELAB` color space for hue color scale and gradient color scales [[#876](https://github.com/JetBrains/lets-plot/issues/876)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.5.0/color_space_update.ipynb).


- New scale transformations: `'log2'` and `'symlog'`.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.5.0/new_scale_transformations.ipynb).


- `plotMargin` parameter in `theme()` [[#856](https://github.com/JetBrains/lets-plot/issues/856)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.5.0/theme_plot_margin.ipynb).


- Dual orientation for geometries:
  - `geomErrorBar()`;
  - `geomCrossbar()`;
  - `geomPointRange()`;
  - `geomLineRange()`;
  - `geomRibbon()`.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.5.0/horizontal_geoms.ipynb).


### Fixed

- Husl palette equivalent [[#876](https://github.com/JetBrains/lets-plot/issues/876)].
- Tooltips are trimmed and not visible on a very narrow chart [[#837](https://github.com/JetBrains/lets-plot/issues/837)].
- Exception label is unresizeble, uncopyable and uncontrollable [[#902](https://github.com/JetBrains/lets-plot/issues/902)].
- Flickering during plot downsizing [[#888](https://github.com/JetBrains/lets-plot/issues/888)].
- Bad default formatting of numeric values in annotations [[#905](https://github.com/JetBrains/lets-plot/issues/905)].
- corr_plot: unexpected whitespace between the "geometry area" and the legend [[#877](https://github.com/JetBrains/lets-plot/issues/877)].
- scale_log: an option to generate only breaks which are integer powers of 10 needed [[#850](https://github.com/JetBrains/lets-plot/issues/850)].
- Trimmed legend when bounds of the rightmost X-axis tick label exceeds the axis length [[#851](https://github.com/JetBrains/lets-plot/issues/851)].
- HTML files exported using ggsave() are missing the encoding specification [[#900](https://github.com/JetBrains/lets-plot/issues/900)].
- `plot_margin` parameter in `theme()` [[#856](https://github.com/JetBrains/lets-plot/issues/856)].
- Subplot themes not inherited by parent [[LPK-#197](https://github.com/JetBrains/lets-plot-kotlin/issues/197)].
- `element_blank()` has no effect on plot title/subtitle/caption in `theme()` [[#913](https://github.com/JetBrains/lets-plot/issues/913)].
- Lollipop in legend is disproportionately large [[LPK-216](https://github.com/JetBrains/lets-plot-kotlin/issues/216)].
- geomBar with fill, produces tooltips artefacts [[#895](https://github.com/JetBrains/lets-plot/issues/895)].
- Exception, when trying to build plot with column name containing line breakes [[#894](https://github.com/JetBrains/lets-plot/issues/894)].
- Added "grey" spelling for the gray color (earlier - "gray" only).

           

## [4.4.3] - 2023-09-15

### Added

- `geomCount()`/`statSum()` [[#821](https://github.com/JetBrains/lets-plot/issues/821)].  
  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.3/geom_count.ipynb).
- `plotMessage` parameter in `theme(...)`  [[#863](https://github.com/JetBrains/lets-plot/issues/863)].  
  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.3/theme_plot_message.ipynb).
- KDocs for the stats.

### Changed

- If layer transparency is set via the alpha-channel in the colors RGBA specification and via the `alpha` aesthetic, \
  then the `alpha` aesthetic overrides the alpha-channel in the color. Previousely it was the opposite.

- `geomPie()` defaults:
  - "stroke" is visible and `strokeSide="both"` (was `strokeSide="outer"`).
  - the "hole" is not created automatically when `strokeSide = "both"/"inner"` (was created automatically).

- `geomBar()` now has solid outline color by default (was transparent).

- `geomTile()`, `geomBin2D()` now have solid outline color by default (was transparent).
  - however, by default the `size` is 0 (i.e. tiles outline initially is not visible).

- [DEPRECATED] Function `statCount2d` has been renamed to `statCount2D`. The old name is deprecated.

### Fixed

- `themeVoid()` + `flavorXxx()`: no expected plot background [[#858](https://github.com/JetBrains/lets-plot/issues/858)].
- `geomTile()`, `geomBin2D()` : the `alpha` aesthetic is applied to the tiles outline.
- `scaleXDateTime()`: error building plot for early dates [[#346](https://github.com/JetBrains/lets-plot/issues/346)].
- Inconsistent color in legend when using `paint_a/paint_b/paint_c` [[#867](https://github.com/JetBrains/lets-plot/issues/867)].


## [4.4.2] - 2023-08-23

### Added

- Flavor-aware colors: **pen**, **brush** and **paper**
  - By default, all geometries utilize new flavor-aware colors.
  - Theme `geom` parameter allows redefinition of "geom colors":  `theme(geom = elementGeom(pen, brush, paper))`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/geom_theme_colors.ipynb).


- Support for variadic line width and/or color in `geom_line()` and `geom_path()` [[LP-313](https://github.com/JetBrains/lets-plot/issues/313)].

  Ses: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/aes_size_color_variadic_lines.ipynb).


- `themeVoid()`:
  [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/theme_void.ipynb)

- `statECDF()` :
  [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/stat_ecdf.ipynb).

- `geomFunction()` :
  [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/geom_function.ipynb).

- `statSummary()`:
  [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/stat_summary.ipynb).

- `statSummaryBin()`:
  [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/stat_summary_bin.ipynb).

- `Stat.sum()` statistic:
  [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/stat_sum.ipynb).

- `Stat.boxplotOutlier()` statistic:
  [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/stat_boxplot_outlier.ipynb).


- In tooltip customization API:\
  `disableSplitting()` function [[#189](https://github.com/JetBrains/lets-plot-kotlin/issues/189)].

  [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/tooltips_disable_splitting.ipynb).


- In `geomPie()`:
  - `stroke` and `color` aesthetics - the width and color of pie sector arcs.
  - `strokeSide` parameter - which arcs to show (inner, outer, both).
  - `spacerWidth` and `spacerColor` parameters - lines between sectors.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/geom_pie_stroke_and_spacers.ipynb).

  - `sizeUnit` parameter : [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/geom_pie_size_unit.ipynb).


### Changed

> Note: Due to major package refactoring in the main Lets-Plot library, this version (4.4.2) of the Kotlin API\
> is not compatible with versions of Lets-Plot library v3.2.0 and earlier.

- The default qualitative color palette is now [Color Brewer "Set1"](https://colorbrewer2.org/#type=qualitative&scheme=Set1&n=9) (was ["Set2"](https://colorbrewer2.org/#type=qualitative&scheme=Set2&n=8))
- Geometries default colors are now flavor-dependent: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/geom_theme_colors.ipynb).
- Geometries default size/line-width is now slightly bigger.
- Point size is adjusted to match the width of a line of the same "size".

- [BREAKING] Kotlin/JS **LEGACY** apps are no longer supported.

- [BREAKING] `geomPie()` no longer supports parameter `strokeColor`.
- [BREAKING] `geomBoxplot()` no longer support parameter `sampling`.
- [BREAKING] `geomPointRange()`: size aesthetic shouldn't affect line width [[#751](https://github.com/JetBrains/lets-plot/issues/751)]:\
  `linewidth` aesthetic is now used for the line width, `size` - for mid-point size only.

- `geomBoxplot()`: `size` and `stroke` parameters now affect outlier shapes.


### Fixed
- ggsave: saving geomImshow() to SVG produces fuzzy picture [[#188](https://github.com/JetBrains/lets-plot-kotlin/issues/188)].
- `geomCrossbar()` aesthetics take `middle` argument instead of `y` [[#804](https://github.com/JetBrains/lets-plot/issues/804)].
- `geomBoxplot()` doesn't apply alpha to outliers [[#754](https://github.com/JetBrains/lets-plot/issues/754)].
- `geomBoxplot()`: outliers do not show tooltips.
- `geomBoxplot()`: some strange outliers drawn here [[#143](https://github.com/JetBrains/lets-plot-kotlin/issues/143)].
- `geomStep()`: no tooltips.
- `geomStep()`: add 'tooltips' parameter [[#195](https://github.com/JetBrains/lets-plot-kotlin/issues/195)].
- `geomStep()`: toggle the behavior of the `direction` parameter when the orientation is changed.
- `geomRibbon()`: not all tooltips are shown on a multi-layer plot [[#847](https://github.com/JetBrains/lets-plot/issues/847)].
- Bug in empty plot: IndexOutOfBoundsException [[#194](https://github.com/JetBrains/lets-plot-kotlin/issues/194)].
- How to remove side tooltips without anchor? [[#189](https://github.com/JetBrains/lets-plot-kotlin/issues/189)].
                          


## [4.4.1] - 2023-05-11

### Fixed

- Lets-Plot dependency: was 3.2.0-RC1, now 3.2.0.
- ggsave: an error if file does already exist [[#187](https://github.com/JetBrains/lets-plot-kotlin/issues/187)].


## [4.4.0] - 2023-05-10

### Added

- `geomLollipop()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.0/geom_lollipop.ipynb).


- Aesthetic `stroke` and its scales `scaleStroke()`, `scaleStrokeIdentity()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.0/aes_stroke.ipynb).


- Aesthetic `linewidth` (for `geomLollipop()`) and its scales `scaleLinewidth()`, `scaleLinewidthIdentity()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.0/geom_lollipop.ipynb).


- The 'newline' character (`\n`) now works as `line break` in legend text.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.0/legend_text_multiline.ipynb).


- Horizontal error bars and vertical "dodge".

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.0/horizontal_error_bars.ipynb).
                                                          

- "Colorbar" in `geom_imshow()`. Parameters `show_legend` and `color_by` [[#717](https://github.com/JetBrains/lets-plot/issues/717)].


### Changed

- [BREAKING] `geomDotplot()` and `geomYDotplot()` no longer support parameter `stat`.

- [BREAKING] Identity scales don't create a legend by default.


### Fixed

- Support multiple subdirectories in `ggsave` path [[contribution by David Phillips]](https://github.com/JetBrains/lets-plot-kotlin/pull/163).
- `scaleXDiscrete` doesn't make scale discrete [[#165](https://github.com/JetBrains/lets-plot-kotlin/issues/165)].
- Batik: `geom_imshow()` fail with an error: "The attribute "xlink:href" of the element <image> is required"
- Batik: bug with usage of "&" [[#713](https://github.com/JetBrains/lets-plot/issues/713)].
- Categorical ordering, it's not respected for Boxplot and violin
  plot [[#746](https://github.com/JetBrains/lets-plot/issues/746)].
- Groups not sorted similarly when using facets [[#679](https://github.com/JetBrains/lets-plot/issues/679)].
- HTML export: exclude computation messages from the output [[#725](https://github.com/JetBrains/lets-plot/issues/725)].
- Image export not working with `geom_imshow()`
  and `geom_raster()` [[LPK-175](https://github.com/JetBrains/lets-plot-kotlin/issues/175)].
- `geom_segment()` doesn't take into account the alpha [[#748](https://github.com/JetBrains/lets-plot/issues/748)].
- `geom_density2d`: Internal error with None values in data [[#702](https://github.com/JetBrains/lets-plot/issues/702)].
- DateTime metadata is not applied for scales other than
  X/Y [[LPK-174](https://github.com/JetBrains/lets-plot-kotlin/issues/174)].
- Quantile should be shown in tooltip if the variable `..quantile..` is mapped to geom aesthetic.
- Bad default formatting for stat variables [[#654](https://github.com/JetBrains/lets-plot/issues/654)].
- The scale name does not apply with `as_discrete()` [[#653](https://github.com/JetBrains/lets-plot/issues/653)].
- Tooltip is not shown when configured for 'const' value [[#610](https://github.com/JetBrains/lets-plot/issues/610)].
- Fix crash when try to add a constant to a tooltip (e.g.`"^size"`, where `size` aesthetic is specified with a number).
- "Variable not found" error in `ggmarginal` [[#681](https://github.com/JetBrains/lets-plot/issues/681)].
- `facet_grid`: Internal error [[#699](https://github.com/JetBrains/lets-plot/issues/699)].
- Tooltips bug [[LPK-176](https://github.com/JetBrains/lets-plot-kotlin/issues/176)].



## [4.3.0] - 2023-03-09

### Added

- `gggrid()` function ([docs](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot/gggrid.html)), as a replacement for earlier variant of [gggrid()](https://github.com/JetBrains/lets-plot-kotlin/blob/master/plot-api/src/commonMain/kotlin/org/jetbrains/letsPlot/gggrid_deprecated.kt).
  - plots inner area alignment in grid
  - nested grids
  - works well with `ggsize()`

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/plot_grid.ipynb).


- `jointPlot()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/joint_plot.ipynb).


- Axis `position` parameter in position scales `scaleX*(), scaleY*()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/axis_position.ipynb).


-  Drawing quantile lines and filling quantile areas in `geomViolin()` and `geomDensity()`.

   See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/quantile_parameters.ipynb).


- `angle` parameter in `elementText()` in `theme()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/axis_text_angle.ipynb).


- Additional "color" aesthetics: `paint_a, paint_b, paint_c`.

  These aesthetics are flexible and can be used as either "color" or "fill" as needed.

  See [Multiple Color Scales](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/multiple_color_scales.ipynb) demo.

  Also added a set of related "color scale" functions with the "aesthetic" parameter for configuring of additional color scales.

  See [New "Scale" Functions](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/scale_functions.ipynb) demo.


- `density2d` and `density2df` geometry types in `residualPlot()`.


### Changed

- [BREAKING] `geomViolin()` no longer supports parameter `drawQuantiles`. Use new `quantileLines` and `quantiles` parameters as needed.

- [BREAKING] `stack` and `fill` position adjustments now stack objects on top of each other only if these objects belong to different **groups**.

  If necessary, use `mode="all"` in `positionStack()` or `positionFill()` to stack objects regardless of their group.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/position_stack.ipynb).

- [BREAKING] The deprecation level raised to "ERROR" for all API that was deprecated in v. 4.2.0 and earlier.

- [DEPRECATED] The earlier variant of [gggrid()](https://github.com/JetBrains/lets-plot-kotlin/blob/master/plot-api/src/commonMain/kotlin/org/jetbrains/letsPlot/gggrid_deprecated.kt).

  From now-on please use new variant added in this release: [new gggrid()](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot/gggrid.html).

- Sampling: drastically increased default N for "pick sampling" and for other types of sampling [[#687](https://github.com/JetBrains/lets-plot/issues/687)].

### Fixed

- Tooltip does not reflect `..quantile..` aesthetic change [[#658](https://github.com/JetBrains/lets-plot/issues/658)].
- 'map_join': variable is lost after "stat" [[#664](https://github.com/JetBrains/lets-plot/issues/664)].
- Error when tooltip has variable mapped to aesthetic used by stat [[#665](https://github.com/JetBrains/lets-plot/issues/665)].
- Groups not sorted similarly when `position='stack'` [[#673](https://github.com/JetBrains/lets-plot/issues/673)].
- Area ridges: fill overlaps geometry borders when colors are repeated [[#674](https://github.com/JetBrains/lets-plot/issues/674)].
- Sampling: increase the default N for "pick sampling" and for other types of sampling [[#687](https://github.com/JetBrains/lets-plot/issues/687)].


## [4.2.0] - 2022-12-29

### Added

- `residualPlot()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/residual_plot.ipynb).

- `geomAreaRidges()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/ridgeline_plot.ipynb).

- `geomPie()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/geom_pie.ipynb).

- Annotations for pie chart:

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/annotations_for_pie.ipynb).

- New variables computed by 'count' and 'count2d' statistics: '..sum..', '..prop..', '..proppct..'.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/stat_count_2d.ipynb).

- `geomImshow()`.

  See: [image_101](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/image_101.ipynb),
  [image_fisher_boat](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/image_fisher_boat.ipynb),
  [image_grayscale](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/image_grayscale.ipynb),
  [image_extent](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/image_extent.ipynb)

- New parameters in `geomViolin()`:
  - `tailsCutoff`
    ([example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/violin_tails_cutoff.ipynb))
  - `showHalf`
    ([example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/violin_show_half.ipynb))

- Static maps:
  - `useCRS` parameter with value "provided" for `geomMap()` and other geoms, working with `SpatialDataset`.

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/projection_provided.ipynb).


### Changed

- Upgraded Lets-Plot version to 3.0.0 (was 2.5.1).
- Java/Swing platf.: Apache Batik upgraded to v.1.16 [[#624](https://github.com/JetBrains/lets-plot/issues/624)], [[LPK #140](https://github.com/JetBrains/lets-plot-kotlin/issues/140)].
- The default size is increased for the plot title and decreased for the caption.
- Upgraded Kotlin version to 1.7.21 (was 1.7.20).


### Fixed

- Themes: can't change plot background after applying a "flavor" [[#623](https://github.com/JetBrains/lets-plot/issues/623)].
- Layout: uneven left/right, top/bottom plot margins [[#625](https://github.com/JetBrains/lets-plot/issues/625)].
- A plot building error with empty data on various geoms.
- Precision error in gradient [[#634](https://github.com/JetBrains/lets-plot/issues/634)].


## [4.1.1] - 2022-11-08

### Added

- In `geomText(), geomLabel()`:

  - the 'newline' character (`\n`) now works as `line break`
  - `lineheight` aesthetic
  - `nudgeX, nudgeY` parameters
  - special text alignments (`vjust` and `hjust`): `"inward"` and `"outward"`

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.1.1/geom_text_and_label_new_features.ipynb).

- `vjust` parameter in `positionStack()` and `positionFill()`.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/03c5a54d8c667be733eec951e9c8364a547ee3e2/docs/examples/jupyter-notebooks/position_stack.ipynb).

### Changed

- Deprecated API:
  - `positionStack`, new usage: `positionStack()`
  - `positionFill`, new usage: `positionFill()`

- `geomBoxplot()`: default value for parameter `whiskerWidth` is 0.5.

- Upgraded Kotlin version to 1.7.20 (was 1.6.21).
- Upgraded Lets-Plot version to 2.5.1 (was 2.5.0).

  See Lets-Plot [What is new in 2.5.1](https://github.com/JetBrains/lets-plot#what-is-new-in-251)
  for more details.

### Fixed

- `elementBlank()` has no effect in theme `legendTitle` [[#608](https://github.com/JetBrains/lets-plot/issues/608)].
- Tooltip: different formats for same aesthetic Y [[#579](https://github.com/JetBrains/lets-plot/issues/579)].
- Positioning with "constant" x/y doesn't work on axis with log10 transform [[#618](https://github.com/JetBrains/lets-plot/issues/618)].
- Positional "constant" doesn't honor axis limits [[#619](https://github.com/JetBrains/lets-plot/issues/619)].
- Several issues leading to crush in Swing/Batik apps. Related to [[discussions](https://github.com/JetBrains/lets-plot-kotlin/discussions/138)]
- Text labels got trimmed occasionally, when symbols `-`, `/`, `\` or `|` present.


## [4.1.0] - 2022-09-30

### Added

- New theme: `themeBW()`.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/b526b48dbb57c968107aa6a6ac6f5cd2ca9bca58/docs/examples/jupyter-notebooks/complete_themes.ipynb).

- Color schemes (flavors) applicable to existing themes:
  - `flavorDarcula()`
  - `flavorSolarizedLight()`
  - `flavorSolarizedDark()`
  - `flavorHighContrastLight()`
  - `flavorHighContrastDark()`

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/b526b48dbb57c968107aa6a6ac6f5cd2ca9bca58/docs/examples/jupyter-notebooks/theme_flavors.ipynb).

- Viridis color scales: `scaleColorViridis()`, `scaleFillViridis()`.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/b526b48dbb57c968107aa6a6ac6f5cd2ca9bca58/docs/examples/jupyter-notebooks/colors_viridis.ipynb).

- New parameters in theme's `elementText()`:
  - `size, family`
    ([example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/b526b48dbb57c968107aa6a6ac6f5cd2ca9bca58/docs/examples/jupyter-notebooks/font_size_and_family.ipynb))
  - `hjust, vjust` for plot title, subtitle, caption, legend and axis titles
    ([example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/b526b48dbb57c968107aa6a6ac6f5cd2ca9bca58/docs/examples/jupyter-notebooks/hjust_vjust.ipynb))
  - `margin` for plot title, subtitle, caption, axis titles and tick labels
    ([example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/b526b48dbb57c968107aa6a6ac6f5cd2ca9bca58/docs/examples/jupyter-notebooks/text_margins.ipynb))

- Parameter `whiskerWidth` in `geomBoxplot()`.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/b526b48dbb57c968107aa6a6ac6f5cd2ca9bca58/docs/examples/jupyter-notebooks/boxplot_whisker_width.ipynb).

- New geometry `geomLabel()`.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/b526b48dbb57c968107aa6a6ac6f5cd2ca9bca58/docs/examples/jupyter-notebooks/geom_label.ipynb).

### Changed

- New tooltip style after applying `coordFlip()`  [[#580](https://github.com/JetBrains/lets-plot/issues/580)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/b526b48dbb57c968107aa6a6ac6f5cd2ca9bca58/docs/examples/jupyter-notebooks/coord_flip.ipynb).

### Fixed

- Density and area geoms: preserve the z-order when grouping [[#552](https://github.com/JetBrains/lets-plot/issues/552)].
- Boxplot, violin, crossbar: position dodge width=0.95 should be used by default [[#553](https://github.com/JetBrains/lets-plot/issues/553)].
- Unclear size unit of width [[#589](https://github.com/JetBrains/lets-plot/issues/589)].
- No tooltips for `geomBoxplot` with zero height [[#563](https://github.com/JetBrains/lets-plot/issues/563)].
- `geomText`: wrong label alignment with `hjust` 0 and 1 [[#592](https://github.com/JetBrains/lets-plot/issues/592)].
- Documentation for the `breaks` parameter in scales [[#507](https://github.com/JetBrains/lets-plot/issues/507)]


## [4.0.0] - 2022-07-25

**BREAKING CHANGES:** Due to refactorings performed in the source code, the v4.0.0 is no longer backward compatible with
earlier versions of
the Lets-Plot Kotlin API.

### Changed

- All previously deprecated API were removed.

* The prefix "**org**" was added to all package names in the
  project.

- Some API elements were moved from package `org.jetbrains.letsPlot` to a more specific subpackages:
  - _**Plot theme**_ elements were moved to subpackage `themes` 
  - _**Coordinate system**_ functions were moved to subpackage `coord`
  - _**Position adjustment**_ functions were moved to subpackage `pos`

* **Deprecated API**:
  - Position adjustment constants: `identity, stack, fill, dodge, nudge` and `jitterdodge` (defined in the `Pos` object),
    are now deprecated in favor of the correspondent top level elements defined in the (new) `org.jetbrains.letsPlot.pos` package:
    `positionIdentity, positionStack, positionFill, positionDodge(), positionNudge()` and `positionJitterDodge()`.

### Migrating to 4.0.0

**In Kotlin project**

- Update all `import` statements: `import jetbrains.letsPlot..` &rarr; `import org.jetbrains.letsPlot..`
- In all places in your project where the code needs to be updated, IntelliJ will show you a `deprecation WARNING` and will offer to fix this
  automatically.

**In Jupyter notebook**

- As soon as you start using 4.0.0 in you notebook, the only thing you will want to do is to manually replace all
  deprecated `Pos.abc` expressions with their new equivalents:
  - `Pos.identity` &rarr; `positionIdentity`
  - `Pos.stack` &rarr; `positionStack`
  - `Pos.fill` &rarr; `positionFill`
  - `Pos.dodge` &rarr; `positionDodge()`
  - `Pos.nudge` &rarr; `positionNudge()`
  - `Pos.jitterdodge` &rarr; `positionJitterDodge()`

> **NOTE:** If your notebook also uses another Kotlin library which depends on an older version of Lets-Plot, then the
> classloader may refuse to load classes from both libraries.
> If this is the case, then you will want to do **NOT** update your notebook to Lets-Plot v4.0.0 as yet:
>   - Make sure your notebook is not using the `%useLatestDescriptors` line magic
>   - Make sure you are using Kotlin Jupyter Kernel version 0.11.0.95 (or earlier), which bundles a previous version of Lets-Plot.

## [3.3.0] - 2022-06-27

### Added

- Global theme configuring with `LetsPlot.theme` property.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-3.3.0/default_theme.ipynb).

- Quantile-Quantile (Q-Q) plot:
  - geometries:
    - `geomQQ()`
    - `geomQQLine()`
    - `geomQQ2()`
    - `geomQQ2Line()`
  - stats:
    - `statQQ()`
    - `statQQLine()`
    - `statQQ2()`
    - `statQQ2Line()`
  - quick Q-Q: `qqPlot()`

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/3754904cd622548cebc8023fc773c4cd7c49b281/docs/examples/jupyter-notebooks/qq_plots.ipynb).

- Marginal plots: the `ggmarginal()` function.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/3754904cd622548cebc8023fc773c4cd7c49b281/docs/examples/jupyter-notebooks/marginal_layers.ipynb).

- Parameter `orientation` in geoms: `bar, boxplot, density, histogram, freqpoly, smooth, violin`.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/3754904cd622548cebc8023fc773c4cd7c49b281/docs/examples/jupyter-notebooks/y_orientation.ipynb).

- New in *plot theme*:
  - `face` parameter in `elementText()`.

    See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-3.3.0/set_font_faces.ipynb).

  - `panelBorder` parameter in `theme()` [[#542](https://github.com/JetBrains/lets-plot/issues/542)].

    See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-3.3.0/panel_border.ipynb).

  - *Tooltip* theme options, new parameters in `theme()`:
    - `tooltip` - tooltip rectangle options;
    - `tooltipText, tooltipTitleText` - tooltip text options;
    - `axisTooltipText, axisTooltipTextX, axisTooltipTextY` - axis tooltip text options.

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/3754904cd622548cebc8023fc773c4cd7c49b281/docs/examples/jupyter-notebooks/tooltips_theme.ipynb).

- `scaleColorGradientN()` and `scaleFillGradientN()` functions [[#504](https://github.com/JetBrains/lets-plot/issues/504)].

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-3.3.0/scale_color_gradientn.ipynb).
          

- `kotlinx.datetime` support.
- `arrow` parameter in `geomSegment` - specification for arrow heads, as created by `arrow()` function.


### Changed

- Potentially **breaking change**: deprecation level for all deprecated API raised to the **ERROR** level.
- `geomImage()` removed as it is not yet production ready.
- Default sampling type for `geom_violin` switched from `systematic` to `pick`.


### Fixed

- Labels out of plot when axisTextY="blank" [[#525](https://github.com/JetBrains/lets-plot/issues/525)].
- Outliers are not shown when boxplot' alpha=0.
- JFX rendering issue that causes tooltips to stuck [[#539](https://github.com/JetBrains/lets-plot/issues/539)].
- Support trim parameter in `density` and `ydensity` stats [[#62](https://github.com/JetBrains/lets-plot/issues/62)].
- `geom_violin`: add missing parameters `kernel`, `bw`, `adjust`, `n`, `fs_max` to signature and docstring.


## [3.2.0] - 2022-03-29

### Added

- New geometries:
  - `geomViolin()`

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/2be6958d014258555060bea8c74aa3b149fda72d/docs/examples/jupyter-notebooks/geom_violin.ipynb).

  - `geomDotplot()`

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/2be6958d014258555060bea8c74aa3b149fda72d/docs/examples/jupyter-notebooks/geom_dotplot.ipynb).

  - `geomYDotplot()`

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/2be6958d014258555060bea8c74aa3b149fda72d/docs/examples/jupyter-notebooks/geom_ydotplot.ipynb).


- Plot subtitle and caption:
  `subtitle` parameter in `ggtitle()` and `labs()`,
  `caption` parameter in `labs()`,
  `plotSubtitle` and `plotCaption` parameters in `theme()`.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/2be6958d014258555060bea8c74aa3b149fda72d/docs/examples/jupyter-notebooks/title_subtitle_caption.ipynb).

- Multi-line labels: The 'newline' character (`\n`) now works as `line break` in plot title, subtitle and caption, in legend's title and in tooltips.

- In tooltip customization API: the `title()` option defines a tooltip "title" text which will always appear above the rest of the tooltip content.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/2be6958d014258555060bea8c74aa3b149fda72d/docs/examples/jupyter-notebooks/tooltip_title.ipynb).

- Parameter `scales` in `facetGrid()/facetWrap()` [[#451](https://github.com/JetBrains/lets-plot/issues/451),
  [#479](https://github.com/JetBrains/lets-plot/issues/479)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/2be6958d014258555060bea8c74aa3b149fda72d/docs/examples/jupyter-notebooks/facets_free_scales.ipynb).


### Changed

- New tooltip style: rounded corners, bold label, colored marker inside the tooltip.
- Deprecated tooltip customization API: function `color()` will be removed in one of the future releases.
- 'Auto shrink': plots automatically shrink when necessary to fit width of the output (notebook) cell [[#488](https://github.com/JetBrains/lets-plot/issues/488)].

### Fixed

- Automatic detection of DateTime series [[#99](https://github.com/JetBrains/lets-plot-kotlin/issues/99)].
- Too limited mapping options in GenericAesMapping [[#82](https://github.com/JetBrains/lets-plot-kotlin/issues/82)]
- scaleColorManual Divide by Zero with 1 mapping [[#506](https://github.com/JetBrains/lets-plot/issues/506)].
- LinearBreaksHelper$Companion.computeNiceBreaks out of memory error [[#105](https://github.com/JetBrains/lets-plot-kotlin/issues/105)].
- CVE-2021-23792 in org.jetbrains.lets-plot:lets-plot-image-export@2.2.1 [[#497](https://github.com/JetBrains/lets-plot/issues/497)].
- Fix tooltips for `geom_histogram(stat='density')`.
- The axis tooltip overlaps the general tooltip [[#515](https://github.com/JetBrains/lets-plot/issues/515)].
- The multi-layer tooltip detection strategy will only be used if more than one layer provides tooltips.
  

## [3.1.1] - 2021-12-13

### Added

- `scaleXTime()` and `scaleYTime()`.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/a5047caf0f7affa5df2a4f195ced6d9bc6102e74/docs/examples/jupyter-notebooks/scale_time.ipynb).

- `plotBackground, legendBackground` parameters in `theme()` [[#485](https://github.com/JetBrains/lets-plot/issues/485)].
- `axisOntop, axisOntopX, axisOntopY` parameters in `theme()`

### Fixed

- It should automatically stringify enums [[#97](https://github.com/JetBrains/lets-plot-kotlin/issues/97)].
- Coord system limits do not work with x/y scale with transform [[#474](https://github.com/JetBrains/lets-plot/issues/474)].
- Provide 0-23 hour formatting [[#469](https://github.com/JetBrains/lets-plot/issues/469)].
- No tooltip shown when I'm trying to add an empty line [[#382](https://github.com/JetBrains/lets-plot/issues/382)].
- `coord_fixed()` should adjust dimensions of "geom" panel accordingly [[#478](https://github.com/JetBrains/lets-plot/issues/478)].
- The tooltip dependence on number of factors works separately by layers [[#481](https://github.com/JetBrains/lets-plot/issues/481)].
- Tooltip on y-axis looks wrong [[#393](https://github.com/JetBrains/lets-plot/issues/393)].
- Is kotlin-reflect really needed for lets-plot? [[#471](https://github.com/JetBrains/lets-plot/issues/471)].


## [3.1.0] - 2021-11-05

### Added

- `coordFlip()`.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/6e84077ede25219397c9b3e6aef63763a52b07ee/docs/examples/jupyter-notebooks/coord_flip.ipynb)
  .

- Date-time formatting support:
  - using date-time format pattern in tooltip format();
  - date/time scales apply date-time formatting to the `breaks`.

  See `Out[7, 8, 10]` in
  the [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/6e84077ede25219397c9b3e6aef63763a52b07ee/docs/examples/jupyter-notebooks/formatting_axes_etc.ipynb)
  .

- Pre-configured themes:
  - Standard ggplot2 themes: `themeGrey(), themeLight(), themeClassic(), themeMinimal()`;
  - Other themes: `themeMinimal2()` - the default theme, `themeNone()`.

- Theme modification: more parameters were added to the `theme()` function.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/6e84077ede25219397c9b3e6aef63763a52b07ee/docs/examples/jupyter-notebooks/themes.ipynb).

> Note: fonts size, family and face still can not be configured.

- `CorrPlot()` function now also accepts pre-computed correlation coefficients.

- Kotlin/JS IR: `xxx.klib` artifacts are now available.

  See: [sample Kotlin/JS IR app](https://github.com/alshan/lets-plot-mini-apps/tree/main/js-ir-frontend-app)


### Changed

- The size of fonts on plot was slightly increased all across the board.
- The default plot size was increased by 20%, it's now 600x400 px.
- **Deprecated API**: all `Theme.xxxBlank()` functions. Please use corresponding parameters in `theme()`.


### Fixed

- Ordering facets - the "order" value 0 disables facet ordering [[#454](https://github.com/JetBrains/lets-plot/issues/454)].
- Tooltips for discrete variables: add the dependence of the tooltip on the number of factors.
  The X-axis tooltip is always shown for discrete data.
- Unreadable breaks on axis [[#430](https://github.com/JetBrains/lets-plot/issues/430)].
                                          

## [3.0.2] - 2021-06-09

### Added

- Ordering categories:

  New parameters added to the `asDiscrete()` function:
  - `orderBy` - name of the variable by which the ordering will be performed;
  - `order` - ordering direction: 1 for ascending direction and -1 for descending (default).

  See: [as_discrete](https://github.com/JetBrains/lets-plot-kotlin/blob/98742bfaa7c685da75007f306b1662007a9eb6c2/docs/as_discrete.md).

### Changed 

- Upgraded dependencies:
  - Kotlin: 1.5.21
  - Lets-Plot: 2.1.0 (see [Lets-Plot CHANGELOG](https://github.com/JetBrains/lets-plot/blob/master/CHANGELOG.md))
  - Apache Batik: 1.14 [[#398](https://github.com/JetBrains/lets-plot/issues/398)]

## [3.0.1] - 2021-06-09

### Added

- The 'format' parameter in all scales [[76](https://github.com/JetBrains/lets-plot-kotlin/issues/76)].

### Changed

- Upgraded `kotlinx.html` version to 0.7.3 (was 0.7.2)

> In JVM projects it's no longer necessary to add `https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven` repository
> to the project configuration.

### Fixed

- Multilayer plots are corrupted [[#385](https://github.com/JetBrains/lets-plot/issues/385)].
- Import "lets-plot-common" transitively [[#78](https://github.com/JetBrains/lets-plot-kotlin/issues/78)]

## [3.0.0] - 2021-06-04

### Added

- In tooltip customization API:
  - `layerTooltips(variables)` - the new parameter `variables` defines a list of variable names, which values will be
    placed in the general multiline tooltip.
    See: [Tooltip Customization](https://github.com/JetBrains/lets-plot-kotlin/blob/dc0a84a900a7f7fbbee88dfccf9af73228fccf33/docs/tooltips.md#variables).


- [lets-plot-mini-apps](https://github.com/alshan/lets-plot-mini-apps) GitHub repository containing examples of using
  the Lets-Plot Kotlin API in JVM and Kotlin/JS projects.

### Changed

- **[BREAKING CHANGE]**: The CDN for delivering the Lets-Plot JavaScript library is
  now [JSDELIVR](https://www.jsdelivr.com/?docs=gh)
  (was CDNJS).

  New URLs:
  - Lets-Plot v2.0.3: https://cdn.jsdelivr.net/gh/JetBrains/lets-plot@v2.0.3/js-package/distr/lets-plot.min.js
  - The latest version: https://cdn.jsdelivr.net/gh/JetBrains/lets-plot/js-package/distr/lets-plot.min.js


- The project has been converted to a "multiplatform" project targeting JVM and JS platforms.

  To use Lets-Plot Kotlin API in your project, include dependencies:
  - JVM:  `implementation "org.jetbrains.lets-plot:lets-plot-kotlin-jvm:3.0.0"`
  - JS:   `implementation "org.jetbrains.lets-plot:lets-plot-kotlin-js:3.0.0"`

  See [USAGE_SWING_JFX_JS.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/USAGE_SWING_JFX_JS.md) for more details.


- **[BREAKING CHANGE]** The JVM artifact `lets-plot-kotlin-api` is no longer deployed: replaced with the
  equivalent `lets-plot-kotlin-jvm`.


- The artifact `lets-plot-kotlin-api-kernel` renamed to `lets-plot-kotlin-kernel` (this change only concerns Kotlin
  Jupyter Kernel)

### Fixed

- Poor font rendering in Swing/Batik. Related to:  [[#364](https://github.com/JetBrains/lets-plot/issues/364)]
- Exclude slf4j implementation from lets-plot-common [[#374](https://github.com/JetBrains/lets-plot/issues/374)]
- geom_boxplot: should be possible to create boxplot without specifying
  x-series [[#325](https://github.com/JetBrains/lets-plot/issues/325)]
- geom_hline: graph plotted outside of coordinate plane visible
  part [[#334](https://github.com/JetBrains/lets-plot/issues/334)]
- Draw geometry only once if layer has no aes mapping
  specified [[#73](https://github.com/JetBrains/lets-plot/issues/73)]
- Can't build plot: "Uncaught SyntaxError: Unexpected string" in a
  console [[#371](https://github.com/JetBrains/lets-plot/issues/371)]

## [2.0.1] - 2021-04-19

### Changed

- **[BREAKING CHANGE]**: The **groupId** of all maven artifacts is now **"org.jetbrains.lets-plot"** (was "
  org.jetbrains.lets-plot-kotlin")
- Built with Lets-Plot v2.0.2 (was v2.0.2).
- All **snake_case** symbols were deprecated and replaced with equivalent **camelCase**
  symbols [[#53](https://github.com/JetBrains/lets-plot-kotlin/discussions/53)].
- Maven artifacts published to [Maven Central](https://search.maven.org/search?q=lets-plot) (due to shutting down
  of [Bintray, JCenter](https://jfrog.com/blog/into-the-sunset-bintray-jcenter-gocenter-and-chartcenter/))

## [1.3.0] - 2021-03-22

### Added

- `facet_wrap()` function.
- in `facet_grid()` function:
  - Ascending/descending ordering of faceting values.
  - Formatting of faceting values.

  See: [Facets demo](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/4b8eca44cf9533b9e9e2114fe2f332f909459b2e/docs/examples/jupyter-notebooks/facets.ipynb)

- The `format` parameter in positional scales: formatting tick labels on X/Y axis. Supported types are `number`
  and `date/time`.

  Example:
   ```
   scale_x_datetime(format="%b %Y")
   scale_x_continuous(format="is {.2f}")
   ```

  Demo: [Formatting demo](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/4b8eca44cf9533b9e9e2114fe2f332f909459b2e/docs/examples/jupyter-notebooks/formatting_axes_etc.ipynb)

  See also: [Formatting](https://github.com/JetBrains/lets-plot-kotlin/blob/4b8eca44cf9533b9e9e2114fe2f332f909459b2e/docs/formats.md)


- The `guides()` function [[#52](https://github.com/JetBrains/lets-plot/issues/52)].

- In tooltip customization API:
  - option `color` overrides the default tooltip color:
      ```
      geom_xxx(tooltips=layer_tooltips().color("red"))
      ```

  See: [Tooltip Customization](https://github.com/JetBrains/lets-plot-kotlin/blob/4b8eca44cf9533b9e9e2114fe2f332f909459b2e/docs/tooltips.md).

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

  Example: [correlation_plot.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/1ed0ba7a2ed7f458a8a24c1ccee729c7d1b77449/docs/examples/jupyter-notebooks/correlation_plot.ipynb)

- The `gggrid()` plot layout utility

  Example: [correlation_plot.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/1ed0ba7a2ed7f458a8a24c1ccee729c7d1b77449/docs/examples/jupyter-notebooks/correlation_plot.ipynb)

- In tooltip customization API:
    - options: `center` and `middle` (anchor).
    - option 'minWidth'.

  Example: [tooltip_config.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/1ed0ba7a2ed7f458a8a24c1ccee729c7d1b77449/docs/examples/jupyter-notebooks/tooltip_config.ipynb)

- The `naText` parameter in `geom_text()`

### Changed

- Built with Lets-Plot v1.5.6 (was v1.5.4).

  See Lets-Plot [CHANGELOG.md](https://github.com/JetBrains/lets-plot/blob/master/CHANGELOG.md) for changes and fixes in
  1.5.5, 1.5.6.

## [1.1.0] - 2020-11-20

### Added

- GeoTools support (see [geotools.md](https://github.com/JetBrains/lets-plot-kotlin/blob/8209109b9a1630b509baea03ae6e7970ff90e13a/docs/geotools.md)).
- API for tooltip customization (
  see [tooltips.md](https://github.com/JetBrains/lets-plot-kotlin/blob/8209109b9a1630b509baea03ae6e7970ff90e13a/docs/tooltips.md)).
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
