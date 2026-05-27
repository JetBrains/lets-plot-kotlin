# What Is New in 4.14.0

- **`ggdeck()`**

  The new `ggdeck()` function overlays multiple independent plots in a shared plotting area.
  Typically, all plots share one axis — enabling dual-axis charts and multivariate comparisons.

  - **Dual Axis:**

    <img src="ggdeck_dual_axis.png" alt="changelog/4.14.0/ggdeck_dual_axis.png" width="550" height="295">

    See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/master/docs/examples/jupyter-notebooks/f-4.14.0/ggdeck_dual_axis.html).

  - **Multivariate Comparison:**

    <img src="ggdeck_plot_overlay.png" alt="changelog/4.14.0/ggdeck_plot_overlay.png" width="600" height="283">

    See [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/master/docs/examples/jupyter-notebooks/f-4.14.0/ggdeck_plot_overlay.html).

- **Alpha Channel in Color Strings**

  - Named colors accept an opacity suffix after a slash: `"steelblue/0.35"`.

  - Hex colors accept an alpha channel: `#RRGGBBAA` or short form `#RGBA`.

  <img src="color_alpha_componnet.png" alt="changelog/4.14.0/color_alpha_componnet.png" width="400" height="214">

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/master/docs/examples/jupyter-notebooks/f-4.14.0/color_alpha.html).

- **Text Angle in Facet Strip Labels**

  Facet strip labels can now be rotated via the `angle` parameter of `elementText()`, applied to `stripText`, `stripTextX`, or `stripTextY`.

  Thanks to a contribution by [tentrillion](https://github.com/tentrillion).

  <img src="facet_strip_text_angle.png" alt="changelog/4.14.0/facet_strip_text_angle.png" width="400" height="225">

  See: [example notebook](https://raw.githack.com/JetBrains/lets-plot-kotlin/master/docs/examples/jupyter-notebooks/f-4.14.0/strip_text_angle.html).

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