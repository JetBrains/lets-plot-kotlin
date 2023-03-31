/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom.text
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.TextAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.TextMapping
import org.jetbrains.letsPlot.intern.layer.geom.TextParameters
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.pos.positionNudge
import org.jetbrains.letsPlot.spatial.SpatialDataset
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Adds a text directly to the plot.
 *
 * ## Examples
 *
 * - [label_format.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/label_format.ipynb)
 *
 * - [formatting_axes_etc.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/formatting_axes_etc.ipynb)
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
 *  String in the following formats: 
 *  - RGB/RGBS (e.g. "rgb(0, 0, 255)")
 *  - HEX (e.g. "#0000FF")
 *  - color name (e.g. "red") 
 *
 *  Or an instance of the `java.awt.Color` class.
 * @param size Font size.
 * @param family default = "sans" ("sans", "serif", "mono" or any other like "Times New Roman").
 *  Font family.
 * @param fontface default = "plain" ("plain", "bold", "italic", "bold italic").
 *  Font style and weight.
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
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 *
 *
 * Format patterns in the `labelFormat` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * For more info see: [formats.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md)
 * Note: the "$" must be escaped as "\$".
 *
 * Examples:
 * - ".2f" -> "12.45"
 * - "Score: {.2f}" -> "Score: 12.45"
 * - "Score: {}" -> "Score: 12.454789"
 *
 */
class geomText(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
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
    override val lineheight: Number? = null,
    override val labelFormat: String? = null,
    override val naText: String? = null,
    override val nudgeX: Number? = null,
    override val nudgeY: Number? = null,
    override val sizeUnit: String? = null,
    override val colorBy: String? = null,
    mapping: TextMapping.() -> Unit = {}

) : TextAesthetics,
    TextParameters,
    WithSizeUnitOption,
    WithSpatialParameters,
    WithColorOption,
    LayerBase(
        mapping = TextMapping().apply(mapping).seal(),
        data = data,
        geom = text(),
        stat = stat,
        position = when {
            nudgeX != null || nudgeY != null -> positionNudge(nudgeX, nudgeY)
            else -> position
        },
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal(): Options {
        return super<TextAesthetics>.seal() +
                super<TextParameters>.seal() +
                super<WithSizeUnitOption>.seal() +
                super<WithColorOption>.seal()
    }
}