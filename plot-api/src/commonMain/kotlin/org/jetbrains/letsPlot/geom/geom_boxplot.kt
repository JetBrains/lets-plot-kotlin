/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom.boxplot
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.*
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.BoxplotAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.BoxplotMapping
import org.jetbrains.letsPlot.intern.layer.geom.BoxplotParameters
import org.jetbrains.letsPlot.intern.layer.geom.PointMapping
import org.jetbrains.letsPlot.intern.layer.stat.BoxplotStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.BoxplotStatParameters
import org.jetbrains.letsPlot.pos.positionDodge
import org.jetbrains.letsPlot.tooltips.TooltipOptions

/**
 * Displays the distribution of data based on a five number summary ("minimum", first quartile (Q1), 
 * median, third quartile (Q3), and "maximum"), and "outlying" points individually.
 *
 * ## Notes
 *
 * Computed variables:
 *
 * - ..lower.. : lower hinge, 25% quantile.
 * - ..middle.. : median, 50% quantile.
 * - ..upper.. : upper hinge, 75% quantile.
 * - ..ymin.. : lower whisker = smallest observation greater than or equal to lower hinge - 1.5 * IQR.
 * - ..ymax.. : upper whisker = largest observation less than or equal to upper hinge + 1.5 * IQR.
 *
 * To hide axis tooltips, set "blank" or the result of [elementBlank()][org.jetbrains.letsPlot.themes.elementBlank]
 * to the `axisTooltip` or `axisTooltipX` parameter of the [theme()][org.jetbrains.letsPlot.themes.theme].
 *
 * ## Examples
 *
 * - [distributions.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/distributions.ipynb)
 *
 * - [stat_boxplot_outlier.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/stat_boxplot_outlier.ipynb).
 *
 * @param data The data to be displayed. If null, the default, the data is inherited
 *  from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat default = `Stat.boxplot()`. The statistical transformation to use on the data for this layer.
 *  Supported transformations: [Stat.identity][org.jetbrains.letsPlot.Stat.identity], [Stat.bin()][org.jetbrains.letsPlot.Stat.bin], [Stat.count()][org.jetbrains.letsPlot.Stat.count], etc. see [Stat][org.jetbrains.letsPlot.Stat].
 * @param position default = `positionDodge()`. Position adjustment: `positionIdentity`, 
 *  `positionStack()`, `positionDodge()`, etc. see [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
 * @param showLegend default = true.
 *  false - do not show legend for this layer.
 * @param inheritAes default = true.
 *  false - do not combine the layer aesthetic mappings with the plot shared mappings.
 * @param manualKey String or result of the call to the `layerKey()` function.
 *  The key to show in the manual legend. Specifies the text for the legend label or advanced settings using the `layerKey()` function.
 * @param tooltips Result of the call to the [layerTooltips()][org.jetbrains.letsPlot.tooltips.layerTooltips] function.
 *  Specifies appearance, style and content.
 *  Set `tooltips = tooltipsNone` to hide tooltips from the layer.
 * @param orientation Specifies the axis that the layer's stat and geom should run along.
 *  The default value (`null`) automatically determines the orientation based on the aesthetic mapping.
 *  If the automatic detection doesn't work, it can be set explicitly by specifying the "x" or "y" orientation.
 * @param outlierAlpha Default transparency aesthetic for outliers.
 * @param outlierColor Color aesthetic for outliers.
 * @param outlierFill Fill aesthetic for outliers.
 * @param outlierShape Shape aesthetic for outliers.
 *  For more info see: [aesthetics.html#point-shapes](https://lets-plot.org/kotlin/aesthetics.html#point-shapes).
 * @param outlierSize Size aesthetic for outliers.
 * @param outlierStroke Default width of the border for outliers.
 * @param outlierAngle Rotation angle of the shape for outliers, in degrees.
 * @param varWidth default = false. If false make a standard box plot.
 *  If true, boxes are drawn with widths proportional to the square-roots of the number of
 *  observations in the groups.
 * @param fatten default = 1.0.
 *  A multiplicative factor applied to size of the middle bar.
 * @param whiskerWidth default = 0.0.
 *  A multiplicative factor applied to the box width to draw horizontal segments on whiskers.
 * @param widthUnit default = "res".
 *  Unit for the width of the boxplot.
 *  Possible values:
 *
 *  - "res": the unit equals the smallest distance between adjacent boxes along the corresponding axis;
 *  - "identity": a unit of 1 corresponds to a difference of 1 in data space;
 *  - "size": a unit of 1 corresponds to the diameter of a point with size=1;
 *  - "px": the unit is measured in screen pixels.
 *
 * @param coef default = 1.5.
 *  Length of the whiskers as multiple of IQR.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param x x-axis coordinate for vertical boxplot.
 * @param lower Lower hinge, 25% quantile for vertical boxplot.
 * @param middle Median, 50% quantile for vertical boxplot.
 * @param upper Upper hinge, 75% quantile for vertical boxplot.
 * @param ymin Lower whisker - the smallest observation greater than or equal to the lower hinge - 1.5 * IQR for vertical boxplot.
 * @param ymax Upper whisker - the largest observation less than or equal to the upper hinge + 1.5 * IQR for vertical boxplot.
 * @param y y-axis coordinate for horizontal boxplot.
 * @param xlower Lower hinge, 25% quantile for horizontal boxplot.
 * @param xmiddle Median, 50% quantile for horizontal boxplot.
 * @param xupper Upper hinge, 75% quantile for horizontal boxplot.
 * @param xmin Lower whisker - the smallest observation greater than or equal to the lower hinge - 1.5 * IQR for horizontal boxplot.
 * @param xmax Upper whisker - the largest observation less than or equal to the upper hinge + 1.5 * IQR for horizontal boxplot.
 * @param width Width of boxplot. Typically ranges between 0 and 1. Values that are greater than 1 lead to overlapping of the boxes.
 * @param alpha Transparency level of a layer.
 *  Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param size Lines width.
 * @param linetype Type of the line of border.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
fun geomBoxplot(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.boxplot(),
    position: PosOptions = positionDodge(),
    showLegend: Boolean = true,
    inheritAes: Boolean? = null,
    manualKey: Any? = null,
    tooltips: TooltipOptions? = null,
    orientation: String? = null,
    x: Number? = null,
    y: Number? = null,
    lower: Number? = null,
    middle: Number? = null,
    upper: Number? = null,
    ymin: Number? = null,
    ymax: Number? = null,
    xlower: Number? = null,
    xmiddle: Number? = null,
    xupper: Number? = null,
    xmin: Number? = null,
    xmax: Number? = null,
    alpha: Number? = null,
    color: Any? = null,
    fill: Any? = null,
    size: Number? = null,
    stroke: Number? = null,
    angle: Number? = null,
    linetype: Any? = null,
    shape: Any? = null,
    width: Any? = null,
    weight: Any? = null,
    outlierAlpha: Number? = null,
    outlierColor: Any? = null,
    outlierFill: Any? = null,
    outlierShape: Any? = null,
    outlierSize: Number? = null,
    outlierStroke: Number? = null,
    outlierAngle: Number? = null,
    fatten: Number? = null,
    whiskerWidth: Number? = null,
    varWidth: Boolean? = null,
    @Suppress("SpellCheckingInspection")
    coef: Number? = null,
    widthUnit: String? = null,
    colorBy: String? = null,
    fillBy: String? = null,
    mapping: BoxplotMapping .() -> Unit = {}
): FeatureList {
    val layers = mutableListOf<Layer>()

    layers += geomBoxplotInternal(
        data,
        stat,
        position,
        showLegend,
        inheritAes,
        manualKey,
        tooltips,
        orientation,
        x, y, lower, middle, upper, ymin, ymax, alpha,
        xlower, xmiddle, xupper, xmin, xmax,
        color, fill, size, linetype, shape, angle, width, weight, fatten,
        whiskerWidth, varWidth, coef,
        widthUnit,
        colorBy, fillBy,
        mapping
    )

    if (stat.kind == StatKind.BOXPLOT) {
        val outlierFatten = 4.0
        val boxplotMapping = BoxplotMapping().apply(mapping)
        val pointMapping: PointMapping.() -> Unit = {
            this.x = boxplotMapping.x
            this.y = boxplotMapping.y
            this.alpha = boxplotMapping.alpha
            this.color = boxplotMapping.color
            this.fill = boxplotMapping.fill
            this.shape = boxplotMapping.shape
            this.angle = boxplotMapping.angle
            this.size = boxplotMapping.size
            // stroke
            this.group = boxplotMapping.group
            this.paint_a = boxplotMapping.paint_a
            this.paint_b = boxplotMapping.paint_b
            this.paint_c = boxplotMapping.paint_c
        }
        layers += geomPoint(
            data = data,
            stat = Stat.boxplotOutlier(),
            position = position,
            showLegend = false,
            inheritAes = inheritAes,
            manualKey = null,
            sampling = null,
            orientation = orientation,
            x = x, y = y,
            alpha = outlierAlpha,
            color = outlierColor ?: color,
            fill = outlierFill ?: fill,
            shape = outlierShape ?: shape,
            size = (outlierSize ?: size)?.let { it.toDouble() * outlierFatten },
            stroke = outlierStroke ?: stroke,
            angle = outlierAngle ?: angle,
            colorBy = colorBy, fillBy = fillBy,
            mapping = pointMapping
        )
    }
    return FeatureList(layers)
}

@Suppress("ClassName")
private class geomBoxplotInternal(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.boxplot(),
    position: PosOptions = positionDodge(),
    showLegend: Boolean = true,
    inheritAes: Boolean? = null,
    manualKey: Any? = null,
    tooltips: TooltipOptions? = null,
    orientation: String? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val lower: Number? = null,
    override val middle: Number? = null,
    override val upper: Number? = null,
    override val ymin: Number? = null,
    override val ymax: Number? = null,
    override val xlower: Number? = null,
    override val xmiddle: Number? = null,
    override val xupper: Number? = null,
    override val xmin: Number? = null,
    override val xmax: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val size: Number? = null,
    override val linetype: Any? = null,
    override val shape: Any? = null,
    override val angle: Number? = null,
    override val width: Any? = null,
    override val weight: Any? = null,
    override val fatten: Number? = null,
    override val whiskerWidth: Number? = null,
    override val varWidth: Boolean? = null,
    @Suppress("SpellCheckingInspection")
    override val coef: Number? = null,
    override val widthUnit: String? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: BoxplotMapping .() -> Unit = {}
) : BoxplotAesthetics,
    BoxplotParameters,
    BoxplotStatAesthetics,
    BoxplotStatParameters,
    WithWidthUnitOption,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = BoxplotMapping().apply(mapping).seal(),
        data = data,
        geom = boxplot(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        inheritAes = inheritAes,
        manualKey = manualKey,
        sampling = null,
        tooltips = tooltips,
        orientation = orientation
    ) {
    override fun seal(): Options {
        return super<BoxplotAesthetics>.seal() +
                super<BoxplotParameters>.seal() +
                super<BoxplotStatAesthetics>.seal() +
                super<BoxplotStatParameters>.seal() +
                super<WithWidthUnitOption>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }
}
