/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.GeomKind
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.ViolinAesthetics
import jetbrains.letsPlot.intern.layer.geom.ViolinMapping
import jetbrains.letsPlot.intern.layer.stat.YDensityStatAesthetics
import jetbrains.letsPlot.intern.layer.stat.YDensityStatParameters
import jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName", "SpellCheckingInspection")
/**
 * A violin plot is a mirrored density plot with an additional grouping as for a boxplot.
 *
 * @param data
 *     The data to be displayed in this layer. If None, the default, the data
 *     is inherited from the plot data as specified in the call to [letsPlot][jetbrains.letsPlot.letsPlot].
 * @param stat string, optional, default="ydensity".
 *     The statistical transformation to use on the data for this layer.
 * @param position
 *     Position adjustment: Pos.identity, Pos.stack,  etc. - see [letsPlot][jetbrains.letsPlot.Pos].
 * @param showLegend Boolean, optional, default=True.
 *      False - do not show legend for this layer.
 * @param tooltips result of the call to the layerTooltips() function.
 *     Specifies appearance, style and content.
 * @param orientation  string, optional
 *     Specifies the axis that the layer' stat and geom should run along.
 *     Possible values: 'x' (default), 'y'.
 * @param drawQuantiles list of float, optional.
 *     Draw horizontal lines at the given quantiles of the density estimate.
 * @param x x-axis coordinates.
 * @param y y-axis coordinates.
 * @param violinWidth density scaled for the violin plot, according to area, counts or to a constant maximum width.
 * @param alpha transparency level of a layer
 *     Understands numbers between 0 and 1.
 * @param color (colour) color of a geometry lines.
 *     Can be continuous or discrete. For continuous value this will be a color gradient between two colors.
 * @param fill color of geometry filling.
 * @param linetype type of the line of tile's border.
 *     Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *     5 = "longdash", 6 = "twodash".
 * @param size lines width.
 *     Defines line width
 * @param width width of violin bounding box
 * @param weight used by "ydensity" stat to compute weighted density.
 * @param scale string, optional.
 *     If 'area' (default), all violins have the same area.
 *     If 'count', areas are scaled proportionally to the number of observations.
 *     If 'width', all violins have the same maximum width.
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */
class geomViolin(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.yDensity(),
    position: jetbrains.letsPlot.intern.layer.PosOptions = Pos.dodge,
    showLegend: Boolean = true,
    sampling: jetbrains.letsPlot.intern.layer.SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    orientation: String? = null,
    private val drawQuantiles: Any? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val violinWidth: Double? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val width: Double? = null,
    override val weight: Double? = null,
    override val scale: String? = null,
    override val bw: Any? = null,
    override val kernel: String? = null,
    override val n: Int? = null,
    override val trim: Boolean? = null,
    override val adjust: Number? = null,
    mapping: ViolinMapping.() -> Unit = {}
) : ViolinAesthetics,
    YDensityStatAesthetics,
    YDensityStatParameters,
    jetbrains.letsPlot.intern.layer.LayerBase(
        mapping = ViolinMapping().apply(mapping).seal(),
        data = data,
        geom = jetbrains.letsPlot.intern.layer.GeomOptions(GeomKind.VIOLIN),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips,
        orientation = orientation
    ) {

    override fun seal() = super<ViolinAesthetics>.seal() +
            super<YDensityStatAesthetics>.seal() +
            super<YDensityStatParameters>.seal() +
            Options.of("draw_quantiles" to drawQuantiles)
}
