package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom.textRepel
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.RepelParameters
import org.jetbrains.letsPlot.intern.layer.geom.TextParameters
import org.jetbrains.letsPlot.intern.layer.geom.TextRepelAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.TextRepelMapping
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.spatial.SpatialDataset
import org.jetbrains.letsPlot.tooltips.TooltipOptions

/**
 * Add text labels that repel away from each other and from data points.
 *
 *  ## Examples
 *
 * - [ggrepel.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.11.0/ggrepel.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat The statistical transformation to use on the data for this layer.
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
 * @param map Data-structure containing series of planar shapes and, optionally,
 *  associated data series (for example: names of States and their boundaries).
 *
 *  Supported shapes: Point and MultiPoint.
 *  All coordinates should be encoded as decimal degrees in WGS84 coordinate reference system.
 *
 *  Can be used with parameter `mapJoin` for joining data and map coordinates.
 * @param mapJoin Pair of Names or Pair of Lists of Names.
 *  Specifies column names to join the `data` and the `map` coordinates on.
 *  - Pair.first: column name or list of column names in the `data` dataframe.
 *  - Pair.second: column name or list of column names in the `map` dataframe.
 * @param useCRS By default, all coordinates are converted into degrees of longitude and latitude,
 *  and these map coordinates are projected onto the screen coordinates using Mercator projection.
 *  Specify useCRS = "provided" to keep the SpatialDataset's original coordinate reference system (CRS).
 * @param x X-axis value.
 * @param y Y-axis value.
 * @param label Text to add to plot.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param size Font size.
 * @param family default = "sans".
 *  For more info see: [aesthetics.html#font-family](https://lets-plot.org/kotlin/aesthetics.html#font-family).
 * @param fontface default = "plain".
 *  For more info see: [aesthetics.html#font-face](https://lets-plot.org/kotlin/aesthetics.html#font-face).
 * @param hjust ("left", "middle", "right") or number between 0 ("left") and 1 ("right")
 *  or "inward" (aligns text towards the plot center), "outward" (away from the plot center).
 *  Horizontal text alignment.
 * @param vjust ("bottom", "center", "top") or number between 0 ("bottom") and 1 ("top")
 *  or "inward" (aligns text towards the plot center), "outward" (away from the plot center).
 *  Vertical text alignment.
 * @param angle Text rotation angle in degrees.
 * @param lineheight Line height multiplier applied to the font size in the case of multi-line text.
 * @param labelFormat Specifies the format pattern for displaying mapped values.
 * @param naText default = "n/a".
 *  Text to show for missing values.
 * @param nudgeX Horizontal adjustment to nudge labels by.
 * @param nudgeY Vertical adjustment to nudge labels by.
 * @param sizeUnit Relates the size of the text to the length of the unit step along one of the axes.
 *  Possible values: "x", "y". If not specified, no fitting is performed.
 * @param nudgeUnit ("identity", "size", "px") Units for x and y nudging.
 * Possible values:
 *  - "identity": a unit of 1 corresponds to a difference of 1 in data space;
 *  - "size": a unit of 1 corresponds to the diameter of a point with `size=1`;
 *  - "px": the unit is measured in screen pixels.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param checkOverlap Skips plotting text that overlaps previous text in the same layer.
 * @param seed Random seed for reproducibility.
 * @param maxIter Maximum number of iterations for the repulsion algorithm.
 * @param maxTime Maximum allowed time in seconds for resolving label collisions.
 * @param direction default = "both" ("both", "x", "y").
 *  Defines the direction in which labels can be moved.
 * @param pointPadding Padding around the data point.
 * @param boxPadding Padding around the label box.
 * @param maxOverlaps Max number of overlapping labels to allow.
 * @param minSegmentLength Minimum length of the connecting line segment. Shorter ones are omitted.
 * @param arrow Specification for arrow head, as created by `arrow()` function.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 *
 *
 * Format patterns in the `labelFormat` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * For more info see: [formats.html](https://lets-plot.org/kotlin/formats.html)
 * Note: the "$" must be escaped as "\$".
 *
 * Examples:
 * - ".2f" -> "12.45"
 * - "Score: {.2f}" -> "Score: 12.45"
 * - "Score: {}" -> "Score: 12.454789"
 *
 */
@Suppress("ClassName")
class geomTextRepel(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    inheritAes: Boolean? = null,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val map: SpatialDataset? = null,
    override val mapJoin: Pair<Any, Any>? = null,
    override val useCRS: String? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val label: String? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val size: Number? = null,
    override val family: String? = null,
    override val fontface: String? = null,
    override val hjust: Any? = null,
    override val vjust: Any? = null,
    override val angle: Number? = null,
    override val shape: Any? = null,
    override val pointSize: Any? = null,
    override val pointStroke: Any? = null,
    override val segmentColor: Any? = null,
    override val segmentSize: Any? = null,
    override val segmentAlpha: Any? = null,
    override val linetype: Any? = null,
    override val lineheight: Number? = null,
    override val labelFormat: String? = null,
    override val naText: String? = null,
    override val nudgeX: Number? = null,
    override val nudgeY: Number? = null,
    override val sizeUnit: String? = null,
    override val nudgeUnit: String? = null,
    override val colorBy: String? = null,
    override val checkOverlap: Boolean? = null,
    override val seed: Int? = null,
    override val maxIter: Int? = null,
    override val maxTime: Double? = null,
    override val direction: String? = null,
    override val pointPadding: Number? = null,
    override val boxPadding: Number? = null,
    override val maxOverlaps: Int? = null,
    override val minSegmentLength: Number? = null,
    override val arrow: Map<String, Any>? = null,
    mapping: TextRepelMapping.() -> Unit = {},
) : TextRepelAesthetics,
    TextParameters,
    RepelParameters,
    WithSizeUnitOption,
    WithSpatialParameters,
    WithColorOption,
    Layer(
        mapping = TextRepelMapping().apply(mapping).seal(),
        data = data,
        geom = textRepel(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        inheritAes = inheritAes,
        manualKey = manualKey,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal(): Options {
        return super<TextRepelAesthetics>.seal() +
                super<TextParameters>.seal() +
                super<RepelParameters>.seal() +
                super<WithSizeUnitOption>.seal() +
                super<WithColorOption>.seal()
    }
}