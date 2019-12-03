/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.stat

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.Geom
import jetbrains.letsPlot.Pos.stack
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.layer.SamplingOptions
import jetbrains.letsPlot.intern.layer.GeomOptions
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.geom.BarAesthetics
import jetbrains.letsPlot.intern.layer.stat.CountAesthetics
import jetbrains.letsPlot.intern.layer.stat.CountMapping
import jetbrains.letsPlot.intern.layer.stat.CountParameters

@Suppress("ClassName")
class stat_count(
    data: Any? = null,
    geom: GeomOptions = Geom.bar(),
    position: PosOptions = stack,
    show_legend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val alpha: Double? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val width: Double? = null,
    override val size: Double? = null,
    override val weight: Double? = null,
    mapping: CountMapping.() -> Unit = {}

) : CountAesthetics, BarAesthetics, CountParameters,
    LayerBase(
        mapping = CountMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.count(),
        position = position,
        show_legend = show_legend,
        sampling = sampling
    ) {

    override fun seal(): Options {
        return super<BarAesthetics>.seal() +
                super<CountAesthetics>.seal() +
                super<CountParameters>.seal()
    }
}

