/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.standardizing

internal object SeriesStandardizing {
    @Suppress("SpellCheckingInspection")
    fun isListy(rawValue: Any?) = when (rawValue) {
        is List<*> -> true
        is Iterable<*> -> true
        is Sequence<*> -> true
        is Array<*> -> true
        is ByteArray -> true
        is ShortArray -> true
        is IntArray -> true
        is LongArray -> true
        is FloatArray -> true
        is DoubleArray -> true
        is CharArray -> true
        is Pair<*, *> -> true
        else -> false
    }

    fun asList(rawValue: Any, messageKey: String? = null): List<Any?> {
        return when (rawValue) {
            is List<*> -> rawValue
            is Iterable<*> -> rawValue.toList()
            is Sequence<*> -> rawValue.toList()
            is Array<*> -> rawValue.asList()
            is ByteArray -> rawValue.asList()
            is ShortArray -> rawValue.asList()
            is IntArray -> rawValue.asList()
            is LongArray -> rawValue.asList()
            is FloatArray -> rawValue.asList()
            is DoubleArray -> rawValue.asList()
            is CharArray -> rawValue.asList()
            is Pair<*, *> -> rawValue.toList()
            else -> {
                val keyInfo = messageKey?.let { "[$messageKey]" } ?: ""
                throw IllegalArgumentException("Can't transform ${rawValue::class.simpleName} to list$keyInfo.")
            }
        }
    }

    fun toList(rawValue: Any, messageKey: String? = null): List<Any?> {
        return standardizeList(asList(rawValue, messageKey))
    }

    private fun needToStandardizeValues(series: Iterable<*>): Boolean {
        return series.any {
            it != null &&
                    (!(it is String || it is Double) ||
                            it is Double && !it.isFinite())
        }
    }

    private fun standardizeList(series: List<*>): List<*> {
        // avoid 'toList' on lists (makes copy)
        return standardizeIterable(series) as List<*>
    }

    private fun standardizeIterable(series: Iterable<*>): Iterable<*> {
        return if (needToStandardizeValues(series)) {
            series.map {
                Standardizing.standardizeValue(it)
            }
        } else {
            series
        }
    }
}