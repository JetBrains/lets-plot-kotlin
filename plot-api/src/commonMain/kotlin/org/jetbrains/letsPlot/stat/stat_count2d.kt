/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.stat

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.*
import org.jetbrains.letsPlot.intern.layer.stat.Count2dStatAesthetics
import org.jetbrains.letsPlot.pos.positionIdentity


@Suppress("ClassName")
class statCount2d(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.pie(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val slice: Number? = null,
    override val explode: Number? = null,
    override val size: Number? = null,
    override val fill: Any? = null,
    override val color: Any? = null,
    override val alpha: Number? = null,
    override val weight: Number? = null,
    mapping: PieMapping.() -> Unit = {}
) : PieAesthetics,
    Count2dStatAesthetics,
    LayerBase(
        mapping = PieMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.count2d(),
        position = position,
        showLegend = showLegend,
        sampling = sampling
    ) {

    override fun seal(): Options {
        return super<PieAesthetics>.seal() +
                super<Count2dStatAesthetics>.seal()
    }
}