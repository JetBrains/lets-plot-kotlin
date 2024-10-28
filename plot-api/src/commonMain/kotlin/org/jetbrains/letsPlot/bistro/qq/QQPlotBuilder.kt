/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.bistro.qq

import org.jetbrains.letsPlot.core.spec.Option.Plot.BISTRO
import org.jetbrains.letsPlot.core.spec.back.transform.bistro.qq.Option.QQ
import org.jetbrains.letsPlot.core.spec.back.transform.bistro.qq.QQPlotOptionsBuilder
import org.jetbrains.letsPlot.intern.OptionsMap
import org.jetbrains.letsPlot.intern.Plot
import org.jetbrains.letsPlot.intern.filterNonNullValues
import org.jetbrains.letsPlot.letsPlot

internal class QQPlotBuilder(
    private val data: Map<*, *>,
    private val sample: String?,
    private val x: String?,
    private val y: String?,
    private val distribution: String?,
    private val dParams: List<Number>?,
    private val quantiles: Pair<Number, Number>?,
    private val group: String?,
    private val showLegend: Boolean,
    private val marginal: String,
    private val color: String?,
    private val fill: String?,
    private val alpha: Number?,
    private val size: Number?,
    private val shape: Int?,
    private val lineColor: String?,
    private val lineSize: Number?,
    private val linetype: Any?
) {
    fun build(): Plot {
        return letsPlot(data) + OptionsMap(
            kind = BISTRO,
            name = QQ.NAME,
            options = mapOf(
                QQ.SAMPLE to sample,
                QQ.X to x,
                QQ.Y to y,
                QQ.DISTRIBUTION to distribution,
                QQ.DISTRIBUTION_PARAMETERS to dParams,
                QQ.QUANTILES to quantiles,
                QQ.GROUP to group,
                QQ.SHOW_LEGEND to showLegend,
                QQ.MARGINAL to marginal,
                QQ.POINT_COLOR to color,
                QQ.POINT_FILL to fill,
                QQ.POINT_ALPHA to alpha,
                QQ.POINT_SIZE to size,
                QQ.POINT_SHAPE to shape,
                QQ.LINE_COLOR to lineColor,
                QQ.LINE_SIZE to lineSize,
                QQ.LINE_TYPE to linetype
            ).filterNonNullValues()
        )
    }

    companion object {
        const val DEF_MARGINAL = QQPlotOptionsBuilder.DEF_MARGINAL
    }
}