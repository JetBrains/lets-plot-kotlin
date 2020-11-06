/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.GeomKind
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.SamplingOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.GeomOptions
import jetbrains.letsPlot.intern.layer.TooltipOptions
import jetbrains.letsPlot.intern.layer.geom.SmoothAesthetics
import jetbrains.letsPlot.intern.layer.geom.SmoothMapping
import jetbrains.letsPlot.intern.layer.stat.SmoothStatParameters

@Suppress("ClassName")
/**
 * Add a smoothed conditional mean.
 * @param data dictionary, optional.
 *     The data to be displayed in this layer. If None, the default, the data
 *     is inherited from the plot data as specified in the call to [lets_plot][jetbrains.letsPlot.lets_plot].
 * @param stat string, optional.
 *      The statistical transformation to use on the data for this layer.
 * @param position string, optional.
 *     Position adjustment, either as a string ("identity", "stack", "dodge", ...), or the result of a call to a
 *     position adjustment function.
 * @param tooltips result of the call to the layer_tooltips() function.
 *     Specifies appearance, style and content.
 * @param x x-axis value.
 * @param y predicted (smoothed) value.
 * @param ymin lower pointwise confidence interval around the mean.
 * @param ymax upper pointwise confidence interval around the mean.
 * @param alpha transparency level of a layer.
 *     Understands numbers between 0 and 1.
 * @param color (colour) color of a geometry.
 *     Can be continuous or discrete. For continuous value this will be a color gradient between two colors.
 * @param size lines width.
 *     Defines line width for conditional mean and confidence bounds lines.
 * @param linetype type of the line of tile's border
 *     Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *     5 = "longdash", 6 = "twodash"
 * @param method smoothing method: lm (Linear Model) or loess (Locally Estimated Scatterplot Smoothing).
 *      Default is 'lm'.
 * @param n number of points to evaluate smoother at. Default is 80.
 * @param se boolean, to display confidence interval around smooth. Default - true.
 * @param level level of confidence interval to use. Default - 0.95.
 * @param span number, optional. Default - 0.5.
 *     Only for LOESS method. The fraction of source points closest to the current point
 *     is taken into account for computing a least-squares regression. A sensible value is usually 0.25 to 0.5.
 * @param deg degree of polynomial for linear regression model. Default - 1.
 * @param seed random seed for LOESS sampling.
 * @param maxN maximum number of data-points for LOESS method. Default - 1000.
 *      If this quantity exceeded random sampling is applied to data.
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */
class geom_smooth(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.smooth(),
    position: PosOptions = Pos.identity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val ymin: Double? = null,
    override val ymax: Double? = null,
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
    mapping: SmoothMapping.() -> Unit = {}
) : SmoothAesthetics,
    SmoothStatParameters,
    LayerBase(
        mapping = SmoothMapping().apply(mapping).seal(),
        data = data,
        geom = GeomOptions(GeomKind.SMOOTH),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal(): Options {
        return super<SmoothAesthetics>.seal() +
                super<SmoothStatParameters>.seal()
    }
}