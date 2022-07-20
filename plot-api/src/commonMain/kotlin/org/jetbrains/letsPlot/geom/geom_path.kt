/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom.path
import org.jetbrains.letsPlot.Pos.identity
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.PathAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.PathMapping
import org.jetbrains.letsPlot.spatial.SpatialDataset
import org.jetbrains.letsPlot.tooltips.TooltipOptions
import org.jetbrains.letsPlot.intern.layer.*

@Suppress("ClassName")
/**
 * Connect observations in the order in which they appear in the data.
 *
 * ## Examples
 *
 * - [lines.ipynb](https://nbviewer.jupyter.org/github/HIL-HK/lets-plot-examples/blob/master/kotlin/lines.ipynb)
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
 *     Supported shapes: LineString and MultiLineString.
 *     All coordinates should be encoded as decimal degrees in WGS84 coordinate reference system.
 *
 *     Can be used with parameter 'mapJoin' for joining data and map coordinates.
 * @param mapJoin pair of names or pair of lists of names
 *     Specifies column names to join the 'data' and the 'map' coordinates on.
 *     Pair.first: column name or list of column names in the 'data' dataframe.
 *     Pair.second: column name or list of column names in the 'map' dataframe.
 * @param x x-axis value.
 * @param y y-axis value.
 * @param alpha transparency level of a point
 *     Understands numbers between 0 and 1.
 * @param color (colour) color of a geometry.
 *     Can be continuous or discrete. For continuous value this will be a color gradient between two colors.
 * @param linetype type of the line.
 *     Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *     5 = "longdash", 6 = "twodash".
 * @param size line width.
 * @param speed animation speed.
 *     The number of pixels covered by animation object per second. Default value is 10.
 * @param flow animation flow.
 *     The number of animation objects passing a reference point per second. Default value is 0.1.
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */
class geomPath(
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
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val speed: Number? = null,
    override val flow: Number? = null,
    mapping: PathMapping.() -> Unit = {}

) : PathAesthetics,
    WithSpatialParameters,
    LayerBase(
        mapping = PathMapping().apply(mapping).seal(),
        data = data,
        geom = path(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    )


