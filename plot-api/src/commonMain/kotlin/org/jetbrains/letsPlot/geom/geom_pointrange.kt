/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.StatOptions
import org.jetbrains.letsPlot.intern.layer.WithColorOption
import org.jetbrains.letsPlot.intern.layer.WithFillOption
import org.jetbrains.letsPlot.intern.layer.geom.PointRangeAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.PointRangeMapping
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions


@Suppress("ClassName", "SpellCheckingInspection")
/**
 * Adds a vertical line defined by upper and lower value with midpoint at y location.
 * Represents a vertical interval, defined by `x`, `ymin`, `ymax`,
 * or a horizontal interval, defined by `y`, `xmin`, `xmax`.
 * The mid-point is defined by `y` or `x`, respectively.
 *
 * ## Examples
 *
 * - [error_bars.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/error_bars.ipynb)
 *
 * - [horizontal_geoms.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/horizontal_geoms.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat The statistical transformation to use on the data for this layer.
 *  Supported transformations: `Stat.identity`, `Stat.bin()`, `Stat.count()`, etc. see [Stat][org.jetbrains.letsPlot.Stat].
 * @param position Position adjustment: `positionIdentity`, `positionStack()`, `positionDodge()`, etc. see 
 *  [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
 * @param showLegend default = true.
 *  false - do not show legend for this layer.
 * @param manualKey String or result of the call to the `layerKey()` function.
 *  The key to show in the manual legend. Specifies the text for the legend label or advanced settings using the `layerKey()` function.
 * @param sampling Result of the call to the `samplingXxx()` function.
 *  To prevent any sampling for this layer pass value `samplingNone`.
 *  For more info see [sampling.html](https://lets-plot.org/kotlin/sampling.html).
 * @param tooltips Result of the call to the `layerTooltips()` function.
 *  Specifies appearance, style and content.
 * @param fatten default = 5.0.
 *  A multiplicative factor applied to size of the middle point.
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
class geomPointRange(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    val fatten: Number? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val ymin: Number? = null,
    override val ymax: Number? = null,
    override val xmin: Number? = null,
    override val xmax: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val shape: Any? = null,
    override val size: Number? = null,
    override val stroke: Number? = null,
    override val linewidth: Number? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: PointRangeMapping.() -> Unit = {}
) : PointRangeAesthetics,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = PointRangeMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.pointrange(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        manualKey = manualKey,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal(): Options {
        return super<PointRangeAesthetics>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal() +
                Options.of("fatten" to fatten)
    }
}