package com.jetbrains.datalore.plot.geom

import com.jetbrains.datalore.plot.Layer
import com.jetbrains.datalore.plot.Options

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