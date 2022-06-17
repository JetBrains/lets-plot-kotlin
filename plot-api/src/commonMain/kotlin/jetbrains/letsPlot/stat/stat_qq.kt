/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.stat

import jetbrains.letsPlot.Geom
import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.geom.QQAesthetics
import jetbrains.letsPlot.intern.layer.geom.QQMapping
import jetbrains.letsPlot.intern.layer.stat.QQStatAesthetics
import jetbrains.letsPlot.intern.layer.stat.QQStatParameters

@Suppress("ClassName")
class statQQ(
    data: Map<*, *>? = null,
    geom: jetbrains.letsPlot.intern.layer.GeomOptions = Geom.qq(),
    position: jetbrains.letsPlot.intern.layer.PosOptions = Pos.identity,
    showLegend: Boolean = true,
    sampling: jetbrains.letsPlot.intern.layer.SamplingOptions? = null,
    override val sample: Double? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val shape: Any? = null,
    override val size: Number? = null,
    override val distribution: String? = null,
    override val dParams: List<Number>? = null,
    mapping: QQMapping.() -> Unit = {}
) : QQAesthetics,
    QQStatAesthetics,
    QQStatParameters,
    jetbrains.letsPlot.intern.layer.LayerBase(
        mapping = QQMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.qq(),
        position = position,
        showLegend = showLegend,
        sampling = sampling
    ) {

    override fun seal(): Options {
        return super<QQAesthetics>.seal() +
                super<QQStatAesthetics>.seal() +
                super<QQStatParameters>.seal()
    }
}
