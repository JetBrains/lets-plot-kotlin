package com.jetbrains.datalore.plot.layer

import com.jetbrains.datalore.plot.Layer
import com.jetbrains.datalore.plot.Options

abstract class GeomLayer(
    private val layerMapping: Options,
    data: Any? = null,
    private val geomFactory: (mapping: Options, constants: Options) -> GeomOptions,
    override val stat: StatOptions,
    position: Any? = null,
    show_legend: Boolean = true,
    sampling: Any? = null
) : Layer(
    data = data,
    position = position,
    show_legend = show_legend,
    sampling = sampling
), FreezableOptions {

    override val geom: GeomOptions by lazy {
        geomFactory.invoke(layerMapping, toFrozen())
    }
}