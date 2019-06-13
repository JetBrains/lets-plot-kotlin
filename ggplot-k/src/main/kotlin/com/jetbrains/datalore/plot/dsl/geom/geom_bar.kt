package com.jetbrains.datalore.plot.dsl.geom

import com.jetbrains.datalore.plot.dsl.Geom
import com.jetbrains.datalore.plot.dsl.Pos
import com.jetbrains.datalore.plot.dsl.Stat
import com.jetbrains.datalore.plot.layer.LayerBase
import com.jetbrains.datalore.plot.layer.PosOptions
import com.jetbrains.datalore.plot.layer.StatOptions
import com.jetbrains.datalore.plot.layer.geom.BarAesthetics
import com.jetbrains.datalore.plot.layer.geom.BarMapping

@Suppress("ClassName")
class geom_bar(
    mapping: BarMapping.() -> Unit = {},
    data: Any? = null,
    stat: StatOptions = Stat.count(),
    position: PosOptions = Pos.stack,
    show_legend: Boolean = true,
    sampling: Any? = null,
    override val x: Any? = null,
    override val y: Any? = null,
    override val alpha: Any? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val width: Any? = null,
    override val size: Any? = null
) : BarAesthetics,
    LayerBase(
        mapping = BarMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.bar(),
        stat = stat,
        position = position,
        show_legend = show_legend,
        sampling = sampling
    )


