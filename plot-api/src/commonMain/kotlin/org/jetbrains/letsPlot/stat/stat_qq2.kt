/*
 * Copyright (c) 2022. JetBrains s.r.o.
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
import org.jetbrains.letsPlot.intern.layer.geom.PointAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.PointMapping
import org.jetbrains.letsPlot.intern.layer.stat.QQ2StatAesthetics

@Suppress("ClassName")
class statQQ2(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.point(),
    position: PosOptions = Pos.identity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val shape: Any? = null,
    override val size: Number? = null,
    override val stroke: Number? = null,
    mapping: PointMapping.() -> Unit = {}
) : PointAesthetics,
    QQ2StatAesthetics,
    LayerBase(
        mapping = PointMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.qq2(),
        position = position,
        showLegend = showLegend,
        sampling = sampling
    ) {

    override fun seal(): Options {
        return super<PointAesthetics>.seal() +
                super<QQ2StatAesthetics>.seal()
    }
}