/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.annotations.AnnotationOptions
import org.jetbrains.letsPlot.intern.Layer
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
 * Draws pie chart.
 *
 * ## Examples
 *
 * - [geom_pie.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/geom_pie.ipynb)
 *
 * - [annotations_for_pie.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/annotations_for_pie.ipynb)
 *
 * - [stat_count_2d.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/stat_count_2d.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat default = `Stat.count2d()`. The statistical transformation to use on the data for this layer.
 *  Supported transformations: `Stat.identity`, `Stat.bin()`, `Stat.count()`, etc. see [Stat][org.jetbrains.letsPlot.Stat].
 * @param position Position adjustment: `positionIdentity`, `positionStack()`, `positionDodge()`, etc. see
 *  [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
 * @param showLegend default = true. false - do not show legend for this layer.
 * @param sampling Result of the call to the `samplingXxx()` function.
 *  To prevent any sampling for this layer pass value `samplingNone`.
 *  For more info see [sampling.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/sampling.md).
 * @param tooltips Result of the call to the `layerTooltips()` function.
 *  Specifies appearance, style and content.
 * @param labels Result of the call to the `layerLabels()` function.
 *  Specifies style and content of the annotations.
 * @param map Data-structure containing series of planar shapes and, optionally, associates data series (for example:
 *  names of States and their boundaries).
 *
 *  Supported shapes: Point and MultiPoint.
 *
 *  All coordinates should be encoded as decimal degrees in WGS84 coordinate reference system.
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
 * @param slice Values associated to pie sectors.
 * @param explode Values to explode slices away from their center point, detaching it from the main pie.
 * @param size Pie diameter.
 * @param fill Fill color.
 *  String in the following formats:
 *  - RGB/RGBA (e.g. "rgb(0, 0, 255)")
 *  - HEX (e.g. "#0000FF")
 *  - color name (e.g. "red")
 *
 *  Or an instance of the `java.awt.Color` class.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param weight Used by `Stat.count2d()` stat to compute weighted sum instead of simple count.
 * @param hole default = 0.0.
 *  A multiplicative factor applied to the pie diameter to draw donut-like chart.
 *  Understands numbers between 0 and 1.
 * @param stroke default = 0.0.
 *  Width of slice borders.
 * @param strokeColor default = "white". Color of slice borders.
 *  String in the following formats:
 *  - RGB/RGBA (e.g. "rgb(0, 0, 255)")
 *  - HEX (e.g. "#0000FF")
 *  - color name (e.g. "red")
 *
 *  Or an instance of the `java.awt.Color` class.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
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
    override val alpha: Number? = null,
    override val weight: Number? = null,
    override val hole: Number? = null,
    override val stroke: Number? = null,
    override val strokeColor: Any? = null,
    override val fillBy: String? = null,
    mapping: PieMapping.() -> Unit = {}
) : PieAesthetics,
    PieParameters,
    Count2dStatAesthetics,
    WithSpatialParameters,
    WithFillOption,
    Layer(
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
                super<PieParameters>.seal() +
                super<Count2dStatAesthetics>.seal() +
                super<WithFillOption>.seal()
    }
}