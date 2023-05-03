/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.LayerBase
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.StatOptions
import org.jetbrains.letsPlot.intern.layer.WithColorOption
import org.jetbrains.letsPlot.intern.layer.WithFillOption
import org.jetbrains.letsPlot.intern.layer.geom.LollipopAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.LollipopMapping
import org.jetbrains.letsPlot.intern.layer.geom.LollipopParameters
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName", "SpellCheckingInspection")
class geomLollipop(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    orientation: String? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val size: Number? = null,
    override val stroke: Number? = null,
    override val linewidth: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val alpha: Number? = null,
    override val shape: Any? = null,
    override val linetype: Any? = null,
    override val fatten: Number? = null,
    override val slope: Number? = null,
    override val intercept: Number? = null,
    override val dir: String? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: LollipopMapping.() -> Unit = {}
) : LollipopAesthetics,
    LollipopParameters,
    WithColorOption,
    WithFillOption,
    LayerBase(
        mapping = LollipopMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.lollipop(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips,
        orientation = orientation
    ) {
    override fun seal(): Options {
        return super<LollipopAesthetics>.seal() +
                super<LollipopParameters>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }
}