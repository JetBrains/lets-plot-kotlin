package com.jetbrains.datalore.plot.dsl.stat

import com.jetbrains.datalore.plot.layer.geom.GeomSupplier
import com.jetbrains.datalore.plot.layer.geom.Geoms
import com.jetbrains.datalore.plot.layer.stat.DensityMapping


@Suppress("ClassName")
class stat_density(
    mapping: DensityMapping.() -> Unit = {},
    data: Any? = null,
    geomSupplier: GeomSupplier = { Geoms.Area() }
)
