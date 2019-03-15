package com.jetbrains.datalore.plot.layer

import com.jetbrains.datalore.plot.Layer
import com.jetbrains.datalore.plot.Options

abstract class StatLayer(
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
//    override val stat: StatOptions by lazy {
//        statFactory.invoke(layerMapping, toFrozen())
//    }
//    }

    override val parameters by lazy { geom.parameters + stat.parameters + this.toFrozen() }
}