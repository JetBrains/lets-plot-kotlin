package com.jetbrains.datalore.plot.dsl

import com.jetbrains.datalore.plot.Layer
import com.jetbrains.datalore.plot.PointAesthetics
import com.jetbrains.datalore.plot.PointMapping
import com.jetbrains.datalore.plot.point

@Suppress("ClassName")
class geom_point(
    mapping: PointMapping.() -> Unit = {},
    data: Any? = null,
    stat: Any? = null,
    position: Any? = null,
    show_legend: Boolean = true,
    sampling: Any? = null,
    override var x: Any? = null,
    override var y: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var group: Any? = null,
    override var shape: Any? = null,
    override var size: Any? = null,
    override var stroke: Any? = null
) : PointAesthetics,
    Layer(
    mapping = PointMapping().apply(mapping).toFrozen(),
    data = data,
    geom = point(),
            stat = stat,
    position = position,
    show_legend = show_legend,
    sampling = sampling
)

