package com.jetbrains.datalore.plot


class PointGeom(
    val mapping: AesMapping? = null,
    val data: Any? = null,
    val stat: Any? = null,
    val position: Any? = null,
    val show_legend: Boolean = true,
    val sampling: Any? = null

) : Layer() {

    class MutableAesMapping(
        var x: Any? = null,
        var y: Any? = null,
        var alpha: Any? = null,
        var colour: Any? = null,
        var fill: Any? = null,
        var group: Any? = null,
        var shape: Any? = null,
        var size: Any? = null,
        var stroke: Any? = null
    ) {
        fun toFrozen() = AesMapping(x, y, alpha, colour, fill, group, shape, size, stroke)
    }

    class AesMapping(
        val x: Any?,
        val y: Any?,
        val alpha: Any?,
        val colour: Any?,
        val fill: Any?,
        val group: Any?,
        val shape: Any?,
        val size: Any?,
        val stroke: Any?
    ) : Options<AesMapping>()
}

