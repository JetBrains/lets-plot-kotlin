/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.stat

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Pos
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.GeomOptions
import org.jetbrains.letsPlot.intern.layer.LayerBase
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.geom.HistogramAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.HistogramMapping
import org.jetbrains.letsPlot.intern.layer.stat.BinStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.BinStatParameters
import org.jetbrains.letsPlot.pos.positionStack

@Suppress("ClassName")
class statBin(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.histogram(),
    position: PosOptions = positionStack,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
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