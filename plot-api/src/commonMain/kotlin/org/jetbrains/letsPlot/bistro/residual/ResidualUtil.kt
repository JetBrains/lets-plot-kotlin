/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.bistro.residual

import jetbrains.datalore.plot.base.DataFrame
import jetbrains.datalore.plot.base.data.DataFrameUtil

internal object ResidualUtil {
    fun appendResiduals(
        data: Map<*, *>,
        x: String,
        y: String,
        model: Model
    ): Map<*, *> {
        val df = DataFrameUtil.fromMap(data)
        val result = data.toMutableMap()
        result[ResidualVar.RESIDUAL] = if (df.rowCount() == 0)
            emptyList()
        else
            calculateResiduals(df, x, y, model)
        return result
    }

    private fun calculateResiduals(df: DataFrame, x: String, y: String, model: Model): List<Double?> {
        val xs = df.getNumeric(DataFrameUtil.findVariableOrFail(df, x))
        val ys = df.getNumeric(DataFrameUtil.findVariableOrFail(df, y))
        val predictor = model.getPredictor(xs, ys)
        return (xs zip ys).map { p ->
            if (p.first?.isFinite() == true && p.second?.isFinite() == true) {
                p.second!! - predictor(p.first!!)
            } else null
        }
    }
}