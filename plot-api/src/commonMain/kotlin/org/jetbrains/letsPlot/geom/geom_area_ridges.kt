/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.AreaRidgesAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.AreaRidgesMapping
import org.jetbrains.letsPlot.intern.layer.geom.AreaRidgesParameters
import org.jetbrains.letsPlot.intern.layer.stat.DensityRidgesStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.DensityRidgesStatParameters
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName", "SpellCheckingInspection")
/**
 * Plots the sum of the `y` and `height` aesthetics versus `x`. Heights of the ridges are relatively scaled.
 *
 * ## Notes
 *
 * Computed variables:
 *
 * - ..height.. : density scaled for the ridges, according to area, counts or to a constant maximum height.
 * - ..density.. : density estimate.
 * - ..count.. : density * number of points.
 * - ..scaled.. : density estimate, scaled to maximum of 1.
 * - ..quantile.. : quantile estimate.
 *
 * To hide axis tooltips, set "blank" or the result of `elementBlank()`
 * to the `axisTooltip` or `axisTooltipX` parameter of the `theme()`.
 *
 * ## Examples
 *
 * - [ridgeline_plot.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/ridgeline_plot.ipynb)
 *
 * - [quantile_parameters.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/quantile_parameters.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat default = `Stat.densityRidges()`. The statistical transformation to use on the data for this layer.
 *  Supported transformations: `Stat.identity`, `Stat.bin()`, `Stat.count()`, etc. see [Stat][org.jetbrains.letsPlot.Stat].
 * @param position Position adjustment: `positionIdentity`, `positionStack()`, `positionDodge()`, etc. see
 *  [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
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
 * @param y Y-axis coordinates.
 * @param height Height of the ridge. Assumed to be between 0 and 1, though this is not required.
 * @param quantile Quantile values to draw quantile lines and fill quantiles of the geometry by color.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param linetype Type of the line of border.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param size Defines line width.
 * @param weight Used by `Stat.densityRidges()` stat to compute weighted density.
 * @param scale default = 1.0
 *  A multiplicative factor applied to height aesthetic.
 *  If `scale = 1.0`, the heights of a ridges are automatically scaled
 *  such that the ridge with `height = 1.0` just touches the one above.
 * @param minHeight default = 0.0.
 *  A height cutoff on the drawn ridges.
 *  All values that fall below this cutoff will be removed.
 * @param quantileLines default = false.
 *  Shows the quantile lines.
 * @param tailsCutoff Extends domain of each ridge on `tailsCutoff * bw` if `trim = false`.
 *  `tailsCutoff = null` (default) extends domain to maximum (domain overall ridges).
 * @param quantiles default = listOf(0.25, 0.5, 0.75).
 *  Draws horizontal lines at the given quantiles of the density estimate.
 * @param bw String or Double.
 *  The method (or exact value) of bandwidth. Either a string (choose among "nrd0" and "nrd") or a double.
 * @param kernel The kernel we use to calculate the density function. Choose among "gaussian", "cosine", "optcosine",
 *  "rectangular" (or "uniform"), "triangular", "biweight" (or "quartic"), "epanechikov" (or "parabolic").
 * @param n The number of sampled points for plotting the function.
 * @param trim default = false.
 *  Trims the tails of the ridges to the range of the data.
 * @param adjust Adjusts the value of bandwidth by multiplying it. Changes how smooth the frequency curve is.
 * @param fullScanMax Maximum size of data to use density computation with "full scan".
 *  For bigger data, less accurate but more efficient density computation is applied.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class geomAreaRidges(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.densityRidges(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    inheritAes: Boolean? = null,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val height: Number? = null,
    override val quantile: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val weight: Number? = null,
    override val scale: Number? = null,
    override val minHeight: Number? = null,
    override val quantileLines: Boolean? = null,
    override val tailsCutoff: Number? = null,
    override val quantiles: List<Number>? = null,
    override val bw: Any? = null,
    override val kernel: String? = null,
    override val n: Int? = null,
    override val trim: Boolean? = null,
    override val adjust: Number? = null,
    override val fullScanMax: Int? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: AreaRidgesMapping.() -> Unit = {}
) : AreaRidgesAesthetics,
    AreaRidgesParameters,
    DensityRidgesStatAesthetics,
    DensityRidgesStatParameters,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = AreaRidgesMapping().apply(mapping).seal(),
        data = data,
        geom = GeomOptions(GeomKind.AREA_RIDGES),
        stat = stat,
        position = position,
        showLegend = showLegend,
        inheritAes = inheritAes,
        manualKey = manualKey,
        sampling = sampling,
        tooltips = tooltips
    ) {

    override fun seal() = super<AreaRidgesAesthetics>.seal() +
            super<AreaRidgesParameters>.seal() +
            super<DensityRidgesStatAesthetics>.seal() +
            super<DensityRidgesStatParameters>.seal() +
            super<WithColorOption>.seal() +
            super<WithFillOption>.seal()
}