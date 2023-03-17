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
 * - [residual_plot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/residual_plot.ipynb)
 *
 * @param data The data to be displayed.
 * @param x Name of independent variable.
 * @param y Name of dependent variable that will be fitted.
 * @param method {"lm", "loess", "lowess", "none"}, default = "lm".
 *  Fitting method: "lm" (Linear Model) or "loess"/"lowess" (Locally Estimated Scatterplot Smoothing).
 *  If value of `deg` parameter is greater than 1 then linear model becomes polynomial of the given degree.
 *  If method is "none" then data lives as is.
 * @param deg default = 1.
 *  Degree of polynomial for linear regression model.
 * @param span default = 0.5.
 *  Only for "loess" method. The fraction of source points closest to the current point is taken into account
 *  for computing a least-squares regression. A sensible value is usually 0.25 to 0.5.
 * @param seed Random seed for "loess" sampling.
 * @param maxN Maximum number of data-points for "loess" method. 
 *  If this quantity exceeded random sampling is applied to data.
 * @param geom {"point", "tile", "none"}, default = "point".
 *  The geometric object to use to display the data. No object will be used if `geom="none"`.
 * @param bins Number or a Pair of Numbers.
 *  Number of bins in both directions, vertical and horizontal. Overridden by `binWidth`.
 *  If only one value given - interpret it as list of two equal values.
 *  Applicable simultaneously for "tile" geom and "histogram" marginal.
 * @param binWidth Number or a Pair of Numbers.
 *  The width of the bins in both directions, vertical and horizontal.
 *  Overrides `bins`. The default is to use bin widths that cover the entire range of the data.
 *  If only one value given - interpret it as list of two equal values.
 *  Applicable simultaneously for "tile" geom and "histogram" marginal.
 * @param color Color of a geometry.
 * @param size Size of a geometry.
 * @param alpha Transparency level of a geometry. 
 *  Understands numbers between 0 and 1.
 * @param colorBy Name of grouping variable.
 * @param showLegend default = true.
 *  false - do not show legend the main layer.
 * @param hline default = true.
 *  false - do not show horizontal line passing through 0.
 * @param marginal default = "dens:r".
 *  Description of marginal layers packed to string value.
 *  Different marginals are separated by the ',' char.
 *  Parameters of a marginal are separated by the ':' char.
 *
 *  First parameter of a marginal is a geometry name.
 *  Possible values: "dens"/"density", "hist"/"histogram", "box"/"boxplot".
 *
 *  Second parameter is a string specifying which sides of the plot the marginal layer will appear on.
 *  Possible values: 't' (top), 'b' (bottom), 'l' (left), 'r' (right).
 *
 *  Third parameter (optional) is size of marginal.
 *  To suppress marginals use `marginal="none"`.
 *
 *  Examples:
 *  - "hist:tr:0.3",
 *  - "dens:tr,hist:bl",
 *  - "box:tr:.05, hist:bl, dens:bl".
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