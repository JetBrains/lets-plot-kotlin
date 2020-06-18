/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.stat

import jetbrains.letsPlot.Geom
import jetbrains.letsPlot.Pos.stack
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.GeomOptions
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.SamplingOptions
import jetbrains.letsPlot.intern.layer.geom.BarAesthetics
import jetbrains.letsPlot.intern.layer.geom.BarMapping
import jetbrains.letsPlot.intern.layer.stat.CountStatAesthetics

@Suppress("ClassName")
class stat_count(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.bar(),
    position: PosOptions = stack,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val alpha: Double? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val width: Double? = null,
    override val size: Double? = null,
    override val weight: Double? = null,
    mapping: BarMapping.() -> Unit = {}

) : CountStatAesthetics, BarAesthetics,
    LayerBase(
        mapping = BarMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.count(),
        position = position,
        showLegend = showLegend,
        sampling = sampling
    ) {

    override fun seal(): Options {
        return super<BarAesthetics>.seal() +
                super<CountStatAesthetics>.seal()
    }
}

