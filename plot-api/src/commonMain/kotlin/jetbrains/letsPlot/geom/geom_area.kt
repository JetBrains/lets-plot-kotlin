/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom.area
import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat.identity
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.SamplingOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.AreaAesthetics
import jetbrains.letsPlot.intern.layer.geom.AreaMapping
import jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Display the development of quantitative values over an interval. This is the continuous analog of geomBar.
 *
 * ## Examples
 *
 * - [marginal_layers.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/marginal_layers.ipynb)
 *
 * - [tooltip_config.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/tooltip_config.ipynb)
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
 *
 * @param tooltips result of the call to the layerTooltips() function.
 *     Specifies appearance, style and content.
 * @param x x-axis coordinates.
 * @param y y-axis coordinates.
 * @param alpha transparency level of a layer
 *     Understands numbers between 0 and 1.
 * @param color (colour) color of a geometry lines.
 *     Can be continuous or discrete. For continuous value this will be a color gradient between two colors.
 * @param fill color of geometry filling.
 * @param linetype type of the line of tile's border.
 *     Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *     5 = "longdash", 6 = "twodash".
 * @param size line width.
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */
class geomArea(
    data: Map<*, *>? = null,
    stat: StatOptions = identity,
    position: PosOptions = Pos.stack,
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
    mapping: AreaMapping.() -> Unit = {}

) : AreaAesthetics,
    LayerBase(
        mapping = AreaMapping().apply(mapping).seal(),
        data = data,
        geom = area(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    )


