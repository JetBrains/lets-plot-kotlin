# What Is New in 4.11.0

- **Time Series Plotting**

  - Support temporal data types from `kotlinx.datetime`, `java.time`, and `java.util`.

  - Support for timezone-aware `java.time.ZonedDateTime` and `java.time.OffsetDateTime` objects.

  <img src="time_date_datetime.png" alt="changelog/4.11.0/time_date_datetime.png" width="400" height="237"/>

  See [Date-time](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/time_date_datetime.ipynb) cookbook.
  <br/><br/>

  <img src="bitcoin_trading.png" alt="changelog/4.11.0/bitcoin_trading.png" width="400" height="237"/>

  See [Bitcoin trading](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/demo/trading_chart.ipynb) demo.
  <br/><br/>

- **`geomSina()` Geometry**

  <img src="geom_sina.png" alt="changelog/4.11.0/geom_sina.png" width="400" height="276"/>

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/geom_sina.ipynb).

- **`geomTextRepel()` and `geomLabelRepel()` Geometries**

  <img src="geom_repel.png" alt="changelog/4.11.0/geom_repel.png" width="400" height="232"/>

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/ggrepel.ipynb).

- **`waterfallPlot()` Chart**

  - Annotations support via `relativeLabels` and `absoluteLabels` parameters.
    <br/><br/>

    <img src="waterfall_plot_annotations.png" alt="changelog/4.11.0/waterfall_plot_annotations.png" width="400" height="253"/>

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/waterfall_plot_annotations.ipynb).
    <br/><br/>

  - Support for combining waterfall bars with other geometry layers.
    <br/><br/>

    <img src="waterfall_plot_layers.png" alt="changelog/4.11.0/waterfall_plot_layers.png" width="400" height="227"/>

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/waterfall_plot_layers.ipynb).

- **Continuous Data on Discrete Scales**

  Continuous data when used with discrete positional scales is no longer transformed to discrete data. <br/>
  Instead, it remains continuous, allowing for precise positioning of continuous elements relative to discrete ones.
  <br/><br/>

  <img src="combo_discrete_continuous.png" alt="changelog/4.11.0/combo_discrete_continuous.png" width="400" height="151"/>

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/numeric_data_on_discrete_scale.ipynb).

  > [!TIP]
  > New way of handling continuous data on discrete scales could potentially break existing plots.
  > If you want to restore a broken plot to its original form, you can use the [`asDiscrete()`](https://lets-plot.org/kotlin/as-discrete.html) function to annotate continuous data as discrete.

- **Plot Layout**

  The default plot layout has been improved to better accommodate axis labels and titles. <br/>
  Also, new `theme()` options `axisTextSpacing`, `axisTextSpacingX`, and `axisTextSpacingY` control spacing between axis ticks and labels.
  <br/><br/>

  <img src="plot_layout_diagram.png" alt="changelog/4.11.0/plot_layout_diagram.png" width="400" height="175"/>

  See new [Plot Layout Diagrams](https://lets-plot.org/kotlin/presentation-options.html#plot-layout-diagrams) showing various layout options and their effects on plot appearance.

- **And More**

  See [CHANGELOG.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/CHANGELOG.md) for a full list of changes.

## Recent Updates in the Gallery

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
            <img alt="Bitcoin daily trend visualization (2024)" src="square-trading_chart.png"/>
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