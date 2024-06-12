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
import org.jetbrains.letsPlot.intern.layer.geom.QQAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.QQMapping
import org.jetbrains.letsPlot.intern.layer.stat.QQStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.QQStatParameters
import org.jetbrains.letsPlot.pos.positionIdentity

@Suppress("ClassName")
/**
 * Displays quantile-quantile plot.
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param geom The geometry to display the Q-Q stat for this layer, default is `Geom.qq()`,
 *  see [Geom][org.jetbrains.letsPlot.Geom].
 * @param position Position adjustment: `positionIdentity`, `positionStack()`, `positionDodge()`, etc. see
 *  [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
 * @param showLegend default = true.
 *  false - do not show legend for this layer.
 * @param sampling Result of the call to the `samplingXxx()` function.
 *  To prevent any sampling for this layer pass value `samplingNone`.
 *  For more info see [sampling.html](https://lets-plot.org/kotlin/sampling.html).
 * @param sample Y-axis value.
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
 * @param distribution default = "norm".
 *  Distribution function to use: "norm", "uniform", "t", "gamma", "exp", "chi2".
 * @param dParams Additional parameters passed on to distribution function.
 *  - If `distribution` is "norm" then `dParams` is a pair (mean, std) (default = listOf(0.0, 1.0)).
 *  - If `distribution` is "uniform" then `dParams` is a pair (a, b) (default = listOf(0.0, 1.0)).
 *  - If `distribution` is "t" then `dParams` is an integer number (d) (default = listOf(1)).
 *  - If `distribution` is "gamma" then `dParams` is a pair (alpha, beta) (default = listOf(1.0, 1.0)).
 *  - If `distribution` is "exp" then `dParams` is a float number (lambda) (default = listOf(1.0)).
 *  - If `distribution` is "chi2" then `dParams` is an integer number (k) (default = listOf(1)).
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class statQQ(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.qq(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val sample: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val shape: Any? = null,
    override val size: Number? = null,
    override val stroke: Number? = null,
    override val distribution: String? = null,
    override val dParams: List<Number>? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: QQMapping.() -> Unit = {}
) : QQAesthetics,
    QQStatAesthetics,
    QQStatParameters,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = QQMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.qq(),
        position = position,
        showLegend = showLegend,
        sampling = sampling
    ) {

    override fun seal(): Options {
        return super<QQAesthetics>.seal() +
                super<QQStatAesthetics>.seal() +
                super<QQStatParameters>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }
}
