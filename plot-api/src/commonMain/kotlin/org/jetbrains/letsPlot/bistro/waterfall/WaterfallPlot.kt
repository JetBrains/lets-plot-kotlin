package org.jetbrains.letsPlot.bistro.waterfall

/**
 * A waterfall plot shows the cumulative effect of sequentially introduced positive or negative values.
 *
 * ## Notes
 *
 * Computed variables:
 *
 * - ..x.. : category id.
 * - ..xlabel.. : category name.
 * - ..ymin.. : lower value of the change.
 * - ..ymax.. : upper value of the change.
 * - ..measure.. : kind of a calculation: absolute, relative or total.
 * - ..flow_type.. : direction of the flow: increasing, decreasing, or the result (total).
 * - ..initial.. : initial value of the change.
 * - ..value.. : current cumsum (result of the change) or absolute value (depending on the "measure" column).
 * - ..dy.. : value of the change.
 *
 * ## Examples
 *
 * - [waterfall_plot.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/waterfall_plot.ipynb)
 *
 * @param data The data to be displayed in this layer.
 * @param x Name of a variable.
 * @param y Name of a numeric variable.
 * @param measure Kind of a calculation.
 * It takes the name of a data column.
 * The values in the column could be:
 *  - "absolute" - the value is shown as is;
 *  - "relative" - the value is shown as a difference from the previous value;
 *  - "total" - the value is shown as a cumulative sum of all previous values.
 *
 * @param group Grouping variable.
 *  Each group calculates its own statistics.
 * @param color Color of the box boundary lines.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 *  Use "flow_type" to color lines by the direction of the flow.
 *  Flow type names: "Absolute", "Increase", "Decrease" and "Total".
 *  You could use these names to change the default colors with the `scaleColorManual()` function.
 * @param fill Fill color of the boxes.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 *  Use "flow_type" to color boxes by the direction of the flow.
 *  Flow type names: "Absolute", "Increase", "Decrease" and "Total".
 *  You could use these names to change the default colors with the `scaleFillManual()` function.
 * @param size default = 0.0.
 *  Line width of the box boundary lines.
 * @param alpha Transparency level of the boxes. Understands numbers between 0 and 1.
 * @param linetype Int or String or List or Pair.
 *  Type of the box boundary lines.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param width default = 0.9.
 *  Width of the boxes. Typically range between 0 and 1.
 *  Values that are greater than 1 lead to overlapping of the boxes.
 * @param showLegend default = false.
 *  true - show the legend.
 * @param relativeTooltips Result of the call to the `layerTooltips()` function.
 *  Tooltips for boxes with relative values.
 *  Specifies appearance, style and content.
 *  When "none", tooltips are not shown.
 *  When "detailed", a more detailed (compared to the default) version of the tooltips is shown.
 * @param absoluteTooltips Result of the call to the `layerTooltips()` function.
 *  Tooltips for boxes with absolute values.
 *  Specifies appearance, style and content.
 *  When "none", tooltips are not shown.
 *  When "detailed", a more detailed (compared to the default) version of the tooltips is shown.
 * @param base default = 0.0.
 *  Values with measure "absolute" or "total" are relative to this value.
 * @param sortedValue default = false.
 *  Sorts categories by absolute value of the changes.
 * @param threshold Groups all categories under a certain threshold value into "Other" category.
 * @param maxValues Groups all categories with the smallest changes, except the first `maxValues`, into "Other" category.
 * @param calcTotal default = true.
 *  Setting the `calcTotal` to true will put the final cumulative sum into a new separate box.
 *  Taken into account only if the "measure" column isn't provided.
 * @param totalTitle The header of the last box with the final cumulative sum, if "measure" column isn't provided.
 *  Also used as a title in the legend for columns of type "total".
 * @param hline Horizontal line passing through 0.
 *  Set "blank" or result of `elementBlank()` to draw nothing.
 *  Set `elementLine()` to specify parameters.
 * @param hlineOntop default = true.
 *  Option to place horizontal line over the other layers.
 * @param connector Line between neighbouring boxes connecting the end of the previous box and the beginning of the next box.
 *  Set "blank" or result of `elementBlank()` to draw nothing.
 *  Set `elementLine()` to specify parameters.
 * @param label Label on the box.
 *  Shows change value.
 *  Set "blank" or result of `elementBlank()` to draw nothing.
 *  Set `elementText()` to specify parameters.
 *  Use "flow_type" for `color` parameter of the `elementText()` to color labels by the direction of the flow.
 * @param labelFormat Format used to transform label mapping values to a string.
 *  For more info see: [formats.html](https://lets-plot.org/kotlin/formats.html)
 *  Note: the "$" must be escaped as "\$".
 *
 *  Examples:
 *  - ".2f" -> "12.45"
 *  - "Score: {.2f}" -> "Score: 12.45"
 *  - "Score: {}" -> "Score: 12.454789"
 *
 */
fun waterfallPlot(
    data: Map<*, *>,
    x: String,
    y: String,
    measure: String? = null,
    group: String? = null,
    color: String? = null,
    fill: String? = null,
    size: Number? = null,
    alpha: Number? = null,
    linetype: Any? = null,
    width: Number? = null,
    showLegend: Boolean? = null,
    relativeTooltips: Any? = null,
    absoluteTooltips: Any? = null,
    base: Number? = null,
    calcTotal: Boolean? = null,
    totalTitle: String? = null,
    sortedValue: Boolean? = null,
    threshold: Number? = null,
    maxValues: Int? = null,
    hline: Any? = null,
    hlineOntop: Boolean? = null,
    connector: Any? = null,
    label: Any? = null,
    labelFormat: String? = null
) = WaterfallPlotBuilder(
    data = data,
    x = x,
    y = y,
    measure = measure,
    group = group,
    color = color,
    fill = fill,
    size = size,
    alpha = alpha,
    linetype = linetype,
    width = width,
    showLegend = showLegend,
    relativeTooltips = relativeTooltips,
    absoluteTooltips = absoluteTooltips,
    base = base,
    calcTotal = calcTotal,
    totalTitle = totalTitle,
    sortedValue = sortedValue,
    threshold = threshold,
    maxValues = maxValues,
    hline = hline,
    hlineOntop = hlineOntop,
    connector = connector,
    label = label,
    labelFormat = labelFormat
).build()