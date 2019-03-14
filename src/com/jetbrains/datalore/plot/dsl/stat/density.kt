package com.jetbrains.datalore.plot.dsl.stat

import com.jetbrains.datalore.plot.StatKind.DENSITY
import com.jetbrains.datalore.plot.mapping.stat.DensityMapping
import com.jetbrains.datalore.plot.mapping.stat.DensityParameters
import com.jetbrains.datalore.plot.stat.StatOptions
import com.jetbrains.datalore.plot.stat.StatSupplier

@Suppress("ClassName")
class density(
    private val mapping: DensityMapping.() -> Unit = {},
    override val bw: Any? = null,
    override val kernel: Any? = null,
    override val n: Any? = null,
    override val trim: Any? = null
) : DensityParameters, StatSupplier {
    override fun invoke(): StatOptions {
        return StatOptions(
            DENSITY,
            mapping = DensityMapping().apply(mapping).toFrozen(),
            parameters = this.toFrozen()
        )
    }
}