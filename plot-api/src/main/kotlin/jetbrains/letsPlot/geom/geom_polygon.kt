/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom
import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.SamplingOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.PolygonAesthetics
import jetbrains.letsPlot.intern.layer.geom.PolygonMapping

@Suppress("ClassName")
/**
 * Display a polygon (filled path).
 *      geom_polygon draws polygons, which are filled paths.
 *      Each vertex of the polygon requires a separate row in the data.
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
 * @param map dictionary, pandas DataFrame or GeoDataFrame (supported shapes Polygon and MultiPolygon)
 *     Data (Dictionary, DataFrame or GeoDataFrame object) contains coordinates of polygon vertices on map.
 *     Can be used with aesthetic parameter 'map_id' for joining data and map coordinates.
 *     Dictionary and DataFrame object must contain keys/columns:
 *       1. 'x' or 'lon' or 'long'
 *       2. 'y' or 'lat'
 * @param map_join str, pair, optional
 *     Pair of names used to join map coordinates with data.
 *     str or first value in pair - column in data
 *     second value in pair - column in map
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
class geom_polygon(
    data: Any? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = Pos.identity,
    show_legend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val size: Double? = null,
    override val linetype: Any? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val alpha: Double? = null,
    // TODO add map/map_join parameters support
    mapping: PolygonMapping.() -> Unit = {}
) : PolygonAesthetics,
    LayerBase(
        mapping = PolygonMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.polygon(),
        stat = stat,
        position = position,
        show_legend = show_legend,
        sampling = sampling
    )