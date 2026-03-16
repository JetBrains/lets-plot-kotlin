package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom.bracket
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.BracketAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.BracketMapping
import org.jetbrains.letsPlot.intern.layer.geom.BracketParameters
import org.jetbrains.letsPlot.intern.layer.geom.TextParameters
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions

/**
 * Annotates a plot with labeled brackets to highlight relationships or groupings between categories or ranges.
 *
 * ## Examples
 *
 * - [geom_bracket.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_bracket.ipynb)
 *
 * @param data The data to be displayed. If null, the default, the data is inherited
 *  from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat default = [Stat.identity][org.jetbrains.letsPlot.Stat.identity]. The statistical transformation to use on the data for this layer.
 *  Supported transformations: [Stat.identity][org.jetbrains.letsPlot.Stat.identity], [Stat.count()][org.jetbrains.letsPlot.Stat.count], [Stat.bin()][org.jetbrains.letsPlot.Stat.bin],
 *  [Stat.smooth()][org.jetbrains.letsPlot.Stat.smooth], [Stat.density()][org.jetbrains.letsPlot.Stat.density], and [Stat.sum()][org.jetbrains.letsPlot.Stat.sum].
 * @param position default = [positionIdentity][org.jetbrains.letsPlot.pos.positionIdentity].
 *  Position adjustment.
 *  Use, for example, [positionDodge()][org.jetbrains.letsPlot.pos.positionDodge], [positionJitter()][org.jetbrains.letsPlot.pos.positionJitter],
 *  [positionNudge()][org.jetbrains.letsPlot.pos.positionNudge], [positionJitterDodge()][org.jetbrains.letsPlot.pos.positionJitterDodge],
 *  [positionFill()][org.jetbrains.letsPlot.pos.positionFill], [positionStack()][org.jetbrains.letsPlot.pos.positionStack], or
 *  [positionIdentity][org.jetbrains.letsPlot.pos.positionIdentity].
 * @param showLegend default = false.
 *  `true` to show the legend for this layer.
 * @param inheritAes default = true.
 *  `false` to not combine the layer aesthetic mappings with the plot shared mappings.
 * @param manualKey String or result of the call to the [layerKey()][org.jetbrains.letsPlot.scale.layerKey] function.
 *  The key to show in the manual legend.
 *  Specifies text for the legend label or advanced settings using the [layerKey()][org.jetbrains.letsPlot.scale.layerKey] function.
 * @param tooltips Result of the call to the [layerTooltips()][org.jetbrains.letsPlot.tooltips.layerTooltips] function.
 *  Specifies appearance, style, and content.
 *  Set `tooltips = tooltipsNone` to hide tooltips from the layer.
 * @param orientation default = "x" ("x", "y").
 *  Specifies the axis that the geom should run along.
 * @param labelFormat Specifies the format pattern for displaying mapped values.
 * @param naText default = "n/a".
 *  Text to show for missing values.
 * @param nudgeX Horizontal adjustment to nudge the geometry by.
 * @param nudgeY Vertical adjustment to nudge the geometry by.
 * @param nudgeUnit default = "identity" ("identity", "size", "px").
 *  Units for x and y nudging.
 *  Possible values:
 *
 *  - "identity": a unit of 1 corresponds to a difference of 1 in data space;
 *  - "size": a unit of 1 corresponds to the diameter of a point with `size = 1`;
 *  - "px": the unit is measured in screen pixels.
 *
 * @param bracketShorten default = 0.
 *  Symmetrically shorten the bracket by shifting both ends toward the center.
 *  Expects values between 0 and 1, where 0 corresponds to no shortening and 1 to a fully collapsed bracket.
 * @param tipLengthUnit default = "size" ("res", "identity", "size", "px").
 *  Unit for the `lenStart` and `lenEnd` aesthetics.
 *  Possible values:
 *
 *  - "res": the unit equals the smallest distance between data points along the corresponding axis;
 *  - "identity": a unit of 1 corresponds to a difference of 1 in data space;
 *  - "size": a unit of 1 corresponds to the diameter of a point with `size = 1`;
 *  - "px": the unit is measured in screen pixels.
 *
 * @param sizeUnit ("x", "y", "min", "max").
 *  Relate the size of the text to the length of the unit step along one of the axes.
 *  "x" uses the unit step along the x-axis, "y" uses the unit step along the y-axis.
 *  "min" uses the smaller of the unit steps along the x- and y-axes.
 *  "max" uses the larger of the unit steps along the x- and y-axes.
 *  If null, no fitting is performed.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
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
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class geomBracket(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = positionIdentity,
    showLegend: Boolean = false,
    inheritAes: Boolean? = null,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    orientation: String? = null,
    override val xmin: Any? = null,
    override val xmax: Any? = null,
    override val y: Any? = null,
    override val ymin: Any? = null,
    override val ymax: Any? = null,
    override val x: Any? = null,
    override val lenStart: Any? = null,
    override val lenEnd: Any? = null,
    override val label: Any? = null,
    override val size: Any? = null,
    override val linetype: Any? = null,
    override val color: Any? = null,
    override val alpha: Any? = null,
    override val family: Any? = null,
    override val fontface: Any? = null,
    override val hjust: Any? = null,
    override val vjust: Any? = null,
    override val angle: Any? = null,
    override val lineheight: Any? = null,
    override val segmentColor: Any? = null,
    override val segmentSize: Any? = null,
    override val segmentAlpha: Any? = null,
    override val labelFormat: String? = null,
    override val naText: String? = null,
    override val nudgeX: Number? = null,
    override val nudgeY: Number? = null,
    override val nudgeUnit: String? = null,
    override val checkOverlap: Boolean? = null,
    override val bracketShorten: Number? = null,
    override val tipLengthUnit: String? = null,
    override val sizeUnit: String? = null,
    override val colorBy: String? = null,
    mapping: BracketMapping.() -> Unit = {}
) : BracketAesthetics,
    BracketParameters,
    TextParameters,
    WithSizeUnitOption,
    WithColorOption,
    Layer(
        mapping = BracketMapping().apply(mapping).seal(),
        data = data,
        geom = bracket(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        inheritAes = inheritAes,
        manualKey = manualKey,
        sampling = sampling,
        tooltips = tooltips,
        orientation = orientation
    ) {
    override fun seal(): Options {
        return super<BracketAesthetics>.seal() +
                super<BracketParameters>.seal() +
                super<TextParameters>.seal() +
                super<WithSizeUnitOption>.seal() +
                super<WithColorOption>.seal()
    }
}