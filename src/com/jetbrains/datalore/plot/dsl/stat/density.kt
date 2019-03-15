package com.jetbrains.datalore.plot.dsl.stat

import com.jetbrains.datalore.plot.StatKind
import com.jetbrains.datalore.plot.layer.StatOptions
import com.jetbrains.datalore.plot.layer.stat.DensityMapping
import com.jetbrains.datalore.plot.layer.stat.DensityParameters

@Suppress("ClassName")
class density(
    mapping: DensityMapping.() -> Unit = {},
    override val bw: Any? = null,
    override val kernel: Any? = null,
    override val n: Any? = null,
    override val trim: Any? = null
) : DensityParameters,
    StatOptions() {
    override val kind = StatKind.DENSITY
    override val mapping = DensityMapping().apply(mapping).toFrozen()
    override val parameters = this.toFrozen()

//    override fun invoke(): StatOptions {
//        return Stats.Density(
//            mapping = DensityMapping().apply(mapping).toFrozen(),
//            parameters = this.toFrozen()
//        )
//    }
}