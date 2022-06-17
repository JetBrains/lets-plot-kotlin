/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom
import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.QQAesthetics
import jetbrains.letsPlot.intern.layer.geom.QQMapping
import jetbrains.letsPlot.intern.layer.stat.QQStatAesthetics
import jetbrains.letsPlot.intern.layer.stat.QQStatParameters
import jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Display quantile-quantile plot.
 *
 * @param data
 *     The data to be displayed in this layer. If None, the default, the data
 *     is inherited from the plot data as specified in the call to [letsPlot][jetbrains.letsPlot.letsPlot].
 * @param stat
 *     The statistical transformation to use on the data for this layer. Supported transformations:
 *     "identity" (leaves the data unchanged),
 *     "qq" (compare two probability distributions),
 *     "count" (counts number of points with same x-axis coordinate),
 *     "bin" (counts number of points with x-axis coordinate in the same bin),
 *     "smooth" (performs smoothing - linear default),
 *     "density" (computes and draws kernel density estimate).
 *     Statistic types: [letsPlot][jetbrains.letsPlot.Stat].
 * @param position
 *     Position adjustment: Pos.identity, Pos.stack,  etc. - see [letsPlot][jetbrains.letsPlot.Pos].
 * @param showLegend boolean, optional, default=true.
 *     False - do not show legend for this layer.
 * @param tooltips result of the call to the layerTooltips() function.
 *     Specifies appearance, style and content.
 * @param distribution string, optional
 *     Distribution function to use: "norm" (default), "uniform", "t", "gamma", "exp", "chi2".
 * @param dParams list of numbers
 *     Additional parameters passed on to distribution function.
 *     If `distribution` is `"norm"` then `dParams` is a pair [mean, std] (=[0.0, 1.0] by default).
 *     If `distribution` is `"uniform"` then `dParams` is a pair [a, b] (=[0.0, 1.0] by default).
 *     If `distribution` is `"t"` then `dParams` is an integer number [d] (=[1] by default).
 *     If `distribution` is `"gamma"` then `dParams` is a pair [alpha, beta] (=[1.0, 1.0] by default).
 *     If `distribution` is `"exp"` then `dParams` is a float number [lambda] (=[1.0] by default).
 *     If `distribution` is `"chi2"` then `dParams` is an integer number [k] (=[1] by default).
 *
 * @param sample y-axis value.
 * @param alpha transparency level of the point
 *     Understands numbers between 0 and 1.
 * @param color (colour) color of the geometry.
 *     Can be continuous or discrete. For continuous value this will be a color gradient between two colors.
 * @param fill color to paint shape's inner points.
 *     Is applied only to the points of shapes having inner points.
 * @param shape shape of the point.
 * @param size size of the point.
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */
class geomQQ(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.qq(),
    position: jetbrains.letsPlot.intern.layer.PosOptions = Pos.identity,
    showLegend: Boolean = true,
    sampling: jetbrains.letsPlot.intern.layer.SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val sample: Double? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val shape: Any? = null,
    override val size: Number? = null,
    override val distribution: String? = null,
    override val dParams: List<Number>? = null,
    mapping: QQMapping.() -> Unit = {}
) : QQAesthetics,
    QQStatAesthetics,
    QQStatParameters,
    jetbrains.letsPlot.intern.layer.LayerBase(
        mapping = QQMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.qq(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    ) {

    override fun seal(): Options {
        return super<QQAesthetics>.seal() +
                super<QQStatAesthetics>.seal() +
                super<QQStatParameters>.seal()
    }
}