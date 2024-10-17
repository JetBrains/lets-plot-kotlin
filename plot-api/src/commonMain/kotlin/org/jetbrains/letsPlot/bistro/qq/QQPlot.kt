/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.bistro.qq

/**
 * Produces a Q-Q plot (quantile-quantile plot).
 *
 * Supply the `sample` parameter to compare distribution of observations with a theoretical distribution
 * ('normal' or as otherwise specified by the `distribution` parameter).
 * Alternatively, supply `x` and `y` parameters to compare the distribution of `x` with the distribution of `y`.
 *
 * ## Examples
 *
 * - [qq_plots.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/qq_plots.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param sample Name of variable.
 *  Specifies a vector of observations used for computing of "sample quantiles".
 *  Use this parameter to produce a "sample vs. theoretical" Q-Q plot.
 * @param x Name of variable specifying a vector of observations used for computing of x "sample quantiles".
 *  Use `x` and `y` parameters to produce a "sample X vs. sample Y" Q-Q plot.
 * @param y Name of variable specifying a vector of observations used for computing of y "sample quantiles".
 *  Use `x` and `y` parameters to produce a "sample X vs. sample Y" Q-Q plot.
 * @param distribution default = "norm".
 *  Distribution function to use: "norm", "uniform", "t", "gamma", "exp", "chi2".
 *  Could be specified if `sample` is.
 * @param dParams Additional parameters passed on to distribution function.
 *  Could be specified if `sample` is.
 *  - If `distribution` is "norm" then `dParams` is a pair (mean, std) (default = listOf(0.0, 1.0)).
 *  - If `distribution` is "uniform" then `dParams` is a pair (a, b) (default = listOf(0.0, 1.0)).
 *  - If `distribution` is "t" then `dParams` is an integer number (d) (default = listOf(1)).
 *  - If `distribution` is "gamma" then `dParams` is a pair (alpha, beta) (default = listOf(1.0, 1.0)).
 *  - If `distribution` is "exp" then `dParams` is a float number (lambda) (default = listOf(1.0)).
 *  - If `distribution` is "chi2" then `dParams` is an integer number (k) (default = listOf(1)).
 * @param quantiles default = Pair(0.25, 0.75).
 *  Pair of quantiles to use when fitting the Q-Q line.
 * @param group Grouping parameter.
 *  If it is specified and color-parameters isn't then different groups will have different colors.
 * @param showLegend default = true.
 *  false - do not show legend for this layer.
 * @param color Color of a points.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Color to paint shape's inner points.
 *  Is applied only to the points of shapes having inner points.
 * @param alpha default = 0.5.
 *  Transparency level of a points. Understands numbers between 0 and 1.
 * @param size default = 3.0.
 *  Size of the points.
 * @param shape Shape of the points.
 *  For more info see: [aesthetics.html#point-shapes](https://lets-plot.org/kotlin/aesthetics.html#point-shapes).
 * @param lineColor default = "#FF0000".
 *  Color of the fitting line.
 * @param lineSize default = 0.75.
 *  Width of the fitting line.
 * @param linetype Int or String or List or Pair.
 *  Type of the fitting line.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 *
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 */
fun qqPlot(
    data: Map<*, *>,
    sample: String? = null,
    x: String? = null,
    y: String? = null,
    distribution: String? = null,
    dParams: List<Number>? = null,
    quantiles: Pair<Number, Number>? = null,
    group: String? = null,
    showLegend: Boolean = true,
    color: String? = null,
    fill: String? = null,
    alpha: Number? = null,
    size: Number? = null,
    shape: Int? = null,
    lineColor: String? = null,
    lineSize: Number? = null,
    linetype: Any? = null,
) = QQPlotBuilder(
        data,
        sample,
        x,
        y,
        distribution,
        dParams,
        quantiles,
        group,
        showLegend,
        color,
        fill,
        alpha,
        size,
        shape,
        lineColor,
        lineSize,
        linetype
    ).build()