/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.bistro.residual

import jetbrains.datalore.base.enums.EnumInfoFactory
import jetbrains.datalore.plot.base.stat.regression.LinearRegression

internal class Model(private val method: Method) {
    fun getPredictor(xs: List<Double?>, ys: List<Double?>): (Double) -> Double {
        return when (method) {
            Method.LM -> fitLinearRegression(xs, ys)
        }
    }

    private fun fitLinearRegression(xs: List<Double?>, ys: List<Double?>): (Double) -> Double {
        val confidenceLevel = 0.95
        val regression = LinearRegression(xs, ys, confidenceLevel)
        return { x -> regression.evalX(x).y }
    }

    enum class Method {
        LM;

        companion object {

            private val ENUM_INFO = EnumInfoFactory.createEnumInfo<Method>()

            fun safeValueOf(v: String): Method {
                return ENUM_INFO.safeValueOf(v) ?:
                throw IllegalArgumentException(
                    "Unsupported method: '$v'\n" +
                    "Use one of: lm."
                )
            }
        }
    }

    companion object {
        val METHOD_DEF = Method.LM
    }
}