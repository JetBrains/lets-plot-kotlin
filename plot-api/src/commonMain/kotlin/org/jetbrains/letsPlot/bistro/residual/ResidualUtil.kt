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
        colorBy: String?,
        model: Model,
        loessCriticalSize: Int,
        samplingSeed: Long
    ): Map<*, *> {

        fun emptyDataWithResiduals() = (data.keys + ResidualVar.RESIDUAL).associateWith { emptyList<Any>() }

        if (data.isEmpty() || data.values.first().isEmpty()) {
            return emptyDataWithResiduals()
        }

        val groupResults = splitByGroup(data, colorBy).map { groupData ->
            if (groupData.isEmpty() || !groupData.containsKey(x) || !groupData.containsKey(y)) {
                return@map emptyDataWithResiduals()
            }
            val xs = groupData[x]!!
            val ys = groupData[y]!!
            val filteredIndices = xs.zip(ys)
                .withIndex()
                .filter { (_, pair) -> SeriesUtil.allFinite(pair.first as? Double, pair.second as? Double) }
                .map { it.index }

                if (filteredIndices.size < 2) {
                    return@map emptyDataWithResiduals()
                }

                val indices = when (filteredIndices.size) {
                    // FIXME: Ensure data size is 3 - currently regressions need at least 3 points
                    // FIXME: jetbrains/datalore/plot/base/stat/regression/LinearRegression.kt:44
                    //        jetbrains/datalore/plot/base/stat/regression/LocalPolynomialRegression.kt:41
                    2 -> filteredIndices + filteredIndices.last()
                    else -> filteredIndices
                }
                val values= groupData.mapValues { SeriesUtil.pickAtIndices(it.value, indices) }
                appendResidualsToGroup(values, x, y, model, loessCriticalSize, samplingSeed)
            }

        // merge maps
        return groupResults.fold(mutableMapOf<String, MutableList<Any?>>()) { mergedMap, map ->
            for ((key, value) in map) {
                mergedMap.getOrPut(key, ::mutableListOf).addAll(value)
            }
            mergedMap
        }
    }

    private fun appendResidualsToGroup(
        groupData: Map<String, List<Any?>>,
        x: String,
        y: String,
        model: Model,
        loessCriticalSize: Int,
        samplingSeed: Long
    ): Map<String, List<Any?>> {
        val df = DataFrameUtil.fromMap(groupData)
        val afterSampling = applySampling(df, model, loessCriticalSize, samplingSeed)
        val xs = getNumeric(afterSampling, x)
        val xRange = SeriesUtil.range(xs)
        val predictor: (Double) -> Double = model.getPredictor(
            xs = xs,
            ys = getNumeric(afterSampling, y)
        )

        val result = groupData.toMutableMap()
        result[ResidualVar.RESIDUAL] = calculateResiduals(
            xs = getNumeric(df, x),
            ys = getNumeric(df, y),
            predictor,
            range = xRange
        )
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

    private fun getNumeric(df: DataFrame, varName: String): List<Double?> {
        return df.getNumeric(DataFrameUtil.findVariableOrFail(df, varName))
    }

    private fun splitByGroup(data: Map<String, List<Any?>>, groupName: String?): List<Map<String, List<Any?>>> {
        if (groupName == null || !data.containsKey(groupName)) {
            return listOf(data)
        }

        val groupIndices = mutableMapOf<Any?, MutableList<Int>>()
        data[groupName]!!.forEachIndexed { index, v ->
            groupIndices.getOrPut(v, ::mutableListOf).add(index)
        }

        val result = groupIndices.map { (_, indices) ->
            data.mapValues { (_, values) -> SeriesUtil.pickAtIndices(values, indices) }
        }
        return result
    }
}