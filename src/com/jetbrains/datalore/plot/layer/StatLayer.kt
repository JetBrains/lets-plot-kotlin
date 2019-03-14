package com.jetbrains.datalore.plot.layer

import com.jetbrains.datalore.plot.Layer
import com.jetbrains.datalore.plot.Options

abstract class StatLayer(
    private val layerMapping: Options,
    data: Any? = null,
    override val geom: GeomOptions,
    private val statFactory: StatFactory,
    position: Any? = null,
    show_legend: Boolean = true,
    sampling: Any? = null
) : Layer(
    data = data,
    position = position,
    show_legend = show_legend,
    sampling = sampling
), FreezableOptions {
    override val stat: StatOptions by lazy {
        statFactory.invoke(layerMapping, toFrozen())
    }
}