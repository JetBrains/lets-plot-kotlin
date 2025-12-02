/*
 * Copyright (c) 2025. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.PointAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.PointDensityMapping
import org.jetbrains.letsPlot.intern.layer.stat.PointDensityStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.PointDensityStatParameters
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.spatial.SpatialDataset
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Plots data points and colors each point by the local density of nearby points.
 *
 * ## Notes
 *
 * Computed variables:
 *
 * - ..density.. : density estimate (mapped by default).
 * - ..count.. : density * number of points (corresponds to number of nearby points for `"neighbours"` method).
 * - ..scaled.. : density estimate, scaled to maximum of 1.

 * To hide axis tooltips, set "blank" or the result of [elementBlank()][org.jetbrains.letsPlot.themes.elementBlank]
 * to the `axisTooltip`, `axisTooltipX` or `axisTooltipY` parameter of the [theme()][org.jetbrains.letsPlot.themes.theme].
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat default = `Stat.pointDensity()`. The statistical transformation to use on the data for this layer.
 *  Supported transformations: [Stat.identity][org.jetbrains.letsPlot.Stat.identity], [Stat.bin()][org.jetbrains.letsPlot.Stat.bin], [Stat.count()][org.jetbrains.letsPlot.Stat.count], etc. see [Stat][org.jetbrains.letsPlot.Stat].
 * @param position Position adjustment: `positionIdentity`, `positionStack()`, `positionDodge()`, etc.
 *  see [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
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
 * @param x X-axis coordinates.
 * @param alpha Transparency level of a layer.
 *  Understands numbers between 0 and 1.
 * @param color Color of geometry lines.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param size Line width.
 * @param fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param weight Used by "density" stat to compute weighted density.
 * @param method default = "auto" ("auto", "neighbours", "kde2d").
 *  Method to compute the density estimate:
 *  - `"neighbours"` - estimates density from the number of nearby points.
 *  - `"kde2d"` - estimates density using a smoothed 2D kernel density.
 *  - `"auto"` - automatically selects an estimation method based on data size.
 * @param kernel The kernel used for KDE. One of `"gaussian"`, `"cosine"`, `"optcosine"`, `"rectangular"`/`"uniform"`,
 *  `"triangular"`, `"biweight"`/`"quartic"`, `"epanechikov"`/`"parabolic"`. Only used when `method = "kde2d"`.
 * @param bw The method (or exact value) of bandwidth. Either a String (choose among "nrd0" and "nrd"),
 *  or a Double array of length 2.
 *  Only used when `method = "kde2d"`.
 * @param adjust For `"neighbours"`, adjusts the radius in which to count neighbours. 
    For `"kde2d"`, adjust the value of bandwidth by multiplying it.
 * @param n Number of sampled points for plotting the function (on x and y, respectively). Only used when `method = "kde2d"`.
 * @param map Data-structure containing series of planar shapes and, optionally, associated data series (for example:
 *  names of States and their boundaries).
 *
 *  Supported shapes: Polygon and MultiPolygon.
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
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class geomPointDensity(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.pointDensity(),          // default stat for this geom
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    inheritAes: Boolean? = null,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val method: String? = null,
    override val kernel: String? = null,
    override val adjust: Number? = null,
    override val bw: Any? = null,
    override val n: Int? = null,
    override val map: SpatialDataset? = null,
    override val mapJoin: Pair<Any, Any>? = null,
    override val useCRS: String? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val shape: Any? = null,
    override val angle: Number? = null,
    override val size: Number? = null,
    override val stroke: Number? = null,
    override val weight: Any? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: PointDensityMapping.() -> Unit = {}
) : PointAesthetics,
    PointDensityStatAesthetics,
    PointDensityStatParameters,
    WithSpatialParameters,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = PointDensityMapping().apply(mapping).seal(),
        data = data,
        geom = GeomOptions(GeomKind.POINT_DENSITY),
        stat = stat,
        position = position,
        showLegend = showLegend,
        inheritAes = inheritAes,
        manualKey = manualKey,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal() =
        super<PointAesthetics>.seal() +
            super<PointDensityStatAesthetics>.seal() +
            super<PointDensityStatParameters>.seal() +
            super<WithColorOption>.seal() +
            super<WithFillOption>.seal()
}

