package com.jetbrains.datalore.plot.dsl.stat

import com.jetbrains.datalore.plot.Options
import com.jetbrains.datalore.plot.layer.StatFactory
import com.jetbrains.datalore.plot.layer.StatLayer
import com.jetbrains.datalore.plot.layer.geom.GeomSupplier
import com.jetbrains.datalore.plot.layer.geom.Geoms
import com.jetbrains.datalore.plot.layer.stat.DensityAesthetics
import com.jetbrains.datalore.plot.layer.stat.DensityMapping
import com.jetbrains.datalore.plot.layer.stat.DensityParameters
import com.jetbrains.datalore.plot.layer.stat.Stats

private var FACTORY: StatFactory = { m, p -> Stats.Density(m, p) }

@Suppress("ClassName")
class stat_density(
    mapping: DensityMapping.() -> Unit = {},
    data: Any? = null,
    geomSupplier: GeomSupplier = { Geoms.Area() },
    position: Any? = null,
    show_legend: Boolean = true,
    sampling: Any? = null,
    override val x: Any?,
    override val y: Any?,
    override val alpha: Any?,
    override val color: Any?,
    override val fill: Any?,
    override val linetype: Any?,
    override val size: Any?,
    override val weight: Any?,
    override val bw: Any?,
    override val kernel: Any?,
    override val n: Any?,
    override val trim: Any?

) : DensityAesthetics, DensityParameters,
    StatLayer(
        layerMapping = DensityMapping().apply(mapping).toFrozen(),
        data = data,
        geom = geomSupplier.invoke(),
        statFactory = FACTORY,
        position = position,
        show_legend = show_legend,
        sampling = sampling

    ) {

    override fun toFrozen(): Options {
        return super<DensityAesthetics>.toFrozen() +
                super<DensityParameters>.toFrozen()
    }
}

