/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.stat

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.PointRangeAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.PointRangeMapping
import org.jetbrains.letsPlot.intern.layer.stat.SummaryStatParameters
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions


@Suppress("ClassName")
/**
 * Displays the aggregated values of a single continuous variable grouped along the x-axis.
 *
 * ## Examples
 *
 * - [stat_summary.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/stat_summary.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param geom The geometry to display the summary stat for this layer, default is `Geom.pointrange()`,
 *  see [Geom][org.jetbrains.letsPlot.Geom].
 * @param position Position adjustment: `positionIdentity`, `positionStack()`, `positionDodge()`, etc. see
 *  [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
 * @param showLegend default = true.
 *  false - do not show legend for this layer.
 * @param sampling Result of the call to the `samplingXxx()` function.
 *  To prevent any sampling for this layer pass value `samplingNone`.
 *  For more info see [sampling.html](https://lets-plot.org/kotlin/sampling.html).
 * @param tooltips Result of the call to the `layerTooltips()` function.
 *  Specifies appearance, style and content.
 * @param orientation Specifies the axis that the layer's stat and geom should run along.
 *  The default value (`null`) automatically determines the orientation based on the aesthetic mapping.
 *  If the automatic detection doesn't work, it can be set explicitly by specifying the "x" or "y" orientation.
 * @param fn default = "mean" ("count", "sum", "mean", "median", "min", "max", "lq", "mq", "uq").
 *  Name of function computing stat variable `..y..`.
 *  Names "lq", "mq", "uq" correspond to lower, middle and upper quantiles, default = listOf(0.25, 0.5, 0.75).
 * @param fnMin default = "min" ("count", "sum", "mean", "median", "min", "max", "lq", "mq", "uq").
 *  Name of function computing stat variable `..ymin..`.
 *  Names "lq", "mq", "uq" correspond to lower, middle and upper quantiles, default = listOf(0.25, 0.5, 0.75).
 * @param fnMax default = "max" ("count", "sum", "mean", "median", "min", "max", "lq", "mq", "uq").
 *  Name of function computing stat variable `..ymax..`.
 *  Names "lq", "mq", "uq" correspond to lower, middle and upper quantiles, default = listOf(0.25, 0.5, 0.75).
 * @param quantiles default = listOf(0.25, 0.5, 0.75).
 *  A list of probabilities defining the quantile functions "lq", "mq" and "uq".
 *  Must contain exactly 3 values between 0 and 1.
 * @param x X-axis coordinates for vertical interval / position of mid-point for horizontal interval.
 * @param y Y-axis coordinates for horizontal interval / position of mid-point for vertical interval.
 * @param ymin Lower bound for vertical interval.
 * @param ymax Upper bound for vertical interval.
 * @param xmin Lower bound for horizontal interval.
 * @param xmax Upper bound for horizontal interval.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  String in the following formats:
 *  - RGB/RGBA (e.g. "rgb(0, 0, 255)")
 *  - HEX (e.g. "#0000FF")
 *  - color name (e.g. "red")
 *  - role name ("pen", "paper" or "brush")
 *
 *  Or an instance of the `java.awt.Color` class.
 * @param fill Fill color.
 *  String in the following formats:
 *  - RGB/RGBA (e.g. "rgb(0, 0, 255)")
 *  - HEX (e.g. "#0000FF")
 *  - color name (e.g. "red")
 *  - role name ("pen", "paper" or "brush")
 *
 *  Or an instance of the `java.awt.Color` class.
 * @param size Lines width, size of mid-point.
 * @param stroke Width of the shape border. Applied only to the shapes having border.
 * @param linewidth Line width.
 * @param linetype Type of the line of border.
 *  Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *  5 = "longdash", 6 = "twodash"
 * @param shape Shape of the mid-point.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class statSummary(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.pointrange(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    orientation: String? = null,
    override val x: Any? = null,
    override val y: Any? = null,
    override val ymin: Any? = null,
    override val ymax: Any? = null,
    override val xmin: Any? = null,
    override val xmax: Any? = null,
    override val alpha: Any? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val shape: Any? = null,
    override val size: Any? = null,
    override val stroke: Any? = null,
    override val linewidth: Any? = null,
    override val fn: String? = null,
    override val fnMin: String? = null,
    override val fnMax: String? = null,
    override val quantiles: List<Number>? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: PointRangeMapping.() -> Unit = {}
) : PointRangeAesthetics,
    SummaryStatParameters,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = PointRangeMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.summary(),
        position = position,
        showLegend = showLegend,
        manualKey = manualKey,
        sampling = sampling,
        tooltips = tooltips,
        orientation = orientation
    ) {

    override fun seal(): Options {
        return super<PointRangeAesthetics>.seal() +
                super<SummaryStatParameters>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }
}