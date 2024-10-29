/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.AreaAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.DensityMapping
import org.jetbrains.letsPlot.intern.layer.stat.DensityStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.DensityStatParameters
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Displays a density estimate, which is a smoothed version of the histogram.
 *
 * ## Notes
 *
 * Computed variables:
 *
 * - ..density.. : density estimate (mapped by default).
 * - ..count.. : density * number of points.
 * - ..scaled.. : density estimate, scaled to maximum of 1.
 * - ..quantile.. : quantile estimate.
 *
 * To hide axis tooltips, set "blank" or the result of `elementBlank()`
 * to the `axisTooltip` or `axisTooltipX` parameter of the `theme()`.
 *
 * ## Examples
 *
 * - [distributions.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/distributions.ipynb)
 *
 * - [quantile_parameters.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/quantile_parameters.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat default = `Stat.density()`. The statistical transformation to use on the data for this layer.
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
 * @param x X-axis coordinates.
 * @param alpha Transparency level of a layer.
 *  Understands numbers between 0 and 1.
 * @param color Color of geometry lines.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param size Line width.
 * @param linetype Type of the line.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param weight Used by "density" stat to compute weighted density.
 * @param bw The method (or exact value) of bandwidth. Can be String ("nrd0" or "nrd") or Double.
 * @param kernel The kernel used to calculate the density function. Choose among "gaussian", "cosine", "optcosine",
 *  "rectangular" (or "uniform"), "triangular", "biweight" (or "quartic"), "epanechikov" (or "parabolic").
 * @param n The number of sampled points for plotting the function.
 * @param trim default = false If false, each density is computed on the full range of the data.
 *  If true, each density is computed over the range of that group.
 * @param adjust Adjusts the value of bandwidth by multiplying it. Changes how smooth the frequency curve is.
 * @param fullScanMax Maximum size of data to use density computation with 'full scan'.
 *  For bigger data, less accurate but more efficient density computation is applied.
 * @param quantiles List of Numbers, default = listOf(0.25, 0.5, 0.75).
 *  Draws horizontal lines at the given quantiles of the density estimate.
 * @param quantileLines default = false.
 *  Shows the quantile lines.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class geomDensity(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.density(),
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
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val weight: Any? = null,
    override val bw: Any? = null,
    override val kernel: String? = null,
    override val n: Int? = null,
    override val trim: Boolean? = null,
    override val adjust: Number? = null,
    override val fullScanMax: Int? = null,
    override val quantiles: List<Number>? = null,
    private val quantileLines: Boolean? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: DensityMapping.() -> Unit = {}
) : AreaAesthetics,
    DensityStatAesthetics,
    DensityStatParameters,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = DensityMapping().apply(mapping).seal(),
        data = data,
        geom = GeomOptions(GeomKind.DENSITY),
        stat = stat,
        position = position,
        showLegend = showLegend,
        inheritAes = inheritAes,
        manualKey = manualKey,
        sampling = sampling,
        tooltips = tooltips,
        orientation = orientation
    ) {

    override fun seal() = super<AreaAesthetics>.seal() +
            super<DensityStatAesthetics>.seal() +
            super<DensityStatParameters>.seal() +
            super<WithColorOption>.seal() +
            super<WithFillOption>.seal() +
            Options.of(
                Option.Geom.Density.QUANTILE_LINES to quantileLines
            )
}


