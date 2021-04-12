/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.GeomKind
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.SamplingOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.GeomOptions
import jetbrains.letsPlot.tooltips.TooltipOptions
import jetbrains.letsPlot.intern.layer.geom.ContourfMapping
import jetbrains.letsPlot.intern.layer.geom.PolygonAesthetics
import jetbrains.letsPlot.intern.layer.stat.ContourStatAesthetics
import jetbrains.letsPlot.intern.layer.stat.ContourStatParameters


@Suppress("ClassName")
/**
 * Fill contours of a 3d surface in 2d.
 * @param data dictionary or pandas DataFrame, optional.
 *     The data to be displayed in this layer. If None, the default, the data
 *     is inherited from the plot data as specified in the call to [letsPlot][jetbrains.letsPlot.letsPlot].
 * @param stat string, optional.
 *     The statistical transformation to use on the data for this layer, as a string. Supported transformations:
 *     "identity" (leaves the data unchanged), "count" (counts number of points with same x-axis coordinate),
 *     "bin" (counts number of points with x-axis coordinate in the same bin), "smooth" (performs smoothing -
 *     linear default)
 * @param position string, optional.
 *     Position adjustment, either as a string ("identity", "stack", "dodge", ...), or the result of a call to a
 *     position adjustment function.
 * @param tooltips result of the call to the layerTooltips() function.
 *     Specifies appearance, style and content.
 * @param bins int, optional.
 *     Number of levels.
 * @param binWidth double, optional.
 *     Distance between levels.
 * @param x x-axis coordinates of the center of rectangles, forming a tessellation.
 * @param y y-axis coordinates of the center of rectangles, forming a tessellation.
 * @param alpha transparency level of a layer.
 *     Understands numbers between 0 and 1.
 * @param fill color of a geometry areas.
 *     Can be continuous or discrete. For continuous value this will be a color gradient between two colors.
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */
class geomContourFilled(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.contourf(),
    position: PosOptions = Pos.identity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val z: Double? = null,
    override val size: Number? = null,
    override val linetype: Any? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val alpha: Number? = null,
    override val bins: Int? = null,
    override val binWidth: Number? = null,
    mapping: ContourfMapping.() -> Unit = {}
) : PolygonAesthetics,
    ContourStatAesthetics,
    ContourStatParameters,
    LayerBase(
        mapping = ContourfMapping().apply(mapping).seal(),
        data = data,
        geom = GeomOptions(GeomKind.CONTOURF),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal(): Options {
        return super<PolygonAesthetics>.seal() +
                super<ContourStatAesthetics>.seal() +
                super<ContourStatParameters>.seal()
    }
}