/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom.abline
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.layer.LayerBase
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.StatOptions
import org.jetbrains.letsPlot.intern.layer.WithColorOption
import org.jetbrains.letsPlot.intern.layer.geom.ABLineAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.ABLineMapping
import org.jetbrains.letsPlot.pos.positionIdentity

@Suppress("ClassName", "SpellCheckingInspection")
/**
 * Adds a line with specified slope and intercept to the plot.
 *
 * ## Examples
 *
 * - [lines.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/lines.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat default = `Stat.identity`. The statistical transformation to use on the data for this layer.
 *  Supported transformations: `Stat.identity`, `Stat.density()`, `Stat.count()`,  etc. - see [letsPlot][org.jetbrains.letsPlot.Stat].
 * @param position PosOptions, optional, default = `positionIdentity`.
 *  Position adjustment: `positionIdentity`, `positionStack()`, `positionDodge()`, etc.
 * @param slope The line slope.
 * @param intercept The value of y at the point where the line crosses the y-axis.
 * @param alpha Transparency level of a point.
 *  Understands numbers between 0 and 1.
 * @param color (colour) Color of a geometry.
 *  Can be continuous or discrete.
 *  For continuous value this will be a color gradient between two colors.
 * @param linetype Type of the line.
 *  Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *  5 = "longdash", 6 = "twodash".
 * @param size Line width.
 * @param colorBy String, {"fill", "color", "paint_a", "paint_b", "paint_c"}, default = "color".
 *  Defines the color aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class geomABLine(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val slope: Number? = null,
    override val intercept: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val colorBy: String? = null,
    mapping: ABLineMapping.() -> Unit = {}
) : ABLineAesthetics,
    WithColorOption,
    LayerBase(
        mapping = ABLineMapping().apply(mapping).seal(),
        data = data,
        geom = abline(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling
    ){
    override fun seal() = super<ABLineAesthetics>.seal() +
            super<WithColorOption>.seal()
}

