package com.jetbrains.datalore.plot.layer

import com.jetbrains.datalore.plot.Layer
import com.jetbrains.datalore.plot.Options
import com.jetbrains.datalore.plot.layer.geom.GeomSuppliers

abstract class GeomLayer(
    private val layerMapping: Options,
    data: Any? = null,
    private val statSupplier: () -> StatOptions,
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
        GeomSuppliers.point(layerMapping, constants = toFrozen()).invoke()
    }

    override val stat: StatOptions by lazy {
        statSupplier.invoke()
    }
}