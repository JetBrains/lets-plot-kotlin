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
 * Displays the distribution of data based on a five number summary ("minimum", first quartile (Q1), 
 * median, third quartile (Q3), and "maximum"), and "outlying" points individually.
 *
 * ## Examples
 *
 * - [distributions.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/distributions.ipynb)
 *
 * @param data The data to be displayed. If null, the default, the data is inherited
 *  from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat default = `Stat.boxplot()`. The statistical transformation to use on the data for this layer.
 *  Supported transformations: `Stat.identity`, `Stat.bin()`, `Stat.count()`, etc. see [Stat][org.jetbrains.letsPlot.Stat].
 * @param position default = `positionDodge()`. Position adjustment: `positionIdentity`, 
 *  `positionStack()`, `positionDodge()`, etc. see [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
 * @param showLegend default = true.
 *  If false - do not show legend for this layer.
 * @param sampling Result of the call to the `samplingXxx()` function.
 *  To prevent any sampling for this layer pass value `samplingNone`.
 *  For more info see [sampling.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/sampling.md).
 * @param tooltips Result of the call to the `layerTooltips()` function.
 *  Specifies appearance, style and content.
 * @param orientation Specifies the axis that the layer's stat and geom should run along, default = "x".
 *  Possible values: "x", "y".
 * @param outlierColor Color aesthetic for outliers.
 * @param outlierFill Fill aesthetic for outliers.
 * @param outlierShape Shape aesthetic for outliers.
 * @param outlierSize Size aesthetic for outliers.
 * @param varWidth default = false. If false make a standard box plot.
 *  If true, boxes are drawn with widths proportional to the square-roots of the number of
 *  observations in the groups.
 * @param fatten default = 1.0.
 *  A multiplicative factor applied to size of the middle bar.
 * @param whiskerWidth default = 0.0.
 *  A multiplicative factor applied to the box width to draw horizontal segments on whiskers.
 * @param coef default = 1.5.
 *  Length of the whiskers as multiple of IQR.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param lower Lower hinge, 25% quantile.
 * @param middle Median, 50% quantile.
 * @param upper Upper hinge, 75% quantile.
 * @param ymin Lower whisker - the smallest observation greater than or equal to the lower hinge - 1.5 * IQR
 * @param ymax Upper whisker - the largest observation less than or equal to the upper hinge + 1.5 * IQR
 * @param width Width of boxplot. Typically ranges between 0 and 1. Values that are greater than 1 lead to overlapping of the boxes.
 * @param alpha Transparency level of a layer.
 *  Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  String in the following formats: 
 *  - RGB/RGBS (e.g. "rgb(0, 0, 255)")
 *  - HEX (e.g. "#0000FF")
 *  - color name (e.g. "red") 
 *
 *  Or an instance of the `java.awt.Color` class.
 * @param fill Color of geometry filling.
 *  String in the following formats: 
 *  - RGB/RGBS (e.g. "rgb(0, 0, 255)")
 *  - HEX (e.g. "#0000FF")
 *  - color name (e.g. "red") 
 *
 *  Or an instance of the `java.awt.Color` class.
 * @param size Lines width.
 * @param linetype Type of the line of border.
 *  Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *  5 = "longdash", 6 = "twodash".
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
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
