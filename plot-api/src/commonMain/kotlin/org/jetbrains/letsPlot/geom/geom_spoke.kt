package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.SpokeAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.SpokeMapping
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Draws a straight line segment with given length and angle from the starting point.
 *
 * ## Examples
 *
 * - [geom_spoke.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_spoke.ipynb)
 *
 * - [geom_spoke_arrow.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_spoke_arrow.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat The statistical transformation to use on the data for this layer.
 *  Supported transformations: [Stat.identity][org.jetbrains.letsPlot.Stat.identity], [Stat.bin()][org.jetbrains.letsPlot.Stat.bin], [Stat.count()][org.jetbrains.letsPlot.Stat.count], etc. see [Stat][org.jetbrains.letsPlot.Stat].
 * @param position Position adjustment: `positionIdentity`, `positionStack()`, `positionDodge()`, etc. see
 *  [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
 * @param showLegend default = true.
 *  false - do not show legend for this layer.
 * @param inheritAes default = true.
 *  false - do not combine the layer aesthetic mappings with the plot shared mappings.
 * @param manualKey String or result of the call to the `layerKey()` function.
 *  The key to show in the manual legend. Specifies the text for the legend label or advanced settings using the `layerKey()` function.
 * @param sampling Result of the call to the `samplingXxx()` function.
 *  To prevent any sampling for this layer pass value `samplingNone`.
 *  For more info see [sampling.html](https://lets-plot.org/kotlin/sampling.html).
 * @param tooltips Result of the call to the [layerTooltips()][org.jetbrains.letsPlot.tooltips.layerTooltips] function.
 *  Specifies appearance, style and content.
 *  Set `tooltips = tooltipsNone` to hide tooltips from the layer.
 * @param arrow Specification for arrow head, as created by `arrow()` function.
 * @param pivot default = "tail" ("tail", "middle", "mid", "tip").
 *  The part of the segment that is anchored to the plane. The segment rotates about this point.
 * @param x X-axis value.
 * @param y Y-axis value.
 * @param angle Slope's angle in radians.
 * @param radius Segment length.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of the line.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param linetype Type of the line.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param size Line width.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class geomSpoke(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    inheritAes: Boolean? = null,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    private val arrow: Map<String, Any>? = null,
    private val pivot: String? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val angle: Number? = null,
    override val radius: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val colorBy: String? = null,
    mapping: SpokeMapping.() -> Unit = {}
) : SpokeAesthetics,
    WithColorOption,
    Layer(
        mapping = SpokeMapping().apply(mapping).seal(),
        data = data,
        geom = GeomOptions(GeomKind.SPOKE),
        stat = stat,
        position = position,
        showLegend = showLegend,
        inheritAes = inheritAes,
        manualKey = manualKey,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal(): Options {
        return super<SpokeAesthetics>.seal() +
                super<WithColorOption>.seal() +
                Options.of(
                    Option.Geom.Spoke.ARROW to arrow,
                    Option.Geom.Spoke.PIVOT to pivot
                )
    }
}