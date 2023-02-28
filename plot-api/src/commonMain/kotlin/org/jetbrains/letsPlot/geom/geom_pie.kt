/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.annotations.AnnotationOptions
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.PieAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.PieMapping
import org.jetbrains.letsPlot.intern.layer.geom.PieParameters
import org.jetbrains.letsPlot.intern.layer.stat.Count2dStatAesthetics
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.spatial.SpatialDataset
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Draw pie chart.
 *
 * ## Examples
 *
 * - [geom_pie.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/geom_pie.ipynb)
 *
 * - [annotations_for_pie.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/annotations_for_pie.ipynb)
 *
 * - [stat_count_2d.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/stat_count_2d.ipynb)
 *
 * @param data
 *     The data to be displayed in this layer. If None, the default, the data
 *     is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat
 *     The statistical transformation to use on the data for this layer. Supported transformations:
 *     "identity" (leaves the data unchanged), "count2d" (counts number of points with same x,y coordinate).
 *     Statistic types: [letsPlot][org.jetbrains.letsPlot.Stat].
 * @param position
 *     Position adjustment: Pos.identity, Pos.stack,  etc. - see [letsPlot][org.jetbrains.letsPlot.Pos].
 * @param tooltips result of the call to the layerTooltips() function.
 *     Specifies appearance, style and content.
 * @param labels result of the call to the layerLabels() function.
 *     Specifies style and content of the annotations.
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
 *
 * @param x x-axis value.
 * @param y y-axis value.
 * @param slice values associated to pie sectors.
 * @param explode values to explode slices away from their center point, detaching it from the main pie.
 * @param size pie diameter.
 * @param fill color of geometry filling (by default).
 * @param color (colour) color of geometry filling if `fillBy="color"`.
 *     Can be continuous or discrete. For continuous value this will be a color gradient between two colors.
 * @param alpha transparency level of the pie.
 *     Accepts numbers between 0 and 1.
 * @param hole number, default=0.0.
 *     A multiplicative factor applied to the pie diameter to draw donut-like chart.
 *     Accepts numbers between 0 and 1.
 * @param fillBy string: {'fill', 'color'}, default='fill'
 *     Defines the source aesthetic for geometry filling.
 * @param stroke number, default=0.0.
 *     Width of slice borders.
 * @param strokeColor string, default="white".
 *     Color of slice borders.
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */
class geomPie(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.count2d(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    labels: AnnotationOptions? = null,
    override val map: SpatialDataset? = null,
    override val mapJoin: Pair<Any, Any>? = null,
    override val useCRS: String? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val slice: Number? = null,
    override val explode: Number? = null,
    override val size: Number? = null,
    override val fill: Any? = null,
    override val color: Any? = null,
    override val alpha: Number? = null,
    override val weight: Number? = null,
    override val hole: Number? = null,
    override val stroke: Number? = null,
    override val strokeColor: Any? = null,
    mapping: PieMapping.() -> Unit = {}
) : PieAesthetics,
    PieParameters,
    Count2dStatAesthetics,
    WithSpatialParameters,
    LayerBase(
        mapping = PieMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.pie(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips,
        labels = labels
    ) {
    override fun seal(): Options {
        return super<PieAesthetics>.seal() +
                super<PieParameters>.seal() + super<Count2dStatAesthetics>.seal()
    }
}