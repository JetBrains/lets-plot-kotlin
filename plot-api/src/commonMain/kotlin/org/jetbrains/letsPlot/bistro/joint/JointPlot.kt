/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.bistro.joint

/**
 * Produces a joint plot that contains bivariate and univariate graphs at the same time.
 *
 * ## Examples
 *
 * - [joint_plot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/joint_plot.ipynb)
 *
 * @param data The data to be displayed.
 * @param x Name of the variable.
 * @param y Name of the variable.
 * @param geom default = "point" ("point", "tile", "density2d", "density2df", "none").
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
 *  String in the following formats: 
 *  - RGB/RGBS (e.g. "rgb(0, 0, 255)")
 *  - HEX (e.g. "#0000FF")
 *  - color name (e.g. "red") 
 *
 *  Or an instance of the `java.awt.Color` class.
 * @param size Size of a geometry.
 * @param alpha Transparency level of a geometry.
 *  Understands numbers between 0 and 1.
 * @param colorBy Name of grouping variable.
 * @param showLegend default = true.
 *  false - do not show legend the main layer.
 * @param regLine If true - show the line of linear regression.
 * @param se default = true.
 *  Displays confidence interval around regression line.
 * @param marginal Description of marginal layers packed to string value.
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
fun jointPlot(
    data: Map<*, *>,
    x: String,
    y: String,
    geom: String? = null,
    bins: Any? = null,
    binWidth: Any? = null,
    color: String? = null,
    size: Number? = null,
    alpha: Number? = null,
    colorBy: String? = null,
    showLegend: Boolean = true,
    regLine: Boolean? = null,
    se: Boolean = true,
    marginal: String? = null
) = JointPlotBuilder(
    data,
    x,
    y,
    geom,
    bins,
    binWidth,
    color,
    size,
    alpha,
    colorBy,
    showLegend,
    regLine,
    se,
    marginal
).build()