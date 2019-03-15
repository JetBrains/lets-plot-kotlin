package com.jetbrains.datalore.plot.layer

import com.jetbrains.datalore.plot.Layer
import com.jetbrains.datalore.plot.Options

abstract class GeomLayer(
    mapping: Options,
    data: Any? = null,
    geom: GeomOptions,
    stat: StatOptions,
    position: Any? = null,
    show_legend: Boolean = true,
    sampling: Any? = null
) : Layer(
    mapping = mapping,
    data = data,
    geom = geom,
    stat = stat,
    position = position,
    show_legend = show_legend,
    sampling = sampling
), FreezableOptions {

    //    override val geom: GeomOptions by lazy {
//        geomFactory.invoke(layerMapping, toFrozen())
//    }
    override val parameters by lazy { geom.parameters + stat.parameters + this.toFrozen() }
}