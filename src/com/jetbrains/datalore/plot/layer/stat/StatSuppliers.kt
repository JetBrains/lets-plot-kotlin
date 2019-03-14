package com.jetbrains.datalore.plot.layer.stat

import com.jetbrains.datalore.plot.Options
import com.jetbrains.datalore.plot.StatKind
import com.jetbrains.datalore.plot.layer.StatOptions

internal typealias StatSupplier = () -> StatOptions

val identity: StatSupplier = {
    StatOptions(
        StatKind.IDENTITY
    )
}

internal object StatSuppliers {
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

