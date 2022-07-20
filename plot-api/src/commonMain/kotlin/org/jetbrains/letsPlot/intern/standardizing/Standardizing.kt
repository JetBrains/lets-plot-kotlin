/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.standardizing

import jetbrains.datalore.base.datetime.Instant

internal object Standardizing {
    fun standardizeValue(value: Any?): Any? {
        return when (value) {
            null -> value
            is String -> value
            is Number -> toDouble(value)
            is Char -> value.toString()
            is jetbrains.datalore.base.values.Color -> value.toHexColor()
            is Map<*, *> -> MapStandardizing.standardize(value)
            is Enum<*> -> value.name
            is Instant -> value.timeSinceEpoch
            else -> {
                if (JvmStandardizing.isJvm(value)) {
                    JvmStandardizing.standardize(value)
                } else if (SeriesStandardizing.isListy(value)) {
                    val l = SeriesStandardizing.toList(value)
                    l.map { standardizeValue(it) }
                } else {
//                    throw IllegalArgumentException(
//                        "Can't standardize the value \"$value\" of type ${
//                            ReflectionPatch.qualifiedName(value)
//                        } as a string, number or date-time."
//                    )
                    // Just ignore: this might be some ligit object like `org.jetbrains.letsPlot.MappingMeta` for example.
                    value
                }
            }
        }
    }

    private fun toDouble(n: Number): Double? {
        return when (n) {
            is Float -> if (n.isFinite()) n.toDouble() else null
            is Double -> if (n.isFinite()) n else null
            else -> n.toDouble()
        }
    }

}