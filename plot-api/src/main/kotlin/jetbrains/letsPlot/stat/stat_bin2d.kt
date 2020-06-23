/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.stat

import jetbrains.letsPlot.Geom
import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.*
import jetbrains.letsPlot.intern.layer.geom.Bin2dMapping
import jetbrains.letsPlot.intern.layer.geom.TileAesthetics
import jetbrains.letsPlot.intern.layer.stat.Bin2dStatAesthetics
import jetbrains.letsPlot.intern.layer.stat.Bin2dStatParameters

@Suppress("ClassName")
class stat_bin2d(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.tile(),
    position: PosOptions = Pos.identity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val width: Double? = null,
    override val height: Double? = null,
    override val alpha: Double? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val size: Double? = null,
    override val weight: Any? = null,
    override val bins: Pair<Int, Int>? = null,
    override val binWidth: Pair<Number?, Number?>? = null,
    override val drop: Boolean? = null,
    mapping: Bin2dMapping.() -> Unit = {}
) : TileAesthetics,
    Bin2dStatAesthetics,
    Bin2dStatParameters,
    LayerBase(
        mapping = Bin2dMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.bin2d(),
        position = position,
        showLegend = showLegend,
        sampling = sampling
    ) {
    override fun seal(): Options {
        return super<TileAesthetics>.seal() +
                super<Bin2dStatAesthetics>.seal() +
                super<Bin2dStatParameters>.seal()
    }
}