/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.stat

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.GeomOptions
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.WithFillOption
import org.jetbrains.letsPlot.intern.layer.geom.PieAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.PieMapping
import org.jetbrains.letsPlot.intern.layer.stat.Count2dStatAesthetics
import org.jetbrains.letsPlot.pos.positionIdentity


@Suppress("ClassName")
/**
 * Displays a distribution of counts by dividing the plane into a grid and counting the number of observations in each category of each rectangle.
 *
 * ## Notes
 *
 * Computed variables:
 *
 * - ..count.. : number of points with same (x,y) coordinate.
 * - ..sum.. : total number of points with same (x,y) coordinate.
 * - ..prop.. : groupwise proportion.
 * - ..proppct.. : groupwise proportion in percent.
 * - ..sumprop.. : proportion of points with same (x,y) coordinate among all points in the dataset.
 * - ..sumpct.. : proportion of points with same (x,y) coordinate among all points in the dataset in percent.
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param geom The geometry to display the 2D count stat for this layer, default is `Geom.pie()`,
 *  see [Geom][org.jetbrains.letsPlot.Geom].
 * @param position Position adjustment: `positionIdentity`, `positionStack()`, `positionDodge()`, etc. see
 *  [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
 * @param showLegend default = true.
 *  false - do not show legend for this layer.
 * @param sampling Result of the call to the `samplingXxx()` function.
 *  To prevent any sampling for this layer pass value `samplingNone`.
 *  For more info see [sampling.html](https://lets-plot.org/kotlin/sampling.html).
 * @param x X-axis value.
 * @param y Y-axis value.
 * @param slice For pie geometry it is a values associated to pie sectors.
 * @param explode For pie geometry it is a values to explode slices away from their center point, detaching it from the main pie.
 * @param size Geometry size.
 * @param fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param alpha Transparency level of a layer.
 *  Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param stroke Geometry border width.
 * @param weight Used by the stat to compute weighted sum instead of simple count.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class statCount2D(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.pie(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val slice: Number? = null,
    override val explode: Number? = null,
    override val size: Number? = null,
    override val fill: Any? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val stroke: Number? = null,
    override val weight: Number? = null,
    override val fillBy: String? = null,
    mapping: PieMapping.() -> Unit = {}
) : PieAesthetics,
    Count2dStatAesthetics,
    WithFillOption,
    Layer(
        mapping = PieMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.count2d(),
        position = position,
        showLegend = showLegend,
        manualKey = manualKey,
        sampling = sampling
    ) {

    override fun seal(): Options {
        return super<PieAesthetics>.seal() +
                super<Count2dStatAesthetics>.seal() +
                super<WithFillOption>.seal()
    }
}

@Deprecated(
    "Please, use a new name for this function.",
    ReplaceWith("statCount2D"),
    level = DeprecationLevel.WARNING
)
typealias statCount2d = statCount2D