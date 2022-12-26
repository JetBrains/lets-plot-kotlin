/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.bistro.residual

import jetbrains.datalore.base.interval.DoubleSpan
import jetbrains.datalore.plot.base.DataFrame
import jetbrains.datalore.plot.base.data.DataFrameUtil
import jetbrains.datalore.plot.base.util.SamplingUtil
import jetbrains.datalore.plot.common.data.SeriesUtil
import kotlin.random.Random

internal object ResidualUtil {
    fun appendResiduals(
        data: Map<String, List<Any?>>,
        x: String,
        y: String,
        model: Model,
        loessCriticalSize: Int,
        samplingSeed: Long
    ): Map<*, *> {
        val result = data.toMutableMap()
        result[ResidualVar.RESIDUAL] = if (data.isEmpty() || data.values.first().isEmpty()) {
            emptyList()
        } else {
            val df = DataFrameUtil.fromMap(data)
            val afterSampling = applySampling(df, model, loessCriticalSize, samplingSeed)
            val xs = extractDataSeries(afterSampling, x)
            val xRange = SeriesUtil.range(xs)
            val predictor: (Double) -> Double = model.getPredictor(
                xs = xs,
                ys = extractDataSeries(afterSampling, y)
            )
            calculateResiduals(
                xs = extractDataSeries(df, x),
                ys = extractDataSeries(df, y),
                predictor,
                range = xRange
            )
        }
        return result
    }

    private fun calculateResiduals(
        xs: List<Double?>,
        ys: List<Double?>,
        predictor: (Double) -> Double,
        range: DoubleSpan?
    ): List<Double?> {
        return (xs zip ys).map { (x, y) ->
            if (SeriesUtil.allFinite(x, y) && range?.contains(x!!) == true) {
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

    private fun extractDataSeries(df: DataFrame, varName: String): List<Double?> {
        val v = DataFrameUtil.findVariableOrFail(df, varName)
        return df.getNumeric(v)
    }
}