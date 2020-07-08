/*
 * Copyright (c) 2020. JetBrains s.r.o.
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
import jetbrains.letsPlot.intern.layer.geom.HistogramAesthetics
import jetbrains.letsPlot.intern.layer.geom.HistogramMapping
import jetbrains.letsPlot.intern.layer.stat.BinStatAesthetics
import jetbrains.letsPlot.intern.layer.stat.BinStatParameters

@Suppress("ClassName")
class stat_bin(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.histogram(),
    position: PosOptions = Pos.stack,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val size: Number? = null,
    override val weight: Any? = null,
    override val bins: Int? = null,
    override val binWidth: Number? = null,
    override val center: Number? = null,
    override val boundary: Number? = null,
    mapping: HistogramMapping.() -> Unit = {}
) : HistogramAesthetics,
    BinStatAesthetics,
    BinStatParameters,
    LayerBase(
        mapping = HistogramMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.bin(),
        position = position,
        showLegend = showLegend,
        sampling = sampling
    ) {
    override fun seal(): Options {
        return super<HistogramAesthetics>.seal() +
                super<BinStatAesthetics>.seal() +
                super<BinStatParameters>.seal()
    }
}