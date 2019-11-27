package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom.raster
import jetbrains.letsPlot.Pos.identity
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.RasterAesthetics
import jetbrains.letsPlot.intern.layer.geom.RasterMapping

@Suppress("ClassName")
class geom_raster(
    data: Any? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = identity,
    show_legend: Boolean = true,
    override val x: Double? = null,
    override val y: Double? = null,
    override val alpha: Double? = null,
    override val fill: Any? = null,
    mapping: RasterMapping.() -> Unit = {}

) : RasterAesthetics,
    LayerBase(
        mapping = RasterMapping().apply(mapping).seal(),
        data = data,
        geom = raster(),
        stat = stat,
        position = position,
        show_legend = show_legend
    )


