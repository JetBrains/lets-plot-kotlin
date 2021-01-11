/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.bistro.corr

import jetbrains.datalore.base.gcommon.base.Preconditions
import jetbrains.datalore.plot.base.DataFrame
import jetbrains.datalore.plot.base.data.DataFrameUtil
import jetbrains.datalore.plot.common.data.SeriesUtil
import jetbrains.letsPlot.intern.asPlotData
import kotlin.math.absoluteValue

internal object CorrUtil {
    fun correlations(
        data: Map<String, List<Any?>>,
        corrFun: (List<Double>, List<Double>) -> Double
    ): Map<Pair<String, String>, Double> {
        @Suppress("NAME_SHADOWING")
        val data = asPlotData(data)

        val df = DataFrameUtil.fromMap(data)
        val numerics = df.variables().filter { df.isNumeric(it) }
        val correlations = HashMap<Pair<DataFrame.Variable, DataFrame.Variable>, Double>()

        for (variable in numerics) {
            correlations[variable to variable] = 1.0
        }

        for ((i, vx) in numerics.withIndex()) {
            val xs = df.getNumeric(vx)

            for (j in 0 until i) {
                val vy = numerics[j]
                val ys = df.getNumeric(vy)
                correlations[vx to vy] = correlation(xs, ys, corrFun)
            }
        }

        return correlations.mapKeys {
            Pair(
                it.key.first.label,
                it.key.second.label
            )
        }
    }

    private fun correlation(
        xs: List<Double?>,
        ys: List<Double?>,
        corrFun: (List<Double>, List<Double>) -> Double
    ): Double {
        val filtered = filterFinite(xs, ys)

        @Suppress("NAME_SHADOWING")
        val xs = filtered[0]

        @Suppress("NAME_SHADOWING")
        val ys = filtered[1]

        return corrFun(xs, ys)
    }


    fun matrixXYSeries(
        correlations: Map<Pair<String, String>, Double>,
        variablesInOrder: List<String>,
        type: String,
        dropDiag: Boolean,
        threshold: Double
    ): Pair<List<String>, List<String>> {

        val xs = ArrayList<String>()
        val ys = ArrayList<String>()
        for ((ix, vx) in variablesInOrder.withIndex()) {
            val iterY = if (type == "upper") {
                variablesInOrder.subList(ix, variablesInOrder.size)
            } else if (type == "lower") {
                variablesInOrder.subList(0, ix + 1)
            } else {
                variablesInOrder
            }
            for (vy in iterY) {
                if (vx == vy && dropDiag) continue

                // ToDo: threshold

                xs.add(vx)
                ys.add(vy)
            }
        }

        return Pair(xs, ys)
    }

    fun correlationsToDataframe(
        params: LayerParams,
        correlations: Map<Pair<String, String>, Double>,
        variablesInOrder: List<String>,
        dropDiag: Boolean,
        threshold: Double,
    ): Map<String, List<Any?>> {

        val (xs, ys) = matrixXYSeries(
            correlations, variablesInOrder, params.type!!, dropDiag, threshold
        )

        val corr = ArrayList<Double?>()
        val corrAbs = ArrayList<Double?>()

        fun toKey(s0: String, s1: String): Pair<String, String> {
            return if (s0 < s1) {
                s0 to s1
            } else {
                s1 to s0
            }
        }

        fun toKey(pair: Pair<String, String>): Pair<String, String> = toKey(pair.first, pair.second)

        val correlations1 = correlations.mapKeys { toKey(it.key) }
        for ((x, y) in xs.zip(ys)) {
            val v = if (params.diag != true && x == y) {
                null
            } else {
                correlations1[toKey(x, y)]
            }

            corr.add(v)
            corrAbs.add(v?.absoluteValue)
        }

        return linkedMapOf<String, List<Any?>>(
            CorrVar.X to xs,
            CorrVar.Y to ys,
            CorrVar.CORR to corr,
            CorrVar.CORR_ABS to corrAbs
        )
    }


    // ToDo: this fun is in 'SeriesUtil' since 1.6.0
    private fun filterFinite(l0: List<Double?>, l1: List<Double?>): List<List<Double>> {
        Preconditions.checkState(l0.size == l1.size)

        val l0Copy = ArrayList<Double>()
        val l1Copy = ArrayList<Double>()
        var copy: Boolean = false
        for ((i, v0) in l0.withIndex()) {
            val v1 = l1[i]
            if (!SeriesUtil.allFinite(v0, v1)) {
                if (!copy) {
                    // copy already checked elements
                    @Suppress("UNCHECKED_CAST")
                    l0Copy.addAll(l0.take(i).toList() as List<Double>)
                    @Suppress("UNCHECKED_CAST")
                    l1Copy.addAll(l1.take(i).toList() as List<Double>)
                    copy = true
                }
                continue
            }

            if (copy) {
                l0Copy.add(v0 as Double)
                l1Copy.add(v1 as Double)
            }
        }

        @Suppress("UNCHECKED_CAST")
        return when (copy) {
            true -> listOf(l0Copy, l1Copy)
            false -> listOf(l0 as List<Double>, l1 as List<Double>)
        }
    }
}