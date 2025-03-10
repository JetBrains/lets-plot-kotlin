/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.ErrorBarAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.ErrorBarMapping
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName", "SpellCheckingInspection")
/**
 * Displays error bars defined by the upper and lower values.
 * Represents a vertical interval, defined by `x`, `ymin`, `ymax`,
 * or a horizontal interval, defined by `y`, `xmin`, `xmax`.
 *
 * ## Notes
 *
 * To hide axis tooltips, set "blank" or the result of `elementBlank()`
 * to the `axisTooltip` or `axisTooltipX` parameter of the `theme()`.
 *
 * ## Examples
 *
 * - [error_bars.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/error_bars.ipynb)
 *
 * - [horizontal_error_bars.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/horizontal_error_bars.ipynb)
 *
 * - [horizontal_geoms.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/horizontal_geoms.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat The statistical transformation to use on the data for this layer.
 *  Supported transformations: `Stat.identity`, `Stat.bin()`, `Stat.count()`, etc. see [Stat][org.jetbrains.letsPlot.Stat].
 * @param position Position adjustment: `positionIdentity`, `positionStack()`, `positionDodge()`, etc.  
 *  see [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
 * @param showLegend default = true.
 *  false - do not show legend for this layer.
 * @param inheritAes default = true.
 *  false - do not combine the layer aesthetic mappings with the plot shared mappings.
 * @param manualKey String or result of the call to the `layerKey()` function.
 *  The key to show in the manual legend. Specifies the text for the legend label or advanced settings using the `layerKey()` function.
 * @param sampling Result of the call to the `samplingXxx()` function.
 *  To prevent any sampling for this layer pass value `samplingNone`. 
 *  For more info see [sampling.html](https://lets-plot.org/kotlin/sampling.html).
 * @param tooltips Result of the call to the `layerTooltips()` function.
 *  Specifies appearance, style and content.
 *  Set `tooltips = tooltipsNone` to hide tooltips from the layer.
 * @param x X-axis coordinates for the vertical error bar.
 * @param ymin Lower bound for the vertical error bar.
 * @param ymax Upper bound for the vertical error bar.
 * @param width Width of the whiskers of the vertical error bar. Typically ranges between 0 and 1.
 *  Values that are greater than 1 lead to overlapping of the bars.
 * @param y Y-axis coordinates for the horizontal error bar.
 * @param xmin Lower bound for the horizontal error bar.
 * @param xmax Upper bound for the horizontal error bar.
 * @param alpha Transparency level of a layer.
 *  Understands numbers between 0 and 1.
 * @param color Color of geometry lines.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param linetype Type of the line.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param size Line width.
 * @param widthUnit default = "res".
 *  Unit for the whisker width of the vertical error bar.
 *  Possible values:
 *
 *  - "res": the unit equals the smallest distance between adjacent error bars along the corresponding axis;
 *  - "identity": a unit of 1 corresponds to a difference of 1 in data space;
 *  - "size": a unit of 1 corresponds to the diameter of a point with size=1;
 *  - "px": the unit is measured in screen pixels.
 *
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class geomErrorBar(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    inheritAes: Boolean? = null,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val x: Number? = null,
    override val ymin: Number? = null,
    override val ymax: Number? = null,
    override val width: Number? = null,
    override val y: Number? = null,
    override val xmin: Number? = null,
    override val xmax: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val widthUnit: String? = null,
    override val colorBy: String? = null,
    mapping: ErrorBarMapping.() -> Unit = {}
) : ErrorBarAesthetics,
    WithWidthUnitOption,
    WithColorOption,
    Layer(
        mapping = ErrorBarMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.errorbar(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        inheritAes = inheritAes,
        manualKey = manualKey,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal() = super<ErrorBarAesthetics>.seal() +
            super<WithWidthUnitOption>.seal() +
            super<WithColorOption>.seal()
}