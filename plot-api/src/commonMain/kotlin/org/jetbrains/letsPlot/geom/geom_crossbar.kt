/*
 * Copyright (c) 2021. JetBrains s.r.o.
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
import org.jetbrains.letsPlot.intern.layer.WithColorOption
import org.jetbrains.letsPlot.intern.layer.WithFillOption
import org.jetbrains.letsPlot.intern.layer.geom.CrossBarAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.CrossBarMapping
import org.jetbrains.letsPlot.pos.positionDodge
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Display bars with horizontal median line.
 *
 * ## Examples
 *
 * - [error_bars.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/error_bars.ipynb)
 *
 * @param data dictionary or pandas DataFrame, optional
 *     The data to be displayed in this layer. If None, the default, the data
 *     is inherited from the plot data as specified in the call to ggplot.
 * @param stat string, optional
 *     The statistical transformation to use on the data for this layer.
 * @param position string, optional
 *     Position adjustment, either as a string ("identity", "stack", "dodge",...), or the result of a call to a
 *
 * @param tooltips result of the call to the layerTooltips() function.
 *     Specifies appearance, style and content.
 * @param fatten : number, default: 2.5
 *     A multiplicative factor applied to size of the middle bar
 * @param x x-axis coordinates
 * @param ymin lower bound for error bar.
 * @param ymax upper bound for error bar.
 * @param middle position of median bar.
 * @param width width of a bar. Typically ranges between 0 and 1. Values that are greater than 1 lead to overlapping of the bars.
 * @param alpha transparency level of a layer
 *     Understands numbers between 0 and 1.
 * @param color (colour) color of a geometry lines
 *     Can be continuous or discrete. For continuous value this will be a color gradient between two colors.
 * @param fill color of geometry filling.
 * @param size lines width.
 * @param linetype type of the line of tile's border
 *     Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *     5 = "longdash", 6 = "twodash"
 * @param colorBy String, {"fill", "color", "paint_a", "paint_b", "paint_c"}, default = "color".
 *  Defines the color aesthetic for the geometry.
 * @param fillBy String, {"fill", "color", "paint_a", "paint_b", "paint_c"}, default = "fill".
 *  Defines the fill aesthetic for the geometry.
 * @param mapping set of aesthetic mappings created by aes() function.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */

class geomCrossbar(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = positionDodge(),
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    val fatten: Number? = null,
    override val x: Number? = null,
    override val ymin: Number? = null,
    override val ymax: Number? = null,
    override val middle: Number? = null,
    override val width: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val shape: Any? = null,
    override val size: Number? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: CrossBarMapping.() -> Unit = {}
) : CrossBarAesthetics,
    WithColorOption,
    WithFillOption,
    LayerBase(
        mapping = CrossBarMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.crossbar(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal(): Options {
        return super<CrossBarAesthetics>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal() +
                Options.of("fatten" to fatten)
    }
}