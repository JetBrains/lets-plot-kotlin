/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.datalore.plot.config.Option.Meta.MappingAnnotation


class MappingMeta(
    val variable: String,
    private val annotation: String,
    private val parameters: Map<String, Any?> = emptyMap()
) {
    fun getAnnotatedData(aes: String): Map<String, Any> {
        return mapOf(
            MappingAnnotation.AES to aes,
            MappingAnnotation.ANNOTATION to annotation,
            MappingAnnotation.PARAMETERS to parameters
        )
    }
}

/**
 * Marks a numeric variable as categorical.
 * The plot will use a discrete scale for the aesthetic mapping.
 * It is similar to the factor() function from R but works differently - there is no data transformation.
 *
 * @param variable string
 *    The name of the variable.
 * @param label string, optional
 *    The name of the scale to be used as the axis label or the legend title (the default is the variable name).
 * @param orderBy string, optional
 *    The variable name by which the ordering will be performed.
 * @param order int, optional
 *    The ordering direction: 1 for ascending direction and -1 for descending.
 */
fun asDiscrete(
    variable: String,
    label: String? = null,
    orderBy: String? = null,
    order: Int? = null
): MappingMeta {
    return MappingMeta(
        variable = variable,
        annotation = MappingAnnotation.AS_DISCRETE,
        parameters = mapOf(
            MappingAnnotation.LABEL to (label ?: variable),
            MappingAnnotation.ORDER_BY to orderBy,
            MappingAnnotation.ORDER to order
        )
    )
}