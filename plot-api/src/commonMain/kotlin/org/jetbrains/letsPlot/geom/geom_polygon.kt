/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Pos
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.PolygonAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.PolygonMapping
import org.jetbrains.letsPlot.spatial.SpatialDataset
import org.jetbrains.letsPlot.tooltips.TooltipOptions
import org.jetbrains.letsPlot.intern.layer.*

@Suppress("ClassName")
/**
 * Display a filled closed path defined by the vertex coordinates of individual polygons.
 *
 * ## Examples
 *
 * - [contours.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/contours.ipynb)
 *
 * - [density_2d.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/density_2d.ipynb)
 *
 * - [geotools_naturalearth.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geotools_naturalearth.ipynb)
 *
 * @param data
 *     The data to be displayed in this layer. If None, the default, the data
 *     is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat string.
 *     The statistical transformation to use on the data for this layer. Supported transformations:
 *     "identity" (leaves the data unchanged), "count" (counts number of points with same x-axis coordinate),
 *     "bin" (counts number of points with x-axis coordinate in the same bin), "smooth" (performs smoothing -
 *     linear default).
 *     Statistic types: [letsPlot][org.jetbrains.letsPlot.Stat].
 * @param position string.
 *     Position adjustment: Pos.identity, Pos.stack,  etc. - see [letsPlot][org.jetbrains.letsPlot.Pos].
 *
 * @param tooltips result of the call to the layerTooltips() function.
 *     Specifies appearance, style and content.
 * @param map SpatialDataset.
 *     Data-structure containing series of planar shapes and, optionally, associates data series (for example:
 *     names of States and their boundaries).
 *
 *     Supported shapes: Polygon and MultiPolygon.
 *     All coordinates should be encoded as decimal degrees in WGS84 coordinate reference system.
 *
 *     Can be used with parameter 'mapJoin' for joining data and map coordinates.
 * @param mapJoin pair of names or pair of lists of names
 *     Specifies column names to join the 'data' and the 'map' coordinates on.
 *     Pair.first: column name or list of column names in the 'data' dataframe.
 *     Pair.second: column name or list of column names in the 'map' dataframe.
 * @param x x-axis coordinates of the vertices of the polygon.
 * @param y y-axis coordinates of the vertices of the polygon.
 * @param alpha transparency level of a layer.
 *     Understands numbers between 0 and 1.
 * @param color (colour) color of a geometry lines.
 *     Can be continuous or discrete. For continuous value this will be a color gradient between two colors.
 * @param size lines width.
 *     Defines line width.
 * @param linetype type of the line of tile's border.
 *     Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *     5 = "longdash", 6 = "twodash".
 * @param fill color of geometry filling.
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */
class geomPolygon(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = Pos.identity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val map: SpatialDataset? = null,
    override val mapJoin: Pair<Any, Any>? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val size: Number? = null,
    override val linetype: Any? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val alpha: Number? = null,
    mapping: PolygonMapping.() -> Unit = {}
) : PolygonAesthetics,
    WithSpatialParameters,
    LayerBase(
        mapping = PolygonMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.polygon(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    )