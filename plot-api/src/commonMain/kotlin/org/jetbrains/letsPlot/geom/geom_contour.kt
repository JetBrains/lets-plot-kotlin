/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */


package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Pos.identity
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.geom.ContourMapping
import org.jetbrains.letsPlot.intern.layer.geom.PathAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.ContourStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.ContourStatParameters
import org.jetbrains.letsPlot.tooltips.TooltipOptions
import org.jetbrains.letsPlot.intern.layer.*

@Suppress("ClassName")
/**
 * Display contours of a 3d surface in 2d.
 *
 * ## Examples
 *
 * - [contours.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/contours.ipynb)
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
 * @param bins int, optional.
 *     Number of levels.
 * @param binWidth double, optional.
 *     Distance between levels.
 * @param x x-axis coordinates of the center of rectangles, forming a tessellation.
 * @param y y-axis coordinates of the center of rectangles, forming a tessellation.
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
class geomContour(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.contour(),
    position: PosOptions = identity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val z: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val speed: Number? = null,
    override val flow: Number? = null,
    override val bins: Int? = null,
    override val binWidth: Number? = null,
    mapping: ContourMapping.() -> Unit = {}
) : PathAesthetics,
    ContourStatAesthetics,
    ContourStatParameters,
    LayerBase(
        mapping = ContourMapping().apply(mapping).seal(),
        data = data,
        geom = GeomOptions(GeomKind.CONTOUR),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal(): Options {
        return super<PathAesthetics>.seal() +
                super<ContourStatAesthetics>.seal() +
                super<ContourStatParameters>.seal()
    }
}