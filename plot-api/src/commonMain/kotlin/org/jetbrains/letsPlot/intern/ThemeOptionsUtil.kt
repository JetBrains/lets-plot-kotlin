package org.jetbrains.letsPlot.intern

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.OptionsUtil.mergeOptions

internal object ThemeOptionsUtil {
    fun toSpec(themeOptionList: List<OptionsMap>): Map<String, Any>? {
        val specList = themeOptionList.map(OptionsMap::toSpec)

        val result: Map<String, Any>? = specList.reduceOrNull() { prev, next ->
            val merged = next[Option.Meta.NAME]?.let {
                // keep the previously specified flavor
                val flavor = prev.filterKeys { it == Option.Theme.FLAVOR }

                // 'named' theme overrides all prev theme options.
                next + flavor
            } ?: mergeOptions(prev, next)

            merged.toMutableMap()
        }
        return result
    }
}