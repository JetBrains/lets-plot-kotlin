/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.bistro.residual

import jetbrains.datalore.plot.base.util.SamplingUtil
import org.jetbrains.letsPlot.intern.asPlotData
import kotlin.random.Random

internal object ResidualUtil {
    fun appendResiduals(
        data: Map<*, *>,
        x: String,
        y: String,
        model: Model,
        loessCriticalSize: Int,
        samplingSeed: Long
    ): Map<*, *> {
        val plotData = asPlotData(data)
        val result = applySampling(plotData, model, loessCriticalSize, samplingSeed).toMutableMap()
        result[ResidualVar.RESIDUAL] = if (plotData.isEmpty() || plotData.values.first().isEmpty())
            emptyList()
        else
            calculateResiduals(plotData, x, y, model)
        return result
    }

    private fun calculateResiduals(
        data: Map<String, List<Any?>>,
        x: String,
        y: String,
        model: Model
    ): List<Double?> {
        val xs = data.getValue(x) as List<Double?>
        val ys = data.getValue(y) as List<Double?>
        val predictor = model.getPredictor(xs, ys)
        return (xs zip ys).map { p ->
            if (p.first?.isFinite() == true && p.second?.isFinite() == true) {
                p.second!! - predictor(p.first!!)
            } else null
        }
    }

    private fun applySampling(
        data: Map<String, List<Any?>>,
        model: Model,
        loessCriticalSize: Int,
        samplingSeed: Long
    ): Map<String, List<Any?>> {
        val rowCount = data.values.first().size
        val onPick: (Set<Int>) -> Map<String, List<Any?>> = { indices ->
            val result: MutableMap<String, List<Any?>> = mutableMapOf()
            data.forEach { (variable, values) ->
                result[variable] = values.slice(indices)
            }
            result
        }
        val onDrop: (Set<Int>) -> Map<String, List<Any?>> = { indices ->
            val oppositeIndices = data.values.first().indices.subtract(indices)
            val result: MutableMap<String, List<Any?>> = mutableMapOf()
            data.forEach { (variable, values) ->
                result[variable] = values.slice(oppositeIndices)
            }
            result
        }
        return if (model.method == Model.Method.LOESS && rowCount > loessCriticalSize)
            SamplingUtil.sampleWithoutReplacement(rowCount, loessCriticalSize, Random(samplingSeed), onPick, onDrop)
        else
            data
    }
}