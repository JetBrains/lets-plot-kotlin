package com.jetbrains.datalore.plot.dsl.geom

import com.jetbrains.datalore.plot.layer.LayerBase
import com.jetbrains.datalore.plot.layer.StatOptions
import com.jetbrains.datalore.plot.layer.geom.AreaAesthetics
import com.jetbrains.datalore.plot.layer.geom.AreaMapping
import com.jetbrains.datalore.plot.layer.stat.identity

@Suppress("ClassName")
class geom_area(
    mapping: AreaMapping.() -> Unit = {},
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
    override val linetype: Any? = null,
    override val size: Any? = null
    ) : AreaAesthetics,
    LayerBase(
        mapping = AreaMapping().apply(mapping).seal(),
        data = data,
        geom = area(),
        stat = stat,
        position = position,
        show_legend = show_legend,
        sampling = sampling
    )


