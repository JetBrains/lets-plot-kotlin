package com.jetbrains.datalore.plot

interface GeomAesthetics {
   fun toFrozen(): Options
}

interface PointAesthetics: GeomAesthetics {
    val x: Any?
    val y: Any?
    val alpha: Any?
    val color: Any?
    val fill: Any?
    val group: Any?
    val shape: Any?
    val size: Any?
    val stroke: Any?

    override fun toFrozen() = Options(
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

class PointMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var group: Any? = null,
    override var shape: Any? = null,
    override var size: Any? = null,
    override var stroke: Any? = null
) : PointAesthetics {
}


