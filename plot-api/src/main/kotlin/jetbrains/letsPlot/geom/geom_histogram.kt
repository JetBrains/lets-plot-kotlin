/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom
import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.SamplingOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.HistogramAesthetics
import jetbrains.letsPlot.intern.layer.geom.HistogramMapping
import jetbrains.letsPlot.intern.layer.stat.BinStatAesthetics
import jetbrains.letsPlot.intern.layer.stat.BinStatParameters

@Suppress("ClassName")
/**
 * Displays a 1d distribution by dividing variable mapped to x axis into bins and counting the number of observations in each bin.
 * @param data dictionary or pandas DataFrame, optional.
 *     The data to be displayed in this layer. If None, the default, the data
 *     is inherited from the plot data as specified in the call to [lets_plot][jetbrains.letsPlot.lets_plot].
 * @param stat string, optional.
 *     The statistical transformation to use on the data for this layer, as a string. Supported transformations:
 *     "identity" (leaves the data unchanged), "count" (counts number of points with same x-axis coordinate),
 *     "bin" (counts number of points with x-axis coordinate in the same bin), "smooth" (performs smoothing -
 *     linear default)
 * @param position string, optional.
 *     Position adjustment, either as a string ("identity", "stack", "dodge", ...), or the result of a call to a
 *     position adjustment function.
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
class geom_histogram(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.bin(),
    position: PosOptions = Pos.stack,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val alpha: Double? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val size: Double? = null,
    override val weight: Any? = null,
    override val binCount: Int = BinStatParameters.DEF_BIN_COUNT,
    override val binWidth: Number? = null,
    override val center: Number? = null,
    override val boundary: Number? = null,
    mapping: HistogramMapping.() -> Unit = {}

) : HistogramAesthetics,
    BinStatAesthetics,
    BinStatParameters,
    LayerBase(
        mapping = HistogramMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.histogram(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling
    ) {
    override fun seal() = super<HistogramAesthetics>.seal() +
            super<BinStatAesthetics>.seal() +
            super<BinStatParameters>.seal()
}


