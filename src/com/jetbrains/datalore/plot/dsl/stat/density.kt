package com.jetbrains.datalore.plot.dsl.stat

import com.jetbrains.datalore.plot.StatKind.DENSITY
import com.jetbrains.datalore.plot.layer.stat.DensityMapping
import com.jetbrains.datalore.plot.layer.stat.DensityParameters
import com.jetbrains.datalore.plot.layer.StatOptions
import com.jetbrains.datalore.plot.layer.stat.StatSupplier

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