package com.jetbrains.datalore.plot

open class Options(private val map: Map<String, Any?>) {
    private val byName: Map<String, Any>

    init {
        val tmp = mutableMapOf<String, Any>()
        for ((k, v) in map) {
            if (v != null) {
                tmp[k] = v
            }
        }
        byName = tmp
    }

    fun has(k: String): Boolean {
        return byName.containsKey(k)
    }

    fun get(k: String): Any {
        return byName[k] ?: throw IllegalArgumentException("No such option: '$k'")
    }
}
