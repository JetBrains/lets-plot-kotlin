/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.stat

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.GeomOptions
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.WithColorOption
import org.jetbrains.letsPlot.intern.layer.WithFillOption
import org.jetbrains.letsPlot.intern.layer.geom.BarAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.BarMapping
import org.jetbrains.letsPlot.intern.layer.stat.CountStatAesthetics
import org.jetbrains.letsPlot.pos.positionStack

@Suppress("ClassName")
/**
 * Displays the counts of items in each observed category.
 *
 * ## Notes
 *
 * Computed variables:
 *
 * - ..count.. : number of points with same x-axis coordinate.
 * - ..sum.. : total number of points with same x-axis coordinate.
 * - ..prop.. : groupwise proportion.
 * - ..proppct.. : groupwise proportion in percent.
 * - ..sumprop.. : proportion of points with same x-axis coordinate among all points in the dataset.
 * - ..sumpct.. : proportion of points with same x-axis coordinate among all points in the dataset in percent.
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param geom The geometry to display the count stat for this layer, default is `Geom.bar()`,
 *  see [Geom][org.jetbrains.letsPlot.Geom].
 * @param position default = `positionStack()`. Position adjustment: `positionIdentity`,
 *  `positionStack()`, `positionDodge()`, etc. see [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
 * @param showLegend default = true.
 *  false - do not show legend for this layer.
 * @param sampling Result of the call to the `samplingXxx()` function.
 *  To prevent any sampling for this layer pass value `samplingNone`.
 *  For more info see [sampling.html](https://lets-plot.org/kotlin/sampling.html).
 * @param x X-axis value (this value will produce cases or bins for bars).
 * @param y Y-axis value (this value will be used to multiply the case's or bin's counts).
 * @param alpha Transparency level of a layer.
 *  Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param width Geometry width.
 * @param size Lines width.
 * @param weight Used by the stat to compute weighted sum instead of simple count.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class statCount(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.bar(),
    position: PosOptions = positionStack(),
    showLegend: Boolean = true,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val width: Number? = null,
    override val size: Number? = null,
    override val weight: Number? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: BarMapping.() -> Unit = {}

) : CountStatAesthetics, BarAesthetics,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = BarMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.count(),
        position = position,
        showLegend = showLegend,
        manualKey = manualKey,
        sampling = sampling
    ) {

    override fun seal(): Options {
        return super<BarAesthetics>.seal() +
                super<CountStatAesthetics>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }
}

