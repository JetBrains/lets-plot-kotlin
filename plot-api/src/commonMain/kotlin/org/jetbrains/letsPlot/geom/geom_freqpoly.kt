/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.StatOptions
import org.jetbrains.letsPlot.intern.layer.WithColorOption
import org.jetbrains.letsPlot.intern.layer.geom.LineAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.LineMapping
import org.jetbrains.letsPlot.intern.layer.stat.BinStatParameters
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName", "SpellCheckingInspection")
/**
 * Displays a line chart which makes the y value proportional to the number of observed variable values, mapped to x-axis.
 *
 * ## Notes
 *
 * Computed variables:
 *
 * - ..count.. : number of points with x-axis coordinate in the same bin.
 * - ..density.. : normalised number of points so that plot area is 1.
 * - ..sumprop.. : normalised number of points so that sum of y-values is 1.
 * - ..sumpct.. : normalised number of points so that sum of y-values is 100.
 * - ..binwidth.. : width of each bin.
 *
 * To hide axis tooltips, set "blank" or the result of [elementBlank()][org.jetbrains.letsPlot.themes.elementBlank]
 * to the `axisTooltip` or `axisTooltipX` parameter of the [theme()][org.jetbrains.letsPlot.themes.theme].
 *
 * ## Examples
 *
 * - [distributions.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/distributions.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat default = `Stat.bin()`. The statistical transformation to use on the data for this layer.
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
 * @param orientation Specifies the axis that the layer's stat and geom should run along, default = "x".
 *  Possible values: "x", "y".
 * @param x X-axis value.
 * @param y Y-axis value.
 * @param alpha Transparency level of a layer.
 *  Understands numbers between 0 and 1.
 * @param color Color of geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param linetype Type of the line.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param size Line width.
 * @param bins Number of bins. Overridden by `binWidth`.
 * @param binWidth The width of the bins. The default is to use bin widths that cover 
 *  the entire range of the data. You should always override this value, exploring multiple widths 
 *  to find the best to illustrate the stories in your data.
 * @param center Specifies x-value to align bin centers to.
 * @param boundary Specifies x-value to align bin boundary (i.e., point between bins) to.
 * @param threshold default = null
 *  Only bins with a `..count..` greater than the threshold will be displayed.
 *  This is useful for free scales in facets - use threshold=0 to make the plot take up the entire panel space.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class geomFreqpoly(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.bin(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    inheritAes: Boolean? = null,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    orientation: String? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val bins: Int? = null,
    override val binWidth: Number? = null,
    override val center: Number? = null,
    override val boundary: Number? = null,
    override val threshold: Number? = null,
    override val colorBy: String? = null,
    mapping: LineMapping.() -> Unit = {}
) : LineAesthetics,
    BinStatParameters,
    WithColorOption,
    Layer(
        mapping = LineMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.line(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        inheritAes = inheritAes,
        manualKey = manualKey,
        sampling = sampling,
        tooltips = tooltips,
        orientation = orientation
    ) {
    override fun seal(): Options {
        return super<LineAesthetics>.seal() +
                super<BinStatParameters>.seal() +
                super<WithColorOption>.seal()
    }
}