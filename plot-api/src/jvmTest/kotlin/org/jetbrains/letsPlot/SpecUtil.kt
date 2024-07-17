package org.jetbrains.letsPlot

import org.jetbrains.letsPlot.core.plot.base.Aes
import org.jetbrains.letsPlot.core.spec.Option.Meta.MappingAnnotation
import org.jetbrains.letsPlot.core.spec.Option.Meta.SeriesAnnotation
import org.jetbrains.letsPlot.intern.filterNonNullValues

object SeriesUtil {
    fun seriesAnnotation(
        column: String,
        type: String? = null,
        factorLevels: List<Any>? = null,
        order: Int? = null
    ): Map<String, Any> {
        return mapOf(
            SeriesAnnotation.COLUMN to column,
            SeriesAnnotation.TYPE to type,
            SeriesAnnotation.FACTOR_LEVELS to factorLevels,
            SeriesAnnotation.ORDER to order
        ).filterNonNullValues()
    }

    fun mappingAsDiscreteAnnotation(
        aes: Aes<*>,
        label: String,
        order: Int? = null,
        orderBy: String? = null
    ): Map<String, Any> {
        return mapOf(
            MappingAnnotation.AES to aes.name,
            MappingAnnotation.ANNOTATION to MappingAnnotation.AS_DISCRETE,
            MappingAnnotation.PARAMETERS to mapOf(
                MappingAnnotation.LABEL to label,
                MappingAnnotation.ORDER_BY to orderBy,
                MappingAnnotation.ORDER to order
            ).filterNonNullValues()
        ).filterNonNullValues()
    }
}
