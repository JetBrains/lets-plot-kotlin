# What Is New in 4.13.0

**Kotlin**: v2.2.20 (was v1.9.25).

> [!IMPORTANT]
>
> **Artifact changes in the core Lets-Plot library** (v4.9.0):
> - **New** artifact for JVM Swing applications: `org.jetbrains.lets-plot:lets-plot-swing`.
    This artifact provides the `SwingPlotPanel` class, which can be used to display plots in Swing applications instead of the now-obsolete `DefaultPlotPanelBatik`.
    For details, see the [jvm-swing-app](https://github.com/alshan/lets-plot-mini-apps/tree/main/jvm-swing-app) example in the "lets-plot-mini-apps" repository.
> - **[BREAKING]**: Removed JavaFX artifacts.
    The `org.jetbrains.lets-plot:lets-plot-jfx` artifact is no longer available.
    Replace it with new `org.jetbrains.lets-plot:lets-plot-swing` dependency and use `SwingPlotPanel` instead of `DefaultPlotPanelJfx`.
    For details, see the [jvm-javafx-app](https://github.com/alshan/lets-plot-mini-apps/tree/main/jvm-javafx-app) example in the "lets-plot-mini-apps" repository.
> - **[BREAKING]**: Removed `plot-image-export` module.
    The `org.jetbrains.lets-plot:lets-plot-image-export` artifact is no longer available.
    The `PlotImageExport` utility has been moved to the `platf-awt` module: `org.jetbrains.letsPlot.awt.plot.PlotImageExport`.
    The required `org.jetbrains.lets-plot:platf-awt` dependency is likely already present in your project.

- **Statistical Summaries Directly on `geomSmooth()` Plot Layer**

  The `geomSmooth()` layer now includes a `labels` parameter designed to display statistical summaries of the fitted model directly on the plot.
  This parameter accepts a `smoothLabels()` object, which provides access to model-specific variables like $R^2$ and the regression equation.

  <img src="smooth_summary.png" alt="changelog/4.13.0/smooth_summary.png" width="400" height="265" />

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.13.0/smooth_summary.html).

- **Plot Tags**

  Plot tags are short labels attached to a plot.

  <img src="plot_tags.png" alt="changelog/4.13.0/plot_tags.png" width="600" height="185" />

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.13.0/plot_tags.html) and updated [Plot Layout Diagrams](https://lets-plot.org/kotlin/presentation-options.html#plot-layout-diagrams).

- **New `geomBracket()` and `geomBracketDodge()` Geometries**

  New geometries designed primarily for significance bars (*p-values*) annotations in categorical plots.

  <img src="geom_bracket.png" alt="changelog/4.13.0/geom_bracket.png" width="400" height="261" />

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.13.0/geom_bracket.html).

- **Custom Color Palettes in `geomImshow()`**

  The `cmap` parameter now allows you to specify a list of hex color codes for visualizing grayscale images.
  Also, the new `cguide` parameter lets you customize the colorbar for grayscale images.

  <img src="image_custom_cmap.png" alt="changelog/4.13.0/image_custom_cmap.png" width="400" height="248" />

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.13.0/image_custom_cmap.html).

- **New `palette()` Method in Color Scales**

  Generates a list of hex color codes that can be used with `scaleColorManual()` to maintain consistent colors across multiple plots.

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.13.0/scale_color_palette.html).

- **New `overflow` parameter in `scaleColorBrewer()`, `scaleFillBrewer()`**

  Controls how colors are generated when more colors are needed than the palette provides.
  Options: `'interpolate'` (`'i'`), `'cycle'` (`'c'`), `'generate'` (`'g'`).

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.13.0/scale_brewer_overflow.html).

- **New `breakWidth` Parameter in Positional Scales**

  Specifies a fixed distance between axis breaks.

  See examples:
  - [datetime scale](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.13.0/scale_break_width_datetime.html)
  - [time (duration) scale](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.13.0/scale_break_width_duration.html)
  - [log10 scale](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.13.0/scale_break_width_log10.html)

- **Axis Minor Ticks Customization**

  The `axisMinorTicks` and `axisMinorTicksLength` parameters in `theme()`.

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.13.0/axis_minor_ticks.html).

- **Pan/Zoom in `gggrid()` with Shared Axes**

  Pan/Zoom now propagates across subplots with shared axes (`sharex`/`sharey`).

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/refs/heads/master/docs/examples/jupyter-notebooks/f-4.13.0/gggrid_scale_share_zoom.html).

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

## Changelog

See [CHANGELOG.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/CHANGELOG.md) for other changes and fixes.