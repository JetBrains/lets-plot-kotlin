package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

/**
 * Aesthetic mappings supported by [geomSpoke()][org.jetbrains.letsPlot.geom.geomSpoke].
 *
 * @param x X-axis value.
 * @param y Y-axis value.
 * @param angle Slope's angle in radians.
 * @param radius Segment length.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of the line.
 * @param linetype Type of the line.
 * @param size Line width.
 * @param group Grouping key. If not set, grouping may be inferred from other aesthetics (e.g., color, size).
 * @param paint_a Auxiliary paint channel A that can be used as either `color` or `fill` as needed. Map a variable here for composite/multi-channel color with a matching scale.
 * @param paint_b Auxiliary paint channel B. See `paint_a`.
 * @param paint_c Auxiliary paint channel C. See `paint_a`.
 */
class SpokeMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var angle: Any? = null,
    override var radius: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var linetype: Any? = null,
    override var size: Any? = null,
    override var group: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null
) : SpokeAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<SpokeAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}
