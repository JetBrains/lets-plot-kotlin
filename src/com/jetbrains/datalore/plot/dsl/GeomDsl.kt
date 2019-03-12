package com.jetbrains.datalore.plot.dsl

import com.jetbrains.datalore.plot.*

@Suppress("ClassName")
class geom_point(
    mapping: PointMapping.() -> Unit = {},
    data: Any? = null,
    stat: Any? = null,
    position: Any? = null,
    show_legend: Boolean = true,
    sampling: Any? = null,
    override val x: Any? = null,
    override val y: Any? = null,
    override val alpha: Any? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val group: Any? = null,
    override val shape: Any? = null,
    override val size: Any? = null,
    override val stroke: Any? = null
) : PointAesthetics,
    GeomLayer(
        layerMapping = PointMapping().apply(mapping).toFrozen(),
        data = data,
        stat = stat,
        position = position,
        show_legend = show_legend,
        sampling = sampling
    ) {
}


abstract class GeomLayer(
    private val layerMapping: Options,
    data: Any? = null,
    stat: Any? = null,
    position: Any? = null,
    show_legend: Boolean = true,
    sampling: Any? = null
) : Layer(
    data = data,
    stat = stat,
    position = position,
    show_legend = show_legend,
    sampling = sampling
), GeomAesthetics {

    override val geom: GeomOptions by lazy() {
        GeomSuppliers.point(layerMapping, constants = toFrozen()).invoke()
    }
}