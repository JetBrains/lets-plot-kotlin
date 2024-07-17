package org.jetbrains.letsPlot.intern

internal object OptionsUtil {
    fun toSpec(optionList: List<OptionsMap>): Map<String, Any>? {
        return optionList
            .map(OptionsMap::toSpec)
            .reduceOrNull { prev, next -> mergeOptions(prev, next).toMutableMap() }
    }

    fun mergeOptions(m0: Map<String, Any>, m1: Map<String, Any>): Map<String, Any> {
        val overlappingKeys = m0.keys.intersect(m1.keys)
        val keysToMerge = overlappingKeys.filter {
            m0[it] is Map<*, *> && m1[it] is Map<*, *>
        }
        val m2 = keysToMerge.associateWith {
            (m0[it] as Map<*, *> + m1[it] as Map<*, *>)
        }
        return m0 + m1 + m2
    }
}