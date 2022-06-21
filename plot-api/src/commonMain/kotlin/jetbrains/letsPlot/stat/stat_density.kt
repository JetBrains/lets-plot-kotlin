/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.stat

import jetbrains.letsPlot.Pos.stack
import jetbrains.letsPlot.Stat.density
import jetbrains.letsPlot.intern.GeomKind
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.geom.AreaAesthetics
import jetbrains.letsPlot.intern.layer.geom.DensityMapping
import jetbrains.letsPlot.intern.layer.stat.DensityStatAesthetics
import jetbrains.letsPlot.intern.layer.stat.DensityStatParameters

@Suppress("ClassName")
class statDensity(
    data: Map<*, *>? = null,
    geom: jetbrains.letsPlot.intern.layer.GeomOptions = jetbrains.letsPlot.intern.layer.GeomOptions(GeomKind.DENSITY),
    position: jetbrains.letsPlot.intern.layer.PosOptions = stack,
    showLegend: Boolean = true,
    sampling: jetbrains.letsPlot.intern.layer.SamplingOptions? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val weight: Double? = null,
    override val bw: Any? = null,
    override val kernel: String? = null,
    override val n: Int? = null,
    override val trim: Boolean? = null,
    override val adjust: Number? = null,
    override val fullScanMax: Int? = null,
    mapping: DensityMapping.() -> Unit = {}

) : AreaAesthetics,
    DensityStatAesthetics,
    DensityStatParameters,
    jetbrains.letsPlot.intern.layer.LayerBase(
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

