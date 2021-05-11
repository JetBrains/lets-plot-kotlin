/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.stat

import jetbrains.letsPlot.Geom
import jetbrains.letsPlot.Pos.dodge
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.geom.BoxplotAesthetics
import jetbrains.letsPlot.intern.layer.geom.BoxplotMapping
import jetbrains.letsPlot.intern.layer.geom.BoxplotParameters
import jetbrains.letsPlot.intern.layer.stat.BoxplotStatAesthetics
import jetbrains.letsPlot.intern.layer.stat.BoxplotStatParameters

@Suppress("ClassName")
class statBoxplot(
    data: Map<*, *>? = null,
    geom: jetbrains.letsPlot.intern.layer.GeomOptions = Geom.boxplot(),
    position: jetbrains.letsPlot.intern.layer.PosOptions = dodge,
    showLegend: Boolean = true,
    sampling: jetbrains.letsPlot.intern.layer.SamplingOptions? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val lower: Double? = null,
    override val middle: Double? = null,
    override val upper: Double? = null,
    override val ymin: Double? = null,
    override val ymax: Double? = null,
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
    override val varWidth: Boolean? = null,
    @Suppress("SpellCheckingInspection")
    override val coef: Number? = null,
    mapping: BoxplotMapping.() -> Unit = {}

) : BoxplotAesthetics,
    BoxplotParameters,
    BoxplotStatAesthetics,
    BoxplotStatParameters,
    jetbrains.letsPlot.intern.layer.LayerBase(
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
                super<BoxplotStatParameters>.seal()
    }
}

