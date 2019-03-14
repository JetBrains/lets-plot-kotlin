package com.jetbrains.datalore.plot.dsl.stat

import com.jetbrains.datalore.plot.layer.GeomOptions
import com.jetbrains.datalore.plot.layer.StatOptions
import com.jetbrains.datalore.plot.layer.geom.GeomSupplier
import com.jetbrains.datalore.plot.layer.geom.GeomSuppliers
import com.jetbrains.datalore.plot.layer.geom.PointMapping
import com.jetbrains.datalore.plot.layer.stat.DensityMapping
import com.jetbrains.datalore.plot.layer.stat.identity


@Suppress("ClassName")
class stat_density(
    mapping: DensityMapping.() -> Unit = {},
    data: Any? = null,
    geomSupplier: () -> GeomOptions = { GeomSuppliers.Area() }
    )
