/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

/**
 * Set of aesthetic mappings for point.
 * Aesthetic mappings describe the way that variables in the data are mapped to plot "aesthetics".
 */
class PointMapping(
    /** X-axis value. */
    override var x: Any? = null,
    /** Y-axis value. */
    override var y: Any? = null,
    /** Transparency level of a layer. Understands numbers between 0 and 1. */
    override var alpha: Any? = null,
    /** Color of the geometry. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill). */
    override var color: Any? = null,
    /** Color to paint shape's inner points. Is applied only to the points of shapes having inner points. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill). */
    override var fill: Any? = null,
    /** Shape of the point. For more info see: [aesthetics.html#point-shapes](https://lets-plot.org/kotlin/aesthetics.html#point-shapes). */
    override var shape: Any? = null,
    /** Size of the point. */
    override var size: Any? = null,
    /** Width of the shape border. Applied only to the shapes having border. */
    override var stroke: Any? = null,
    /** Rotation angle of the shape, in degrees. */
    override var angle: Any? = null,
    /** Aesthetic group. */
    override var group: Any? = null,
    /** Additional "color" aesthetic. Provide support for additional "color" or "fill" scales as needed. */
    override var paint_a: Any? = null,
    /** Additional "color" aesthetic. Provide support for additional "color" or "fill" scales as needed. */
    override var paint_b: Any? = null,
    /** Additional "color" aesthetic. Provide support for additional "color" or "fill" scales as needed. */
    override var paint_c: Any? = null
) : PointAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<PointAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}


