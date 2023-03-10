/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom.boxplot
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.LayerBase
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.StatOptions
import org.jetbrains.letsPlot.intern.layer.WithColorOption
import org.jetbrains.letsPlot.intern.layer.WithFillOption
import org.jetbrains.letsPlot.intern.layer.geom.BoxplotAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.BoxplotMapping
import org.jetbrains.letsPlot.intern.layer.geom.BoxplotParameters
import org.jetbrains.letsPlot.intern.layer.stat.BoxplotStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.BoxplotStatParameters
import org.jetbrains.letsPlot.pos.positionDodge
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Display the distribution of data based on a five number summary ("minimum", first quartile (Q1), median, third quartile (Q3), and "maximum"),
 * and "outlying" points individually.
 *
 * ## Examples
 *
 * - [distributions.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/distributions.ipynb)
 *
 * @param data dictionary or pandas DataFrame, optional
 *     The data to be displayed in this layer. If None, the default, the data
 *     is inherited from the plot data as specified in the call to ggplot.
 * @param stat string, optional
 *     The statistical transformation to use on the data for this layer. Supported transformations:
 *     "identity" (leaves the data unchanged) should be used to define boxplot from your own computations via lower,
 *     upper, ymin, ymax, middle aesthetics mappings (see below), "count" (counts number of points with same x-axis
 *     coordinate), "bin" (counts number of points with x-axis coordinate in the same bin), "smooth" (performs
 *     smoothing - linear default)
 * @param position string, optional
 *     Position adjustment, either as a string ("identity", "stack", "dodge",...), or the result of a call to a
 *     position adjustment function.
 * @param tooltips result of the call to the layerTooltips() function.
 *     Specifies appearance, style and content.
 * @param orientation Specifies the axis that the layer' stat and geom should run along.
 *     Possible values: 'x' (default), 'y'.
 * @param outlierColor
 * @param outlierFill
 * @param outlierShape
 * @param outlierSize
 *     Default aesthetics for outliers.
 * @param varWidth
 *     if FALSE (default) make a standard box plot.
 *     If TRUE, boxes are drawn with widths proportional to the square-roots of the number of
 *     observations in the groups.
 * @param fatten : number, default: 1.0
 *     A multiplicative factor applied to size of the middle bar.
 * @param whiskerWidth: number, default: 0.0
 *     A multiplicative factor applied to the box width to draw horizontal segments on whiskers.
 * @param coef Number, default = 1.5.
 *  Length of the whiskers as multiple of IQR.
 * @param colorBy String, {"fill", "color", "paint_a", "paint_b", "paint_c"}, default = "color".
 *  Defines the color aesthetic for the geometry.
 * @param fillBy String, {"fill", "color", "paint_a", "paint_b", "paint_c"}, default = "fill".
 *  Defines the fill aesthetic for the geometry.
 * @param lower lower hinge, 25% quantile
 * @param middle median, 50% quantile
 * @param upper upper hinge, 75% quantile
 * @param ymin lower whisker = smallest observation greater than or equal to lower hinge - 1.5 * IQR
 * @param ymax upper whisker = largest observation less than or equal to upper hinge + 1.5 * IQR
 * @param width width of boxplot. Typically ranges between 0 and 1. Values that are greater than 1 lead to overlapping of the boxes.
 * @param alpha transparency level of a layer
 *     Understands numbers between 0 and 1.
 * @param color color of a geometry lines
 * @param fill color of geometry filling
 * @param size lines width
 * @param linetype type of the line of border
 *     Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *     5 = "longdash", 6 = "twodash"
 * @param mapping set of aesthetic mappings created by aes() function.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */
class geomBoxplot(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.boxplot(),
    position: PosOptions = positionDodge(),
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    orientation: String? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val lower: Number? = null,
    override val middle: Number? = null,
    override val upper: Number? = null,
    override val ymin: Number? = null,
    override val ymax: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val size: Number? = null,
    override val linetype: Any? = null,
    override val shape: Any? = null,
    override val width: Any? = null,
    override val weight: Any? = null,
    override val outlierColor: Any? = null,
    override val outlierFill: Any? = null,
    override val outlierShape: Any? = null,
    override val outlierSize: Number? = null,
    override val fatten: Number? = null,
    override val whiskerWidth: Number? = null,
    override val varWidth: Boolean? = null,
    @Suppress("SpellCheckingInspection")
    override val coef: Number? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: BoxplotMapping .() -> Unit = {}

) : BoxplotAesthetics,
    BoxplotParameters,
    BoxplotStatAesthetics,
    BoxplotStatParameters,
    WithColorOption,
    WithFillOption,
    LayerBase(
        mapping = BoxplotMapping().apply(mapping).seal(),
        data = data,
        geom = boxplot(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips,
        orientation = orientation
    ) {
    override fun seal(): Options {
        return super<BoxplotAesthetics>.seal() +
                super<BoxplotParameters>.seal() +
                super<BoxplotStatAesthetics>.seal() +
                super<BoxplotStatParameters>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }
}
