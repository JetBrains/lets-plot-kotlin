/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

/**
 * Aesthetic mappings supported by [geomPoint()][org.jetbrains.letsPlot.geom.geomPoint].
 *
 * @param x X-axis value.
 * @param y Y-axis value.
 * @param alpha Opacity; a number in [0, 1]. Lower values are more transparent (0 - transparent, 1 - opaque).
 * @param color Point color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Point fill color. Applied only to the points of shapes having inner points.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param shape Point shape.
 *  For more info see: [aesthetics.html#point-shapes](https://lets-plot.org/kotlin/aesthetics.html#point-shapes).
 * @param size Point size.
 * @param stroke Width of the shape border. Applied only to the shapes having border.
 * @param angle Rotation angle of the shape, in degrees.
 * @param group Grouping key. Observations with the same value form one group.
 *  If not set, grouping may be inferred from other aesthetics (e.g., color, shape).
 * @param paint_a Auxiliary paint channel A that can be used as either `color` or `fill` as needed.
 *  Map a variable here for composite/multi-channel color with a matching scale.
 * @param paint_b Auxiliary paint channel B. See `paint_a`.
 * @param paint_c Auxiliary paint channel C. See `paint_a`.
 */
class PointMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var shape: Any? = null,
    override var size: Any? = null,
    override var stroke: Any? = null,
    override var angle: Any? = null,
    override var group: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null
) : PointAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<PointAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}


