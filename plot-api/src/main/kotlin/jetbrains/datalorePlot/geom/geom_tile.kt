package jetbrains.datalorePlot.geom

import jetbrains.datalorePlot.Geom
import jetbrains.datalorePlot.Pos
import jetbrains.datalorePlot.Stat.identity
import jetbrains.datalorePlot.intern.layer.LayerBase
import jetbrains.datalorePlot.intern.layer.PosOptions
import jetbrains.datalorePlot.intern.layer.StatOptions
import jetbrains.datalorePlot.intern.layer.geom.TileAesthetics
import jetbrains.datalorePlot.intern.layer.geom.TileMapping

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


