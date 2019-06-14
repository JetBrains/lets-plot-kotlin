package com.jetbrains.datalore.plot.dsl.geom

import com.jetbrains.datalore.plot.dsl.Geom.point
import com.jetbrains.datalore.plot.dsl.Pos.identity
import com.jetbrains.datalore.plot.dsl.Stat
import com.jetbrains.datalore.plot.layer.LayerBase
import com.jetbrains.datalore.plot.layer.PosOptions
import com.jetbrains.datalore.plot.layer.StatOptions
import com.jetbrains.datalore.plot.layer.geom.PointAesthetics
import com.jetbrains.datalore.plot.layer.geom.PointMapping

@Suppress("ClassName")
class geom_point(
    data: Any? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = identity,
    show_legend: Boolean = true,
    sampling: Any? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val alpha: Double? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val shape: Any? = null,
    override val size: Double? = null,
    override val stroke: Double? = null,
    mapping: PointMapping.() -> Unit = {}

) : PointAesthetics,
    LayerBase(
        mapping = PointMapping().apply(mapping).seal(),
        data = data,
        geom = point(),
        stat = stat,
        position = position,
        show_legend = show_legend,
        sampling = sampling
    )


