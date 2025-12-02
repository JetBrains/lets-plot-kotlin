/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.StatOptions
import org.jetbrains.letsPlot.intern.layer.WithColorOption
import org.jetbrains.letsPlot.intern.layer.WithFillOption
import org.jetbrains.letsPlot.intern.layer.geom.QQAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.QQMapping
import org.jetbrains.letsPlot.intern.layer.stat.QQStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.QQStatParameters
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Displays quantile-quantile plot.
 *
 * ## Notes
 *
 * The Q-Q plot is used for comparing two probability distributions
 * (sample and theoretical) by plotting their quantiles against each other.
 *
 * If the two distributions being compared are similar, the points in the Q-Q plot
 * will approximately lie on the straight line.
 *
 * Computed variables:
 *
 * - ..theoretical.. : theoretical quantiles.
 * - ..sample.. : sample quantiles.
 *
 * To hide axis tooltips, set "blank" or the result of [elementBlank()][org.jetbrains.letsPlot.themes.elementBlank]
 * to the `axisTooltip`, `axisTooltipX` or `axisTooltipY` parameter of the [theme()][org.jetbrains.letsPlot.themes.theme].
 *
 * ## Examples
 *
 * - [qq_plots.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/qq_plots.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat default = `Stat.qq()`. The statistical transformation to use on the data for this layer.
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
 * @param distribution default = "norm".
 *  Distribution function to use: "norm", "uniform", "t", "gamma", "exp", "chi2".
 * @param dParams Additional parameters passed on to distribution function.
 *  - If `distribution` is "norm" then `dParams` is a pair (mean, std) (default = listOf(0.0, 1.0)).
 *  - If `distribution` is "uniform" then `dParams` is a pair (a, b) (default = listOf(0.0, 1.0)).
 *  - If `distribution` is "t" then `dParams` is an integer number (d) (default = listOf(1)).
 *  - If `distribution` is "gamma" then `dParams` is a pair (alpha, beta) (default = listOf(1.0, 1.0)).
 *  - If `distribution` is "exp" then `dParams` is a float number (lambda) (default = listOf(1.0)).
 *  - If `distribution` is "chi2" then `dParams` is an integer number (k) (default = listOf(1)).
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param sample Y-axis value.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Color to paint shape's inner points.
 *  Is applied only to the points of shapes having inner points.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param shape Shape of the point.
 *  For more info see: [aesthetics.html#point-shapes](https://lets-plot.org/kotlin/aesthetics.html#point-shapes).
 * @param size Size of the point.
 * @param stroke Width of the shape border. Applied only to the shapes having border.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class geomQQ(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.qq(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    inheritAes: Boolean? = null,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val sample: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val shape: Any? = null,
    override val size: Number? = null,
    override val stroke: Number? = null,
    override val distribution: String? = null,
    override val dParams: List<Number>? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: QQMapping.() -> Unit = {}
) : QQAesthetics,
    QQStatAesthetics,
    QQStatParameters,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = QQMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.qq(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        inheritAes = inheritAes,
        manualKey = manualKey,
        sampling = sampling,
        tooltips = tooltips
    ) {

    override fun seal(): Options {
        return super<QQAesthetics>.seal() +
                super<QQStatAesthetics>.seal() +
                super<QQStatParameters>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }
}