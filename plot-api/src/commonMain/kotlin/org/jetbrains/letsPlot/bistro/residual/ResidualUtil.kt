/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.bistro.residual

import jetbrains.datalore.plot.base.DataFrame
import jetbrains.datalore.plot.base.data.DataFrameUtil
import jetbrains.datalore.plot.base.stat.regression.LinearRegression

internal object ResidualUtil {
    private const val REGRESSION_CONFIDENCE_LEVEL = 0.95

    fun appendResiduals(
        data: Map<String, List<Any?>>,
        x: String,
        y: String
    ): Map<String, List<Any?>> {
        val df = DataFrameUtil.fromMap(data)
        val result = data.toMutableMap()
        result[ResidualVar.RESIDUAL] = if (df.rowCount() == 0)
            emptyList()
        else
            calculateResiduals(df, x, y)
        return result
    }

    private fun calculateResiduals(df: DataFrame, x: String, y: String): List<Double?> {
        val xs = df.getNumeric(DataFrameUtil.findVariableOrFail(df, x))
        val ys = df.getNumeric(DataFrameUtil.findVariableOrFail(df, y))
        val regression = LinearRegression(xs, ys, REGRESSION_CONFIDENCE_LEVEL)
        return (xs zip ys).map { p ->
            if (p.first?.isFinite() == true && p.second?.isFinite() == true) {
                p.second!! - regression.evalX(p.first!!).y
            } else null
        }
    }
}