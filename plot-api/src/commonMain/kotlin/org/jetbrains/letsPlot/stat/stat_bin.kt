/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.stat

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.HistogramAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.HistogramMapping
import org.jetbrains.letsPlot.intern.layer.stat.BinStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.BinStatParameters
import org.jetbrains.letsPlot.pos.positionStack

@Suppress("ClassName")
/**
 * Displays a 1d distribution by dividing variable mapped to x-axis into bins and counting the number of observations in each bin.
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
 * To hide axis tooltips, set "blank" or the result of `elementBlank()`
 * to the `axisTooltip` or `axisTooltipX` parameter of the `theme()`.
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param geom The geometry to display the bin stat for this layer, default is `Geom.histogram()`,
 *  see [Geom][org.jetbrains.letsPlot.Geom].
 * @param position Position adjustment: `positionIdentity`, `positionStack()`, `positionDodge()`, etc. see
 *  [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
 * @param showLegend default = true.
 *  false - do not show legend for this layer.
 * @param inheritAes default = true.
 *  false - do not combine the layer aesthetic mappings with the plot shared mappings.
 * @param sampling Result of the call to the `samplingXxx()` function.
 *  To prevent any sampling for this layer pass value `samplingNone`.
 *  For more info see [sampling.html](https://lets-plot.org/kotlin/sampling.html).
 * @param x X-axis coordinates.
 * @param y Y-axis coordinates.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param size Lines width.
 * @param weight Used to compute weighted sum instead of simple count.
 * @param bins Number of bins. Overridden by `binWidth`.
 * @param binWidth The width of the bins. The default is to use bin widths that cover
 *  the entire range of the data. You should always override this value, exploring multiple widths
 *  to find the best to illustrate the stories in your data.
 * @param center Specifies x-value to align bin centers to.
 * @param boundary Specifies x-value to align bin boundary (i.e. point between bins) to.
 * @param threshold default = null
 *  Only bins with a `..count..` greater than the threshold will be displayed.
 *  This is useful for free scales in facets - use threshold=0 to make the plot take up the entire panel space.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class statBin(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.histogram(),
    position: PosOptions = positionStack(),
    showLegend: Boolean = true,
    inheritAes: Boolean? = null,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val size: Number? = null,
    override val weight: Any? = null,
    override val bins: Int? = null,
    override val binWidth: Number? = null,
    override val center: Number? = null,
    override val boundary: Number? = null,
    override val threshold: Number? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: HistogramMapping.() -> Unit = {}
) : HistogramAesthetics,
    BinStatAesthetics,
    BinStatParameters,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = HistogramMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.bin(),
        position = position,
        showLegend = showLegend,
        inheritAes = inheritAes,
        manualKey = manualKey,
        sampling = sampling
    ) {
    override fun seal(): Options {
        return super<HistogramAesthetics>.seal() +
                super<BinStatAesthetics>.seal() +
                super<BinStatParameters>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }
}