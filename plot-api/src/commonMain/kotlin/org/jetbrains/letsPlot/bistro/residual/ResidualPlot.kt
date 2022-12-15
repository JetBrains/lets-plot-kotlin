/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.bistro.residual

fun residualPlot(
    data: Map<*, *>,
    x: String,
    y: String,
    method: String? = null,
    deg: Int? = null,
    span: Double? = null,
    seed: Long = ResidualPlotBuilder.SAMPLING_SEED_DEF,
    maxN: Int = ResidualPlotBuilder.LOESS_CRITICAL_SIZE_DEF,
    geom: String? = ResidualPlotBuilder.DEF_GEOM,
    bins: Any? = null,
    binWidth: Any? = null,
    color: String? = null,
    size: Number? = null,
    alpha: Number? = null,
    colorBy: String? = null,
    showLegend: Boolean = true,
    hline: Boolean = true,
    marginal: String = ResidualPlotBuilder.MARGINAL_DEFAULT
) = ResidualPlotBuilder(
    data,
    x,
    y,
    method,
    deg,
    span,
    maxN,
    seed,
    geom,
    bins,
    binWidth,
    color,
    size,
    alpha,
    colorBy,
    showLegend,
    hline,
    marginal
).build()