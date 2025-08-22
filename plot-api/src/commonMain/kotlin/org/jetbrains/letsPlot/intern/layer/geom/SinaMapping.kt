package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

/**
 * Aesthetic mappings supported by [geomSina()][org.jetbrains.letsPlot.geom.geomSina].
 *
 * @param x X-axis coordinates.
 * @param y Y-axis coordinates.
 * @param violinWidth Density scaled for the sina plot, according to area, counts or to a constant maximum width.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 * @param fill Fill color.
 * @param shape Shape of the sina points.
 * @param size Lines width.
 * @param stroke Width of the shape border. Applied only to the shapes having border.
 * @param width Width of sina bounding box.
 * @param group Grouping key. If not set, grouping may be inferred from other aesthetics (e.g., color, size).
 * @param paint_a Auxiliary paint channel A that can be used as either `color` or `fill` as needed. Map a variable here for composite/multi-channel color with a matching scale.
 * @param paint_b Auxiliary paint channel B. See `paint_a`.
 * @param paint_c Auxiliary paint channel C. See `paint_a`.
 */
class SinaMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var violinWidth: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var shape: Any? = null,
    override var size: Any? = null,
    override var stroke: Any? = null,
    override var width: Any? = null,
    override var group: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null
) : SinaAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<SinaAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}