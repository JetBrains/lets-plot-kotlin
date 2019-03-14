package com.jetbrains.datalore.plot.dsl.geom

import com.jetbrains.datalore.plot.layer.GeomLayer
import com.jetbrains.datalore.plot.layer.StatOptions
import com.jetbrains.datalore.plot.layer.geom.PointAesthetics
import com.jetbrains.datalore.plot.layer.geom.PointMapping
import com.jetbrains.datalore.plot.layer.stat.identity

@Suppress("ClassName")
class geom_point(
    mapping: PointMapping.() -> Unit = {},
    data: Any? = null,
    stat: () -> StatOptions = { identity },
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
    GeomLayer(
        layerMapping = PointMapping().apply(mapping).toFrozen(),
        data = data,
        statSupplier = stat,
        position = position,
        show_legend = show_legend,
        sampling = sampling
    ) {
}


