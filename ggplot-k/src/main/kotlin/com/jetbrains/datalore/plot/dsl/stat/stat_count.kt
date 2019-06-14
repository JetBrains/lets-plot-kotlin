package com.jetbrains.datalore.plot.dsl.stat

import com.jetbrains.datalore.plot.Options
import com.jetbrains.datalore.plot.dsl.Geom
import com.jetbrains.datalore.plot.dsl.Pos.stack
import com.jetbrains.datalore.plot.dsl.Stat
import com.jetbrains.datalore.plot.layer.GeomOptions
import com.jetbrains.datalore.plot.layer.LayerBase
import com.jetbrains.datalore.plot.layer.PosOptions
import com.jetbrains.datalore.plot.layer.stat.CountAesthetics
import com.jetbrains.datalore.plot.layer.stat.CountMapping
import com.jetbrains.datalore.plot.layer.stat.CountParameters

@Suppress("ClassName")
class stat_count(
    data: Any? = null,
    geom: GeomOptions = Geom.bar(),
    position: PosOptions = stack,
    show_legend: Boolean = true,
    sampling: Any? = null,
    override val x: Any? = null,
    override val y: Any? = null,
    override val alpha: Any? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val width: Any? = null,
    override val size: Any? = null,
    override val weight: Any? = null,
    mapping: CountMapping.() -> Unit = {}

) : CountAesthetics, CountParameters,
    LayerBase(
        mapping = CountMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.count(),
        position = position,
        show_legend = show_legend,
        sampling = sampling

    ) {

    override fun seal(): Options {
        return super<CountAesthetics>.seal() +
                super<CountParameters>.seal()
    }
}

