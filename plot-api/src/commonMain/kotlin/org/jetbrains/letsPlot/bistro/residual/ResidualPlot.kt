/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.bistro.residual


/**
 * Produces a residual plot that shows the difference between the observed response and the fitted response values.
 *
 * ## Examples
 *
 * - [residual_plot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/residual_plot.ipynb)
 *
 * @param data
 *     The data to be displayed.
 * @param x string
 *     Name of independent variable.
 * @param y string
 *     Name of dependent variable that will be fitted.
 * @param method string, {"lm", "loess", "lowess", "none"}, default="lm"
 *     Fitting method: "lm" (Linear Model) or "loess"/"lowess" (Locally Estimated Scatterplot Smoothing).
 *     If value of `deg` parameter is greater than 1 then linear model becomes polynomial of the given degree.
 *     If method is "none" then data lives as is.
 * @param deg int, default=1
 *     Degree of polynomial for linear regression model.
 * @param span double, default=0.5
 *     Only for 'loess' method. The fraction of source points closest to the current point is taken into account
 *     for computing a least-squares regression. A sensible value is usually 0.25 to 0.5.
 * @param seed number
 *     Random seed for 'loess' sampling.
 * @param maxN int
 *     Maximum number of data-points for 'loess' method. If this quantity exceeded random sampling is applied to data.
 * @param geom string, {"point", "tile", "none"}, default="point"
 *     The geometric object to use to display the data. No object will be used if `geom="none"`.
 * @param bins number or a pair of numbers
 *     Number of bins in both directions, vertical and horizontal. Overridden by `binWidth`.
 *     If only one value given - interpret it as list of two equal values.
 *     Applicable simultaneously for 'tile' geom and 'histogram' marginal.
 * @param binWidth number or a pair of numbers
 *     The width of the bins in both directions, vertical and horizontal.
 *     Overrides `bins`. The default is to use bin widths that cover the entire range of the data.
 *     If only one value given - interpret it as list of two equal values.
 *     Applicable simultaneously for 'tile' geom and 'histogram' marginal.
 * @param color string
 *     Color of a geometry.
 * @param size number
 *     Size of a geometry.
 * @param alpha number
 *     Transparency level of a geometry. Accepts values between 0 and 1.
 * @param colorBy string
 *     Name of grouping variable.
 * @param showLegend boolean, optional, default=true
 *     false - do not show legend the main layer.
 * @param hline boolean, default=true
 *     false - do not show horizontal line passing through 0.
 * @param marginal string, default="dens:r"
 *     Description of marginal layers packed to string value.
 *     Different marginals are separated by the ',' char.
 *     Parameters of a marginal are separated by the ':' char.
 *     First parameter of a marginal is a geometry name.
 *     Possible values: "dens"/"density", "hist"/"histogram", "box"/"boxplot".
 *     Second parameter is a string specifying which sides of the plot the marginal layer will appear on.
 *     Possible values: 't' (top), 'b' (bottom), 'l' (left), 'r' (right).
 *     Third parameter (optional) is size of marginal.
 *     To suppress marginals use `marginal='none'`.
 *     Examples:
 *     "hist:tr:0.3",
 *     "dens:tr,hist:bl",
 *     "box:tr:.05, hist:bl, dens:bl".
 */
fun residualPlot(
    data: Map<*, *>,
    x: String,
    y: String,
    method: String? = null,
    deg: Int? = null,
    span: Double? = null,
    seed: Long = ResidualPlotBuilder.SAMPLING_SEED_DEF,
    maxN: Int = ResidualPlotBuilder.LOESS_CRITICAL_SIZE_DEF,
    geom: String? = ResidualPlotBuilder.DEF_GEOM,
    bins: Any? = null,
    binWidth: Any? = null,
    color: String? = null,
    size: Number? = null,
    alpha: Number? = null,
    colorBy: String? = null,
    showLegend: Boolean = true,
    hline: Boolean = true,
    marginal: String = ResidualPlotBuilder.DEF_MARGINAL
) = ResidualPlotBuilder(
    data,
    x,
    y,
    method,
    deg,
    span,
    maxN,
    seed,
    geom,
    bins,
    binWidth,
    color,
    size,
    alpha,
    colorBy,
    showLegend,
    hline,
    marginal
).build()