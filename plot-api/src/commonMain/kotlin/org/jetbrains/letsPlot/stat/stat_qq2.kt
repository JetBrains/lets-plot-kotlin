/*
 * Copyright (c) 2022. JetBrains s.r.o.
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
import org.jetbrains.letsPlot.intern.layer.geom.PointAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.PointMapping
import org.jetbrains.letsPlot.intern.layer.stat.QQ2StatAesthetics
import org.jetbrains.letsPlot.pos.positionIdentity

@Suppress("ClassName")
/**
 * Displays quantile-quantile plot.
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param geom The geometry to display the Q-Q 2 stat for this layer, default is `Geom.point()`,
 *  see [Geom][org.jetbrains.letsPlot.Geom].
 * @param position Position adjustment: `positionIdentity`, `positionStack()`, `positionDodge()`, etc. see
 *  [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/)
 * @param showLegend default = true.
 *  false - do not show legend for this layer.
 * @param sampling Result of the call to the `samplingXxx()` function.
 *  To prevent any sampling for this layer pass value `samplingNone`.
 *  For more info see [sampling.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/sampling.md).
 * @param x X-axis value.
 * @param y Y-axis value.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  String in the following formats:
 *  - RGB/RGBA (e.g. "rgb(0, 0, 255)")
 *  - HEX (e.g. "#0000FF")
 *  - color name (e.g. "red")
 *  - role name ("pen", "paper" or "brush")
 *
 *  Or an instance of the `java.awt.Color` class.
 * @param fill Fill color.
 *  String in the following formats:
 *  - RGB/RGBA (e.g. "rgb(0, 0, 255)")
 *  - HEX (e.g. "#0000FF")
 *  - color name (e.g. "red")
 *  - role name ("pen", "paper" or "brush")
 *
 *  Or an instance of the `java.awt.Color` class.
 * @param shape Shape of the point.
 * @param size Size of the point.
 * @param stroke Width of the shape border. Applied only to the shapes having border.
 * @param angle Rotation angle of the shape, in degrees.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class statQQ2(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.point(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val shape: Any? = null,
    override val size: Number? = null,
    override val stroke: Number? = null,
    override val angle: Number? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: PointMapping.() -> Unit = {}
) : PointAesthetics,
    QQ2StatAesthetics,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = PointMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.qq2(),
        position = position,
        showLegend = showLegend,
        sampling = sampling
    ) {

    override fun seal(): Options {
        return super<PointAesthetics>.seal() +
                super<QQ2StatAesthetics>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }
}