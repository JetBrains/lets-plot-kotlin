/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import org.jetbrains.letsPlot.core.spec.Option.Meta.MappingAnnotation


/**
 * @suppress
 */
class MappingMeta(
    val variable: String,
    val annotation: String,
    val label: String,
    val orderBy: String?,
    val order: Int?,
    val levels: List<Any>?,
) {
    private val parameters = mapOf(
        MappingAnnotation.LABEL to label,
        MappingAnnotation.ORDER_BY to orderBy,
        MappingAnnotation.ORDER to order
    )
}

/**
 * Marks a numeric variable as categorical.
 * The plot will use a discrete scale for the aesthetic mapping.
 * It is similar to the `factor()` function from R but works differently - there is no data transformation.
 *
 * See also [as_discrete.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/as_discrete.md).
 *
 * ## Examples
 *
 * - [ordering_examples.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/ordering_examples.ipynb)
 *
 * - [geom_smooth.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_smooth.ipynb)
 *
 * - [factor_levels.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.6.0/factor_levels.ipynb)
 *
 *
 * @param variable Name of the variable.
 * @param label Name of the scale to be used as the axis label or the legend title (the default is the variable name).
 * @param orderBy The variable name by which the ordering will be performed.
 * @param order Ordering direction: 1 for ascending direction and -1 for descending.
 * @param levels The list of values that defines a specific order of categories.
 */
fun asDiscrete(
    variable: String,
    label: String? = null,
    orderBy: String? = null,
    order: Int? = null,
    levels: List<Any>? = null,
) = MappingMeta(
    variable,
    annotation = MappingAnnotation.AS_DISCRETE,
    label = label ?: variable,
    orderBy = orderBy,
    order = order,
    levels = levels
)