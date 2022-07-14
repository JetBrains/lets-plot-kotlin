/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.geom

import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.Geom.segment
import jetbrains.letsPlot.Pos.identity
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.SegmentAesthetics
import jetbrains.letsPlot.intern.layer.geom.SegmentMapping
import jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Draw a straight line segment between points (x, y) and (xend, yend).
 *
 * ## Examples
 *
 * - [lines.ipynb](https://nbviewer.jupyter.org/github/HIL-HK/lets-plot-examples/blob/master/kotlin/lines.ipynb)
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
 * @param tooltips result of the call to the layerTooltips() function.
 *     Specifies appearance, style and content.
 * @param arrow Specification for arrow head, as created by `arrow()` function.
 *
 * @param x x-axis value.
 * @param y y-axis value.
 * @param xend x-axis value.
 * @param yend y-axis value.
 * @param alpha transparency level of a point
 *     Understands numbers between 0 and 1.
 * @param color (colour) color of a geometry.
 *     Can be continuous or discrete. For continuous value this will be a color gradient between two colors.
 * @param linetype type of the line.
 *     Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *     5 = "longdash", 6 = "twodash".
 * @param size line width.
 * @param speed animation speed.
 *     The number of pixels covered by animation object per second. Default value is 10.
 * @param flow animation flow.
 *     The number of animation objects passing a reference point per second. Default value is 0.1.
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */
class geomSegment(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.identity,
    position: jetbrains.letsPlot.intern.layer.PosOptions = identity,
    showLegend: Boolean = true,
    sampling: jetbrains.letsPlot.intern.layer.SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    private val arrow: Map<String, Any>? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val xend: Double? = null,
    override val yend: Double? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val speed: Double? = null,
    override val flow: Double? = null,
    mapping: SegmentMapping.() -> Unit = {}

) : SegmentAesthetics,
    jetbrains.letsPlot.intern.layer.LayerBase(
        mapping = SegmentMapping().apply(mapping).seal(),
        data = data,
        geom = segment(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal(): Options {
        return super.seal() + Options.of(Option.Geom.Segment.ARROW to arrow)
    }
}