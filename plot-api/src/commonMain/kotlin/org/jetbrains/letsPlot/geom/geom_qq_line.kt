/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.LayerBase
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.StatOptions
import org.jetbrains.letsPlot.intern.layer.WithColorByParameter
import org.jetbrains.letsPlot.intern.layer.geom.QQLineAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.QQLineMapping
import org.jetbrains.letsPlot.intern.layer.stat.QQLineStatParameters
import org.jetbrains.letsPlot.intern.layer.stat.QQStatAesthetics
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Display quantile-quantile fitting line.
 *
 * ## Examples
 *
 * - [qq_plots.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/qq_plots.ipynb)
 *
 * @param data
 *     The data to be displayed in this layer. If None, the default, the data
 *     is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat
 *     The statistical transformation to use on the data for this layer. Supported transformations:
 *     "identity" (leaves the data unchanged),
 *     "qq_line" (compare two probability distributions),
 *     "count" (counts number of points with same x-axis coordinate),
 *     "bin" (counts number of points with x-axis coordinate in the same bin),
 *     "smooth" (performs smoothing - linear default),
 *     "density" (computes and draws kernel density estimate).
 *     Statistic types: [letsPlot][org.jetbrains.letsPlot.Stat].
 * @param position
 *     Position adjustment: Pos.identity, Pos.stack,  etc. - see [letsPlot][org.jetbrains.letsPlot.Pos].
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
 * @param quantiles pair of numbers, default=[0.25, 0.75]
 *     Pair of quantiles to use when fitting the Q-Q line.
 * @param colorBy String, {"fill", "color", "paint_a", "paint_b", "paint_c"}, default = "color".
 *  Defines the color aesthetic for the geometry.
 * @param sample y-axis value.
 * @param alpha transparency level of the point
 *     Understands numbers between 0 and 1.
 * @param color (colour) color of the geometry.
 *     Can be continuous or discrete. For continuous value this will be a color gradient between two colors.
 * @param linetype type of the line.
 *     Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *     5 = "longdash", 6 = "twodash".
 * @param size line width.
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */
class geomQQLine(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.qqLine(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val sample: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val distribution: String? = null,
    override val dParams: List<Number>? = null,
    override val quantiles: Pair<Number, Number>? = null,
    override val colorBy: String? = null,
    mapping: QQLineMapping.() -> Unit = {}
) : QQLineAesthetics,
    QQStatAesthetics,
    QQLineStatParameters,
    WithColorByParameter,
    LayerBase(
        mapping = QQLineMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.qqLine(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    ) {

    override fun seal(): Options {
        return super<QQLineAesthetics>.seal() +
                super<QQStatAesthetics>.seal() +
                super<QQLineStatParameters>.seal() +
                super<WithColorByParameter>.seal()
    }
}