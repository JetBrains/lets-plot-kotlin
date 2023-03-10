/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.stat

import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.GeomOptions
import org.jetbrains.letsPlot.intern.layer.LayerBase
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.WithColorOption
import org.jetbrains.letsPlot.intern.layer.WithFillOption
import org.jetbrains.letsPlot.intern.layer.geom.SmoothAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.SmoothMapping
import org.jetbrains.letsPlot.intern.layer.stat.SmoothStatParameters
import org.jetbrains.letsPlot.pos.positionIdentity

@Suppress("ClassName")
class statSmooth(
    data: Map<*, *>? = null,
    geom: GeomOptions = GeomOptions(GeomKind.SMOOTH),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val ymin: Number? = null,
    override val ymax: Number? = null,
    override val size: Number? = null,
    override val linetype: Any? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val alpha: Number? = null,
    override val method: String? = null,
    override val n: Int? = null,
    override val level: Number? = null,
    override val se: Boolean? = null,
    override val span: Number? = null,
    override val deg: Int? = null,
    override val seed: Long? = null,
    override val maxN: Int? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: SmoothMapping.() -> Unit = {}
) : SmoothAesthetics,
    SmoothStatParameters,
    WithColorOption,
    WithFillOption,
    LayerBase(
        mapping = SmoothMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.smooth(),
        position = position,
        showLegend = showLegend,
        sampling = sampling
    ) {
    override fun seal(): Options {
        return super<SmoothAesthetics>.seal() +
                super<SmoothStatParameters>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }
}