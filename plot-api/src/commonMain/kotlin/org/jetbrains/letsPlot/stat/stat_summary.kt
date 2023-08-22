/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.stat

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.PointRangeAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.PointRangeMapping
import org.jetbrains.letsPlot.intern.layer.stat.SummaryStatParameters
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions

class statSummary(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.pointrange(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val x: Any? = null,
    override val y: Any? = null,
    override val ymin: Any? = null,
    override val ymax: Any? = null,
    override val alpha: Any? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val shape: Any? = null,
    override val size: Any? = null,
    override val stroke: Any? = null,
    override val fn: String? = null,
    override val fnMin: String? = null,
    override val fnMax: String? = null,
    override val quantiles: List<Number>? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: PointRangeMapping.() -> Unit = {}
) : PointRangeAesthetics,
    SummaryStatParameters,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = PointRangeMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.summary(),
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    ) {

    override fun seal(): Options {
        return super<PointRangeAesthetics>.seal() +
                super<SummaryStatParameters>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }
}