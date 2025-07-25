/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.annotations.layerLabels
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
 * ## Notes
 *
 * Computed variables:
 *
 * - ..count.. : number of points with the same (x,y) coordinate.
 * - ..sum.. : total number of points with the same (x,y) coordinate.
 * - ..prop.. : groupwise proportion.
 * - ..proppct.. : groupwise proportion in percent.
 * - ..sumprop.. : proportion of points with the same (x,y) coordinate among all points in the dataset.
 * - ..sumpct.. : proportion of points with the same (x,y) coordinate among all points in the dataset in percent.
 *
 * To hide axis tooltips, set "blank" or the result of `elementBlank()`
 * to the `axisTooltip`, `axisTooltipX` or `axisTooltipY` parameter of the `theme()`.
 *
 * ## Examples
 *
 * - [geom_pie.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_pie.ipynb)
 *
 * - [annotations_for_pie.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/annotations_for_pie.ipynb)
 *
 * - [stat_count_2d.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/stat_count_2d.ipynb)
 *
 * - [geom_pie_stroke_and_spacers.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_pie_stroke_and_spacers.ipynb)
 *
 * - [geom_pie_size_unit.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_pie_size_unit.ipynb)
 *
 * - [geom_pie_params.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_pie_params.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat default = `Stat.count2d()`. The statistical transformation to use on the data for this layer.
 *  Supported transformations: `Stat.identity`, `Stat.bin()`, `Stat.count()`, etc. see [Stat][org.jetbrains.letsPlot.Stat].
 * @param position Position adjustment: `positionIdentity`, `positionStack()`, `positionDodge()`, etc. see
 *  [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
 * @param showLegend default = true.
 *  false - do not show legend for this layer.
 * @param inheritAes default = true.
 *  false - do not combine the layer aesthetic mappings with the plot shared mappings.
 * @param manualKey String or result of the call to the `layerKey()` function.
 *  The key to show in the manual legend. Specifies the text for the legend label or advanced settings using the `layerKey()` function.
 * @param sampling Result of the call to the `samplingXxx()` function.
 *  To prevent any sampling for this layer pass value `samplingNone`.
 *  For more info see [sampling.html](https://lets-plot.org/kotlin/sampling.html).
 * @param tooltips Result of the call to the `layerTooltips()` function.
 *  Specifies appearance, style and content.
 *  Set `tooltips = tooltipsNone` to hide tooltips from the layer.
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
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of inner and outer arcs of pie sector.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param stroke Width of inner and outer arcs of pie sector.
 * @param weight Used by `Stat.count2d()` stat to compute weighted sum instead of simple count.
 * @param hole default = 0.0.
 *  A multiplicative factor applied to the pie diameter to draw donut-like chart.
 *  Understands numbers between 0 and 1.
 * @param strokeSide default = "both" ("outer", "inner", "both").
 *  Defines which arcs of pie sector should have a stroke.
 * @param spacerWidth default = 0.75.
 *  Line width between sectors.
 *  Spacers are not applied to exploded sectors and to sides of adjacent sectors.
 * @param spacerColor Color for spacers between sectors. By default, the "paper" color is used.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param start Specify the angle at which the first sector starts. Accept values between 0 and 360.
 *  Default is a negative angle of the first sector.
 * @param direction default = 1.
 *  Specify angle direction, 1=clockwise, -1=counter-clockwise.
 * @param sizeUnit ("x", "y", "min", "max").
 *  Relate the size of the pie chart to the length of the unit step along one of the axes.
 *  "x" uses the unit step along the x-axis, "y" uses the unit step along the y-axis.
 *  "min" uses the smaller of the unit steps along the x- and y-axes.
 *  "max" uses the larger of the unit steps along the x- and y-axes.
 *  If not specified, no fitting is performed.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class geomPie(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.count2d(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    inheritAes: Boolean? = null,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    labels: layerLabels? = null,
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
    override val color: Any? = null,
    override val stroke: Number? = null,
    override val weight: Number? = null,
    override val hole: Number? = null,
    override val strokeSide: String? = null,
    override val spacerWidth: Number? = null,
    override val spacerColor: Any? = null,
    override val start: Number? = null,
    override val direction: Int? = null,
    override val sizeUnit: String? = null,
    override val fillBy: String? = null,
    override val colorBy: String? = null,
    mapping: PieMapping.() -> Unit = {}
) : PieAesthetics,
    PieParameters,
    Count2dStatAesthetics,
    WithSizeUnitOption,
    WithSpatialParameters,
    WithFillOption,
    WithColorOption,
    Layer(
        mapping = PieMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.pie(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        inheritAes = inheritAes,
        manualKey = manualKey,
        sampling = sampling,
        tooltips = tooltips,
        labels = labels
    ) {
    override fun seal(): Options {
        return super<PieAesthetics>.seal() +
                super<PieParameters>.seal() +
                super<Count2dStatAesthetics>.seal() +
                super<WithSizeUnitOption>.seal() +
                super<WithFillOption>.seal() +
                super<WithColorOption>.seal()
    }
}