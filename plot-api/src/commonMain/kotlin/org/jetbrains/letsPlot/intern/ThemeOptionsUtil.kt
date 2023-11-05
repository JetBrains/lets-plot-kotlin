package org.jetbrains.letsPlot.intern

import org.jetbrains.letsPlot.core.plot.builder.defaultTheme.values.ThemeOption
import org.jetbrains.letsPlot.core.spec.Option

internal object ThemeOptionsUtil {
    fun toSpec(themeOptionList: List<OptionsMap>): Map<String, Any>? {
        val specList = themeOptionList.map { it.toSpec() }

        val result: Map<String, Any>? = specList.reduceOrNull() { prev, next ->
            val merged = next[Option.Meta.NAME]?.let {
                // keep the previously specified flavor
                val flavor = prev.filterKeys { it == ThemeOption.FLAVOR }

                // 'named' theme overrides all prev theme options.
                next + flavor
            } ?: mergeThemeOptions(prev, next)

            merged.toMutableMap()
        }
        return result
    }

    private fun mergeThemeOptions(m0: Map<String, Any>, m1: Map<String, Any>): Map<String, Any> {
        val overlappingKeys = m0.keys.intersect(m1.keys)
        val keysToMerge = overlappingKeys.filter {
            m0[it] is Map<*, *> && m1[it] is Map<*, *>
        }
        val m2 = keysToMerge.map {
            it to (m0[it] as Map<*, *> + m1[it] as Map<*, *>)
        }.toMap()
        return m0 + m1 + m2
    }
}