/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption
import org.jetbrains.letsPlot.intern.layer.stat.QQStatAesthetics

/**
 * Aesthetic mappings supported by [geomQQ()][org.jetbrains.letsPlot.geom.geomQQ].
 *
 * @param sample Y-axis value.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of the geometry. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Color to paint shape's inner points. Is applied only to the points of shapes having inner points. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param shape Shape of the point. For more info see: [aesthetics.html#point-shapes](https://lets-plot.org/kotlin/aesthetics.html#point-shapes).
 * @param size Size of the point.
 * @param stroke Width of the shape border. Applied only to the shapes having border.
 * @param group Grouping key. If not set, grouping may be inferred from other aesthetics (e.g., color, size).
 * @param paint_a Auxiliary paint channel A that can be used as either `color` or `fill` as needed. Map a variable here for composite/multi-channel color with a matching scale.
 * @param paint_b Auxiliary paint channel B. See `paint_a`.
 * @param paint_c Auxiliary paint channel C. See `paint_a`.
 */
class QQMapping(
    override var sample: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var shape: Any? = null,
    override var size: Any? = null,
    override var stroke: Any? = null,
    override var group: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null
) : QQAesthetics, QQStatAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<QQAesthetics>.seal() +
            super<QQStatAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}