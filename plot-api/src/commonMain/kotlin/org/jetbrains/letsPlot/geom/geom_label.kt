/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.LabelAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.LabelMapping
import org.jetbrains.letsPlot.intern.layer.geom.LabelParameters
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.spatial.SpatialDataset
import org.jetbrains.letsPlot.tooltips.TooltipOptions


@Suppress("ClassName")
/**
 *  Add a text directly to the plot with a rectangle behind the text.
 *
 * ## Examples
 *
 * - [geom_label.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_label.ipynb)
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
 * @param fill background color of the label.
 * @param size font size.
 * @param family ('sans' | 'serif' | 'mono' | any other like: "Times New Roman")
 *     Font family. The default is 'sans'
 * @param fontface ('plain' | 'bold' | 'italic' | 'bold italic')
 *     Font style and weight. The default is 'plain'
 * @param hjust ('left', 'middle', 'right') or number between 0 ('left') and 1 ('right').
 *     Horizontal label alignment.
 * @param vjust ('bottom', 'center', 'top') or number between 0 ('bottom') and 1 ('top').
 *     Vertical label alignment.
 * @param angle label rotation angle in degrees.
 * @param labelFormat string.
 *     Specifies the format pattern for displaying mapped values.
 * @param labelPadding double, optional.
 *     Amount of padding around label. Defaults to 0.25 of font size.
 * @param labelR: double, optional.
 *     Radius of rounded corners. Defaults to 0.15 of label height.
 * @param labelSize: double, optional, default = 1.0
 *     Size of label border.
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
class geomLabel(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = positionIdentity,
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
    override val fill: Any? = null,
    override val size: Number? = null,
    override val family: String? = null,
    override val fontface: String? = null,
    override val hjust: Any? = null,
    override val vjust: Any? = null,
    override val angle: Number? = null,
    override val labelFormat: String? = null,
    override val naText: String? = null,
    override val labelPadding: Number? = null,
    override val labelR: Number? = null,
    override val labelSize: Number? = null,
    override val sizeUnit: String? = null,
    mapping: LabelMapping.() -> Unit = {}

) : LabelAesthetics,
    LabelParameters,
    WithSizeUnitOption,
    WithSpatialParameters,
    LayerBase(
        mapping = LabelMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.label(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal(): Options {
        return super<LabelAesthetics>.seal() +
                super<LabelParameters>.seal() +
                super<WithSizeUnitOption>.seal()
    }
}