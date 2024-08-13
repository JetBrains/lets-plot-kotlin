/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.ContourfMapping
import org.jetbrains.letsPlot.intern.layer.geom.PolygonAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.ContourStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.ContourStatParameters
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions


@Suppress("ClassName")
/**
 * Fills contours of a 3d surface in 2d.
 *
 * ## Examples
 *
 * - [contours.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/contours.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat default = `Stat.contourFilled()`. The statistical transformation to use on the data for this layer.
 *  Supported transformations: `Stat.identity`, `Stat.bin()`, `Stat.count()`, etc. see [Stat][org.jetbrains.letsPlot.Stat].
 * @param position Position adjustment: `positionIdentity`, `positionStack()`, `positionDodge()`, etc.
 *  see [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
 * @param showLegend default = true.
 *  false - do not show legend for this layer.
 * @param manualKey String or result of the call to the `layerKey()` function.
 *  The key to show in the manual legend. Specifies the text for the legend label or advanced settings using the `layerKey()` function.
 * @param sampling Result of the call to the `samplingXxx()` function.
 *  To prevent any sampling for this layer pass value `samplingNone`.
 *  For more info see [sampling.html](https://lets-plot.org/kotlin/sampling.html).
 * @param tooltips Result of the call to the `layerTooltips()` function.
 *  Specifies appearance, style and content.
 * @param bins Number of levels.
 * @param binWidth Distance between levels.
 * @param x X-axis coordinates of the center of rectangles, forming a tessellation.
 * @param y Y-axis coordinates of the center of rectangles, forming a tessellation.
 * @param z Value at point (x, y).
 * @param size Line width.
 * @param linetype Type of the line.
 *  Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *  5 = "longdash", 6 = "twodash".
 * @param alpha Transparency level of a layer.
 *  Understands numbers between 0 and 1.
 * @param fill Color of a geometry areas.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param color Color of geometry lines.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class geomContourFilled(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.contourFilled(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val z: Number? = null,
    override val size: Number? = null,
    override val linetype: Any? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val alpha: Number? = null,
    override val bins: Int? = null,
    override val binWidth: Number? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: ContourfMapping.() -> Unit = {}
) : PolygonAesthetics,
    ContourStatAesthetics,
    ContourStatParameters,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = ContourfMapping().apply(mapping).seal(),
        data = data,
        geom = GeomOptions(GeomKind.CONTOURF),
        stat = stat,
        position = position,
        showLegend = showLegend,
        manualKey = manualKey,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal(): Options {
        return super<PolygonAesthetics>.seal() +
                super<ContourStatAesthetics>.seal() +
                super<ContourStatParameters>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }
}