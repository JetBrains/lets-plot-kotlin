## [4.12.0] - 2025-11-dd

This release is 100% compatible with Lets-Plot [v 4.8.0](https://github.com/JetBrains/lets-plot/releases/tag/v4.7.3),
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


### Changed

- [**BREAKING**] Explicit `group` aesthetic now overrides default grouping behavior instead of combining with it [[#1401](https://github.com/JetBrains/lets-plot/issues/1401)].

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/group_override_defaults.html).

> [!IMPORTANT]
> Previously, setting `group="variable"` would group by both the explicit variable AND any discrete
> aesthetics (color, shape, etc.). \
> Now it groups ONLY by the explicit variable, matching `ggplot2` behavior. \
> Use `group=listOf(var1, var2, ...)` to group by multiple variables explicitly, \
> and `group=emptyList<Any>()` to disable any grouping.

- Missing values in `geomLine(), geomPath(), geomRibbon()`, and `geomArea()` create gaps in geometries instead of being interpolated over [[#818](https://github.com/JetBrains/lets-plot/issues/818)], [[#1406](https://github.com/JetBrains/lets-plot/issues/1406)].

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/missing_values_line_path_area_ribbon.html).

- `theme`: the `exponentFormat` default value changed to `"pow"` - superscript powers of 10 (was e-notation).

- The multi-layer line plot now shows tooltips for each series simultaneously, in the same way that a single-layer plot with color mapped to series does.


### Fixed

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

