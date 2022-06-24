/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom
import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.HistogramAesthetics
import jetbrains.letsPlot.intern.layer.geom.HistogramMapping
import jetbrains.letsPlot.intern.layer.stat.BinStatAesthetics
import jetbrains.letsPlot.intern.layer.stat.BinStatParameters
import jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Display a 1d distribution by dividing variable mapped to x axis into bins and counting the number of observations in each bin.
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
 * @param x x-axis value (this values will produce cases or bins for bars).
 * @param y y-axis value (this value will be used to multiply the bar heights), setting y to '..density..' produces
 *     normalized (density) histogram.
 * @param alpha transparency level of a layer
 *     Understands numbers between 0 and 1.
 * @param color (colour) color of a geometry lines.
 *     Can be continuous or discrete. For continuous value this will be a color gradient between two colors.
 * @param fill color of geometry filling.
 * @param size line width.
 *     Defines bar line width.
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */
class geomHistogram(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.bin(),
    position: jetbrains.letsPlot.intern.layer.PosOptions = Pos.stack,
    showLegend: Boolean = true,
    sampling: jetbrains.letsPlot.intern.layer.SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
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
    mapping: HistogramMapping.() -> Unit = {}

) : HistogramAesthetics,
    BinStatAesthetics,
    BinStatParameters,
    jetbrains.letsPlot.intern.layer.LayerBase(
        mapping = HistogramMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.histogram(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips,
        orientation = orientation
    ) {
    override fun seal() = super<HistogramAesthetics>.seal() +
            super<BinStatAesthetics>.seal() +
            super<BinStatParameters>.seal()
}


