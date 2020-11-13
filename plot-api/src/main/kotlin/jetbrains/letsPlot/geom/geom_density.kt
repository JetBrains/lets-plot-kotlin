/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.GeomKind
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.SamplingOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.GeomOptions
import jetbrains.letsPlot.tooltips.TooltipOptions
import jetbrains.letsPlot.intern.layer.geom.AreaAesthetics
import jetbrains.letsPlot.intern.layer.geom.DensityMapping
import jetbrains.letsPlot.intern.layer.stat.DensityStatAesthetics
import jetbrains.letsPlot.intern.layer.stat.DensityStatParameters

@Suppress("ClassName")
/**
 * Display a density estimate, which is a smoothed version of the histogram.
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
 * @param tooltips result of the call to the layer_tooltips() function.
 *     Specifies appearance, style and content.
 * @param x x-axis coordinates.
 * @param alpha transparency level of a layer
 *     Understands numbers between 0 and 1.
 * @param color (colour) color of a geometry lines.
 *     Can be continuous or discrete. For continuous value this will be a color gradient between two colors.
 * @param size lines width.
 *     Defines line width
 * @param linetype type of the line of tile's border.
 *     Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *     5 = "longdash", 6 = "twodash".
 * @param fill color of geometry filling.
 * @param weight used by "density" stat to compute weighted density.
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */
class geom_density(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.density(),
    position: PosOptions = Pos.identity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val weight: Any? = null,
    override val bw: Any? = null,
    override val kernel: String? = null,
    override val n: Int? = null,
    override val trim: Boolean? = null,
    override val adjust: Number? = null,
    mapping: DensityMapping.() -> Unit = {}

) : AreaAesthetics,
    DensityStatAesthetics,
    DensityStatParameters,
    LayerBase(
        mapping = DensityMapping().apply(mapping).seal(),
        data = data,
        geom = GeomOptions(GeomKind.DENSITY),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    ) {

    override fun seal() = super<AreaAesthetics>.seal() +
            super<DensityStatAesthetics>.seal() +
            super<DensityStatParameters>.seal()
}


