package com.jetbrains.datalore.plot

class Options(map: Map<String, Any?>) {
    companion object {
        fun of(pair: Pair<String, Any?>) = Options(mapOf(pair))
        fun of(vararg pairs: Pair<String, Any?>) = Options(mapOf(*pairs))

        fun empty() = Options(emptyMap())
    }

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

    operator fun plus(other: Options) = union(other)

    fun has(k: String): Boolean {
        return byName.containsKey(k)
    }

    fun get(k: String): Any {
        return byName[k] ?: throw IllegalArgumentException("No such option: '$k'")
    }

    fun isEmpty() = byName.isEmpty()
    fun union(other: Options) = Options(this.byName + other.byName)
}
