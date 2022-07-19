/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom
import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.SamplingOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.LineAesthetics
import jetbrains.letsPlot.intern.layer.geom.LineMapping
import jetbrains.letsPlot.intern.layer.stat.BinStatParameters
import jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName", "SpellCheckingInspection")
/**
 * Display a line chart which makes the y value proportional to the number of observed variable values, mapped to x axis.
 *
 * ## Examples
 *
 * - [distributions.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/distributions.ipynb)
 *
 * @param data
 *     The data to be displayed in this layer. If None, the default, the data
 *     is inherited from the plot data as specified in the call to [letsPlot][jetbrains.letsPlot.letsPlot].
 * @param stat
 *     The statistical transformation to use on the data for this layer. Supported transformations:
 *     "identity" (leaves the data unchanged), "count" (counts number of points with same x-axis coordinate),
 *     "bin" (counts number of points with x-axis coordinate in the same bin), "smooth" (performs smoothing -
 *     linear default).
 *     Statistic types: [letsPlot][jetbrains.letsPlot.Stat].
 * @param position
 *     Position adjustment: Pos.identity, Pos.stack,  etc. - see [letsPlot][jetbrains.letsPlot.Pos].
 * @param tooltips result of the call to the layerTooltips() function.
 *     Specifies appearance, style and content.
 * @param orientation Specifies the axis that the layer' stat and geom should run along.
 *     Possible values: 'x' (default), 'y'.
 * @param x x-axis value.
 * @param y y-axis value.
 * @param alpha transparency level of a point
 *     Understands numbers between 0 and 1.
 * @param color (colour) color of a geometry.
 *     Can be continuous or discrete. For continuous value this will be a color gradient between two colors.
 * @param linetype type of the line.
 *     Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *     5 = "longdash", 6 = "twodash".
 * @param size line width.
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */
class geomFreqpoly(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.bin(),
    position: PosOptions = Pos.identity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    orientation: String? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val bins: Int? = null,
    override val binWidth: Number? = null,
    override val center: Number? = null,
    override val boundary: Number? = null,
    mapping: LineMapping.() -> Unit = {}
) : LineAesthetics,
    BinStatParameters,
    LayerBase(
        mapping = LineMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.line(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips,
        orientation = orientation
    ) {
    override fun seal(): Options {
        return super<LineAesthetics>.seal() +
                super<BinStatParameters>.seal()
    }
}