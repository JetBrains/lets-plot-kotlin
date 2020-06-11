/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.stat

import jetbrains.letsPlot.Geom
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.Pos.dodge
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.layer.GeomOptions
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.SamplingOptions
import jetbrains.letsPlot.intern.layer.stat.*

@Suppress("ClassName")
class stat_boxplot(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.boxplot(),
    position: PosOptions = dodge,
    show_legend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val varWidth: Any? = null,
    override val coef: Any? = null,
    override val weight: Double? = null,
    mapping: BoxplotMapping.() -> Unit = {}

) : BoxplotAesthetics, BoxplotStatParameters,
    LayerBase(
        mapping = BoxplotMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.boxplot(),
        position = position,
        show_legend = show_legend,
        sampling = sampling

    ) {

    override fun seal(): Options {
        return super<BoxplotAesthetics>.seal() +
                super<BoxplotStatParameters>.seal()
    }
}

