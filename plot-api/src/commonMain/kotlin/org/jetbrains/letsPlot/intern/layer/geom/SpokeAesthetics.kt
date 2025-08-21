package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic properties supported by [geomSpoke()][org.jetbrains.letsPlot.geom.geomSpoke].
 *
 * @property x X-axis value.
 * @property y Y-axis value.
 * @property angle Slope's angle in radians.
 * @property radius Segment length.
 * @property alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @property color Color of the line.
 * @property linetype Type of the line.
 * @property size Line width.
 */
interface SpokeAesthetics: OptionsCapsule {

    val x: Any?
    val y: Any?
    val angle: Any?
    val radius: Any?
    val alpha: Any?
    val color: Any?
    val linetype: Any?
    val size: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "angle" to angle,
        "radius" to radius,
        "alpha" to alpha,
        "color" to color,
        "linetype" to linetype,
        "size" to size
    )
}