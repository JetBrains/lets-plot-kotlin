/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.stat

import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.GeomOptions
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.WithColorOption
import org.jetbrains.letsPlot.intern.layer.WithFillOption
import org.jetbrains.letsPlot.intern.layer.geom.ViolinAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.ViolinMapping
import org.jetbrains.letsPlot.intern.layer.stat.YDensityStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.YDensityStatParameters
import org.jetbrains.letsPlot.pos.positionDodge

@Suppress("ClassName", "SpellCheckingInspection")
/**
 * Displays a violin plot.
 *
 * ## Notes
 *
 * Computed variables:
 *
 * - ..violinwidth.. : density scaled for the violin plot, according to area, counts or to a constant maximum width (mapped by default).
 * - ..density.. : density estimate.
 * - ..count.. : density * number of points.
 * - ..scaled.. : density estimate, scaled to maximum of 1.
 * - ..quantile.. : quantile estimate.
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param geom The geometry to display the y-density stat for this layer, default is violin,
 *  see [Geom][org.jetbrains.letsPlot.Geom].
 * @param position default = `positionDodge()`. Position adjustment: `positionIdentity`, `positionStack()`, `positionDodge()`, etc. see
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
 * @param violinWidth Density scaled for the violin plot, according to area, counts or to a constant maximum width.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param linetype Type of the line.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param size Lines width.
 * @param width Geometry width.
 * @param weight Used by the stat to compute weighted density.
 * @param scale default = "area".
 *  - If "area", all violins have the same area.
 *  - If "count", areas are scaled proportionally to the number of observations.
 *  - If "width", all violins have the same maximum width.
 * @param tailsCutoff default = 3.0.
 *  Extends domain of each violin on `tailsCutoff * bw` if `trim = false`.
 * @param bw The method (or exact value) of bandwidth. Either a string (choose among "nrd0" and "nrd") or a double.
 * @param kernel The kernel we use to calculate the density function. Choose among "gaussian", "cosine", "optcosine",
 *  "rectangular" (or "uniform"), "triangular", "biweight" (or "quartic"), "epanechikov" (or "parabolic").
 * @param n The number of sampled points for plotting the function.
 * @param trim default = true.
 *  Trims the tails of the violins to the range of the data.
 * @param adjust Adjusts the value of bandwidth by multiplying it. Changes how smooth the frequency curve is.
 * @param fullScanMax Maximum size of data to use density computation with "full scan".
 *  For bigger data, less accurate but more efficient density computation is applied.
 * @param quantiles default = listOf(0.25, 0.5, 0.75).
 *  Calculates given quantiles of the density estimate.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class statYDensity(
    data: Map<*, *>? = null,
    geom: GeomOptions = GeomOptions(GeomKind.VIOLIN),
    position: PosOptions = positionDodge(),
    showLegend: Boolean = true,
    inheritAes: Boolean? = null,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val violinWidth: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val width: Number? = null,
    override val weight: Number? = null,
    override val scale: String? = null,
    override val tailsCutoff: Number? = null,
    override val bw: Any? = null,
    override val kernel: String? = null,
    override val n: Int? = null,
    override val trim: Boolean? = null,
    override val adjust: Number? = null,
    override val fullScanMax: Int? = null,
    override val quantiles: List<Number>? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: ViolinMapping.() -> Unit = {}
) : ViolinAesthetics,
    YDensityStatAesthetics,
    YDensityStatParameters,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = ViolinMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.yDensity(),
        position = position,
        showLegend = showLegend,
        inheritAes = inheritAes,
        manualKey = manualKey,
        sampling = sampling
    ) {

    override fun seal(): Options {
        return super<ViolinAesthetics>.seal() +
                super<YDensityStatAesthetics>.seal() +
                super<YDensityStatParameters>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }
}