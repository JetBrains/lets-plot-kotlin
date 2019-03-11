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
        var color: Any? = null,
        var fill: Any? = null,
        var group: Any? = null,
        var shape: Any? = null,
        var size: Any? = null,
        var stroke: Any? = null
    ) {
        fun toFrozen() = AesMapping(
            mapOf(
                "x" to x,
                "y" to y,
                "alpha" to alpha,
                "color" to color,
                "fill" to fill,
                "group" to group,
                "shape" to shape,
                "size" to size,
                "stroke" to stroke
            )
        )
    }

    class AesMapping(map: Map<String, Any?>) : Options(map)
}


