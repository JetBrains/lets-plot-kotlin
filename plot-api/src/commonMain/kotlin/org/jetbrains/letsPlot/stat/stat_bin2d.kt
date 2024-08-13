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
import org.jetbrains.letsPlot.intern.layer.geom.Bin2dMapping
import org.jetbrains.letsPlot.intern.layer.geom.TileAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.Bin2dStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.Bin2dStatParameters
import org.jetbrains.letsPlot.pos.positionIdentity

@Suppress("ClassName")
/**
 * Displays a 2d distribution by dividing the plane into a grid and counting the number of observations in each rectangle.
 *
 * @param data The data to be displayed in this layer. If null, the default, the data is inherited from the plot
 *  data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param geom The geometry to display the bin2D stat for this layer, default is `Geom.tile()`,
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
 * @param width Width of a bin.
 * @param height Height of a bin.
 * @param alpha Transparency level of a layer.
 *  Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param linetype Type of the line.
 *  Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash".
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param size Lines width.
 * @param weight Used by `Stat.bin2D()`stat to compute weighted sum instead of simple count.
 * @param bins default = Pair(30, 30).
 *  Number of bins in both directions, vertical and horizontal. Overridden by `binWidth`.
 * @param binWidth The width of the bins in both directions, vertical and horizontal. Overrides `bins`.
 *  The default is to use bin widths that cover the entire range of the data.
 * @param drop default = true.
 *  Specifies whether to remove all bins with 0 counts.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are mapped to plot "aesthetics".
 */
class statBin2D(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.tile(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val width: Number? = null,
    override val height: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val weight: Any? = null,
    override val bins: Pair<Int, Int>? = null,
    override val binWidth: Pair<Number?, Number?>? = null,
    override val drop: Boolean? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: Bin2dMapping.() -> Unit = {}
) : TileAesthetics,
    Bin2dStatAesthetics,
    Bin2dStatParameters,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = Bin2dMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.bin2D(),
        position = position,
        showLegend = showLegend,
        sampling = sampling
    ) {
    override fun seal(): Options {
        return super<TileAesthetics>.seal() +
                super<Bin2dStatAesthetics>.seal() +
                super<Bin2dStatParameters>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }
}