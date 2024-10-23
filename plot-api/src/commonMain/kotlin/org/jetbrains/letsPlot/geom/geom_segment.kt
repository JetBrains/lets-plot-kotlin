/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.Geom.segment
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.StatOptions
import org.jetbrains.letsPlot.intern.layer.WithColorOption
import org.jetbrains.letsPlot.intern.layer.geom.SegmentAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.SegmentMapping
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Draws a straight line segment between points (x, y) and (xend, yend).
 *
 * ## Examples
 *
 * - [lines.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/lines.ipynb)
 *
 * - [graph_edges.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/graph_edges.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat The statistical transformation to use on the data for this layer.
 *  Supported transformations: `Stat.identity`, `Stat.bin()`, `Stat.count()`, etc. see [Stat][org.jetbrains.letsPlot.Stat].
 * @param position Position adjustment: `positionIdentity`, `positionStack()`, `positionDodge()`, etc. see 
 *  [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
 * @param showLegend default = true.
 *  false - do not show legend for this layer.
 * @param inheritAes default = true.
 *  false - do not combine the layer aesthetic mappings with the plot shared mappings.
 * @param manualKey String or result of the call to the `layerKey()` function.
 *  The key to show in the manual legend. Specifies the text for the legend label or advanced settings using the `layerKey()` function.
 * @param sampling Result of the call to the `samplingXxx()` function.
 *  To prevent any sampling for this layer pass value `samplingNone`.
 *  For more info see [sampling.html](https://lets-plot.org/kotlin/sampling.html).
 * @param tooltips Result of the call to the `layerTooltips()` function.
 *  Specifies appearance, style and content.
 * @param arrow Specification for arrow head, as created by `arrow()` function.
 * @param spacer default = 0.0.
 *  Space to shorten a segment by moving the start/end.
 * @param flat default = false.
 *  true - keeps a line straight (corresponding to a loxodrome in case of Mercator projection).
 *  false - allows a line to be reprojected, so it can become a curve.
 * @param x X-axis value.
 * @param y Y-axis value.
 * @param xend X-axis value.
 * @param yend Y-axis value.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param linetype Type of the line.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param size Line width.
 * @param sizeStart Offset from the segment start coordinate.
 *  Usually equal to the size of the point object from which the segment starts to avoid overlapping with it.
 * @param sizeEnd Offset from the segment end coordinate.
 *  Usually equal to the size of the point object from which the segment ends to avoid overlapping with it.
 * @param strokeStart Offset from the segment start coordinate.
 *  Usually equal to the stroke of the point object from which the segment starts to avoid overlapping with it.
 * @param strokeEnd Offset from the segment end coordinate.
 *  Usually equal to the stroke of the point object from which the segment ends to avoid overlapping with it.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class geomSegment(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    inheritAes: Boolean? = null,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    private val arrow: Map<String, Any>? = null,
    private val spacer: Number? = null,
    private val flat: Boolean? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val xend: Number? = null,
    override val yend: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val sizeStart: Number? = null,
    override val sizeEnd: Number? = null,
    override val strokeStart: Number? = null,
    override val strokeEnd: Number? = null,
    override val colorBy: String? = null,
    mapping: SegmentMapping.() -> Unit = {}

) : SegmentAesthetics,
    WithColorOption,
    Layer(
        mapping = SegmentMapping().apply(mapping).seal(),
        data = data,
        geom = segment(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        inheritAes = inheritAes,
        manualKey = manualKey,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal(): Options {
        return super<SegmentAesthetics>.seal() +
                super<WithColorOption>.seal() +
                Options.of(
                    Option.Geom.Segment.ARROW to arrow,
                    Option.Geom.Segment.SPACER to spacer,
                    Option.Geom.Segment.FLAT to flat
                )
    }
}