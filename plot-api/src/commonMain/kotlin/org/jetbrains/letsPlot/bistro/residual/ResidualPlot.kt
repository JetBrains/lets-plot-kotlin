/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.bistro.residual

fun residualPlot(
    data: Map<*, *>,
    x: String,
    y: String,
) = ResidualPlotBuilder(
    data,
    x,
    y
).build()