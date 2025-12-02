/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.layer.GeomOptions
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.WithColorOption
import org.jetbrains.letsPlot.intern.layer.WithFillOption
import org.jetbrains.letsPlot.intern.layer.geom.DotplotAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.DotplotMapping
import org.jetbrains.letsPlot.intern.layer.geom.DotplotParameters
import org.jetbrains.letsPlot.intern.layer.stat.DotplotStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.DotplotStatParameters
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName", "SpellCheckingInspection")
/**
 * Dotplot represents individual observations in a batch of data with circular dots.
 * The diameter of a dot corresponds to the maximum width or bin width, depending on the binning algorithm.
 *
 * ## Notes
 *
 * With "dotdensity" binning, the bin positions are determined by the data and binwidth, which is the maximum width of each bin.
 * With "histodot" binning, the bins have fixed positions and fixed widths, much like a histogram.
 *
 * Computed variables:
 *
 * - ..count.. : number of points with the x-axis coordinate in the same bin.
 * - ..binwidth.. : max width of each bin if method is "dotdensity"; width of each bin if method is "histodot".
 *
 * To hide axis tooltips, set "blank" or the result of [elementBlank()][org.jetbrains.letsPlot.themes.elementBlank]
 * to the `axisTooltip` or `axisTooltipX` parameter of the [theme()][org.jetbrains.letsPlot.themes.theme].
 *
 * ## Examples
 *
 * - [geom_dotplot.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_dotplot.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
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
 * @param x X-axis coordinates.
 * @param bins When method is "histodot", this specifies number of bins (default = 30). Overridden by `binWidth`.
 * @param center When method is "histodot", this specifies x-value to align bin centers to.
 * @param boundary When method is "histodot", this specifies x-value to align bin boundary
 *  (i.e., point between bins) to.
 * @param method default = "dotdensity".
 *  Use "dotdensity" for dot-density binning,
 *  or "histodot" for fixed bin widths (like in geomHistogram).
 * @param binWidth When method is "dotdensity", this specifies maximum bin width.
 *  When method is "histodot", this specifies bin width.
 * @param stackDir Which direction to stack the dots.
 *  Values: "up", "down", "center", "centerwhole" (default = "up").
 * @param stackRatio default = 1.0.
 *  How close to stack the dots.
 *  Use smaller values for closer, overlapping dots.
 * @param dotSize default = 1.0.
 *  The diameter of the dots relative to binWidth.
 * @param stackGroups Stack dots across groups when method = "histodot".
 * @param stroke Width of the dot border.
 * @param alpha Transparency level of a layer.
 *  Understands numbers between 0 and 1.
 * @param color Color of geometry lines.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class geomDotplot(
    data: Map<*, *>? = null,
    showLegend: Boolean = true,
    inheritAes: Boolean? = null,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val x: Number? = null,
    override val bins: Int? = null,
    override val center: Number? = null,
    override val boundary: Number? = null,
    override val method: String? = null,
    override var binWidth: Number? = null,
    override var stackSize: Number? = null,
    override val stackDir: String? = null,
    override val stackRatio: Number? = null,
    override val dotSize: Number? = null,
    override val stackGroups: Boolean? = null,
    override val stroke: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: DotplotMapping.() -> Unit = {},
) : DotplotAesthetics,
    DotplotParameters,
    DotplotStatAesthetics,
    DotplotStatParameters,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = DotplotMapping().apply(mapping).seal(),
        data = data,
        geom = GeomOptions(GeomKind.DOTPLOT),
        stat = Stat.dotplot(),
        position = positionIdentity,
        showLegend = showLegend,
        inheritAes = inheritAes,
        manualKey = manualKey,
        sampling = sampling,
        tooltips = tooltips
    ) {

    override fun seal() = super<DotplotAesthetics>.seal() +
            super<DotplotParameters>.seal() +
            super<DotplotStatAesthetics>.seal() +
            super<DotplotStatParameters>.seal() +
            super<WithColorOption>.seal() +
            super<WithFillOption>.seal()
}
