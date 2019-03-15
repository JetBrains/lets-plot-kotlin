package com.jetbrains.datalore.plot.dsl.geom

import com.jetbrains.datalore.plot.layer.LayerBase
import com.jetbrains.datalore.plot.layer.StatOptions
import com.jetbrains.datalore.plot.layer.geom.PointAesthetics
import com.jetbrains.datalore.plot.layer.geom.PointMapping
import com.jetbrains.datalore.plot.layer.stat.identity

@Suppress("ClassName")
class geom_point(
    mapping: PointMapping.() -> Unit = {},
    data: Any? = null,
    stat: StatOptions = identity,
    position: Any? = null,
    show_legend: Boolean = true,
    sampling: Any? = null,
    override val x: Any? = null,
    override val y: Any? = null,
    override val alpha: Any? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val shape: Any? = null,
    override val size: Any? = null,
    override val stroke: Any? = null
) : PointAesthetics,
    LayerBase(
        mapping = PointMapping().apply(mapping).seal(),
        data = data,
        geom = point(),
        stat = stat,
        position = position,
        show_legend = show_legend,
        sampling = sampling
    )


