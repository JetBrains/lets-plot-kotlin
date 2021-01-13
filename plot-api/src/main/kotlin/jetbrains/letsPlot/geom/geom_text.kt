/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom.text
import jetbrains.letsPlot.Pos.identity
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.*
import jetbrains.letsPlot.intern.layer.geom.TextAesthetics
import jetbrains.letsPlot.intern.layer.geom.TextMapping
import jetbrains.letsPlot.intern.layer.geom.TextParameters
import jetbrains.letsPlot.spatial.SpatialDataset
import jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Add a text directly to the plot.
 * @param data dictionary or pandas DataFrame, optional.
 *     The data to be displayed in this layer. If None, the default, the data
 *     is inherited from the plot data as specified in the call to [lets_plot][jetbrains.letsPlot.lets_plot].
 * @param stat string, optional.
 *     The statistical transformation to use on the data for this layer, as a string. Supported transformations:
 *     "identity" (leaves the data unchanged), "count" (counts number of points with same x-axis coordinate),
 *     "bin" (counts number of points with x-axis coordinate in the same bin), "smooth" (performs smoothing -
 *     linear default)
 * @param position string, optional.
 *     Position adjustment, either as a string ("identity", "stack", "dodge", ...), or the result of a call to a
 *     position adjustment function.
 * @param tooltips result of the call to the layer_tooltips() function.
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
 *     Format used to transform label mapping values to a string.
 *     Examples:
 *     ".2f" -> "12.45"
 *     "Num {}" -> "Num 12.456789"
 *     "TTL: \${.2f}" -> "TTL: $12.45"
 *     Note, the "$" must be escaped as "\$"
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */
class geom_text(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = identity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val map: SpatialDataset? = null,
    override val mapJoin: Pair<Any, Any>? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val label: String? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val size: Number? = null,
    override val family: String? = null,
    override val fontface: String? = null,
    override val hjust: Any? = null,
    override val vjust: Any? = null,
    override val angle: Double? = null,
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