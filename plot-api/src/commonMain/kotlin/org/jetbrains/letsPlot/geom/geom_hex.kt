package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.BinHexMapping
import org.jetbrains.letsPlot.intern.layer.geom.HexAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.BinHexStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.BinHexStatParameters
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Divides the plane into a hexagonal grid and color the bins by the count of cases in them.
 *
 * ## Notes
 *
 * Computed variables:
 *
 * - ..count.. : number of points with coordinates in the same hexagonal bin.
 *
 * To hide axis tooltips, set "blank" or the result of `elementBlank()`
 * to the `axisTooltip`, `axisTooltipX` or `axisTooltipY` parameter of the `theme()`.
 *
 * @param data The data to be displayed in this layer. If null, the default, the data is inherited from the plot
 *  data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat default = `Stat.binhex()`. The statistical transformation to use on the data for this layer.
 *  Supported transformations: `Stat.identity`, `Stat.bin()`, `Stat.count()`, etc. see [Stat][org.jetbrains.letsPlot.Stat].
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
 * @param tooltips Result of the call to the `layerTooltips()` function.
 *  Specifies appearance, style and content.
 *  Set `tooltips = tooltipsNone` to hide tooltips from the layer.
 * @param bins default = Pair(30, 30).
 *  Number of bins in both directions, vertical and horizontal. Overridden by `binWidth`.
 * @param binWidth The width of the bins in both directions, vertical and horizontal. Overrides `bins`.
 *  The default is to use bin widths that cover the entire range of the data.
 * @param drop default = true.
 *  Specifies whether to remove all bins with 0 counts.
 * @param x X-axis value.
 * @param y Y-axis value.
 * @param width Width of a hexagon.
 * @param height The real height of the hexagon will be 2/sqrt(3) times this value, so with width=height the hexagon will be the regular.
 * @param alpha Transparency level of a layer.
 *  Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param linetype Type of the line.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param size Line width, default = 0 (i.e. hexagons outline initially is not visible).
 * @param weight Used by `Stat.binhex()`stat to compute weighted sum instead of simple count.
 * @param widthUnit default = "res".
 *  Unit for width of the hexagon.
 *  Possible values:
 *
 *  - "res": if `stat = Stat.binhex()`, the unit equals the hexagonal bin width (binWidth.first); otherwise, it represents the smallest distance between adjacent hexagons along the corresponding axis;
 *  - "identity": a unit of 1 corresponds to a difference of 1 in data space;
 *  - "size": a unit of 1 corresponds to the diameter of a point with size=1;
 *  - "px": the unit is measured in screen pixels.
 *
 * @param heightUnit default = "res".
 *  Unit for height of the hexagon.
 *  Possible values:
 *
 *  - "res": if `stat = Stat.binhex()`, the unit equals the hexagonal bin height (binWidth.second); otherwise, it represents the smallest distance between adjacent hexagons along the corresponding axis;
 *  - "identity": a unit of 1 corresponds to a difference of 1 in data space;
 *  - "size": a unit of 1 corresponds to the diameter of a point with size=1;
 *  - "px": the unit is measured in screen pixels.
 *
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are mapped to plot "aesthetics".
 */
class geomHex(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.binhex(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    inheritAes: Boolean? = null,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val width: Number? = null,
    override val height: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val weight: Any? = null,
    override val bins: Pair<Int, Int>? = null,
    override val binWidth: Pair<Number?, Number?>? = null,
    override val drop: Boolean? = null,
    override val widthUnit: String? = null,
    override val heightUnit: String? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: BinHexMapping.() -> Unit = {}
) : HexAesthetics,
    BinHexStatAesthetics,
    BinHexStatParameters,
    WithWidthUnitOption,
    WithHeightUnitOption,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = BinHexMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.hex(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        inheritAes = inheritAes,
        manualKey = manualKey,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal(): Options {
        return super<HexAesthetics>.seal() +
                super<BinHexStatAesthetics>.seal() +
                super<BinHexStatParameters>.seal() +
                super<WithWidthUnitOption>.seal() +
                super<WithHeightUnitOption>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }
}