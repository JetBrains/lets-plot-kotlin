/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.bistro.residual

import jetbrains.datalore.base.enums.EnumInfoFactory
import jetbrains.datalore.plot.base.stat.regression.LinearRegression
import jetbrains.datalore.plot.base.stat.regression.PolynomialRegression

internal class Model(
    private val method: Method,
    private val polynomialDegree: Int
) {
    fun getPredictor(xs: List<Double?>, ys: List<Double?>): (Double) -> Double {
        return when (method) {
            Method.LM -> getLMPredictor(xs, ys)
        }
    }

    private fun getLMPredictor(xs: List<Double?>, ys: List<Double?>): (Double) -> Double {
        require(polynomialDegree >= 1) { "Degree of polynomial regression must be at least 1" }
        val confidenceLevel = 0.95
        val regression = if (polynomialDegree == 1) {
            LinearRegression(xs, ys, confidenceLevel)
        } else {
            require(PolynomialRegression.canBeComputed(xs, ys, polynomialDegree))
                { "Degree of polynomial is too big for the given data" }
            PolynomialRegression(xs, ys, confidenceLevel, polynomialDegree)
        }
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
        const val POLYNOMIAL_DEGREE_DEF = 1
    }
}