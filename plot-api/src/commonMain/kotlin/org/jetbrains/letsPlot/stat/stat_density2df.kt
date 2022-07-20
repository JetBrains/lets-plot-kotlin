/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.stat

import org.jetbrains.letsPlot.Pos
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.GeomOptions
import org.jetbrains.letsPlot.intern.layer.LayerBase
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.geom.Density2dfMapping
import org.jetbrains.letsPlot.intern.layer.geom.PolygonAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.Density2dStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.Density2dStatParameters

@Suppress("ClassName")
class statDensity2DFilled(
    data: Map<*, *>? = null,
    geom: GeomOptions = GeomOptions(GeomKind.DENSITY2DF),
    position: PosOptions = Pos.identity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val size: Number? = null,
    override val linetype: Any? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val alpha: Number? = null,
    override val weight: Number? = null,
    override val bw: Any? = null,
    override val kernel: String? = null,
    override val n: Int? = null,
    override val adjust: Number? = null,
    override val contour: Boolean? = null,
    override val bins: Int? = null,
    override val binWidth: Number? = null,
    mapping: Density2dfMapping.() -> Unit = {}
) : PolygonAesthetics,
    Density2dStatAesthetics,
    Density2dStatParameters,
    LayerBase(
        mapping = Density2dfMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.density2DFilled(),
        position = position,
        showLegend = showLegend,
        sampling = sampling
    ) {
    override fun seal(): Options {
        return super<PolygonAesthetics>.seal() +
                super<Density2dStatAesthetics>.seal() +
                super<Density2dStatParameters>.seal()
    }
}