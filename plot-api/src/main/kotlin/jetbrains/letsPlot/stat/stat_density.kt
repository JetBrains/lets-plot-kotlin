/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.stat

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.Geom.area
import jetbrains.letsPlot.Pos.stack
import jetbrains.letsPlot.Stat.density
import jetbrains.letsPlot.intern.layer.SamplingOptions
import jetbrains.letsPlot.intern.layer.GeomOptions
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.stat.DensityAesthetics
import jetbrains.letsPlot.intern.layer.stat.DensityMapping
import jetbrains.letsPlot.intern.layer.stat.DensityParameters

@Suppress("ClassName")
class stat_density(
    data: Any? = null,
    geom: GeomOptions = area(),
    position: PosOptions = stack,
    show_legend: Boolean = true,
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
    override val kernel: Any? = null,
    override val n: Any? = null,
    override val trim: Any? = null,
    override val adjust: Any? = null,
    mapping: DensityMapping.() -> Unit = {}

) : DensityAesthetics, DensityParameters,
    LayerBase(
        mapping = DensityMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = density(),
        position = position,
        show_legend = show_legend,
        sampling = sampling
    ) {

    override fun seal(): Options {
        return super<DensityAesthetics>.seal() +
                super<DensityParameters>.seal()
    }
}

