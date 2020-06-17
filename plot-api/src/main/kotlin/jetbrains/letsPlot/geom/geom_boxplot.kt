/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom.boxplot
import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.*
import jetbrains.letsPlot.intern.layer.geom.BoxplotAesthetics
import jetbrains.letsPlot.intern.layer.geom.BoxplotMapping
import jetbrains.letsPlot.intern.layer.geom.BoxplotParameters

@Suppress("ClassName")
/**
 * Add box plot.
 *
 * @param data dictionary or pandas DataFrame, optional
 *     The data to be displayed in this layer. If None, the default, the data
 *     is inherited from the plot data as specified in the call to ggplot.
 * @param stat string, optional
 *     The statistical transformation to use on the data for this layer, as a string. Supported transformations:
 *     "identity" (leaves the data unchanged) should be used to define boxplot from your own computations via lower,
 *     upper, ymin, ymax, middle aesthetics mappings (see below), "count" (counts number of points with same x-axis
 *     coordinate), "bin" (counts number of points with x-axis coordinate in the same bin), "smooth" (performs
 *     smoothing - linear default)
 * @param position string, optional
 *     Position adjustment, either as a string ("identity", "stack", "dodge",...), or the result of a call to a
 *     position adjustment function.
 * @param outlierColor
 * @param outlierFill
 * @param outlierShape
 * @param outlierSize
 *     Default aesthetics for outliers.
 * @param varWidth
 *     if FALSE (default) make a standard box plot.
 *     If TRUE, boxes are drawn with widths proportional to the square-roots of the number of
 *     observations in the groups.
 *  @param fatten : number, default: 1.0
 *     A multiplicative factor applied to size of the middle bar
 * @param lower lower hinge, 25% quantile
 * @param middle median, 50% quantile
 * @param upper upper hinge, 75% quantile
 * @param ymin lower whisker = smallest observation greater than or equal to lower hinge - 1.5 * IQR
 * @param ymax upper whisker = largest observation less than or equal to upper hinge + 1.5 * IQR
 * @param width width of boxplot (0..1)
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
class geom_boxplot(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.boxplot(),
    position: PosOptions = Pos.dodge,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val lower: Double? = null,
    override val middle: Double? = null,
    override val upper: Double? = null,
    override val ymin: Double? = null,
    override val ymax: Double? = null,
    override val alpha: Double? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val size: Double? = null,
    override val linetype: Any? = null,
    override val shape: Any? = null,
    override val width: Any? = null,
    override val weight: Any? = null,
    override val outlierColor: Any? = null,
    override val outlierFill: Any? = null,
    override val outlierShape: Any? = null,
    override val outlierSize: Double? = null,
    override val fatten: Double? = null,
    override val varWidth: Boolean? = null,
    @Suppress("SpellCheckingInspection")
    override val coef: Any? = null,
    override val group: Any? = null,
    mapping: BoxplotMapping .() -> Unit = {}

) : BoxplotAesthetics,
    BoxplotParameters,
    WithGroupOption,
    LayerBase(
        mapping = BoxplotMapping().apply(mapping).seal(),
        data = data,
        geom = boxplot(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling
    ) {
    override fun seal(): Options {
        return super<BoxplotAesthetics>.seal() +
                super<BoxplotParameters>.seal() + group()
    }
}
