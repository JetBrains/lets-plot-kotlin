package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

/**
 * Aesthetic mappings supported by [geomBracketDodge()][org.jetbrains.letsPlot.geom.geomBracketDodge].
 *
 * @param x Primary axis category for horizontal brackets.
 * @param y Bracket level - the position along the y-axis at which the bracket is drawn for horizontal brackets.
 * @param istart Index of the dodged group at the bracket start. Accept integer values between 0 and `nGroup - 1`.
 * @param iend Index of the dodged group at the bracket end. Accept integer values between 0 and `nGroup - 1`.
 * @param lenstart Length of the tip at the bracket start (at `istart`).
 * @param lenend Length of the tip at the bracket end (at `iend`).
 * @param label Text to add.
 * @param size Font size.
 * @param linetype Type of the bracket line.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param color Color of the geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param alpha Transparency level of a layer.
 *  Accept values between 0 and 1.
 * @param family Font family.
 *  For more info see: [aesthetics.html#text](https://lets-plot.org/kotlin/aesthetics.html#text).
 * @param fontface Font style and weight.
 *  For more info see: [aesthetics.html#text](https://lets-plot.org/kotlin/aesthetics.html#text).
 * @param hjust Horizontal text alignment relative to the x-coordinate.
 *  Possible values: 0 or "left" - left-aligned (text starts at x), 0.5 or "middle" (default) - text is centered on x,
 *  1 or "right" - right-aligned (text ends at x).
 *  There are two special alignments: "inward" (aligns text towards the plot center) and "outward" (away from the plot center).
 * @param vjust Vertical text alignment relative to the y-coordinate.
 *  Accept either a numeric value or one of: "bottom", "center", or "top".
 *  The numeric values 0, 0.5 (default), and 1 correspond to "bottom", "center", and "top", respectively.
 *  There are two special alignments: "inward" (aligns text towards the plot center) and "outward" (away from the plot center).
 * @param angle Text rotation angle in degrees.
 * @param lineheight Line height multiplier applied to the font size in the case of multi-line text.
 * @param segmentColor Color of the bracket line (the segments forming the bracket).
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param segmentSize Width of the bracket line (the segments forming the bracket).
 * @param segmentAlpha Transparency level of the bracket line.
 *  Accept values between 0 and 1.
 * @param group Grouping key. If not set, grouping may be inferred from other aesthetics (e.g., color, size).
 * @param paint_a Auxiliary paint channel A that can be used as either `color` or `fill` as needed. Map a variable here for composite/multi-channel color with a matching scale.
 * @param paint_b Auxiliary paint channel B. See `paint_a`.
 * @param paint_c Auxiliary paint channel C. See `paint_a`.
 */
class BracketDodgeMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var istart: Any? = null,
    override var iend: Any? = null,
    override var lenstart: Any? = null,
    override var lenend: Any? = null,
    override var label: Any? = null,
    override var size: Any? = null,
    override var linetype: Any? = null,
    override var color: Any? = null,
    override var alpha: Any? = null,
    override var family: Any? = null,
    override var fontface: Any? = null,
    override var hjust: Any? = null,
    override var vjust: Any? = null,
    override var angle: Any? = null,
    override var lineheight: Any? = null,
    override var segmentColor: Any? = null,
    override var segmentSize: Any? = null,
    override var segmentAlpha: Any? = null,
    override var group: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null
) : BracketDodgeAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<BracketDodgeAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}