/*
 * Copyright (c) 2021. JetBrains s.r.o.
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
import org.jetbrains.letsPlot.intern.layer.geom.Bin2dMapping
import org.jetbrains.letsPlot.intern.layer.geom.TileAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.Bin2dStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.Bin2dStatParameters
import org.jetbrains.letsPlot.pos.positionIdentity

@Suppress("ClassName")
class statBin2D(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.tile(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val width: Number? = null,
    override val height: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
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
        stat = Stat.bin2D(),
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