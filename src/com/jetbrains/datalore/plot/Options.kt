package com.jetbrains.datalore.plot

open class Options(private val map: Map<String, Any?>) {
    private val optionsMap: Map<String, Any>

    init {
        val tmp = mutableMapOf<String, Any>()
        for ((k, v) in map) {
            if (v != null) {
                tmp[k] = v
            }
        }
        optionsMap = tmp
    }

    fun has(k: String): Boolean {
        return optionsMap.containsKey(k)
    }

    fun get(k: String): Any {
        return optionsMap[k] ?: throw IllegalArgumentException("No such option: '$k'")
    }
}
