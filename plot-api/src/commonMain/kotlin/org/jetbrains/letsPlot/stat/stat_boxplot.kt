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
    width: Any? = null,
    weight: Any? = null,
    outlierAlpha: Number? = null,
    outlierColor: Any? = null,
    outlierFill: Any? = null,
    outlierShape: Any? = null,
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
        lower, middle, upper, ymin, ymax, alpha, color, fill, size, linetype, shape, width, weight, fatten,
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
            alpha = outlierAlpha ?: alpha,
            color = outlierColor ?: color,
            fill = outlierFill ?: fill,
            shape = outlierShape ?: shape,
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
