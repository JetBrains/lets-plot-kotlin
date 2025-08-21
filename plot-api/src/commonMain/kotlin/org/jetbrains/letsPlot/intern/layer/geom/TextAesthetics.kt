/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface TextAesthetics : OptionsCapsule {
    /** X-axis value. */
    val x: Any?
    /** Y-axis value. */
    val y: Any?
    /** Text to add to plot. */
    val label: Any?
    /** Transparency level of a layer. Understands numbers between 0 and 1. */
    val alpha: Any?
    /** Color of the geometry. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill). */
    val color: Any?
    /** Font size. */
    val size: Any?
    /** For more info see: [aesthetics.html#font-family](https://lets-plot.org/kotlin/aesthetics.html#font-family). */
    val family: Any?
    /** For more info see: [aesthetics.html#font-face](https://lets-plot.org/kotlin/aesthetics.html#font-face). */
    val fontface: Any?
    /** Horizontal text alignment relative to the x-coordinate. Possible values: 0 or "left" - left-aligned (text starts at x), 0.5 or "middle" (default) - text is centered on x, 1 or "right" - right-aligned (text ends at x). There are two special alignments: "inward" (aligns text towards the plot center) and "outward" (away from the plot center). */
    val hjust: Any?
    /** Vertical text alignment relative to the y-coordinate. Possible values: 0 or "bottom" - bottom-aligned (bottom of text at y), 0.5 or "center" (default) - middle of text at y, 1 or "top" - top-aligned (top of text at y). There are two special alignments: "inward" (aligns text towards the plot center) and "outward" (away from the plot center). */
    val vjust: Any?
    /** Text rotation angle in degrees. */
    val angle: Any?
    /** Line height multiplier applied to the font size in the case of multi-line text. */
    val lineheight: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "label" to label,
        "alpha" to alpha,
        "color" to color,
        "size" to size,
        "family" to family,
        "fontface" to fontface,
        "hjust" to hjust,
        "vjust" to vjust,
        "angle" to angle,
        "lineheight" to lineheight
    )
}