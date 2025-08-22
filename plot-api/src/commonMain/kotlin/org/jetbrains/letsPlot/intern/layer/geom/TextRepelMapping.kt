/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

/**
 * Aesthetic mappings supported by [geomTextRepel()][org.jetbrains.letsPlot.geom.geomTextRepel].
 *
 * @param x X-axis value.
 * @param y Y-axis value.
 * @param label Text label to display.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 * @param size Font size.
 * @param family Font family.
 * @param fontface Font face.
 * @param hjust horizontal text alignment relative to the x-coordinate.
 * @param vjust vertical text alignment relative to the y-coordinate.
 * @param angle Text rotation angle in degrees.
 * @param lineheight Line height multiplier applied to the font size in the case of multi-line text.
 * @param shape Shape of the point.
 * @param pointSize A value representing the visual size of the point associated with the label.
 * @param pointStroke Width of the point border.
 * @param segmentColor Color of the line segment connecting the label to the point.
 * @param segmentSize Width of the line segment connecting the label to the point.
 * @param segmentAlpha Transparency level of the line segment. Understands numbers between 0 and 1.
 * @param linetype Type of the line.
 * @param group Grouping key. If not set, grouping may be inferred from other aesthetics (e.g., color, size).
 * @param paint_a Auxiliary paint channel A that can be used as either `color` or `fill` as needed. Map a variable here for composite/multi-channel color with a matching scale.
 * @param paint_b Auxiliary paint channel B. See `paint_a`.
 * @param paint_c Auxiliary paint channel C. See `paint_a`.
 */
class TextRepelMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var label: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var size: Any? = null,
    override var family: Any? = null,
    override var fontface: Any? = null,
    override var hjust: Any? = null,
    override var vjust: Any? = null,
    override var angle: Any? = null,
    override var lineheight: Any? = null,
    override var shape: Any? = null,
    override var pointSize: Any? = null,
    override var pointStroke: Any? = null,
    override var segmentColor: Any? = null,
    override var segmentSize: Any? = null,
    override var segmentAlpha: Any? = null,
    override var linetype: Any? = null,
    override var group: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null
) : TextRepelAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<TextRepelAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}


