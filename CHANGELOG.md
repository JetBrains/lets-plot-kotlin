# lets-plot-kotlin changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres
to [Semantic Versioning](https://semver.org/spec/v2.0.0.html). All scales should have the 'format' parameter.


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

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/position_stack.ipynb).

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

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/complete_themes.ipynb).

- Color schemes (flavors) applicable to existing themes:
  - `flavorDarcula()`
  - `flavorSolarizedLight()`
  - `flavorSolarizedDark()`
  - `flavorHighContrastLight()`
  - `flavorHighContrastDark()`

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/theme_flavors.ipynb).

- Viridis color scales: `scaleColorViridis()`, `scaleFillViridis()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/colors_viridis.ipynb).

- New parameters in theme's `elementText()`:
  - `size, family`
    ([example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/font_size_and_family.ipynb))
  - `hjust, vjust` for plot title, subtitle, caption, legend and axis titles
    ([example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/hjust_vjust.ipynb))
  - `margin` for plot title, subtitle, caption, axis titles and tick labels
    ([example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/text_margins.ipynb))

- Parameter `whiskerWidth` in `geomBoxplot()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/boxplot_whisker_width.ipynb).

- New geometry `geomLabel()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_label.ipynb).

### Changed

- New tooltip style after applying `coordFlip()`  [[#580](https://github.com/JetBrains/lets-plot/issues/580)].

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/coord_flip.ipynb).

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

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/qq_plots.ipynb).

- Marginal plots: the `ggmarginal()` function.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/marginal_layers.ipynb).

- Parameter `orientation` in geoms: `bar, boxplot, density, histogram, freqpoly, smooth, violin`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/y_orientation.ipynb).

- New in *plot theme*:
  - `face` parameter in `elementText()`.

    See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-3.3.0/set_font_faces.ipynb).

  - `panelBorder` parameter in `theme()` [[#542](https://github.com/JetBrains/lets-plot/issues/542)].

    See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-3.3.0/panel_border.ipynb).

  - *Tooltip* theme options, new parameters in `theme()`:
    - `tooltip` - tooltip rectangle options;
    - `tooltipText, tooltipTitleText` - tooltip text options;
    - `axisTooltipText, axisTooltipTextX, axisTooltipTextY` - axis tooltip text options.

    See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/tooltips_theme.ipynb).

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

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_violin.ipynb).

  - `geomDotplot()`

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_dotplot.ipynb).

  - `geomYDotplot()`

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_ydotplot.ipynb).


- Plot subtitle and caption:
  `subtitle` parameter in `ggtitle()` and `labs()`,
  `caption` parameter in `labs()`,
  `plotSubtitle` and `plotCaption` parameters in `theme()`.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/title_subtitle_caption.ipynb).

- Multi-line labels: The 'newline' character (`\n`) now works as `line break` in plot title, subtitle and caption, in legend's title and in tooltips.

- In tooltip customization API: the `title()` option defines a tooltip "title" text which will always appear above the rest of the tooltip content.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/tooltip_title.ipynb).

- Parameter `scales` in `facetGrid()/facetWrap()` [[#451](https://github.com/JetBrains/lets-plot/issues/451),
  [#479](https://github.com/JetBrains/lets-plot/issues/479)].

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/facets_free_scales.ipynb).


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

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/scale_time.ipynb).

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

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/coord_flip.ipynb)
  .

- Date-time formatting support:
  - using date-time format pattern in tooltip format();
  - date/time scales apply date-time formatting to the `breaks`.

  See `Out[7, 8, 10]` in
  the [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/formatting_axes_etc.ipynb)
  .

- Pre-configured themes:
  - Standard ggplot2 themes: `themeGrey(), themeLight(), themeClassic(), themeMinimal()`;
  - Other themes: `themeMinimal2()` - the default theme, `themeNone()`.

- Theme modification: more parameters were added to the `theme()` function.

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/themes.ipynb).

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

  See: [as_discrete](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/as_discrete.md).

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
    See: [Tooltip Customization](https://github.com/JetBrains/lets-plot/blob/master/docs/tooltips.md#variables).


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

  See [README_DEV.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/README_DEV.md) for more details.


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
