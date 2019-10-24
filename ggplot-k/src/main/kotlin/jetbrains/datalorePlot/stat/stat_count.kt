package jetbrains.datalorePlot.stat

import jetbrains.datalorePlot.intern.Options
import jetbrains.datalorePlot.Geom
import jetbrains.datalorePlot.Pos.stack
import jetbrains.datalorePlot.Stat
import jetbrains.datalorePlot.intern.layer.GeomOptions
import jetbrains.datalorePlot.intern.layer.LayerBase
import jetbrains.datalorePlot.intern.layer.PosOptions
import jetbrains.datalorePlot.intern.layer.stat.CountAesthetics
import jetbrains.datalorePlot.intern.layer.stat.CountMapping
import jetbrains.datalorePlot.intern.layer.stat.CountParameters

@Suppress("ClassName")
class stat_count(
    data: Any? = null,
    geom: GeomOptions = Geom.bar(),
    position: PosOptions = stack,
    show_legend: Boolean = true,
    sampling: Any? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val alpha: Double? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val width: Double? = null,
    override val size: Double? = null,
    override val weight: Double? = null,
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

