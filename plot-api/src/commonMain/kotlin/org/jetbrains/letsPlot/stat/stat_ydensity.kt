/*
 * Copyright (c) 2022. JetBrains s.r.o.
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
import org.jetbrains.letsPlot.intern.layer.geom.ViolinAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.ViolinMapping
import org.jetbrains.letsPlot.intern.layer.stat.YDensityStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.YDensityStatParameters
import org.jetbrains.letsPlot.pos.positionDodge

@Suppress("ClassName", "SpellCheckingInspection")
class statYDensity(
    data: Map<*, *>? = null,
    geom: GeomOptions = GeomOptions(GeomKind.VIOLIN),
    position: PosOptions = positionDodge(),
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val violinWidth: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val width: Number? = null,
    override val weight: Number? = null,
    override val scale: String? = null,
    override val bw: Any? = null,
    override val kernel: String? = null,
    override val n: Int? = null,
    override val trim: Boolean? = null,
    override val adjust: Number? = null,
    override val fullScanMax: Int? = null,
    mapping: ViolinMapping.() -> Unit = {}
) : ViolinAesthetics,
    YDensityStatAesthetics,
    YDensityStatParameters,
    LayerBase(
        mapping = ViolinMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.yDensity(),
        position = position,
        showLegend = showLegend,
        sampling = sampling
    ) {

    override fun seal(): Options {
        return super<ViolinAesthetics>.seal() +
                super<YDensityStatAesthetics>.seal() +
                super<YDensityStatParameters>.seal()
    }
}