/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.stat

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.*
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.*
import org.jetbrains.letsPlot.intern.layer.stat.BoxplotOutlierStatParameters
import org.jetbrains.letsPlot.intern.layer.stat.BoxplotStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.BoxplotStatParameters
import org.jetbrains.letsPlot.pos.positionDodge

/**
 * Displays the distribution of data based on a five number summary ("minimum", first quartile (Q1),
 * median, third quartile (Q3), and "maximum"), and "outlying" points individually.
 *
 * @param data The data to be displayed. If null, the default, the data is inherited
 *  from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param geom The geometry to display the boxplot stat for this layer, default is `Geom.boxplot()`,
 *  see [Geom][org.jetbrains.letsPlot.Geom].
 * @param position default = `positionDodge()`. Position adjustment: `positionIdentity`,
 *  `positionStack()`, `positionDodge()`, etc. see [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
 * @param showLegend default = true.
 *  If false - do not show legend for this layer.
 * @param sampling Result of the call to the `samplingXxx()` function.
 *  To prevent any sampling for this layer pass value `samplingNone`.
 *  For more info see [sampling.html](https://lets-plot.org/kotlin/sampling.html).
 * @param x X-axis value.
 * @param y Y-axis value.
 * @param lower Lower hinge, 25% quantile.
 * @param middle Median, 50% quantile.
 * @param upper Upper hinge, 75% quantile.
 * @param ymin Lower whisker - the smallest observation greater than or equal to the lower hinge - 1.5 * IQR
 * @param ymax Upper whisker - the largest observation less than or equal to the upper hinge + 1.5 * IQR
 * @param alpha Transparency level of a layer.
 *  Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Fill color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param size Lines width and outliers size.
 * @param stroke Width of the outlier shape border. Applied only to the shapes having border.
 * @param linetype Type of the line of border.
 *  Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *  5 = "longdash", 6 = "twodash".
 * @param shape Shape of the outliers.
 * @param width Width of boxplot. Typically ranges between 0 and 1. Values that are greater than 1 lead to overlapping of the boxes.
 * @param outlierAlpha Default transparency aesthetic for outliers.
 * @param outlierColor Color aesthetic for outliers.
 * @param outlierFill Fill aesthetic for outliers.
 * @param outlierShape Shape aesthetic for outliers.
 * @param outlierSize Size aesthetic for outliers.
 * @param outlierStroke Default width of the border for outliers.
 * @param outlierAngle Rotation angle of the shape for outliers, in degrees.
 * @param fatten default = 1.0.
 *  A multiplicative factor applied to size of the middle bar.
 * @param whiskerWidth default = 0.0.
 *  A multiplicative factor applied to the box width to draw horizontal segments on whiskers.
 * @param varWidth default = false. If false make a standard box plot.
 *  If true, boxes are drawn with widths proportional to the square-roots of the number of
 *  observations in the groups.
 * @param coef default = 1.5.
 *  Length of the whiskers as multiple of IQR.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
fun statBoxplot(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.boxplot(),
    position: PosOptions = positionDodge(),
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    x: Number? = null,
    y: Number? = null,
    lower: Number? = null,
    middle: Number? = null,
    upper: Number? = null,
    ymin: Number? = null,
    ymax: Number? = null,
    alpha: Number? = null,
    color: Any? = null,
    fill: Any? = null,
    size: Number? = null,
    stroke: Number? = null,
    linetype: Any? = null,
    shape: Any? = null,
    angle: Number? = null,
    width: Any? = null,
    weight: Any? = null,
    outlierAlpha: Number? = null,
    outlierColor: Any? = null,
    outlierFill: Any? = null,
    outlierShape: Any? = null,
    outlierAngle: Number? = null,
    outlierSize: Number? = null,
    outlierStroke: Number? = null,
    fatten: Number? = null,
    whiskerWidth: Number? = null,
    varWidth: Boolean? = null,
    @Suppress("SpellCheckingInspection")
    coef: Number? = null,
    colorBy: String? = null,
    fillBy: String? = null,
    mapping: BoxplotMapping.() -> Unit = {}
): FeatureList {
    val layers = mutableListOf<Layer>()

    layers += statBoxplotInternal(
        data,
        geom,
        position,
        showLegend,
        sampling,
        x, y,
        lower, middle, upper, ymin, ymax, alpha, color, fill, size, linetype, shape, angle, width, weight, fatten,
        whiskerWidth, varWidth, coef,
        colorBy, fillBy,
        mapping
    )

    if (geom.kind == GeomKind.BOX_PLOT) {
        val outlierFatten = 4.0
        val boxplotMapping = BoxplotMapping().apply(mapping)
        val pointMapping: PointMapping.() -> Unit = {
            this.x = boxplotMapping.x
            this.y = boxplotMapping.y
            this.alpha = boxplotMapping.alpha
            this.color = boxplotMapping.color
            this.fill = boxplotMapping.fill
            this.shape = boxplotMapping.shape
            this.angle = boxplotMapping.angle
            this.size = boxplotMapping.size
            // stroke
            this.group = boxplotMapping.group
            this.paint_a = boxplotMapping.paint_a
            this.paint_b = boxplotMapping.paint_b
            this.paint_c = boxplotMapping.paint_c
        }
        layers += statBoxplotOutlierInternal(
            data = data,
            geom = Geom.point(),
            position = position,
            showLegend = false,
            sampling = null,
            x = x, y = y,
            alpha = outlierAlpha,
            color = outlierColor ?: color,
            fill = outlierFill ?: fill,
            shape = outlierShape ?: shape,
            angle = outlierAngle ?: angle,
            size = (outlierSize ?: size)?.let { it.toDouble() * outlierFatten },
            stroke = outlierStroke ?: stroke,
            colorBy = colorBy, fillBy = fillBy,
            mapping = pointMapping
        )
    }
    return FeatureList(layers)
}


@Suppress("ClassName")
private class statBoxplotInternal(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.boxplot(),
    position: PosOptions = positionDodge(),
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
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
    override val angle: Number? = null,
    override val width: Any? = null,
    override val weight: Any? = null,
    override val fatten: Number? = null,
    override val whiskerWidth: Number? = null,
    override val varWidth: Boolean? = null,
    @Suppress("SpellCheckingInspection")
    override val coef: Number? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: BoxplotMapping.() -> Unit = {}

) : BoxplotAesthetics,
    BoxplotParameters,
    BoxplotStatAesthetics,
    BoxplotStatParameters,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = BoxplotMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.boxplot(),
        position = position,
        showLegend = showLegend,
        sampling = sampling

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

@Suppress("ClassName")
private class statBoxplotOutlierInternal(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.point(),
    position: PosOptions = positionDodge(),
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val shape: Any? = null,
    override val size: Number? = null,
    override val stroke: Number? = null,
    override val angle: Number? = null,
    @Suppress("SpellCheckingInspection")
    override val coef: Number? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: PointMapping.() -> Unit = {},

) : PointAesthetics,
    BoxplotOutlierStatParameters,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = PointMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.boxplotOutlier(),
        position = position,
        showLegend = showLegend,
        sampling = sampling

    ) {

    override fun seal(): Options {
        return super<PointAesthetics>.seal() +
                super<BoxplotOutlierStatParameters>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }
}
