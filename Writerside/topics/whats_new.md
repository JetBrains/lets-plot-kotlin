# What Is New in 4.12.0

- **`geomPointDensity()` Geometry**

  <img src="geom_pointdensity.png" alt="changelog/4.12.0/geom_pointdensity.png" width="400" height="246"/>

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/geom_pointdensity.html).

- **Explicit `group` aesthetic now overrides default grouping behavior instead of combining with it**

  > [!IMPORTANT]
  > **BREAKING CHANGE:**
  >
  > Previously, setting `group='variable'` would group by both the explicit variable AND any discrete
  > aesthetics (color, shape, etc.). \
  > Now it groups ONLY by the explicit variable, matching `ggplot2` behavior. \
  > Use `group=[var1, var2, ...]` to group by multiple variables explicitly, \
  > and `group=[]` to disable any grouping.

  <img src="group_override_defaults.png" alt="changelog/4.12.0/group_override_defaults.png" width="400" height="263">

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/group_override_defaults.html).

- **`gggrid()`: support for shared legends (parameter `guides`)**

  <img src="gggrid_legend_collect.png" alt="changelog/4.12.0/group_override_defaults.png" width="500" height="172">

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/gggrid_legend_collect.html).

- **Better handling of missing values in `geomLine()`, `geomPath()`, `geomRibbon()`, and `geomArea()`**

  <img src="missing_values_ribbon.png" alt="changelog/4.12.0/missing_values_ribbon.png" width="500" height="192">

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/missing_values_line_path_area_ribbon.html).

- **`geomHistogram()`: custom bin bounds (parameter `breaks`)**

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/geom_histogram_param_breaks.html).

- **Legend automatically wraps to prevent overlap — up to 15 rows for vertical legends and 5 columns for horizontal ones**

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/legend_wrap.html).

- **`flavorStandard()` resets the theme's default color scheme**

  Use to override other flavors or make defaults explicit.

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/flavor_standard.html).

- **`theme` methods controlling legend justification: `legendJustificationTop()`, `legendJustificationRight()`, `legendJustificationBottom()`, and `legendJustificationLeft()`**

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/legend_justification.html).

- **`ggtb()`: Added `sizeZoomin` and `sizeBasis` parameters to control point size scaling behavior when zooming (works with `geomPoint` and related layers).**

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.12.0/ggtb_size_zoomin.html).

- **And More**

  See [CHANGELOG.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/CHANGELOG.md) for a full list of changes.

## Recent Updates in the [Gallery](gallery.md)

<list columns="4">
    <li>
        <a href="%nb-raincloud%">
            <img alt="Raincloud plot" src="square-raincloud.png"/>
        </a>
    </li>
    <li>
        <a href="%nb-europe_capitals%">
            <img alt="Lets-Plot GeoTools with texts and labels" src="square-europe_capitals.png"/>
        </a>
    </li>
    <li>
        <a href="%nb-trading_chart%">
            <img alt="Bitcoin daily trend visualization" src="square-trading_chart.png"/>
        </a>
    </li>
    <li>
        <a href="%nb-magnifier_inset%">
            <img alt="Creating magnifier inset effect with ggbunch()" src="square-magnifier_inset.png"/>
        </a>
    </li>
    <li>
        <a href="%nb-theme_legend_scheme%">
            <img alt="Theme legend scheme" src="square-theme_legend_scheme.png"/>
        </a>
    </li>
    <li>
        <a href="%nb-interact_pan_zoom%">
            <img alt="Zoom and Pan interactivity" src="square-interact_pan_zoom.png"/>
        </a>
    </li>
    <li>
        <a href="%nb-lp_verse%">
            <img alt="The observable LP-verse" src="square-lp_verse.png"/>
        </a>
    </li>
    <li>
        <a href="%nb-sunshine_hours%">
            <img alt="Sunshine hours" src="square-sunshine_hours.png"/>
        </a>
    </li>
</list>

## Change Log

See [CHANGELOG.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/CHANGELOG.md) for other changes and fixes.