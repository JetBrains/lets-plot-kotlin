/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

/**
 * Set of aesthetic mappings for line.
 * Aesthetic mappings describe the way that variables in the data are mapped to plot "aesthetics".
 */
class LineMapping(
    /** X-axis value. */
    override var x: Any? = null,
    /** Y-axis value. */
    override var y: Any? = null,
    /** Transparency level of a layer. Understands numbers between 0 and 1. */
    override var alpha: Any? = null,
    /** Color of the geometry. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill). */
    override var color: Any? = null,
    /** Type of the line. Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"), a hex string (up to 8 digits for dash-gap lengths), or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`. For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types). */
    override var linetype: Any? = null,
    /** Line width. */
    override var size: Any? = null,
    /** Aesthetic group. */
    override var group: Any? = null,
    /** Additional "color" aesthetic. Provide support for additional "color" or "fill" scales as needed. */
    override var paint_a: Any? = null,
    /** Additional "color" aesthetic. Provide support for additional "color" or "fill" scales as needed. */
    override var paint_b: Any? = null,
    /** Additional "color" aesthetic. Provide support for additional "color" or "fill" scales as needed. */
    override var paint_c: Any? = null
) : LineAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<LineAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}


