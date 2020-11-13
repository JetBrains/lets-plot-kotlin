/*
 * Copyright (c) 2020. JetBrains s.r.o.
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
import jetbrains.letsPlot.tooltips.TooltipOptions
import jetbrains.letsPlot.intern.layer.geom.PointRangeAesthetics
import jetbrains.letsPlot.intern.layer.geom.PointRangeMapping

@Suppress("ClassName", "SpellCheckingInspection")
/**
 * Add a vertical line defined by upper and lower value with midpoint at y location.
 * @param data dictionary or pandas DataFrame, optional.
 *     The data to be displayed in this layer. If None, the default, the data
 *     is inherited from the plot data as specified in the call to [lets_plot][jetbrains.letsPlot.lets_plot].
 * @param stat string, optional.
 *      The statistical transformation to use on the data for this layer.
 * @param position string, optional.
 *     Position adjustment, either as a string ("identity", "stack", "dodge", ...), or the result of a call to a
 *     position adjustment function.
 * @param tooltips result of the call to the layer_tooltips() function.
 *     Specifies appearance, style and content.
 * @param fatten : number, default: 5.0
 *     A multiplicative factor applied to size of the middle bar
 * @param x x-axis coordinates.
 * @param y position of mid-point,
 * @param ymin lower bound for error bar.
 * @param ymax upper bound for error bar.
 * @param alpha transparency level of a layer
 *     Understands numbers between 0 and 1.
 * @param color (colour) color of a geometry lines.
 *     Can be continuous or discrete. For continuous value this will be a color gradient between two colors.
 * @param fill color of geometry filling.
 * @param size lines width, size of mid-point.
 * @param linetype type of the line of tile's border
 *     Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *     5 = "longdash", 6 = "twodash"
 * @param shape shape of the mid-point.
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */
class geom_pointrange(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = Pos.identity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    val fatten: Double? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val ymin: Double? = null,
    override val ymax: Double? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val shape: Any? = null,
    override val size: Number? = null,
    mapping: PointRangeMapping.() -> Unit = {}
) : PointRangeAesthetics,
    LayerBase(
        mapping = PointRangeMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.pointrange(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal(): Options {
        return super.seal() + Options.of("fatten" to fatten)
    }
}