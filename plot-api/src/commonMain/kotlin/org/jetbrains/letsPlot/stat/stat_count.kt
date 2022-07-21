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
import org.jetbrains.letsPlot.intern.layer.geom.BarAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.BarMapping
import org.jetbrains.letsPlot.intern.layer.stat.CountStatAesthetics
import org.jetbrains.letsPlot.pos.positionStack

@Suppress("ClassName")
class statCount(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.bar(),
    position: PosOptions = positionStack,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val width: Number? = null,
    override val size: Number? = null,
    override val weight: Number? = null,
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

