package com.jetbrains.datalore.plot.dsl.stat

import com.jetbrains.datalore.plot.Options
import com.jetbrains.datalore.plot.StatKind.DENSITY
import com.jetbrains.datalore.plot.mapping.stat.DensityMapping
import com.jetbrains.datalore.plot.stat.StatOptions
import com.jetbrains.datalore.plot.stat.StatSupplier

@Suppress("ClassName")
class density(
    private val mapping: DensityMapping.() -> Unit = {},
    val bw: Any? = null,
    val kernel: Any? = null,
    val n: Any? = null,
    val trim: Any? = null
) : StatSupplier {
    override fun invoke(): StatOptions {
        return StatOptions(
            DENSITY,
            mapping = DensityMapping().apply(mapping).toFrozen(),
            parameters = Options(
                mapOf(
                    "bw" to bw,
                    "kernel" to kernel,
                    "n" to n,
                    "trim" to trim
                )
            )
        )
    }
}