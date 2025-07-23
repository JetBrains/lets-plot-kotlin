/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.LineAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.LineMapping
import org.jetbrains.letsPlot.intern.standardizing.SeriesStandardizing.asList
import org.jetbrains.letsPlot.intern.standardizing.SeriesStandardizing.isListy
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Computes and draws a function.
 *
 * ## Notes
 *
 * To hide axis tooltips, set "blank" or the result of `elementBlank()`
 * to the `axisTooltip`, `axisTooltipX` or `axisTooltipY` parameter of the `theme()`.
 *
 * ## Examples
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
 * @param tooltips Result of the call to the `layerTooltips()` function.
 *  Specifies appearance, style and content.
 *  Set `tooltips = tooltipsNone` to hide tooltips from the layer.
 * @param fn A function to use.
 * @param xlim default = Pair(0, 1).
 *  Range of the function.
 * @param n default = 512.
 *  Number of points to interpolate along the x-axis.
 * @param x X-axis value.
 * @param y Y-axis value.
 * @param alpha Transparency level of a layer.
 *  Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param linetype Type of the line.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param size Line width.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class geomFunction(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.identity,
    geom: GeomOptions = Geom.line(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    inheritAes: Boolean? = null,
    manualKey: Any? = null,
    tooltips: TooltipOptions? = null,
    fn: ((Double) -> Double)? = null,
    xlim: Pair<Number, Number>? = null,
    n: Int? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val colorBy: String? = null,
    mapping: LineMapping.() -> Unit = {},
) : LineAesthetics,
    WithColorOption,
    Layer(
        mapping = getMapping(mapping),
        data = getFunData(data, mapping, fn, xlim, n),
        geom = geom,
        stat = stat,
        position = position,
        showLegend = showLegend,
        inheritAes = inheritAes,
        manualKey = manualKey,
        sampling = null,
        tooltips = tooltips
    ) {

    override fun seal() = super<LineAesthetics>.seal() +
            super<WithColorOption>.seal()

    companion object {
        private const val FUN_X_NAME = "x"
        private const val FUN_Y_NAME = "y"
        private val DEF_X_LIM = Pair(0, 1)
        private const val DEF_SIZE = 512

        private fun getMapping(mapping: LineMapping.() -> Unit): Options {
            val dict = LineMapping().apply(mapping).seal().map
            return Options(mapOf("x" to FUN_X_NAME) + dict + ("y" to FUN_Y_NAME))
        }

        private fun getFunData(
            data: Map<*, *>?,
            mapping: LineMapping.() -> Unit,
            fn: ((Double) -> Double)? = null,
            xlim: Pair<Number, Number>? = null,
            n: Int? = null,
        ): Map<*, *> {
            val mappingDict = LineMapping().apply(mapping).seal().map
            val aesXValue = mappingDict["x"]

            val xs: List<Double?> = when {
                aesXValue is String && data != null -> {
                    require(aesXValue in data) { "'$aesXValue' is not found in data" }
                    val values = data[aesXValue]!!
                    require(isListy(values)) { "'$aesXValue'  must contain a list of values" }
                    val list = asList(values)
                    require(list.all { it is Number? }) { "'$aesXValue' must contain only numbers: $list" }
                    list.map { (it as? Number)?.toDouble() }
                }
                else -> getDefaultXRange(xlim, n)
            }

            val ys: List<Double?> = when (fn) {
                null -> List(xs.size) { null }
                else -> xs.map { x -> x?.let(fn) }
            }

            return data?.plus(mapOf(FUN_Y_NAME to ys)) ?: mapOf(FUN_X_NAME to xs, FUN_Y_NAME to ys)
        }

        private fun getDefaultXRange(xlim: Pair<Number, Number>?, n: Int?): List<Double> {
            fun linspace(start: Double, stop: Double, num: Int): List<Double> {
                if (num == 1) {
                    return listOf(start)
                }
                val step = (stop - start) / (num - 1)
                return List(num) { start + step * it }
            }

            val (start, stop) = xlim ?: DEF_X_LIM
            val size = n ?: DEF_SIZE

            return linspace(start.toDouble(), stop.toDouble(), size)
        }
    }
}

