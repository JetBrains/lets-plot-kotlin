/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.stat

import jetbrains.letsPlot.Geom
import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.geom.PathAesthetics
import jetbrains.letsPlot.intern.layer.geom.PathMapping
import jetbrains.letsPlot.intern.layer.stat.QQ2LineStatParameters
import jetbrains.letsPlot.intern.layer.stat.QQ2StatAesthetics

@Suppress("ClassName")
class statQQ2Line(
    data: Map<*, *>? = null,
    geom: jetbrains.letsPlot.intern.layer.GeomOptions = Geom.path(),
    position: jetbrains.letsPlot.intern.layer.PosOptions = Pos.identity,
    showLegend: Boolean = true,
    sampling: jetbrains.letsPlot.intern.layer.SamplingOptions? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val speed: Double? = null,
    override val flow: Double? = null,
    override val quantiles: Pair<Number, Number>? = null,
    mapping: PathMapping.() -> Unit = {}
): PathAesthetics,
    QQ2StatAesthetics,
    QQ2LineStatParameters,
    jetbrains.letsPlot.intern.layer.LayerBase(
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