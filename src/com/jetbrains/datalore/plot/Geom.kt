package com.jetbrains.datalore.plot


class PointGeom(
    mapping: Options,
    data: Any? = null,
    stat: Any? = null,
    position: Any? = null,
    show_legend: Boolean = true,
    sampling: Any? = null

) : Layer(
    mapping = mapping,
    data = data,
    stat = stat,
    position = position,
    show_legend = show_legend,
    sampling = sampling
) {

    class AesMapping(
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
        fun toFrozen() = Options(
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
}


