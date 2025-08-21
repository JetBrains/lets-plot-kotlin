/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

/**
 * Set of aesthetic mappings for text.
 * Aesthetic mappings describe the way that variables in the data are mapped to plot "aesthetics".
 */
class TextMapping(
    /** X-axis value. */
    override var x: Any? = null,
    /** Y-axis value. */
    override var y: Any? = null,
    /** Text to add to plot. */
    override var label: Any? = null,
    /** Transparency level of a layer. Understands numbers between 0 and 1. */
    override var alpha: Any? = null,
    /** Color of the geometry. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill). */
    override var color: Any? = null,
    /** Font size. */
    override var size: Any? = null,
    /** For more info see: [aesthetics.html#font-family](https://lets-plot.org/kotlin/aesthetics.html#font-family). */
    override var family: Any? = null,
    /** For more info see: [aesthetics.html#font-face](https://lets-plot.org/kotlin/aesthetics.html#font-face). */
    override var fontface: Any? = null,
    /** Horizontal text alignment relative to the x-coordinate. Possible values: 0 or "left" - left-aligned (text starts at x), 0.5 or "middle" (default) - text is centered on x, 1 or "right" - right-aligned (text ends at x). There are two special alignments: "inward" (aligns text towards the plot center) and "outward" (away from the plot center). */
    override var hjust: Any? = null,
    /** Vertical text alignment relative to the y-coordinate. Possible values: 0 or "bottom" - bottom-aligned (bottom of text at y), 0.5 or "center" (default) - middle of text at y, 1 or "top" - top-aligned (top of text at y). There are two special alignments: "inward" (aligns text towards the plot center) and "outward" (away from the plot center). */
    override var vjust: Any? = null,
    /** Text rotation angle in degrees. */
    override var angle: Any? = null,
    /** Line height multiplier applied to the font size in the case of multi-line text. */
    override var lineheight: Any? = null,
    /** Aesthetic group. */
    override var group: Any? = null,
    /** Additional "color" aesthetic. Provide support for additional "color" or "fill" scales as needed. */
    override var paint_a: Any? = null,
    /** Additional "color" aesthetic. Provide support for additional "color" or "fill" scales as needed. */
    override var paint_b: Any? = null,
    /** Additional "color" aesthetic. Provide support for additional "color" or "fill" scales as needed. */
    override var paint_c: Any? = null
) : TextAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<TextAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}


