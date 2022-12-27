/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.bistro.residual

import jetbrains.datalore.base.enums.EnumInfoFactory
import jetbrains.datalore.plot.base.stat.regression.LinearRegression
import jetbrains.datalore.plot.base.stat.regression.LocalPolynomialRegression
import jetbrains.datalore.plot.base.stat.regression.PolynomialRegression

internal class Model(
    val method: Method,
    private val polynomialDegree: Int,
    private val span: Double
) {
    fun getPredictor(xs: List<Double?>, ys: List<Double?>): (Double) -> Double {
        return when (method) {
            Method.LM -> getLMPredictor(xs, ys)
            Method.LOESS, Method.LOWESS -> getLoessPredictor(xs, ys)
            Method.NONE -> { _ -> 0.0 }
        }
    }

    private fun getLMPredictor(xs: List<Double?>, ys: List<Double?>): (Double) -> Double {
        require(polynomialDegree >= 1) { "Degree of polynomial regression must be at least 1" }
        val regression = if (polynomialDegree == 1) {
            LinearRegression(xs, ys, CONFIDENCE_LEVEL)
        } else {
            require(PolynomialRegression.canBeComputed(xs, ys, polynomialDegree))
                { "Degree of polynomial is too big for the given data" }
            PolynomialRegression(xs, ys, CONFIDENCE_LEVEL, polynomialDegree)
        }
        return { x -> regression.evalX(x).y }
    }

    private fun getLoessPredictor(xs: List<Double?>, ys: List<Double?>): (Double) -> Double {
        val regression = LocalPolynomialRegression(xs, ys, CONFIDENCE_LEVEL, span)
        require(regression.canCompute) { "Too small dataset for the loess method" }
        return { x -> regression.evalX(x).y }
    }

    enum class Method {
        LM, LOESS, LOWESS, NONE;

        companion object {

            private val ENUM_INFO = EnumInfoFactory.createEnumInfo<Method>()

            fun safeValueOf(v: String): Method {
                return ENUM_INFO.safeValueOf(v) ?:
                throw IllegalArgumentException(
                    "Unsupported method: '$v'\n" +
                    "Use one of: lm, loess, lowess, none."
                )
            }
        }
    }

    companion object {
        val METHOD_DEF = Method.LM
        const val POLYNOMIAL_DEGREE_DEF = 1
        const val SPAN_DEF = 0.5

        const val CONFIDENCE_LEVEL = 0.95
    }
}