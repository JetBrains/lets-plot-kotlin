/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.stat

import jetbrains.letsPlot.Geom
import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.GeomOptions
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.SamplingOptions
import jetbrains.letsPlot.intern.layer.geom.QQLineAesthetics
import jetbrains.letsPlot.intern.layer.geom.QQLineMapping
import jetbrains.letsPlot.intern.layer.stat.QQLineStatParameters
import jetbrains.letsPlot.intern.layer.stat.QQStatAesthetics

@Suppress("ClassName")
class statQQLine(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.qqLine(),
    position: PosOptions = Pos.identity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val sample: Double? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val distribution: String? = null,
    override val dParams: List<Number>? = null,
    override val quantiles: Pair<Number, Number>? = null,
    mapping: QQLineMapping.() -> Unit = {}
) : QQLineAesthetics,
    QQStatAesthetics,
    QQLineStatParameters,
    LayerBase(
        mapping = QQLineMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.qqLine(),
        position = position,
        showLegend = showLegend,
        sampling = sampling
    ) {

    override fun seal(): Options {
        return super<QQLineAesthetics>.seal() +
                super<QQStatAesthetics>.seal() +
                super<QQLineStatParameters>.seal()
    }
}