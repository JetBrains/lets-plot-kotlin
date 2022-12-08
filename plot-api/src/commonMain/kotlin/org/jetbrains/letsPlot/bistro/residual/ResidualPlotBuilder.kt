/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.bistro.residual

import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.intern.GenericAesMapping
import org.jetbrains.letsPlot.intern.Plot
import org.jetbrains.letsPlot.intern.asPlotData
import org.jetbrains.letsPlot.letsPlot

internal class ResidualPlotBuilder(
    private val data: Map<*, *>,
    private val x: String,
    private val y: String,
    private val method: String?,
    private val polynomialDegree: Int?,
) {
    fun build(): Plot {
        val statData = ResidualUtil.appendResiduals(data, x, y, getModel())
        val mapping: GenericAesMapping.() -> Unit = {
            x = this@ResidualPlotBuilder.x
            y = ResidualVar.RESIDUAL
        }
        return letsPlot(asPlotData(statData), mapping) + geomPoint()
    }

    private fun getModel(): Model {
        return Model(
            if (method != null) Model.Method.safeValueOf(method) else Model.METHOD_DEF,
            polynomialDegree ?: Model.POLYNOMIAL_DEGREE_DEF
        )
    }
}