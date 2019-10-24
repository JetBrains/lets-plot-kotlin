package jetbrains.datalorePlot.intern

class Options(map: Map<String, Any?>) {
    companion object {
        fun of(pair: Pair<String, Any?>) = Options(mapOf(pair))
        fun of(vararg pairs: Pair<String, Any?>) = Options(mapOf(*pairs))

        fun empty() = Options(emptyMap())
    }

    internal val map: Map<String, Any>

    init {
        val tmp = mutableMapOf<String, Any>()
        for ((k, v) in map) {
            if (v != null) {
                tmp[k] = v
            }
        }
        this.map = tmp
    }

    operator fun plus(other: Options) = union(other)

    fun has(k: String): Boolean {
        return map.containsKey(k)
    }

    fun get(k: String): Any {
        return map[k] ?: throw IllegalArgumentException("No such option: '$k'")
    }

    fun isEmpty() = map.isEmpty()

    fun union(other: Options) = Options(this.map + other.map)
}
