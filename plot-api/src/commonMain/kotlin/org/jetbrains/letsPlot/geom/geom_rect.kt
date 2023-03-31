/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.RectAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.RectMapping
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.spatial.SpatialDataset
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Displays a rectangle defined by the four corners (xmin, xmax, ymin and ymax).
 *
 * ## Examples
 *
 * - [geotools_naturalearth.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geotools_naturalearth.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat The statistical transformation to use on the data for this layer.
 *  Supported transformations: `Stat.identity`, `Stat.bin()`, `Stat.count()`, etc. see [Stat][org.jetbrains.letsPlot.Stat].
 * @param position Position adjustment: `positionIdentity`, `positionStack()`, `positionDodge()`, etc. see 
 *  [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
 * @param showLegend default = true.
 *  false - do not show legend for this layer.
 * @param sampling Result of the call to the `samplingXxx()` function.
 *  To prevent any sampling for this layer pass value `samplingNone` .
 *  For more info see [sampling.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/sampling.md).
 * @param tooltips Result of the call to the `layerTooltips()` function.
 *  Specifies appearance, style and content.
 * @param map Data-structure containing series of planar shapes and, optionally, associates data series (for example:
 *  names of States and their boundaries).
 *
 *  `geomRect` will draw rectangles using bounding boxes of shapes.
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
 * @param xmin X-axis value.
 * @param xmax X-axis value.
 * @param ymin Y-axis value.
 * @param ymax Y-axis value.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  String in the following formats: 
 *  - RGB/RGBS (e.g. "rgb(0, 0, 255)")
 *  - HEX (e.g. "#0000FF")
 *  - color name (e.g. "red") 
 *
 *  Or an instance of the `java.awt.Color` class.
 * @param size Lines width.
 * @param linetype Type of the line of tile's border.
 *  Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *  5 = "longdash", 6 = "twodash".
 * @param fill Color of geometry filling.
 *  String in the following formats: 
 *  - RGB/RGBS (e.g. "rgb(0, 0, 255)")
 *  - HEX (e.g. "#0000FF")
 *  - color name (e.g. "red") 
 *
 *  Or an instance of the `java.awt.Color` class.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class geomRect(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val map: SpatialDataset? = null,
    override val mapJoin: Pair<Any, Any>? = null,
    override val useCRS: String? = null,
    override val xmin: Number? = null,
    override val xmax: Number? = null,
    override val ymin: Number? = null,
    override val ymax: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val fill: Any? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: RectMapping.() -> Unit = {}

) : RectAesthetics,
    WithSpatialParameters,
    WithColorOption,
    WithFillOption,
    LayerBase(
        mapping = RectMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.rect(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal() = super<RectAesthetics>.seal() +
            super<WithColorOption>.seal() +
            super<WithFillOption>.seal()
}

