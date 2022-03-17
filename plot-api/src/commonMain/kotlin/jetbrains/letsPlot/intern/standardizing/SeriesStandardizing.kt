/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.standardizing

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
       /* return when (rawValue) {
            is List<*> -> standardizeList(rawValue)
            is Iterable<*> -> standardizeIterable(rawValue).toList()
            is Sequence<*> -> standardizeIterable(rawValue.asIterable()).toList()
            is Array<*> -> standardizeList(rawValue.asList())
            is ByteArray -> standardizeList(rawValue.asList())
            is ShortArray -> standardizeList(rawValue.asList())
            is IntArray -> standardizeList(rawValue.asList())
            is LongArray -> standardizeList(rawValue.asList())
            is FloatArray -> standardizeList(rawValue.asList())
            is DoubleArray -> standardizeList(rawValue.asList())
            is CharArray -> standardizeList(rawValue.asList())
            is Pair<*, *> -> standardizeList(rawValue.toList())
            else -> {
                val keyInfo = messageKey?.let { "[$messageKey]" } ?: ""
                throw IllegalArgumentException("Can't transform ${rawValue::class.simpleName} to list$keyInfo.")
            }
        }*/
    }

//    fun toListOrPass(rawValue: Any): Any {
//        if (isListy(rawValue)) {
//            return toList("<key not provided>", rawValue)
//        }
//        return rawValue
//    }

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
//        fun toDouble(n: Number): Double? {
//            return when (n) {
//                is Float -> if (n.isFinite()) n.toDouble() else null
//                is Double -> if (n.isFinite()) n else null
//                else -> n.toDouble()
//            }
//        }
        return if (needToStandardizeValues(series)) {
            series.map {
//                when (it) {
//                    null -> it
//                    is String -> it
//                    is Number -> toDouble(it)
//                    is Char -> it.toString()
//                    is jetbrains.datalore.base.values.Color -> it.toHexColor()
//                    else -> {
//                        if (JvmStandardizing.isJvm(it)) {
//                            JvmStandardizing.standardize(it)
//                        } else {
//                            throw IllegalArgumentException(
//                                "Can't standardize the value \"$it\" of type ${
//                                    ReflectionPatch.qualifiedName(
//                                        it
//                                    )
//                                } as a string, number or date-time."
//                            )
//                        }
//                    }
//                }
                Standardizing.standardizeValue(it)
            }
        } else {
            series
        }
    }
}