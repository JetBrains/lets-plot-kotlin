package org.jetbrains.letsPlot.bistro.waterfall

/**
 * A waterfall plot shows the cumulative effect of sequentially introduced positive or negative values.
 *
 * ## Examples
 *
 * - [waterfall_plot.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/waterfall_plot.ipynb)
 *
 * @param data The data to be displayed in this layer.
 * @param x Name of a variable.
 * @param y Name of a numeric variable.
 * @param measure Kind of a calculation.
 *  Values in 'measure' column could be:
 *  - "absolute" - the value is shown as is;
 *  - "relative" - the value is shown as a difference from the previous value;
 *  - "total" - the value is shown as a cumulative sum of all previous values.
 *
 * @param group Grouping variable.
 *  Each group calculates its own statistics.
 * @param color Color of the box boundary lines.
 *  Use "flow_type" to color lines by the direction of the flow.
 * @param fill Fill color of the boxes.
 *  Use "flow_type" to color boxes by the direction of the flow.
 * @param size default = 0.0.
 *  Line width of the box boundary lines.
 * @param alpha Transparency level of the boxes. Understands numbers between 0 and 1.
 * @param linetype Int or String.
 *  Type of the box boundary lines.
 *  Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *  5 = "longdash", 6 = "twodash".
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
    size: Double? = null,
    alpha: Double? = null,
    linetype: Any? = null,
    width: Double? = null,
    showLegend: Boolean? = null,
    relativeTooltips: Any? = null,
    absoluteTooltips: Any? = null,
    calcTotal: Boolean? = null,
    totalTitle: String? = null,
    sortedValue: Boolean? = null,
    threshold: Double? = null,
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