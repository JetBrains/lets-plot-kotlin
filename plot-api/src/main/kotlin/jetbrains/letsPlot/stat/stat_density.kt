/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.stat

import jetbrains.letsPlot.Pos.stack
import jetbrains.letsPlot.Stat.density
import jetbrains.letsPlot.intern.GeomKind
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.GeomOptions
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.SamplingOptions
import jetbrains.letsPlot.intern.layer.geom.AreaAesthetics
import jetbrains.letsPlot.intern.layer.geom.DensityMapping
import jetbrains.letsPlot.intern.layer.stat.DensityStatAesthetics
import jetbrains.letsPlot.intern.layer.stat.DensityStatParameters

@Suppress("ClassName")
class stat_density(
    data: Map<*, *>? = null,
    geom: GeomOptions = GeomOptions(GeomKind.DENSITY),
    position: PosOptions = stack,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Any? = null,
    override val y: Any? = null,
    override val alpha: Any? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val size: Any? = null,
    override val weight: Any? = null,
    override val bw: Any? = null,
    override val kernel: String? = null,
    override val n: Int? = null,
    override val trim: Boolean? = null,
    override val adjust: Number? = null,
    mapping: DensityMapping.() -> Unit = {}

) : AreaAesthetics,
    DensityStatAesthetics,
    DensityStatParameters,
    LayerBase(
        mapping = DensityMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = density(),
        position = position,
        showLegend = showLegend,
        sampling = sampling
    ) {

    override fun seal(): Options {
        return super<AreaAesthetics>.seal() +
                super<DensityStatAesthetics>.seal() +
                super<DensityStatParameters>.seal()
    }
}

