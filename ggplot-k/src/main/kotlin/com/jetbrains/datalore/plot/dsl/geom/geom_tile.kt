package com.jetbrains.datalore.plot.dsl.geom

import com.jetbrains.datalore.plot.dsl.Geom
import com.jetbrains.datalore.plot.dsl.Pos
import com.jetbrains.datalore.plot.dsl.Stat.identity
import com.jetbrains.datalore.plot.layer.LayerBase
import com.jetbrains.datalore.plot.layer.PosOptions
import com.jetbrains.datalore.plot.layer.StatOptions
import com.jetbrains.datalore.plot.layer.geom.TileAesthetics
import com.jetbrains.datalore.plot.layer.geom.TileMapping

@Suppress("ClassName")
class geom_tile(
    data: Any? = null,
    stat: StatOptions = identity,
    position: PosOptions = Pos.identity,
    show_legend: Boolean = true,
    sampling: Any? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val width: Double? = null,
    override val height: Double? = null,
    override val alpha: Double? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val size: Double? = null,
    mapping: TileMapping.() -> Unit = {}

) : TileAesthetics,
    LayerBase(
        mapping = TileMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.tile(),
        stat = stat,
        position = position,
        show_legend = show_legend,
        sampling = sampling
    )


