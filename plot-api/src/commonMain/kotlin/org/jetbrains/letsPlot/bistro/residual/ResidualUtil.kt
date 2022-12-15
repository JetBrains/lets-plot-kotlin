/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.bistro.residual

import jetbrains.datalore.plot.base.DataFrame
import jetbrains.datalore.plot.base.data.DataFrameUtil
import jetbrains.datalore.plot.base.util.SamplingUtil
import jetbrains.datalore.plot.common.data.SeriesUtil
import kotlin.random.Random

internal object ResidualUtil {
    fun appendResiduals(
        df: DataFrame,
        xVar: DataFrame.Variable,
        yVar: DataFrame.Variable,
        model: Model,
        loessCriticalSize: Int,
        samplingSeed: Long
    ): Map<*, *> {
        val data = applySampling(df, model, loessCriticalSize, samplingSeed)

        val residuals = if (data.isEmpty || data[data.variables().first()].isEmpty()) {
            emptyList()
        } else {
            calculateResiduals(
                xs = data.getNumeric(xVar),
                ys = data.getNumeric(yVar),
                model
            )
        }
        val result = DataFrameUtil.toMap(data).toMutableMap()
        result[ResidualVar.RESIDUAL] = residuals
        return result
    }

    private fun calculateResiduals(
        xs: List<Double?>,
        ys: List<Double?>,
        model: Model
    ): List<Double?> {
        val predictor = model.getPredictor(xs, ys)
        return (xs zip ys).map { (x, y) ->
            if (SeriesUtil.allFinite(x, y)) {
                y!! - predictor(x!!)
            } else {
                null
            }
        }
    }

    private fun applySampling(
        df: DataFrame,
        model: Model,
        loessCriticalSize: Int,
        samplingSeed: Long
    ): DataFrame {
        return if (model.method == Model.Method.LOESS && df.rowCount() > loessCriticalSize) {
            SamplingUtil.sampleWithoutReplacement(loessCriticalSize, Random(samplingSeed), df)
        } else {
            df
        }
    }
}