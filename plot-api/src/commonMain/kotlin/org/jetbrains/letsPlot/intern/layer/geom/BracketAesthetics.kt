package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.plot.base.Aes
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic properties supported by [geomBracket()][org.jetbrains.letsPlot.geom.geomBracket].
 *
 * @param xmin Left end of the bracket for horizontal brackets.
 * @param xmax Right end of the bracket for horizontal brackets.
 * @param y Y-axis coordinate for horizontal brackets.
 * @param ymin Lower end of the bracket for vertical brackets.
 * @param ymax Upper end of the bracket for vertical brackets.
 * @param x X-axis coordinate for vertical brackets.
 * @param lenStart Length of the tip at the bracket start (at `xmin` for horizontal brackets, or `ymin` for vertical brackets).
 * @param lenEnd Length of the tip at the bracket end (at `xmax` for horizontal brackets, or `ymax` for vertical brackets).
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
 */
interface BracketAesthetics : OptionsCapsule {
    val xmin: Any?
    val xmax: Any?
    val y: Any?
    val ymin: Any?
    val ymax: Any?
    val x: Any?
    val lenStart: Any?
    val lenEnd: Any?
    val label: Any?
    val size: Any?
    val linetype: Any?
    val color: Any?
    val alpha: Any?
    val family: Any?
    val fontface: Any?
    val hjust: Any?
    val vjust: Any?
    val angle: Any?
    val lineheight: Any?
    val segmentColor: Any?
    val segmentSize: Any?
    val segmentAlpha: Any?

    override fun seal() = Options.of(
        Aes.XMIN.name to xmin,
        Aes.XMAX.name to xmax,
        Aes.Y.name to y,
        Aes.YMIN.name to ymin,
        Aes.YMAX.name to ymax,
        Aes.X.name to x,
        Aes.LENSTART.name to lenStart,
        Aes.LENEND.name to lenEnd,
        Aes.LABEL.name to label,
        Aes.SIZE.name to size,
        Aes.LINETYPE.name to linetype,
        Aes.COLOR.name to color,
        Aes.ALPHA.name to alpha,
        Aes.FAMILY.name to family,
        Aes.FONTFACE.name to fontface,
        Aes.HJUST.name to hjust,
        Aes.VJUST.name to vjust,
        Aes.ANGLE.name to angle,
        Aes.LINEHEIGHT.name to lineheight,
        Aes.SEGMENT_COLOR.name to segmentColor,
        Aes.SEGMENT_SIZE.name to segmentSize,
        Aes.SEGMENT_ALPHA.name to segmentAlpha,
    )
}