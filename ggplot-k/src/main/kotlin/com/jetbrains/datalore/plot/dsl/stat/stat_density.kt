package com.jetbrains.datalore.plot.dsl.stat

import com.jetbrains.datalore.plot.Options
import com.jetbrains.datalore.plot.dsl.Geom.area
import com.jetbrains.datalore.plot.dsl.Pos.stack
import com.jetbrains.datalore.plot.dsl.Stat.density
import com.jetbrains.datalore.plot.layer.GeomOptions
import com.jetbrains.datalore.plot.layer.LayerBase
import com.jetbrains.datalore.plot.layer.PosOptions
import com.jetbrains.datalore.plot.layer.stat.DensityAesthetics
import com.jetbrains.datalore.plot.layer.stat.DensityMapping
import com.jetbrains.datalore.plot.layer.stat.DensityParameters

@Suppress("ClassName")
class stat_density(
    data: Any? = null,
    geom: GeomOptions = area(),
    position: PosOptions = stack,
    show_legend: Boolean = true,
    sampling: Any? = null,
    override val x: Any? = null,
    override val y: Any? = null,
    override val alpha: Any? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val size: Any? = null,
    override val weight: Any? = null,
    override val bw: Any? = null,
    override val kernel: Any? = null,
    override val n: Any? = null,
    override val trim: Any? = null,
    mapping: DensityMapping.() -> Unit = {}

) : DensityAesthetics, DensityParameters,
    LayerBase(
        mapping = DensityMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = density(),
        position = position,
        show_legend = show_legend,
        sampling = sampling

    ) {

    override fun seal(): Options {
        return super<DensityAesthetics>.seal() +
                super<DensityParameters>.seal()
    }
}

