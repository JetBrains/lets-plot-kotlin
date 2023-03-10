/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.stat

import org.jetbrains.letsPlot.Stat.density
import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.GeomOptions
import org.jetbrains.letsPlot.intern.layer.LayerBase
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.WithColorOption
import org.jetbrains.letsPlot.intern.layer.WithFillOption
import org.jetbrains.letsPlot.intern.layer.geom.AreaAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.DensityMapping
import org.jetbrains.letsPlot.intern.layer.stat.DensityStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.DensityStatParameters
import org.jetbrains.letsPlot.pos.positionStack

@Suppress("ClassName")
class statDensity(
    data: Map<*, *>? = null,
    geom: GeomOptions = GeomOptions(GeomKind.DENSITY),
    position: PosOptions = positionStack(),
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val weight: Number? = null,
    override val bw: Any? = null,
    override val kernel: String? = null,
    override val n: Int? = null,
    override val trim: Boolean? = null,
    override val adjust: Number? = null,
    override val fullScanMax: Int? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: DensityMapping.() -> Unit = {}

) : AreaAesthetics,
    DensityStatAesthetics,
    DensityStatParameters,
    WithColorOption,
    WithFillOption,
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
                super<DensityStatParameters>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }
}

