/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.stat

import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.GeomKind
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.GeomOptions
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.SamplingOptions
import jetbrains.letsPlot.intern.layer.geom.ContourMapping
import jetbrains.letsPlot.intern.layer.geom.PathAesthetics
import jetbrains.letsPlot.intern.layer.stat.ContourStatAesthetics
import jetbrains.letsPlot.intern.layer.stat.ContourStatParameters

@Suppress("ClassName")
class statContour(
    data: Map<*, *>? = null,
    geom: GeomOptions = GeomOptions(GeomKind.CONTOUR),
    position: PosOptions = Pos.identity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val z: Double? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val speed: Double? = null,
    override val flow: Double? = null,
    override val bins: Int? = null,
    override val binWidth: Number? = null,
    mapping: ContourMapping.() -> Unit = {}
) : PathAesthetics,
    ContourStatAesthetics,
    ContourStatParameters,
    LayerBase(
        mapping = ContourMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.contour(),
        position = position,
        showLegend = showLegend,
        sampling = sampling
    ) {
    override fun seal(): Options {
        return super<PathAesthetics>.seal() +
                super<ContourStatAesthetics>.seal() +
                super<ContourStatParameters>.seal()
    }
}