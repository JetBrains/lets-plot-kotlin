/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.annotations.layerLabels
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.HistogramAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.HistogramMapping
import org.jetbrains.letsPlot.intern.layer.geom.HistogramParameters
import org.jetbrains.letsPlot.intern.layer.stat.BinStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.BinStatParameters
import org.jetbrains.letsPlot.pos.positionStack
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Display a 1D distribution by dividing the variable mapped to the x-axis into bins and counting the number of observations in each bin.
 *
 * ## Notes
 *
 * Computed variables:
 *
 * - ..count.. : number of points with the x-axis coordinate in the same bin.
 * - ..density.. : normalized number of points so that plot area is 1.
 * - ..sumprop.. : normalized number of points so that sum of y-values is 1.
 * - ..sumpct.. : normalized number of points so that sum of y-values is 100.
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
 *  Supported transformations: [Stat.identity][org.jetbrains.letsPlot.Stat.identity], [Stat.bin()][org.jetbrains.letsPlot.Stat.bin], [Stat.count()][org.jetbrains.letsPlot.Stat.count], etc. see [Stat][org.jetbrains.letsPlot.Stat].
 * @param position default = `positionStack()`. Position adjustment: `positionIdentity`, `positionStack()`, `positionDodge()`, etc.  
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
 * @param tooltips Result of the call to the [layerTooltips()][org.jetbrains.letsPlot.tooltips.layerTooltips] function.
 *  Specifies appearance, style and content.
 *  Set `tooltips = tooltipsNone` to hide tooltips from the layer.
 * @param labels Result of the call to the [layerLabels()][org.jetbrains.letsPlot.annotations.layerLabels] function.
 *  Specifies style and content of the annotations.
 * @param orientation Specifies the axis that the layer's stat and geom should run along, default = "x".
 *  Possible values: "x", "y".
 * @param x X-axis value (this value will produce cases or bins for bars).
 * @param y Y-axis value (this value will be used to multiply the bar heights), setting y to "..density.." produces
 *  normalized (density) histogram.
 * @param alpha Transparency level of a layer.
 *  Understands numbers between 0 and 1.
 * @param color Color of geometry lines.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param size Defines bar line width.
 * @param weight Used by `Stat.bin()` to compute weighted sum instead of simple count.
 * @param bins Number of bins. Overridden by `binWidth`.
 * @param binWidth The width of the bins. The default is to use bin widths that cover 
 *  the entire range of the data. You should always override this value, exploring multiple widths 
 *  to find the best to illustrate the stories in your data.
 * @param center Specifies x-value to align bin centers to.
 * @param boundary Specifies x-value to align bin boundary (i.e., point between bins) to.
 * @param threshold default = null
 *  If a bin's `..count..` is less than the threshold, it will be removed, but only if it is on the left or right edge of the histogram.
 *  This is useful for free scales in facets - use threshold=0 to make the plot take up the entire panel space.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param breaks A list of data values that specify exact positions of the bin boundaries.
 *  Overrides `bins`, `binwidth`, `center` and `boundary`.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class geomHistogram(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.bin(),
    position: PosOptions = positionStack(),
    showLegend: Boolean = true,
    inheritAes: Boolean? = null,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    labels: layerLabels? = null,
    orientation: String? = null,
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
    override val breaks: List<Number>? = null,
    mapping: HistogramMapping.() -> Unit = {}

) : HistogramAesthetics,
    BinStatAesthetics,
    BinStatParameters,
    HistogramParameters,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = HistogramMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.histogram(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        inheritAes = inheritAes,
        manualKey = manualKey,
        sampling = sampling,
        tooltips = tooltips,
        labels = labels,
        orientation = orientation,
    ) {
    override fun seal() = super<HistogramAesthetics>.seal() +
            super<BinStatAesthetics>.seal() +
            super<BinStatParameters>.seal() +
            super<HistogramParameters>.seal() +
            super<WithColorOption>.seal() +
            super<WithFillOption>.seal()
}
