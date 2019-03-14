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
    geom: GeomSupplier = { Geoms.Area() },
    position: Any? = null,
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
    override val trim: Any? = null

) : DensityAesthetics, DensityParameters,
    StatLayer(
        layerMapping = DensityMapping().apply(mapping).toFrozen(),
        data = data,
        geom = geom.invoke(),
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

