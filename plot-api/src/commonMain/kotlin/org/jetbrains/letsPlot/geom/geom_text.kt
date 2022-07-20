/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom.text
import org.jetbrains.letsPlot.Pos.identity
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.TextAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.TextMapping
import org.jetbrains.letsPlot.intern.layer.geom.TextParameters
import org.jetbrains.letsPlot.spatial.SpatialDataset
import org.jetbrains.letsPlot.tooltips.TooltipOptions
import org.jetbrains.letsPlot.intern.layer.*

@Suppress("ClassName")
/**
 * Add a text directly to the plot.
 *
 * ## Examples
 *
 * - [label_format.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/label_format.ipynb)
 *
 * - [formatting_axes_etc.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/formatting_axes_etc.ipynb)
 *
 * - [geotools_naturalearth.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geotools_naturalearth.ipynb)
 *
 * @param data
 *     The data to be displayed in this layer. If None, the default, the data
 *     is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat
 *     The statistical transformation to use on the data for this layer. Supported transformations:
 *     "identity" (leaves the data unchanged), "count" (counts number of points with same x-axis coordinate),
 *     "bin" (counts number of points with x-axis coordinate in the same bin), "smooth" (performs smoothing -
 *     linear default).
 *     Statistic types: [letsPlot][org.jetbrains.letsPlot.Stat].
 * @param position
 *     Position adjustment: Pos.identity, Pos.stack,  etc. - see [letsPlot][org.jetbrains.letsPlot.Pos].
 *
 * @param tooltips result of the call to the layerTooltips() function.
 *     Specifies appearance, style and content.
 * @param map SpatialDataset.
 *     Data-structure containing series of planar shapes and, optionally, associates data series (for example:
 *     names of States and their boundaries).
 *
 *     Supported shapes: Point and MultiPoint.
 *     All coordinates should be encoded as decimal degrees in WGS84 coordinate reference system.
 *
 *     Can be used with parameter 'mapJoin' for joining data and map coordinates.
 * @param mapJoin pair of names or pair of lists of names
 *     Specifies column names to join the 'data' and the 'map' coordinates on.
 *     Pair.first: column name or list of column names in the 'data' dataframe.
 *     Pair.second: column name or list of column names in the 'map' dataframe.
 * @param x x-axis value.
 * @param y y-axis value.
 * @param label text to add to plot.
 * @param alpha transparency level of a point
 *     Understands numbers between 0 and 1.
 * @param color (colour) color of a geometry.
 *     Can be continuous or discrete. For continuous value this will be a color gradient between two colors.
 * @param size font size.
 * @param family ('sans' | 'serif' | 'mono' | any other like: "Times New Roman")
 *     Font family. The default is 'sans'
 * @param fontface ('plain' | 'bold' | 'italic' | 'bold italic')
 *     Font style and weight. The default is 'plain'
 * @param hjust ('left', 'middle', 'right') or number between 0 ('right') and 1 ('left').
 *     Horizontal text alignment.
 * @param vjust ('bottom', 'center', 'top') or number between 0 ('bottom') and 1 ('top').
 *     Vertical text alignment.
 * @param angle text rotation angle in degrees.
 * @param labelFormat string.
 *     Specifies the format pattern for displaying mapped values.
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 *
 *
 * Format patterns in the `labelFormat` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * For more info see: https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md
 * Note: the "$" must be escaped as "\$"
 *
 * Examples:
 * ".2f" -> "12.45"
 * "Score: {.2f}" -> "Score: 12.45"
 * "Score: {}" -> "Score: 12.454789"
 *
 */
class geomText(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = identity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val map: SpatialDataset? = null,
    override val mapJoin: Pair<Any, Any>? = null,
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
    override val labelFormat: String? = null,
    override val naText: String? = null,
    override val sizeUnit: String? = null,
    mapping: TextMapping.() -> Unit = {}

) : TextAesthetics,
    TextParameters,
    WithSizeUnitOption,
    WithSpatialParameters,
    LayerBase(
        mapping = TextMapping().apply(mapping).seal(),
        data = data,
        geom = text(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal(): Options {
        return super<TextAesthetics>.seal() +
                super<TextParameters>.seal() + super<WithSizeUnitOption>.seal()
    }
}