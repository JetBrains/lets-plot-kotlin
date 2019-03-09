package com.jetbrains.datalore.plot.dsl

import com.jetbrains.datalore.plot.PointGeom

fun geom_point(
    mapping: PointGeom.MutableAesMapping.() -> Unit = {},
    data: Any? = null,
    stat: Any? = null,
    position: Any? = null,
    show_legend: Boolean = true,
    sampling: Any? = null
): PointGeom {
    return PointGeom(
        mapping = PointGeom.MutableAesMapping().apply(mapping).toFrozen(),
        data = data,
        stat = stat,
        position = position,
        show_legend = show_legend,
        sampling = sampling
    )
}

