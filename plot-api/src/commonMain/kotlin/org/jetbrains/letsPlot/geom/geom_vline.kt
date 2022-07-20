/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom.vline
import org.jetbrains.letsPlot.Pos.identity
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.layer.LayerBase
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.StatOptions
import org.jetbrains.letsPlot.intern.layer.geom.VLineAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.VLineMapping
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName", "SpellCheckingInspection")
/**
 * Add a straight vertical line to the plot.
 *
 * ## Examples
 *
 * - [lines.ipynb](https://nbviewer.jupyter.org/github/HIL-HK/lets-plot-examples/blob/master/kotlin/lines.ipynb)
 *
 * @param data
 *     The data to be displayed in this layer. If None, the default, the data
 *     is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat
 *     The statistical transformation to use on the data for this layer. Supported transformations:
 *     "identity" (leaves the data unchanged), "count" (counts number of points with same x-axis coordinate),
 *     "bin" (counts number of points with x-axis coordinate in the same bin), "smooth" (performs smoothing -
 *     linear default).
 *     Statistic types: [letsPlot][org.jetbrains.letsPlot.Stat].
 * @param position
 *     Position adjustment: Pos.identity, Pos.stack,  etc. - see [letsPlot][org.jetbrains.letsPlot.Pos].
 *
 * @param tooltips result of the call to the layerTooltips() function.
 *     Specifies appearance, style and content.
 * @param xintercept line x-intercept.
 *     The value of x at the point where the line crosses the x axis.
 * @param alpha transparency level of a layer
 *     Understands numbers between 0 and 1.
 * @param color (colour) color of a geometry lines.
 *     Can be continuous or discrete. For continuous value this will be a color gradient between two colors.
 * @param size lines width.
 * @param linetype type of the line.
 *     Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *     5 = "longdash", 6 = "twodash".
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */
class geomVLine(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = identity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val xintercept: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    mapping: VLineMapping.() -> Unit = {}

) : VLineAesthetics,
    LayerBase(
        mapping = VLineMapping().apply(mapping).seal(),
        data = data,
        geom = vline(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    )

