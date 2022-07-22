/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.stat

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.GeomOptions
import org.jetbrains.letsPlot.intern.layer.LayerBase
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.geom.QQAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.QQMapping
import org.jetbrains.letsPlot.intern.layer.stat.QQStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.QQStatParameters
import org.jetbrains.letsPlot.pos.positionIdentity

@Suppress("ClassName")
class statQQ(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.qq(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val sample: Number? = null,
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
    LayerBase(
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
