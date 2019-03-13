package com.jetbrains.datalore.plot.stat

import com.jetbrains.datalore.plot.Options
import com.jetbrains.datalore.plot.StatKind

internal typealias StatSupplier = () -> StatOptions

val identity: StatSupplier = { StatOptions(StatKind.IDENTITY) }

internal object GeomSuppliers {
    internal fun density(
        mapping: Options,
        parameters: Options
    ) = {
        StatOptions(
            StatKind.DENSITY,
            mapping = mapping,
            parameters = parameters
        )
    }
}

