/*
 * Copyright (c) 2021. JetBrains s.r.o.
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
import org.jetbrains.letsPlot.intern.layer.geom.SmoothAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.SmoothMapping
import org.jetbrains.letsPlot.intern.layer.stat.SmoothStatParameters
import org.jetbrains.letsPlot.pos.positionIdentity

@Suppress("ClassName")
/**
 * Displays a smoothed conditional mean.
 *
 * ## Notes
 *
 * `statSmooth()` aids the eye in seeing patterns in the presence of overplotting.
 *
 * Computed variables:
 *
 * - ..y.. : predicted (smoothed) value.
 * - ..ymin.. : lower pointwise confidence interval around the mean.
 * - ..ymax.. : upper pointwise confidence interval around the mean.
 * - ..se.. : standard error.
 *
 * To hide axis tooltips, set "blank" or the result of [elementBlank()][org.jetbrains.letsPlot.themes.elementBlank]
 * to the `axisTooltip` or `axisTooltipX` parameter of the [theme()][org.jetbrains.letsPlot.themes.theme].
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param geom The geometry to display the smooth stat for this layer, default is smoothed line,
 *  see [Geom][org.jetbrains.letsPlot.Geom].
 * @param position Position adjustment: [positionIdentity][org.jetbrains.letsPlot.pos.positionIdentity], [positionStack()][org.jetbrains.letsPlot.pos.positionStack], [positionDodge()][org.jetbrains.letsPlot.pos.positionDodge], etc. see
 *  [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
 * @param showLegend default = true.
 *  false - do not show legend for this layer.
 * @param inheritAes default = true.
 *  false - do not combine the layer aesthetic mappings with the plot shared mappings.
 * @param sampling Result of the call to the `samplingXxx()` function.
 *  To prevent any sampling for this layer pass value [samplingNone][org.jetbrains.letsPlot.sampling.samplingNone].
 *  For more info see [sampling.html](https://lets-plot.org/kotlin/sampling.html).
 * @param x X-axis value.
 * @param y Predicted (smoothed) value.
 * @param ymin Lower pointwise confidence interval around the mean.
 * @param ymax Upper pointwise confidence interval around the mean.
 * @param size Lines width.
 * @param linetype Type of the line.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param color Color of the geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param method default = "lm".
 *  Smoothing method: lm (Linear Model) or loess (Locally Estimated Scatterplot Smoothing).
 * @param n default = 80. Number of points to evaluate smoother at.
 * @param level default = 0.95. Level of confidence interval to use.
 * @param se default = true. To display confidence interval around smooth.
 * @param span default = 0.5.
 *  Only for LOESS method. The fraction of source points closest to the current point
 *  is taken into account for computing a least-squares regression. A sensible value is usually 0.25 to 0.5.
 * @param deg default = 1. Degree of polynomial for linear regression model.
 * @param seed Random seed for LOESS sampling.
 * @param maxN default = 1000. Maximum number of data-points for LOESS method.
 *  If this quantity exceeded random sampling is applied to data.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class statSmooth(
    data: Map<*, *>? = null,
    geom: GeomOptions = GeomOptions(GeomKind.SMOOTH),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    inheritAes: Boolean? = null,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val ymin: Number? = null,
    override val ymax: Number? = null,
    override val size: Number? = null,
    override val linetype: Any? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val alpha: Number? = null,
    override val method: String? = null,
    override val n: Int? = null,
    override val level: Number? = null,
    override val se: Boolean? = null,
    override val span: Number? = null,
    override val deg: Int? = null,
    override val seed: Long? = null,
    override val maxN: Int? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: SmoothMapping.() -> Unit = {}
) : SmoothAesthetics,
    SmoothStatParameters,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = SmoothMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.smooth(),
        position = position,
        showLegend = showLegend,
        inheritAes = inheritAes,
        manualKey = manualKey,
        sampling = sampling
    ) {
    override fun seal(): Options {
        return super<SmoothAesthetics>.seal() +
                super<SmoothStatParameters>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }
}