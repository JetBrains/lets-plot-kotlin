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
import org.jetbrains.letsPlot.intern.layer.geom.PathAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.PathMapping
import org.jetbrains.letsPlot.intern.layer.stat.QQ2LineStatParameters
import org.jetbrains.letsPlot.intern.layer.stat.QQ2StatAesthetics

@Suppress("ClassName")
class statQQ2Line(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.path(),
    position: PosOptions = Pos.identity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val speed: Number? = null,
    override val flow: Number? = null,
    override val quantiles: Pair<Number, Number>? = null,
    mapping: PathMapping.() -> Unit = {}
) : PathAesthetics,
    QQ2StatAesthetics,
    QQ2LineStatParameters,
    LayerBase(
        mapping = PathMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.qq2Line(),
        position = position,
        showLegend = showLegend,
        sampling = sampling
    ) {

    override fun seal(): Options {
        return super<PathAesthetics>.seal() +
                super<QQ2StatAesthetics>.seal() +
                super<QQ2LineStatParameters>.seal()
    }
}