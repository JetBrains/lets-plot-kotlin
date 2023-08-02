/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.bistro.residual

import org.jetbrains.letsPlot.core.commons.enums.EnumInfoFactory
import org.jetbrains.letsPlot.core.plot.base.stat.regression.LinearRegression
import org.jetbrains.letsPlot.core.plot.base.stat.regression.LocalPolynomialRegression
import org.jetbrains.letsPlot.core.plot.base.stat.regression.PolynomialRegression

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
        val regression = when (polynomialDegree) {
            1 -> LinearRegression.fit(xs, ys, CONFIDENCE_LEVEL)
            else -> PolynomialRegression.fit(xs, ys, CONFIDENCE_LEVEL, polynomialDegree)
        }
        require(regression != null) {
            when (polynomialDegree) {
                1 -> "Too small dataset for the linear regression"
                else -> "Degree of polynomial is too big for the given data"
            }
        }
        return { x -> regression.evalX(x).y }
    }

    private fun getLoessPredictor(xs: List<Double?>, ys: List<Double?>): (Double) -> Double {
        val regression = LocalPolynomialRegression.fit(xs, ys, CONFIDENCE_LEVEL, span)
        require(regression != null) { "Too small dataset for the loess method" }
        return { x -> regression.evalX(x).y }
    }

    enum class Method {
        LM, LOESS, LOWESS, NONE;

        companion object {

            private val ENUM_INFO = EnumInfoFactory.createEnumInfo<Method>()

            fun safeValueOf(v: String): Method {
                return ENUM_INFO.safeValueOf(v) ?: throw IllegalArgumentException(
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