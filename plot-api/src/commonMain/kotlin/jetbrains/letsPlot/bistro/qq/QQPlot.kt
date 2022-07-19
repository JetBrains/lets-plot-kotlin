/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.bistro.qq

/**
 * Produces a Q-Q plot (quantile-quantile plot).
 *
 * Supply the `sample` parameter to compare distribution of observations with a theoretical distribution
 * ('normal' or as otherwise specified by the `distribution` parameter).
 * Alternatively, supply `x` and `y` parameters to compare the distribution of `x` with the distribution of `y`.
 *
 * ## Examples
 *
 * - [qq_plots.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/qq_plots.ipynb)
 *
 * @param data
 *     The data to be displayed in this layer. If None, the default, the data
 *     is inherited from the plot data as specified in the call to [letsPlot][jetbrains.letsPlot.letsPlot].
 * @param sample string
 *     Name of variable.
 *     Specifies a vector of observations used for computing of "sample quantiles".
 *     Use this parameter to produce a "sample vs. theoretical" Q-Q plot.
 * @param x string
 * @param y string
 *     Names of variables specifying two vectors of observations used for computing of x and y "sample quantiles".
 *     Use these two parameters to produce a "sample X vs. sample Y" Q-Q plot.
 * @param distribution string
 *     Distribution function to use: "norm" (default), "uniform", "t", "gamma", "exp", "chi2".
 *     Could be specified if `sample` is.
 * @param dParams list of numbers
 *     Additional parameters passed on to distribution function.
 *     Could be specified if `sample` is.
 *     If `distribution` is `"norm"` then `dParams` is a pair [mean, std] (=[0.0, 1.0] by default).
 *     If `distribution` is `"uniform"` then `dParams` is a pair [a, b] (=[0.0, 1.0] by default).
 *     If `distribution` is `"t"` then `dParams` is an integer number [d] (=[1] by default).
 *     If `distribution` is `"gamma"` then `dParams` is a pair [alpha, beta] (=[1.0, 1.0] by default).
 *     If `distribution` is `"exp"` then `dParams` is a float number [lambda] (=[1.0] by default).
 *     If `distribution` is `"chi2"` then `dParams` is an integer number [k] (=[1] by default).
 * @param quantiles pair of numbers, default=[0.25, 0.75]
 *     Pair of quantiles to use when fitting the Q-Q line.
 * @param group string
 *     Grouping parameter.
 *     If it is specified and color-parameters isn't then different groups will has different colors.
 * @param showLegend boolean, optional, default=true.
 *     False - do not show legend for this layer.
 * @param color string
 *     Color of a points.
 * @param fill string
 *     Color to paint shape's inner points.
 *     Is applied only to the points of shapes having inner points.
 * @param alpha number, default=0.5
 *     Transparency level of a points. Understands numbers between 0 and 1.
 * @param size number, default=3.0
 *     Size of the points.
 * @param shape int
 *     Shape of the points.
 * @param lineColor string, default="#FF0000"
 *     Color of the fitting line.
 * @param lineSize number, default=0.75
 *     Width of the fitting line.
 * @param linetype int or string
 *     Type of the fitting line.
 *     Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *     5 = "longdash", 6 = "twodash"
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