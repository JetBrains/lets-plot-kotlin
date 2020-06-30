/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.datalore.plot.config.Option.Meta.MappingAnnotation


class MappingMeta(
    val variable: String,
    private val annotation: String,
    private val parameters: Map<String, Any> = emptyMap()
) {
    fun getAnnotatedData(aes: String): Map<String, Any> {
        return if (aes.isNotEmpty()) {
            mapOf(
                MappingAnnotation.TAG to listOf(
                    mapOf(
                        MappingAnnotation.AES to aes,
                        MappingAnnotation.ANNOTATION to annotation,
                        MappingAnnotation.PARAMETERS to parameters
                    )
                )
            )
        } else {
            emptyMap()
        }
    }
}

/**
 * Marks a numeric variable as categorical.
 * The plot will use a discrete scale for the aesthetic mapping.
 * It is similar to the factor() function from R but works differently - there is no data transformation.
 *
 * @param variable string
 *    The name of the variable
 * @param label string
 *    The name of the scale - used as the axis label or the legend title
 *
 */
@Suppress("FunctionName")
fun as_discrete(
    variable: String,
    label: String? = null
): MappingMeta {
    return MappingMeta(
        variable = variable,
        annotation = MappingAnnotation.AS_DISCRETE,
        parameters = mapOf(MappingAnnotation.LABEL to (label ?: variable))
    )
}